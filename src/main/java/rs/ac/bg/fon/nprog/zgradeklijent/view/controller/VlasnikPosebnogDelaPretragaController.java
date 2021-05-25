/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.nprog.zgradeklijent.communication.Communication;
import rs.ac.bg.fon.nprog.zgradeklijent.view.component.table.VlasnikPosebnogDelaTableModel;
import rs.ac.bg.fon.nprog.zgradeklijent.view.constant.Constants;
import rs.ac.bg.fon.nprog.zgradeklijent.view.cordinator.MainCordinator;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.FrmPretragaVlasnikPosebnogDela;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;

/**
 *
 * @author Sara
 */
public class VlasnikPosebnogDelaPretragaController {

    private final FrmPretragaVlasnikPosebnogDela frmPretragaVlasnikPosebnogDela;

    public VlasnikPosebnogDelaPretragaController(FrmPretragaVlasnikPosebnogDela frmPretragaVlasnikPosebnogDela) {
        this.frmPretragaVlasnikPosebnogDela = frmPretragaVlasnikPosebnogDela;
        addActionListener();

    }

    private void addActionListener() {
        frmPretragaVlasnikPosebnogDela.btnPronadjiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VlasnikPosebnogDela vlasnik = new VlasnikPosebnogDela();
                String prezime = frmPretragaVlasnikPosebnogDela.getTxtPrezime().getText();
                if (prezime == null) {
                    prezime = "";
                }
                vlasnik.setPrezime(prezime);
                try {
                    List<VlasnikPosebnogDela> lista = Communication.getInstance().nadjiVlasnikePosebnihDelova(vlasnik);
                    ((VlasnikPosebnogDelaTableModel) (frmPretragaVlasnikPosebnogDela.getTblTabela().getModel())).setVlasnici(lista);
                    if (lista.isEmpty()) {
                        JOptionPane.showMessageDialog(frmPretragaVlasnikPosebnogDela, "Sistem ne moze da nadje vlasnike posebnih delova po zadatoj vrednosti");
                    } else {
                        JOptionPane.showMessageDialog(frmPretragaVlasnikPosebnogDela, "Sistem je nasao vlasnike posebnih delova po zadatoj vrednosti");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmPretragaVlasnikPosebnogDela, "Sistem ne moze da nadje vlasnike posebnih delova po zadatoj vrednosti");
                    Logger.getLogger(VlasnikPosebnogDelaPretragaController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        frmPretragaVlasnikPosebnogDela.getbtnDetaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmPretragaVlasnikPosebnogDela.getTblTabela().getSelectedRow();
                if (row >= 0) {
                    Long sifraVLasnika = ((VlasnikPosebnogDelaTableModel) frmPretragaVlasnikPosebnogDela.getTblTabela().getModel()).getVlasnikAt(row).getVlasnikId();
                    VlasnikPosebnogDela vlasnik = new VlasnikPosebnogDela();
                    vlasnik.setVlasnikId(sifraVLasnika);
                    VlasnikPosebnogDela ucitaniVlasnik = null;
                    try {
                        ucitaniVlasnik = Communication.getInstance().ucitajVlasnikaPosebnogDela(vlasnik);
                        JOptionPane.showMessageDialog(frmPretragaVlasnikPosebnogDela, "Sistem je ucitao vlasnika posebnog dela");
                    } catch (Exception ex) {
                        Logger.getLogger(VlasnikPosebnogDelaPretragaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(frmPretragaVlasnikPosebnogDela, "Sistem ne moze da ucita vlasnika posebnog dela");

                    }
                    MainCordinator.getInstance().addParam(Constants.PARAM_VLASNIK, ucitaniVlasnik);
                    MainCordinator.getInstance().openVlasnikPosebnogDelaDetailsVlasnikForm();
                } else {
                    JOptionPane.showMessageDialog(frmPretragaVlasnikPosebnogDela, "Morate selektovati vlasnika posebnog dela", "VLASNIK POSEBNOG DELA DETAILS", JOptionPane.ERROR_MESSAGE);
                }
                fillTblVlasnikaPosebnihDelova();
            }
        });

    }

    public void openForm() {
        frmPretragaVlasnikPosebnogDela.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        prepareView();
        frmPretragaVlasnikPosebnogDela.setVisible(true);
    }

    private void prepareView() {
        frmPretragaVlasnikPosebnogDela.setTitle("Pretraga vlasnika posebnog dela");
        fillTblVlasnikaPosebnihDelova();
    }

    private void fillTblVlasnikaPosebnihDelova() {
        List<VlasnikPosebnogDela> vlasnici;
        try {
            vlasnici = Communication.getInstance().ucitajListuVlasnikaPosebnihDelova();
            VlasnikPosebnogDelaTableModel model = new VlasnikPosebnogDelaTableModel(vlasnici);
            frmPretragaVlasnikPosebnogDela.getTblTabela().setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmPretragaVlasnikPosebnogDela, "Error: " + ex.getMessage(), "ERROR DETAILS", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(StambenaZajednicaViewAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refresh() {
        fillTblVlasnikaPosebnihDelova();
    }
}
