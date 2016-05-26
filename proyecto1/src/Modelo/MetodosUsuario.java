/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Marianghel
 */
public class MetodosUsuario {
     public  ArrayList <Usuario> arrayUsuario;
   String arreglo[];
     ArrayList <Login> arrayLogin;
String arregloLogin[];  
MetodosUsuario metodosUsuario;
   
 
  public MetodosUsuario(){
      arrayUsuario=new ArrayList <Usuario>();
      arreglo=new String [4];
       arrayLogin=new ArrayList <Login>();
    arregloLogin=new String [2]; 
   
  }
  public ArrayList <Usuario> getArray(){
      return arrayUsuario;
  } 
  public void setArray(ArrayList <Usuario> array){
      arrayUsuario=array;
  }
  
  
  
  public boolean confirmarUsuario(String usuario, String contrasena){
    boolean existe=false;
    for(int contador=0; contador<arrayLogin.size();contador++){
        if((arrayUsuario.get(contador).getNombreCompleto().equals(usuario)||arrayUsuario.get(contador).getContrasena().equals(contrasena))){
            arregloLogin[0]=arrayLogin.get(contador).getNombre();
            arregloLogin[1]=arrayLogin.get(contador).getContrasena();
            existe=true;
        }
    }
    return existe;
}
  
public boolean buscarUsuario(String nombreUsuario){
    boolean usuarioExiste=false;
    for(int contador=0;contador<arrayUsuario.size();contador++){
        if(arrayUsuario.get(contador).getNombreUsuario().equals(nombreUsuario)){
            arreglo[0]=arrayUsuario.get(contador).getNombreUsuario();
            arreglo[1]=arrayUsuario.get(contador).getNombreCompleto();
            arreglo[2]=arrayUsuario.get(contador).getContrasena();
            arreglo[3]=arrayUsuario.get(contador).getTipo();
            usuarioExiste=true;
   }
    }
        return usuarioExiste;
}  
public String[] getArreglo(){
    return this.arreglo;
}
public void agregarUsuario(String informacion[]){
    Usuario temporal= new Usuario(informacion[0],informacion[1],informacion[2],informacion[3]);
    arrayUsuario.add(temporal);
    System.out.println(temporal.getInformacion());
}
public void modificarUsuario(String arreglo[]){
    for(int contador=0;contador<arrayUsuario.size();contador++){
        arrayUsuario.get(contador).setNombreUsuario(arreglo[0]);
        arrayUsuario.get(contador).setNombreCompleto(arreglo[1]);
        arrayUsuario.get(contador).setContrasena(arreglo[2]);
        arrayUsuario.get(contador).setTipo(arreglo[3]);
    }
}
public void eliminarUsuario(String arreglo[]){
    for(int contador=0;contador<arrayUsuario.size();contador++){
      arrayUsuario.remove(contador);
    }
}
}
