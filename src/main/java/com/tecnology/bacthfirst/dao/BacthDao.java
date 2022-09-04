package com.tecnology.bacthfirst.dao;

import com.tecnology.bacthfirst.model.persona;
import org.springframework.data.repository.CrudRepository;

public interface BacthDao extends CrudRepository<persona, Long> {
}
