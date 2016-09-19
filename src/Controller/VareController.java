/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Ret;
import Model.RetType;
import Model.Vare;
import Model.VareGruppe;
import Model.VareLine;
import database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Listeners;

/**
 *
 * @author markh_000
 */
public class VareController {

    private static VareController vc;
    private Database db;
    private Vare vare;
    private ArrayList<Vare> vareList;
    private ArrayList<Ret> retList;
    private ArrayList<VareGruppe> vgList;
    private ArrayList<RetType> retTypeList;
    private Listeners l;

    private VareController() throws ClassNotFoundException, SQLException {
        db = new Database();
        vareList = new ArrayList<>();
        retList = new ArrayList<>();
        retTypeList = new ArrayList<>();
        l = Listeners.getList();
        dostuff();
    }

    public void dostuff() {
        vareList.removeAll(vareList);
        retList.removeAll(retList);
        try {
            ResultSet rs = db.query("select vare.Nummer, vare.Navn, vare.Pris, varegruppe.Id, varegruppe.Navn "
                    + "from vare, varegruppe "
                    + "where vare.Gruppe_Id = varegruppe.Id;");

            while (rs.next()) {
                vare = new Vare(rs.getInt("vare.Nummer"), rs.getString("vare.Navn"), rs.getInt("vareGruppe.Id"), rs.getString("varegruppe.Navn"), rs.getInt("vare.Pris"));
                vareList.add(vare);
            }
        } catch (SQLException ex) {
            System.out.println("Fejl " + ex.getLocalizedMessage());
        }

        try {
            ResultSet rl = db.query("select * from ret, rettype where RetType_Id = rettype.Id;");

            while (rl.next()) {
                Ret rt = new Ret(rl.getInt("Nummer"), rl.getString("ret.Navn"), rl.getInt("TotalPris"), rl.getInt("AntalDage"), rl.getInt("RetType_Id"), rl.getString("rettype.Navn"));
                retList.add(rt);
            }

        } catch (SQLException ex) {
            System.out.println("Fejl " + ex.getLocalizedMessage());
        }

        int retNummer;
        for (int i = 0; i < retList.size(); i++) {
            retNummer = retList.get(i).getRetNummer();
            try {
                ResultSet rt = db.query("select * from vare, vareline where vareline.Ret_Nummer = " + retNummer + " "
                        + "and vare.Nummer = vareline.Vare_Nummer;");

                while (rt.next()) {
                    for (Vare vare1 : vareList) {
                        if (vare1.getVareNummer() == rt.getInt("Nummer")) {
                            VareLine vl = new VareLine(vare1, rt.getInt("Ret_Nummer"), rt.getInt("VareAntal"), rt.getInt("TotalPris"));
                            retList.get(i).addVare(vl);
                        }
                    }

                }
            } catch (SQLException ex) {
                System.out.println("Fejl " + ex.getLocalizedMessage());
            }
        }
    }

