package uniandes.ecos.psp;

import java.util.LinkedList;

/**
 * Clase que contiene la estructura de los parametros de regresion y coeficiente de regresion
 * Created by edgaguil on 8/03/2017.
 */
public class ModeloRegresion
{

    // Parametro regresion B0
    private double parametroRegresionB0;

    // Parametro regresion B1
    private double parametroRegresionB1;

    // Coeficiente Regresion Rxy
    private double coeficienteRegresionRxy;

    // Coeficiente Regresion R2
    private double coeficienteRegresionR2;

    // Valor prediccion Yk
    private double prediccionYk;

    public double getValorEstimadoXk()
    {
        return valorEstimadoXk;
    }

    public void setValorEstimadoXk(double valorEstimadoXk)
    {
        this.valorEstimadoXk = valorEstimadoXk;
    }

    // Valor estimado Xk
    private double valorEstimadoXk;

    // Datos de entrada para los calculos de la regresion
    private LinkedList<ModeloParejaXY> datosEntrada;


    public double getParametroRegresionB0()
    {
        return parametroRegresionB0;
    }

    public void setParametroRegresionB0(double parametroRegresionB0)
    {
        this.parametroRegresionB0 = parametroRegresionB0;
    }

    public double getParametroRegresionB1()
    {
        return parametroRegresionB1;
    }

    public void setParametroRegresionB1(double parametroRegresionB1)
    {
        this.parametroRegresionB1 = parametroRegresionB1;
    }

    public double getCoeficienteRegresionRxy()
    {
        return coeficienteRegresionRxy;
    }

    public void setCoeficienteRegresionRxy(double coeficienteRegresionRxy)
    {
        this.coeficienteRegresionRxy = coeficienteRegresionRxy;
    }

    public double getCoeficienteRegresionR2()
    {
        return coeficienteRegresionR2;
    }

    public void setCoeficienteRegresionR2(double coeficienteRegresionR2)
    {
        this.coeficienteRegresionR2 = coeficienteRegresionR2;
    }

    public double getPrediccionYk()
    {
        return prediccionYk;
    }

    public void setPrediccionYk(double prediccionYk)
    {
        this.prediccionYk = prediccionYk;
    }

    public LinkedList<ModeloParejaXY> getDatosEntrada()
    {
        return datosEntrada;
    }

    public void setDatosEntrada(LinkedList<ModeloParejaXY> datosEntrada)
    {
        this.datosEntrada = datosEntrada;
    }
}
