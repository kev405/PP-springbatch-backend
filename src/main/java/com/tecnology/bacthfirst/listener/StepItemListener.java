package com.tecnology.bacthfirst.listener;


import com.tecnology.bacthfirst.model.ChargeStatus;
import com.tecnology.bacthfirst.service.IBatchService;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StepItemListener implements StepExecutionListener {

    @Autowired
    SkipStepListener skipStepListener;

    @Autowired
    IBatchService batchService;

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        System.out.println(" " + stepExecution.getWriteCount() + " " + stepExecution.getReadCount() + " " + skipStepListener.getQuantityReadErrors()+ " " + skipStepListener.getQuantityWriteErrors() + " " + stepExecution.getJobParameters().getParameters().get("user"));

        ChargeStatus chargeStatus = new ChargeStatus(String.valueOf(stepExecution.getReadCount()), String.valueOf(skipStepListener.getQuantityReadErrors()), String.valueOf(stepExecution.getWriteCount()), String.valueOf(skipStepListener.getQuantityWriteErrors()), String.valueOf((stepExecution.getReadCount() + skipStepListener.getQuantityReadErrors())), stepExecution.getJobParameters().getParameters().get("user").toString());

//        bacthDao.saveStatus(String.valueOf(stepExecution.getReadCount()), String.valueOf(skipStepListener.getQuantityReadErrors()), String.valueOf(stepExecution.getWriteCount()), String.valueOf(skipStepListener.getQuantityWriteErrors()), String.valueOf((stepExecution.getReadCount() + skipStepListener.getQuantityReadErrors())), stepExecution.getJobParameters().getParameters().get("user").toString());

        batchService.saveStatus(chargeStatus);

        skipStepListener.setQuantityReadErrors(0);
        skipStepListener.setQuantityWriteErrors(0);

        return stepExecution.getExitStatus();
    }
}
