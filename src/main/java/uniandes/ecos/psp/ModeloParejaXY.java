package uniandes.ecos.psp;

/**
 * Clase que contiene la estructura de una pareja de datos X, Y
 * Created by edgaguil on 8/03/2017.
 */
public class ModeloParejaXY
{
    /**
     * Constructor de la clase
     * @param datoX Dato X
     * @param datoY Dato Y
     */
    public ModeloParejaXY(double datoX, double datoY)
    {
        setDatoX(datoX);
        setDatoY(datoY);
    }

    public ModeloParejaXY()
    {
    }

    //Dato X
    private double datoX;

    //Dato Y
    private double datoY;

    /**
     * Obtiene el valor de X
     * @return
     */
    public double getDatoX()
    {
        return datoX;
    }

    /***
     * Asigna el valor de X
     * @param datoX
     */
    public void setDatoX(double datoX)
    {
        this.datoX = datoX;
    }

    /***
     * Obtiene el valor X
     * @return
     */
    public double getDatoY()
    {
        return datoY;
    }

    /***
     * Se asignsa el valor de Y
     * @param datoY
     */
    public void setDatoY(double datoY)
    {
        this.datoY = datoY;
    }
}
