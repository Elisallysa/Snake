package models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class MyButtonListener implements ActionListener {

	private MySnakeFrame snakeFrame;
	private TableroJuego tablero;
	@SuppressWarnings("unused")
	private JLabel puntos;
	
	
	public MyButtonListener(MySnakeFrame sf, TableroJuego t) {
		snakeFrame = sf;
		tablero = t;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (((JButton)ae.getSource()).getText() == "Start") {
			snakeFrame.empezarDeNuevo();
			tablero.requestFocus();			
		} else { //Botón de pausar
			snakeFrame.pausaContinuaJuego();
			tablero.requestFocus();
		}
	}

}
