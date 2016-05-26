
package Modelo;

import java.io.Serializable;

public class Usuario implements Serializable{
     
    private String nombreCompleto;
    private String nombreUsuario;
    private String contrasena;
    private String tipo;

    public Usuario( String nombreUsuario,String nombreCompleto, String contrasena, String tipo) {
        
        this.nombreUsuario = nombreUsuario;
        this.nombreCompleto = nombreCompleto;
        this.contrasena = contrasena;
        this.tipo = tipo;
    } 
    
    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getInformacion(){
        return "nombreC: "+getNombreCompleto()+"nombreU: "+getNombreUsuario()+"contraseña: "+getContrasena()+"tipo: "+getTipo();
    }
 
}
