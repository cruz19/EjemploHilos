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
                boolean marcado, detener = false;
                // Recorrer todos los equipos
                for (HiloCorredor[] equipoCorredor : equiposCorredores) {
                    String color = equipoCorredor[0].getEquipo().getColor();
                    for (int i = 0; i < 150; i++) {
                        marcado = false;
                        for (HiloCorredor corredor : equipoCorredor) {
                            if (corredor.getPosicionInicial() == i){
                                System.out.print(color + corredor.getSimbolo());
                                marcado = true;
                            }
                            if (corredor.getPosicionInicial() == 149){
                                mensajeGanador = corredor.getEquipo().getColor();
                                mensajeGanador += "GANADOR EQUIPO " + corredor.getEquipo().getNumero();
                                detener = true;
                            }
                        }
                        if (!marcado)
                            System.out.print(color + "_");
                    }
                    System.out.println(ConsoleColors.RESET);
                }
                System.out.println("\n\n");
                
                if (detener){
                    detenerHilos();
                    System.out.println(mensajeGanador);
                    System.out.println(ConsoleColors.RESET);
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
