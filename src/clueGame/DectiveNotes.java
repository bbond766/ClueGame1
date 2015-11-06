package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DectiveNotes extends JPanel{
	JCheckBox MrsScarlet;
	JCheckBox ColMustard;
	JCheckBox MrGreen;
	JCheckBox MrsWhite;
	JCheckBox MrsPeacock;
	JCheckBox MrPlum;
	JCheckBox Kitchen;
	JCheckBox Lounge;
	JCheckBox Conservatory;
	JCheckBox Study;
	JCheckBox BillardRoom;
	JCheckBox DiningRoom;
	JCheckBox Ballroom;
	JCheckBox Hall;
	JCheckBox Library;
	JCheckBox CandleStick;
	JCheckBox LeadPipe;
	JCheckBox Rope;
	JCheckBox Knife;
	JCheckBox Revolver;
	JCheckBox Wrench;
	JComboBox<String> personGuess, weaponGuess, roomGuess;
	
	public DectiveNotes(){
		setLayout(new GridLayout(3,2));
		JPanel panel = createPeople();
		JMenuBar menuBar = new JMenuBar();

		this.personGuess = personGuess();
		this.weaponGuess = weaponGuess();
		this.roomGuess = roomGuess();
		JPanel panel1 = createRooms();
		JPanel panel4 = createWeapons();
		add(panel);
		add(personGuess);
		add(panel1);
		add(roomGuess);
		add(panel4);
		add(weaponGuess);
		setVisible(true);
	}
	private JPanel createPeople(){
		JPanel panel = new JPanel();
		MrsScarlet = new JCheckBox("Mrs. Scarlet");
		ColMustard = new JCheckBox("Colonel Mustard");
		MrGreen = new JCheckBox("Mrs. Green");
		MrsWhite = new JCheckBox("Mrs. White");
		MrsPeacock = new JCheckBox("Mrs. Peacock");
		MrPlum = new JCheckBox("Mr. Plum");
		
		panel.add(MrsScarlet);
		panel.add(ColMustard);
		panel.add(MrGreen);
		panel.add(MrsWhite);
		panel.add(MrsPeacock);
		panel.add(MrPlum);
		
		panel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		return panel;
		
	}

	public JComboBox<String> personGuess() {
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("Ms. Scarlet");
		combo.addItem("Ms. White");
		combo.addItem("Ms. Peacock");
		combo.addItem("Mr. Greeen");
		combo.addItem("Mr. Plum");
		combo.addItem("Col. Mustard");
		combo.setBorder(new TitledBorder(new EtchedBorder(), "Person Guess"));

		return combo;

	}
	private class ComboListener implements ActionListener {
		  public void actionPerformed(ActionEvent e)
		  {
//		    if (e.getSource() == personGuess)
//		      dp.setToCity(toCity.getSelectedItem().toString());
//		    else
//		      dp.setFromCity(fromCity.getSelectedItem().toString());
		  }
		}
	private JPanel createWeapons(){
		JPanel panel = new JPanel();
		CandleStick = new JCheckBox("Candle Stick");
		LeadPipe = new JCheckBox("Lead Pipe");
		Rope = new JCheckBox("Rope");
		Knife = new JCheckBox("Knife");
		Revolver = new JCheckBox("Revolver");
		Wrench = new JCheckBox("Wrench");
		
		panel.add(CandleStick);
		panel.add(LeadPipe);
		panel.add(Rope);
		panel.add(Knife);
		panel.add(Revolver);
		panel.add(Wrench);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		return panel;
	}

	public JComboBox<String> weaponGuess() {
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("Candle Stick");
		combo.addItem("Lead Pipe");
		combo.addItem("Rope");
		combo.addItem("Knife");
		combo.addItem("Revolver");
		combo.addItem("Wrench");
		combo.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		return combo;

	}
	private JPanel createRooms(){
		JPanel panel = new JPanel();
		Kitchen = new JCheckBox("Kitchen");
		Lounge = new JCheckBox("Lounge");
		Conservatory = new JCheckBox("Conservatory");
		Study = new JCheckBox("Study");
		BillardRoom = new JCheckBox("Billard Room");
		DiningRoom = new JCheckBox("Dinning Room");
		Ballroom = new JCheckBox("Ball Room");
		Hall = new JCheckBox("Mrs. Scarlet");
		Library = new JCheckBox("Library");

		panel.add(Kitchen);
		panel.add(Lounge);
		panel.add(Conservatory);
		panel.add(Study);
		panel.add(BillardRoom);
		panel.add(DiningRoom);
		panel.add(Ballroom);
		panel.add(Hall);
		panel.add(Library);
		
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		return panel;
	}
	public JComboBox<String> roomGuess() {
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("Kitchen");
		combo.addItem("Lounge");
		combo.addItem("Conservatory");
		combo.addItem("Study");
		combo.addItem("BillardRoom");
		combo.addItem("DiningRoom");
		combo.addItem("Ballroom");
		combo.addItem("Hall");
		combo.addItem("Library");
		combo.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));

		return combo;

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(550, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DectiveNotes gui = new DectiveNotes();
		frame.add(gui, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
