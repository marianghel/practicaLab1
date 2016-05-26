
package Controlador;

import Modelo.ArchivoUsuario;
import Modelo.ConexionBD;
import Modelo.MetodosUsuario_XML;
import Vista.FRM_Cursos;
import Vista.FRM_Estudiantes;
import Vista.FRM_FuenteInformacion;
import Vista.FRM_Login;
import Vista.FRM_Matricula;
import Vista.FRM_MenuPrincipal;
import Vista.FRM_Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controlador_FRM_MenuPrincipal implements ActionListener{
    
    FRM_Estudiantes mantenimientoEstudiantes;
    FRM_Cursos mantenimientoCursos;
    FRM_Matricula matricula;
    ArchivoUsuario archivoUsuario;
    FRM_Login login;
    FRM_MenuPrincipal ventana;
    FRM_Usuario ventanaUsuario;
    FRM_FuenteInformacion mantenimientoFuente;
    ConexionBD conexion;
   
    
    public Controlador_FRM_MenuPrincipal(FRM_MenuPrincipal ventana)
    { 
        this.ventana=ventana;
        mantenimientoEstudiantes=new FRM_Estudiantes(this);
        mantenimientoCursos=new FRM_Cursos(this);
        mantenimientoFuente= new FRM_FuenteInformacion(this);
        matricula= new FRM_Matricula(mantenimientoEstudiantes,mantenimientoCursos,this);
        archivoUsuario=new ArchivoUsuario();
        login=new FRM_Login(this);
        ventanaUsuario=new FRM_Usuario(this);
        conexion=new ConexionBD();
        
       mantenimientoFuente.setVisible(true);
    }
    public void mostrarLogin(){
        
        login.setVisible(true);
    }
    
       public void opcionConfirmar(){
           //archivo
        if(this.mantenimientoFuente.devolverOpcionFuente()==1){
            confirmandoUsuarioArchivo(login.devolverInformacion());
        }
        //base datos
        if(this.mantenimientoFuente.devolverOpcionFuente()==2){
             confirmarBaseDatos();
        }
        //XML
         if(this.mantenimientoFuente.devolverOpcionFuente()==3){
            confirmarXML();
       
            }
        }
     
      
    //confirma login para entrar a menu principal
      public void confirmandoUsuarioArchivo(String arreglo[])
    {
        if(archivoUsuario.confirmarUsuario(arreglo))
        {
            accesoCorrectoConfimar();
        }
        else
        {
            login.setVisible(false);
            if(accesoIncorrectoConfirmar()){
             ventanaUsuario.setVisible(true); 
             System.out.println("abriendo modulo usuario");
            }else{
               login.setVisible(true);
              
               JOptionPane.showMessageDialog(null,"Intente nuevamente entrar en login");
            }
        }
    }
      public void confirmarBaseDatos(){
          if(conexion.nombreUsuario(this.login.devolverUsuario())&&conexion.contrasena(login.devolverContrasena()))
          {
             accesoCorrectoConfimar();
             System.out.println("paso por aqui");
          }else{
                login.setVisible(false);
            if(accesoIncorrectoConfirmar()){ 
             ventanaUsuario.setVisible(true);  
            System.out.println("abriendo modulo usuario");
            
            //accesoMenu();
            }else{
               login.setVisible(true);
              
               JOptionPane.showMessageDialog(null,"Intente nuevamente entrar en login");
            }
          }
      }
      public void confirmarXML(){
            if(this.ventanaUsuario.controlador.metodosXML.confirmarInformacionDelXml(login.devolverUsuario(),login.devolverContrasena())){
             accesoCorrectoConfimar();
             System.out.println("paso por aqui");
          }else{
                login.setVisible(false);
            if(accesoIncorrectoConfirmar()){ 
             ventanaUsuario.setVisible(true);  
            System.out.println("abriendo modulo usuario");
            
            //accesoMenu();
            }else{
               login.setVisible(true);
              
               JOptionPane.showMessageDialog(null,"Intente nuevamente entrar en login");
            }
          }  
      }
      // entra al menu principal
    public void accesoCorrectoConfimar()
    {
        login.setVisible(false);
        ventana.setVisible(true);
    }
    public boolean accesoIncorrectoConfirmar(){// Pregunta si desea hacer un nuevo usuario
       String respuesta;
       boolean acceso=false;
       respuesta= JOptionPane.showInputDialog("¿Desea hacer un nuevo usuario? Si/No");
       if(respuesta.equalsIgnoreCase("Si")){
           acceso=true;
       }else{
           acceso=false;
       }
        return acceso;
    }
     //abre login 
    public void accesoMenu(){
        //archivo
        if(this.mantenimientoFuente.devolverOpcionFuente()==1){
        if(archivoUsuario.verificarDatosArchivo()){
            mantenimientoFuente.setVisible(false);
            login.setVisible(true);
            System.out.println("login archivo"); 
        }else{//sino hay nada, abre usuario 
            ventanaUsuario.setVisible(true);
             System.out.println("usuario");
             this.mantenimientoFuente.setVisible(false);
        }
        }
        //base datos
        if(this.mantenimientoFuente.devolverOpcionFuente()==2){
            if(conexion.verificarDatosBases()!=true){
            mantenimientoFuente.setVisible(false);
           login.setVisible(true);
          System.out.println("login bases"); 
            }else{
            ventanaUsuario.setVisible(true);
             System.out.println("usuario bases");
             this.mantenimientoFuente.setVisible(false);
        }
        }
        //xml
        if(this.mantenimientoFuente.devolverOpcionFuente()==3)
        {
           if(this.ventanaUsuario.controlador.metodosXML.cargarXML())
           {
               mantenimientoFuente.setVisible(false);
               login.setVisible(true);
           }
           else
           {
               ventanaUsuario.setVisible(true);
               this.mantenimientoFuente.setVisible(false);
               ventanaUsuario.controlador.metodosXML.crearXML();
           }
        }
    }
        
   public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Salir"))
        {
            System.exit(0);        
        }
        if(e.getActionCommand().equals("Estudiantes"))
        {
            this.mantenimientoEstudiantes.setVisible(true);
        }
        if(e.getActionCommand().equals("Cursos"))
        {
            this.mantenimientoCursos.setVisible(true);;
        }
        if(e.getActionCommand().equals("Matricula"))
        {
           this.matricula.setVisible(true);
            this.matricula.colocarCodigo();
        }
          if(e.getActionCommand().equals("Aceptar")){
              System.out.println("aceptar");
            this.mantenimientoFuente.mensajeOpcion();
            accesoMenu();
        }

    }

    }
    

