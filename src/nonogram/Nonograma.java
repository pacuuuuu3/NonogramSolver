/* Clase que modela un nonograma */
package nonograma;

import java.util.List;

/**
 * Modelo de un nonograma.
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class Nonograma{

    private final int filas, columnas; /* Número de filas y columnas */
    private boolean[][] colores; /* Matriz de colores (false = blanco, true = negro) */
    private final List<Integer>[] restriccionesF, restriccionesC; /* Restricciones en filas y columnas */

    /**
     * Constructor.
     * @param filas - Número de filas del nonograma
     * @param columnas - Número de columnas del nonograma
     * @param restriccionesF - Restricciones de filas
     * @param restriccionesC - Restricciones de columnas
     */
    public Nonograma(int filas, int columnas, List<Integer>[] restriccionesF, List<Integer>[] restriccionesC){
	this.filas = filas;
	this.columnas = columnas;
	this.restriccionesF = restriccionesF;
	this.restriccionesC = restriccionesC;
	this.colores = new boolean[filas][columnas];
    }    
}
