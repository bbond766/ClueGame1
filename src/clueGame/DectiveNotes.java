package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
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
	public DectiveNotes(){
		setLayout(new GridLayout(3,2));
		JPanel panel = createPeople();
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
//		JPanel panel1 = createPersonGuess();
//		JPanel panel2 = createRooms();
//		JPanel panel3 = createRoomGuess();
//		JPanel panel4 = createWeapons();
//		JPanel panel5 = createWeaponGuess();
//		add(panel1);
//		add(panel2);
//		add(panel3);
		add(panel);
//		add(panel4);
//		add(panel5);
		
		
		
		
//		CandleStick = new JCheckBox("Candle Stick");
//		LeadPipe = new JCheckBox("Lead Pipe");
//		Rope = new JCheckBox("Rope");
//		Knife = new JCheckBox("Knife");
//		Revolver = new JCheckBox("Revolver");
//		Wrench = new JCheckBox("Wrench");
//		
//		
//		add(Kitchen);
//		add(Lounge);
//		add(Conservatory);
//		add(Study);
//		add(BillardRoom);
//		add(DiningRoom);
//		add(Ballroom);
//		add(Hall);
//		add(Library);
//		add(CandleStick);
//		add(LeadPipe);
//		add(Rope);
//		add(Knife);
//		add(Revolver);
//		add(Wrench);
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
//	private JPanel createPersonGuess(){
//		
//	}
	
	private JMenu createFileMenu()
	{
		JMenu menu = new JMenu("File"); 
		menu.add(createFileExitItem());
		return menu;
	}

	private JMenuItem createFileExitItem()
	{
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());

		return item;
	}

//	
//	private JPanel createRooms(){
//		JPanel panel = new JPanel();
//		Kitchen = new JCheckBox("Kitchen");
//		Lounge = new JCheckBox("Lounge");
//		Conservatory = new JCheckBox("Conservatory");
//		Study = new JCheckBox("Study");
//		BillardRoom = new JCheckBox("Billard Room");
//		DiningRoom = new JCheckBox("Dinning Room");
//		Ballroom = new JCheckBox("Ball Room");
//		Hall = new JCheckBox("Mrs. Scarlet");
//		Library = new JCheckBox("Library");
//
//		add(Kitchen);
//		add(Lounge);
//		add(Conservatory);
//		add(Study);
//		add(BillardRoom);
//		add(DiningRoom);
//		add(Ballroom);
//		add(Hall);
//		add(Library);
//		return panel;
//	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DectiveNotes gui = new DectiveNotes();
		frame.add(gui, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
