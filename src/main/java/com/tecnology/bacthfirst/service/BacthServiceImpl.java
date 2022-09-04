package com.tecnology.bacthfirst.service;

import com.tecnology.bacthfirst.dao.BacthDao;
import com.tecnology.bacthfirst.model.persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BacthServiceImpl implements IBatchService{

    @Autowired
    private BacthDao bacthDao;


    @Override
    @Transactional(readOnly = true)
    public List<persona> findAll() {
        return (List<persona>)  bacthDao.findAll();
    }
}
