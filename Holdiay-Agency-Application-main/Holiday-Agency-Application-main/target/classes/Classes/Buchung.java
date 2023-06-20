package Classes;

import reisebuero.FullDB;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buchung {

    private static Integer buchungid;
    private Date date;
    private String startzeit;
    private String endezeit;
    private String kundename;
    private String kundenachname;
    private String zustand = "nicht";
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Buchung(String kundename, String kundenachname) throws SQLException {
        buchungid = FullDB.getBuchungId();
        this.kundename = kundename;
        this.kundenachname = kundenachname;

    }


    public Buchung(int buchungid, String kundename, String kundenachname,String date, String startzeit, String endezeit) throws SQLException {
        this.buchungid = FullDB.getBuchungId();
        this.kundename = kundename;
        this.kundenachname = kundenachname;
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Buchung.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.startzeit = startzeit;
        this.endezeit = startzeit;
    }

    public Buchung(String kundename, String kundenachname, int buchungid, String zustand, String date, String startzeit, String endezeit){
        this.kundename = kundename;
        this.kundenachname = kundenachname;
        this.buchungid = buchungid;
        this.zustand = zustand;
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(Buchung.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.startzeit = startzeit;
        this.endezeit = startzeit;
    }

    // Setter
    public void setBuchungId(int buchungid) {Buchung.buchungid = buchungid;
    }

    public void setDate(String date) throws ParseException { this.date = dateFormat.parse(date); }

    public void setStartzeit(String gegebenstartzeit) {
        startzeit = gegebenstartzeit;
    }

    public void setEndezeit(String gegebenendezeit) {
        endezeit = gegebenendezeit;
    }

    public void setKundename(String kundename) {
        this.kundename = kundename;
    }

    public void setZustand(String zustand) {
        this.zustand = zustand;
    }

    public void setKundenachname(String kundenachname) {
        this.kundenachname = kundenachname;
    }

    // Getter
    public static Integer getBuchungid() {
        return buchungid;
    }

    public String getDate() {
        return dateFormat.format(date);
    }

    public String getStartzeit() {
        return startzeit;
    }

    public String getEndezeit() {
        return endezeit;
    }

    public String getZustand() {
        return zustand;
    }

    public String getKundename() {
        return kundename;
    }

    public String getKundenachname() {
        return kundenachname;
    }

    public static boolean controlDate(int terminid) throws SQLException, ParseException {
        if(terminid == -1) return false;
        Buchung buchung = FullDB.getBuchung(buchungid);
        String buchungdate = buchung.getDate();
        Date buchungdateDate = dateFormat.parse(buchungdate);
        Date today = new Date();
        return buchungdateDate.after(today);
    }
}
