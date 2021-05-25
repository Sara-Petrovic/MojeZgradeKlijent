/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.nprog.zgradeklijent.communication.Communication;
import rs.ac.bg.fon.nprog.zgradeklijent.view.component.table.StambenaZajednicaTableModel;
import rs.ac.bg.fon.nprog.zgradeklijent.view.constant.Constants;
import rs.ac.bg.fon.nprog.zgradeklijent.view.cordinator.MainCordinator;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.FrmViewStambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;

/**
 *
 * @author Sara
 */
public class StambenaZajednicaViewAllController {

    private final FrmViewStambenaZajednica frmViewStambenaZajednica;

    public StambenaZajednicaViewAllController(FrmViewStambenaZajednica frmViewStambenaZajednica) {
        this.frmViewStambenaZajednica = frmViewStambenaZajednica;
        addActionListener();
    }

    private void addActionListener() {
        frmViewStambenaZajednica.getBtnDetailsAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = frmViewStambenaZajednica.getTblStambenaZajednica().getSelectedRow();
                if (row >= 0) {
                    StambenaZajednica stambenaZajednica = ((StambenaZajednicaTableModel) frmViewStambenaZajednica.getTblStambenaZajednica().getModel()).getStambenaZajednicaAt(row);
                    MainCordinator.getInstance().addParam(Constants.PARAM_STAMBENA_ZAJEDNICA, stambenaZajednica);
                    MainCordinator.getInstance().openStambenaZajednicaDetailsStambenaZajednicaForm();
                } else {
                    JOptionPane.showMessageDialog(frmViewStambenaZajednica, "You must select stambena zajednica", "STAMBENA ZAJEDNICA DETAILS", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frmViewStambenaZajednica.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                fillTblStambeneZajednice();
            }
        });

    }

    public void openForm() {
        frmViewStambenaZajednica.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        prepareView();
        frmViewStambenaZajednica.setVisible(true);
    }

    private void prepareView() {
        frmViewStambenaZajednica.setTitle("View stambene zajednice");
        fillTblStambeneZajednice();
    }

    private void fillTblStambeneZajednice() {
        List<StambenaZajednica> stambeneZajednice;
        try {
            stambeneZajednice = Communication.getInstance().ucitajListuStambenihZajednica();
            StambenaZajednicaTableModel sztm = new StambenaZajednicaTableModel(stambeneZajednice);
            frmViewStambenaZajednica.getTblStambenaZajednica().setModel(sztm);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmViewStambenaZajednica, "Error: " + ex.getMessage(), "ERROR DETAILS", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(StambenaZajednicaViewAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refresh() {
        fillTblStambeneZajednice();
    }

}
