import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PizzaServingsCalculator extends JFrame {
  private final JButton    button;
  private final JLabel     label1;
  private final JLabel     label2;
  private final JLabel     label3;
  private final JPanel     panel;
  private final JTextField textField;

  public PizzaServingsCalculator () {
    super("Pizza Servings Calculator");
    setLayout(new GridLayout(4, 1, 0, 0));

    label1 = new JLabel("Pizza Servings Calculator",              SwingConstants.CENTER);
    label1.setForeground(Color.RED);
    label1.setFont(new Font(label1.getFont().getName(), Font.PLAIN, 18));
    label2 = new JLabel("Enter the size of the pizza in inches:", SwingConstants.CENTER);
    label3 = new JLabel("",                                       SwingConstants.CENTER);

    textField = new JTextField("", 4);
    textField.setEditable(true);

    ButtonHandler handler = new ButtonHandler();
    button = new JButton("Calculate Servings");
    button.addActionListener(handler);

    panel = new JPanel();
    panel.add(label2);
    panel.add(textField);

    add(label1);
    add(panel);
    add(button);
    add(label3);
  }

  private class ButtonHandler implements ActionListener {
    @Override
    public void actionPerformed (ActionEvent e) {
      double size     = Double.parseDouble(textField.getText());
      double servings = Math.pow((size / 8), 2);
      label3.setText(String.format("A %.0f inch pizza will serve %.2f people.", size, servings));
    }
  }

  public static void main (String[] args) {
    PizzaServingsCalculator pizzaServingsCalculator = new PizzaServingsCalculator();
                            pizzaServingsCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            pizzaServingsCalculator.setSize(350, 300);
                            pizzaServingsCalculator.setVisible(true);
  }
}
