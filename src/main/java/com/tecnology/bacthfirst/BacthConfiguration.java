package com.tecnology.bacthfirst;

import com.tecnology.bacthfirst.listener.JobListener;
import com.tecnology.bacthfirst.listener.SkipStepListener;
import com.tecnology.bacthfirst.listener.StepItemListener;
import com.tecnology.bacthfirst.model.Approval;
import com.tecnology.bacthfirst.model.dto.ApprovalDTO;
import com.tecnology.bacthfirst.processor.ApprovalItemProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.dao.DuplicateKeyException;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BacthConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BacthConfiguration.class);

//    @Autowired
//    PersonaRepository approvalRepository;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @StepScope
    public FlatFileItemReader<ApprovalDTO> reader(@Value("#{jobParameters}") Map jobParameters){

        log.info(jobParameters.get("nameFile").toString());
            return new FlatFileItemReaderBuilder<ApprovalDTO>()
                    .name("approvalItemReader")
                    .resource(new FileSystemResource(".//src//main//resources//" + jobParameters.get("nameFile").toString()))
                    .delimited()
                    .names(new String[] {"approvalTypeId", "simsValue", "superflexValue"})
                    .fieldSetMapper(new BeanWrapperFieldSetMapper<ApprovalDTO>(){{
                        setTargetType(ApprovalDTO.class);
                    }})
                    .build();
    }

    @Bean
    public ApprovalItemProcessor processor(){
        return new ApprovalItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Approval> writer(DataSource dataSource){
            return new JdbcBatchItemWriterBuilder<Approval>()
                    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                    .sql("INSERT INTO public.approval(\n" +
                            "\tapproval_type_id, sims_value, superflex_value, created_at, updated_at, \"user\")\n" +
                            "\tVALUES (:approvalTypeId, :simsValue, :superflexValue, :createdAt, :updatedAt, :user);")
                    .dataSource(dataSource)
                    .build();

    }

    @Bean
    public JobLauncher procesarArchivoSubido() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();

        return jobLauncher;
    }

    @Bean
    public Job importApprovalJob(JobListener listener, Step step1){
        return jobBuilderFactory.get("importApprovalJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepItemListener stepItemListener, SkipStepListener skipStepListener, JdbcBatchItemWriter<Approval> writer){
        return stepBuilderFactory.get("step1")
                .<ApprovalDTO, Approval> chunk(1)
                .reader(reader(null))
                .processor(processor())
                .writer(writer)
                .faultTolerant()
                .skipLimit(1000)
                .skip(DuplicateKeyException.class)
                .skip(FlatFileParseException.class)
                .listener(skipStepListener)
                .listener(stepItemListener)
                .build();
    }
}
