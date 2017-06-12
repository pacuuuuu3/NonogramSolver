package nonograma.test;

import nonograma.Nonograma;
import java.util.List;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link Nonograma}.
 * @author Victor Zamora Gutierrez
 * @version 1.0
 */
public class TestNonograma{

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    private Nonograma n, n2, n3;

    /**
     * Construímos un Nonograma */
    public TestNonograma(){
	int[][] resF = {{1}, {1}};
      	n = new Nonograma(2, 2, resF, resF);
	int[][] resF2 ={{1, 1}, {2}, {0}};
	int[][] resC2 = {{2},{1},{1}};
	n2 = new Nonograma(3, 3, resF2, resC2);	
	int[][] filas = {{2}, {1, 4}, {2, 2}, {3, 2}, {2, 2}, {4, 2}, {4}, {3}};
	int[][] columnas = {{1}, {2, 1}, {5, 1}, {4}, {2, 3}, {4, 1}, {2, 4}, {1, 2}};
	boolean[][] colores= {{false, false, true, true, false, false, false, false}, {false, false, false, true, false, false, false, false},
		       {true, true, false, true, true, false, false, false}, {false, false, false, true, true, false, false, false},
		       {false, false, false, false, true, true, true, false}, {false, false, false, false, true, true, true, true},
		       {false, false, false, false, true, true, true, true}, {false, false, false, false, true, true, true, false}};
	Nonograma n3 = new Nonograma(8, 8, colores, filas, columnas);
    }
    
    /**
     * Prueba unitaria para {@link Nonograma#filaValida}.
     */
    @Test public void testFilaValida(){
	Assert.assertFalse(n.filaValida(0));
	Assert.assertFalse(n.filaValida(1));
	n.colorea(0, 0);
	n.colorea(1, 1);
	Assert.assertTrue(n.filaValida(0));
	Assert.assertTrue(n.filaValida(1));
	Assert.assertFalse(n2.filaValida(0));
	Assert.assertFalse(n2.filaValida(1));
	Assert.assertTrue(n2.filaValida(2));
	n2.colorea(0, 0);
	n2.colorea(0, 2);
	n2.colorea(1, 0);
	n2.colorea(1, 1);
	Assert.assertTrue(n2.filaValida(0));
	Assert.assertTrue(n2.filaValida(1));
	Assert.assertTrue(n2.filaValida(2));
    }

    /**
     * Prueba unitaria para {@link Nonograma#erroresFila}.
     */
    @Test public void testErroresFila(){
	Assert.assertTrue(n.erroresFila(0) == 1);
	Assert.assertTrue(n.erroresFila(1) == 1);
	n.colorea(0, 0);
	n.colorea(1, 1);
	Assert.assertTrue(n.erroresFila(0) == 0);
	Assert.assertTrue(n.erroresFila(1) == 0);
	Assert.assertTrue(n2.erroresFila(0) == 2);
	Assert.assertTrue(n2.erroresFila(1) == 2);
	Assert.assertTrue(n2.erroresFila(2) == 0);
	n2.colorea(0, 0);
	n2.colorea(0, 2);
	n2.colorea(1, 0);
	n2.colorea(1, 1);
	Assert.assertTrue(n2.erroresFila(0) == 0);
	Assert.assertTrue(n2.erroresFila(1) == 0);
	Assert.assertTrue(n2.erroresFila(2) == 0);
	int[][] filas = {{2}, {1, 4}, {2, 2}, {3, 2}, {2, 2}, {4, 2}, {4}, {3}};
	int[][] columnas = {{1}, {2, 1}, {5, 1}, {4}, {2, 3}, {4, 1}, {2, 4}, {1, 2}};
	boolean[][] colores= {{false, false, true, true, false, false, false, false}, {false, false, false, true, false, false, false, false},
		       {true, true, false, true, true, false, false, false}, {false, false, false, true, true, false, false, false},
		       {false, false, false, false, true, true, true, false}, {false, false, false, false, true, true, true, true},
		       {false, false, false, false, true, true, true, true}, {false, false, false, false, true, true, true, false}};
	Nonograma n3 = new Nonograma(8, 8, colores, filas, columnas);
	Assert.assertTrue(n3.erroresFila(0) == 0);
	Assert.assertTrue(n3.erroresFila(1) == 4);
	Assert.assertTrue(n3.erroresFila(2) == 0);
	Assert.assertTrue(n3.erroresFila(3) == 3);
	Assert.assertTrue(n3.erroresFila(4) == 3);
	Assert.assertTrue(n3.erroresFila(5) == 2);
	Assert.assertTrue(n3.erroresFila(6) == 0);
	Assert.assertTrue(n3.erroresFila(7) == 0);
    }
    
