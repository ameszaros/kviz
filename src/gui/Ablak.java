package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import model.Adatbazis;

/**
 *
 * @author Dr. Mészáros András - 2016.12.31.
 *
 */
public class Ablak extends JFrame implements ActionListener {

  /*menüelemek deklarálása*/
  private final JMenu mProgram = new JMenu("Program");
  private final JMenuItem miNevjegy = new JMenuItem("Névjegy");
  private final JMenuItem miKilepes = new JMenuItem("Kilépés");

  private final JMenu mKerdesek = new JMenu("Kérdések");
  private final JMenuItem miUjKerdes = new JMenuItem("Új kérdés felvitele...");
  private final JMenuItem miKerdesFrissites = new JMenuItem("Kérdés módosítása...");
  private final JMenuItem miKerdesTorles = new JMenuItem("Kérdés törlése...");

  private final JMenu mVizsga = new JMenu("Vizsga");
  private final JMenuItem miVizsga = new JMenuItem("Vizsga indítása...");

  public Ablak() {
    //<editor-fold defaultstate="collapsed" desc="Look & feel">
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(Ablak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(Ablak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(Ablak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Ablak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    initComponents();
  }

  private void initComponents() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("JAVA kikérdező 0.1");
    setSize(800, 600);
    setLocationRelativeTo(this);
    setResizable(false);
    JMenuBar mb = new JMenuBar();
    setJMenuBar(mb);

    miVizsga.addActionListener(this);

    /*Menük összerakása*/
    mProgram.add(miNevjegy);
    miNevjegy.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "<html><br><i>JAVA kikérdező program</i><br>"
                + "<br><b>Készítette:</b> Dr. Mészáros András</html>", "Névjegy", JOptionPane.INFORMATION_MESSAGE);
      }
    });
    mProgram.addSeparator();
    mProgram.add(miKilepes);
    miKilepes.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    mKerdesek.add(miUjKerdes);
    miUjKerdes.addActionListener(this);
    mKerdesek.add(miKerdesFrissites);
    miKerdesFrissites.addActionListener(this);
    mKerdesek.add(miKerdesTorles);
    miKerdesTorles.addActionListener(this);
    
    mVizsga.add(miVizsga);

    mb.add(mProgram);
    mb.add(mKerdesek);
    mb.add(mVizsga);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == miVizsga) {
      new VizsgaAblak(idBekeres("A helyes válasz színe: ZÖLD\n"
              + "A hibásan kiválasztott válasz színe: PIROS\n"
              + "A hibásan meg nem jelölt válasz színe: SÁRGA\n\n"
              + "Kérem a vizsgafeladatok számát "));
    }
        
    if (e.getSource() == miUjKerdes) {
        new KerdesMentes();
    }
    
    if (e.getSource() == miKerdesFrissites) {
      new KerdesMentes(idBekeres("Kérem a frissítendő kérdés számát "), 'f');
    }
    
    if (e.getSource() == miKerdesTorles) {
      new KerdesMentes(idBekeres("Kérem a törlendő kérdés számát "), 't');
    }
  }
  
  private int idBekeres(String szoveg) {
      int maximum;
      Adatbazis a = new Adatbazis();
      try {
        maximum = a.Meret();
      } 
      catch (SQLException x) {
        JOptionPane.showMessageDialog(this, x.getMessage());
        return 0;        
      }
      int bekertAdat = 0;
      boolean joValasz = false;
      do {
        String adat = JOptionPane.showInputDialog(this, szoveg+"(max:"+maximum+"):");
        try {
          bekertAdat = Integer.parseInt(adat);
          joValasz = true;
          if (bekertAdat > maximum) {
            JOptionPane.showMessageDialog(this, "A megadott érték túl nagy!", "HIBA", 
                  JOptionPane.ERROR_MESSAGE);
            joValasz = false;
          }
          if (bekertAdat <=0) {
            JOptionPane.showMessageDialog(this, "A megadott érték túl kicsi!", "HIBA", 
                  JOptionPane.ERROR_MESSAGE);
            joValasz = false;
          }
        }
        catch (NumberFormatException x) {
          JOptionPane.showMessageDialog(this, "A megadott értéket nem lehet "
                  + "számmá alakítani!", "HIBA!", JOptionPane.ERROR_MESSAGE);
          joValasz = false;
        }
      } while(!joValasz);
      return bekertAdat;
    }

}/*EOF*/
