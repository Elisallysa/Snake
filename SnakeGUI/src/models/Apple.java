package models;

import java.awt.Color;
import java.awt.Graphics2D;

public class Apple {

	// Atributos

	// Necesitamos la posición de la manzana
	private int posX;
	private int posY;

	// El lado de la manzana
	private int lado;

	// Ahora el color
	private int colorCuadrado;

	// Constructor
	public Apple(int pX, int pY, int l, int cc) {
		posX = pX;
		posY = pY;
		lado = l;
		colorCuadrado = cc;
	}

	// Getters y setters:
	public int getX() {
		return posX;
	}

	public int getY() {
		return posY;
	}

	public int getLado() {
		return lado;
	}

	public int getColor() {
		return colorCuadrado;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	// Métodos
	
	/**
	 * Método que pinta la manzana
	 * @param g - ??
	 */
	public void pintarse(Graphics2D g) {

		g.setColor(new Color(colorCuadrado));
		// g.drawRect(posX, posY, lado, lado);
		g.fillRect(posX, posY, lado, lado);
		// g.fillOval(posX, posY, lado, lado);

	}

}
