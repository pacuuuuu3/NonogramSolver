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
    private boolean[][] colores; /* Colores de la matriz */
    private final int[][] restriccionesF, restriccionesC; /* Restricciones de filas y columnas */

    /**
     * Constructor.
     * @param filas - Número de filas del nonograma
     * @param columnas - Número de columnas del nonograma
     * @param restriccionesF - Restricciones de filas
     * @param restriccionesC - Restricciones de columnas
     */
    public Nonograma(int filas, int columnas, int[][] restriccionesF, int[][] restriccionesC){
	this.filas = filas;
	this.columnas = columnas;
	this.restriccionesF = restriccionesF;
	this.restriccionesC = restriccionesC;
	this.colores = new boolean[filas][columnas];
    }

    /**
     * Nos dice si la solución dada a un nonograma es válida.
     * @return Si la solución es válida o no.
     */
    public boolean solucionValida(){
	for(int i = 0; i < filas; ++i)
	    if(!filaValida(i))
		return false;
	for(int j = 0; j < columnas; ++j)
	    if(!columnaValida(j))
		return false;
	return true;
    }

    public boolean columnaValida(int c){
	return true;
    }

    /**
     * Nos dice si una fila es válida de acuerdo a sus restricciones.
     */
    public boolean filaValida(int i){
	if(restriccionesF[i][0] == 0){
	    for(int k = 0; k < columnas; k++)
		if(colores[i][k])
		    return false;
	    return true;
	} 
	int actual = 0; /* Columna actual sobre la que verificamos las restricciones */
	for(int j = 0; j < columnas; ++j){
	    int res = restriccionesF[i][j]; /* Leemos la siguiente restricción */
	    if(res <= 0)
		break;
	    while(actual < columnas && !colores[i][actual]) /* Vamos hasta el siguiente negro */
		actual++;
	    if(actual == columnas)
		return false;
	    for(int k = 0; k < res; ++k)
		if(actual+k > columnas || !colores[i][actual+k])
		    return false;
	    actual = actual + res;
	}
	while(actual < columnas && !colores[i][actual++]);
	if(actual != columnas)
	    return false;
	return true;
    }

    /**
     * Cambia el color de la coordenada (fila, columna)
     * @param fila - La fila sobre la que se colorea
     * @param columna - La columna sobre la que se colorea
     */
    public void colorea(int fila, int columna){
	this.colores[fila][columna] = !this.colores[fila][columna];
    }
}
