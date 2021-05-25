/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.nprog.zgradeklijent.communication.Communication;
import rs.ac.bg.fon.nprog.zgradeklijent.view.constant.Constants;
import rs.ac.bg.fon.nprog.zgradeklijent.view.cordinator.MainCordinator;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.FrmMain;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;

/**
 *
 * @author Sara
 */
public class MainController {

    private  final FrmMain frmMain;

    public MainController(FrmMain frmMain) {
        this.frmMain = frmMain;
        addActionListener();

    }
    
    

    public void openForm() {
        //pre nego sto se prikaze forma doda se ko je ulogovan u labelu
        Korisnik korisnik = (Korisnik) MainCordinator.getInstance().getParam(Constants.PARAM_CURRENT_USER);
        frmMain.getLblUlogovanKorisnik().setText(korisnik.getIme() + ", " + korisnik.getPrezime());
        frmMain.setVisible(true);
        try {
            Communication.getInstance().setFrmMain(frmMain);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    private void addActionListener() {

        frmMain.jmiStambenaZajednicaNovaAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainCordinator.getInstance().openAddNewStambenaZajednicaForm();
            }

        });
        frmMain.jmiStabenaZajednicaPrikaziSveAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainCordinator.getInstance().openViewAllStambenaZajednicaForm();
            }

        });

        frmMain.jmiStabenaZajednicaPretragaAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openPretragaStambenaZajednicaForm();
            }
        });
        frmMain.jmiVlasnikPosebnogDelaNoviAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainCordinator.getInstance().openAddNewVlasnikPosebnogDelaForm();
            }

        });

        frmMain.jmiVlasnikPosebnogDelaPretrazivanjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openPretragaVlasnikaPosebnogDelaForm();
            }
        });

        frmMain.jmiSednicaSkupstineNovaAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    MainCordinator.getInstance().openAddNewSednicaSkupstineForm();
                } catch (Exception ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmMain, "Sistem ne moze da kreira sednicu skupstine");
                }
            }

        });
        frmMain.jmiSednicaSkupstinePretrazivanjeAddActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainCordinator.getInstance().openPretragaSednicaSkupstineForm();
            }

        });
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }

}
