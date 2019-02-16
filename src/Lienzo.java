import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Lienzo extends JCompone
        nt {
    private ArrayList<Punto> puntos;
    private ArrayList<Arista> aristas;
    private ArrayList<Arista> neo;
    private Point a, b;
    public boolean estado = false;
    public boolean punto = false;

    public Lienzo()
    {
        aristas = new ArrayList<Arista>();
        puntos = new ArrayList<Punto>();
        neo = new ArrayList<Arista>();
    }

    public void paintComponent(Graphics g)
    {
        if (punto)
        {
            g.setColor(Color.BLUE);
            g.drawLine((int) a.getX() + 5, (int) a.getY() + 5, (int) b.getX() + 5, (int) b.getY() + 5);
        }
        for (int i = 0; i < aristas.size(); i++)
        {
            final Arista arista = (Arista) aristas.get(i);
            arista.pintarRecta(g);
        }
        if (estado)
            for (int i = 0; i < neo.size(); i++)
            {
                final Arista arista = (Arista) neo.get(i);
                arista.setColor(Color.RED);
                arista.pintarRecta(g);
            }
        for (int i = 0; i < puntos.size(); i++)
        {
            final Punto punto = (Punto) puntos.get(i);
            punto.pintarPunto(g);
        }
    }

    public void cambiarGrafo(Grafo nuevo)
    {
        Arco aux;

        for (int i = 0; i < aristas.size(); i++)
        {
            aux = aristas.get(i).getArista();

            if (nuevo.busarArista(aux) == true)
                neo.add(aristas.get(i));
        }

        for (int i = 0; i < aristas.size(); i++)
        {
            final Arco n = aristas.get(i).getArista();
            nuevo.getAristas().add(n);
        }

        estado = true;

        repaint();
    }

    public ArrayList<Punto> getPuntos()
    {
        return puntos;
    }

    public void setPuntos(final ArrayList<Punto> puntos)
    {
        this.puntos = puntos;
    }

    public ArrayList<Arista> getAristas()
    {
        return aristas;
    }

    public void setAristas(final ArrayList<Arista> aristas)
    {
        this.aristas = aristas;
    }

    public ArrayList<Arista> getNeo()
    {
        return neo;
    }

    public void setNeo(final ArrayList<Arista> neo)
    {
        this.neo = neo;
    }

    public void setA(Point a)
    {
        this.a = a;
    }

    public void setB(Point b)
    {
        this.b = b;
    }

    public void setBounds(int i, int i1, int i2, int i3) {
    }
}
