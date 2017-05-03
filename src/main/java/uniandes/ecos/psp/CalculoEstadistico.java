/******************************************************************/
/* Autor: Edgar Alexander Aguilar Bolaños
/* Fecha de Creación: 07/03/2017
/* Propósito: Clase encargada de realizar calculos estadisticos
/* Notas especiales:
/******************************************************************/

package uniandes.ecos.psp;

/**
 * Created by edgaguil on 3/05/2017.
 */
import java.util.LinkedList;
import java.util.List;

/**
 * Clase encargada de realizar calculos estadisticos
 * @author edgaguil
 */
public class CalculoEstadistico
{
    /***
     * Metodo que realiza el calculo de la media a partir del listado de datos de entrada
     * @param listadoDatos Listado de numeros enteros
     * @return Valor de la media del listado de numeros ingresados
     */
    public static double calcularMedia(LinkedList<Double> listadoDatos)
    {
        double sumatoria = 0;
        double media = 0;

        for(double numero : listadoDatos)
        {
            sumatoria += numero;
        }

        if (listadoDatos.size() > 0) {
            media = sumatoria / listadoDatos.size();
        }

        return media;
    }

    /**
     * Metodo que realiza el calculo de la desviación estandar a partir del listado de datos de entrada
     * @param listadoDatos Listado de numeros enteros
     * @return Valor de la desviacion estandar del listado de numeros ingresados
     */
    public static double calcularDesviacionEstandar(LinkedList<Double> listadoDatos)
    {
        double desviacionEstandar = 0;
        double media = calcularMedia(listadoDatos);
        double sumatoria = 0;


        if (listadoDatos.size() > 0)
        {
            for(double numero : listadoDatos)
            {
                sumatoria += Math.pow(numero - media, 2);
            }
            desviacionEstandar = Math.sqrt(sumatoria / (listadoDatos.size() - 1));
        }

        return desviacionEstandar;
    }


    /**
     * Metodo que realiza el calcuo de la sumatoria sobre un listado de datos
     * @param listaDatos Listado de numeros reales
     * @return Valor de la sumatoria
     */
    public static double calcularSumatoria(LinkedList<Double> listaDatos)
    {
        double totalSumatoria = 0;

        if (listaDatos != null)
        {
            for (double numero: listaDatos)
            {
                totalSumatoria += numero;
            }
        }

        return totalSumatoria;
    }

    /**
     * Metodo que realiza el calcuo de la sumatoria del producto de una lista de parejas de datos
     * @param listadoParejaDatos Listado de parejas de  numeros reales
     * @return Valor de la sumatoria
     */
    public  static double calcularSumatoriaProductoParejaDatos(List<ModeloParejaXY> listadoParejaDatos)
    {
        double totalSumatoria = 0;

        if (listadoParejaDatos != null)
        {
            for (ModeloParejaXY parejaDatos: listadoParejaDatos)
            {
                totalSumatoria += parejaDatos.getDatoX() * parejaDatos.getDatoY();
            }
        }

        return totalSumatoria;
    }


    /**
     * Metodo que realiza el calcuo de la sumatoria del cuadrado de una lista de numeros reales
     * @param listaDatos Listado de numeros reales
     * @return Valor de la sumatoria
     */
    public static double calcularSumatoriaCuadrado(LinkedList<Double> listaDatos)
    {
        double totalSumatoria = 0;

        if (listaDatos != null)
        {
            for (double numero: listaDatos)
            {
                totalSumatoria += numero * numero;
            }
        }

        return totalSumatoria;
    }

    /**
     * Metodo que realiza el calculo de los parametros de regresion y los coeficientes de regresion
     * @param modeloRegresion Modelo que contiene el listado de datos y un punto estimado
     * @return Modelo que contiene los parametros de regresion y los coeficientes de regresion
     */
    public static ModeloRegresion calcularValoresRegresion(ModeloRegresion modeloRegresion)
    {
        if (modeloRegresion != null)
        {
            LinkedList<ModeloParejaXY> listadoParejaDatos = modeloRegresion.getDatosEntrada();

            if (listadoParejaDatos != null)
            {
                LinkedList<Double> listadoDatosX = new LinkedList<Double>();
                LinkedList<Double> listadoDatosY = new LinkedList<Double>();

                for (ModeloParejaXY parejaDatoXY: listadoParejaDatos)
                {
                    listadoDatosX.add(parejaDatoXY.getDatoX());
                    listadoDatosY.add(parejaDatoXY.getDatoY());
                }

                int numeroDatos = listadoParejaDatos.size();
                double mediaX = calcularMedia(listadoDatosX);
                double mediaY = calcularMedia(listadoDatosY);

                double sumatoriaProductoXY =calcularSumatoriaProductoParejaDatos(listadoParejaDatos);
                double sumatoriaX = calcularSumatoria(listadoDatosX);
                double sumatoriaY = calcularSumatoria(listadoDatosY);
                double sumatoriaCuadradoX = calcularSumatoriaCuadrado(listadoDatosX);
                double sumatoriaCuadradoY = calcularSumatoriaCuadrado(listadoDatosY);

                double denominadorB1 = (sumatoriaCuadradoX - (numeroDatos*Math.pow(mediaX,2)));

                double parametroB1 = denominadorB1 != 0 ?  ((sumatoriaProductoXY - (numeroDatos*mediaX*mediaY)) / denominadorB1) : 0;

                double denominadorCoeficienteRegresionRxy = (Math.sqrt(( (numeroDatos* sumatoriaCuadradoX) - Math.pow(sumatoriaX, 2)) * ((numeroDatos* sumatoriaCuadradoY) - Math.pow(sumatoriaY, 2)) ) );

                double coeficienteRegresionRxy = denominadorCoeficienteRegresionRxy != 0 ?  (((numeroDatos* sumatoriaProductoXY) - (sumatoriaX*sumatoriaY)) / denominadorCoeficienteRegresionRxy) : 0;

                double coeficienteRegresionR2 = Math.pow(coeficienteRegresionRxy, 2);

                double parametroB0 = mediaY - (parametroB1*mediaX);

                double prediccionYk = parametroB0 + (parametroB1* modeloRegresion.getValorEstimadoXk());

                modeloRegresion.setCoeficienteRegresionRxy(coeficienteRegresionRxy);
                modeloRegresion.setCoeficienteRegresionR2(coeficienteRegresionR2);
                modeloRegresion.setParametroRegresionB0(parametroB0);
                modeloRegresion.setParametroRegresionB1(parametroB1);
                modeloRegresion.setPrediccionYk(prediccionYk);
            }
        }

        return modeloRegresion;
    }