    public static VareController getInstance() {
        if (vc == null) {
            try {
                vc = new VareController();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(VareController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return vc;
    }

    public VareController(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Ret> getallRetter() {
        ArrayList<Ret> retList = new ArrayList<>();
        try {
            ResultSet rs = db.query("Call getRetter()");
            while (rs.next()) {
                Ret r = new Ret(rs.getInt("ret.Nummer"), rs.getString("ret.Navn"), rs.getInt("ret.TotalPris"), rs.getInt("ret.AntalDage"),
                        rs.getInt("rettype.id"), rs.getString("rettype.Navn"));

                ResultSet rs2 = db.query("Call getRetIndhold(" + r.getRetNummer() + ")");
                while (rs2.next()) {
                    VareLine vl = new VareLine(new Vare(rs2.getInt("Vare_Nummer"), rs2.getString("vare.Navn"), rs2.getInt("vare.Gruppe_Id"),
                            rs2.getString("varegruppe.Navn"), rs2.getInt("vare.Pris")),
                            rs2.getInt("Ret_Nummer"), rs2.getInt("VareAntal"), rs2.getInt("Pris"));
                    r.addVare(vl);
                }
                retList.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retList;
    }

    public ArrayList<VareGruppe> getVareGruppe() {
        vgList = new ArrayList<>();
        try {
            ResultSet rs = db.query("call getProduktGrup()");

            while (rs.next()) {
                VareGruppe vg = new VareGruppe(rs.getInt("id"), rs.getString("Navn"));
                vgList.add(vg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vgList;
    }

    public ArrayList<Vare> getChoosenVareGrup(int id) {
        ArrayList<Vare> vareList = new ArrayList<>();
        try {
            ResultSet rs = db.query("Call getChoosenVareGrup(" + id + ")");
            while (rs.next()) {
                Vare v = new Vare(rs.getInt("vare.Nummer"), rs.getString("vare.Navn"), rs.getInt("varegruppe.id"), rs.getString("varegruppe.navn"), rs.getInt("vare.Pris"));
                vareList.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vareList;
    }

    public ArrayList<RetType> getRetTyper() {
        ArrayList<RetType> retTypeList = new ArrayList<RetType>();
        try {
            ResultSet rs = db.query("Call getRetTyper()");
            while (rs.next()) {
                RetType rt = new RetType(rs.getInt("id"), rs.getString("Navn"));
                retTypeList.add(rt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retTypeList;
    }

    public ArrayList<Ret> getChoosenRetter(int id) {
        ArrayList<Ret> retList = new ArrayList<>();
        try {
            ResultSet rs = db.query("Call getChoosenRetter(" + id + ")");
            while (rs.next()) {
                Ret r = new Ret(rs.getInt("ret.Nummer"), rs.getString("ret.Navn"), rs.getInt("ret.TotalPris"), rs.getInt("ret.AntalDage"),
                        rs.getInt("rettype.id"), rs.getString("rettype.Navn"));

                ResultSet rs2 = db.query("Call getRetIndhold(" + r.getRetNummer() + ")");
                while (rs2.next()) {
                    VareLine vl = new VareLine(new Vare(rs2.getInt("Vare_Nummer"), rs2.getString("vare.Navn"), rs2.getInt("vare.Gruppe_Id"),
                            rs2.getString("varegruppe.Navn"), rs2.getInt("vare.Pris")),
                            rs2.getInt("Ret_Nummer"), rs2.getInt("VareAntal"), rs2.getInt("Pris"));
                    r.addVare(vl);
                }
                retList.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retList;
    }

    public ArrayList<Vare> getVareList() {
        ArrayList<Vare> vareListTotal = new ArrayList<>();

        try {
            ResultSet rs = db.query("Call getvare()");
            while (rs.next()) {
                Vare v = new Vare(rs.getInt("Nummer"), rs.getString("Navn"), 0, "", rs.getInt("Pris"));
                vareListTotal.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vareListTotal;
    }

    public void SafeVare(int id, String navn, int pris) {
        try {
            db.ændre("Update vare set vare.navn='" + navn + "', vare.pris=" + pris + " where vare.nummer=" + id + ";");
        } catch (SQLException ex) {
            Logger.getLogger(VareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dostuff();
        l.notifyListeners("Update Fucking All");
    }

    public ArrayList<Vare> søgVare(String vare) {
        ArrayList<Vare> søgVare = new ArrayList<>();
        try {
            ResultSet rs = db.query("Call søgVare('%" + vare + "%');");
            while (rs.next()) {
                Vare v = new Vare(rs.getInt("Nummer"), rs.getString("Navn"), 0, "", rs.getInt("Pris"));
                søgVare.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return søgVare;
    }

    public void indsætVare(String navn, int pris, String GruppeNavn) {
        int GruppeId = 0;
        for (VareGruppe vg : vgList) {
            if (vg.getNavn().equals(GruppeNavn)) {
                GruppeId = vg.getId();
            }
        }
        try {
            db.ændre("Call IndsetProdukt('" + navn + "' , " + pris + "," + GruppeId + ");");
        } catch (SQLException ex) {
            Logger.getLogger(VareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dostuff();
        l.notifyListeners("Update Fucking All");
    }

    public ArrayList<Ret> getRetList() {
        return retList;
    }

    public void getRetType(String retType) {
        try {
            db.ændre("Call IndsetRetType('" + retType + "')");
        } catch (SQLException ex) {

        }
    }

    public void GemRet(Ret ret) {

        try {
            db.ændre("Call IndsetRet('" + ret.getRetNavn() + "'," + ret.getId() + " ," + ret.getTotalPris() + "," + ret.getAntalDage() + ");");
            for (VareLine vl : ret.getVareList()) {
                db.ændre("Call IndsetVareLine(" + vl.getVare().getVareNummer() + "," + vl.getVareAntal() + "," + vl.getTotalPris() + ");");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VareController.class.getName()).log(Level.SEVERE, null, ex);
        }
        l.notifyListeners("Update Fucking All");
    }

    public void removeVareLine(int retNummer, int vareNummer) {
        try {
            db.ændre("call RemoveVareLine(" + vareNummer + "," + retNummer + ");");

        } catch (SQLException ex) {
            Logger.getLogger(VareController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
