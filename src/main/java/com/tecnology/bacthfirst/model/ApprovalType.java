package com.tecnology.bacthfirst.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "approval_type")
public class ApprovalType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name_approval")
    private String nameApproval;

    @Column(name = "description")
    private Integer description;

    @Column(name = "create_at")
    private Timestamp createAt;

    @Column(name = "update_at")
    private Timestamp updateAt;

    @Column(name = "user")
    private String user;

    public Long getId() {
        return id;
    }

    public String getNameApproval() {
        return nameApproval;
    }

    public Integer getDescription() {
        return description;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public String getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNameApproval(String nameApproval) {
        this.nameApproval = nameApproval;
    }

    public void setDescription(Integer description) {
        this.description = description;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
