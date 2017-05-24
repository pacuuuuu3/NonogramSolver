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

    private Nonograma n;

    /**
     * Construímos un Nonograma */
    public TestNonograma(){
	int[][] resF = {{1, -1}, {1, -1}};
      	n = new Nonograma(2, 2, resF, resF);
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
    }
    
    /**
     * Prueba unitaria para {@link Nonograma#solucionValida}.
     */
    @Test public void testSolucionValida(){
    }
}
