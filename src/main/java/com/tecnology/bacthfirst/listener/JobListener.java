package com.tecnology.bacthfirst.listener;

import com.tecnology.bacthfirst.model.persona;
import com.tecnology.bacthfirst.processor.PersonaItemProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(PersonaItemProcessor.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JobListener(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.info("Finalizo El Job Exitosamente! Verifica los resultados: ");

            jdbcTemplate.query("Select primer_nombre, segundo_nombre, telefono FROM persona", (rs, row) ->
                    new persona(rs.getString(1), rs.getString(2), rs.getString(3)))
                    .forEach(persona -> log.info("Registro <" + persona.toString() + ">"));
        }
    }
}
