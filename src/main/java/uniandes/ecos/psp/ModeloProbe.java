/**
 * Autor: Edgar Alexander Aguilar Bolaños
 * Fecha de Creación: 03/05/2017
 * Propósito: Clase contenedora de los datos sobre los que se realiza el calculo de los valores del metodo probe la integral
 * Notas especiales:
 * @author  Edgar Alexander Aguilar Bolaños
 * @version 1.0
 */
package uniandes.ecos.psp;

/**
 * Clase contenedora de los datos sobre los que se realiza el calculo de los valores del metodo probe la integral
 * Created by edgaguil on 3/05/2017.
 */
public class ModeloProbe
{
    // Campo que contiene los datos del modelo de regresion
    private ModeloRegresion modeloRegresion;

    // Valor de la significancia
    private double valorSignificancia;

    // Valor del rango
    private double valorRango;

    // Valor superior 70 porciento
    private double valorUPI70;

    // Valor inferior 70 porciento
    private double valorLPI70;

    /**
     * Obtiene el objeto que contiene los datos de regresion
     * @return
     */
    public ModeloRegresion getModeloRegresion()
    {
        return modeloRegresion;
    }

    /***
     * Obtiene el valor de la significancia
     * @return
     */
    public double getValorSignificancia()
    {
        return valorSignificancia;
    }

    /**
     * Asigna el valor de la significancia
     * @param valorSignificancia
     */
    public void setValorSignificancia(double valorSignificancia)
    {
        this.valorSignificancia = valorSignificancia;
    }

    /***
     * Obtiene el valor del rango
     * @return
     */
    public double getValorRango()
    {
        return valorRango;
    }

    /***
     * Asigna el valor del rango
     * @param valorRango
     */
    public void setValorRango(double valorRango)
    {
        this.valorRango = valorRango;
    }

    /***
     * Obtiene el valor de UPI
     * @return
     */
    public double getValorUPI70()
    {
        return valorUPI70;
    }

    /***
     * Asigna el valor de UPI
     * @param valorUPI70
     */
    public void setValorUPI70(double valorUPI70)
    {
        this.valorUPI70 = valorUPI70;
    }

    /***
     * Obtiene el valor de LPI
     * @return
     */
    public double getValorLPI70()
    {
        return valorLPI70;
    }

    /***
     * Asigna el valor de LPI
     * @param valorLPI70
     */
    public void setValorLPI70(double valorLPI70)
    {
        this.valorLPI70 = valorLPI70;
    }

    /***
     * Asigna el objeto que tiene los datos de la regresion
     * @param modeloRegresion
     */
    public void setModeloRegresion(ModeloRegresion modeloRegresion)
    {
        this.modeloRegresion = modeloRegresion;
    }
}
