/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;

/**
 *
 * @author markh_000
 */
public class Ret extends RetType{
    private int retNummer;
    private String retNavn;
    private int totalPris;
    private int antalDage;
    private ArrayList<VareLine> vareListe;

    public Ret(int retNummer, String retNavn, int totalPris, int antalDage, int id, String type) {
        super(id, type);
        this.retNummer = retNummer;
        this.retNavn = retNavn;
        this.totalPris = totalPris;
        this.antalDage = antalDage;
        vareListe = new ArrayList<>();
    }
    
    public void addVare(VareLine vareLine){
            vareListe.add(vareLine);
    }
    
    public ArrayList<VareLine> getVareList(){
        return vareListe;
    }
    
    public void setVareLine(ArrayList<VareLine> varelineList){
        vareListe = varelineList;
    }

    public int getRetNummer() {
        return retNummer;
    }

    public void setRetNummer(int retNummer) {
        this.retNummer = retNummer;
    }

    public String getRetNavn() {
        return retNavn;
    }

    public void setRetNavn(String retNavn) {
        this.retNavn = retNavn;
    }

    public int getTotalPris() {
        totalPris = 0;
        for (VareLine vareLine : vareListe) {
            totalPris = totalPris + vareLine.getTotalPris();
        }
        return totalPris;
    }

    public void setTotalPris(int totalPris) {
        this.totalPris = totalPris;
    }

    public int getAntalDage() {
        return antalDage;
    }

    public void setAntalDage(int antalDage) {
        this.antalDage = antalDage;
    }
    
}
