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
public class ArchivoCurso {
    
    ObjectOutputStream archivoSalida;
    ObjectInputStream archivoEntrada;

    public ArchivoCurso() {
        if(cargarArchivo()){
            System.out.println("Se cargo la informacion del archivo curso");
        }else{
            System.out.println("Error al cargar la informacion del archivo curso");
        }
    }
    
    public void crearArchivoCurso(){
        try{
            archivoSalida= new ObjectOutputStream(new FileOutputStream("curso.dat"));
        }catch(IOException ex){
            System.out.println("Error al crear archivo de curso");
        }
    }
    
    public void ingresarInfoAlArchivo(Curso curso){
        try{
            archivoSalida.writeObject(curso);
            System.out.println("Se escribio correctamente la informacion en el archivo curso");
        }catch(Exception e){
            System.out.println("Error al ingresar la informacion de curso en el archivo");
        }
    }
    
    public boolean cargarArchivo(){
        boolean existeCurso=false;
        try{
            archivoEntrada=new ObjectInputStream(new FileInputStream("curso.dat"));
            existeCurso=true;
        }catch(Exception e){
            System.out.println(""+e);
        }
        return existeCurso;
    }
    
    public ArrayList<Curso> devolverInfoDelArchivo(){
        ArrayList<Curso> array=new ArrayList<Curso>();
        try{
            while(true){
                array.add((Curso)archivoEntrada.readObject());
            }
        }catch(Exception e){
            System.out.println("Se llego al final del archivo");
        }
        return array;
    }
    
}
