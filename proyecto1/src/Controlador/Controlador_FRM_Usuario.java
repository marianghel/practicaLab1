package Controlador;

import Modelo.ArchivoUsuario;
import Modelo.MetodosEstudiante_XML;
import Modelo.MetodosUsuario;
import Modelo.MetodosUsuario_XML;
import Modelo.Usuario;
import Vista.FRM_Usuario;
import Vista.GUI_InformacionUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador_FRM_Usuario implements ActionListener {

    FRM_Usuario usuario;
    MetodosUsuario metodos;
    ArchivoUsuario archivo;
    Controlador_FRM_MenuPrincipal controladorP;
    MetodosUsuario_XML metodosXML;

    public Controlador_FRM_Usuario(FRM_Usuario usuario, Controlador_FRM_MenuPrincipal controladorP) {
        this.usuario = usuario;
        this.controladorP = controladorP;
        metodos = new MetodosUsuario();
        archivo = new ArchivoUsuario();
        metodosXML = new MetodosUsuario_XML(usuario);
        cargarArchivoCuandoAbreVentana();
    }

    public void cargarArchivoCuandoAbreVentana() {
        ArrayList<Usuario> arrayTemporal = archivo.devolverArchivo();
        metodos.setArray(arrayTemporal);
    }

    public void agregarInformacionCuandoCierraVentana() {
        this.archivo.creaArchivo();
        ArrayList<Usuario> arrayGuarda;
        arrayGuarda = metodos.getArray();//array lleno de la ventana
        for (int contador = 0; contador < arrayGuarda.size(); contador++) {
            this.archivo.escribeArchivo(arrayGuarda.get(contador));
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Buscar")) {

            buscar();
        }
        if (e.getActionCommand().equals("Agregar")) {
            //agregar archivo
            if (controladorP.mantenimientoFuente.devolverOpcionFuente()==1) {
                metodos.agregarUsuario(usuario.informacion());
                usuario.limpiarVentana();
                System.out.println("agrego al archivo");
                usuario.estadoInicio();
            }
            //agregar bases 
            if (controladorP.mantenimientoFuente.devolverOpcionFuente()==2) {
                controladorP.conexion.registrarUsuario(usuario.devolverInformacion());
                usuario.limpiarVentana();
                System.out.println("agrego a base de datos");
                usuario.estadoInicio();
            }
            //agregar XML
            if (controladorP.mantenimientoFuente.devolverOpcionFuente()==3) {
                System.out.println("agrego a xml");
                metodosXML.guardarEnXML(usuario.devolverInformacion());
                usuario.limpiarVentana();
                System.out.println("agrego a xml");
                usuario.estadoInicio();
            }
        }
        if (e.getActionCommand().equals("Modificar")) {
            //modifica arhivo
            if (controladorP.mantenimientoFuente.devolverOpcionFuente()==1) {
                metodos.modificarUsuario(usuario.informacion());
                usuario.estadoInicio();
                usuario.limpiarVentana();
            }
            //modifica la base de datos
            if (controladorP.mantenimientoFuente.devolverOpcionFuente()==2) {
                controladorP.conexion.actualizarUsuario(usuario.devolverNombreU(), usuario.devolverNombreC(), usuario.devolverContrasena(), usuario.devolverTipo());
                usuario.estadoInicio();
                usuario.limpiarVentana();
            }
            //modifica xml
            if (controladorP.mantenimientoFuente.devolverOpcionFuente()==3) {
                metodosXML.modificarInformacionDelXml(usuario.devolverInformacion());
                usuario.estadoInicio();
                usuario.limpiarVentana();
            }
        }
        if (e.getActionCommand().equals("Eliminar")) {
            
            //elimina archivo
            if (controladorP.mantenimientoFuente.devolverOpcionFuente()==1) {
                metodos.eliminarUsuario(usuario.informacion());
                usuario.estadoInicio();
                usuario.limpiarVentana();
            }
            //elimina la base de datos
            if (controladorP.mantenimientoFuente.devolverOpcionFuente()==2) {
                controladorP.conexion.eliminarUsuario(usuario.devolverNombreU());
                usuario.estadoInicio();
                usuario.limpiarVentana();
            }
            //elimina xml
             if (controladorP.mantenimientoFuente.devolverOpcionFuente()==3) {
                metodosXML.eliminarInformacionDelXml(usuario.devolverNombreU());
                usuario.estadoInicio();
                usuario.limpiarVentana();
            }
        }

    }

    public void buscar() {
        //busca archivo
        if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 1) {
            if (metodos.buscarUsuario(usuario.devolverNombreU())) {
                usuario.mostrarInformacion(metodos.getArreglo());
                System.out.println("buscar si");
                usuario.modificarEliminar();
                usuario.mostrarMensaje("Usuario encontrado en archivo plano.");
            } else {
                usuario.agregar();
                usuario.mostrarMensaje("Usuario no encontrado en archivo plano.");
            }
        }
        //Busca BD
        if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 2) {
            if (controladorP.conexion.consultarUsuario(usuario.devolverNombreU())) {
                this.usuario.mostrarInformacion(controladorP.conexion.getArregloInformacion());
                System.out.println("buscar si");
                usuario.modificarEliminar();
            } else {
                usuario.agregar();
                usuario.mostrarMensaje("Usuario no encontrado.");
            }
        }
        //Busca xml
        if (controladorP.mantenimientoFuente.devolverOpcionFuente() == 3) {
            /* if (metodosXML.consultarInformacionDelXml(usuario.devolverNombreU())) {
            this.usuario.mostrarInformacion(metodosXML.getArregloInformacion());
            System.out.println("buscar si");
            usuario.modificarEliminar();
        } else {
            usuario.agregar();
            usuario.mostrarMensaje("Usuario no encontrado.");
        }
    }*/
        }
    }
}
    

