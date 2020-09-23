package ejerciciohilos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hilo para imprimir los corredores
 * @author Stiven Cruz
 * @since 22/09/2020
 * @version 1.0.0
 */
public class HiloImpresion extends Thread {
    /**
     * Array de corredores
     */
    private HiloCorredor[][] equiposCorredores;

    /**
     * Constructor
     * @param equiposCorredores array de corredores
     */
    public HiloImpresion(HiloCorredor[][] equiposCorredores) {
        this.equiposCorredores = equiposCorredores;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                String mensajeGanador = "";
                boolean marcado, finalizado = false;
                // Recorrer todos los equipos
                for (HiloCorredor[] equipoCorredor : equiposCorredores) {
                    // Obtener el equipo actual
                    Equipo equipo = equipoCorredor[0].getEquipo();
                    // Cambia el color de la consola
                    System.out.print(equipo.getColor());
                    // Imprimir el número del equipo
                    System.out.print("EQUIPO " + equipo.getNumero() + " ");
                    // Dibujar pista/recorrido
                    for (int i = 0; i < 150; i++) {
                        marcado = false;
                        // Recorrer los corredores del equipo
                        for (HiloCorredor corredor : equipoCorredor) {
                            // Si la posición actual del corredor es igual al i, se coloca el simbolo
                            if (corredor.getPosicion() == i){
                                System.out.print(corredor.getSimbolo());
                                marcado = true;
                            }
                            // Si un corredor ya finalizó el recorrido
                            if (corredor.getPosicion() == 149){
                                mensajeGanador = equipo.getColor();
                                mensajeGanador += "\t¡¡¡ GANA EL EQUIPO " + equipo.getNumero() + " !!!\n\n";
                                finalizado = true;
                            }
                        }
                        // Si ningún corredor del equipo tiene la posición marcada
                        if (!marcado)
                            System.out.print("_");
                    }
                    // Salto de línea
                    System.out.println();
                }
                System.out.println("\n\n");
                
                // Una vez dibujado todos los corredores, de todos los equipos se verifica si alguno finalizó el recorrido
                if (finalizado){
                    // detener todos los hilos aún activos
                    detenerHilos();
                    // Imprimir el mensaje del equipo ganador
                    System.out.println(mensajeGanador + ConsoleColors.RESET);
                    // Salir del while de impresión
                    break;
                }
                
                // Imprimir cada segundo
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloImpresion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void detenerHilos(){
        for (HiloCorredor[] equipoCorredor : equiposCorredores) {
            for (HiloCorredor corredor : equipoCorredor) {
                if (corredor.isAlive()){
                    corredor.interrupt();
                }
            }
        }
    }

    /**
     * Se encarga de retornar el array de equipos corredores
     * @return 
     */
    public HiloCorredor[][] getEquiposCorredores() {
        return equiposCorredores;
    }

    /**
     * Se encarga de modificar el array de equipos corredores
     * @param equiposCorredores 
     */
    public void setEquiposCorredores(HiloCorredor[][] equiposCorredores) {
        this.equiposCorredores = equiposCorredores;
    }
}
