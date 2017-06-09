/* Algoritmo genético que resuelve un Nonograma */
package nonograma;
import java.util.Random;

/**
 * Clase con un algoritmo genético para resolver un Nonograma 
 * @author Víctor Zamora Gutiérrez
 * @version 1.0
 */
public class GeneticoN{

    private final long semilla;
    private final Random random; /* Generador de números aleatorios. */
    private final int individuos; /* Número de individuos */
    private final double probamutacion; /* Probabilidad de que un hijo mute */
    private final int filas, columnas; /* Número de filas y columnas */
    private final int[][] restriccionesF, restriccionesC; /* Restricciones de filas y columnas */
    private Nonograma[] poblacion; /* Población de Nonogramas */
    private double sumaAptitudes; /* Suma de las aptitudes */
    private Nonograma elite; /* Solo voy a mantener uno de élite */
    
    /**
     * Constructor.
     * @param semilla - Semilla para el generador de números aleatorios.
     * @param individuos - Número de individuos por generación.
     * @param probamutacion - Probabilidad de que el hijo de dos individuos mute.
     * @param filas - Número de filas del nonograma
     * @param columnas - Número de columnas del nonograma
     * @param restriccionesF - Restricciones de filas
     * @param restriccionesC - Restricciones de columnas
     */
    public GeneticoN(long semilla, int individuos,
		     double probamutacion, int filas, int columnas,
		     int[][] restriccionesF, int[][] restriccionesC){
	this.sumaAptitudes = 0;
	this.semilla = semilla;
	this.random = new Random(semilla);
	this.individuos = individuos;
	this.probamutacion = probamutacion;
	this.filas = filas;
	this.columnas = columnas;
	this.restriccionesF = restriccionesF;
	this.restriccionesC = restriccionesC;
	this.poblacion = new Nonograma[individuos];
	this.elite = null;
	/* Llenamos la población */
	for(int i = 0; i < individuos; ++i){
	    boolean[][] colores = new boolean[filas][columnas]; /* Colores del nuevo individuo */
	    for(int j = 0; j < filas; ++j)
		for(int k = 0; k < columnas; ++k)
		    colores[j][k] = random.nextBoolean(); 
	    poblacion[i] = new Nonograma(filas, columnas, colores, restriccionesF, restriccionesC);
	    if(this.elite == null || poblacion[i].getAptitud() > elite.getAptitud())
		elite = poblacion[i];
	    sumaAptitudes += poblacion[i].getAptitud();
	}
    }
    
    /**
     * Reproduce dos Nonogramas del mismo tamaño.
     * Para esto, los parte a la mitad y junta las mitades. 
     * @param n1 - El primer Nonograma con el cuál reproducir.
     * @param n2 - El segundo Nonograma con el cuál reproducir. 
     * @return El hijo de los Nonogramas, potencialmente mutado.
     */
    public Nonograma reproduce(Nonograma n1, Nonograma n2){
	boolean cual = random.nextBoolean(); /* Variable para ver si reproducimos por filas o por columnas */
	boolean[][] nuevosColores = new boolean[filas][columnas]; /* Colores del Nonograma hijo */
	if(cual){
	    /* Reproducimos por filas */
	    for(int i = 0; i < (filas)/2; ++i)
		for(int j = 0; j < columnas; ++j){
		    double rand = random.nextDouble(); /* Número aleatorio */
		    if(rand <= probamutacion)
			nuevosColores[i][j] = !n1.colores[i][j];
		    else
			nuevosColores[i][j] = n1.colores[i][j];
		}
	    for(int i = (filas)/2;i < filas; ++i)
		for(int j = 0; j < columnas; ++j){
		    double rand = random.nextDouble(); /* Número aleatorio */
		    if(rand <= probamutacion)
			nuevosColores[i][j] = !n2.colores[i][j];
		    else
			nuevosColores[i][j] = n2.colores[i][j];
		}
	}else{
	    /* Reproducimos por columnas */
	    for(int i = 0; i < (columnas)/2; ++i)
		for(int j = 0; j < filas; ++j){
		    double rand = random.nextDouble(); /* Número aleatorio */
		    if(rand <= probamutacion)
			nuevosColores[j][i] = !n1.colores[j][i];
		    else
			nuevosColores[j][i] = n1.colores[j][i];
		}
	    for(int i = columnas/2; i < columnas; ++i)
		for(int j = 0; j < filas; ++j){
		    double rand = random.nextDouble(); /* Número aleatorio */
		    if(rand <= probamutacion)
			nuevosColores[j][i] = !n2.colores[j][i];
		    else
			nuevosColores[j][i] = n2.colores[j][i];

		}
	}
	// double rand = random.nextDouble(); /* Número aleatorio */
	// if(rand <= probamutacion){
	//     /* Mutamos */
	//     int fila = random.nextInt(filas); /* Fila a mutar. */
	//     int columna = random.nextInt(columnas); /* Columna a mutar */
	//     nuevosColores[fila][columna] = !nuevosColores[fila][columna];
	// }
	return new Nonograma(filas, columnas, nuevosColores, restriccionesF, restriccionesC);
    }

