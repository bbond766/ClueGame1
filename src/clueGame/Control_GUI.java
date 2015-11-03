package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Control_GUI extends JPanel{
	private JTextField currentPlayer, dieRoll, guess, response;
	
	public Control_GUI() {
		setLayout(new GridLayout(4,3));
		JPanel panel = createCurrentPlayerPannel();
		JPanel panel1 = createDieRollPannel();
		JPanel panel2 = createGuessPannel();
		JPanel panel3 = createResponsePannel();
		JPanel panel4 = createNextPlayerButton();
		JPanel panel5 = createAccusationButton();
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel);
		add(panel4);
		add(panel5);
	}
	
	private JPanel createCurrentPlayerPannel(){
		JPanel panel = new JPanel();
		JLabel CurrentPlayerLabel = new JLabel("Whose Turn?");
		currentPlayer = new JTextField(20);
		panel.add(CurrentPlayerLabel);
		panel.add(currentPlayer);
		return panel;
	}
	private JPanel createDieRollPannel(){
		JPanel panel = new JPanel();
		JLabel dieLabel = new JLabel("Roll");
		dieRoll = new JTextField(10);
		dieRoll.setEditable(false);
		panel.add(dieLabel);
		panel.add(dieRoll);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		
		return panel;
	}
	private JPanel createGuessPannel(){
		JPanel panel = new JPanel();
		JLabel guessLabel = new JLabel("Guess");
		guess = new JTextField(30);
		guess.setEditable(false);
		panel.add(guessLabel);
		panel.add(guess);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		return panel;
	}
	private JPanel createResponsePannel(){
		JPanel panel = new JPanel();
		JLabel responseLabel = new JLabel("Response");
		response = new JTextField(20);
		response.setEditable(false);
		panel.add(responseLabel);
		panel.add(response);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		return panel;
	}
	private JPanel createNextPlayerButton(){
		JButton nextPlayer = new JButton("Next Player");
		JPanel panel = new JPanel();
		panel.add(nextPlayer);
		return panel;
	}
	private JPanel createAccusationButton(){
		JButton accusation = new JButton("Make an Accusation");
		JPanel panel = new JPanel();
		panel.add(accusation);
		return panel;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Control_GUI GUI = new Control_GUI();
		frame.add(GUI, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 250);
		frame.setVisible(true);
	}

}
