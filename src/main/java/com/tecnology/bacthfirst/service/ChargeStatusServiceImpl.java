package com.tecnology.bacthfirst.service;

import com.tecnology.bacthfirst.dao.ChargeStatusRepository;
import com.tecnology.bacthfirst.model.ChargeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChargeStatusServiceImpl implements IChargeStatusService {

    @Autowired
    private ChargeStatusRepository chargeStatusRepository;


    @Override
    @Transactional(readOnly = true)
    public List<ChargeStatus> findAll() {
        return (List<ChargeStatus>)  chargeStatusRepository.findAll();
    }

    @Override
    public void saveStatus(ChargeStatus chargeStatus) {
        chargeStatusRepository.saveStatus(chargeStatus.getReadSuccess(), chargeStatus.getReadError(), chargeStatus.getWriteSuccess(), chargeStatus.getWriteError(), chargeStatus.getTotalData(), chargeStatus.getUser());
    }
}
