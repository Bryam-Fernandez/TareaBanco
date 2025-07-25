package com.ejemplo.banco.service;

import com.ejemplo.banco.modelo.Movimiento;
import com.ejemplo.banco.modelo.Cuenta;
import com.ejemplo.banco.enums.TipoMovimiento;
import com.ejemplo.banco.repository.MovimientoRepository;

import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MovimientoService {

    private MovimientoRepository movimientoRepository;

    public void setEntityManager(EntityManager em) {
        this.movimientoRepository = new MovimientoRepository();
        this.movimientoRepository.setEntityManager(em);
    }

    @Transactional
    public void registrarMovimiento(Movimiento movimiento) {
        movimientoRepository.save(movimiento);
    }

    public Movimiento buscarPorId(Long id) {
        return movimientoRepository.findById(id);
    }

    public List<Movimiento> listarPorCuenta(Cuenta cuenta) {
        return movimientoRepository.findByCuenta(cuenta);
    }

    public List<Movimiento> listarPorTipo(TipoMovimiento tipo) {
        return movimientoRepository.findByTipoMovimiento(tipo);
    }

    public void actualizarMovimiento(Movimiento movimiento) {
        movimientoRepository.update(movimiento);
    }
}
