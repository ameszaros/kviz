package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import model.Adatbazis;
import model.Kerdes;

/**
 *
 * @author Dr. Mészáros András - 2017.01.04. Kérdések felvitelét és módosítását
 * (törlését) végző osztály
 */
public class KerdesMentes extends JDialog implements ActionListener {

  /*Feliratok definiálása*/
  private final JLabel lbKerdes = new JLabel("Kérem a kérdést: ");
  private final JLabel lbValasz1 = new JLabel("Kérem az a.) választ: ");
  private final JLabel lbValasz2 = new JLabel("Kérem a b.) választ: ");
  private final JLabel lbValasz3 = new JLabel("Kérem a c.) választ: ");
  private final JLabel lbValasz4 = new JLabel("Kérem a d.) választ: ");
  private final JLabel lbMegoldas = new JLabel("Kérem a helyes megoldások betüjelét (pl: ab): ");

  /*Szövegmezők definiálása*/
  private JTextField tfKerdes = new JTextField("");
  private JTextField tfValasz1 = new JTextField("");
  private JTextField tfValasz2 = new JTextField("");
  private JTextField tfValasz3 = new JTextField("");
  private JTextField tfValasz4 = new JTextField("");
  private JTextField tfMegoldas = new JTextField("");

  /*Kezelőgombok defininálása*/
  private JButton btOK = new JButton("Mentés");
  private JButton btMegse = new JButton("Mégse/Bezár");

  private final Font f = new Font("sans-serif", Font.PLAIN, 16);
  
  private int iD;

  public static void fontCsere(Component component, Font f) {
    component.setFont(f);
    if (component instanceof Container) {
      for (Component child : ((Container) component).getComponents()) {
        fontCsere(child, f);
      }
    }
  }

  /**
   * Grafikus felület inicializálása
   */
  private void init() {
    setSize(800, 460);
    setLocationRelativeTo(this);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setModal(true);
    setTitle("Új kérdés felvitele az adatbázisba");
    JPanel contentPanel = new JPanel();
    Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    contentPanel.setBorder(padding);
    contentPanel.setLayout(new GridLayout(7, 2, 10, 10));
    contentPanel.add(lbKerdes);
    contentPanel.add(tfKerdes);
    contentPanel.add(lbValasz1);
    contentPanel.add(tfValasz1);
    contentPanel.add(lbValasz2);
    contentPanel.add(tfValasz2);
    contentPanel.add(lbValasz3);
    contentPanel.add(tfValasz3);
    contentPanel.add(lbValasz4);
    contentPanel.add(tfValasz4);
    contentPanel.add(lbMegoldas);
    contentPanel.add(tfMegoldas);
    btOK.addActionListener(this);
    contentPanel.add(btOK);
    btMegse.addActionListener(this);
    contentPanel.add(btMegse);
    add(contentPanel);
    fontCsere(this, f);
    //<editor-fold defaultstate="collapsed" desc="Beolvasható szöveghossz beállítása">
    /*Beolvasott szöveg maximum hossza 4 karakter*/
    tfMegoldas.setDocument(new PlainDocument() {
      @Override
      public void insertString(int offs, String str, AttributeSet a)
              throws BadLocationException {
        if (getLength() + str.length() <= 4) {
          super.insertString(offs, str, a);
        }
      }
    });
    /*A kérdés és a válaszok egyenként 200 karakter hosszúak lehetnek.*/
    tfKerdes.setDocument(new PlainDocument() {
      @Override
      public void insertString(int offs, String str, AttributeSet a)
              throws BadLocationException {
        if (getLength() + str.length() <= 200) {
          super.insertString(offs, str, a);
        }
      }
    });

    tfValasz1.setDocument(new PlainDocument() {
      @Override
      public void insertString(int offs, String str, AttributeSet a)
              throws BadLocationException {
        if (getLength() + str.length() <= 200) {
          super.insertString(offs, str, a);
        }
      }
    });
    tfValasz2.setDocument(new PlainDocument() {
      @Override
      public void insertString(int offs, String str, AttributeSet a)
              throws BadLocationException {
        if (getLength() + str.length() <= 200) {
          super.insertString(offs, str, a);
        }
      }
    });
    tfValasz3.setDocument(new PlainDocument() {
      @Override
      public void insertString(int offs, String str, AttributeSet a)
              throws BadLocationException {
        if (getLength() + str.length() <= 200) {
          super.insertString(offs, str, a);
        }
      }
    });
    tfValasz4.setDocument(new PlainDocument() {
      @Override
      public void insertString(int offs, String str, AttributeSet a)
              throws BadLocationException {
        if (getLength() + str.length() <= 200) {
          super.insertString(offs, str, a);
        }
      }
    });
    //</editor-fold>
    
  }

