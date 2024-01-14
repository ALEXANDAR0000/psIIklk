/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class ServerskiOdgovor implements Serializable{
    private Object odgovor;
    private String message;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, String message) {
        this.odgovor = odgovor;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }
    
    
}
