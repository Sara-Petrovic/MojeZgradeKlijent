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
import rs.ac.bg.fon.nprog.zgradeklijent.view.component.table.StambenaZajednicaTableModel;
import rs.ac.bg.fon.nprog.zgradeklijent.view.constant.Constants;
import rs.ac.bg.fon.nprog.zgradeklijent.view.cordinator.MainCordinator;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.FrmPretragaStambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;

/**
 *
 * @author Sara
 */
public class StambenaZajednicaPretragaController {

    private final FrmPretragaStambenaZajednica frmStambenaZajednicaPretraga;

    public StambenaZajednicaPretragaController(FrmPretragaStambenaZajednica frmStambenaZajednicaPretraga) {
        this.frmStambenaZajednicaPretraga = frmStambenaZajednicaPretraga;
        addActionListener();

    }

    private void addActionListener() {

        frmStambenaZajednicaPretraga.getBtnPronadji().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StambenaZajednica sz1 = new StambenaZajednica();
                String ulica = frmStambenaZajednicaPretraga.getTxtUlica().getText().trim();
                if (ulica == null) {
                    ulica = "";
                }
                sz1.setUlica(ulica);
                try {
                    List<StambenaZajednica> lista = Communication.getInstance().nadjiStambeneZajednice(sz1);
                    //System.out.println("lista duzina" + lista.size());
                    ((StambenaZajednicaTableModel) (frmStambenaZajednicaPretraga.getTblTabela().getModel())).setStambeneZajednice(lista);
                    if (lista.isEmpty()) {
                        JOptionPane.showMessageDialog(frmStambenaZajednicaPretraga, "Sistem ne moze da nadje stambene zajednice po zadatoj vrednosti");
                    } else {
                        JOptionPane.showMessageDialog(frmStambenaZajednicaPretraga, "Sistem je nasao stambene zajednice po zadatoj vrednosti");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmStambenaZajednicaPretraga, ex.getMessage());
                    Logger.getLogger(StambenaZajednicaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        frmStambenaZajednicaPretraga.getBtnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmStambenaZajednicaPretraga.getTblTabela().getSelectedRow();
                if (row >= 0) {
                    Long stambenaZajednicaId = ((StambenaZajednicaTableModel) frmStambenaZajednicaPretraga.getTblTabela().getModel()).getStambenaZajednicaAt(row).getStambenaZajednicaId();
                    StambenaZajednica stambenaZajednica = new StambenaZajednica();
                    stambenaZajednica.setStambenaZajednicaId(stambenaZajednicaId);
                    StambenaZajednica ucitanaStambenaZajednica = null;
                    try {
                        ucitanaStambenaZajednica = Communication.getInstance().ucitajStambenuZajednicu(stambenaZajednica);
                        System.out.println("mestoid: " + ucitanaStambenaZajednica.getMesto().getMestoId());
                        JOptionPane.showMessageDialog(frmStambenaZajednicaPretraga, "Sistem je ucitao stambenu zajednicu");

                    } catch (Exception ex) {
                        Logger.getLogger(StambenaZajednicaPretragaController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(frmStambenaZajednicaPretraga, "Sistem ne moze da ucita stambenu zajednicu");

                    }
                    MainCordinator.getInstance().addParam(Constants.PARAM_STAMBENA_ZAJEDNICA, ucitanaStambenaZajednica);
                    MainCordinator.getInstance().openStambenaZajednicaDetailsStambenaZajednicaForm();
                } else {
                    JOptionPane.showMessageDialog(frmStambenaZajednicaPretraga, "Morate selektovati stambenu zajednicu", "STAMBENA ZAJEDNICA DETAILS", JOptionPane.ERROR_MESSAGE);
                }
                fillTblStambeneZajednice();
            }
        });

    }

    public void openForm() {
        frmStambenaZajednicaPretraga.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        prepareView();
        frmStambenaZajednicaPretraga.setVisible(true);
        

    }

    private void prepareView() {
        frmStambenaZajednicaPretraga.setTitle("Pretraga stambene zajednice");
        fillTblStambeneZajednice();
    }

    private void fillTblStambeneZajednice() {
        List<StambenaZajednica> stambeneZajednice;
        try {
            stambeneZajednice = Communication.getInstance().ucitajListuStambenihZajednica();
            StambenaZajednicaTableModel sztm = new StambenaZajednicaTableModel(stambeneZajednice);
            frmStambenaZajednicaPretraga.getTblTabela().setModel(sztm);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmStambenaZajednicaPretraga, "Error: " + ex.getMessage(), "ERROR DETAILS", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(StambenaZajednicaViewAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refresh() {
        fillTblStambeneZajednice();
    }
}