    /**
     * Regresa un Nonogramas apto para reproducir.
     * @return Un Nonogramas aptos para reproducir.
     */
    public Nonograma ruleta(){
	double aleat = random.nextDouble()*sumaAptitudes; /* Double aleatorio */
	double suma = 0.0; /* Suma de las aptitudes de Nonogramas vistos en la ruleta
			    * cuando esta sea mayor a aleat, elegimos ese Nonograma */
	for(Nonograma n: poblacion){
	    suma += n.getAptitud();
	    if(suma > aleat)
		return n;
	}
	return null;
    }

    /**
     * Avanza una generación.
     */
    public void generacion(){
	double nuevaSumaApt = 0.0; /* Nueva suma de aptitudes */
	Nonograma[] nuevaPoblacion = new Nonograma[individuos]; /* Nueva población tras la mezcla */
	nuevaPoblacion[0] = elite;
	nuevaSumaApt += elite.getAptitud();
	/* Metemos un aleatorio */
	boolean[][] colores = new boolean[filas][columnas]; /* Colores del nuevo individuo */
	for(int j = 0; j < filas; ++j)
	    for(int k = 0; k < columnas; ++k)
		colores[j][k] = random.nextBoolean(); 
	nuevaPoblacion[1] = new Nonograma(filas, columnas, colores, restriccionesF, restriccionesC);
	nuevaSumaApt += nuevaPoblacion[1].getAptitud();
	if(nuevaPoblacion[1].getAptitud() > elite.getAptitud())
	    elite = nuevaPoblacion[1];

	for(int i = 2; i < individuos; ++i){
	    Nonograma hijo = reproduce(ruleta(), ruleta());
	    nuevaPoblacion[i] = hijo;
	    nuevaSumaApt += hijo.getAptitud();
	    if(hijo.getAptitud() > elite.getAptitud()){
		elite = hijo;
	    }
	}
	sumaAptitudes = nuevaSumaApt;
	poblacion = nuevaPoblacion;
    }

    /**
     * Soluciona un Nonograma.
     */
    public static void main(String args[]){
	int[][] filas = {{0}, {4}, {6}, {2, 2}, {2, 2}, {6}, {4}, {2}, {2}, {2}, {0}};
	int[][] columnas = {{0}, {9}, {9}, {2, 2}, {2, 2}, {4}, {4}, {0}};
	// int[][] filas = {{3}, {2}, {3, 1}, {1, 1}, {1, 2}};
	// int[][] columnas = {{1, 1}, {3}, {5}, {1, 1}, {2}};

	GeneticoN s = new GeneticoN(69,69,.007, 11, 8,filas, columnas);
	// System.out.println(s.poblacion[0]);
	// System.out.println(s.poblacion[199]);
	// System.out.println(s.reproduce(s.poblacion[0], s.poblacion[199]));
	
	int generacion = 0;
	while(true){
	    System.out.printf("generación: %d\n", generacion++);
	    System.out.println(s.elite);
	    System.out.println(s.elite.getAptitud());
	    if(s.elite.getAptitud() == 1)
		{
		    System.out.println(s.elite);
		    break;
		}
	    s.generacion();
	}
    }
    
}
