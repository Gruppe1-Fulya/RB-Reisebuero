package Classes;

public class Reisepakete {


    private Integer reisepaketeid;
    private String reiseort;
    private String startdatum;
    private String endedatum;
    private Integer anzahl_der_tage;



    public Reisepakete(Integer reisepaketeid, String startdatum, String endedatum, Integer anzahl_der_tage,String reiseort) throws SQLException {
        this.reisepaketeid = reisepaketeid;
        this.startdatum = startdatum;
        this.endedatum = endedatum;
        this.anzahl_der_tage = anzahl_der_tage;
        this.reiseort = reiseort;

    }



    // Setter
    public void setReisepaketeid(Integer reisepaketeid) { this.reisepaketeid = reisepaketeid; }

    public void setReiseort(String reiseort) { this.reiseort = reiseort; }

    public void setStartdatum(String startdatum) { this.startdatum = startdatum; }

    public void setEndedatum(String endedatum) { this.endedatum = endedatum; }

    public void setAnzahlderTage(int anzahl_der_tage) { this.anzahl_der_tage = anzahl_der_tage; }


    // Getter
    public void getAnzahl_der_tage(Integer anzahl_der_tage) { this.anzahl_der_tage = anzahl_der_tage; }

    public void getReisepaketeid(Integer reisepaketeid) { this.reisepaketeid = reisepaketeid; }

    public void getReiseort(String reiseort) { this.reiseort = reiseort; }

    public void getStartdatum(String startdatum) { this.startdatum = startdatum; }

    public void getEndedatum(String endedatum) { this.endedatum = endedatum; }




}