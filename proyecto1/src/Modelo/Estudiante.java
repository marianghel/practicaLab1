/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Angelica
 */
public class Estudiante implements Serializable{
    
    private String cedula;
    private String nombre;
    private String direccion;


    public Estudiante() {
    }

    
    public Estudiante(String cedula, String nombre, String horario) {
        this.cedula = cedula;
        this.nombre = nombre;

    }

    /**
     * @return the cedula
    /**
     * @return the cedula
     */
    
    
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the direccion
     */
    
    public String getInformacion(){
        return "Cedula: "+cedula+" Nombre: "+nombre+" Direccion= "+direccion;
    }
    
    
}
