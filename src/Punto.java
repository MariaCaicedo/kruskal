import java.awt.*;

public class Punto {
    private Point ubicacion;
    private String nombre;
    private Color colorPunto = Color.BLUE;
    private static final int RADIO = 10;

    public Punto(int x, int y, String nombre)
    {
        ubicacion = new Point(x, y);
        this.nombre = nombre;
    }

    public void pintarPunto(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawString(nombre, ubicacion.x - 3, ubicacion.y - 3);
        g.setColor(colorPunto);
        g.fillOval(ubicacion.x, ubicacion.y, 10, 10);
    }

    public void pintarPunto(Graphics g, Color color)
    {
        g.setColor(Color.BLACK);
        g.drawString(nombre, ubicacion.x - 3, ubicacion.y - 3);
        g.setColor(color);
        g.fillOval(ubicacion.x, ubicacion.y, 10, 10);
    }

    public boolean ecuacionDeCirculo(Point punto)
    {
        return (((punto.x - ubicacion.x) * (punto.x - ubicacion.x) + (punto.y - ubicacion.y) * (punto.y - ubicacion.y)) <= (RADIO * RADIO));
    }

    public Point getUbicacion()
    {
        return ubicacion;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setUbicacion(Point ubicacion)
    {
        this.ubicacion = ubicacion;
    }

    public Color getColorPunto()
    {
        return colorPunto;
    }

    public void setColorPunto(Color colorPunto)
    {
        this.colorPunto = colorPunto;
    }


}
