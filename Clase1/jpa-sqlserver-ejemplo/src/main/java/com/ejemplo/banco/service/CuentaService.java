package com.ejemplo.banco.service;

import com.ejemplo.banco.modelo.Cuenta;
import com.ejemplo.banco.enums.TipoCuenta;
import com.ejemplo.banco.repository.CuentaRepository;

import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CuentaService {

    private CuentaRepository cuentaRepository;


    public void setEntityManager(EntityManager em) {
        this.cuentaRepository = new CuentaRepository();
        this.cuentaRepository.setEntityManager(em);
    }

    @Transactional
    public void registrarCuenta(Cuenta cuenta) {
        cuentaRepository.save(cuenta);
    }

    public Cuenta buscarPorNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }

    public List<Cuenta> buscarPorTipoCuenta(TipoCuenta tipo) {
        return cuentaRepository.findByTipoCuenta(tipo);
    }

    public Cuenta buscarPorId(Long id) {
        return cuentaRepository.findById(id);
    }

    public void actualizarCuenta(Cuenta cuenta) {
        cuentaRepository.update(cuenta);
    }
}
