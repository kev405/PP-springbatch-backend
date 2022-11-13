package com.tecnology.bacthfirst.model;

import javax.persistence.*;

@Entity
@Table(name = "charge_status")
public class ChargeStatus {

    @Column(name = "read_success")
    private String readSuccess;
    @Column(name = "read_error")
    private String readError;

    @Column(name = "write_success")
    private String writeSuccess;

    @Column(name = "write_error")
    private String writeError;

    @Column(name = "total_data")
    private String totalData;

    @Column(name = "user")
    private String user;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ChargeStatus() {
        super();
    }

    public ChargeStatus(String readSuccess, String readError, String writeSuccess, String writeError, String totalData, String user) {
        this.readSuccess = readSuccess;
        this.readError = readError;
        this.writeSuccess = writeSuccess;
        this.writeError = writeError;
        this.totalData = totalData;
        this.user = user;
    }

    public String getReadSuccess() {
        return readSuccess;
    }

    public void setReadSuccess(String readSuccess) {
        this.readSuccess = readSuccess;
    }

    public String getReadError() {
        return readError;
    }

    public void setReadError(String readError) {
        this.readError = readError;
    }

    public String getWriteSuccess() {
        return writeSuccess;
    }

    public void setWriteSuccess(String writeSuccess) {
        this.writeSuccess = writeSuccess;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getWriteError() {
        return writeError;
    }

    public void setWriteError(String writeError) {
        this.writeError = writeError;
    }

    public String getTotalData() {
        return totalData;
    }

    public void setTotalData(String totalData) {
        this.totalData = totalData;
    }

    @Override
    public String toString() {
        return "ChargeStatus{" +
                "readSuccess='" + readSuccess + '\'' +
                ", readError='" + readError + '\'' +
                ", writeSuccess='" + writeSuccess + '\'' +
                ", writeError='" + writeError + '\'' +
                ", totalData='" + totalData + '\'' +
                '}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
