package model;

import java.util.Objects;

/**
 *
 * @author Dr. Mészáros András - 2016.12.31.
 * A kérdéseket és a megoldást tartalmazó POJO
 */
public class Kerdes {

  private String kerdes, valasz1, valasz2, valasz3, valasz4, megoldas;
  private int id;

  public String getKerdes() {
    return kerdes;
  }

  public void setKerdes(String kerdes) {
    this.kerdes = kerdes;
  }

  public String getValasz1() {
    return valasz1;
  }

  public void setValasz1(String valasz1) {
    this.valasz1 = valasz1;
  }

  public String getValasz2() {
    return valasz2;
  }

  public void setValasz2(String valasz2) {
    this.valasz2 = valasz2;
  }

  public String getValasz3() {
    return valasz3;
  }

  public void setValasz3(String valasz3) {
    this.valasz3 = valasz3;
  }

  public String getValasz4() {
    return valasz4;
  }

  public void setValasz4(String valasz4) {
    this.valasz4 = valasz4;
  }

  public String getMegoldas() {
    return megoldas;
  }

  public void setMegoldas(String megoldas) {
    this.megoldas = megoldas;
  }
  
  public void setId (int id){
    this.id = id;
  }
  
  public int getId() {
    return id;
  }
  
  /**
   * Feladatot létrehozó osztály
   * @param kerdes - a megválaszolandó kérdés
   * @param valasz1 - az első válaszlehetőség (a válasz)
   * @param valasz2 - a második válaszlehetőség (b válasz)
   * @param valasz3 - a harmadik válasszlehetőség (c válasz)
   * @param valasz4 - a negyedik válaszlehetőség (d válasz)
   * @param megoldas - a helyes megoldás(oka)t jelőlő kérdések betüjele, vagy
   * betüjelei, pl: a, ab, acd.
   */
  public Kerdes(String kerdes, String valasz1, String valasz2, String valasz3,
          String valasz4, String megoldas) {
    this.kerdes = kerdes;
    this.valasz1 = valasz1;
    this.valasz2 = valasz2;
    this.valasz3 = valasz3;
    this.valasz4 = valasz4;
    this.megoldas = megoldas;
  }
  
  /**
   * Túlterhelt konstruktor a feladatot létrehozó osztályhoz, amely csak három
   * válaszlehetőséget tartalmaz.
   * @param kerdes - a megválaszolandó kérdés
   * @param valasz1 - az első válaszlehetőség (a válasz)
   * @param valasz2 - a második válaszlehetőség (b válasz)
   * @param valasz3 - a harmadik válasszlehetőség (c válasz)
   * @param megoldas - a helyes megoldás(oka)t jelőlő kérdések betüjele, vagy
   * betüjelei, pl: a, ab, abc.
   */
  public Kerdes(String kerdes, String valasz1, String valasz2, String valasz3,
          String megoldas) {
    this(kerdes, valasz1, valasz2, valasz3, null, megoldas);
  }
  
  /**
   * Túlterhelt konstruktor a feladatot létrehozó osztályhoz, amely csak két
   * válaszlehetőséget tartalmaz.
   * @param kerdes - a megválaszolandó kérdés
   * @param valasz1 - az első válaszlehetőség (a válasz)
   * @param valasz2 - a második válaszlehetőség (b válasz)
   * @param megoldas - a helyes megoldás(oka)t jelőlő kérdések betüjele, vagy
   * betüjelei, pl: a, ab.
   */
  public Kerdes(String kerdes, String valasz1, String valasz2, String megoldas) {
    this(kerdes, valasz1, valasz2, null, null, megoldas);
  }

  public Kerdes() {
    this(null, null, null, null, null, null);
  }
  
  @Override
  public String toString() {
    return "Kerdes{id = "+getId()+"\n"
            + "kerdes="+getKerdes()+"\n, "
            + "valasz1="+getValasz1()+"\n, "
            + "valasz2="+getValasz2()+"\n, "
            + "valasz3="+getValasz3()+"\n, "
            + "valasz4="+getValasz4()+"\n, "
            + "megoldas="+getMegoldas()+"}";
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Kerdes other = (Kerdes) obj;
    if (this.id != other.id) {
      return false;
    }
    if (!Objects.equals(this.kerdes, other.kerdes)) {
      return false;
    }
    if (!Objects.equals(this.valasz1, other.valasz1)) {
      return false;
    }
    if (!Objects.equals(this.valasz2, other.valasz2)) {
      return false;
    }
    if (!Objects.equals(this.valasz3, other.valasz3)) {
      return false;
    }
    if (!Objects.equals(this.valasz4, other.valasz4)) {
      return false;
    }
    if (!Objects.equals(this.megoldas, other.megoldas)) {
      return false;
    }
    return true;
  }
  
  
}
