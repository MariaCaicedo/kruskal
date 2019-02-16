import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;

public class Arista {
    private Arco arista;
    private Punto a, b;
    private Point inicial;
    private Point terminal, ubicacionExt;
    private Color color = new Color(0, 128, 128), aux = Color.RED;
    private int tipo;
    private float peso;

    public Arista(Punto puntoA, Punto puntoB, int tipo, float peso)
    {
        arista = new Arco(puntoA.getNombre(), puntoB.getNombre(), peso);

        a = puntoA;
        b = puntoB;
        this.tipo = tipo;
        this.peso = peso;
    }

    public void pintarRecta(Graphics ga)
    {
        inicial = a.getUbicacion();
        terminal = b.getUbicacion();

        Graphics2D g = (Graphics2D) ga;
        double a = (inicial.y - terminal.y);
        double b = (inicial.x - terminal.x);
        double m = a / b;
        double grado = Math.atan(m);

        switch (tipo)
        {
            case 0:
                g.setColor(color);
                g.drawLine(inicial.x + 5, inicial.y + 5, terminal.x + 5, terminal.y + 5);
                g.setColor(aux);
                g.drawString(peso + "", (inicial.x + terminal.x) / 2, (inicial.y + terminal.y) / 2);
                break;

            case 1:
                g.setColor(color);
                g.drawOval(inicial.x - 10, inicial.y - 30, 30, 30);
                g.setColor(aux);
                g.drawString(peso + "", inicial.x - 3, inicial.y - 30);
                break;

            case 2:
                Point control = null;
                if (grado > 0)
                {
                    if (grado <= 45 && grado >= 0)
                        control = new Point((inicial.x + terminal.x) / 2 - 10, (inicial.y + terminal.y) / 2 + 50);
                    if (grado <= 90 && grado > 45)
                        control = new Point((inicial.x + terminal.x) / 2 + 50, (inicial.y + terminal.y) / 2 + 10);
                }
                else
                {
                    if (grado >= -45 && grado <= 0)
                        control = new Point((inicial.x + terminal.x) / 2 - 30, (inicial.y + terminal.y) / 2 - 10);
                    if (grado >= -90 && grado < -45)
                        control = new Point((inicial.x + terminal.x) / 2 - 50, (inicial.y + terminal.y) / 2 - 10);
                }

                Point tempInicial = new Point(inicial.x + 5, inicial.y + 5),
                        tempFinal = new Point(terminal.x + 5, terminal.y + 5);

                QuadCurve2D.Double quad = new QuadCurve2D.Double();

                quad.setCurve(tempInicial, control, tempFinal);

                g.setColor(aux);
                g.drawString(peso + "", control.x, control.y);
                g.setColor(color);
                g.draw(quad);

                break;

        }
    }

    public Point getUbicacion()
    {
        return ubicacionExt;
    }

    public int getTipo()
    {
        return tipo;
    }

    public Arco getArista()
    {
        return arista;
    }

    public void setArista(Arco arista)
    {
        this.arista = arista;
    }

    public void getColor()
    {
        color = new Color(0, 128, 128);
        aux = Color.RED;
    }

    public void setColor(Color color)
    {

        if (color.equals(new Color(0, 128, 128)))
            aux = Color.RED;
        else
            aux = Color.BLUE;

        this.color = color;
    }

}
