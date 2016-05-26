package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConexionBD {

    Connection con = null;
    String[] arregloUsuario = new String[4];
    String[] arregloEstudiantes = new String[3];
    String[] arregloCurso = new String[4];
    String[] arregloLogin = new String[2];
    String[] arregloMatricula = new String[2];
    String[] arregloDetalleMatricula = new String[2];

    public ConexionBD() {
        this.realizarConexion();
        //registrarEstudiante("B39208","Melissa");
        //consultarEstudiante("B40188");
        //actualizarEstudiante("B39208", "Melissa Barquero");
        //this.eliminarEstudiante("B40188");
       
    }

    public void realizarConexion() {
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
    //Usuario-----------------------------------------------------------------------------------------------------

    public boolean verificarDatosBases() {
        ResultSet rs = null;
        Statement cmd = null;
        int cantidadUsuarios = 0;
        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                cantidadUsuarios = cantidadUsuarios + 1;
            }
            // cantidadUsuarios = cmd.execute("SELECT  count(*) FROM usuarios"); 
            System.out.println(cantidadUsuarios);
            if (cantidadUsuarios == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }

    public boolean nombreUsuario(String nombreUsuario) {
        boolean existe = false;
        ResultSet rs = null;
        Statement cmd = null;
        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM usuarios where nombreUsuario='" + nombreUsuario + "'");
            while (rs.next()) {
                existe = true;
                System.out.println("Información de la BD: Nombre Usuario: " + nombreUsuario);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        return existe;
    }

    public boolean contrasena(String contrasena) {
        boolean existe = false;
        ResultSet rs = null;
        Statement cmd = null;
        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM usuarios where password='" + contrasena + "'");
            while (rs.next()) {
                //int edad = rs.getInt(2);
                existe = true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        return existe;

    }
// Usuario----------------------------------------------------------------------------------------------------------

    public boolean registrarUsuario(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO usuarios (nombreUsuario, nombreCompleto,password, tipo) VALUES ('" + informacion[0] + "','" + informacion[1] + "','" + informacion[2] + "','" + informacion[3] + "')");
            System.out.println("agregar bases conexion");
            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }

    public boolean consultarUsuario(String nombreU) {
        boolean existe = false;
        ResultSet rs = null;
        Statement cmd = null;
        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM usuarios where nombreUsuario='" + nombreU + "'");
            while (rs.next()) {

                String nombreC = rs.getString("nombreCompleto");
                String contrasena = rs.getString("password");
                String tipo = rs.getString("tipo");
                arregloUsuario[0] = nombreU;
                arregloUsuario[1] = nombreC;
                arregloUsuario[2] = contrasena;
                arregloUsuario[3] = tipo;
                //int edad = rs.getInt(2);
                existe = true;
                System.out.println("Información de la BD: Nombre Usuario: " + nombreU + " Nombre Completo: " + nombreC + " Contraseña: " + contrasena + " Tipo: " + tipo);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        return existe;

    }

    public String[] getArregloInformacion() {
        return this.arregloUsuario;
    }

    public boolean actualizarUsuario(String nombreU, String nombreC, String contrasena, String tipo) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE usuarios SET nombreUsuario='" + nombreU + "',nombreCompleto='" + nombreC + "',password='" + contrasena + "',tipo='" + tipo + "' WHERE nombreUsuario='" + nombreU + "'");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario(String nombreU) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM usuarios WHERE nombreUsuario='" + nombreU + "'");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }

    //Estudiante---------------------------------------------------------------------------------------------------
    public boolean registrarEstudiante(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO estudiantes(cedula, nombre,direccion) VALUES ('" + informacion[0] + "','" + informacion[1] + "','" + informacion[2] + "')");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }

    public boolean consultarEstudiante(String cedula) {
        boolean ejecuto = false;
        ResultSet rs = null;
        Statement cmd = null;

        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM estudiantes where cedula='" + cedula + "'");

            while (rs.next()) {
                String nombreCompleto = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                //int edad = rs.getInt(2);
                arregloEstudiantes[0] = cedula;
                arregloEstudiantes[1] = nombreCompleto;
                arregloEstudiantes[2] = direccion;
                ejecuto = true;
                System.out.println("Información de la BD: Nombre: " + nombreCompleto + " Cedula: " + cedula + " Direccion: " + direccion);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        return ejecuto;
    }

    public String[] getArreglo() {
        return arregloEstudiantes;
    }

    public boolean actualizarEstudiante(String cedula, String nombre, String direccion) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE estudiantes SET cedula= '" + cedula + "',nombre='" + nombre + "',direccion='" + direccion + "'WHERE cedula='" + cedula + "'");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }

    public boolean eliminarEstudiante(String cedula) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM estudiantes WHERE cedula= '" + cedula + "'");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }

    //Cursos--------------------------------------------------------------------------------------
    public boolean registrarCurso(String informacion[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO curso (Sigla, nombreCurso, creditos, horario) VALUES ('" + informacion[0] + "','" + informacion[1] + "','" + informacion[2] + "','" + informacion[3] + "')");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }

    public boolean consultarCurso(String siglas) {
        boolean ejecuto = false;
        ResultSet rs = null;
        Statement cmd = null;

        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM curso where Sigla='" + siglas + "'");

            while (rs.next()) {
                String nombre = rs.getString("nombreCurso");
                int creditos = rs.getInt(3);//NUMERO DE CAMPO DE LA TABLA
                //String creditos= rs.getString("creditos");
                String horario = rs.getString("horario");
                arregloCurso[0] = siglas;
                arregloCurso[1] = nombre;
                arregloCurso[2] = "" + creditos;
                arregloCurso[3] = horario;
                System.out.println("Información de la BD: Nombre: " + nombre + " Sigla: " + siglas + " creditos: " + creditos + " horario: " + horario);
                ejecuto = true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia de curso: " + e.getMessage());
        }
        return ejecuto;
    }

    public String[] getArregloCurso() {
        return arregloCurso;
    }

    public boolean actualizarCurso(String siglas, String nombre, int creditos, String horario) {
        System.out.println("En el metodo de actualizar cutso sigla:" + siglas + "nombre:" + nombre + "creditos: " + creditos + "horario:" + horario);
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE curso SET Sigla= '" + siglas + "',nombreCurso='" + nombre + "',creditos='" + creditos + "',horario='" + horario + "'WHERE Sigla='" + siglas + "'");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }

    public boolean eliminarCurso(String siglas) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM curso WHERE Sigla= '" + siglas + "'");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }

    //Matricula----------------------------------------------------------------------------------------------------------
  
    public boolean registrarMatricula(String codigo, String cedula) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO matricula (codigo, cedula) VALUES ('" + codigo + "','" + cedula + "'");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }


    public boolean consultarMatricula(String codigo) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto = false;

        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM matricula where codigo='" + codigo + "'");

            while (rs.next()) {
                String cedula = rs.getString("cedula");
                arregloMatricula[0] = codigo;
                arregloMatricula[1] = cedula;
                ejecuto = true;

                System.out.println("Información de la BD: Codigo: " + codigo + " Cedula: " + cedula);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        return ejecuto;
    }

   /* public boolean actualizarMatricula(String codigo, String cedula, String siglas) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE estudiantes SET codigo= '" + codigo + "',cedula='" + cedula + "'WHERE codigo='" + codigo + "'");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }

    }*/

    public boolean eliminarMatricula(String codigo) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM estudiantes WHERE codigo= '" + codigo + "'");

            return true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            return false;
        }
    }

    public String[] devolverArregloMatricula() {
        return arregloMatricula;
    }

    //DetalleMatricula----------------------------------------------
    
    public boolean registrarMatriculaDetallada(String codigo, String siglas) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        boolean existe = false;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("INSERT INTO detallematricula(codigo, siglas) VALUES ('" + codigo + "','" + siglas +"'");
            existe = true;
            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
            existe = false;
        }
        return existe;
    }

   /* public boolean consultarMatriculaDetallada(String codigo) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean existe = false;

        try {
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT * FROM detalle_matricula where codigo='" + codigo + "'");

            while (rs.next()) {
                String siglas = rs.getString("cedula");
                arregloDetalleMatricula[0] = codigo;
                arregloDetalleMatricula[1] = siglas;
                existe = true;
            }
            existe = true;
            rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());
        }
        return existe;
    }

    public void eliminarMatriculaDetallada(String codigo) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("DELETE FROM detalleMatricula WHERE codigo ='" + codigo + "'");
            System.out.println("eliminado con exito");

            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
    }

    public void modificarMatriculaDetallada(String arreglo[]) {
        ResultSet rs = null;
        Statement cmd = null;
        boolean ejecuto;
        try {
            cmd = con.createStatement();
            ejecuto = cmd.execute("UPDATE detalleMatricula SET sigla='" + arreglo[1] + "'WHERE codigo='" + arreglo[0] + "'");

            // rs.close();
        } catch (Exception e) {
            System.out.println("SQLException ejecutando sentencia: " + e.getMessage());

        }
    }
    
    
    public String [] devolverArregloDetalleMatricula(){
        return arregloDetalleMatricula;
    }
        
*/
}

