/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.nprog.zgradeklijent.communication.Communication;
import rs.ac.bg.fon.nprog.zgradeklijent.view.component.table.VlasnikPosebnogDelaTableModel;
import rs.ac.bg.fon.nprog.zgradeklijent.view.cordinator.MainCordinator;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.FrmMain;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.FrmSednicaSkupstine;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.util.FormMode;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaSkupstine;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;

/**
 *
 * @author Sara
 */
public class SednicaSkupstineController {

    private final FrmSednicaSkupstine frmSednicaSkupstine;

    public SednicaSkupstineController(FrmSednicaSkupstine frmSednicaSkupstine) {
        this.frmSednicaSkupstine = frmSednicaSkupstine;
        addActionListeners();
    }

    private void addActionListeners() {
        frmSednicaSkupstine.addSaveBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }

            private void save() {
                try {
                    SednicaSkupstine sednicaSkupstine = new SednicaSkupstine();
                    sednicaSkupstine.setSednicaSkupstineId(Long.parseLong(frmSednicaSkupstine.getTxtSednicaId().getText().trim()));
                    sednicaSkupstine.setStambenaZajednica((StambenaZajednica) frmSednicaSkupstine.getCbStambenaZajednica().getSelectedItem());
                    SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
                    sednicaSkupstine.setDatumOdrzavanja(format.parse(frmSednicaSkupstine.getTxtDatumOdrzavanja().getText()));
                    sednicaSkupstine.setBrojPrisutnih(Integer.parseInt(frmSednicaSkupstine.getTxtBrojPrisutnih().getText().trim()));
                    sednicaSkupstine.setDnevniRed(frmSednicaSkupstine.getTxtDnevniRed().getText().trim());
                    List<VlasnikPosebnogDela> prisutni = ((VlasnikPosebnogDelaTableModel) frmSednicaSkupstine.getTblPrisutnih().getModel()).getVlasnici();
                    sednicaSkupstine.setVlasnici(prisutni);

                    Communication.getInstance().zapamtiSednicuSkupstine(sednicaSkupstine);
                    JOptionPane.showMessageDialog(frmSednicaSkupstine, "Sistem je zapamtio sednicu skupstine");
                    frmSednicaSkupstine.dispose();
                } catch (Exception ex) {
                    Logger.getLogger(SednicaSkupstineController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmSednicaSkupstine, ex.getMessage());
                    frmSednicaSkupstine.dispose();
                }
            }
        });

        frmSednicaSkupstine.addCancelBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }

            private void cancel() {
                frmSednicaSkupstine.dispose();
            }
        });
        frmSednicaSkupstine.addUcitajVlasnikeBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frmSednicaSkupstine.getCbVlasnici().removeAllItems();
                    VlasnikPosebnogDela vlasnik = new VlasnikPosebnogDela();
                    vlasnik.setStambenaZajednica((StambenaZajednica) frmSednicaSkupstine.getCbStambenaZajednica().getSelectedItem());
                    List<VlasnikPosebnogDela> vlasnici = Communication.getInstance().nadjiVlasnikePosebnihDelova(vlasnik);
                    frmSednicaSkupstine.getCbVlasnici().setModel(new DefaultComboBoxModel<>(vlasnici.toArray()));
                    frmSednicaSkupstine.getBtnSave().setEnabled(true);
                } catch (Exception ex) {
                    Logger.getLogger(SednicaSkupstineController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(frmSednicaSkupstine, ex.getMessage());
                    frmSednicaSkupstine.dispose();
                }
            }
        });
        frmSednicaSkupstine.addUbaciVlasnikaBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    VlasnikPosebnogDela vlasnik = (VlasnikPosebnogDela) frmSednicaSkupstine.getCbVlasnici().getSelectedItem();
                    VlasnikPosebnogDelaTableModel model = (VlasnikPosebnogDelaTableModel) frmSednicaSkupstine.getTblPrisutnih().getModel();
                    model.addVlasnikPosebnogDela(vlasnik);
                    frmSednicaSkupstine.getTxtBrojPrisutnih().setText("" + model.getRowCount());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frmSednicaSkupstine, "Nevalidni podaci! " + ex.getMessage());
                    frmSednicaSkupstine.dispose();
                }
            }
        });
        frmSednicaSkupstine.addIzbaciVlasnikaBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = frmSednicaSkupstine.getTblPrisutnih().getSelectedRow();
                VlasnikPosebnogDelaTableModel model = (VlasnikPosebnogDelaTableModel) frmSednicaSkupstine.getTblPrisutnih().getModel();
                if (rowIndex >= 0) {
                    model.removeVlasnikPosebnogDela(rowIndex);
                    frmSednicaSkupstine.getTxtBrojPrisutnih().setText("" + model.getRowCount());
                } else {
                    JOptionPane.showMessageDialog(frmSednicaSkupstine, "Vlasnik posebnog dela nije selektovan!");
                    frmSednicaSkupstine.dispose();
                }
            }
        });

    }

    public void openForm(FormMode formMode) throws Exception {
        frmSednicaSkupstine.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        prepareView(formMode);
        SednicaSkupstine sednicaSkupstine = new SednicaSkupstine();
        sednicaSkupstine.setSednicaSkupstineId(-1l);
        sednicaSkupstine.setStambenaZajednica((StambenaZajednica) frmSednicaSkupstine.getCbStambenaZajednica().getSelectedItem());
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        try {
            sednicaSkupstine.setDatumOdrzavanja(format.parse(frmSednicaSkupstine.getTxtDatumOdrzavanja().getText()));
        } catch (ParseException ex) {
            Logger.getLogger(SednicaSkupstineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        sednicaSkupstine.setBrojPrisutnih(Integer.parseInt(frmSednicaSkupstine.getTxtBrojPrisutnih().getText().trim()));
        sednicaSkupstine.setDnevniRed(frmSednicaSkupstine.getTxtDnevniRed().getText().trim());
        SednicaSkupstine novaSednicaSkupstine;
        try {
             novaSednicaSkupstine = Communication.getInstance().kreirajSednicuSkupstine(sednicaSkupstine);
        } catch (Exception ex) {
            Logger.getLogger(SednicaSkupstineController.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Sistem ne moze da kreira sednicu skupstine");
        }
        frmSednicaSkupstine.getTxtSednicaId().setText(novaSednicaSkupstine.getSednicaSkupstineId()+"");
        frmSednicaSkupstine.getTxtSednicaId().setEnabled(false);
         JOptionPane.showMessageDialog(frmSednicaSkupstine,"Sistem je kreirao sednicu skupstine");
        frmSednicaSkupstine.setVisible(true);
       
    }

    private void prepareView(FormMode formMode) {
        fillCbStambenaZajednica();
        fillDefaultValues();
        setupComponents(formMode);

        frmSednicaSkupstine.getTblPrisutnih().setModel(new VlasnikPosebnogDelaTableModel(new ArrayList<>()));
    }

    private void fillCbStambenaZajednica() {
        frmSednicaSkupstine.getCbStambenaZajednica().removeAllItems();
        List<StambenaZajednica> stambenaZajednice = new ArrayList<>();
        try {
            stambenaZajednice = Communication.getInstance().ucitajListuStambenihZajednica();
        } catch (Exception ex) {
            Logger.getLogger(SednicaSkupstineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        frmSednicaSkupstine.getCbStambenaZajednica().setModel(new DefaultComboBoxModel<>(stambenaZajednice.toArray()));
    }

    private void setupComponents(FormMode formMode) {
        switch (formMode) {
            case FORM_ADD:
                frmSednicaSkupstine.getBtnCancel().setEnabled(true);
                frmSednicaSkupstine.getBtnSave().setEnabled(false); //dok se ne dodaju vlasnici

                frmSednicaSkupstine.getTxtSednicaId().setEnabled(true);
                frmSednicaSkupstine.getCbStambenaZajednica().setEnabled(true);
                frmSednicaSkupstine.getTxtBrojPrisutnih().setEnabled(true);
                frmSednicaSkupstine.getTxtDatumOdrzavanja().setEnabled(true);
                frmSednicaSkupstine.getTxtDnevniRed().setEnabled(true);
                frmSednicaSkupstine.getCbVlasnici().setEnabled(true);
                frmSednicaSkupstine.getBtnUbaciVlasnika().setEnabled(true);
                frmSednicaSkupstine.getBtnIzbaciVlasnika().setEnabled(true);
                frmSednicaSkupstine.getBtnUcitajSveVlasnike().setEnabled(true);
                break;
            case FORM_VIEW:

            case FORM_EDIT:

        }
    }

    private SednicaSkupstine makeSednicaSkupstineFromForm() throws ParseException {
        SednicaSkupstine sednicaSkupstine = new SednicaSkupstine();
        sednicaSkupstine.setSednicaSkupstineId(Long.parseLong(frmSednicaSkupstine.getTxtSednicaId().getText().trim()));
        sednicaSkupstine.setStambenaZajednica((StambenaZajednica) frmSednicaSkupstine.getCbStambenaZajednica().getSelectedItem());
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        sednicaSkupstine.setDatumOdrzavanja(format.parse(frmSednicaSkupstine.getTxtDatumOdrzavanja().getText()));
        sednicaSkupstine.setBrojPrisutnih(Integer.parseInt(frmSednicaSkupstine.getTxtBrojPrisutnih().getText().trim()));
        sednicaSkupstine.setDnevniRed(frmSednicaSkupstine.getTxtDnevniRed().getText().trim());
        List<VlasnikPosebnogDela> prisutni = ((VlasnikPosebnogDelaTableModel) frmSednicaSkupstine.getTblPrisutnih().getModel()).getVlasnici();
        sednicaSkupstine.setVlasnici(prisutni);

        return sednicaSkupstine;
    }

    private void fillDefaultValues() {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        frmSednicaSkupstine.getTxtDatumOdrzavanja().setText(currentDate);
        frmSednicaSkupstine.getTxtBrojPrisutnih().setText("0");
    }

}
