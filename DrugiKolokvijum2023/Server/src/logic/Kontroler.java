/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import domain.Korisnik;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Kontroler {

    private static Kontroler instance;

    private int maxUlogovanih = 0;
    private int brojUlogovanih = 0;

    private ArrayList<Korisnik> korisnici;
    private ArrayList<Korisnik> ulogovani;

    private Kontroler() {
        korisnici = new ArrayList<>();
        ulogovani = new ArrayList<>();

        korisnici.add(new Korisnik(1, "paja"));
        korisnici.add(new Korisnik(2, "maja"));
        korisnici.add(new Korisnik(3, "raja"));
        korisnici.add(new Korisnik(4, "caja"));
        korisnici.add(new Korisnik(5, "gaja"));
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public int getMaxUlogovanih() {
        return maxUlogovanih;
    }

    public void setMaxUlogovanih(int maxUlogovanih) {
        this.maxUlogovanih = maxUlogovanih;
    }

    public Korisnik login(Korisnik korisnik) {
        for (Korisnik k : korisnici) {
            if (korisnik.getUsername().equals(k.getUsername())) {
                if (daLiPostoji(k)) {
                    k.setKorisnikID(-1);
                    return k;
                }
                if (brojUlogovanih == maxUlogovanih) {
                    korisnik.setKorisnikID(-2);
                    return korisnik;
                }

                ulogovani.add(k);
                brojUlogovanih++;
                return k;
            }

        }
        return null;
    }

    private boolean daLiPostoji(Korisnik k) {
        for (Korisnik korisnik : ulogovani) {
            if (k.getUsername().equals(korisnik.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public boolean logout(Korisnik korisnik) {
        for (Korisnik k : ulogovani) {
            if (korisnik.getUsername().equals(k.getUsername())) {
                brojUlogovanih--;
                for (Korisnik korisnik1 : ulogovani) {
                    System.out.println(korisnik1.getUsername());
                }
                ulogovani.remove(k);
                for (Korisnik korisnik1 : ulogovani) {
                    System.out.println(korisnik1.getUsername());
                }
                return true;
            }
        }
        return false;
    }

    public String[] izracunaj(String izraz) {
        String[] parts = izraz.split(";");
        int operand1 = Integer.parseInt(parts[0]);
        int operand2 = Integer.parseInt(parts[1]);
        String operacija = parts[2];

        double rezultat = izracunajIzraz(operand1, operand2, operacija);
        String rez = String.valueOf(rezultat);
        
        String[] izlazni = {parts[0], parts[1], parts[2], rez}; 
        return izlazni;
    }

    private double izracunajIzraz(int operand1, int operand2, String operacija) {
        switch (operacija) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return (double)operand1 / operand2;
            default:
                throw new AssertionError();
        }
    }

}
