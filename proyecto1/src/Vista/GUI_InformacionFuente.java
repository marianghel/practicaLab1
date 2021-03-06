/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Controlador_FRM_Estudiantes;
import Controlador.Controlador_FRM_MenuPrincipal;

/**
 *
 * @author Marianghel
 */
public class GUI_InformacionFuente extends javax.swing.JPanel {

    /**
     * Creates new form GUI_InformacionFuente
     */
    Controlador_FRM_MenuPrincipal controladorP;

    public GUI_InformacionFuente() {
        initComponents();
        this.rbtn_Archivo.setSelected(true);
    }

    public void agregarControlador(Controlador_FRM_MenuPrincipal controladorP) {
        this.controladorP = controladorP;
    }

    public void agregarEvento(Controlador_FRM_MenuPrincipal controladorP) {
        this.controladorP = controladorP;
        this.btn_Aceptar.addActionListener(controladorP);
    }

    public void mensaje() {
        String mensaje = "Opcion elegida:";
        if (rbtn_Archivo.isSelected()) {
            mensaje = mensaje + " Abrir sistema con archivo plano";
        }
        if (rbtn_Base.isSelected()) {
            mensaje = mensaje + " Abrir sistema con Base de datos";
        }
        if (rbtn_xml.isSelected()) {
            mensaje = mensaje + " Abrir sistema con XML";
        }
        jl_Resultado.setText(mensaje);
    }

    public int opcionFuente() {
        System.out.println("Metodo de opcion fuente");
        
        int opcionElegida = 0;
        if (this.rbtn_Archivo.isSelected()) {
            opcionElegida = 1;
            System.out.println("opcion fuente 1");
        }
        if (this.rbtn_Base.isSelected()) {
            opcionElegida = 2;
            System.out.println("opcion fuente 2");
        }
        if (this.rbtn_xml.isSelected()) {
            opcionElegida = 3;
            System.out.println("opcion fuente 3");
        }
        return opcionElegida;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gbtn_GrupoFuentes = new javax.swing.ButtonGroup();
        rbtn_Archivo = new javax.swing.JRadioButton();
        rbtn_Base = new javax.swing.JRadioButton();
        rbtn_xml = new javax.swing.JRadioButton();
        btn_Aceptar = new javax.swing.JButton();
        jl_Resultado = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Fuente Información"));

        gbtn_GrupoFuentes.add(rbtn_Archivo);
        rbtn_Archivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtn_Archivo.setText("Archivo plano");

        gbtn_GrupoFuentes.add(rbtn_Base);
        rbtn_Base.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtn_Base.setText("Base de datos");

        gbtn_GrupoFuentes.add(rbtn_xml);
        rbtn_xml.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rbtn_xml.setText("XML");

        btn_Aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/aceptar.png"))); // NOI18N
        btn_Aceptar.setActionCommand("Aceptar");
        btn_Aceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_AceptarKeyPressed(evt);
            }
        });

        jl_Resultado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_Resultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtn_xml)
                    .addComponent(rbtn_Base)
                    .addComponent(rbtn_Archivo))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(rbtn_Archivo)
                .addGap(18, 18, 18)
                .addComponent(rbtn_Base)
                .addGap(18, 18, 18)
                .addComponent(rbtn_xml)
                .addGap(27, 27, 27)
                .addComponent(btn_Aceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jl_Resultado, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AceptarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_AceptarKeyPressed
        if (evt.getKeyCode() == 10) {
            this.controladorP.opcionConfirmar();
        }
    }//GEN-LAST:event_btn_AceptarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Aceptar;
    private javax.swing.ButtonGroup gbtn_GrupoFuentes;
    private javax.swing.JLabel jl_Resultado;
    private javax.swing.JRadioButton rbtn_Archivo;
    private javax.swing.JRadioButton rbtn_Base;
    private javax.swing.JRadioButton rbtn_xml;
    // End of variables declaration//GEN-END:variables
}
