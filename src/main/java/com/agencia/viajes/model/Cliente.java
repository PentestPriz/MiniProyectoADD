package com.agencia.viajes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "El DNI es obligatorio")
    private String dni;
    
    @Column(nullable = false, length = 200)
    @NotBlank(message = "El nombre es obligatorio")
    private String cliente;
    
    @Column(nullable = false, length = 300)
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
    
    @Column(name = "caducidad_dni", nullable = false)
    @NotNull(message = "La caducidad del DNI es obligatoria")
    private LocalDate caducidadDni;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;
    
    @Column(nullable = false, length = 20)
    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;
    
    @Column(nullable = false, length = 50)
    @NotBlank(message = "La nacionalidad es obligatoria")
    private String nacionalidad;
    
    @Column(name = "seguro_viaje", nullable = false, length = 2)
    @Pattern(regexp = "SI|NO", message = "El seguro debe ser SI o NO")
    private String seguroViaje;
    
    @Column(name = "correo_electronico", nullable = false, length = 100)
    @Email(message = "Email inválido")
    @NotBlank(message = "El correo es obligatorio")
    private String correoElectronico;

    // Constructores
    public Cliente() {}

    public Cliente(String dni, String cliente, String direccion, LocalDate caducidadDni,
                   LocalDate fechaNacimiento, String telefono, String nacionalidad,
                   String seguroViaje, String correoElectronico) {
        this.dni = dni;
        this.cliente = cliente;
        this.direccion = direccion;
        this.caducidadDni = caducidadDni;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.nacionalidad = nacionalidad;
        this.seguroViaje = seguroViaje;
        this.correoElectronico = correoElectronico;
    }

    // Getters y Setters
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public LocalDate getCaducidadDni() { return caducidadDni; }
    public void setCaducidadDni(LocalDate caducidadDni) { this.caducidadDni = caducidadDni; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public String getSeguroViaje() { return seguroViaje; }
    public void setSeguroViaje(String seguroViaje) { this.seguroViaje = seguroViaje; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
}