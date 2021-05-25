/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.nprog.zgradeklijent.communication.Communication;
import rs.ac.bg.fon.nprog.zgradeklijent.view.constant.Constants;
import rs.ac.bg.fon.nprog.zgradeklijent.view.cordinator.MainCordinator;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.FrmStambenaZajednica;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.util.FormMode;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;

/**
 *
 * @author Sara
 */
public class StambenaZajednicaController {

    private final FrmStambenaZajednica frmStambenaZajednica;

    public StambenaZajednicaController(FrmStambenaZajednica frmStambenaZajednica) {
        this.frmStambenaZajednica = frmStambenaZajednica;
        addActionListeners();
    }

    private void addActionListeners() {
        frmStambenaZajednica.addSaveBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    StambenaZajednica stambenaZajednica = new StambenaZajednica();
                    stambenaZajednica.setStambenaZajednicaId(Long.parseLong(frmStambenaZajednica.getTxtStambenaZajednicaId().getText().trim()));
                    stambenaZajednica.setMesto((Mesto) frmStambenaZajednica.getCbMesto().getSelectedItem());
                    stambenaZajednica.setUlica(frmStambenaZajednica.getTxtUlica().getText().trim());
                    stambenaZajednica.setBroj(frmStambenaZajednica.getTxtBroj().getText().trim());
                    stambenaZajednica.setTekuciRacun(frmStambenaZajednica.getTxtTekuciRacun().getText().trim());
                    stambenaZajednica.setBanka(frmStambenaZajednica.getTxtBanka().getText().trim());
                    stambenaZajednica.setPib(frmStambenaZajednica.getTxtPib().getText().trim());
                    stambenaZajednica.setMaticniBroj(frmStambenaZajednica.getTxtMaticniBroj().getText().trim());

                    Communication.getInstance().unesiStambenuZajednicu(stambenaZajednica);
                    JOptionPane.showMessageDialog(frmStambenaZajednica, "Sistem je zapamtio stambenu zajednicu");
                    frmStambenaZajednica.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(FrmStambenaZajednica.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmStambenaZajednica, ex.getMessage());
//                    try {
//                        Communication.getInstance().getFrmMain().dispose();
//                    } catch (Exception ex1) {
//                        Logger.getLogger(StambenaZajednicaController.class.getName()).log(Level.SEVERE, null, ex1);
//                    }
                }
            }
        });

        frmStambenaZajednica.addEnableChangesBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupComponents(FormMode.FORM_EDIT);
            }
        });

        frmStambenaZajednica.addCancelBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }

            private void cancel() {
                frmStambenaZajednica.dispose();
            }
        });

        frmStambenaZajednica.addDeleteBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }

            private void delete() {
                StambenaZajednica stambenaZajednica = makeStambenaZajednicaFromForm();
                try {
                    Communication.getInstance().obrisiStambenuZajednicu(stambenaZajednica);
                    JOptionPane.showMessageDialog(frmStambenaZajednica, "Sistem je obrisao stambenu zajednicu");
                    frmStambenaZajednica.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmStambenaZajednica, ex.getMessage());
                    Logger.getLogger(StambenaZajednicaController.class.getName()).log(Level.SEVERE, null, ex);
                    frmStambenaZajednica.dispose();
                }
            }
        });

        frmStambenaZajednica.addEditBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }

            private void edit() {
                StambenaZajednica stambenaZajednica = makeStambenaZajednicaFromForm();
                try {
                    Communication.getInstance().zapamtiStambenuZajednicu(stambenaZajednica);
                    JOptionPane.showMessageDialog(frmStambenaZajednica, "Sistem je zapamtio stambenu zajednicu");
                    frmStambenaZajednica.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frmStambenaZajednica, "Sistem ne moze da zapamti stambenu zajednicu");
                    Logger.getLogger(StambenaZajednicaController.class.getName()).log(Level.SEVERE, null, ex);
                    frmStambenaZajednica.dispose();

                }
            }
        });
    }

    public void openForm(FormMode formMode) {
        frmStambenaZajednica.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        prepareView(formMode);
        frmStambenaZajednica.setVisible(true);

    }

    private void prepareView(FormMode formMode) {
        fillCbMesto();
        setupComponents(formMode);
    }

    private void fillCbMesto() {
        try {
            frmStambenaZajednica.getCbMesto().removeAllItems();
            List<Mesto> mesta = Communication.getInstance().ucitajListuMesta();
            frmStambenaZajednica.getCbMesto().setModel(new DefaultComboBoxModel<>(mesta.toArray()));
        } catch (Exception ex) {
            Logger.getLogger(StambenaZajednicaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frmStambenaZajednica, ex.getMessage());
                                frmStambenaZajednica.dispose();

        }
    }

    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmStambenaZajednica.getBtnCancel().setEnabled(true);
                frmStambenaZajednica.getBtnDelete().setEnabled(false);
                frmStambenaZajednica.getBtnEdit().setEnabled(false);
                frmStambenaZajednica.getBtnEnableChanges().setEnabled(false);
                frmStambenaZajednica.getBtnSave().setEnabled(true);

                frmStambenaZajednica.getTxtStambenaZajednicaId().setEnabled(true);
                frmStambenaZajednica.getTxtUlica().setEnabled(true);
                frmStambenaZajednica.getTxtBroj().setEnabled(true);
                frmStambenaZajednica.getTxtPib().setEnabled(true);
                frmStambenaZajednica.getTxtTekuciRacun().setEnabled(true);
                frmStambenaZajednica.getTxtBanka().setEnabled(true);
                frmStambenaZajednica.getTxtMaticniBroj().setEnabled(true);
                frmStambenaZajednica.getCbMesto().setEnabled(true);
                break;
            case FORM_VIEW:
                frmStambenaZajednica.getBtnCancel().setEnabled(true);
                frmStambenaZajednica.getBtnDelete().setEnabled(true);
                frmStambenaZajednica.getBtnEdit().setEnabled(false);
                frmStambenaZajednica.getBtnEnableChanges().setEnabled(true);
                frmStambenaZajednica.getBtnSave().setEnabled(false);

                //zabrani izmenu vrednosti
                frmStambenaZajednica.getTxtStambenaZajednicaId().setEnabled(false);
                frmStambenaZajednica.getTxtUlica().setEnabled(false);
                frmStambenaZajednica.getTxtBroj().setEnabled(false);
                frmStambenaZajednica.getTxtPib().setEnabled(false);
                frmStambenaZajednica.getTxtTekuciRacun().setEnabled(false);
                frmStambenaZajednica.getTxtBanka().setEnabled(false);
                frmStambenaZajednica.getTxtMaticniBroj().setEnabled(false);
                frmStambenaZajednica.getCbMesto().setEnabled(false);

                //get stambena zajednica
                StambenaZajednica stambenaZajednica = (StambenaZajednica) MainCordinator.getInstance().getParam(Constants.PARAM_STAMBENA_ZAJEDNICA);
                frmStambenaZajednica.getTxtStambenaZajednicaId().setText(stambenaZajednica.getStambenaZajednicaId() + "");
                frmStambenaZajednica.getTxtUlica().setText(stambenaZajednica.getUlica());
                frmStambenaZajednica.getTxtBroj().setText(stambenaZajednica.getBroj());
                frmStambenaZajednica.getTxtTekuciRacun().setText(stambenaZajednica.getTekuciRacun());
                frmStambenaZajednica.getTxtBanka().setText(stambenaZajednica.getBanka());
                frmStambenaZajednica.getTxtPib().setText(stambenaZajednica.getPib());
                frmStambenaZajednica.getTxtMaticniBroj().setText(stambenaZajednica.getMaticniBroj());
                frmStambenaZajednica.getCbMesto().setSelectedItem(stambenaZajednica.getMesto());
                break;
            case FORM_EDIT:
                frmStambenaZajednica.getBtnCancel().setEnabled(true);
                frmStambenaZajednica.getBtnDelete().setEnabled(false);
                frmStambenaZajednica.getBtnEdit().setEnabled(true);
                frmStambenaZajednica.getBtnEnableChanges().setEnabled(false);
                frmStambenaZajednica.getBtnSave().setEnabled(false);

                //zabrani izmenu vrednosti
                frmStambenaZajednica.getTxtStambenaZajednicaId().setEnabled(false);
                frmStambenaZajednica.getTxtUlica().setEnabled(true);
                frmStambenaZajednica.getTxtBroj().setEnabled(true);
                frmStambenaZajednica.getTxtPib().setEnabled(true);
                frmStambenaZajednica.getTxtTekuciRacun().setEnabled(true);
                frmStambenaZajednica.getTxtBanka().setEnabled(true);
                frmStambenaZajednica.getTxtMaticniBroj().setEnabled(true);
                frmStambenaZajednica.getCbMesto().setEnabled(true);
                break;
        }
    }

    private StambenaZajednica makeStambenaZajednicaFromForm() {
        StambenaZajednica stambenaZajednica = new StambenaZajednica();
        stambenaZajednica.setStambenaZajednicaId(Long.parseLong(frmStambenaZajednica.getTxtStambenaZajednicaId().getText().trim()));
        stambenaZajednica.setUlica(frmStambenaZajednica.getTxtUlica().getText().trim());
        stambenaZajednica.setBroj(frmStambenaZajednica.getTxtBroj().getText().trim());
        stambenaZajednica.setTekuciRacun(frmStambenaZajednica.getTxtTekuciRacun().getText().trim());
        stambenaZajednica.setBanka(frmStambenaZajednica.getTxtBanka().getText().trim());
        stambenaZajednica.setPib(frmStambenaZajednica.getTxtPib().getText().trim());
        stambenaZajednica.setMaticniBroj(frmStambenaZajednica.getTxtMaticniBroj().getText().trim());
        stambenaZajednica.setMesto((Mesto) frmStambenaZajednica.getCbMesto().getSelectedItem());
        return stambenaZajednica;
    }

}