  public KerdesMentes() {
    this(0, null);
    setVisible(true);
  }

  /**
   * Túlterhelt konstruktor, ami megadott azonosító esetén betölti az
   * adatbázisból az azonosítóhoz tartozó kérdést.
   *
   * @param id A frissítendő, törlendő kérdés azonosítója
   * @param uzemmod 't' - törlés mód, 'f' - frissítés mód
   */
  @SuppressWarnings("empty-statement")
  public KerdesMentes(Integer id, Character uzemmod) {
    //<editor-fold defaultstate="collapsed" desc="Look & feel beállítása">
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      ;
    } catch (InstantiationException ex) {
      ;
    } catch (IllegalAccessException ex) {
      ;
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      ;
    }
    //</editor-fold>
    init();
    if (id == 0) return;
    Adatbazis a = new Adatbazis();
    try {
      Kerdes k = a.Feladat(id);
      tfKerdes.setText(k.getKerdes());
      tfValasz1.setText(k.getValasz1());
      tfValasz2.setText(k.getValasz2());
      tfValasz3.setText(k.getValasz3());
      tfValasz4.setText(k.getValasz4());
      tfMegoldas.setText(k.getMegoldas());
      iD = id;
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage(), "HIBA", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if (uzemmod.equals('f')) {
      btOK.setText("Frissítés");
    }
    if (uzemmod.equals('t')) {
      btOK.setText("Törlés");
    }
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btOK) {
      Kerdes k = new Kerdes();
      k.setKerdes(tfKerdes.getText().trim());
      k.setValasz1(tfValasz1.getText().trim());
      k.setValasz2(tfValasz2.getText().trim());
      k.setValasz3(tfValasz3.getText().trim());
      k.setValasz4(tfValasz4.getText().trim());
      k.setMegoldas(tfMegoldas.getText().trim());
      if (btOK.getText().equals("Törlés")) {
        if (JOptionPane.showConfirmDialog(this, 
                "Biztos, hogy törlöd a kiválasztott kérdést?", "Megerősítés", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 
                JOptionPane.YES_OPTION) {
          Adatbazis a = new Adatbazis();
          try {
            a.Torles(iD);
          }
          catch (SQLException x) {
            JOptionPane.showMessageDialog(this, x.getMessage(), "HIBA!", 
                    JOptionPane.ERROR_MESSAGE);
          }
          //FIXME - az adatbázisban történt törlés esetén újra kell számozni
          //a magasabb sorszámú kérdéseket.
        }
       this.dispose();
       return;         
      }
      if (btOK.getText().equals("Frissítés")) {
        if (JOptionPane.showConfirmDialog(this, 
                "Biztos, hogy frissíted a kiválasztott kérdést?", "Megerősítés", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 
                JOptionPane.YES_OPTION) {
          Adatbazis a = new Adatbazis();
          k.setId(iD);
          try {
            a.Modositas(k);
          }
          catch (SQLException x) {
            JOptionPane.showMessageDialog(this, x.getMessage(), "HIBA!", 
                    JOptionPane.ERROR_MESSAGE);
          }     
        }
       this.dispose();
       return;         
      }
      Adatbazis a = new Adatbazis();
      try {
        a.Mentes(k);
      }
      catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
      }
      finally {
        tfKerdes.setText("");
        tfValasz1.setText("");
        tfValasz2.setText("");
        tfValasz3.setText("");
        tfValasz4.setText("");
        tfMegoldas.setText("");
      }
    }
    
    if (e.getSource() == btMegse) {
      this.dispose();
    }
  }
}
