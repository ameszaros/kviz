package feltolto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import model.Adatbazis;
import model.Kerdes;

/**
 *
 * @author Dr. Mészáros András - 2017.01.12.
 * 
 */
public class main {
  
  private static final String FAJLNEV = "./feladatok.txt";
  private static String[] adat = new String[7];
  
  public static void main(String[] args) {
    System.out.println("Kvíz program adatbázisának feltöltése");
    System.out.println("=====================================");
    try {
      FileInputStream is = new FileInputStream(FAJLNEV);
      BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      String line; int szamlalo = 0;
      Adatbazis a = new Adatbazis();
      while ((line = br.readLine()) != null) {
        szamlalo++;
        System.out.println(szamlalo+" "+line);
        adat[szamlalo] = line;
        if (szamlalo == 6) {
          Kerdes k = new Kerdes(adat[1].trim(), adat[2].trim(), adat[3].trim(),
                  adat[4].trim(), adat[5].trim(), adat[6].trim());
          a.Mentes(k);
          System.out.println(k);
          szamlalo=0;
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
