package com.empresa.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
public class Transaccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTransaccion;
	
	private String monedaOrigen;
	
    private String monedaDestino;
	
    private Double tipoCambio;
    
    private Double montoOrigen;
    
    private Double montoDestino;
	
	private String usuarioCreacion;
	
	private LocalDateTime fechaCreacion;
	
}
