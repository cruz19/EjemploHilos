package ejerciciohilos;

import java.util.*;
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
    private Corredor[][] equiposCorredores;

    /**
     * Constructor
     * @param equiposCorredores array de corredores
     */
    public HiloImpresion(Corredor[][] equiposCorredores) {
        this.equiposCorredores = equiposCorredores;
    }
    
    @Override
    public void run(){
        // Repetir el procedimiento hasta que haya un equipo ganador
        while(true){
            try {
                List<Equipo> equiposEnMeta = new ArrayList<>();
                boolean marcado;
                // Recorrer todos los equipos de corredores
                for (Corredor[] equipoCorredor : equiposCorredores) {
                    // Obtener el equipo de cualquier
                    Equipo equipo = equipoCorredor[0].getEquipo();
                    // Cambia el color de la consola para el equipo
                    System.out.print(equipo.getColor());
                    // Mostrar el número del equipo
                    System.out.print("EQUIPO " + equipo.getNumero() + " ");
                    // Dibujar pista/recorrido y posición de los corredores del equipo
                    for (int i = 0; i < 150; i++) {
                        // El fragmento de pista no ha sido marcado aún
                        marcado = false;
                        // Recorrer los corredores del equipo
                        for (Corredor corredor : equipoCorredor) {
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
                        equiposEnMeta.add(equipo);
                    }
                    // Salto de línea para la impresión del siguiente equipo
                    System.out.println("°°°");
                }
                // Saltos de línea para diferenciar las impresiones en consola por segundo
                System.out.println("\n\n");
                
                // Antes de la siguiente iteración, verificar si algún equipo ya finalizo el recorrido
                if (!equiposEnMeta.isEmpty()){
                    // Detener el resto de hilos/corredores activos
                    detenerHilos();
                    // Mostrar el resultado de la carrera en consola
                    mostrarResultadoCarrera(equiposEnMeta);
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
        for (Corredor[] equipoCorredor : equiposCorredores) {
            // Recorrer los corredores del equipo
            for (Corredor corredor : equipoCorredor) {
                if (corredor.isAlive()){
                    corredor.interrupt();
                }
            }
        }
    }
    
    /**
     * Se encarga de mostrar el resultado de la carrera
     * es posible que ocurra un empate
     */
    private void mostrarResultadoCarrera(List<Equipo> equiposEnMeta){
        if (equiposEnMeta.size() == 1){
            System.out.print(equiposEnMeta.get(0).getColor() 
                        + "°°° GANÓ EL EQUIPO " + equiposEnMeta.get(0).getNumero() + " °°°");
        } else {
            System.out.println("HA OCURRIDO UN EMPATE ENTRE:");
            equiposEnMeta.stream().forEach((equipo) -> {
                System.out.println(equipo.getColor() + "EQUIPO " + equipo.getNumero());
            });
        }
        System.out.println("\n\n");
    }

    /**
     * Se encarga de retornar el array de equipos corredores
     * @return 
     */
    public Corredor[][] getEquiposCorredores() {
        return equiposCorredores;
    }

    /**
     * Se encarga de modificar el array de equipos corredores
     * @param equiposCorredores 
     */
    public void setEquiposCorredores(Corredor[][] equiposCorredores) {
        this.equiposCorredores = equiposCorredores;
    }
}
