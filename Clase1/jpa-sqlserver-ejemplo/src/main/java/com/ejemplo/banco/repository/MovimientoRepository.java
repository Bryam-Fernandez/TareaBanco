package com.ejemplo.banco.repository;

import com.ejemplo.banco.modelo.Movimiento;
import com.ejemplo.banco.modelo.Cuenta;
import com.ejemplo.banco.enums.TipoMovimiento;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class MovimientoRepository {

	private EntityManager em;

	public void setEntityManager(EntityManager em) {
	    this.em = em;
	}


    public Movimiento findById(Long id) {
        return em.find(Movimiento.class, id);
    }

    public List<Movimiento> findByCuenta(Cuenta cuenta) {
        return em.createQuery(
            "SELECT m FROM Movimiento m WHERE m.cuenta = :cuenta ORDER BY m.fechaMovimiento DESC", Movimiento.class
        ).setParameter("cuenta", cuenta).getResultList();
    }

    public List<Movimiento> findByTipoMovimiento(TipoMovimiento tipo) {
        return em.createQuery(
            "SELECT m FROM Movimiento m WHERE m.tipoMovimiento = :tipo", Movimiento.class
        ).setParameter("tipo", tipo).getResultList();
    }

    public void save(Movimiento movimiento) {
        em.persist(movimiento);
    }

    public void update(Movimiento movimiento) {
        em.merge(movimiento);
    }
}
