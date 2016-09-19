/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author markh_000
 */
public class VareLine {
    private Vare vare;
    private int retNummer;
    private int vareAntal;
    private int totalPris;

    public VareLine(Vare vare, int retNummer, int vareAntal, int totalPris) {
        this.vare = vare;
        this.retNummer = retNummer;
        this.vareAntal = vareAntal;
        this.totalPris = totalPris;
    }

    public Vare getVare() {
        return vare;
    }

    public void setVare(Vare vare) {
        this.vare = vare;
    }

    public int getRetNummer() {
        return retNummer;
    }

    public void setRetNummer(int retNummer) {
        this.retNummer = retNummer;
    }

    public int getVareAntal() {
        return vareAntal;
    }

    public void setVareAntal(int vareAntal) {
        this.vareAntal = vareAntal;
    }

    public int getTotalPris() {
        int pris = vare.getVarePris() * vareAntal;
        return pris;
    }

    public void setTotalPris(int totalPris) {
        this.totalPris = totalPris;
    }
    
    
    
}
