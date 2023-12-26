package com.empresa.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.ErrorResponse;
import com.empresa.entity.TipoCambio;
import com.empresa.entity.Transaccion;
import com.empresa.jwt.JwtUtil;
import com.empresa.service.TipoCambioService;
import com.empresa.util.Constantes;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tipocambio")
@CrossOrigin(origins = "http://localhost:4200")
public class ApiTipoCambioController {

	@Autowired
	private TipoCambioService tipoCambioService;
		
	@PostMapping("/realizar")
	public ResponseEntity<?> realizarTipoCambio(@RequestBody Transaccion transaccion) {
	    try {
	        Transaccion resultado = tipoCambioService.realizarTipoCambio(transaccion);
	        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	    } catch (NoSuchElementException e) {
	        ErrorResponse errorResponse = new ErrorResponse("Primero registre el tipo de cambio para las monedas especificadas.");
	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }
	}
	
	@GetMapping("/listar-transacciones")
    public ResponseEntity<List<Transaccion>> listarTransacciones() {
        List<Transaccion> transacciones = tipoCambioService.obtenerTransacciones();
        return new ResponseEntity<>(transacciones, HttpStatus.OK);
    }
	
	@PostMapping("/actualizar-tipo-cambio")
    public ResponseEntity<TipoCambio> actualizarCrearTipoCambio(@RequestBody TipoCambio tipoCambio) {
        TipoCambio resultado = tipoCambioService.actualizarTipoCambio(tipoCambio.getMonedaOrigen(), tipoCambio.getMonedaDestino(), tipoCambio.getTipoCambio());
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/obtener-tipo-cambio")
    public ResponseEntity<List<TipoCambio>> obtenerTodos() {
        List<TipoCambio> tiposCambio = tipoCambioService.obtenerTodos();
        return new ResponseEntity<>(tiposCambio, HttpStatus.OK);
    }
	
}
