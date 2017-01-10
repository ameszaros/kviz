package test;

import model.Adatbazis;
import model.Kerdes;
import model.KvizAdat;

/**
 *
 * @author Dr. Mészáros András - 2016.12.31.
 * 
 */
public class TestAdatbazis implements KvizAdat {
  public static void main(String[] args) {
//    System.out.println("A kvízprogram adatbázisának teszetelése");
//    System.out.println("=======================================");
//    System.out.print("Az adatbázis létrehozásának megkísérlése: ");
//    try {
//      Adatbazis a = new Adatbazis();
//      System.out.println("OK!");
//    }
//    catch (Exception e) {
//      System.out.println("HIBA!");
//      System.out.println(e.getMessage());
//    }
    
/*
           
 



*/
    Kerdes k = new Kerdes("", "", "", "", "", "");
    k.setKerdes("Jelölje be az összes helyes párosítást!");
    k.setValasz1("a) Editor – Programszerkesztő");
    k.setValasz2("b) Natív kód – Gépi kód");
    k.setValasz3("c) Fortran – Magasszintű nyelv");
    k.setValasz4("d) Interpreter – Fordító ");
    k.setMegoldas("bc");
    //System.out.println(k);
    System.out.print("Kérdés lementése az adatbázisba: ");
    try {
      Adatbazis a = new Adatbazis();
      a.Mentes(k);
      System.out.println("OK");
      
    } catch(Exception e) {
      System.out.println("HIBA!");
      System.out.println(e.getMessage());
    } 
//    System.out.print("Feladat frissítése az adatbázisban: ");       
//    try {
//      Adatbazis a = new Adatbazis();
//      k.setValasz4(null);
//      k.setMegoldas("a");
//      a.Modositas(1, k);
//      System.out.println("OK");
//    } catch(Exception e) {
//      System.out.println("HIBA!");
//      System.out.println(e.getMessage());
//    }
//    System.out.print("Kérdés törlése az adatbázisból: ");
//    try {
//      Adatbazis a = new Adatbazis();
//      a.Torles(1);
//      System.out.println("OK");
//    }
//    catch (Exception e) {
//      System.out.println("HIBA!");
//      System.out.println(e.getMessage());
//    }
  }
}
