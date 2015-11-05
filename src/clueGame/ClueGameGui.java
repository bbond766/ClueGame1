package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClueGameGui extends JFrame{
	private Board board;
	
	public ClueGameGui(){
		board = new Board();
		board.initialize();
		setSize(1000, 1000);
		add(board, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	public static void main(String [] args){
		ClueGameGui cgg = new ClueGameGui();
	}
}
