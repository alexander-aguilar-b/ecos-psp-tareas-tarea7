/**
 * Autor: Edgar Alexander Aguilar Bolaños
 * Fecha de Creación: 19/04/2017
 * Propósito: Clase principal del programa PSP6 (Calculo del valor X)
 * Notas especiales:
 * @author  Edgar Alexander Aguilar Bolaños
 * @version 1.0
*/
package uniandes.ecos.psp;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static spark.Spark.*;

/** Clase principal del programa PSP6 (Calculo del valor X)
 * Created by edgaguil on 4/04/2017.
 */
public class PSPProgram7
{
    /**
     * Metodo de inicio del programa
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Integer puerto = System.getenv("PORT") != null ? Integer.valueOf(System.getenv("PORT")) : 4567;
        port(puerto);
        get("/psp5/test1", (req, res) -> calcularValorIntegral(TestPSP5.Test1), new FreeMarkerEngine());
        get("/psp5/test2", (req, res) -> calcularValorIntegral(TestPSP5.Test2), new FreeMarkerEngine());
        get("/psp5/test3", (req, res) -> calcularValorIntegral(TestPSP5.Test3), new FreeMarkerEngine());
        get("/psp6", (req, res) -> calcularValoresX(), new FreeMarkerEngine());
        get("/psp7", (req, res) -> calcularValoresProbe(), new FreeMarkerEngine());
    }

    /**
     * Metodo que recibe ala peticicion del calculo de X
     * @return Vista con los resultados de la busqueda de  los valores de X
     */
    private static ModelAndView calcularValoresX()
    {
        Map<String, Object> atributosSalida = new HashMap<>();

        try
        {
            ControladorCalculo controladorEstadistica = new ControladorCalculo();
            ModeloCalculoIntegral modeloCalculoIntegralTest1 = new ModeloCalculoIntegral(6, 0.2);
            ModeloCalculoIntegral modeloCalculoIntegralTest2 = new ModeloCalculoIntegral(15, 0.45);
            ModeloCalculoIntegral modeloCalculoIntegralTest3 = new ModeloCalculoIntegral(4, 0.495);

            modeloCalculoIntegralTest1 = controladorEstadistica.calcularValorX(modeloCalculoIntegralTest1);
            modeloCalculoIntegralTest2 = controladorEstadistica.calcularValorX(modeloCalculoIntegralTest2);
            modeloCalculoIntegralTest3 = controladorEstadistica.calcularValorX(modeloCalculoIntegralTest3);

            atributosSalida.put("valorPTest1", String.format("%1$,.3f", modeloCalculoIntegralTest1.getValorEsperado()));
            atributosSalida.put("valorDOFTest1", modeloCalculoIntegralTest1.getGradosLibertad());
            atributosSalida.put("valorXEsperadoTest1", String.format("%1$,.5f", 0.55338));
            atributosSalida.put("valorXCalculadoTest1", String.format("%1$,.10f", modeloCalculoIntegralTest1.getValorX()));

            atributosSalida.put("valorPTest2", String.format("%1$,.3f", modeloCalculoIntegralTest2.getValorEsperado()));
            atributosSalida.put("valorDOFTest2", modeloCalculoIntegralTest2.getGradosLibertad());
            atributosSalida.put("valorXEsperadoTest2", String.format("%1$,.5f", 1.75305));
            atributosSalida.put("valorXCalculadoTest2", String.format("%1$,.10f", modeloCalculoIntegralTest2.getValorX()));

            atributosSalida.put("valorPTest3", String.format("%1$,.3f", modeloCalculoIntegralTest3.getValorEsperado()));
            atributosSalida.put("valorDOFTest3", modeloCalculoIntegralTest3.getGradosLibertad());
            atributosSalida.put("valorXEsperadoTest3", String.format("%1$,.5f", 4.60409));
            atributosSalida.put("valorXCalculadoTest3", String.format("%1$,.10f", modeloCalculoIntegralTest3.getValorX()));
        }
        catch (Exception e)
        {
            atributosSalida.put("message", "Se presento un error intente mas tarde." + e);
            return new ModelAndView(atributosSalida, "error.ftl");
        }

        return new ModelAndView(atributosSalida, "psp6.ftl");
    }

