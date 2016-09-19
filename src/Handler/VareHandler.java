/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Handler;

import Controller.VareController;
import Model.Ret;
import Model.RetType;
import Model.Vare;
import Model.VareGruppe;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Listeners;

/**
 *
 * @author markh_000
 */
public class VareHandler {
    private VareController varecontroller;
    private static VareHandler vareHandler;
    private Listeners l;
    private int VareGruppeId;
    private int rettypeId;

    private VareHandler() {
        varecontroller = VareController.getInstance();
        l = Listeners.getList();
        VareGruppeId = 0;
        rettypeId = 0;
    }
    
    public static VareHandler getInstance(){
        if (vareHandler == null) {
            vareHandler = new VareHandler();
        }
        return vareHandler;
    }
    
    public ArrayList<Vare> getVareList(){
        return varecontroller.getVareList();
    }
    
    public ArrayList<Ret> getRetList(){
        return varecontroller.getRetList();
    }
    
    public ArrayList<VareGruppe> getVareGruppe(){
        return varecontroller.getVareGruppe();
    }
    
    public HashSet<String> getVareGrupper(){
        HashSet<String> vareGruppeList = new HashSet<>();
        for (Vare vare : getVareList()) {
            vareGruppeList.add(vare.getGrupNavn());
        }
        return vareGruppeList;
    }
    
    public void setVareGruppeId(int id){
        VareGruppeId = id;
    }
    
    public ArrayList<Vare> getChoosenVare(){
        return varecontroller.getChoosenVareGrup(VareGruppeId);
    }
    
    public ArrayList<RetType> getRetType(){
        return varecontroller.getRetTyper();
    }
    
    public void setRetTypeId(int id){
        rettypeId = id;
    }
    
    public ArrayList<Ret> getChoosenRetList(){
        return varecontroller.getChoosenRetter(rettypeId);
    }
    
    public ArrayList<Ret> getAllRetter(){
        return varecontroller.getallRetter();
    }
    
    public void safeVare(int id, String navn, int pris){
        varecontroller.SafeVare(id, navn, pris);
        l.notifyListeners("UpdateAll");
    }
    
    public ArrayList<Vare> søgVare(String vareNavn){
        return varecontroller.søgVare(vareNavn);
    }
    
    public void indsætVare(String navn, int pris, String GruppeNavn){
        varecontroller.indsætVare(navn, pris, GruppeNavn);
    }
    
}
