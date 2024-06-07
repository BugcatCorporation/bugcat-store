package com.bugcatcorp.app_bugcat_store.repository;

import com.bugcatcorp.app_bugcat_store.model.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago,Long> {
}
