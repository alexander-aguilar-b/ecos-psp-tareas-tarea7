/******************************************************************/
/* Autor: Edgar Alexander Aguilar Bolaños
/* Fecha de Creación: 07/03/2017
/* Propósito: Clase encargada de las pruebas de la clase de calculos estadisticos
/* Notas especiales:
/******************************************************************/
import org.junit.Test;
import uniandes.ecos.psp.CalculoEstadistico;
import uniandes.ecos.psp.ModeloParejaXY;
import uniandes.ecos.psp.ModeloRegresion;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Clase encargada de las pruebas de la clase de calculos estadisticos
 * Created by edgaguil on 8/03/2017.
 */
public class TestCalculoEstadistico {

    /**
     * Metodo que realiza la prueba del calculo de la media
     */
    @Test
    public void testMedia() {

        LinkedList<Double> listaDatos = new LinkedList<Double>();

        listaDatos.add((double)130);
        listaDatos.add((double)650);
        listaDatos.add((double)99);
        listaDatos.add((double)150);
        listaDatos.add((double)128);
        listaDatos.add((double)302);
        listaDatos.add((double)95);
        listaDatos.add((double)945);
        listaDatos.add((double)368);
        listaDatos.add((double)961);

         double media = CalculoEstadistico.calcularMedia(listaDatos);
         assertEquals(382.8, media, 0.01);
    }

    /**
     * Metodo que realiza la prueba de la sumatoria de una lista de numeros reales
     */
    @Test
    public void testCalcularSumatoria() {

        LinkedList<Double> listaDatos = new LinkedList<Double>();

        listaDatos.add((double)130);
        listaDatos.add((double)650);
        listaDatos.add((double)99);
        listaDatos.add((double)150);
        listaDatos.add((double)128);
        listaDatos.add((double)302);
        listaDatos.add((double)95);
        listaDatos.add((double)945);
        listaDatos.add((double)368);
        listaDatos.add((double)961);

        double sumatoria = CalculoEstadistico.calcularSumatoria(listaDatos);
        assertEquals(3828, sumatoria, 0.01);
    }

    /**
     * Metodo que realiza la prueba de la sumatoria al cuadrado de una lista de numeros reales
     */
    @Test
    public void testCalcularSumatoriaCuadrado() {

        LinkedList<Double> listaDatos = new LinkedList<Double>();

        listaDatos.add((double)130);
        listaDatos.add((double)650);
        listaDatos.add((double)99);
        listaDatos.add((double)150);
        listaDatos.add((double)128);
        listaDatos.add((double)302);
        listaDatos.add((double)95);
        listaDatos.add((double)945);
        listaDatos.add((double)368);
        listaDatos.add((double)961);

        double sumatoria = CalculoEstadistico.calcularSumatoriaCuadrado(listaDatos);
        assertEquals(2540284, sumatoria, 0.01);
    }

    /**
     * Metodo que realiza la prueba del calculo de la regresion lineal
     */
    @Test
    public void testCalcularValoresRegresion() {

        LinkedList<ModeloParejaXY> listaDatos = new LinkedList<ModeloParejaXY>();
        ModeloRegresion modeloRegresionEntrada = new ModeloRegresion();
        modeloRegresionEntrada.setDatosEntrada(listaDatos);
        modeloRegresionEntrada.setValorEstimadoXk(386);


        listaDatos.add(new ModeloParejaXY (130, 186));
        listaDatos.add(new ModeloParejaXY (650, 699));
        listaDatos.add(new ModeloParejaXY (99, 132));
        listaDatos.add(new ModeloParejaXY (150, 272));
        listaDatos.add(new ModeloParejaXY (128, 291));
        listaDatos.add(new ModeloParejaXY (302, 331));
        listaDatos.add(new ModeloParejaXY (95, 199));
        listaDatos.add(new ModeloParejaXY (945, 1890));
        listaDatos.add(new ModeloParejaXY (368, 788));
        listaDatos.add(new ModeloParejaXY (961, 1601));

        ModeloRegresion modeloRegresionSalida = CalculoEstadistico.calcularValoresRegresion(modeloRegresionEntrada);

        assertEquals(-22.552411930643984, modeloRegresionSalida.getParametroRegresionB0(), 0.01);
        assertEquals(1.7279322294608912, modeloRegresionSalida.getParametroRegresionB1(), 0.01);
        assertEquals(0.9544966011998116, modeloRegresionSalida.getCoeficienteRegresionRxy(), 0.01);
        assertEquals(0.9110637617019922, modeloRegresionSalida.getCoeficienteRegresionR2(), 0.01);
        assertEquals(644.4294286412601, modeloRegresionSalida.getPrediccionYk(), 0.01);
    }

    /**
     * Metodo que realiza la prueba del calculo de los parametros de regresion, significancia  y rango
     */
    @Test
    public void testCalcularValoresProbe()
    {
        LinkedList<ModeloParejaXY> listaDatos = new LinkedList<ModeloParejaXY>();
        ModeloRegresion modeloRegresionEntrada = new ModeloRegresion();
        modeloRegresionEntrada.setDatosEntrada(listaDatos);
        modeloRegresionEntrada.setValorEstimadoXk(386);

        listaDatos.add(new ModeloParejaXY (130, 186));
        listaDatos.add(new ModeloParejaXY (650, 699));
        listaDatos.add(new ModeloParejaXY (99, 132));
        listaDatos.add(new ModeloParejaXY (150, 272));
        listaDatos.add(new ModeloParejaXY (128, 291));
        listaDatos.add(new ModeloParejaXY (302, 331));
        listaDatos.add(new ModeloParejaXY (95, 199));
        listaDatos.add(new ModeloParejaXY (945, 1890));
        listaDatos.add(new ModeloParejaXY (368, 788));
        listaDatos.add(new ModeloParejaXY (961, 1601));

        ModeloRegresion modeloRegresionSalida = CalculoEstadistico.calcularValoresRegresion(modeloRegresionEntrada);

        double valorSignificancia = CalculoEstadistico.calcularSignificancia(listaDatos, 386);
        double valorRango = CalculoEstadistico.calcularRango70(listaDatos, 386);

        assertEquals(-22.552411930643984, modeloRegresionSalida.getParametroRegresionB0(), 0.01);
        assertEquals(1.7279322294608912, modeloRegresionSalida.getParametroRegresionB1(), 0.01);
        assertEquals(0.9544966011998116, modeloRegresionSalida.getCoeficienteRegresionRxy(), 0.01);
        assertEquals(0.9110637617019922, modeloRegresionSalida.getCoeficienteRegresionR2(), 0.01);
        assertEquals(644.4294286412601, modeloRegresionSalida.getPrediccionYk(), 0.01);

        assertEquals(0.00000177517, valorSignificancia, 0.001);
        assertEquals(230.0017197, valorRango, 0.002);
    }
}
