/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.nprog.zgradeklijent.communication.Communication;
import rs.ac.bg.fon.nprog.zgradeklijent.view.constant.Constants;
import rs.ac.bg.fon.nprog.zgradeklijent.view.cordinator.MainCordinator;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.FrmLogin;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;


/**
 *
 * @author Sara
 */
public class LoginController {

    private final FrmLogin frmLogin;

    public LoginController(FrmLogin frmLogin) {
        this.frmLogin = frmLogin;
        addActionListener();
    }

    public void openForm() {
        frmLogin.setVisible(true);
        

    }

    private void addActionListener() {
        frmLogin.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }

            private void loginUser() {
                resetForm();
                try {
                    String username = frmLogin.getTxtUsername().getText().trim();
                    String password = String.copyValueOf(frmLogin.getTxtPassword().getPassword());

                    validateForm(username, password);

                    Korisnik user = Communication.getInstance().login(username, password); //ovo je kontroler poslovne logike
                    MainCordinator.getInstance().addParam(Constants.PARAM_CURRENT_USER, user); //dodala, da znamo ko je ulogovan
                    JOptionPane.showMessageDialog(
                            frmLogin,
                            "Welcome " + user.getIme() + ", " + user.getPrezime(),
                            "Login", JOptionPane.INFORMATION_MESSAGE
                    );
                    frmLogin.dispose();

                    MainCordinator.getInstance().openMainForm();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frmLogin, e.getMessage(), "Login error", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void resetForm() {
                frmLogin.getLblUsernameError().setText("");
                frmLogin.getLblPasswordError().setText("");
            }

            private void validateForm(String username, String password) throws Exception {
                String errorMessage = "";
                if (username.isEmpty()) {
                    frmLogin.getLblUsernameError().setText("Username can not be empty!");
                    errorMessage += "Username can not be empty!\n";
                }
                if (password.isEmpty()) {
                    frmLogin.getLblPasswordError().setText("Password can not be empty!");
                    errorMessage += "Password can not be empty!\n";
                }
                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
            }
        });
    }

}
