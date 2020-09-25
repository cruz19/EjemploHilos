package ejerciciohilos;

/**
 * Clase Principal
 * @author Steven Cruz
 * @author Daniel Zambrano
 * @since 22/09/2020
 * @version 1.0.0
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Creación de los tres equipos, cada uno con su número y su color (en consola)
        Equipo equipo1 = new Equipo(1, ConsoleColors.GREEN);
        Equipo equipo2 = new Equipo(2, ConsoleColors.RED);
        Equipo equipo3 = new Equipo(3, ConsoleColors.BLUE);
        
        // Array de los corredores del equipo1
        Corredor[] equipoCorredores1 = new Corredor[]{
            new Corredor(0, equipo1, 'X'),
            new Corredor(50, equipo1, 'O'),
            new Corredor(100, equipo1, '#')
        };
        
        // Array de los corredores del equipo 2
        Corredor[] equipoCorredores2 = new Corredor[]{
            new Corredor(0, equipo2, 'X'),
            new Corredor(50, equipo2, 'O'),
            new Corredor(100, equipo2, '#')
        };
        
        // Array de los corredores del equipo 3
        Corredor[] equipoCorredores3 = new Corredor[]{
            new Corredor(0, equipo3, 'X'),
            new Corredor(50, equipo3, 'O'),
            new Corredor(100, equipo3, '#')
        };
        
        // Array con todos los equipos corredores para la impresión
        Corredor[][] equiposCorredores = new Corredor[][]{
            equipoCorredores1,
            equipoCorredores2,
            equipoCorredores3
        };
        
        // INICIAR TODOS LOS HILOS CORREDORES
        // Recorrer equipos
        for (Corredor[] equipoCorredor : equiposCorredores) {
            // Recorrer corredores
            for (Corredor corredor : equipoCorredor) {
                corredor.start();
            }
        }
        
        /**
         * Hilo encargado de imprimir la posición de los corredores en consola
         * y el resultado de la carrera
         */
        HiloImpresion hiloImpresion = new HiloImpresion(equiposCorredores);
        hiloImpresion.start();
    }
}
