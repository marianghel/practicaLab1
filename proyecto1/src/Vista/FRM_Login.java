
package Vista;

import Controlador.Controlador_FRM_MenuPrincipal;

public class FRM_Login extends javax.swing.JFrame {

    /**
     * Creates new form FRM_Login
     */
    public FRM_Login(Controlador_FRM_MenuPrincipal controlador) {
        initComponents();
        
       setLocation(160,150);
       
         this.gUI_InformacionLogin1.agregarControladorPrincipal(controlador);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gUI_InformacionLogin1 = new Vista.GUI_InformacionLogin();
        jl_Fondo = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(gUI_InformacionLogin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jl_Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo 2.png"))); // NOI18N
        getContentPane().add(jl_Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-210, -40, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Vista.GUI_InformacionLogin gUI_InformacionLogin1;
    private javax.swing.JLabel jl_Fondo;
    // End of variables declaration//GEN-END:variables
}