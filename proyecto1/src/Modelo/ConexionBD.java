/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Angelica
 */
public class ConexionBD {
    Connection con = null;
    public ConexionBD()
    {
     this.realizarConexion();
     //registrarEstudiante("B39208","Melissa");
     //consultarEstudiante("B40188");
     //actualizarEstudiante("B39208", "Melissa Barquero");
     this.eliminarEstudiante("B40188");
    }
    public void realizarConexion()
    {
        try {
            String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/matricula";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("Conexión Realizada");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
            e.printStackTrace();
        } 
    }
    
    //Estudiante---------------------------------------------------------------------------------------------------
    public boolean registrarEstudiante(String cedula, String nombre, String direccion)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
                cmd = con.createStatement();
                ejecuto = cmd.execute("INSERT INTO Estudiantes(Carnet, Nombre) VALUES ('"+cedula+"','"+nombre+"','"+direccion+"')");
                
               return true;
               // rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
        
    }
    public void consultarEstudiante(String cedula)
    {
        ResultSet rs = null;
        Statement cmd = null;

        try {
                cmd = con.createStatement();
                rs = cmd.executeQuery("SELECT * FROM Estudiantes where cedula='"+cedula+"'");
                
                while (rs.next()) 
                {
                    String nombre = rs.getString("Nombre");
                    String direccion = rs.getString("Direccion");
                    //int edad = rs.getInt(2);
                    System.out.println("Información de la BD: Nombre: "+nombre+" Cedula: "+cedula+" Direccion: "+direccion); 
                }
                rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        
    }
    
    public boolean actualizarEstudiante(String cedula, String nombre, String direccion)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
                cmd = con.createStatement();
                ejecuto = cmd.execute("UPDATE estudiantes set cedula= '"+cedula+"',nombre='"+nombre+"',direccion='"+direccion+"'WHERE cedula='"+cedula+"'");
                
               return true;
               // rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
        
    }
    
    
    public boolean eliminarEstudiante(String cedula)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
                cmd = con.createStatement();
                ejecuto = cmd.execute("DELETE FROM estudiantes WHERE cedula= '"+cedula+"'");
                
               return true;
               // rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
        
    }
    
    //Cursos--------------------------------------------------------------------------------------
    
    public boolean registrarCurso(String siglas, String nombre, int creditos, String horario)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
                cmd = con.createStatement();
                ejecuto = cmd.execute("INSERT INTO Estudiantes(Carnet, Nombre) VALUES ('"+siglas+"','"+nombre+"','"+creditos+"','"+horario+"')");
                
               return true;
               // rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
        
    }
    public void consultarCurso(String siglas)
    {
        ResultSet rs = null;
        Statement cmd = null;

        try {
                cmd = con.createStatement();
                rs = cmd.executeQuery("SELECT * FROM Estudiantes where siglas='"+siglas+"'");
                
                while (rs.next()) 
                {
                    String nombre = rs.getString("Nombre");
                    int creditos = rs.getInt(2);
                    String horario = rs.getString("Horario");
                    
                    System.out.println("Información de la BD: Nombre: "+nombre+" Siglas: "+siglas+" Creditos: "+creditos+" Horario: "+horario); 
                }
                rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        
    }
    
    public boolean actualizarCurso(String siglas, String nombre, int creditos, String horario)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
                cmd = con.createStatement();
                ejecuto = cmd.execute("UPDATE estudiantes set siglas= '"+siglas+"',nombre='"+nombre+"',creditos='"+creditos+"',horario='"+horario+"'WHERE siglas='"+siglas+"'");
                
               return true;
               // rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
        
    }
    
    
    public boolean eliminarCurso(String siglas)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
                cmd = con.createStatement();
                ejecuto = cmd.execute("DELETE FROM estudiantes WHERE siglas= '"+siglas+"'");
                
               return true;
               // rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
        
    }
    
    //Matricula----------------------------------------------------------------------------------------------------------
    
     public boolean registrarMatricula(String codigo, String cedula, String siglas)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
                cmd = con.createStatement();
                ejecuto = cmd.execute("INSERT INTO Estudiantes(Carnet, Nombre) VALUES ('"+codigo+"','"+cedula+"','"+siglas+"')");
                
               return true;
               // rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
        
    }
    public void consultarMatricula(String codigo)
    {
        ResultSet rs = null;
        Statement cmd = null;

        try {
                cmd = con.createStatement();
                rs = cmd.executeQuery("SELECT * FROM Estudiantes where codigo='"+codigo+"'");
                
                while (rs.next()) 
                {
                    String cedula = rs.getString("Cedula");
                    String siglas = rs.getString("Siglas");
                    
                    System.out.println("Información de la BD: Codigo: "+codigo+" Cedula: "+cedula+" Siglas: "+siglas); 
                }
                rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        
    }
    
    public boolean actualizarMatricula(String codigo, String cedula, String siglas)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
                cmd = con.createStatement();
                ejecuto = cmd.execute("UPDATE estudiantes set codigo= '"+codigo+"',cedula='"+cedula+"',siglas='"+siglas+"'WHERE codigo='"+codigo+"'");
                
               return true;
               // rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
        
    }
    
    
    public boolean eliminarMatricula(String codigo)
    {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
                cmd = con.createStatement();
                ejecuto = cmd.execute("DELETE FROM estudiantes WHERE codigo= '"+codigo+"'");
                
               return true;
               // rs.close();
        }
        catch(Exception e)
        {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
        
    }
}
