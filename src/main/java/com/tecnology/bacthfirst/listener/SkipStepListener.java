package com.tecnology.bacthfirst.listener;

import com.tecnology.bacthfirst.BacthConfiguration;
import com.tecnology.bacthfirst.model.Approval;
import com.tecnology.bacthfirst.model.dto.ApprovalDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

@Component
public class SkipStepListener implements SkipListener <ApprovalDTO, Approval>{

    private int quantityReadErrors;
    private int quantityWriteErrors;

    private static final Logger log = LoggerFactory.getLogger(BacthConfiguration.class);

    @Override
    public void onSkipInRead(Throwable throwable) {
        counterQuantityReadErrors();
        log.error(" El error fue: " + throwable.toString());
    }

    @Override
    public void onSkipInWrite(Approval approval, Throwable throwable) {
        counterQuantityWriteErrors();
        log.error("Error en la escritura de el dato " + approval.toString() + " El error fue: " + throwable.toString());
    }

    @Override
    public void onSkipInProcess(ApprovalDTO approvalDTO, Throwable throwable) {
        log.error("Error en la escritura de el dato " + approvalDTO.toString() + " El error fue: " + throwable.toString());
    }

    public void counterQuantityReadErrors(){
        this.quantityReadErrors = this.quantityReadErrors + 1;
    }
    public void counterQuantityWriteErrors(){
        this.quantityWriteErrors = this.quantityWriteErrors + 1;
    }

    public int getQuantityReadErrors() {
        return quantityReadErrors;
    }

    public void setQuantityReadErrors(int quantityReadErrors) {
        this.quantityReadErrors = quantityReadErrors;
    }

    public int getQuantityWriteErrors() {
        return quantityWriteErrors;
    }

    public void setQuantityWriteErrors(int quantityWriteErrors) {
        this.quantityWriteErrors = quantityWriteErrors;
    }
}
