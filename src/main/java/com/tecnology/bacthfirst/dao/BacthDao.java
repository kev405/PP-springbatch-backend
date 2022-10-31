package com.tecnology.bacthfirst.dao;

import com.tecnology.bacthfirst.model.Persona;
import org.springframework.data.repository.CrudRepository;

public interface BacthDao extends CrudRepository<Persona, Long> {
}