    /**
     * Prueba unitaria para {@link Nonograma#erroresColumna}.
     */
    @Test public void testErroresColumna(){
	Assert.assertTrue(n.erroresColumna(0) == 1);
	Assert.assertTrue(n.erroresColumna(1) == 1);
	n.colorea(0, 0);
	n.colorea(1, 1);
	Assert.assertTrue(n.erroresColumna(0) == 0);
	Assert.assertTrue(n.erroresColumna(1) == 0);
	Assert.assertTrue(n2.erroresColumna(0) == 2);
	Assert.assertTrue(n2.erroresColumna(1) == 1);
	Assert.assertTrue(n2.erroresColumna(2) == 1);
	n2.colorea(0, 0);
	n2.colorea(0, 2);
	n2.colorea(1, 0);
	n2.colorea(1, 1);
	Assert.assertTrue(n2.erroresColumna(0) == 0);
	Assert.assertTrue(n2.erroresColumna(1) == 0);
	Assert.assertTrue(n2.erroresColumna(2) == 0);
	int[][] filas = {{2}, {1, 4}, {2, 2}, {3, 2}, {2, 2}, {4, 2}, {4}, {3}};
	int[][] columnas = {{1}, {2, 1}, {5, 1}, {4}, {2, 3}, {4, 1}, {2, 4}, {1, 2}};
	boolean[][] colores= {{false, false, true, true, false, false, false, false}, {false, false, false, true, false, false, false, false},
		       {true, true, false, true, true, false, false, false}, {false, false, false, true, true, false, false, false},
		       {false, false, false, false, true, true, true, false}, {false, false, false, false, true, true, true, true},
		       {false, false, false, false, true, true, true, true}, {false, false, false, false, true, true, true, false}};
	Nonograma n3 = new Nonograma(8, 8, colores, filas, columnas);
	Assert.assertTrue(n3.erroresColumna(0) == 0);
	Assert.assertTrue(n3.erroresColumna(1) == 2);
	Assert.assertTrue(n3.erroresColumna(2) == 5);
	Assert.assertTrue(n3.erroresColumna(3) == 0);
	Assert.assertTrue(n3.erroresColumna(4) == 5);
	Assert.assertTrue(n3.erroresColumna(5) == 1);
	Assert.assertTrue(n3.erroresColumna(6) == 2);
	Assert.assertTrue(n3.erroresColumna(7) == 1);
    }

    /**
     * Prueba unitaria para {@link Nonograma#calculaAptitud}.
     */
    @Test public void testCalculaAptitud(){
	int[][] resF = {{1, 1}, {2}, {2}};
	int[][] resC = {{2}, {2}, {1, 1}};
	n = new Nonograma(3, 3, resF, resC);
	n.colorea(0, 0);
	n.colorea(1, 0);
	n.colorea(1, 1);
	n.colorea(2, 2);
	Assert.assertTrue(n.calculaAptitud() == 1 - (4.0/9));
	n.colorea(0, 2);
	n.colorea(2, 1);
	Assert.assertTrue(n.calculaAptitud() == 1.0);
    }

    
    /**
     * Prueba unitaria para {@link Nonograma#columnaValida}.
     */
    @Test public void testColumnaValida(){
	int[][] resF = {{1}, {1}};
      	n = new Nonograma(2, 2, resF, resF);
	Assert.assertFalse(n.columnaValida(0));
	Assert.assertFalse(n.columnaValida(1));
	n.colorea(0, 0);
	n.colorea(1, 1);
	Assert.assertTrue(n.columnaValida(0));
	Assert.assertTrue(n.columnaValida(1));
	int[][] resF2 ={{1, 1}, {2}, {0}};
	int[][] resC2 = {{2},{1},{1}};
	n2 = new Nonograma(3, 3, resF2, resC2);
	Assert.assertFalse(n2.columnaValida(0));
	Assert.assertFalse(n2.columnaValida(1));
	Assert.assertFalse(n2.columnaValida(2));
	n2.colorea(0, 0);
	n2.colorea(0, 2);
	n2.colorea(1, 0);
	n2.colorea(1, 1);
	Assert.assertTrue(n2.columnaValida(0));
	Assert.assertTrue(n2.columnaValida(1));
	Assert.assertTrue(n2.columnaValida(2));
    }
    
    /**
     * Prueba unitaria para {@link Nonograma#solucionValida}.
     */
    @Test public void testSolucionValida(){
	int[][] resF = {{1}, {1}};
	n = new Nonograma(2, 2, resF, resF);
	n.colorea(0, 0);
	n.colorea(1, 1);
	Assert.assertTrue(n.solucionValida());
	int[][] resF2 ={{1, 1}, {2}, {0}};
	int[][] resC2 = {{2},{1},{1}};
	n2 = new Nonograma(3, 3, resF2, resC2);
       	n2.colorea(0, 0);
	n2.colorea(0, 2);
	n2.colorea(1, 0);
	n2.colorea(1, 1);
	Assert.assertTrue(n2.solucionValida());
    }
}
