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
import rs.ac.bg.fon.nprog.zgradeklijent.view.constant.Constants;
import rs.ac.bg.fon.nprog.zgradeklijent.view.cordinator.MainCordinator;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.FrmStambenaZajednica;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.FrmVlasnikPosebnogDela;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.util.FormMode;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.MernaJedinica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;

/**
 *
 * @author Sara
 */
public class VlasnikPosebnogDelaController {

    private final FrmVlasnikPosebnogDela frmVlasnikPosebnogDela;

    public VlasnikPosebnogDelaController(FrmVlasnikPosebnogDela frmVlasnikPosebnogDela) {
        this.frmVlasnikPosebnogDela = frmVlasnikPosebnogDela;
        addActionListeners();
    }

    private void addActionListeners() {
        frmVlasnikPosebnogDela.addSaveBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    VlasnikPosebnogDela vlasnikPosebnogDela = new VlasnikPosebnogDela();
                    vlasnikPosebnogDela.setVlasnikId(Long.parseLong(frmVlasnikPosebnogDela.getTxtVlasnikId().getText().trim()));
                    vlasnikPosebnogDela.setStambenaZajednica((StambenaZajednica) frmVlasnikPosebnogDela.getCbStambenaZajednica().getSelectedItem());
                    vlasnikPosebnogDela.setIme(frmVlasnikPosebnogDela.getTxtIme().getText().trim());
                    vlasnikPosebnogDela.setPrezime(frmVlasnikPosebnogDela.getTxtPrezime().getText().trim());
                    vlasnikPosebnogDela.setBrojPosebnogDela(frmVlasnikPosebnogDela.getTxtBroj().getText().trim());
                    vlasnikPosebnogDela.setVelicinaPosebnogDela(Double.parseDouble(frmVlasnikPosebnogDela.getTxtVelicina().getText().trim()));
                    vlasnikPosebnogDela.setKontaktVlasnika(frmVlasnikPosebnogDela.getTxtKontakt().getText().trim());
                    vlasnikPosebnogDela.setMernaJedinica((MernaJedinica) frmVlasnikPosebnogDela.getCbMernaJedinica().getSelectedItem());

                    Communication.getInstance().unesiVlasnikaPosebnogDela(vlasnikPosebnogDela);
                    JOptionPane.showMessageDialog(frmVlasnikPosebnogDela, "Sistem je zapamtio vlasnika posebnog dela");
                    frmVlasnikPosebnogDela.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(FrmStambenaZajednica.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmVlasnikPosebnogDela, ex.getMessage());
                }
            }
        });

        frmVlasnikPosebnogDela.addEnableChangesBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupComponents(FormMode.FORM_EDIT);
            }
        });

        frmVlasnikPosebnogDela.addCancelBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }

            private void cancel() {
                frmVlasnikPosebnogDela.dispose();
            }
        });

        frmVlasnikPosebnogDela.addEditBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }

            private void edit() {
                VlasnikPosebnogDela vlasnik = makeVlasnikPosebnogDelaFromForm();
                try {
                    Communication.getInstance().zapamtiVlasnikaPosebnogDela(vlasnik);
                    JOptionPane.showMessageDialog(frmVlasnikPosebnogDela, "Sistem je zapamtio vlasnika posebnog dela");
                    frmVlasnikPosebnogDela.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmVlasnikPosebnogDela, "Sistem ne moze da zapamti vlasnika posebnog dela");
                    Logger.getLogger(VlasnikPosebnogDelaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void openForm(FormMode formMode) {
        frmVlasnikPosebnogDela.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        prepareView(formMode);
        frmVlasnikPosebnogDela.setVisible(true);

    }

    private void prepareView(FormMode formMode) {
        fillCbStambenaZajednica();
        fillCbMernaJedinica();
        setupComponents(formMode);
    }

    private void fillCbStambenaZajednica() {
        frmVlasnikPosebnogDela.getCbStambenaZajednica().removeAllItems();
        List<StambenaZajednica> stambenaZajednice = new ArrayList<>();
        try {
            stambenaZajednice = Communication.getInstance().ucitajListuStambenihZajednica();
        } catch (Exception ex) {
            Logger.getLogger(VlasnikPosebnogDelaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmVlasnikPosebnogDela.getCbStambenaZajednica().setModel(new DefaultComboBoxModel<>(stambenaZajednice.toArray()));
    }

    private void fillCbMernaJedinica() {
        frmVlasnikPosebnogDela.getCbMernaJedinica().removeAllItems();
        frmVlasnikPosebnogDela.getCbMernaJedinica().setModel(new DefaultComboBoxModel<>(MernaJedinica.values()));
    }

    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmVlasnikPosebnogDela.getBtnCancel().setEnabled(true);
                frmVlasnikPosebnogDela.getBtnEdit().setEnabled(false);
                frmVlasnikPosebnogDela.getBtnEnableChanges().setEnabled(false);
                frmVlasnikPosebnogDela.getBtnSave().setEnabled(true);

                frmVlasnikPosebnogDela.getTxtVlasnikId().setEnabled(true);
                frmVlasnikPosebnogDela.getCbStambenaZajednica().setEnabled(true);
                frmVlasnikPosebnogDela.getTxtBroj().setEnabled(true);
                frmVlasnikPosebnogDela.getTxtVelicina().setEnabled(true);
                frmVlasnikPosebnogDela.getCbMernaJedinica().setEnabled(true);
                frmVlasnikPosebnogDela.getTxtIme().setEnabled(true);
                frmVlasnikPosebnogDela.getTxtPrezime().setEnabled(true);
                frmVlasnikPosebnogDela.getTxtKontakt().setEnabled(true);
                break;
            case FORM_VIEW:
                frmVlasnikPosebnogDela.getBtnCancel().setEnabled(true);
                frmVlasnikPosebnogDela.getBtnEdit().setEnabled(false);
                frmVlasnikPosebnogDela.getBtnEnableChanges().setEnabled(true);
                frmVlasnikPosebnogDela.getBtnSave().setEnabled(false);

                //zabrani izmenu vrednosti
                frmVlasnikPosebnogDela.getTxtVlasnikId().setEnabled(false);
                frmVlasnikPosebnogDela.getCbStambenaZajednica().setEnabled(false);
                frmVlasnikPosebnogDela.getTxtBroj().setEnabled(false);
                frmVlasnikPosebnogDela.getTxtVelicina().setEnabled(false);
                frmVlasnikPosebnogDela.getCbMernaJedinica().setEnabled(false);
                frmVlasnikPosebnogDela.getTxtIme().setEnabled(false);
                frmVlasnikPosebnogDela.getTxtPrezime().setEnabled(false);
                frmVlasnikPosebnogDela.getTxtKontakt().setEnabled(false);

                //get vlasnika
                VlasnikPosebnogDela vlasnikPosebnogDela = (VlasnikPosebnogDela) MainCordinator.getInstance().getParam(Constants.PARAM_VLASNIK);
                frmVlasnikPosebnogDela.getTxtVlasnikId().setText(vlasnikPosebnogDela.getVlasnikId() + "");
                frmVlasnikPosebnogDela.getTxtIme().setText(vlasnikPosebnogDela.getIme());
                frmVlasnikPosebnogDela.getTxtPrezime().setText(vlasnikPosebnogDela.getPrezime());
                frmVlasnikPosebnogDela.getTxtKontakt().setText(vlasnikPosebnogDela.getKontaktVlasnika());
                frmVlasnikPosebnogDela.getTxtBroj().setText(vlasnikPosebnogDela.getBrojPosebnogDela());
                frmVlasnikPosebnogDela.getTxtVelicina().setText(vlasnikPosebnogDela.getVelicinaPosebnogDela() + "");
                frmVlasnikPosebnogDela.getCbMernaJedinica().setSelectedItem(vlasnikPosebnogDela.getMernaJedinica());
                System.out.println(vlasnikPosebnogDela.getStambenaZajednica());
                System.out.println(vlasnikPosebnogDela.getStambenaZajednica().getStambenaZajednicaId());
                frmVlasnikPosebnogDela.getCbStambenaZajednica().setSelectedItem(vlasnikPosebnogDela.getStambenaZajednica());
                System.out.println(frmVlasnikPosebnogDela.getCbStambenaZajednica().getSelectedItem());
                break;
            case FORM_EDIT:
                frmVlasnikPosebnogDela.getBtnCancel().setEnabled(true);
                frmVlasnikPosebnogDela.getBtnEdit().setEnabled(true);
                frmVlasnikPosebnogDela.getBtnEnableChanges().setEnabled(false);
                frmVlasnikPosebnogDela.getBtnSave().setEnabled(false);

                //zabrani izmenu vrednosti
                frmVlasnikPosebnogDela.getTxtVlasnikId().setEnabled(false);
                frmVlasnikPosebnogDela.getCbStambenaZajednica().setEnabled(true);
                frmVlasnikPosebnogDela.getTxtBroj().setEnabled(true);
                frmVlasnikPosebnogDela.getTxtVelicina().setEnabled(true);
                frmVlasnikPosebnogDela.getCbMernaJedinica().setEnabled(true);
                frmVlasnikPosebnogDela.getTxtIme().setEnabled(true);
                frmVlasnikPosebnogDela.getTxtPrezime().setEnabled(true);
                frmVlasnikPosebnogDela.getTxtKontakt().setEnabled(true);
                break;
        }
    }

    private VlasnikPosebnogDela makeVlasnikPosebnogDelaFromForm() {
        VlasnikPosebnogDela vlasnikPosebnogDela = new VlasnikPosebnogDela();
        vlasnikPosebnogDela.setVlasnikId(Long.parseLong(frmVlasnikPosebnogDela.getTxtVlasnikId().getText().trim()));
        vlasnikPosebnogDela.setIme(frmVlasnikPosebnogDela.getTxtIme().getText().trim());
        vlasnikPosebnogDela.setPrezime(frmVlasnikPosebnogDela.getTxtPrezime().getText().trim());
        vlasnikPosebnogDela.setKontaktVlasnika(frmVlasnikPosebnogDela.getTxtKontakt().getText().trim());
        vlasnikPosebnogDela.setBrojPosebnogDela(frmVlasnikPosebnogDela.getTxtBroj().getText().trim());
        vlasnikPosebnogDela.setVelicinaPosebnogDela(Double.parseDouble(frmVlasnikPosebnogDela.getTxtVelicina().getText().trim()));
        vlasnikPosebnogDela.setMernaJedinica((MernaJedinica) frmVlasnikPosebnogDela.getCbMernaJedinica().getSelectedItem());
        vlasnikPosebnogDela.setStambenaZajednica((StambenaZajednica) frmVlasnikPosebnogDela.getCbStambenaZajednica().getSelectedItem());
        return vlasnikPosebnogDela;
    }

}
