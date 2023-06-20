
package Classes;

public class Kunde extends Person {

    private Integer kundeid;
    private Double rabatt;
    private Integer gekauftereisepakete;
    private Integer kontostand;

    public Kunde(Integer id, String name, String nachname) {
        super(id,name,nachname);
    }
    public Kunde(Integer id, String name, String nachname, Integer telefonnummer,  Integer kontostand, int kundeid, double rabatt,int gekauftereiepakete) {
        super(id, name, nachname, telefonnummer);
        this.rabatt = rabatt;
        this.kundeid = kundeid;
        this.gekauftereisepakete = gekauftereiepakete;
        this.kontostand = kontostand;

    }

    // Setter

    public void setKundeid(Integer kundeid) {
        this.kundeid = kundeid;
    }

    public void setRabatt(Double rabatt) { this.rabatt = rabatt;}

    public void setGekauftereisepakete(Integer gekauftereisepakete) { this.gekauftereisepakete = gekauftereisepakete;}

    public void setKontostand(Integer kontostand) { this.kontostand = kontostand;}

    // Getter

    public Integer getKontostand() {
        return kontostand;
    }

    public Integer getKundeid() { return kundeid; }

    public Double getRabatt() { return rabatt; }

    public Integer getGekauftereisepakete() { return gekauftereisepakete; }
}