    /***
     * Metodo que realiza el calculo de la significancia de un conjunto de datos
     * @param datosEntrada Listado que contiene el conjunto de datos
     * @param valorEstimadoXk Valor estimadoXk
     * @return Valor de la significancia de la correlacion entre los datos
     */
    public static double calcularSignificancia(LinkedList<ModeloParejaXY> datosEntrada, double valorEstimadoXk)
    {
        ModeloRegresion modeloRegresionEntrada= new ModeloRegresion();
        modeloRegresionEntrada.setValorEstimadoXk(valorEstimadoXk);
        modeloRegresionEntrada.setDatosEntrada(datosEntrada);
        ModeloRegresion modeloRegresionSalida = CalculoEstadistico.calcularValoresRegresion(modeloRegresionEntrada);

        double numeradorX = modeloRegresionSalida.getCoeficienteRegresionRxy() * Math.sqrt(datosEntrada.size() - 2);
        double denominadorX = Math.sqrt(1 - Math.pow(modeloRegresionSalida.getCoeficienteRegresionRxy(), 2));

        double valorX = denominadorX != 0 ? numeradorX / denominadorX : 0.0;

        CalculoIntegral calculoIntegral = new CalculoIntegral();

        ModeloCalculoIntegral modeloCalculoIntegral = new ModeloCalculoIntegral();

        modeloCalculoIntegral.setNumeroSegmentos(40);
        modeloCalculoIntegral.setGradosLibertad(datosEntrada.size() - 2);
        modeloCalculoIntegral.setValorX(valorX);

        double valorIntegralX =  calculoIntegral.calcularValorIntegralIndividual(modeloCalculoIntegral);

        double significancia = 1.0 - (2.0 * valorIntegralX);

        return significancia;
    }


    /***
     * Metodo que realiza el claculo del rango del 70 porciento para el listado de pareja de datos X y Y
     * @param datosEntrada Objeto que contiene los datos de entrada
     * @param valorEstimadoXk Valor estimadoXk
     * @return Valor del rango 70 porciento
     */
    public static double calcularRango70(LinkedList<ModeloParejaXY> datosEntrada, double valorEstimadoXk)
    {
        double sumatoriaCuadradoXiXAvg = 0;
        double sumatoriaCalculoDesviacion = 0;
        double desviacionEstandar = 0;
        double rango = 0;
        double valorRaiz = 0;
        double valorMediaX = 0;

        LinkedList<Double> listadoDatosX = new LinkedList<Double>();
        LinkedList<Double> listadoDatosY = new LinkedList<Double>();

        ModeloRegresion modeloRegresionEntrada = new ModeloRegresion();

        modeloRegresionEntrada.setDatosEntrada(datosEntrada);
        modeloRegresionEntrada.setValorEstimadoXk(valorEstimadoXk);

        ModeloRegresion modeloRegresion = CalculoEstadistico.calcularValoresRegresion(modeloRegresionEntrada);

        int numeroDatos = modeloRegresion.getDatosEntrada().size();

        for (ModeloParejaXY parejaDatoXY: modeloRegresion.getDatosEntrada())
        {
            listadoDatosX.add(parejaDatoXY.getDatoX());
            listadoDatosY.add(parejaDatoXY.getDatoY());
            double sumatoriaYB0B1X1 = parejaDatoXY.getDatoY() - modeloRegresion.getParametroRegresionB0() - (modeloRegresion.getParametroRegresionB1() * parejaDatoXY.getDatoX());
            sumatoriaCalculoDesviacion += Math.pow(sumatoriaYB0B1X1, 2);
        }

        valorMediaX = calcularMedia(listadoDatosX);

        for (Double datoX: listadoDatosX)
        {
            sumatoriaCuadradoXiXAvg += Math.pow(datoX - valorMediaX, 2);
        }

        desviacionEstandar = Math.sqrt((1 / (numeroDatos - 2.0)) * sumatoriaCalculoDesviacion);


        CalculoIntegral calculoIntegral = new CalculoIntegral();

        ModeloCalculoIntegral modeloCalculoIntegral = new ModeloCalculoIntegral(numeroDatos - 2, 0.35);
        modeloCalculoIntegral.setNumeroSegmentos(20);
        modeloCalculoIntegral.setValorErrorAceptable(0.00001);

        ModeloCalculoIntegral modeloCalculoIntegralSalida = calculoIntegral.calcularValorX(modeloCalculoIntegral);

        valorRaiz = Math.sqrt(1 + (1/(double)numeroDatos) + (Math.pow(modeloRegresion.getValorEstimadoXk() - valorMediaX, 2) / sumatoriaCuadradoXiXAvg));

        rango = modeloCalculoIntegral.getValorX() * desviacionEstandar * valorRaiz;

        return rango;
    }

}

