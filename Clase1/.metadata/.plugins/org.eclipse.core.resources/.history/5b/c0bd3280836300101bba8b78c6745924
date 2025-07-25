package com.ejemplo.banco.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ejemplo.banco.enums.*;

@Entity
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String numeroTransaccion;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoMovimiento tipoMovimiento;

    @DecimalMin(value = "0.01", message = "El monto debe ser mayor que cero")
    @NotNull
    private BigDecimal monto;

    @NotNull
    private BigDecimal montoAnterior;

    @NotNull
    private BigDecimal montoNuevo;

    @NotBlank
    private String descripcion;

    @NotNull
    private LocalDateTime fechaTransaccion;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Canal canal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id", nullable = false)
    private Cuenta cuenta;

    private String cuentaDestino;
    
 // Getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}

	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public BigDecimal getMontoAnterior() {
		return montoAnterior;
	}

	public void setMontoAnterior(BigDecimal montoAnterior) {
		this.montoAnterior = montoAnterior;
	}

	public BigDecimal getMontoNuevo() {
		return montoNuevo;
	}

	public void setMontoNuevo(BigDecimal montoNuevo) {
		this.montoNuevo = montoNuevo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public String getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	} 



    
}