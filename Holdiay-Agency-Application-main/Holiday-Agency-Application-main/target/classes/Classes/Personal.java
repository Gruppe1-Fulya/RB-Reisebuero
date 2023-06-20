package Classes;

public class Personal extends Person {

    private Integer personalnummer;
    private Double gehalt;

    public Personal(Integer id, String name, String nachname) {
        super(id, name, nachname);
    }

    public Personal(int id, String name, String nachname, int telefonnummer, int personalnummer, double gehalt) {
        super(id, name,nachname,telefonnummer);
        this.gehalt = gehalt;
        this.personalnummer = personalnummer;
    }

    // set
    public void setPersonalnummer(int personalnummer) {
        this.personalnummer = personalnummer;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    // get
    public double getGehalt() {
        return gehalt;
    }

    public int getPersonalnummer() {
        return personalnummer;
    }

}