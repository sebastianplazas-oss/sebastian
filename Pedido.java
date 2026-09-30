package com.miempresa;

import java.time.LocalDate; // Para manejar fechas
import java.util.List;
import java.util.Objects;
 
   public class Pedido {
    
    private String id;
    private String clienteId;
    private LocalDate fechaPedido;
    private double total;
    private List<String> detalles;

    public Pedido(String id, String clienteId, LocalDate fechaPedido, double total, List<String> detalles) {
        this.id = id;
        this.clienteId = clienteId;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.detalles = detalles;
    }

    public String getId() {
        return id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public List<String> getDetalles() {
        return detalles;
    }

    // Metodos equals y hashCode basados en el ID para asegurar unicidad
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pedido{" +
              "id='" + id + '\'' +
              ", clienteId='" + clienteId + '\'' +
              ", fechaPedido=" + fechaPedido +
              ", total=" + total +
              ", detalles=" + detalles +
              '}';
    }
}


   