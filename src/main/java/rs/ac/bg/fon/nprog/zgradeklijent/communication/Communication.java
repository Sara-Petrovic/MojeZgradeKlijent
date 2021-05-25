/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeklijent.communication;

import java.net.Socket;
import java.util.List;

import rs.ac.bg.fon.nprog.zgradezajednicki.communication.Operation;
import rs.ac.bg.fon.nprog.zgradezajednicki.communication.Receiver;
import rs.ac.bg.fon.nprog.zgradezajednicki.communication.Request;
import rs.ac.bg.fon.nprog.zgradezajednicki.communication.Response;
import rs.ac.bg.fon.nprog.zgradezajednicki.communication.Sender;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaSkupstine;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;
import rs.ac.bg.fon.nprog.zgradeklijent.view.form.FrmMain;

/**
 *
 * @author Sara
 */
public class Communication {

    Socket socket;
    Sender sender;
    Receiver receiver;
    private static Communication instance;
    FrmMain frmMain;

    public static Communication getInstance() throws Exception {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public FrmMain getFrmMain() {
        return frmMain;
    }

    public void setFrmMain(FrmMain frmMain) {
        this.frmMain = frmMain;
    }
    

    private Communication() throws Exception {
        socket = new Socket("127.0.0.1", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public Korisnik login(String username, String password) throws Exception {
        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnickoIme(username);
        korisnik.setLozinka(password);
        Request request = new Request(Operation.LOGIN, korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (Korisnik) response.getResult();
        } else {
            throw response.getException();
        }

    }

    public List<Mesto> ucitajListuMesta() throws Exception {
        Request request = new Request(Operation.UCITAJ_LISTU_MESTA, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Mesto>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void unesiStambenuZajednicu(StambenaZajednica stambenaZajednica) throws Exception {//add
        Request request = new Request(Operation.UNESI_STAMBENU_ZAJEDNICU, stambenaZajednica);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public List<StambenaZajednica> ucitajListuStambenihZajednica() throws Exception {//getAll
        Request request = new Request(Operation.UCITAJ_LISTU_STAMBENIH_ZAJEDNICA, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<StambenaZajednica>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void obrisiStambenuZajednicu(StambenaZajednica stambenaZajednica) throws Exception {
        Request request = new Request(Operation.OBRISI_STAMBENU_ZAJEDNICU, stambenaZajednica);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void zapamtiStambenuZajednicu(StambenaZajednica stambenaZajednica) throws Exception {//edit
        Request request = new Request(Operation.ZAPAMTI_STAMBENU_ZAJEDNICU, stambenaZajednica);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public List<StambenaZajednica> nadjiStambeneZajednice(StambenaZajednica sz) throws Exception {
        Request request = new Request(Operation.NADJI_STAMBENE_ZAJEDNICE, sz);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<StambenaZajednica>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public StambenaZajednica ucitajStambenuZajednicu(StambenaZajednica sz) throws Exception {
        Request request = new Request(Operation.UCITAJ_STAMBENU_ZAJEDNICU, sz);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (StambenaZajednica) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void unesiVlasnikaPosebnogDela(VlasnikPosebnogDela vlasnikPosebnogDela) throws Exception {
        Request request = new Request(Operation.UNESI_VLASNIKA_POSEBNOG_DELA, vlasnikPosebnogDela);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public void zapamtiVlasnikaPosebnogDela(VlasnikPosebnogDela vlasnik) throws Exception {
        Request request = new Request(Operation.ZAPAMTI_VLASNIKA_POSEBNOG_DELA, vlasnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }

    public List<VlasnikPosebnogDela> nadjiVlasnikePosebnihDelova(VlasnikPosebnogDela vlasnik) throws Exception {
        Request request = new Request(Operation.NADJI_VLASNIKE_POSEBNIH_DELOVA, vlasnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<VlasnikPosebnogDela>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<VlasnikPosebnogDela> ucitajListuVlasnikaPosebnihDelova() throws Exception {
        Request request = new Request(Operation.UCITAJ_LISTU_VLASNIKA_POSEBNIH_DELOVA, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<VlasnikPosebnogDela>) response.getResult();
        } else {
            throw response.getException();
        }
    }
     public VlasnikPosebnogDela ucitajVlasnikaPosebnogDela(VlasnikPosebnogDela sz) throws Exception {
        Request request = new Request(Operation.UCITAJ_VLASNIKA_POSEBNOG_DELA, sz);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (VlasnikPosebnogDela) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public SednicaSkupstine kreirajSednicuSkupstine(SednicaSkupstine sednicaSkupstine) throws Exception {
        Request request = new Request(Operation.KREIRAJ_SEDNICU_SKUPSTINE, sednicaSkupstine);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            SednicaSkupstine nova = (SednicaSkupstine) response.getResult();
            System.out.println(nova.getSednicaSkupstineId());
            sednicaSkupstine.setSednicaSkupstineId(nova.getSednicaSkupstineId());
            return sednicaSkupstine;
        } else {
            throw response.getException();
        }
    }

    public void zapamtiSednicuSkupstine(SednicaSkupstine sednicaSkupstine) throws Exception {
        Request request = new Request(Operation.ZAPAMTI_SEDNICU_SKUPSTINE, sednicaSkupstine);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {

        } else {
            throw response.getException();
        }
    }


    public List<SednicaSkupstine> nadjiSedniceSkupstina(SednicaSkupstine sednicaSkupstine) throws Exception {
        Request request = new Request(Operation.NADJI_SEDNICE_SKUPSTINA, sednicaSkupstine);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<SednicaSkupstine>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    
    

}
