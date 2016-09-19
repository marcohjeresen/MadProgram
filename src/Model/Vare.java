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
public class Vare extends VareGruppe{
    private int vareNummer;
    private String vareNavn;
    private int varePris;
    private int grupId;
    private String grupNavn;

    public Vare(int varenummer, String varenavn, int id, String navn, int varepris) {
        super(id, navn);
        this.vareNummer = varenummer;
        this.vareNavn = varenavn;
        this.varePris = varepris;
        this.grupId = super.getId();
        this.grupNavn = super.getNavn();
    }

    public int getVareNummer() {
        return vareNummer;
    }

    public int getGrupId() {
        return grupId;
    }

    public void setGrupId(int grupId) {
        this.grupId = grupId;
    }

    public String getGrupNavn() {
        return grupNavn;
    }

    public void setGrupNavn(String grupNavn) {
        this.grupNavn = grupNavn;
    }

    public void setVareNummer(int vareNummer) {
        this.vareNummer = vareNummer;
    }

    public String getVareNavn() {
        return vareNavn;
    }

    public void setVareNavn(String vareNavn) {
        this.vareNavn = vareNavn;
    }

    public int getVarePris() {
        return varePris;
    }

    public void setVarePris(int varePris) {
        this.varePris = varePris;
    }

    @Override
    public String toString() {
        return "vareNummer: " + vareNummer + " vareNavn: " + vareNavn + " varePris: " + varePris + " vareGruppe: " + getId() + " vareGruppeNavn: " + getNavn();
    }

    
    
    
}
