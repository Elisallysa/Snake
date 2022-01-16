package models;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * Clase MySnakeFrame
 * 
 * Este es el primer ejemplo de comentarios de documentación que vemos en JAVA
 * Como se puede apreciar son un bloque de comentarios, no sólo una línea
 * 
 * Aquí explicamos qué sentido tiene la clase
 * 
 * Esta clase hereda de JFrame. JFrame es una ventana equivalente a un Form en
 * Gambas3 Nuestra ventana principal además del comportamiento y estado natural
 * a una ventana gráfica controlará todo el estado del juego. Como nuestro
 * "game loop" está codificado como "run forever" tenemos que tener algún
 * objeto que controle si el juego está pausado, empezado, terminado en todo
 * momento, y que a su vez le diga a la serpiente que se resetee, que se mueva,
 * crezca, etc. Para controlar el estado se usan "semáforos". Estos no son nada
 * más que booleanos. Cuando están a false sería el equivalente a "semáforo
 * rojo" y cuando están a true sería el equivalente a "semáforo verde" Estos
 * semáforos se ponen en una serie de "if" que permiten controlar al objeto
 * MySnakeFrame si se ejecuta alguna acción o no en la serpiente.
 * 
 * @author andres
 *
 */

@SuppressWarnings("unused")
public class MySnakeFrame extends JFrame {

	// ***** estado

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Nuestra serpiente y la manzana
	private Serpiente snake;
	private Apple apple;

	// semáforos para indicar que estamos jugando o no
	private boolean jugando;
	private boolean pausado;

	// semáforos para mostrar mensaje al final, sólo una vez.
	private boolean mostrarFinal;
	private boolean mostrado;

	// **** Comportamientos

	// Constructor
	public MySnakeFrame() {
		snake = new Serpiente();
		jugando = false;
		mostrarFinal = false;
		mostrado = false;
		pausado = false;
		apple = new Apple(160, 160, 20, 260236);
	}

	// si alguien necesita nuestra serpiente, se la proporcionamos.
	public Serpiente getSerpiente() {
		return snake;
	}

	public Apple getApple() {
		return apple;
	}

	// tenemos que mostrar la ventanita de final de partida??? Sólo una vez...
	public boolean mostrarFin() {
		boolean resultado;

		resultado = false;
		if (mostrarFinal && !mostrado) { // estamos al final de una partida y no hemos mostrado nada
			resultado = true; // activamos el resultado para que se muestre la ventana
			mostrado = true; // ya no dejamos que se muestre más la próxima vez...
		}

		return resultado;
	}

	// toca crecer sólo si estamos en una partida activa y no estamos pausados...
	public void tocaCrecer() {
		if (jugando && !pausado)
			snake.crecer();

	}

	// toca moverse sólo si estamos en una partida activa y no estamos pausados...
	public void tocaMoverse() {
		if (jugando && !pausado)
			snake.moverse(this.apple);

	}

	// han pulsado el botón de start, hay que ponerlo todo en orden.
	public void empezarDeNuevo() {
		snake = new Serpiente(); // nueva y flamante serpiente
		jugando = true; // estamos jugando a partir de ¡YA!
		mostrarFinal = false; // ni estamos al final ni mucho menos
		mostrado = false; // hemos mostrado el msg de final
		pausado = false; // Y todavía nadie ha pulsado el pause, todavía...
	}

	// Hay que ver si la serpiente sigue viva, pero sólo si estamos jugando y no en
	// modo pausa...
	public void comprobarEstado(int iAlto, int iAncho) {
		if (jugando && !pausado) {
			if (snake.estaMuerta(iAlto, iAncho)) {
				// acabamos de matarnos. Hay que mostrar msg al final y ya no jugamos...
				jugando = false;
				mostrarFinal = true;
				mostrado = false;
			}
		}
	}

	// sólo pausamos / continuamos si estamos jugando.
	public void pausaContinuaJuego() {
		if (jugando) {
			pausado = !pausado;
		}
	}

	// nos han pulsado tecla, cambiamos de dirección.
	public void cambiaDireccion(int key) {
		snake.cambiaDireccion(key);
	}

	/**
	 * Crea una manzana en un lugar aleatorio del tablero. Comprueba primero que
	 * no se va a colocar encima de la serpiente o de la manzana:
	 * @param tableroHeight - altura del tablero
	 * @param tableroWidth - ancho del tablero
	 */
	
	public void createApple(int tableroHeight, int tableroWidth) {
		
		boolean buscaOtro = false;
		int vertical;
		int horizontal;
		
		do {
		vertical = (int) (Math.random() * (tableroHeight - 1)) + 1;
			
		if (vertical == this.apple.getY()) {
			buscaOtro = true;
		}
		
		for (int cont=0; cont < snake.getListaCuadrados().size(); cont++) {
	          
				if (vertical == snake.getListaCuadrados().get(cont).getY()) {
					buscaOtro = true;
					break;
				} else {
					buscaOtro = false;
				}
				
	        }
		}while(!buscaOtro);
		
		buscaOtro = false;
		do {
			horizontal = (int) (Math.random() * (tableroWidth - 1)) + 1;
			
			if (horizontal == this.apple.getX()) {
				buscaOtro = true;
			}
			
		for (int cont=0; cont < snake.getListaCuadrados().size(); cont++) {
	          
				if (horizontal == snake.getListaCuadrados().get(cont).getY()) {
					buscaOtro = true;
				} else {
					buscaOtro = false;
				}
				
	        }
		}while(!buscaOtro);
		
		this.apple.setPosY(vertical);
		this.apple.setPosX(horizontal);
		
		
	}
}
