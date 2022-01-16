package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("unused")
public class Cuadrado {
	
	//Constantes. Son las direcciones en las que nuestro cuadrado puede moverse
    static public final int ARRIBA = 1;
    static public final int ABAJO = 2;
    static public final int IZQ = 3;
    static public final int DER = 4;


    //Atributos
    private int posX;
    private int posY;
    private int lado;
    private int colorCuadrado;

    /**
     * Constructor para crear un objeto Cuadrado
     * @param pX - Posici�n X en el tablero
     * @param pY - Posici�n Y en el tablero
     * @param l - Lado del Cuadrado
     * @param cc - Color del cuadrado
     */
    public Cuadrado(int pX,int pY,int l, int cc) {
        posX = pX;
        posY = pY;
        lado = l;
        colorCuadrado = cc;
    }
    

    /**
     * M�todo para mover el Cuadrado
     * @param iDireccion - la direcci�n suma o resta el lado del Cuadrado a la posici�n X o Y del mismo Cuadrado
     */
    public void moverse(int iDireccion) {
        switch (iDireccion) {
            case Cuadrado.ARRIBA: posY -= lado;   // 1 es arriba
                    break;
            case Cuadrado.ABAJO: posY += lado;   // 2 es abajo
                    break;
            case Cuadrado.IZQ: posX -= lado;   // 3 es izquierda
                    break;
            case Cuadrado.DER: posX += lado;   // 4 es derecha

        }
    }

    /**
     * M�todo que devuelve true si un Cuadrado est� encima de otro objeto Cuadrado.
     * @param otroC - Otro objeto Cuadrado
     * @return true - si un cuadrado est� encima de otro Cuadrado; false - si un cuadrado no est� encima de otro Cuadrado
     */
    public boolean estaEncimaDe(Cuadrado otroC) {
    	//en nuestro caso, sólo comprobamos la esquina superior izq 
    	//almacenada en las posiciones X e Y. No hay otra posibilidad.
        return (otroC.getX() == posX && otroC.getY() == posY);
    }
    
    /**
     * M�todo que devuelve true si el objeto Cuadrado est� encima de la manzana.
     * @param manzana - Objeto de la clase Cuadrado que hace las veces de manzana
     * @return true - si el Cuadrado est� encima de la manzana; false - si el Cuadrado no est� encima de la manzana
     */
    public boolean estaEncimaDe(Apple manzana) {
    	//en nuestro caso, sólo comprobamos la esquina superior izq 
    	//almacenada en las posiciones X e Y. No hay otra posibilidad.
        return (manzana.getX() == posX && manzana.getY() == posY);
    }
    

    // Getters y setters
    public int getX(){
        return posX;
    }
    public int getY(){
        return posY;
    }
    public int getLado() {return lado;}
    
    public int getColor() {
    	return colorCuadrado;
    }

    /**
     * Este m�todo pinta el cuadrado
     * @param g - ??
     */
    public void pintarse(Graphics2D g) {
        
    	g.setColor(new Color(colorCuadrado));
    	//g.drawRect(posX, posY, lado, lado);
    	g.fillRect(posX, posY, lado, lado);
		//g.fillOval(posX, posY, lado, lado);
		
    }
    
}
