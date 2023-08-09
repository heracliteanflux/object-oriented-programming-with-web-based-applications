/*  Dave Friedman
 *  CMPSC 221 - Object-Oriented Programming with Web-Based Applications
 *  HW4 - Guess-the-Number Game
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.security.SecureRandom;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GuessNumberGame extends JFrame {
  private int random_number;
  private int previous_guess;
  private final JLabel label;
  private final JTextField text_field;
  private final JButton button;
  private final SecureRandom random;

  public GuessNumberGame () {
    super("Guess-the-Number Game");
    
    random = new SecureRandom();
    random_number = 1 + random.nextInt(1000);
    previous_guess = 1000;
    
    setLayout(new FlowLayout());
    
    label = new JLabel("Guess the number between 1 and 1000:");
    label.setToolTipText("");
    add(label);

    text_field = new JTextField(10);
    add(text_field);

    button = new JButton("Play Again");
    button.setVisible(false);
    add(button);

    text_field.addActionListener(
      new ActionListener () {
        @Override
        public void actionPerformed (ActionEvent e) {
          String guess = text_field.getText();
          int guessed_number = Integer.parseInt(guess);
          int difference = Math.abs(random_number - guessed_number);
          if (difference == 0) {
            label.setText("Correct!");
            text_field.setEditable(false);
            button.setVisible(true);
            getContentPane().setBackground(Color.GREEN);
          }
          else if (difference > previous_guess) {
            label.setText("Getting colder...");
            getContentPane().setBackground(Color.BLUE);
          }
          else if (difference < previous_guess) {
            label.setText("Getting warmer...");
            getContentPane().setBackground(Color.RED);
          }
          else if (difference == previous_guess) {
            label.setText("Keep trying...");
            getContentPane().setBackground(Color.WHITE);
          }
          previous_guess = Math.abs(random_number - guessed_number);
        }
      }
    );
    button.addActionListener(
      new ActionListener () {
        @Override
        public void actionPerformed (ActionEvent e) {
          random_number = 1 + random.nextInt(1000);
          previous_guess = 1000;
          label.setText("Guess the number between 1 and 1000:");
          text_field.setText("");
          text_field.setEditable(true);
          button.setVisible(false);
          getContentPane().setBackground(Color.WHITE);
        }
      }
    );
  }

  public static void main (String[] args) {
    GuessNumberGame gng = new GuessNumberGame();
    gng.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gng.setSize(300, 300);
    gng.setVisible(true);
  }
}
