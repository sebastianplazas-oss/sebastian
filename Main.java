package com.miempresa;

import com.miempresa.ClienteNoEncontradoException;
import com.miempresa.DatosInvalidosException;
import com.miempresa.IdDuplicadoException;
import com.miempresa.PedidoNoEncontradoException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {
        SistemaPedidos sistema = new SistemaPedidos();

        // --- Escenarios Exitosos ---
        System.out.println("--- ESCENARIOS EXITOSOS ---");
        try {
            // 1. Registrar Clientes
            Cliente cliente1 = new Cliente("C001", "Ana Garcia", "ana.g@example.com");
            sistema.registrarCliente(cliente1);

            Cliente cliente2 = new Cliente("C002", "Luis Perez", "luis.p@example.com");
            sistema.registrarCliente(cliente2);

            sistema.listarTodosClientes();

            // 2. Crear Pedidos
            List<String> detallesPedido1 = Arrays.asList("Laptop", "Mouse");
            Pedido pedido1 = sistema.crearPedido("C001", 1200.50, detallesPedido1);

            List<String> detallesPedido2 = Arrays.asList("Teclado Mecanico", "Monitor");
            Pedido pedido2 = sistema.crearPedido("C002", 750.00, detallesPedido2);

            List<String> detallesPedido3 = Arrays.asList("Webcam", "Microfono");
            Pedido pedido3 = sistema.crearPedido("C001", 150.75, detallesPedido3);

            sistema.listarTodosPedidos();

            // 3. Buscar Cliente y Pedido
            Cliente clienteEncontrado = sistema.buscarCliente("C001");
            System.out.println("\nCliente encontrado por ID: " + clienteEncontrado);

            Pedido pedidoEncontrado = sistema.buscarPedido(pedido1.getId());
            System.out.println("Pedido encontrado por ID: " + pedidoEncontrado);

            List<Pedido> pedidosDeAna = sistema.buscarPedidosPorCliente("C001");
            System.out.println("\nPedidos de Ana (C001):");
            pedidosDeAna.forEach(System.out::println);

        } catch (DatosInvalidosException | IdDuplicadoException e) {
            System.err.println("Error (Unchecked): " + e.getMessage());
            // e.printStackTrace(); // Solo para depuración
        } catch (ClienteNoEncontradoException | PedidoNoEncontradoException e) {
            System.err.println("Error (Checked): " + e.getMessage());
            // e.printStackTrace(); // Solo para depuración
        } catch (Exception e) {
            System.err.println("Error Inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        // --- Escenarios de Error (Manejo de Excepciones) ---
        System.out.println("\n--- ESCENARIOS DE ERROR ---");

        // Caso 1: Registrar cliente con ID duplicado (Unchecked)
        try {
            System.out.println("\nIntentando registrar cliente C001 de nuevo...");
            Cliente clienteDuplicado = new Cliente("C001", "Pedro Gómez", "pedro.g@example.com");
            sistema.registrarCliente(clienteDuplicado);
        } catch (IdDuplicadoException e) {
            System.err.println("CAPTURA DE ERROR: " + e.getMessage());
        }

        // Caso 2: Registrar cliente con datos inválidos (Unchecked)
        try {
            System.out.println("\nIntentando registrar cliente con nombre vacío...");
            Cliente clienteInvalido = new Cliente("C003", "", "c3@example.com");
            sistema.registrarCliente(clienteInvalido);
        } catch (DatosInvalidosException e) {
            System.err.println("CAPTURA DE ERROR: " + e.getMessage());
        }

        // Caso 3: Crear pedido para cliente no existente (Checked)
        try {
            System.out.println("\nIntentando crear pedido para cliente C999 (no existe)...");
            sistema.crearPedido("C999", 50.00, Arrays.asList("Artículo no existente"));
        } catch (ClienteNoEncontradoException e) {
            System.err.println("CAPTURA DE ERROR: " + e.getMessage());
        }

        // Caso 4: Crear pedido con total negativo (Unchecked)
        try {
            System.out.println("\nIntentando crear pedido con total negativo...");
            sistema.crearPedido("C001", -10.00, Arrays.asList("Regalo"));
        } catch (DatosInvalidosException e) {
            System.err.println("CAPTURA DE ERROR: " + e.getMessage());
        }

        // Caso 5: Buscar pedido no existente (Checked)
        try {
            System.out.println("\nIntentando buscar pedido P999 (no existe)...");
            sistema.buscarPedido("P999");
        } catch (PedidoNoEncontradoException e) {
            System.err.println("CAPTURA DE ERROR: " + e.getMessage());
        }

        // Caso 6: Buscar cliente con ID nulo (Unchecked)
        try {
            System.out.println("\nIntentando buscar cliente con ID nulo...");
            sistema.buscarCliente(null);
        } catch (DatosInvalidosException e) {
            System.err.println("CAPTURA DE ERROR: " + e.getMessage());
        }

        // Caso 7: Buscar pedidos de cliente no existente (Checked)
        try {
            System.out.println("\nIntentando buscar pedidos de cliente C888 (no existe)...");
            sistema.buscarPedidosPorCliente("C888");
        } catch (ClienteNoEncontradoException e) {
            System.err.println("CAPTURA DE ERROR: " + e.getMessage());
        }

        System.out.println("\n--- Fin de las pruebas ---");
        sistema.listarTodosClientes();
        sistema.listarTodosPedidos();
    }
}