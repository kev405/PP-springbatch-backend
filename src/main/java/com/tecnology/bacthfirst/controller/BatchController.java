package com.tecnology.bacthfirst.controller;

import com.tecnology.bacthfirst.model.ChargeStatus;
import com.tecnology.bacthfirst.service.IBatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
public class BatchController {

    @Autowired
    private IBatchService bacthService;

    @Autowired
    JobLauncher procesarArchivoSubido;

    @Autowired
    Job importApprovalJob;

    private static final Logger log = LoggerFactory.getLogger(BatchController.class);

    @PostMapping("/upload/{user}")
    public String uploadFile(@PathVariable("user") String user, @RequestParam("file")MultipartFile file)
            throws IOException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        if (file == null || file.isEmpty()) {
            log.info("No Hay Archivo");

            return "No Hay Archivo";
        }

        JobExecution jobExecution = procesarArchivoSubido
                .run(importApprovalJob, new JobParametersBuilder()
                        .addLong("idInicio", System.nanoTime())
                        .addString("nameFile", file.getOriginalFilename())
                        .addString("user", user)
                        .toJobParameters());

        log.info("el archivo es: " + file.getOriginalFilename());

        StringBuilder builder = new StringBuilder();
        //builder.append(System.getProperty(".//src//main//resources//files//"));
        //builder.append(File.separator);
        //builder.append("spring_upload_example");
        //builder.append(File.separator);
        //builder.append(file.getOriginalFilename());

        byte[] fileBytes = file.getBytes();
        Path path = Paths.get(".//src//main//resources//" + file.getOriginalFilename());
        Files.write(path, fileBytes);

        return "Archivo Guardado";
    }

    @GetMapping("/listar")
    public List<ChargeStatus> listar(){
        return bacthService.findAll();
    }

}
