package ejerciciohilos;
/**
 * Clase Hilo Corredor
 * @author Stiven Cruz
 * @author Daniel Zambrano
 * @since 22/09/2020
 * @version 1.0.0
 */
public class Corredor extends Thread {
    
    /**
     * Actual posición del corredor
     */
    private int posicion;
    
    /**
     * Equipo del corredor
     */
    private Equipo equipo;
    
    /**
     * Símbolo que se imprimirá en consola del corredor 
     */
    private char simbolo;

    /**
     * Constructor
     * @param posicion posición del corredor
     * @param equipo equipo al que pertence el corredor
     * @param simbolo simbolo que será mostrado en consola para el corredor
     */
    public Corredor(int posicion, Equipo equipo, char simbolo) {
        this.posicion = posicion;
        this.equipo = equipo;
        this.simbolo = simbolo;
    }
    
    @Override
    public void run(){
        iniciar();
    }
    
    /**
     * Se encarga de determinar cuál es el corredor que se moverá en la pista
     * y poner en espera a los demás
     */
    private void iniciar(){
        // Si la posición inicial del corredor es igual a la posición en la que está el equipo en general
        if (this.posicion == equipo.getPosicion()){
            // El corredor hace su recorrido
            moverCorredor();
        } else {
            // Se sincroniza el objeto equipo y se pone en espera el corredor
            synchronized(equipo){
                try {
                    equipo.wait();
                } catch (InterruptedException ex) {
                    //Logger.getLogger(HiloCorredor.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.currentThread().interrupt();
                }
                /*
                    Una vez sea despertado el corredor, se vuelve a llamar este mismo método
                    pero con la diferencia de que ahora la posición del equipo ha cambiado,
                    así que ahora es posible que sea su turno para realizar el recorrido
                */
                iniciar();
            }
        }
    }
    
    /**
     * Se encarga de hacer el recorrido del corredor
     * Cada corredor hará un total de 49 pasos hasta llegar a su relevo o a la meta
     * Cuando complete los 49 pasos de recorrido, la posición actual del equipo aumentará
     * Finalmente usará el método notifyAll() para despertar al próximo corredor (si lo hay)
     */
    private void moverCorredor(){
        int recorrido = 0;
        // El corredor debe completar 49 pasos (uno antes de la posición inicial del relevo o la meta)
        while (recorrido < 49){
            try {
                // Obtener un número random de pasos entre 1 y 3
                int pasos = obtenerPasos();
                // Si le sumamos los pasos obtenidor al recorrido, y supera los 49
                if ((recorrido + pasos) > 49){
                    // Obtenemos la cantidad de pasos que faltan para llegar a los 49
                    pasos = 49 - recorrido;
                }
                // Aumentar el recorrido según los pasos
                recorrido += pasos;
                // Esperar un segundo
                Thread.sleep(1000);
                // Modificar la posición actual del corredor
                this.posicion += pasos;
            } catch (InterruptedException ex) {
                //Logger.getLogger(HiloCorredor.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
            }
        }
        //System.out.println("Posición: " + posicion);
        /*
            Se sincroniza el objeto equipo y se modifica la posición actual del equipo, 
            es necesario para que el próximo corredor commienze su recorrido (si lo hay)
        */
        synchronized(equipo){
            equipo.setPosicion(equipo.getPosicion() + 50);
            //System.out.println("Posición: " + equipo.getPosicion());
            // Despierta a los corredores del equipo para que continuen el recorrido (si los hay)
            equipo.notifyAll();
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
