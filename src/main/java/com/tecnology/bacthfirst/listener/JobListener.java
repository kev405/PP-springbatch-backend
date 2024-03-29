package com.tecnology.bacthfirst.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class JobListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobListener.class);


    @Override
    public void afterJob(JobExecution jobExecution) {

            File archivo = new File( ".//src//main//resources//" + jobExecution.getJobParameters().getParameters().get("nameFile").toString());


            boolean estatus = archivo.delete();

            if (!estatus) {

                System.out.println("Error no se ha podido eliminar el  archivo");

            }else{

                System.out.println("Se ha eliminado el archivo exitosamente");

            }

        if(jobExecution.getStatus() == BatchStatus.COMPLETED){

            log.info("Finalizo El Job Exitosamente! Verifica los resultados: ");

//            jdbcTemplate.query("Select nombre1, nombre2, telephone FROM persona", (rs, row) ->
//                    new Persona(rs.getString(1), rs.getString(2), rs.getString(3)))
//                    .forEach(persona -> log.info("Registro <" + persona.toString() + ">"));
        }
    }
}
