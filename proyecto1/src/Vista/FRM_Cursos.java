package Vista;

import Controlador.Controlador_FRM_Cursos;
import Controlador.Controlador_FRM_MenuPrincipal;
import javax.swing.JOptionPane;

public class FRM_Cursos extends javax.swing.JFrame {

    Controlador_FRM_MenuPrincipal controladorP;
    public Controlador_FRM_Cursos controlador_FRM_Cursos;

    public FRM_Cursos(Controlador_FRM_MenuPrincipal controladorP) {
        initComponents();
        setLocationRelativeTo(null);
        controlador_FRM_Cursos = new Controlador_FRM_Cursos(this, controladorP);
        gUI_Botones2.agregarEventos(controlador_FRM_Cursos);
        this.gUI_InformacionCursos2.agregarControlador(controlador_FRM_Cursos);
        estadoInicial();
    }

    public String[] devolverInformacion() {
        return this.gUI_InformacionCursos2.devolverInformacion();
    }

    public String devolverSigla() {
        return this.gUI_InformacionCursos2.devolverSigla();
    }

    public String devolverNombre() {
        return this.gUI_InformacionCursos2.devolverNombre();
    }

    public int devolverCreditos() {
        return this.gUI_InformacionCursos2.devolverCreditos();
    }

    public String devolverHorario() {
        return this.gUI_InformacionCursos2.devolverHorario();
    }

    public void mostrarInformacion(String arreglo[]) {
        this.gUI_InformacionCursos2.mostrarInformacion(arreglo);
    }

    public void estadoInicial() {
        this.gUI_InformacionCursos2.estadoInicial();
        this.gUI_Botones2.estadoInicial();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void limpiarInterfaz() {
        this.gUI_InformacionCursos2.limpiarInterfaz();
    }

    public void agregar() {
        this.gUI_InformacionCursos2.habilitarNoExiste();
        this.gUI_Botones2.habilitarAgregar();
    }

    public void modificaElimina() {
        this.gUI_Botones2.habilitarExistencia();
        this.gUI_InformacionCursos2.hablilitarSiExiste();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gUI_InformacionCursos2 = new Vista.GUI_InformacionCursos();
        gUI_Botones2 = new Vista.GUI_Botones();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(gUI_InformacionCursos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        getContentPane().add(gUI_Botones2, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 317, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        controlador_FRM_Cursos.agregarInformacionAlArchivo();
    }//GEN-LAST:event_formComponentHidden


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Vista.GUI_Botones gUI_Botones2;
    private Vista.GUI_InformacionCursos gUI_InformacionCursos2;
    // End of variables declaration//GEN-END:variables
}
