/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.view.form;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Sara
 */
public class FrmVlasnikPosebnogDela extends javax.swing.JDialog {

    /**
     * Creates new form FrmVlasnikPosebnogDela
     */
    public FrmVlasnikPosebnogDela(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblVlasnikId = new javax.swing.JLabel();
        txtVlasnikId = new javax.swing.JTextField();
        lblPib = new javax.swing.JLabel();
        lblUlica = new javax.swing.JLabel();
        txtKontakt = new javax.swing.JTextField();
        lblMesto = new javax.swing.JLabel();
        lblBroj = new javax.swing.JLabel();
        lblTekuciRacun = new javax.swing.JLabel();
        txtPrezime = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtBroj = new javax.swing.JTextField();
        btnEnableChanges = new javax.swing.JButton();
        txtVelicina = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        lblNaslov = new javax.swing.JLabel();
        lblVelicinaPosebnogDela = new javax.swing.JLabel();
        cbStambenaZajednica = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtIme = new javax.swing.JTextField();
        cbMernaJedinica = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblVlasnikId.setText("ID:");

        lblPib.setText("Kontakt vlasnika:");

        lblUlica.setText("Prezime:");

        lblMesto.setText("Ime:");

        lblBroj.setText("Broj posebnog dela (stana):");

        lblTekuciRacun.setText("Merna jedinica:");

        btnEdit.setText("Edit");

        btnCancel.setText("Cancel");

        btnEnableChanges.setText("Enable changes");

        btnSave.setText("Save");

        lblNaslov.setText("Vlasnik posebnog dela:");

        lblVelicinaPosebnogDela.setText("Velicina posebnog dela (stana):");

        cbStambenaZajednica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Stambena zajednica:");

        cbMernaJedinica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUlica)
                                    .addComponent(lblBroj))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrezime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtBroj, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPib)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtKontakt, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblVelicinaPosebnogDela)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                .addComponent(txtVelicina, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblVlasnikId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtVlasnikId, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbStambenaZajednica, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMesto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNaslov)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTekuciRacun)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbMernaJedinica, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEnableChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(324, 324, 324)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNaslov)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbStambenaZajednica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVlasnikId)
                    .addComponent(txtVlasnikId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMesto)
                    .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUlica)
                    .addComponent(txtPrezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBroj)
                    .addComponent(txtBroj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVelicina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVelicinaPosebnogDela))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTekuciRacun)
                    .addComponent(cbMernaJedinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPib)
                    .addComponent(txtKontakt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnCancel)
                    .addComponent(btnEnableChanges)
                    .addComponent(btnSave))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEnableChanges;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<Object> cbMernaJedinica;
    private javax.swing.JComboBox<Object> cbStambenaZajednica;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBroj;
    private javax.swing.JLabel lblMesto;
    private javax.swing.JLabel lblNaslov;
    private javax.swing.JLabel lblPib;
    private javax.swing.JLabel lblTekuciRacun;
    private javax.swing.JLabel lblUlica;
    private javax.swing.JLabel lblVelicinaPosebnogDela;
    private javax.swing.JLabel lblVlasnikId;
    private javax.swing.JTextField txtBroj;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtKontakt;
    private javax.swing.JTextField txtPrezime;
    private javax.swing.JTextField txtVelicina;
    private javax.swing.JTextField txtVlasnikId;
    // End of variables declaration//GEN-END:variables

     public void addSaveBtnActionListener(ActionListener actionListener) {
        btnSave.addActionListener(actionListener);
    }

    public void addEnableChangesBtnActionListener(ActionListener actionListener) {
        btnEnableChanges.addActionListener(actionListener);
    }

    public void addCancelBtnActionListener(ActionListener actionListener) {
        btnCancel.addActionListener(actionListener);
    }

    public void addEditBtnActionListener(ActionListener actionListener) {
        btnEdit.addActionListener(actionListener);
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnEnableChanges() {
        return btnEnableChanges;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JComboBox<Object> getCbMernaJedinica() {
        return cbMernaJedinica;
    }

    public JComboBox<Object> getCbStambenaZajednica() {
        return cbStambenaZajednica;
    }

    public JTextField getTxtBroj() {
        return txtBroj;
    }

    public JTextField getTxtIme() {
        return txtIme;
    }

    public JTextField getTxtKontakt() {
        return txtKontakt;
    }

    public JTextField getTxtPrezime() {
        return txtPrezime;
    }

    public JTextField getTxtVelicina() {
        return txtVelicina;
    }

    public JTextField getTxtVlasnikId() {
        return txtVlasnikId;
    }
    
}
