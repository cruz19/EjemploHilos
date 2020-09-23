package ejerciciohilos;

/**
 * Clase Equipo
 * @author Stiven Cruz
 * @since 22/09/2020
 * @version 1.0.0
 */
public class Equipo {
    /**
     * Número del equipo
     */
    private int numero;
    /**
     * Posición del equipo en la pista
     */
    private int posicion;
    
    /**
     * Color del equipo en la pista
     */
    private String color;

    /**
     * Constructor
     * @param numero número del equipo
     * @param color color del equipo
     */
    public Equipo(int numero, String color) {
        this.numero = numero;
        this.color = color;
        this.posicion = 0;
    }

    /**
     * Se encarga de retornar el número del equipo
     * @return número de equipo
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Se encarga de modificar el número del equipo
     * @param numero 
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Se encarga de retornar la posición del equipo
     * @return posición
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Se encarga de modificar la posición del equipo
     * @param posicion 
     */
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    /**
     * Se encarga de retornar el color asignado para la impresión en consola
     * @return 
     */
    public String getColor() {
        return color;
    }

    /**
     * Se encarga de modificar el color asignado para la impresión en consola
     * @param color 
     */
    public void setConsoleColor(String color) {
        this.color = color;
    }
    
}
