package ejerciciohilos;

/**
 * Clase Main
 * @author Steven Cruz
 * @since 22/09/2020
 * @version 1.0.0
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Creación de los 3 equipos
        Equipo equipo1 = new Equipo(1, ConsoleColors.GREEN);
        Equipo equipo2 = new Equipo(2, ConsoleColors.RED);
        Equipo equipo3 = new Equipo(3, ConsoleColors.BLUE);
        
        // Creación de todos los corredores agrupados en equipos
        HiloCorredor[] equipoCorredores1 = new HiloCorredor[]{
            new HiloCorredor(0, equipo1, 'X'),
            new HiloCorredor(50, equipo1, 'O'),
            new HiloCorredor(100, equipo1, '$')
        };
        
        HiloCorredor[] equipoCorredores2 = new HiloCorredor[]{
            new HiloCorredor(0, equipo2, 'X'),
            new HiloCorredor(50, equipo2, 'O'),
            new HiloCorredor(100, equipo2, '$')
        };
        
        HiloCorredor[] equipoCorredores3 = new HiloCorredor[]{
            new HiloCorredor(0, equipo3, 'X'),
            new HiloCorredor(50, equipo3, 'O'),
            new HiloCorredor(100, equipo3, '$')
        };
        
        // Crear array de todos los equipos corredores
        HiloCorredor[][] equiposCorredores = new HiloCorredor[][]{
            equipoCorredores1,
            equipoCorredores2,
            equipoCorredores3
        };
        
        // Iniciar todos los corredores
        // Recorrer equipos
        for (HiloCorredor[] equipoCorredor : equiposCorredores) {
            // Recorrer corredores
            for (HiloCorredor corredor : equipoCorredor) {
                corredor.start();
            }
        }
        
        // Hilo encargado de imprimir la posición de los corredores
        HiloImpresion hiloImpresion = new HiloImpresion(equiposCorredores);
        hiloImpresion.start();
    }
}
