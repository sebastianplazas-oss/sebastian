package com.miempresa;

import java.util.Objects; // Necesario para Objects.hash y Objects.equals

import java.time.LocalDate; // Para manejar fechas
import java.util.List;
import java.util.Objects;

public class Cliente {
   public class Cliente {
    
    private String id;
    private String nombre;
    private String email;

    public Cliente(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    // Métodos equals y hashCode basados en el ID para asegurar unicidad
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cliente{" +
              "id='" + id + '\'' +
              ", nombre='" + nombre + '\'' +
              ", email='" + email + '\'' +
              '}';
    }
}

}