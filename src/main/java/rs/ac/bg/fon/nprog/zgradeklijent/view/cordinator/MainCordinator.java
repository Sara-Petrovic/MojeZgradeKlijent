/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.view.cordinator;

import java.util.HashMap;
import java.util.Map;

import rs.ac.bg.fon.nprog.zgradeklijent.view.constant.Constants;
import rs.ac.bg.fon.nprog.zgradeklijent.view.controller.*;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.*;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.util.FormMode;

/**
 *
 * @author Sara
 */
public class MainCordinator {

    private static MainCordinator instance;

    private final MainController mainController;

    private final Map<String, Object> params;

    private MainCordinator() {
        mainController = new MainController(new FrmMain());
        params = new HashMap<>();
    }

    public static MainCordinator getInstance() {
        if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }

    public void openLoginForm() {
        LoginController loginContoller = new LoginController(new FrmLogin());
        loginContoller.openForm();
    }

    public void openMainForm() {
        mainController.openForm();
        
    }

    public void openAddNewStambenaZajednicaForm() {
        StambenaZajednicaController stambenaZajednicaController = new StambenaZajednicaController(new FrmStambenaZajednica(mainController.getFrmMain(), true));
        stambenaZajednicaController.openForm(FormMode.FORM_ADD);
    }

    public void openPretragaStambenaZajednicaForm() {
        StambenaZajednicaPretragaController stambenaZajednicaPretragaController = new StambenaZajednicaPretragaController(new FrmPretragaStambenaZajednica(mainController.getFrmMain(), true));
        stambenaZajednicaPretragaController.openForm();
    }

    public void openViewAllStambenaZajednicaForm() {
        FrmViewStambenaZajednica form = new FrmViewStambenaZajednica(mainController.getFrmMain(), true);

        StambenaZajednicaViewAllController stambenaZajednicaViewAllController = new StambenaZajednicaViewAllController(form);
        stambenaZajednicaViewAllController.openForm();
    }

    public void openStambenaZajednicaDetailsStambenaZajednicaForm() {
        FrmStambenaZajednica stambenaZajednicaDetails = new FrmStambenaZajednica(mainController.getFrmMain(), true);
        StambenaZajednicaController stambenaZajednicaController = new StambenaZajednicaController(stambenaZajednicaDetails);
        stambenaZajednicaController.openForm(FormMode.FORM_VIEW);
        params.put(Constants.PARAM_STAMBENA_ZAJEDNICA, stambenaZajednicaDetails);
    }

    public void openAddNewVlasnikPosebnogDelaForm() {
        VlasnikPosebnogDelaController vlasnikPosebnogDelaController = new VlasnikPosebnogDelaController(new FrmVlasnikPosebnogDela(mainController.getFrmMain(), true));
        vlasnikPosebnogDelaController.openForm(FormMode.FORM_ADD);
    }

    public void openPretragaVlasnikaPosebnogDelaForm() {
        VlasnikPosebnogDelaPretragaController vlasnikPosebnogDelaPretragaController = new VlasnikPosebnogDelaPretragaController(new FrmPretragaVlasnikPosebnogDela(mainController.getFrmMain(), true));
        vlasnikPosebnogDelaPretragaController.openForm();
    }

    public void openVlasnikPosebnogDelaDetailsVlasnikForm() {
        FrmVlasnikPosebnogDela frmVlasnikPosebnogDela = new FrmVlasnikPosebnogDela(mainController.getFrmMain(), true);
        VlasnikPosebnogDelaController vlasnikPosebnogDelaController = new VlasnikPosebnogDelaController(frmVlasnikPosebnogDela);
        vlasnikPosebnogDelaController.openForm(FormMode.FORM_VIEW);
        // params.put(Constants.PARAM_VLASNIK, frmVlasnikPosebnogDela);
    }

    public void openAddNewSednicaSkupstineForm() throws Exception {
        SednicaSkupstineController sednicaSkupstineController = new SednicaSkupstineController(new FrmSednicaSkupstine(mainController.getFrmMain(), true));
        sednicaSkupstineController.openForm(FormMode.FORM_ADD);
    }

    public void openPretragaSednicaSkupstineForm() {
        SednicaSkupstinePretragaController sednicaSkupstinePretragaController = new SednicaSkupstinePretragaController(new FrmPretragaSednicaSkupstine(mainController.getFrmMain(), true));
        sednicaSkupstinePretragaController.openForm();
    }

    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }

    public MainController getMainController() {
        return mainController;
    }

}
