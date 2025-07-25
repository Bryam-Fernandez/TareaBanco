package com.ejemplo.banco.service;

import com.ejemplo.banco.modelo.Cliente;
import com.ejemplo.banco.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;

public class ClienteService {

    private EntityManager em;
    private ClienteRepository clienteRepository;

    public void setEntityManager(EntityManager em) {
        this.em = em;
        this.clienteRepository = new ClienteRepository(em); 
    }

    @Transactional
    public void registrarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente buscarPorDocumento(String numeroDocumento) {
        return clienteRepository.findByNumeroDocumento(numeroDocumento);
    }
}
