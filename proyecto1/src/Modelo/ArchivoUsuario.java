/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Marianghel
 */
public class ArchivoUsuario {
    ObjectInputStream archivoEntrada;//lee y carga
    ObjectOutputStream archivoSalida;//crea y escribe
    
    public ArchivoUsuario(){
        if(verificarDatosArchivo()){
            System.out.println("carga archivo correcta");
        }else{
            System.out.println("carga archivo incorrecta");
        }
    }
 public boolean confirmarUsuario(String arreglo[])
    {
        boolean login=false;
        ArrayList<Usuario> array=devolverArchivo();
        for(int contador=0;contador<array.size();contador++)
        {
            if(array.get(contador).getNombreUsuario().equals(arreglo[0]) &&  array.get(contador).getContrasena().equals(arreglo[1]))
            {
                login=true;
            }    
        }
        return login;
    }
public boolean verificarDatosArchivo(){
        boolean carga=false;
        try{
          archivoEntrada=new  ObjectInputStream(new FileInputStream("archivoUsuario.dat"));
          carga=true;
         
        }catch(Exception e){
         System.out.println("Error cargar archivoUsuario: "+e);
        }
        return carga;
    }
    public void creaArchivo(){//primero llama este metodos en agregarInformacion en contador
        try{
            archivoSalida=new ObjectOutputStream(new FileOutputStream("archivoUsuario.dat"));
        }catch(Exception e){
           System.out.println("No se creo archivoUsuario");
        }
    }
    public void escribeArchivo(Usuario usuario){//para agregarInformacion en controlador
        try{
            archivoSalida.writeObject(usuario);
        }catch(Exception e){
            System.out.println("No se escribió correctamente en el archivoUsuario");
        }
    }
    public ArrayList<Usuario> devolverArchivo(){// para el cargarArchivo en controlador
    ArrayList <Usuario> array=new ArrayList<Usuario>();
        try{
        while(true){
            array.add((Usuario)archivoEntrada.readObject());
        }
    }catch(Exception e){
       System.out.println("No se lleno el array con el objeto Usuario"+e);
    }
        return array;
}
}
