package com.ejemplo.banco.main;

import com.ejemplo.banco.modelo.*;
import com.ejemplo.banco.enums.*;
import com.ejemplo.banco.service.*;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class BancoMain {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoPU");
    private static final EntityManager em = emf.createEntityManager();

    private static final ClienteService clienteService = new ClienteService();
    private static final CuentaService cuentaService = new CuentaService();
    private static final MovimientoService movimientoService = new MovimientoService();

    public static void main(String[] args) {
        clienteService.setEntityManager(em);
        cuentaService.setEntityManager(em);
        movimientoService.setEntityManager(em);

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== MENÚ BANCO ===");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Crear Cuenta");
            System.out.println("3. Realizar Movimiento");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
            case 1:
                registrarCliente(sc);
                break;
            case 2:
                registrarCuenta(sc);
                break;
            case 3:
                registrarMovimiento(sc);
                break;
            case 4:
                System.out.println("Hasta luego.");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }


        } while (opcion != 4);

        em.close();
        emf.close();
        sc.close();
    }

    private static void registrarCliente(Scanner sc) {
        System.out.println("\n=== REGISTRO DE CLIENTE ===");

        System.out.print("Número de documento: ");
        String numeroDocumento = sc.nextLine();

        System.out.print("Tipo de documento (DNI, PASAPORTE, CARNET): ");
        String tipoDocStr = sc.nextLine().toUpperCase();
        TipoDocumento tipoDocumento = TipoDocumento.valueOf(tipoDocStr); 

        System.out.print("Nombres: ");
        String nombres = sc.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
        String fechaNacStr = sc.nextLine();
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacStr);

        
        Cliente cliente = new Cliente();
        cliente.setNumeroDocumento(numeroDocumento);
        cliente.setTipoDocumento(tipoDocumento);
        cliente.setNombres(nombres);
        cliente.setApellidos(apellidos);
        cliente.setEmail(email);
        cliente.setTelefono(telefono);
        cliente.setFechaNacimiento(fechaNacimiento);
        // No seteas estado ni fechaRegistro porque @PrePersist lo hace

        em.getTransaction().begin();
        clienteService.registrarCliente(cliente); 
        em.getTransaction().commit();

        System.out.println("Cliente registrado exitosamente.");
    }


    private static void registrarCuenta(Scanner sc) {
        System.out.println("=== CREAR CUENTA ===");
        System.out.print("Número de documento del cliente: ");
        String documento = sc.nextLine();

        Cliente cliente = clienteService.buscarPorDocumento(documento);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("Número de cuenta: ");
        String numeroCuenta = sc.nextLine();

        System.out.print("Tipo de cuenta (AHORRO/CORRIENTE): ");
        TipoCuenta tipo = TipoCuenta.valueOf(sc.nextLine().toUpperCase());

        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(cliente);
        cuenta.setNumeroCuenta(numeroCuenta);
        cuenta.setTipoCuenta(tipo);
        cuenta.setSaldoActual(BigDecimal.valueOf(1500.50));
        em.getTransaction().begin();
        cuentaService.registrarCuenta(cuenta);
        em.getTransaction().commit();

        System.out.println("Cuenta creada.");
    }

    private static void registrarMovimiento(Scanner sc) {
        System.out.println("=== MOVIMIENTO ===");
        System.out.print("Número de cuenta: ");
        String numeroCuenta = sc.nextLine();

        Cuenta cuenta = cuentaService.buscarPorNumeroCuenta(numeroCuenta);
        if (cuenta == null) {
            System.out.println("Cuenta no encontrada.");
            return;
        }

        System.out.print("Tipo de movimiento (DEPOSITO/RETIRO): ");
        TipoMovimiento tipo = TipoMovimiento.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Monto: ");
        double monto = sc.nextDouble();
        sc.nextLine(); 
        
        System.out.print("Número de transacción: ");
        String numeroTransaccion = sc.nextLine();

        Movimiento mov = new Movimiento();
        mov.setCuenta(cuenta);
        mov.setTipoMovimiento(tipo);
        mov.setMonto(BigDecimal.valueOf(monto));
        mov.setNumeroTransaccion(numeroTransaccion);

        mov.setDescripcion(tipo + " realizado");
     
        if (tipo == TipoMovimiento.RETIRO && cuenta.getSaldoActual().doubleValue() < monto) {
            System.out.println("Saldo insuficiente.");
            return;
        }

        if (tipo == TipoMovimiento.DEPOSITO) {
            cuenta.setSaldoActual(cuenta.getSaldoActual().add(BigDecimal.valueOf(monto)));
        } else {
            cuenta.setSaldoActual(cuenta.getSaldoActual().subtract(BigDecimal.valueOf(monto)));
        }

        em.getTransaction().begin();
        movimientoService.registrarMovimiento(mov);
        cuentaService.actualizarCuenta(cuenta);
        em.getTransaction().commit();

        System.out.println("Movimiento realizado.");
    }
}
