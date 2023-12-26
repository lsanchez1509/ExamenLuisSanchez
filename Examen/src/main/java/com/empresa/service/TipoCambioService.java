package com.empresa.service;

import java.util.List;

import com.empresa.entity.TipoCambio;
import com.empresa.entity.Transaccion;

public interface TipoCambioService {

	public abstract List<TipoCambio> obtenerTodos();

	public abstract TipoCambio actualizarTipoCambio(String monedaOrigen, String monedaDestino, Double nuevoTipoCambio);
	
	public abstract Transaccion realizarTipoCambio(Transaccion obj);
	
	public abstract List<Transaccion> obtenerTransacciones();

}
