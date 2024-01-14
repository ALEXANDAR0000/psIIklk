/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.Scanner;
import logic.Kontroler;
import thread.PokreniServer;

/**
 *
 * @author ASUS
 */
public class MainServer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Unesi maksimalni broj korisnika: ");
        int maxUlogovanih = scanner.nextInt();
        
        Kontroler.getInstance().setMaxUlogovanih(maxUlogovanih);
        
        System.out.println("\nMaskimalni broj korisnika: " + maxUlogovanih);
        scanner.close();
        
        PokreniServer ps = new PokreniServer();
        ps.start();
    }
}
