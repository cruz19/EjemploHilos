package ejerciciohilos;

/**
 * Clase Principal
 * @author Steven Cruz
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
        HiloCorredor[] equipoCorredores1 = new HiloCorredor[]{
            new HiloCorredor(0, equipo1, 'X'),
            new HiloCorredor(50, equipo1, 'O'),
            new HiloCorredor(100, equipo1, '$')
        };
        
        // Array de los corredores del equipo 2
        HiloCorredor[] equipoCorredores2 = new HiloCorredor[]{
            new HiloCorredor(0, equipo2, 'X'),
            new HiloCorredor(50, equipo2, 'O'),
            new HiloCorredor(100, equipo2, '$')
        };
        
        // Array de los corredores del equipo 3
        HiloCorredor[] equipoCorredores3 = new HiloCorredor[]{
            new HiloCorredor(0, equipo3, 'X'),
            new HiloCorredor(50, equipo3, 'O'),
            new HiloCorredor(100, equipo3, '$')
        };
        
        // Array con todos los equipos corredores para la impresión
        HiloCorredor[][] equiposCorredores = new HiloCorredor[][]{
            equipoCorredores1,
            equipoCorredores2,
            equipoCorredores3
        };
        
        // INICIAR TODOS LOS HILOS CORREDORES
        // Recorrer equipos
        for (HiloCorredor[] equipoCorredor : equiposCorredores) {
            // Recorrer corredores
            for (HiloCorredor corredor : equipoCorredor) {
                corredor.start();
            }
        }
        
        // Hilo encargado de imprimir la posición de los corredores en consola
        HiloImpresion hiloImpresion = new HiloImpresion(equiposCorredores);
        hiloImpresion.start();
    }
}
