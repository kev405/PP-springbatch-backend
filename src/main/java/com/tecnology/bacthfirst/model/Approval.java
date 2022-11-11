package com.tecnology.bacthfirst.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "approval")
public class Approval implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "approval_type_id")
    @NotNull
    private int approvalTypeId;

    @Column(name = "sims_value")
    private String simsValue;

    @Column(name = "superflex_value")
    private String superflexValue;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "user")
    private String user;

    public Approval() {
        super();
    }

    public Approval(int approvalTypeId, String simsValue, String superflexValue, Timestamp createdAt, Timestamp updatedAt, String user) {
        this.approvalTypeId = approvalTypeId;
        this.simsValue = simsValue;
        this.superflexValue = superflexValue;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public int getApprovalTypeId() {
        return approvalTypeId;
    }

    public String getSimsValue() {
        return simsValue;
    }

    public String getSuperflexValue() {
        return superflexValue;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public String getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setApprovalTypeId(int approvalTypeId) {
        this.approvalTypeId = approvalTypeId;
    }

    public void setSimsValue(String simsValue) {
        this.simsValue = simsValue;
    }

    public void setSuperflexValue(String superflexValue) {
        this.superflexValue = superflexValue;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Approval{" +
                "id=" + id +
                ", approvalTypeId=" + approvalTypeId +
                ", simsValue='" + simsValue + '\'' +
                ", superflexValue='" + superflexValue + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user='" + user + '\'' +
                '}';
    }
}
