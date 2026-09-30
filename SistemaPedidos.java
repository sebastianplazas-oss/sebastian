package com.miempresa;

import com.miempresa.ClienteNoEncontradoException;
import com.miempresa.DatosInvalidosException;
import com.miempresa.IdDuplicadoException;
import com.miempresa.PedidoNoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID; // Para generar IDs únicos fácilmente

public class SistemaPedidos {
    private Map<String, Cliente> clientes;
    private Map<String, Pedido> pedidos;

    public SistemaPedidos() {
        this.clientes = new HashMap<>();
        this.pedidos = new HashMap<>();
    }

    /**
     * Registra un nuevo cliente en el sistema.
    * Lanza DatosInvalidosException si los datos del cliente no son válidos.
    * Lanza IdDuplicadoException si el ID del cliente ya existe.
    */
    public void registrarCliente(Cliente cliente) {
        if (cliente == null || cliente.getId() == null || cliente.getId().trim().isEmpty() ||
            cliente.getNombre() == null || cliente.getNombre().trim().isEmpty() ||
            cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            throw new DatosInvalidosException("Datos del cliente incompletos o inválidos.");
        }
        if (clientes.containsKey(cliente.getId())) {
            throw new IdDuplicadoException("El cliente con ID '" + cliente.getId() + "' ya existe.");
        }
        clientes.put(cliente.getId(), cliente);
        System.out.println("Cliente registrado exitosamente: " + cliente.getNombre() + " (ID: " + cliente.getId() + ")");
    }

    /**
     * Crea un nuevo pedido para un cliente existente.
    * Lanza DatosInvalidosException si los datos del pedido no son válidos.
    * Lanza ClienteNoEncontradoException si el cliente no existe.
    * Lanza IdDuplicadoException si el ID del pedido generado ya existe (muy improbable con UUID).
    */
    public Pedido crearPedido(String clienteId, double total, List<String> detalles) throws ClienteNoEncontradoException {
        if (clienteId == null || clienteId.trim().isEmpty() || total <= 0 || detalles == null || detalles.isEmpty()) {
            throw new DatosInvalidosException("Datos del pedido incompletos o inválidos.");
        }
        if (!clientes.containsKey(clienteId)) {
            throw new ClienteNoEncontradoException("El cliente con ID '" + clienteId + "' no existe.");
        }

        String pedidoId = UUID.randomUUID().toString(); // Genera un ID único para el pedido
        if (pedidos.containsKey(pedidoId)) { // Aunque UUID es muy improbable, buena práctica verificar
            throw new IdDuplicadoException("ID de pedido generado duplicado. Intente de nuevo.");
        }

        Pedido nuevoPedido = new Pedido(pedidoId, clienteId, LocalDate.now(), total, detalles);
        pedidos.put(pedidoId, nuevoPedido);
        System.out.println("Pedido creado exitosamente: " + nuevoPedido.getId() + " para Cliente: " + clienteId);
        return nuevoPedido;
    }

    /**
     * Busca un cliente por su ID.
    * Lanza DatosInvalidosException si el ID es nulo o vacío.
    * Lanza ClienteNoEncontradoException si el cliente no existe.
    */
    public Cliente buscarCliente(String id) throws ClienteNoEncontradoException {
        if (id == null || id.trim().isEmpty()) {
            throw new DatosInvalidosException("El ID del cliente no puede ser nulo o vacío.");
        }
        Cliente cliente = clientes.get(id);
        if (cliente == null) {
            throw new ClienteNoEncontradoException("Cliente con ID '" + id + "' no encontrado.");
        }
        return cliente;
    }

    /**
     * Busca un pedido por su ID.
    * Lanza DatosInvalidosException si el ID es nulo o vacío.
    * Lanza PedidoNoEncontradoException si el pedido no existe.
    */
    public Pedido buscarPedido(String id) throws PedidoNoEncontradoException {
        if (id == null || id.trim().isEmpty()) {
            throw new DatosInvalidosException("El ID del pedido no puede ser nulo o vacío.");
        }
        Pedido pedido = pedidos.get(id);
        if (pedido == null) {
            throw new PedidoNoEncontradoException("Pedido con ID '" + id + "' no encontrado.");
        }
        return pedido;
    }

    /**
     * Busca todos los pedidos realizados por un cliente específico.
    * Lanza DatosInvalidosException si el ID del cliente es nulo o vacío.
    * Lanza ClienteNoEncontradoException si el cliente no existe.
    */
    public List<Pedido> buscarPedidosPorCliente(String clienteId) throws ClienteNoEncontradoException {
        if (clienteId == null || clienteId.trim().isEmpty()) {
            throw new DatosInvalidosException("El ID del cliente no puede ser nulo o vacío.");
        }
        if (!clientes.containsKey(clienteId)) {
            throw new ClienteNoEncontradoException("El cliente con ID '" + clienteId + "' no existe para buscar sus pedidos.");
        }

        List<Pedido> pedidosCliente = new ArrayList<>();
        for (Pedido pedido : pedidos.values()) {
            if (pedido.getClienteId().equals(clienteId)) {
                pedidosCliente.add(pedido);
            }
        }
        return pedidosCliente;
    }

    public void listarTodosClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        System.out.println("\n--- Clientes Registrados ---");
        clientes.values().forEach(System.out::println);
        System.out.println("--------------------------");
    }

    public void listarTodosPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        System.out.println("\n--- Pedidos Registrados ---");
        pedidos.values().forEach(System.out::println);
        System.out.println("-------------------------");
    }
}