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
import Model.VareLine;
import java.util.ArrayList;
import util.Listeners;

/**
 *
 * @author Mark
 */
public class NyRetHandler {
    private static NyRetHandler nRH;
    private ArrayList<VareLine> TilføjList;
    private ArrayList<Vare> VareList;
    private Listeners l;
    private VareController vc;
    private Ret ret;

    private NyRetHandler() {
        this.vc = VareController.getInstance();
        this.TilføjList = new ArrayList<>();
        this.VareList = vc.getVareList();
        this.l = Listeners.getList();
        this.ret = null;
    }
    
    public static NyRetHandler getInstance(){
        if (nRH == null) {
            nRH = new NyRetHandler();
        }
        return nRH;
    }
    
    public void addRet(String retNavn, int antalDage, String RetType){
        int retTypeId = 0;
        for (RetType rt : vc.getRetTyper()) {
            if (rt.getType().equals(RetType)) {
                retTypeId = rt.getId();
            }
        }
        ret = new Ret(0, retNavn, 0, antalDage, retTypeId, RetType);
        vareLineTilRet();
        vc.GemRet(ret);
    }
    
    public void vareLineTilRet(){
        ret.setVareLine(TilføjList);
    }
    
    public void addProdukt(Vare vare) {
        boolean erder = false;
        for (int i = 0; i < TilføjList.size(); i++) {
            if (TilføjList.get(i).getVare().getVareNummer() == vare.getVareNummer()) {
                erder = true;
                int antal = TilføjList.get(i).getVareAntal();
                TilføjList.get(i).setVareAntal(antal + 1);
            }
        }
        if (!erder) {
            VareLine vl = new VareLine(vare, 0, 1, 0);
            TilføjList.add(vl);
        }
        l.notifyListeners("Update Ny Ret");
    }
    
    public void removeCompliteVareLine(VareLine vl, boolean edit){
        for (int i = 0; i < TilføjList.size(); i++) {
            if (TilføjList.get(i).getVare().getVareNavn().equals(vl.getVare().getVareNavn())) {
                TilføjList.remove(i);
            }
        }
        if (edit) {
            System.out.println(vl.getRetNummer() + vl.getVare().getVareNummer() + "");
//            vc.removeVareLine(vl.getRetNummer(),vl.getVare().getVareNummer());
        }
        l.notifyListeners("Update Ny Ret");
    }
    
    public void removeVarelineComplite(){
        TilføjList.removeAll(TilføjList);
    }
    
    public void removeFromVareLine1(VareLine vl){
        for (int i = 0; i < TilføjList.size(); i++) {
            if (TilføjList.get(i).getVare().getVareNavn().equals(vl.getVare().getVareNavn())) {
                TilføjList.get(i).setVareAntal(TilføjList.get(i).getVareAntal() - 1);
                if (TilføjList.get(i).getVareAntal() == 0) {
                    TilføjList.remove(i);
                }
            }
        }
        l.notifyListeners("Update Ny Ret");
    }
    
    public void add1ToVareLine(VareLine vl){
        for (int i = 0; i < TilføjList.size(); i++) {
            if (TilføjList.get(i).getVare().getVareNavn().equals(vl.getVare().getVareNavn())) {
                TilføjList.get(i).setVareAntal(TilføjList.get(i).getVareAntal() + 1);
            }
        }
        l.notifyListeners("Update Ny Ret");
    }
    
    public ArrayList<VareLine> getVareLine(){
        return TilføjList;
    }
    
    public ArrayList<Vare> getChoosenVare(int VareGruppeId){
        return vc.getChoosenVareGrup(VareGruppeId);
    }
    
    public void addProduktString(String vare){
        for (Vare v : VareList) {
            if (v.getVareNavn().equals(vare)) {
                addProdukt(v);
            }
        }
    }
    
    public int getPris(){
        int pris = 0;
        for (VareLine T1 : TilføjList) {
            pris = pris + T1.getTotalPris();
        }
        return pris;
    }
    
}
