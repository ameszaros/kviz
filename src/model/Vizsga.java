package model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dr. Mészáros András - 2017.01.01.
 * 
 */
public class Vizsga {

  /**
   * Az adott vizsga kérdéseinek száma
   */
  private final int vizsgaKerdesek;
  
   /**
   * Az adatbázisba rögzített feladatok száma
   */
  private int osszesFeladat;
  
  
  /**
   * A vizsgához szükséges kérdések számának legenerálása az adatbázisból
   */
  private ArrayList<Integer> kerdesValogatas() {
    /*A kérdések sorszámának generálása, figyelemmel arra,
    hogy ne legyen olyan kérdés, amely 2x szerepel a feladatsoron
    */
    ArrayList<Integer> kerdesLista = new ArrayList<>();
    int feladat = 0;
    while (kerdesLista.size()<vizsgaKerdesek) {
      feladat = (int)(Math.random()*osszesFeladat+1);
      if (!kerdesLista.contains(feladat)) {
        kerdesLista.add(feladat);
      }        
    }
    return kerdesLista;
  }
  
  /**
   * A vizsgafeladatok lekérése az adatbázisból
   * @return 
   */
  private ArrayList<Kerdes> vizsgaFeladatok(ArrayList<Integer> generaltSzamok)
      throws SQLException {
    ArrayList<Kerdes> feladatok = new ArrayList<>();
    Adatbazis a = new Adatbazis();
    for (int i=0; i<generaltSzamok.size(); i++) {
      Kerdes k = a.Feladat(generaltSzamok.get(i));
      if (k!=null) feladatok.add(k);
    }
    return feladatok;
  }
  
  public ArrayList<Kerdes>feladatSor() throws SQLException {
    return vizsgaFeladatok(kerdesValogatas());
  }
  
  public Vizsga(int db) throws SQLException {
    vizsgaKerdesek = db;
    Adatbazis a = new Adatbazis();
    try {
      osszesFeladat = a.Meret();
      if (vizsgaKerdesek > osszesFeladat) 
        throw new IllegalArgumentException("A kért válaszok száma túl nagy!");
      }
    catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    
  }
}
