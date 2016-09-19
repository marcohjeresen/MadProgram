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
public class VareGruppe {
    private int id;
    private String navn;

    public VareGruppe(int id, String navn) {
        this.id = id;
        this.navn = navn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return "VareGruppe{" + "id=" + id + ", navn=" + navn + '}';
    }
    
}
