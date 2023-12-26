package com.empresa.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.TipoCambio;
import com.empresa.entity.Transaccion;
import com.empresa.repository.TipoCambioRepository;
import com.empresa.repository.TransaccionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {

	@Autowired
	private TipoCambioRepository repository;
	
	@Autowired
	private TransaccionRepository repositoryTrs;
	
	/*
	 * public Flux<TipoCambio> obtenerTodos() { return
	 * Flux.fromIterable(repository.findAll()); }
	 * 
	 * public Mono<TipoCambio> realizarTipoCambio(TipoCambio tipoCambio) {
	 * //tipoCambio.setFechaCreacion(LocalDateTime.now()); return
	 * Mono.fromCallable(() -> tipoCambioRepository.save(tipoCambio)); }
	 */

	public List<TipoCambio> obtenerTodos() {
		// TODO Auto-generated method stub
        return repository.findAll();
    }

	public Transaccion realizarTipoCambio(Transaccion transaccion) {
		// TODO Auto-generated method stub
		Optional<TipoCambio> optionalTipoCambio = repository.findByMonedaOrigenAndMonedaDestino(
                transaccion.getMonedaOrigen(), transaccion.getMonedaDestino());

        if (optionalTipoCambio.isPresent()) {
            TipoCambio tipoCambio = optionalTipoCambio.get();
            transaccion.setTipoCambio(tipoCambio.getTipoCambio());
            transaccion.setMontoDestino(transaccion.getMontoOrigen() * tipoCambio.getTipoCambio());
            transaccion.setFechaCreacion(LocalDateTime.now());

            return repositoryTrs.save(transaccion);
        } else {
            throw new NoSuchElementException("Primero registre el tipo de cambio para las monedas especificadas.");
        }

    }
	
	public TipoCambio actualizarTipoCambio(String monedaOrigen, String monedaDestino, Double nuevoTipoCambio) {
		// TODO Auto-generated method stub
		return repository.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino)
	            .map(tipoCambio -> {
	                tipoCambio.setTipoCambio(nuevoTipoCambio);
	                tipoCambio.setFechaCreacion(LocalDateTime.now());
	                return repository.save(tipoCambio);
                })
	            .orElseGet(() -> {
	                TipoCambio nuevoTipoCambioObj = new TipoCambio();
	                nuevoTipoCambioObj.setMonedaOrigen(monedaOrigen);
	                nuevoTipoCambioObj.setMonedaDestino(monedaDestino);
	                nuevoTipoCambioObj.setTipoCambio(nuevoTipoCambio);
	                nuevoTipoCambioObj.setFechaCreacion(LocalDateTime.now());
	                return repository.save(nuevoTipoCambioObj);
	            });
    }
	
	public List<Transaccion> obtenerTransacciones() {
		// TODO Auto-generated method stub
        return repositoryTrs.findAll();
    }
	
	
}
