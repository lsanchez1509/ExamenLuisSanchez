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
/* @Table(name = "tb_tipo_cambio") */
@Data
public class TipoCambio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoCambio;
	
	private String monedaOrigen;
	
    private String monedaDestino;
	
    private Double tipoCambio;
	
	private String usuarioCreacion;
	
	private LocalDateTime fechaCreacion;
	
}
