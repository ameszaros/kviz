package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.Border;
import model.Kerdes;
import model.Vizsga;

/**
 *
 * @author Dr. Mészáros András - 2017.01.05.
 * A vizsga lefutását vezénylő ablak
 */
public class VizsgaAblak extends JDialog implements ActionListener {
  
  private final JLabel lbKerdes = new JLabel("Kérem a kérdést: ");
  private final JCheckBox cbValasz1 = new JCheckBox("a.) válasz: ");
  private final JCheckBox cbValasz2 = new JCheckBox("b.) válasz: ");
  private final JCheckBox cbValasz3 = new JCheckBox("c.) válasz: ");
  private final JCheckBox cbValasz4 = new JCheckBox("d.) válasz: ");
  
  private final JButton btOK = new JButton("Válaszok mutatása");
  private final JLabel lbSzamlalo = new JLabel("", SwingConstants.CENTER);
  private final JButton btMegse = new JButton("Megszakít");

  private final Font f = new Font("sans-serif", Font.PLAIN, 16);
  
  private int vizsgaFeladatokSzama=0;
  private int joValaszokSzama=0;
  private static ArrayList<Kerdes> feladatok = null;
  
  private int aktualisKerdesSzama = 0;
  
  public static void fontCsere(Component component, Font f) {
    component.setFont(f);
    if (component instanceof Container) {
      for (Component child : ((Container) component).getComponents()) {
        fontCsere(child, f);
      }
    }
  }
 
  private void GUI() {
    setTitle("Vizsga");
    setSize(800, 460);
    setLocationRelativeTo(this);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setModal(true);
    JPanel contentPanel = new JPanel();
    Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    contentPanel.setBorder(padding);
    contentPanel.setLayout(new GridLayout(6, 1, 10, 10));
    contentPanel.add(lbKerdes);
    contentPanel.add(cbValasz1);
    contentPanel.add(cbValasz2);
    contentPanel.add(cbValasz3);
    contentPanel.add(cbValasz4);
    JPanel btPanel = new JPanel(new GridLayout(1,3));
    btMegse.addActionListener(this);
    btOK.addActionListener(this);
    btPanel.add(btOK); btPanel.add(lbSzamlalo);btPanel.add(btMegse);
    contentPanel.add(btPanel);
    add(contentPanel);
    fontCsere(this, f);
  }
  
  private void kerdesKiir(Kerdes k) {
    lbKerdes.setText("<html><b>"+k.getId()+". "+k.getKerdes()+"</b></html>");
    cbValasz1.setText("<html><i>a.)</i> "+k.getValasz1()+"</html>");
    cbValasz2.setText("<html><i>b.)</i> "+k.getValasz2()+"</html>");
    cbValasz3.setText("<html><i>c.)</i> "+k.getValasz3()+"</html>");
    cbValasz4.setText("<html><i>d.)</i> "+k.getValasz4()+"</html>");
    lbSzamlalo.setText((aktualisKerdesSzama+1)+"/"+vizsgaFeladatokSzama);
    cbValasz1.setSelected(false);
    cbValasz2.setSelected(false);
    cbValasz3.setSelected(false);
    cbValasz4.setSelected(false);
  }
  
  public VizsgaAblak(int feladatSzam) {
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
    GUI();
    try {
      Vizsga v = new Vizsga(feladatSzam);
      feladatok = v.feladatSor();
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage());
    }
    vizsgaFeladatokSzama = feladatok.size();
    //System.out.println(vizsgaFeladatokSzama);
    kerdesKiir(feladatok.get(aktualisKerdesSzama));
    setVisible(true);
  }

  private void megoldasMegmutatasa(JCheckBox cb, String megoldas, String jomegoldas) {
      if (cb.isSelected() && 
              megoldas.contains(jomegoldas)) {
          cb.setForeground(Color.GREEN);
      } else if (cb.isSelected() && 
              !megoldas.contains(jomegoldas)) {
          cb.setForeground(Color.RED);
      } else if (!cb.isSelected() && 
              megoldas.contains(jomegoldas)) {
          cb.setForeground(Color.YELLOW);
      }
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btOK) {
      /*A helyes válaszok megmutatása*/
      if ("válaszok mutatása".equals(btOK.getText().toLowerCase())) {
      megoldasMegmutatasa(cbValasz1, feladatok.get(aktualisKerdesSzama).
              getMegoldas().toLowerCase(), "a" );
      megoldasMegmutatasa(cbValasz2, feladatok.get(aktualisKerdesSzama).
              getMegoldas().toLowerCase(), "b" );
      megoldasMegmutatasa(cbValasz3, feladatok.get(aktualisKerdesSzama).
              getMegoldas().toLowerCase(), "c" );
      megoldasMegmutatasa(cbValasz4, feladatok.get(aktualisKerdesSzama).
              getMegoldas().toLowerCase(), "d" );
      btOK.setText("Következő kérdés");
      return;
      }
      cbValasz1.setForeground(Color.BLACK);
      cbValasz2.setForeground(Color.BLACK);
      cbValasz3.setForeground(Color.BLACK);
      cbValasz4.setForeground(Color.BLACK);
      btOK.setText("Válaszok mutatása");
      /*Eredmény értékelése, és a következő kérdés kiíratása*/
      StringBuilder sb = new StringBuilder();
      if (cbValasz1.isSelected()) { sb.append("a");}
      if (cbValasz2.isSelected()) { sb.append("b");}
      if (cbValasz3.isSelected()) { sb.append("c");}
      if (cbValasz4.isSelected()) { sb.append("d");}
      String felhasznaloValasz = sb.toString();
     // System.out.println("Kérdés megoldása: "+feladatok.get(aktualisKerdesSzama).getMegoldas().toLowerCase());
     // System.out.println("Felhasználó által adott megoldás: "+ felhasznaloValasz);
      if (feladatok.get(aktualisKerdesSzama).getMegoldas().toLowerCase().equals
        (felhasznaloValasz)) {
        /*A felhasználó hibátlan választ adott*/
        joValaszokSzama++;
      }
      if ((aktualisKerdesSzama+1) >= vizsgaFeladatokSzama) {
        /*A vizsga véget ért, megmutatjuk az eredményt*/
        btOK.setEnabled(false);
        btMegse.setText("Kilépés");
        int szazalek = (int)(((double)joValaszokSzama/(double)vizsgaFeladatokSzama)*100);
        String uzenet = "";
        uzenet += "<html>A feltett kérdések száma:<b> "+vizsgaFeladatokSzama+"</b><br>";
        uzenet += "A helyes válaszok száma:<b> "+joValaszokSzama+"</b><br>";
        uzenet += "A vizsga százalékos eredménye:<b> "+szazalek+"%</b><br>";
        uzenet += "A vizsga értékelése:<b> ";
        if (szazalek >=70) {
          uzenet += "MEGFELELT!";
        }
        else {
          uzenet += "NEM FELELT MEG!";
        }
        uzenet += "</b></html>";
        JOptionPane.showMessageDialog(this, uzenet);
      }
      else {
        aktualisKerdesSzama++;
        kerdesKiir(feladatok.get(aktualisKerdesSzama));
      }
    }
    if  (e.getSource() == btMegse) {
      if (btMegse.getText().equals("Kilépés")) {
        this.dispose();
        return;
      }
      int valasztas = JOptionPane.showConfirmDialog(null, "Biztos, hogy meg akarod szakítani a"
                + " tesztet?", "Megszakítás megerősítése", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (valasztas == JOptionPane.YES_OPTION) {
          this.dispose();
        }
    }
  }

}
