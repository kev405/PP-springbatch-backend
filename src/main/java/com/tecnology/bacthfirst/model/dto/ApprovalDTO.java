package com.tecnology.bacthfirst.model.dto;

import com.sun.istack.NotNull;

public class ApprovalDTO {

    public ApprovalDTO(int approvalTypeId, String simsValue, String superflexValue) {
        this.approvalTypeId = approvalTypeId;
        this.simsValue = simsValue;
        this.superflexValue = superflexValue;
    }

    public ApprovalDTO() {
    }

    public int getApprovalTypeId() {
        return approvalTypeId;
    }

    public void setApprovalTypeId(int approvalTypeId) {
        this.approvalTypeId = approvalTypeId;
    }

    public String getSimsValue() {
        return simsValue;
    }

    public void setSimsValue(String simsValue) {
        this.simsValue = simsValue;
    }

    public String getSuperflexValue() {
        return superflexValue;
    }

    public void setSuperflexValue(String superflexValue) {
        this.superflexValue = superflexValue;
    }

    @NotNull
    private int approvalTypeId;

    private String simsValue;

    private String superflexValue;

    @Override
    public String toString() {
        return "ApprovalDTO{" +
                "approvalTypeId=" + approvalTypeId +
                ", simsValue='" + simsValue + '\'' +
                ", superflexValue='" + superflexValue + '\'' +
                '}';
    }
}
