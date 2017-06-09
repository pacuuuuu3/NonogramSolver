/* Clase que modela un nonograma */
package nonograma;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Modelo de un nonograma.
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class Nonograma{

    public final int filas, columnas; /* Número de filas y columnas */
    protected boolean[][] colores; /* Colores de la matriz */
    public final int[][] restriccionesF, restriccionesC; /* Restricciones de filas y columnas */
    public double aptitud; /* Aptitud del nonograma */
    
    /**
     * Constructor de un nonograma sin colorear.
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
	this.aptitud = this.calculaAptitud();
    }
    
    /**
     * Constructor de un nonograma coloreado. 
     * @param filas - Número de filas del nonograma
     * @param columnas - Número de columnas del nonograma
     * @param colores - Valores de color del nonograma
     * @param restriccionesF - Restricciones de filas
     * @param restriccionesC - Restricciones de columnas
     */
    public Nonograma(int filas, int columnas, boolean[][] colores, int[][] restriccionesF, int[][] restriccionesC){
	this.filas = filas;
	this.columnas = columnas;
	this.colores = colores;
	this.restriccionesF = restriccionesF;
	this.restriccionesC = restriccionesC;
	this.aptitud = this.calculaAptitud();
    }

    /** 
     * Regresa una cadena que representa al Nonograma.
     * @return Cadena que representa al Nonograma.
     */
    @Override
    public String toString(){
       	String regreso = ""; /* Cadena a regresar */
	// regreso += "Tamaño: " + filas + " * " + columnas + "\n";
	// regreso += "Restricciones de filas: \n";
	// for(int i = 0; i < restriccionesF.length; ++i){
	//     for(int j = 0; j < restriccionesF[i].length; ++j)
	// 	regreso += restriccionesF[i][j] + " ";
	//     regreso += "\n"; 
	// }	
	// regreso += "Restricciones de columnas: \n";
	// for(int i = 0; i < restriccionesC.length; ++i){
	//     for(int j = 0; j < restriccionesC[i].length; ++j)
	// 	regreso += restriccionesC[i][j] + " ";
	//     regreso += "\n"; 
	// }	
	for(int i = 0; i < colores.length; ++i){
	    for(int j = 0; j < colores[i].length; ++j)
		if(colores[i][j])
 		    regreso += "|#|";
		else
		    regreso += "|_|";
	    regreso += "\n";
	}
	return regreso;
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

    /**
     * Saca el número de errores en una fila. 
     * @param fila - El número de fila de la cuál veremos los errores
     * @return El número de errores en la fila 'fila'
     */
    public int erroresFila(int fila){
	List<Integer> listaFila = new ArrayList<>(); /* Lista que representa los 
						     * valores de color de la fila. */ 
	int actual = 0; /* Valor de color actual en la fila */
	for(int j = 0; j < columnas; ++j)
	    if(colores[fila][j])
		actual++;
	    else if(actual > 0){
		listaFila.add(actual);
		actual = 0;
	    }
	if(actual != 0)
	    listaFila.add(actual);
 	if(listaFila.size() == 0){
	    int suma = 0;
	    for(int i: restriccionesF[fila])
		suma += i;
	    return suma;
	}
	int min = Integer.MAX_VALUE; /* Mínima distancia entre nuestra solución 
				      * y la solución esperada */
	for(int j = restriccionesF[fila].length; j > -(listaFila.size()); --j){
	    int distancia = 0; /* Distancia entre la solución recorrida j lugares
				* y las restricciones. */
	    for(int i = 0; i < listaFila.size(); i++){
		int indice = i+j; /* El índice de restricción a sacar */
		int restriccion = (indice >= restriccionesF[fila].length || indice < 0) ? 0 : restriccionesF[fila][indice]; 
		distancia += Math.abs(listaFila.get(i)-restriccion);
	    }
	    if(distancia < min)
		min = distancia;
	}
	return min;
    }

    /**
     * Saca los errores en la columna dada 
     * @param columna - El número de columna de la cuál veremos los errores
     * @return El número de errores en la columna 'columna'
     */
    public int erroresColumna(int columna){
	List<Integer> listaColumna = new ArrayList<>(); /* Lista que representa los 
							 * valores de color de la columna. */ 
	int actual = 0; /* Valor de color actual en la columna */
	for(int j = 0; j < filas; ++j)
	    if(colores[j][columna])
		actual++;
	    else if(actual > 0){
		listaColumna.add(actual);
		actual = 0;
	    }
	if(actual != 0)
	    listaColumna.add(actual);
 	if(listaColumna.size() == 0){
	    int suma = 0;
	    for(int i: restriccionesC[columna])
		suma += i;
	    return suma;
	}
	int min = Integer.MAX_VALUE; /* Mínima distancia entre nuestra solución 
				      * y la solución esperada */
	for(int j = restriccionesC[columna].length; j > -(listaColumna.size()); --j){
	    int distancia = 0; /* Distancia entre la solución recorrida j lugares
				* y las restricciones. */
	    for(int i = 0; i < listaColumna.size(); i++){
		int indice = i+j; /* El índice de restricción a sacar */
		int restriccion = (indice >= restriccionesC[columna].length || indice < 0) ? 0 : restriccionesC[columna][indice]; 
		distancia += Math.abs(listaColumna.get(i)-restriccion);
	    }
	    if(distancia < min)
		min = distancia;
	}
	return min;
    }


    /**
     * Calcula la aptitud de un Nonograma.
     * aptitud = 1-(errores/casillas)
     * @return La aptitud del nonograma para utilizar una heurística de resolución.
     */
    public double calculaAptitud(){
	int errores = 0; /* Número de errores en el nonograma */
	for(int i = 0; i < filas; ++i)
	    errores += erroresFila(i);
	for(int j = 0; j < columnas; ++j)
	    errores += erroresColumna(j);
	return 1 - ((double)errores/(filas*columnas));
    }

    /**
     * Getter de la aptitud.
     * @return La aptitud.
     */
    public double getAptitud(){
	return this.aptitud;
    }

    /**
     * Nos dice si una fila es válida de acuerdo a sus restricciones.
     * @param i - El número de fila que queremos verificar
     * @return Si la fila es válida de acuerdo a las restricciones
     */
    public boolean filaValida(int i){
	return erroresFila(i) == 0;
    }

    /**
     * Nos dice si una columna es válida de acuerdo a sus restricciones.
     * @param j - El número de columna a verificar
     * @return Si la columna es válida de acuerdo a las restricciones
     */
    public boolean columnaValida(int j){
	return erroresColumna(j) == 0;
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
