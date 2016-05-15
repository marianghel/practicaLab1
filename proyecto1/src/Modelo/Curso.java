/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Angelica
 */
public class Curso {
    
    private String siglas;
    private String nombre;
    private int creditos;
    private String horario;
    public Curso() {
    }

    
    
    public Curso(String siglas, String nombre, int creditos, String horario) {
        this.siglas = siglas;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horario= horario;
    }

    public String getSiglas() {
        return siglas;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    
    public String getInformacion(){
        return "Siglas: "+siglas+" Nombre: "+nombre+" Créditos:"+creditos+" Horario: "+horario;
    }
    
}