    /**
     * Metodo encargado de mostrar los resultados de los calculos
     * @param testPSP5 Test que se va a solicitar y desplegar los resultados
     * @return Vista que contiene los resultados de los calculos para el test solicitado
     */
    private static ModelAndView calcularValorIntegral(TestPSP5 testPSP5)
    {
        Map<String, Object> atributosSalida = new HashMap<>();

        try
        {
            ControladorCalculo controladorEstadistica = new ControladorCalculo();
            ModeloCalculoIntegral modeloCalculoIntegral = controladorEstadistica.calcularValorIntegral(testPSP5);

            if (modeloCalculoIntegral != null)
            {
                String formatoValorX = "0 a x = %1$,.4f";
                atributosSalida.put("numeroTest", testPSP5.toString());
                atributosSalida.put("valorX", String.format(formatoValorX, modeloCalculoIntegral.getValorX()));
                atributosSalida.put("gradosLibertad", modeloCalculoIntegral.getGradosLibertad());
                atributosSalida.put("valorEsperado", String.format("%1$,.5f", modeloCalculoIntegral.getValorEsperado()));
                atributosSalida.put("valorActual", String.format("%1$,.10f", modeloCalculoIntegral.getResultadoCalculo()));
            }

        }
        catch (Exception e)
        {
            atributosSalida.put("message", "Se presento un error intente mas tarde." + e);
            return new ModelAndView(atributosSalida, "error.ftl");
        }

        return new ModelAndView(atributosSalida, "psp5.ftl");
    }


    /**
     * Metodo encargado de cargar los atributos de salida para el ejercinio 7 de psp
     * @param atributosSalida Objeto que contiene la coleccion de atributos de salida
     * @param modeloProbe Objeto que contiene los datos que se vana a cvargar en los atributos de salida
     * @param nombreTest Identificador del test para el cual se estan cargando los resultados
     */
    private static void cargarAtributosSalida(Map<String, Object> atributosSalida, ModeloProbe modeloProbe, String nombreTest)
    {

        atributosSalida.put("valorRxy" + nombreTest, String.format("%1$,.10f", modeloProbe.getModeloRegresion().getCoeficienteRegresionRxy()));
        atributosSalida.put("valorR2" +  nombreTest, String.format("%1$,.10f", modeloProbe.getModeloRegresion().getCoeficienteRegresionR2()));
        atributosSalida.put("valorSignificancia" + nombreTest, String.format("%1$,.10f", modeloProbe.getValorSignificancia()));
        atributosSalida.put("valorB0" + nombreTest, String.format("%1$,.10f", modeloProbe.getModeloRegresion().getParametroRegresionB0()));
        atributosSalida.put("valorB1" + nombreTest, String.format("%1$,.10f", modeloProbe.getModeloRegresion().getParametroRegresionB1()));
        atributosSalida.put("valorYk" + nombreTest, String.format("%1$,.10f", modeloProbe.getModeloRegresion().getPrediccionYk()));
        atributosSalida.put("valorRango" + nombreTest, String.format("%1$,.10f", modeloProbe.getValorRango()));
        atributosSalida.put("valorUPI" + nombreTest, String.format("%1$,.10f", modeloProbe.getValorUPI70()));
        atributosSalida.put("valorLPI" + nombreTest, String.format("%1$,.10f", modeloProbe.getValorLPI70()));
    }

