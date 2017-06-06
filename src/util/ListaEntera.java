package util;
import java.util.ArrayList;

/**
 * Clase que representa una lista de enteros con la cualidad de que
 * la operación get regresa 0 por default.
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class ListaEntera extends ArrayList<Integer>{

    /**
     * Nueva versión de get que ahora regresa 0 cuando el índice es
     * inválido.
     * @param index El índice a obtener
     * @return El index-ésimo elemento si existe y 0 en otro caso.
     */
    @Override
    public Integer get(int index){
	try{
	    return super.get(index);
	}catch (IndexOutOfBoundsException e){
	    return 0;
	}
    }
}
