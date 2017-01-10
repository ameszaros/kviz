package model;

public interface KvizAdat {
   
  String DRIVER = "jdbc:sqlite:";  //A program működéséhez szükséges SQL driver
  String FAJLNEV = "kerdesek.db";     //A kérdéseket tároló adatbázisfájl neve
  
  /**
   * Új kérdés lementése az adatbázisba
   * A kérdés lementéséhez meg kell adni a kérdést, maximum 4
   * lehetséges választ, valamint a jó válaszok kódját.
   */
  String KERDESBESZURAS = "INSERT INTO kerdesek ("
          + "kerdes, valasz1, valasz2, valasz3, valasz4, megoldas) "
          + "VALUES(?, ?, ?, ?, ?, ?)";
  
  /**
   * Egy kérdés lehívása az adatbázisból az ID alapján.
   */
  String KERDESVALASZTAS = "SELECT * FROM kerdesek WHERE id=?";
  
  /**
   * Egy kérdés adatainak frissítése az adatbázisban ID alapján
   * A frissítéshez meg kell adni a kérdést, valamint a válaszokat.
   * Amelyik válasz üresen marad, az törlésre kerül az adatbázisból.
   */
  String KERDESFRISSITES = "UPDATE kerdesek SET "
          + "kerdes=?, "
          + "valasz1=?, "
          + "valasz2=?, "
          + "valasz3=?, "
          + "valasz4=?, "
          + "megoldas=? "
          + "WHERE id=?";
  
  /**
   * Egy kérdés törlése az adatbázisból ID alapján
   */
  String KERDESTORLES = "DELETE FROM kerdesek WHERE id=?";
  
  /**
   * Az adatbázistábla létrehozását leíró SQL parancs
   */
  String TABLALETREHOZAS = "CREATE TABLE kerdesek "
          + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
          + "kerdes TEXT NOT NULL, "
          + "valasz1 TEXT NOT NULL, "
          + "valasz2 TEXT NOT NULL, "
          + "valasz3 TEXT, "
          + "valasz4 TEXT, "
          + "megoldas TEXT NOT NULL)";
}
