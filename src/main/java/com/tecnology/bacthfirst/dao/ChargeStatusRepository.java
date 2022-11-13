package com.tecnology.bacthfirst.dao;

import com.tecnology.bacthfirst.model.ChargeStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ChargeStatusRepository extends CrudRepository<ChargeStatus, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO public.charge_status(\n" +
            "\tread_success, read_error, write_success, write_error, total_data, \"user\")\n" +
            "\tVALUES (:readSuccess, :readError, :writeSucces, :writeError, :totalData, :user);")
    void saveStatus(@Param("readSuccess") String readSuccess, @Param("readError") String readError, @Param("writeSucces") String writeSucces,
                           @Param("writeError") String writeError, @Param("totalData") String totalData, @Param("user") String user);
}
