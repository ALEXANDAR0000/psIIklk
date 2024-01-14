/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import constant.Operacije;
import domain.Korisnik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author ASUS
 */
public class ObradaKlijentskihZahteva extends Thread{
    Socket socket;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        while(true){
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                    Korisnik korisnik = (Korisnik) kz.getParametar();
                    Korisnik k = Kontroler.getInstance().login(korisnik);
                    so.setOdgovor(k);
                    break;
                case Operacije.LOGOUT:
                    Korisnik kor = (Korisnik) kz.getParametar();
                    boolean uspesno = Kontroler.getInstance().logout(kor);
                    so.setOdgovor(uspesno);
                    break;
                case Operacije.IZRACUNAJ:
                    String izraz = (String) kz.getParametar();
                    String[] odgovor = Kontroler.getInstance().izracunaj(izraz);
                    so.setOdgovor(odgovor);
                    break;
            }
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
