package com.ejemplo.banco.repository;

import com.ejemplo.banco.modelo.Cuenta;
import com.ejemplo.banco.enums.TipoCuenta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CuentaRepository {

    private EntityManager em;

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public Cuenta findById(Long id) {
        return em.find(Cuenta.class, id);
    }

    public Cuenta findByNumeroCuenta(String numeroCuenta) {
        TypedQuery<Cuenta> query = em.createQuery(
            "SELECT c FROM Cuenta c WHERE c.numeroCuenta = :numero", Cuenta.class
        );
        query.setParameter("numero", numeroCuenta);
        return query.getSingleResult();
    }

    public List<Cuenta> findByTipoCuenta(TipoCuenta tipoCuenta) {
        return em.createQuery(
            "SELECT c FROM Cuenta c WHERE c.tipoCuenta = :tipo", Cuenta.class
        ).setParameter("tipo", tipoCuenta).getResultList();
    }

    public void save(Cuenta cuenta) {
        em.persist(cuenta);
    }

    public void update(Cuenta cuenta) {
        em.merge(cuenta);
    }
}
