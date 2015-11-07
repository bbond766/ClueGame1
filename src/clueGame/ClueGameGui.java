package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ClueGameGui extends JFrame{
	private Board board;

	public ClueGameGui(){
		board = new Board();
		board.initialize();
		setSize(1000, 1000);
		setTitle("Clue Game");
		add(board, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		setVisible(true);
	}
	private JMenu createFileMenu(){
		JMenu menu = new JMenu("File");
		menu.add(createFileDNItem());
		menu.add(createFileExitItem());
		return menu;
	}
	private JMenuItem createFileExitItem(){
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener{
		public void actionPerformed(ActionEvent a){
			System.exit(0);
		}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	private JMenuItem createFileDNItem(){
		JMenuItem item = new JMenuItem("Detective Notes");
		class MenuItemListener implements ActionListener{
			public void actionPerformed(ActionEvent a){
				DectiveNotes dn = new DectiveNotes();
				JDialog dialog = new JDialog();
				dialog.getContentPane().add(dn);
				dialog.setSize(550,550);
				dialog.setTitle("Test");
				dialog.setVisible(true);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	
	public static void main(String [] args){
		ClueGameGui cgg = new ClueGameGui();
	}
}
