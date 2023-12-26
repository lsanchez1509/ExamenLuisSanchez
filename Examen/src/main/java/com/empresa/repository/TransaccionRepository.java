package com.empresa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entity.TipoCambio;
import com.empresa.entity.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

    
}


