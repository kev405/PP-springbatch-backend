package com.tecnology.bacthfirst.service;

import com.tecnology.bacthfirst.model.ChargeStatus;

import java.util.List;

public interface IBatchService {

    public List<ChargeStatus> findAll();

    public void saveStatus(ChargeStatus chargeStatus);
}
