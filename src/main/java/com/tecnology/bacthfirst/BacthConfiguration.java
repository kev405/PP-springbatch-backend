package com.tecnology.bacthfirst;

import com.tecnology.bacthfirst.listener.JobListener;
import com.tecnology.bacthfirst.model.persona;
import com.tecnology.bacthfirst.processor.PersonaItemProcessor;
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
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BacthConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BacthConfiguration.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    private String fileInput = "sample-data.csv";

    @Bean
    @StepScope
    public FlatFileItemReader<persona> reader(@Value("#{jobParameters}") Map jobParameters){

        log.info(jobParameters.get("nameFile").toString());

        return new FlatFileItemReaderBuilder<persona>()
                .name("personaItemReader")
                .resource(new ClassPathResource(jobParameters.get("nameFile").toString()))
                .delimited()
                .names(new String[] {"primerNombre", "segundoNombre", "telefono"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<persona>(){{
                    setTargetType(persona.class);
                }})
                .build();
    }

    @Bean
    public PersonaItemProcessor processor(){
        return new PersonaItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<persona> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<persona>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO persona (primer_nombre, segundo_nombre, telefono) VALUES (:primerNombre, :segundoNombre, :telefono)")
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
    public Job importPersonaJob(JobListener listener, Step step1){
        return jobBuilderFactory.get("importPersonaJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<persona> writer){
        return stepBuilderFactory.get("step1")
                .<persona, persona> chunk(10)
                .reader(reader(null))
                .processor(processor())
                .writer(writer)
                .build();
    }
}
