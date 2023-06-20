package Classes;

import reisebuero.FullDB;

import java.sql.SQLException;

public class Zahlung {
    private static Integer zahlungid;
    private String zahlungsart;
    private Double zahlungsbetrag;
    private String zustand;
    private Double rabatt;
    private static Integer kundeid;
    private String name;
    private String nachname;
    public Zahlung(String zahlungsart, double zahlungsbetrag, Integer kundeid) throws SQLException {
        zahlungid = FullDB.getZahlungid();
        Zahlung.kundeid = kundeid;
        this.zahlungsart = zahlungsart;
        this.zahlungsbetrag = zahlungsbetrag;
        zustand  = "nicht";
    }
    public Zahlung(String zahlungsart, double zahlungsbetrag, Integer zahlungid, String zustand, Integer kundeid, String name, String nachname, Double rabatt) throws SQLException {
        zahlungid = FullDB.getZahlungid();
        this.zahlungsart = zahlungsart;
        this.zahlungsbetrag = zahlungsbetrag;
        this.zustand = zustand;
        Zahlung.kundeid = kundeid;
        this.name = name;
        this.nachname = nachname;
        this.rabatt = rabatt;
    }

    // Setter
    public static void setZahlungid(Integer zahlungid) {
        Zahlung.zahlungid = zahlungid;
    }

    public void setZahlungsart(String zahlungsart) {
        this.zahlungsart = zahlungsart;
    }

    public void setZahlungsbetrag(Double zahlungsbetrag) {
        this.zahlungsbetrag = zahlungsbetrag;
    }

    public void setZustand(String zustand) {
        this.zustand = zustand;
    }

    public void setKundeid(Integer kundeid) {
        Zahlung.kundeid = kundeid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setRabatt(Double rabatt) { this.rabatt = rabatt; }

    // Getter
    public static Integer getZahlungid() {
        return zahlungid;
    }

    public String getZahlungsart() {
        return zahlungsart;
    }

    public Double getZahlungsbetrag() {
        return zahlungsbetrag;
    }

    public String getZustand() {
        return zustand;
    }

    public static Integer getKundeid() {
        return kundeid;
    }

    public String getName() {
        return name;
    }

    public String getNachname() {
        return nachname;
    }

    public Double getRabatt() { return rabatt; }
}
