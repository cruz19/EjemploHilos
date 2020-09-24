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
        // Repetir el procedimiento hasta que haya un equipo ganador
        while(true){
            try {
                String mensajeGanador = "";
                boolean marcado, meta = false;
                // Recorrer todos los equipos de corredores
                for (HiloCorredor[] equipoCorredor : equiposCorredores) {
                    // Obtener el equipo actual
                    Equipo equipo = equipoCorredor[0].getEquipo();
                    // Cambia el color de la consola
                    System.out.print(equipo.getColor());
                    // Imprimir el número del equipo
                    System.out.print("EQUIPO " + equipo.getNumero() + " ");
                    // Dibujar pista/recorrido
                    for (int i = 0; i < 150; i++) {
                        // El fragmento de pista no ha sido marcado aún
                        marcado = false;
                        // Recorrer los corredores del equipo
                        for (HiloCorredor corredor : equipoCorredor) {
                            // Si la posición actual del corredor es igual al i (fragmento), se coloca el simbolo del corredor
                            if (corredor.getPosicion() == i){
                                System.out.print(corredor.getSimbolo());
                                marcado = true;
                            }
                        }
                        // Si ningún corredor del equipo tiene la posición/fragmento marcada
                        if (!marcado)
                            System.out.print("_");
                    }
                    // Verificar si el equipo ha llegado a la meta
                    if (equipo.getPosicion() == 150){
                        mensajeGanador = equipo.getColor();
                        mensajeGanador += "\t¡¡¡ GANÓ EL EQUIPO " + equipo.getNumero() + " !!!";
                        meta = true;
                    }
                    // Salto de línea para la impresión del siguiente equipo
                    System.out.println("°°°");
                }
                // Saltos de línea para diferenciar las impresiones en consola por segundo
                System.out.println("\n\n");
                
                // Antes de la siguiente iteración, verificar si algún equipo ya finalizo el recorrido
                if (meta){
                    // Detener el resto de hilos activos
                    detenerHilos();
                    // Imprimir el ganador en consola
                    System.out.print(mensajeGanador);
                    System.out.println(ConsoleColors.RESET + "\n\n");
                    // Salir del while de impresión
                    break;
                }
                
                // Pausa para imprimir cada segundo
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloImpresion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Se encarga de interrumpir todos los hilos que todavía este activos/vivos
     */
    private void detenerHilos(){
        // Recorrer los equipos de corredores
        for (HiloCorredor[] equipoCorredor : equiposCorredores) {
            // Recorrer los corredores del equipo
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
