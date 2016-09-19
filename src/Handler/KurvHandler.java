/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;

import Controller.VareController;
import Model.Ret;
import Model.Vare;
import Model.VareLine;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Listeners;

/**
 *
 * @author markh_000
 */
public class KurvHandler {

    private static KurvHandler kurvHandler;
    
    private VareHandler vareHandler;
    private ArrayList<Ret> retList;
    private ArrayList<VareLine> vareList;
    private Listeners lt;
    private int pris;
    private ArrayList<Ret> AllRetList;
    private int antalDageRetter;

    private KurvHandler() {
        vareHandler = VareHandler.getInstance();
        antalDageRetter = 0;
        lt = Listeners.getList();
        retList = new ArrayList<>();
        vareList = new ArrayList<>();
        AllRetList = new ArrayList<>();
        AllRetList = vareHandler.getAllRetter();
        pris = 0;
    }

    public static KurvHandler getInstance() {
        if (kurvHandler == null) {
            kurvHandler = new KurvHandler();
        }
        return kurvHandler;
    }
    
    public int getAntalAllRettersDage(){
        AllRetList = vareHandler.getAllRetter();
        int count = 0;
        for (Ret retList1 : AllRetList) {
            count = count + retList1.getAntalDage();
        }
        return count;
    }
    
    public void FindRandomRetter(int antalDage){
        AllRetList = vareHandler.getAllRetter();
        boolean erder = false;
        int fundetDage = 0;
        Random ran = new Random();
        Ret reten = null;
        while (fundetDage < antalDage){
            int nummer = ran.nextInt(AllRetList.size());
            reten = AllRetList.get(nummer);
            for (Ret retList1 : retList) {
                if (retList1.getRetNavn().equals(reten.getRetNavn())) {
                    erder = true;
                    AllRetList.remove(nummer);
                }
            }
            if (!erder) {
                addRet(reten);
                fundetDage = fundetDage + reten.getAntalDage();
                AllRetList.remove(nummer);
            }else{
                erder = false;
            }
            
        }
        lt.notifyListeners("Update");
    }
    
    public int getAntalDageRetter(){
        antalDageRetter = 0;
        for (Ret retList1 : retList) {
            antalDageRetter = antalDageRetter + retList1.getAntalDage();
        }
        return antalDageRetter;
    }
    


    public void addRet(Ret ret) {
        boolean erder = false;
        for (int i = 0; i < retList.size(); i++) {
            if (retList.get(i).getRetNavn().equals(ret.getRetNavn())) {
                erder = true;
            }
        }
        if (!erder) {
            retList.add(ret);
            for (VareLine vl : ret.getVareList()) {
                addRetVare(vl);
            }
        }
        lt.notifyListeners("Update");
    }

    public void addRetVare(VareLine vl) {
        boolean erder = false;
        for (int i = 0; i < vareList.size(); i++) {
            if (vareList.get(i).getVare().getVareNummer() == vl.getVare().getVareNummer()) {
                erder = true;
                int antal = vareList.get(i).getVareAntal();
                vareList.get(i).setVareAntal(antal + vl.getVareAntal());
            }
        }
        if (!erder) {
            vareList.add(vl);
        }
    }

    public void addProdukt(Vare vare) {
        boolean erder = false;
        for (int i = 0; i < vareList.size(); i++) {
            if (vareList.get(i).getVare().getVareNummer() == vare.getVareNummer()) {
                erder = true;
                int antal = vareList.get(i).getVareAntal();
                vareList.get(i).setVareAntal(antal + 1);
            }
        }
        if (!erder) {
            VareLine vl = new VareLine(vare, 0, 1, 0);
            vareList.add(vl);
        }
        lt.notifyListeners("Update");
    }

    public void removeRet(Ret ret) {
        for (int i = 0; i < retList.size(); i++) {
            if (ret.getRetNavn().equals(retList.get(i).getRetNavn())) {
                retList.remove(i);
            }
        }
        removeFromVareRet(ret);
        lt.notifyListeners("Update");
    }

    public void removeFromVareRet(Ret ret) {
        for (VareLine vareLine : ret.getVareList()) {
            for (int i = 0; i < vareList.size(); i++) {
                if (vareLine.getVare().getVareNavn().equals(vareList.get(i).getVare().getVareNavn())) {
                    int vareLineAntal = vareLine.getVareAntal();
                    int vareListAntal = vareList.get(i).getVareAntal();
                    if (vareLineAntal < vareListAntal) {
                        vareList.get(i).setVareAntal(vareListAntal - vareLineAntal);
                    } else {
                        vareList.remove(i);
                    }
                }
            }
        }
        lt.notifyListeners("Update");
    }

    public void removeFromVareLine(VareLine vl) {
        for (int i = 0; i < vareList.size(); i++) {
            if (vareList.get(i).getVare().getVareNavn().equals(vl.getVare().getVareNavn())) {
                int minusAntal = vl.getVareAntal();
                int vareListAntal = vareList.get(i).getVareAntal();
                if (minusAntal < vareListAntal) {
                    vareList.get(i).setVareAntal(vareListAntal - minusAntal);
                } else {
                    vareList.remove(i);
                }
            }
        }
        lt.notifyListeners("Update");
    }
    
    public void removeCompliteVareLine(VareLine vl){
        for (int i = 0; i < vareList.size(); i++) {
            if (vareList.get(i).getVare().getVareNavn().equals(vl.getVare().getVareNavn())) {
                vareList.remove(i);
            }
        }
        lt.notifyListeners("Update");
    }
    
    public void removeFromVareLine1(VareLine vl){
        for (int i = 0; i < vareList.size(); i++) {
            if (vareList.get(i).getVare().getVareNavn().equals(vl.getVare().getVareNavn())) {
                vareList.get(i).setVareAntal(vareList.get(i).getVareAntal() - 1);
                if (vareList.get(i).getVareAntal() == 0) {
                    vareList.remove(i);
                }
            }
        }
        lt.notifyListeners("Update");
    }
    
    public void add1ToVareLine(VareLine vl){
        for (int i = 0; i < vareList.size(); i++) {
            if (vareList.get(i).getVare().getVareNavn().equals(vl.getVare().getVareNavn())) {
                vareList.get(i).setVareAntal(vareList.get(i).getVareAntal() + 1);
            }
        }
        lt.notifyListeners("Update");
    }

    public int getAntalProdukter() {
        int count = 0;
        pris = 0;
        for (VareLine vareline : vareList) {
            count = count + vareline.getVareAntal();
            pris = pris + vareline.getTotalPris();
        }
        return count;
    }

    public int getTotalPris() {
        return pris;
    }

    public ArrayList<Ret> getRetList() {
        return retList;
    }

    public ArrayList<VareLine> getVareLine() {
        return vareList;
    }

    public void removeAll() {
        vareList.removeAll(vareList);
        retList.removeAll(retList);
        lt.notifyListeners("Update");
    }
}