    /**
     * Metodo que realiza el calculo de los valores de probe para las cuatro puebas de psp 7
     * @return Vista con los resultados de los calculos
     */
    private static ModelAndView calcularValoresProbe()
    {
        Map<String, Object> atributosSalida = new HashMap<>();

        try
        {
            LinkedList<ModeloParejaXY> datosTest1 = new LinkedList<ModeloParejaXY>();
            datosTest1.add(new ModeloParejaXY (130, 186));
            datosTest1.add(new ModeloParejaXY (650, 699));
            datosTest1.add(new ModeloParejaXY (99, 132));
            datosTest1.add(new ModeloParejaXY (150, 272));
            datosTest1.add(new ModeloParejaXY (128, 291));
            datosTest1.add(new ModeloParejaXY (302, 331));
            datosTest1.add(new ModeloParejaXY (95, 199));
            datosTest1.add(new ModeloParejaXY (945, 1890));
            datosTest1.add(new ModeloParejaXY (368, 788));
            datosTest1.add(new ModeloParejaXY (961, 1601));

            ModeloRegresion modeloRegresionTest1 = new ModeloRegresion();
            modeloRegresionTest1.setDatosEntrada(datosTest1);
            modeloRegresionTest1.setValorEstimadoXk(386);

            ControladorCalculo controladorCalculo = new ControladorCalculo();
            ModeloProbe resultadosTest1 = controladorCalculo.calcularModeloProbe(modeloRegresionTest1);

            cargarAtributosSalida(atributosSalida, resultadosTest1, "Test1");

            LinkedList<ModeloParejaXY> datosTest2 = new LinkedList<ModeloParejaXY>();
            datosTest2.add(new ModeloParejaXY (130, 15.0));
            datosTest2.add(new ModeloParejaXY (650, 69.9));
            datosTest2.add(new ModeloParejaXY (99, 6.5));
            datosTest2.add(new ModeloParejaXY (150, 22.4));
            datosTest2.add(new ModeloParejaXY (128, 28.4));
            datosTest2.add(new ModeloParejaXY (302, 65.9));
            datosTest2.add(new ModeloParejaXY (95, 19.4));
            datosTest2.add(new ModeloParejaXY (945, 198.7));
            datosTest2.add(new ModeloParejaXY (368, 38.8));
            datosTest2.add(new ModeloParejaXY (961, 138.2));

            ModeloRegresion modeloRegresionTest2 = new ModeloRegresion();
            modeloRegresionTest2.setDatosEntrada(datosTest2);
            modeloRegresionTest2.setValorEstimadoXk(386);

            ModeloProbe resultadosTest2 = controladorCalculo.calcularModeloProbe(modeloRegresionTest2);

            cargarAtributosSalida(atributosSalida, resultadosTest2, "Test2");

            LinkedList<ModeloParejaXY> datosTest3 = new LinkedList<ModeloParejaXY>();
            datosTest3.add(new ModeloParejaXY (155, 304));
            datosTest3.add(new ModeloParejaXY (172, 138));
            datosTest3.add(new ModeloParejaXY (244, 303));
            datosTest3.add(new ModeloParejaXY (217, 136));

            ModeloRegresion modeloRegresionTest3 = new ModeloRegresion();
            modeloRegresionTest3.setDatosEntrada(datosTest3);
            modeloRegresionTest3.setValorEstimadoXk(189);

            ModeloProbe resultadosTest3 = controladorCalculo.calcularModeloProbe(modeloRegresionTest3);

            cargarAtributosSalida(atributosSalida, resultadosTest3, "Test3");

            LinkedList<ModeloParejaXY> datosTest4 = new LinkedList<ModeloParejaXY>();
            datosTest4.add(new ModeloParejaXY (155, 7.78));
            datosTest4.add(new ModeloParejaXY (172, 6.15));
            datosTest4.add(new ModeloParejaXY (244, 9.13));
            datosTest4.add(new ModeloParejaXY (217, 6.98));

            ModeloRegresion modeloRegresionTest4 = new ModeloRegresion();
            modeloRegresionTest4.setDatosEntrada(datosTest4);
            modeloRegresionTest4.setValorEstimadoXk(189);

            ModeloProbe resultadosTest4 = controladorCalculo.calcularModeloProbe(modeloRegresionTest4);

            cargarAtributosSalida(atributosSalida, resultadosTest4, "Test4");

        }
        catch (Exception e)
        {
            atributosSalida.put("message", "Se presento un error intente mas tarde." + e);
            return new ModelAndView(atributosSalida, "error.ftl");
        }

        return new ModelAndView(atributosSalida, "psp7.ftl");
    }
}
