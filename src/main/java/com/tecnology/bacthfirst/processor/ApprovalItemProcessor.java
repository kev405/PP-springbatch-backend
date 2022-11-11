package com.tecnology.bacthfirst.processor;

import com.tecnology.bacthfirst.model.Approval;
import com.tecnology.bacthfirst.model.dto.ApprovalDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.TimeZone;

public class ApprovalItemProcessor implements ItemProcessor<ApprovalDTO, Approval> {

    private static final Logger log = LoggerFactory.getLogger(ApprovalItemProcessor.class);

    @Override
    public Approval process(ApprovalDTO item) throws Exception {

        Date date = new Date();

        Timestamp timestamp = new Timestamp(date.getTime());

        Approval approval = new Approval(item.getApprovalTypeId(), item.getSimsValue(), item.getSuperflexValue(), timestamp , null, null);

        return approval;
    }
}
