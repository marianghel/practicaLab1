/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Angelica
 */
public class ArchivoMatricula {
    
    ObjectOutputStream archivoSalida;//lee el archivo
    ObjectInputStream archivoEntrada;//escribe en el archivo

    public ArchivoMatricula() {
         if (cargarArchivo()) {
            System.out.println("Se cargo de forma correcta la informacion archivo");

        } else {
            System.out.println("Error al cargar informacion al archivo" );
        }
    }
   
    
    
    public void crearArchivoMatricula() {//crea el archivo
        try {
            archivoSalida = new ObjectOutputStream(new FileOutputStream("matricula.dat"));
        } catch (IOException ex) {
            System.out.println("Error al crear archivo de matricula");
        }
    }
    
    public void ingresarInfoAlArchivo(Matricula matricula){//escribe en el archivo
        try{
            archivoSalida.writeObject(matricula);
            System.out.println("Se escribio de forma correcta la informacion de matricula en el archivo");
        }
        catch(Exception e){
            System.out.println("Error al ingresar informacion de matricula en el archivo" + e);
        }
    }
    
    public boolean cargarArchivo(){//lee el archivo
        boolean existeMatricula=false;
        try{
            archivoEntrada= new ObjectInputStream(new FileInputStream("matricula.dat"));
            existeMatricula = true;
        }
        catch(Exception e){
            System.out.println("" + e);
        }
        return existeMatricula;
    }
    
    
    public ArrayList<Estudiante> devolverInformacionDelArchivo(){//devuelve el array del archivo
        ArrayList <Estudiante> array= new ArrayList<Estudiante>();
        try{
            while(true){
                array.add((Estudiante)archivoEntrada.readObject());
            }
        }
        catch(Exception e){
             System.out.println("Se llego al final del archivo");//lee el archivo y lo ingresa en el array, hasta que no hay mas objetos y retorna el array
        }
        
        return array;
    }
}
