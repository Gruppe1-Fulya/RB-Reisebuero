package Classes;

public class Person {

    private Integer id;
    private String name;
    private String nachname;
    private Integer telefonnummer;

    public Person(Integer id, String name, String nachname) {
        this.id = id;
        this.name = name;
        this.nachname = nachname;
    }

    public Person(Integer id, String name, String nachname, Integer telefonnummer) {
        this.id = id;
        this.name = name;
        this.nachname = nachname;
        this.telefonnummer = telefonnummer;
    }

    // set
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelefonnummer(Integer telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    // get
    public Integer getId() {
        return id;
    }

    public Integer getTelefonnummer() {
        return telefonnummer;
    }

    public String getNachname() {
        return nachname;
    }

    public String getName() {
        return name;
    }
}