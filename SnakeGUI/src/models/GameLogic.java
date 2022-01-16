package models;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import enums.OpcionesDificultad;

public class GameLogic extends Thread {

	// Atributos

	private int contador;
	private MySnakeFrame frame;
	private JPanel mainPanel;
	private TableroJuego tablero;
	private JPanel botonera;
	private JLabel puntos;
	private JLabel puntosNum;
	private JButton start;
	private JButton pause;
	private ControlTeclado miControlador;

	// Constructor
	public GameLogic() {
	}

	public void initialize() {
		startGame();
	}

	/**
	 * Método para solucionar la dificultad que generan los hilos en la UI.
	 */
	@Override
	public void run() {
		frame = new MySnakeFrame();
		try {
			startGame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que crea todo el juego. Tanto componentes como lógica.
	 */
	public void startGame() {

		// 1. Crear el frame.
		frame = new MySnakeFrame();

		// 2. Se aplica el tamaño que han seleccionado en el JOptionPane de la clase
		// LevelSizeView:
		switch (LevelSizeView.getSelection().toString()) {
		case "Pequeño":
			frame.setSize(600, 600);
			break;
		case "Mediano":
			frame.setSize(800, 700);
			break;
		case "Grande":
			frame.setSize(1000, 800);
			break;
		default:
			System.out.println("Default");
			break;
		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 3. Ahora creamos los componentes y los ponemos en la frame (ventana).

		// El panel de fondo. Rellena el frame, y sirve de contenedor del tablero y de
		// la botonera.
		mainPanel = new JPanel(new BorderLayout());

		// Ahora creamos el tablero. Recordamos: no deja de ser un panel un poquito
		// "especial"
		tablero = new TableroJuego();

		// Les damos las propiedades a nuestro tablero. Su color, tamaÃ±o y borde
		tablero.setBorder(BorderFactory.createLineBorder(Color.black));
		tablero.setBackground(new java.awt.Color(255, 255, 255));
		tablero.setSize(600, 400);

		// Le damos un enlace al tablero para que sepa quiÃ©n es su frame (ventana) y
		// asÃ­
		// sepa
		// quiÃ©n contiene la serpiente y quiÃ©n controla el juego...
		tablero.setSnakeFrame(frame);

		// Ahora el turno de la botonera. TendrÃ¡ los dos botones y las etiquetas de
		// texto
		botonera = new JPanel();
		botonera.setBorder(BorderFactory.createLineBorder(Color.black));
		botonera.setBackground(new java.awt.Color(150, 150, 150));

		// Ahora definimos las dos etiquetas para los puntos.
		puntos = new JLabel();
		puntos.setText("Puntos: ");
		puntos.setBackground(new java.awt.Color(190, 190, 190));

		puntosNum = new JLabel();
		puntosNum.setText("0");
		puntosNum.setBackground(new java.awt.Color(190, 190, 190));

		// turno de los botones de empezar y pausar/continuar
		start = new JButton();
		start.setSize(50, 20);
		start.setText("Start");
		start.addActionListener(new MyButtonListener(frame, tablero));

		pause = new JButton();
		pause.setSize(50, 20);
		pause.setText("Pause");
		pause.addActionListener(new MyButtonListener(frame, tablero));

		// Preparamos el control del teclado
		miControlador = new ControlTeclado();
		miControlador.setSnakeFrame(frame); // le damos al controlador de teclado un
											// enlace el frame principal
		tablero.addKeyListener(miControlador); // le decimos al tablero que el teclado es cosa de nuestro controlador
		tablero.setFocusable(true); // permitimos que el tablero pueda coger el foco.

		// AÃ±adimos los componentes uno a uno, cada uno en su contenedor, y al final el
		// panel principal
		// se aÃ±ade al frame principal.
		botonera.add(start);
		botonera.add(pause);
		botonera.add(puntos);
		botonera.add(puntosNum);

		mainPanel.add(botonera, BorderLayout.PAGE_END);
		mainPanel.add(tablero, BorderLayout.CENTER);
		frame.getContentPane().add(mainPanel);

		frame.setVisible(true); // activamos la ventana principal para que sea "pintable"

		contador = 0; // nuestro control de los pasos del tiempo. Cada vez que contador cuenta un
						// paso, pasan 10ms

		/**
		 * Según el nivel seleccionado en la ventana de selección de dificultad, se
		 * aplicará un multiplicador u otro a la velocidad de la serpiente.
		 */
		OpcionesDificultad level = LevelSizeView.getDificultadSeleccionada();
		double n = 1;

		switch (level) {
		case Fácil:
			n = 3;
			break;
		case Intermedio:
			n = 2;
			break;
		case Difícil:
			n = 0.5;
			break;
		case Imposible:
			n = 0.1;
			break;
		default:
			n = 2;
		}

		while (true)

		{ // por siempre jamÃ¡s (hasta que nos cierren la ventana) estamos controlando el
			// juego.

			// actualizamos el estado del juego

			/**
			 * PARA NO BORRAR .. CÓDIGO CON EL QUE CRECÍA CADA 30*n ms
			 * 
			 * if (contador % (10*n) == 0) { // cada 200ms nos movemos o crecemos... if
			 * (contador == (30*n)) { // Cada 600ms crecemos y reseteamos el contador
			 * contador = 0; frame.tocaCrecer(); // La serpiente ya no crece cada 30*n ms //
			 * hemos crecido... actualizamos puntos.
			 * puntosNum.setText(Integer.toString(frame.getSerpiente().getPuntos())); } else
			 * { // a los 200 y 400 ms nos movemos... contador++; frame.tocaMoverse();
			 */

			if (contador % (10 * n) == 0) {
				if (contador == (30 * n)) { // A los 30*n se resetea el contador
					contador = 0;
				} else { // Cada 10* y 20*n se mueve
					contador++;
					frame.tocaMoverse();

					// Si la cabeza de la serpiente está encima de la manzana:
					if (frame.getSerpiente().getListaCuadrados().get(0).estaEncimaDe(frame.getApple())) {
						puntosNum.setText(Integer.toString(frame.getSerpiente().getPuntos())); // aumentamos la
																								// puntuación
						frame.createApple(tablero.getHeight(), tablero.getWidth()); // Luego creamos una nueva en el
																					// tablero
					}

				}
				frame.comprobarEstado(tablero.getHeight(), tablero.getWidth()); // comprobamos si hemos muerto o no.

			} else { // Cada vez que no hay que moverse o crecer, simplemente contamos...
				contador++;
			}

			// hemos terminado?? mostramos msg
			if (frame.mostrarFin()) {
				JOptionPane.showMessageDialog(frame,
						"Se acabó, vaquero, has conseguido " + puntosNum.getText() + " puntos.");
			}

			// Repintamos
			tablero.repaint();

			// Esperamos para dar tiempo al thread de repintado a pintar.

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
