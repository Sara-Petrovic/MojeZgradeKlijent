/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.nprog.zgradeklijent.communication.Communication;
import rs.ac.bg.fon.nprog.zgradeklijent.view.component.table.SednicaSkupstineTableModel;
import rs.ac.bg.fon.nprog.zgradeklijent.view.cordinator.MainCordinator;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.FrmPretragaSednicaSkupstine;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaSkupstine;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;

/**
 *
 * @author Sara
 */
public class SednicaSkupstinePretragaController {

    private final FrmPretragaSednicaSkupstine frmPretragaSednicaSkupstine;

    public SednicaSkupstinePretragaController(FrmPretragaSednicaSkupstine frmPretragaSednicaSkupstine) {
        this.frmPretragaSednicaSkupstine = frmPretragaSednicaSkupstine;
        addActionListener();

    }

    private void addActionListener() {
        frmPretragaSednicaSkupstine.btnPronadjiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SednicaSkupstine sednicaSkupstine = new SednicaSkupstine();
                StambenaZajednica sz = (StambenaZajednica) frmPretragaSednicaSkupstine.getCbStambeneZajednice().getSelectedItem();

                sednicaSkupstine.setStambenaZajednica(sz);
                try {
                    List<SednicaSkupstine> sednice = Communication.getInstance().nadjiSedniceSkupstina(sednicaSkupstine);
                    ((SednicaSkupstineTableModel) (frmPretragaSednicaSkupstine.getTblTabela().getModel())).setSednice(sednice);
                    if (sednice.isEmpty()) {
                        JOptionPane.showMessageDialog(frmPretragaSednicaSkupstine, "Sistem ne moze da nadje sednice skupstina po zadatoj vrednosti");
                    } else {
                        JOptionPane.showMessageDialog(frmPretragaSednicaSkupstine, "Sistem je nasao sednice skupstina po zadatoj vrednosti");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmPretragaSednicaSkupstine, "Sistem ne moze da nadje sednice skupstina po zadatoj vrednosti");
                    Logger.getLogger(SednicaSkupstinePretragaController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    public void openForm() {
        frmPretragaSednicaSkupstine.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        prepareView();
        frmPretragaSednicaSkupstine.setVisible(true);
    }

    private void prepareView() {
        frmPretragaSednicaSkupstine.setTitle("Pretraga sednica skupstine");
        fillCbStambeneZajednice();
        fillTblSedniceSkupstine();
    }

    private void fillTblSedniceSkupstine() {
        List<SednicaSkupstine> sednice = new ArrayList<>();
        try {
            SednicaSkupstineTableModel model = new SednicaSkupstineTableModel(sednice);
            frmPretragaSednicaSkupstine.getTblTabela().setModel(model);
            SednicaSkupstine sednicaSkupstine = new SednicaSkupstine();
            StambenaZajednica sz = (StambenaZajednica) frmPretragaSednicaSkupstine.getCbStambeneZajednice().getSelectedItem();

            sednicaSkupstine.setStambenaZajednica(sz);
            try {
                List<SednicaSkupstine> sednice1 = Communication.getInstance().nadjiSedniceSkupstina(sednicaSkupstine);
                ((SednicaSkupstineTableModel) (frmPretragaSednicaSkupstine.getTblTabela().getModel())).setSednice(sednice1);
                JOptionPane.showMessageDialog(frmPretragaSednicaSkupstine, "Sistem je nasao sednice skupstina po zadatoj vrednosti");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frmPretragaSednicaSkupstine, "Sistem ne moze da nadje sednice skupstina po zadatoj vrednosti");
                Logger.getLogger(SednicaSkupstinePretragaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmPretragaSednicaSkupstine, "Error: " + ex.getMessage(), "ERROR DETAILS", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SednicaSkupstinePretragaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refresh() {
        fillTblSedniceSkupstine();
    }

    private void fillCbStambeneZajednice() {
        frmPretragaSednicaSkupstine.getCbStambeneZajednice().removeAllItems();
        List<StambenaZajednica> stambenaZajednice = new ArrayList<>();
        try {
            stambenaZajednice = Communication.getInstance().ucitajListuStambenihZajednica();
        } catch (Exception ex) {
            Logger.getLogger(SednicaSkupstinePretragaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmPretragaSednicaSkupstine.getCbStambeneZajednice().setModel(new DefaultComboBoxModel<>(stambenaZajednice.toArray()));
    }
}
