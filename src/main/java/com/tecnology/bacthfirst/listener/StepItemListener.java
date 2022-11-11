package com.tecnology.bacthfirst.listener;


import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StepItemListener implements StepExecutionListener {

    @Autowired
    SkipStepListener skipStepListener;

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        System.out.println(" " + stepExecution.getWriteCount() + " " + stepExecution.getReadCount() + " " + skipStepListener.getQuantityReadErrors());

        skipStepListener.setQuantityReadErrors(0);

        return stepExecution.getExitStatus();
    }
}
