package reisebuero;

import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class FullDB{
    public  static Connection con = null;
    static String query = null;
    static String query2 = null;
    static Connection connection = null;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet = null;
    private static boolean update;
    static Integer id;

    public static Connection connect() throws SQLException {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/reisebuero.db";
        con = DriverManager.getConnection(url);

        return con;
    }

    public static void setConnection() {
        try {
            connection = connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Personal SQL Operationen
    // ADD Personal
    protected static void getPersonalQuery(Personal personal) {
        id = personal.getId();
        System.out.println(id);
        if (!update) {
            query = "INSERT INTO person ('name', 'nachname', id , telefonnummer ) VALUES(?,?,?,?)";
            query2 = "INSERT INTO personal ( id, personalnummer, gehalt ) VALUES(?,?,?)";
        } else {
            query = "UPDATE 'person' SET"
                    + "'name' =?,"
                    + "'nachname' =?,"
                    + "'id' = ?,"
                    + "'telefonnummer' =? WHERE id ='" + id + "'";
            query2 = "UPDATE 'personal' SET"
                    + "'id' = ?,"
                    + "'personalnummer' =?,"
                    + "'gehalt' =? WHERE id = '" + id + "'";
        }

    }
    protected static void insertPerson(Person person) {
        getPersonalQuery((Personal) person);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(3, String.valueOf(person.getId()));
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getNachname());
            preparedStatement.setString(4, String.valueOf(person.getTelefonnummer()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    protected static void insertPersonal(Personal personal) {
        getPersonalQuery(personal);
        try {
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, String.valueOf(personal.getId()));
            preparedStatement.setString(2, String.valueOf(personal.getPersonalnummer()));
            preparedStatement.setString(3, String.valueOf(personal.getGehalt()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // Personal Table
    protected static ObservableList<Personal> getPersonalDB() {
        ObservableList<Personal> oblist = FXCollections.observableArrayList();
        try {
            oblist.clear();
            query = "SELECT * FROM person INNER JOIN personal WHERE person.id = personal.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                oblist.add(new Personal(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("nachname"), resultSet.getInt("telefonnummer"),
                        resultSet.getString("adresse"), resultSet.getInt("personalnummer"),
                        resultSet.getDouble("gehalt")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oblist;
    }
    protected static void deletePersonal(Personal personal) {
        id = personal.getId();
        try {
            query = "DELETE FROM 'personal' WHERE id = " + id;
            String query2 = "DELETE FROM 'person' WHERE id = " + id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Tier SQL Operationen
    // ADD Tier
    protected static void getKundeQuery(Kunde kunde) {
        id = kunde.getKundeid();
        if (!update) {
            query = "INSERT INTO person ('name', 'nachname', id , telefonnummer ) VALUES(?,?,?,?)";
            query2 = "INSERT INTO kunde (kundeid, kontostand) VALUES(?,?)";
        } else {
            query = "UPDATE 'person' SET"
                    + "'name' =?,"
                    + "'nachname' =?,"
                    + "'id' = ?,"
                    + "'telefonnummer' =? WHERE id ='" + id + "'";
            query2 = "UPDATE 'kunde' SET"
                    + "'kundeid' = ?,"
                    + "'gekauftereisepakete' = ?,"
                    + "'rabatt' = ?,"
                    + "'kontostand' = ? WHERE kundeid = '" + id + "'";
        }

    }
    protected static void insertKunde(Kunde kunde) {
        getKundeQuery(kunde);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, kunde.getName());
            preparedStatement.setString(2, kunde.getNachname());
            preparedStatement.setString(3, String.valueOf(kunde.getId()));
            preparedStatement.setString(4, String.valueOf(kunde.getTelefonnummer()));
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, String.valueOf(kunde.getId()));
            preparedStatement.setString(2, tier.getHbname());
            preparedStatement.setString(3, String.valueOf(tier.getHbid()));
            preparedStatement.setString(4, String.valueOf(tier.getKontostand()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // Kunde Table
    protected static ObservableList<Kunde> getKundeDB() {
        ObservableList<Kunde> oblist = FXCollections.observableArrayList();
        try {
            oblist.clear();
            query = "SELECT * FROM tier INNER JOIN person ON person.id = kunde.kundeid";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Tier tier = new Tier(resultSet.getInt("kundeid"), resultSet.getString("name"),
                        resultSet.getString("nachname"), resultSet.getInt("telefonnummer"),
                        resultSet.getString("hbname"), resultSet.getInt("hbid"),
                        resultSet.getDouble("kontostand"));
                oblist.add(kunde);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oblist;
    }
    protected static void deleteKunde(Kunde kunde) {
        id = kunde.getId();
        try {
            query = "DELETE FROM 'kunde' WHERE kundeid = " + id;
            query2 = "DELETE FROM 'person' WHERE id = " + id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Appointment SQL Operationen
    // ADD Appointment
    public static ResultSet getTierarztname() throws SQLException {
        query = "SELECT name FROM 'person' INNER JOIN 'personal' ON 'person'.id = 'personal'.id ";
        connection = FullDB.connect();
        preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
    protected static void insertBuchung(Buchung buchung) {
        getBuchungQuery(buchung);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(buchung.getDate()));
            preparedStatement.setString(2, String.valueOf(buchung.getStartzeit()));
            preparedStatement.setString(3, String.valueOf(buchung.getEndezeit()));
            preparedStatement.setString(4, String.valueOf(Buchung.getBuchungid()));
            preparedStatement.setString(5, buchung.getKundename());
            preparedStatement.setString(6, buchung.getKundenachname());
            preparedStatement.setString(7, "nicht");
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    protected static void getBuchungQuery(Buchung buchung) {
        id = Buchung.getBuchungid();

        if (!update) {
            query = "INSERT INTO buchung ('date', 'startzeit', 'endezeit', buchungid, 'kundename', 'kundenachname', 'zustand') VALUES(?,?,?,?,?,?,?)";
        } else {
            query = "UPDATE 'buchung' SET"
                    + "'date' =?,"
                    + "'startzeit' =?,"
                    + "'endezeit' = ?,"
                    + "'buchungid' =?,"
                    + "'kundename' =?,"
                    + "'kundenachname' =?,"
                    + "'zustand' = 'nicht' WHERE buchungid ='" + id + "'";
        }

    }

    // Appointment Table
    public static int getBuchungId() throws SQLException {
        query = "SELECT * FROM termin ORDER BY buchungid";
        int buchungid = 0;
        try {
            connection = FullDB.connect();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (resultSet.next()) {
            buchungid = resultSet.getInt("buchungid");
        }
        return buchungid+1;
    }
    public static Buchung getBuchung(int id) throws SQLException {
        Buchung buchung = null;
        query = "SELECT * FROM buchung WHERE buchungid =" + id;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(resultSet.next()) {
            buchung = new Buchung(resultSet.getInt("buchungid"), resultSet.getString("kundename"),
                    resultSet.getString("kundenachname"), resultSet.getString("date"),
                    resultSet.getString("startzeit"), resultSet.getString("endezeit"));
        }
        return buchung;
    }
    protected static void makeDone() {
        try {
            id = Buchung.getBuchungid();
            query = "UPDATE buchung SET zustand = 'erledigt' WHERE buchungid = " + id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected static ObservableList<Buchung> getBuchungDB() {
        ObservableList<Buchung> oblist = FXCollections.observableArrayList();
        try {
            oblist.clear();
            query = "SELECT * FROM buchung";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Buchung buchung = new Buchung(resultSet.getString("kundename"), resultSet.getString("kundenachname"),
                        resultSet.getInt("buchungid"), resultSet.getString("zustand"),
                        resultSet.getString("date"), resultSet.getString("startzeit"), resultSet.getString("endezeit"));
                oblist.add(buchung);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oblist;
    }
    protected static void removeBuchung(Buchung buchung) {
        try {
            id = buchung.getBuchungid();
            query = "DELETE FROM 'buchung' WHERE buchungid = " + id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet getKundeinfo() throws SQLException {
        query = "SELECT kundeid,name,nachname FROM 'kunde' INNER JOIN person p on kunde.kundeid = p.id";
        preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    // Zahlung SQL Operationen
    // ADD Zahlung
    protected static void getZahlungQuery(Zahlung zahlung) {
        id = Zahlung.getZahlungid();

        if (!update) {
            query = "INSERT INTO zahlung ('zahlungsart', 'zahlungsbetrag', 'zustand', 'zahlungid', 'kundeid') VALUES(?,?,?,?,?)";
        } else {
            query = "UPDATE 'zahlung' SET"
                    + "'zahlungsart' =?,"
                    + "'zahlungsbetrag' =?,"
                    + "'zustand' =?,"
                    + "'zahlungid' =?,"
                    + "'kundeid' =? WHERE zahlungid ='" + id + "'";
        }

    }
    protected static void insertZahlung(Zahlung zahlung){
        id = zahlung.getZahlungid();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, zahlung.getZahlungsart());
            preparedStatement.setString(2, String.valueOf(zahlung.getZahlungsbetrag()));
            preparedStatement.setString(3, "nicht");
            preparedStatement.setString(4, String.valueOf(Zahlung.getZahlungid()));
            preparedStatement.setString(5, String.valueOf(zahlung.getKundeid()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    protected static ResultSet getKunde() {
        try {
            query = "SELECT * FROM person INNER JOIN tier WHERE person.id = kunde.kundeid";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Zahlung Table
    protected static Double getTotalAmount() {
        try {
            query = "SELECT SUM(zahlungsbetrag) FROM zahlung WHERE zustand = 'nicht'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return resultSet.getDouble("SUM(zahlungsbetrag)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void deleteZahlung(int id) {
        try {
            query = "DELETE FROM 'zahlung' WHERE zahlungid = " + id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getZahlungid() throws SQLException {
        query = "SELECT * FROM zahlung ORDER BY zahlungid";
        int zahlungid = 0;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (resultSet.next()) {
            zahlungid = resultSet.getInt("zahlungid");
        }
        return zahlungid+1;
    }
    protected static void makePaid(Zahlung zahlung) {
        try {
            query = "UPDATE zahlung SET zustand = 'gezahlt', zahlungsbetrag =  ABS(zahlungsbetrag) WHERE zahlungid = " + Zahlung.getZahlungid();
            String query2 = "UPDATE tier SET kontostand = kontostand + " + zahlung.getZahlungsbetrag() + " WHERE kundeid = '" + zahlung.getKundeid() + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected static ObservableList<Zahlung> getZahlungDB() {
        ObservableList<Zahlung> oblist = FXCollections.observableArrayList();
        try {
            oblist.clear();
            query = "SELECT * FROM zahlung INNER JOIN kunde k ON zahlung.kundeid = k.kundeid INNER JOIN person p on k.kundeid = p.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Zahlung zahlung = new Zahlung(resultSet.getString("zahlungsart"), resultSet.getDouble("zahlungsbetrag"),
                        resultSet.getInt("zahlungid"), resultSet.getString("zustand"), resultSet.getInt("kundeid"),
                        resultSet.getString("name"), resultSet.getString("nachname"));
                oblist.add(zahlung);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oblist;
    }

    // Rezepte SQL Operationen
    // ADD Rezepte
    public static int getReisepaketeId() throws SQLException {
        query = "SELECT * FROM reisepakete";
        int reisepaketeid = 0;
        try {
            connection = FullDB.connect();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (resultSet.next()) {
            reisepaketeid = resultSet.getInt("reisepaketeid");
        }
        return reisepaketeid+1;
    }
    protected static void getReisepaketeQuery(Reisepakete reisepakete) {
        id = reisepakete.getReisepaketeid();

        if (!update) {
            query = "INSERT INTO reisepakete ('reisepaketeid', 'kundeid', 'kundename', 'nachname', 'reiseort','anzahl_der_tage', 'startdatum','endedatum') VALUES(?,?,?,?,?,?,?,?)";
        } else {
            query = "UPDATE 'reisepakete' SET"
                    + "'kundeid' =?,"
                    + "'kundename' =?,"
                    + "'nachname' =?,"
                    + "'reiseort' =?,"
                    + "'anzahl_der_tage' =?,"
                    + "'startdatum' =?,"
                    + "'endedatum' =?,"
                    + "'reisepaketeid' =? WHERE reisepaketeid ='" + id + "'";
        }

    }
    protected static void insertReisepakete(Reisepakete reisepakete){
        id = reisepakete.getReisepaketeid();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(reisepakete.getReisepaketeid()));
            preparedStatement.setString(2, String.valueOf(reisepakete.getKundeid()));
            preparedStatement.setString(3, reisepakete.getKundename());
            preparedStatement.setString(4, reisepakete.getNachname());
            preparedStatement.setString(5, reisepakete.getReiseort());
            preparedStatement.setString(6, String.valueOf(reisepakete.getAnzahl_der_tage()));
            preparedStatement.setString(7, reisepakete.getStartdatum());
            preparedStatement.setString(8, reisepakete.getEndedatum());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Rezepte Table
    protected static ObservableList<Reisepakete> getReisepaketeDB() {
        ObservableList<Reisepakete> oblist = FXCollections.observableArrayList();
        try {
            oblist.clear();
            query = "SELECT * FROM reisepakete";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Reisepakete reisepakete = new Reisepakete(resultSet.getInt("reisepaketeid"), resultSet.getInt("kundeid"),
                        resultSet.getString("kundename"), resultSet.getString("nachname"),
                        resultSet.getString("reiseort") , resultSet.getString("nachname"));
                oblist.add(reisepakete);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oblist;
    }
}
