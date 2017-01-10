
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Dr. Mészáros András - 2017.01.05.
 * 
 */
public class KerdesFelvitelGUI {

  private static class GUI extends JFrame {
    
    private final JLabel lbKerdes = new JLabel("Kérem a kérdést: ");
    private final JTextField tfKerdes = new JTextField("kérdés");
    private final JLabel lbValasz1 = new JLabel("Kérem az a.) választ: ");
    private final JTextField tfValasz1 = new JTextField("a.) válasz");
    private final JLabel lbValasz2 = new JLabel("Kérem a b.) választ: ");
    private final JTextField tfValasz2 = new JTextField("b.) válasz");
    private final JLabel lbValasz3 = new JLabel("Kérem a c.) választ: ");
    private final JTextField tfValasz3 = new JTextField("c.) válasz");
    private final JLabel lbValasz4 = new JLabel("Kérem a d.) választ: ");
    private final JTextField tfValasz4 = new JTextField("d.) válasz");
    private final JLabel lbMegoldas = new JLabel("Kérem a megoldás(oka)t: ");
    private final JTextField tfMegoldas = new JTextField("megoldás");
    private final JButton btOK = new JButton("OK");
    private final JButton btMegse = new JButton("Mégse");
    
    public GUI() {
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setTitle("Kérdések felvitele");
      setSize(800, 600);
      setLocationRelativeTo(this);
      JPanel contentPanel = new JPanel();
      Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
      contentPanel.setBorder(padding);
      contentPanel.setLayout(new GridLayout(7, 2, 10, 10));
      contentPanel.add(lbKerdes); contentPanel.add(tfKerdes);
      contentPanel.add(lbValasz1);contentPanel.add(tfValasz1);
      contentPanel.add(lbValasz2);contentPanel.add(tfValasz2);
      contentPanel.add(lbValasz3);contentPanel.add(tfValasz3);
      contentPanel.add(lbValasz4);contentPanel.add(tfValasz4);
      contentPanel.add(lbMegoldas);contentPanel.add(tfMegoldas);
      contentPanel.add(btOK); contentPanel.add(btMegse);
      add(contentPanel);
      setVisible(true);
    }
    
  }
  
  public static void main(String[] args) {
    new GUI();
  }
}
