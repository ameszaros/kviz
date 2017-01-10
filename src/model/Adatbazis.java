package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dr. Mészáros András - 2017.01.08.
 * Kvízprogram adatbázis-kezelését megvalósító osztály
 */
public class Adatbazis implements KvizAdat {
  
  private static Connection kapcsolat = null;
  
  /**
   * Az adatbázissal való kapcsolat felépítése.
   * @return Connection objektum az adatbázishoz
   * @throws SQLException amennyiben a kapcsolat felépítése során
   * hiba lépett fel.
   */
  private Connection kapcsolatFelvetel() throws SQLException {
    if (kapcsolat == null) {
      try {
        Class.forName("org.sqlite.JDBC");
        kapcsolat = DriverManager.getConnection(DRIVER+FAJLNEV);
      }
      catch (ClassNotFoundException ex) {
        System.out.println("Driver nem található!");
        System.exit(1);
      }
    }
    return kapcsolat;
  }
  
  /**
   * Az adatbázis létrehozásáért felelős eljárás
   * @throws SQLException amennyiben az adatbázis létrehozás során
   * hiba lépett fel.
   */
  private void tablaLetrehozas() throws SQLException {
    if (kapcsolat == null) {
        kapcsolat = kapcsolatFelvetel();
    }
    Statement stmt = kapcsolat.createStatement();
    stmt.execute(TABLALETREHOZAS);
  }
  
  /**
   * Kérdések lementése az adatbázisba
   * @param k - a lementendő kérdést tartalmazó objektum
   * @throws SQLException - ha a lementés során hiba lépett fel.
   */
  public void Mentes(Kerdes k) throws SQLException {
    if (kapcsolat == null) {
      kapcsolat = kapcsolatFelvetel();
    }
    PreparedStatement ps = kapcsolat.prepareStatement(KERDESBESZURAS);
    ps.setString(1, k.getKerdes());
    ps.setString(2, k.getValasz1());
    ps.setString(3, k.getValasz2());
    ps.setString(4, k.getValasz3());
    ps.setString(5, k.getValasz4());
    ps.setString(6, k.getMegoldas());
    ps.execute();
  }
  
  /**
   * Kérdés adatainak frissítése az adatbázisban.
   * @param k - a frissítendő kérdést tartalmazó objektum
   * @throws SQLException - ha a frissítés során hiba lépett fel.
   */
  public void Modositas(Kerdes k) throws SQLException {
    if (kapcsolat == null) {
      kapcsolat = kapcsolatFelvetel();
    }
    PreparedStatement ps = kapcsolat.prepareStatement(KERDESFRISSITES);
    ps.setString(1, k.getKerdes());
    ps.setString(2, k.getValasz1());
    ps.setString(3, k.getValasz2());
    ps.setString(4, k.getValasz3());
    ps.setString(5, k.getValasz4());
    ps.setString(6, k.getMegoldas());
    ps.setInt(7, k.getId());
    ps.executeUpdate();
  }

  /**
   * Lementett kérdés törlése az adatbázisból.
   * @param id - a törlendő kérdés azonosítója az adatbázisban
   * @throws SQLException - ha a törlés során hiba lépett fel.
   */
  public void Torles(int id) throws SQLException {
    if (kapcsolat == null) {
      kapcsolat = kapcsolatFelvetel();
    }
    PreparedStatement ps = kapcsolat.prepareStatement(KERDESTORLES);
    System.out.println(id);
    ps.setInt(1, id);
    ps.executeUpdate();
  }
  
  /**
   * Az adatbázisban lementett kérdések számának lekérdezése
   * @return Az adatbázisba lementett kérdések száma
   * @throws SQLException - ha a lekérdezés során hiba lépett fel.
   */
  public int Meret() throws SQLException {
    if (kapcsolat == null) {
      kapcsolat = kapcsolatFelvetel();
    }
    int sorokSzama=0;
    Statement stmt = kapcsolat.createStatement();
    ResultSet rs = stmt.executeQuery("select count(*) from kerdesek");
    if (rs.next()) {
      sorokSzama = rs.getInt(1);
    }
    return sorokSzama;
  }
  
  /**
   * Az adatbázisban lementett feladatok közül egynek a lekérése.
   * @param index - a lekérdezésre kerülő feladat sorszáma
   * @return Kerdes objektum, amely a lekérdezett feladatot tartalmazza
   * @throws SQLException  - ha a lekérdezés során hiba lépett fel.
   */
  public Kerdes Feladat(int index) throws SQLException {
    if (kapcsolat == null) {
      kapcsolat = kapcsolatFelvetel();
    }
    PreparedStatement ps = kapcsolat.prepareStatement(KERDESVALASZTAS);
    ps.setInt(1, index);
    ResultSet rs = ps.executeQuery();
    Kerdes k = null;
    if (rs != null) {
      k = new Kerdes();
      k.setId(rs.getInt("id"));
      k.setKerdes(rs.getString("kerdes"));
      k.setValasz1(rs.getString("valasz1"));
      k.setValasz2(rs.getString("valasz2"));
      k.setValasz3(rs.getString("valasz3"));
      k.setValasz4(rs.getString("valasz4"));
      k.setMegoldas(rs.getString("megoldas"));
    }
    return k;
  }
  
  public Adatbazis() {
    if (!new File(FAJLNEV).exists()) {
      try {
        tablaLetrehozas();
      }
      catch(SQLException ex) {
        System.out.println("Az adatbázis létrehozása során SQL hiba történt.");
        System.out.println("A hibaüzenet: "+ex.getMessage());
      }
    }
  }
  
} /*EOF*/
