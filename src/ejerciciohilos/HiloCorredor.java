package ejerciciohilos;
/**
 * Clase HiloCorredor
 * @author Stiven Cruz
 * @since 22/09/2020
 * @version 1.0.0
 */
public class HiloCorredor extends Thread {
    
    /**
     * Posición del corredor
     */
    private int posicion;
    
    /**
     * Equipo del corredor
     */
    private Equipo equipo;
    
    /**
     * Símbolo del corredor en la pista 
     */
    private char simbolo;

    /**
     * Constructor
     * @param posicion posición del corredor
     * @param equipo equipo al que pertence el corredor
     * @param simbolo simbolo que será mostrado en consola para el corredor
     */
    public HiloCorredor(int posicion, Equipo equipo, char simbolo) {
        this.posicion = posicion;
        this.equipo = equipo;
        this.simbolo = simbolo;
    }
    
    @Override
    public void run(){
        while(equipo.getPosicion() != 150){
            if (posicion == equipo.getPosicion()){
                int recorrido = 0;
                while (recorrido != 49){
                    // Dar pasos random
                    int pasos = obtenerPasos();
                    if ((recorrido + pasos) > 49){
                        pasos = 49 - recorrido;
                    }
                    recorrido += pasos;
                    // Esperar un segundo
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(HiloCorredor.class.getName()).log(Level.SEVERE, null, ex);
                        Thread.currentThread().interrupt();
                    }
                    // Modificar la posición del corredor
                    posicion += pasos;
                }
                //System.out.println("Posición Inicial: " + posicionInicial);
                // Modificar la posición del equipo
                synchronized(equipo){
                    equipo.setPosicion(equipo.getPosicion() + 50);
                    //System.out.println("Posición: " + equipo.getPosicion());
                    equipo.notifyAll();
                }
            } else {
                synchronized(equipo){
                    try {
                        equipo.wait();
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(HiloCorredor.class.getName()).log(Level.SEVERE, null, ex);
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
    
    /**
     * Se encarga de retornar un número random entre [1,2,3] para determinar el 
     * número de pasos dados por el corredor
     * @return pasos
     */
    private int obtenerPasos(){
        return (int) (Math.random() * 3 + 1);
    }

    /**
     * Se encarga de retornar la posición del corredor
     * @return posición
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Se encarga de modificar la posición del correador
     * @param posicion
     */
    public void setPosicionInicial(int posicion) {
        this.posicion = posicion;
    }

    /**
     * Se encarga de retornar el equipo
     * @return equipo
     */
    public Equipo getEquipo() {
        return equipo;
    }

    /**
     * Se encarga de modificar el equipo
     * @param equipo 
     */
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    /**
     * Se encargar de retornar el simbolo del corredor
     * @return 
     */
    public char getSimbolo() {
        return simbolo;
    }

    /**
     * Se encarga de modificar el símbolo del corredor
     * @param simbolo 
     */
    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }
}
