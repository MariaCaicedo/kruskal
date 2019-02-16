/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Ventana implements MouseListener, ItemListener, ActionListener, MouseMotionListener
{
       int cantidad = 0;

       ArrayList<Punto> puntos;
       ArrayList<Arista> aristas;
       static JFrame frame;
       JButton aplicar,nuevo,recuperar;
       Container contenedor;
       JRadioButton radioNodo, radioArista, radioMod;
       JLabel ayuda, titulo;
       ButtonGroup grupo;
       JComboBox comboOpcionesRecta,ayudaCombo;
       JScrollPane panel;
       public JTextArea area;
       String ayudaNod,ayudaAri,ayudaMod,ayudaRes,ayudaNue,ayudaApl;
       Punto pun[];
       public Lienzo lienzo;
       Grafo grafo;
       int j,i;
       int x,y;

       private int contador = 0;

       public Ventana()
       {
               frame = new JFrame();
               contenedor=frame.getContentPane();

               Font font=new Font("Verdana",Font.BOLD,11);
               Font font1=new Font("Verdana",Font.PLAIN,12);

               grafo=new Grafo();

               lienzo = new Lienzo();
               lienzo.setBounds(0, 0, 600, 600);
               lienzo.setBorder(BorderFactory.createBevelBorder(1));
               lienzo.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

               pun = new Punto[2];
               pun[0]=null;
               pun[1]=null;

               puntos = new ArrayList<Punto>();
               aristas = new ArrayList<Arista>();

               area=new JTextArea();
               area.setFont(font1);
               panel=new JScrollPane(area);
               panel.setBounds(660, 270, 280, 250);

               titulo=new JLabel("Dibuje su grafo:");
               titulo.setFont(font);
               titulo.setBounds(610, 20, 130, 20);
               ayuda=new JLabel("Ayuda:");
               ayuda.setBounds(610, 200, 100, 20);
               ayuda.setFont(font);

               comboOpcionesRecta = new JComboBox();
               comboOpcionesRecta.addItem("Arista");
               comboOpcionesRecta.addItem("Arista Bucle");
               comboOpcionesRecta.addItem("Arista Curva");
               comboOpcionesRecta.setFont(font);

               ayudaCombo=new JComboBox();
               ayudaCombo.addItem(">Como hacer un Nodo");
               ayudaCombo.addItem(">Como hacer una Arista");
               ayudaCombo.addItem(">Como modificar un Grafo");
               ayudaCombo.addItem(">Boton 'Nuevo'");
               ayudaCombo.addItem(">Boton 'Aplicar'");
               ayudaCombo.addItem(">Boton'Reset'");
               ayudaCombo.setFont(font);
               ayudaCombo.setSelectedIndex(-1);

               radioNodo = new JRadioButton("Crear Nodo");
               radioArista = new JRadioButton("Crear Arista");
               radioMod = new JRadioButton("Modificar");

               radioArista.setFont(font);
               radioMod.setFont(font);
               radioNodo.setFont(font);

               grupo = new ButtonGroup();
               grupo.add(radioNodo);
               grupo.add(radioArista);
               grupo.add(radioMod);
               radioNodo.setSelected(true);

               radioNodo.setBounds(680, 60, 120, 20);
               radioArista.setBounds(680, 110, 130, 20);
               radioMod.setBounds(820, 60, 100, 20);

               aplicar=new JButton("Aplicar");
               aplicar.setBounds(670,160, 80, 20);
               aplicar.setFont(font);
               nuevo=new JButton("Nuevo");
               nuevo.setBounds(760,160, 80, 20);
               nuevo.setFont(font);
               recuperar=new JButton("Reset");
               recuperar.setBounds(850,160, 80, 20);
               recuperar.setFont(font);

               comboOpcionesRecta.setBounds(815, 110, 100, 20);
               ayudaCombo.setBounds(710,240,175,20);

               ayudaNod = "\n¡¡¡COMO CREAR UN NODO!!!\n\n" + "1. Seleccione el RadioButton \"Crear Nodo\".\n" + "2. Haga doble click sobre el area de Dibujo.\n" + "3. Inserte el nombre del Nodo.\n" + "4. Haga click en Aceptar.";
               ayudaAri = "\n¡¡¡COMO CREAR UNA ARISTA!!!\n\n" + "1. Seleccione el RadioButton \"Crear Arista\".\n" + "2. Seleccione el Tipo de arista que desee\n    \"Arista\", \"Arista Curva\", o \"Arista Bucle\".\n" + "3. Haga click sobre el nodo que desea sea el\n    nodo inicial.\n" + "4. Mantega presionado el boton del mouse\n    y arrastrelo hasta el nodo terminal.\n" + "5. Suelte el boton del mouse e ingrese\n    el peso de la Arista.\n" + "6. Haga click en Aceptar.";
               ayudaMod = "\n¡¡¡COMO MODIFICAR EL GRAFO!!!\n\n" + "1. Seleccione el RadioButton \"Modificar\".\n" + "2. Haga click sobre un Nodo\n" + "3. Mantega presionado el botón del mouse \n    y arrastrelo para darle una nueva\n    posición al nodo.\n" + "4. Suelte el botón del mouse.";
               ayudaRes = "\n¡¡¡BOTON RESET!!!\n\n" + " Este botón permite recuperar el estado \n" + " del Grafo antes de aplicar el Algoritmo de \n" + " Kruskal.\n";
               ayudaApl = "\n¡¡¡BOTON APLICAR!!!\n\n" + " Este botón permite Aplicar el Algoritmo de\n" + " Kruskal al Grafo dibujado por el Usuario.";
               ayudaNue = "\n¡¡¡BOTON NUEVO!!!\n\n" + " Limpia el area de Dibujo para crear un\n" + " nuevo Grafo y poder aplicar nuevamente\n" + " el Algoritmo de Kruskal.\n";

               contenedor.setLayout(null);

               contenedor.add(comboOpcionesRecta);
               contenedor.add(radioMod);
               contenedor.add(radioArista);
               contenedor.add(radioNodo);
               contenedor.add(ayudaCombo);
               contenedor.add(lienzo);
               contenedor.add(aplicar);
               contenedor.add(panel);
               contenedor.add(nuevo);
               contenedor.add(recuperar);
               contenedor.add(ayuda);
               contenedor.add(titulo);


               frame.setFont(font);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setLayout(null);
               frame.setSize(1000, 600);
               frame.setLocation(10, 20);
               frame.setTitle("GrafoMatic Displayer");
               frame.setVisible(true);


               lienzo.addMouseListener(this);
               lienzo.addMouseMotionListener(this);
               aplicar.addActionListener(this);
               nuevo.addActionListener(this);
               recuperar.addActionListener(this);
               radioNodo.addItemListener(this);
               radioArista.addItemListener(this);
               radioMod.addItemListener(this);
               ayudaCombo.addItemListener(this);
       }

       public void mouseClicked(MouseEvent evento)
    {
               if (evento.getClickCount() == 2&&radioNodo.isSelected()==true)
               {

                       String nombre = "hay";

                       do
                       {
                               try
                               {
                                       nombre = JOptionPane.showInputDialog(null,"Ingrese la Etiqueta del Nodo");
                                       nombre.length();
                               }
                               catch(NullPointerException e)
                               {
                                       return;
                               }
                               if(grafo.getNombres().contains(nombre)||nombre==null)
                               {
                                       JOptionPane.showMessageDialog(null,"La Etiqueta es incorrecta, recuerde que no debe haber otra igual","Ingrese de nuevo la Etiqueta",JOptionPane.ERROR_MESSAGE );
                                       nombre="hay";
                               }
                       }
                       while(nombre=="hay");

                       Punto punto = new Punto((int) evento.getPoint().getX() - 5, (int) evento.getPoint().getY() - 5, nombre);
                       grafo.ingresarNodo(nombre);
                       punto.pintarPunto(lienzo.getGraphics());
                       puntos.add(punto);
                       lienzo.setPuntos(puntos);
               }

       }
       public void actionPerformed(ActionEvent e) {

               if(e.getSource()==aplicar)
               {
                       AlgoritmoKruskal nuevo=new AlgoritmoKruskal();

                       Grafo kruskal= new Grafo();
                       kruskal=nuevo.aplicarKruskal(grafo);
                       lienzo.cambiarGrafo(kruskal);
               }
               if(e.getSource()==nuevo)
               {
                       grafo=new Grafo();
                       lienzo.getAristas().clear();
                       lienzo.getPuntos().clear();
                       lienzo.getNeo().clear();
                       aristas.clear();
                       lienzo.punto=false;
                       lienzo.repaint();
               }
               if(e.getSource()==recuperar)
               {
                       for(int i=0;i<lienzo.getNeo().size();i++)
                       {
                               Arista arista=(Arista)lienzo.getNeo().get(i);
                               arista.setColor(new Color(0,128,128));

                       }
                       lienzo.getNeo().clear();
                       lienzo.estado=false;
                       lienzo.repaint();
               }
       }


       public void itemStateChanged(ItemEvent evento)
       {
               if (evento.getSource() == radioNodo)
                       comboOpcionesRecta.setEnabled(false);
               else
                       comboOpcionesRecta.setEnabled(true);
               if(evento.getSource()==radioNodo||radioNodo.isSelected())
                       lienzo.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
               else
                       lienzo.setCursor(Cursor.getDefaultCursor());
               if(evento.getSource()==ayudaCombo)
               {
                       if(ayudaCombo.getSelectedIndex()==0)
                               area.setText(ayudaNod);
                       if(ayudaCombo.getSelectedIndex()==1)
                               area.setText(ayudaAri);
                       if(ayudaCombo.getSelectedIndex()==2)
                               area.setText(ayudaMod);
                       if(ayudaCombo.getSelectedIndex()==3)
                               area.setText(ayudaNue);
                       if(ayudaCombo.getSelectedIndex()==4)
                               area.setText(ayudaApl);
                       if(ayudaCombo.getSelectedIndex()==5)
                               area.setText(ayudaRes);
               }
       }


       public void mousePressed(MouseEvent arg0)
       {
               contador=0;
               if(radioArista.isSelected())
               {
                       for (int i = 0; i < puntos.size(); i++)
                       {
                               if (puntos.get(i).ecuacionDeCirculo(arg0.getPoint()))
                               {
                                       puntos.get(i).setColorPunto(Color.RED);//pintarPunto(lienzo.getGraphics(), Color.RED);
                                       x=puntos.get(i).getUbicacion().x;
                                       y=puntos.get(i).getUbicacion().y;

                                       if(comboOpcionesRecta.getSelectedIndex()==1)
                                       {
                                               pun[contador] = puntos.get(i);
                                               contador++;
                                       }

                                       pun[contador] = puntos.get(i);

                                       break;
                               }
                       }
               }
               if(radioMod.isSelected())
               {
                       for (int i = 0; i < puntos.size(); i++)
                       {
                               if (puntos.get(i).ecuacionDeCirculo(arg0.getPoint()))
                               {
                                       puntos.get(i).setColorPunto(Color.RED);
                                       pun[contador] = puntos.get(i);

                                       break;
                               }
                       }
                       contador=0;
               }
               lienzo.repaint();
       }

       public void mouseReleased(MouseEvent arg0)
       {
               if(radioArista.isSelected())
               {
                       if(pun[1]==null||pun[1].ecuacionDeCirculo(arg0.getPoint())==false||pun[0].getUbicacion().equals(pun[1].getUbicacion()))
                                       contador=0;

                       if(contador==2||comboOpcionesRecta.getSelectedIndex()==1)
                       {
                               float peso=-1;
                               do
                               {
                                       try
                                       {
                                               peso = Float.parseFloat(JOptionPane.showInputDialog(null,"Ingrese el Peso de la Arista"));
                                       }
                                       catch(NumberFormatException ex)
                                       {
                                               JOptionPane.showMessageDialog(null,"El peso de la Arista debe ser un Número","Ingrese de nuevo el Peso",JOptionPane.ERROR_MESSAGE );
                                               peso=-1;
                                       }
                                       catch(NullPointerException e)
                                       {
                                               pun[0].setColorPunto(Color.BLUE);
                                               if(pun[1]!=null)
                                                       pun[1].setColorPunto(Color.BLUE);
                                               lienzo.punto=false;
                                               lienzo.repaint();
                                               return;
                                       }
                               }
                               while(peso==-1);

                               Arista arista=new Arista(pun[0], pun[1], comboOpcionesRecta.getSelectedIndex(),peso);

                               aristas.add(arista);
                               lienzo.setAristas(aristas);

                               arista.pintarRecta(lienzo.getGraphics());
                               grafo.agregarArista(pun[0].getNombre(),pun[1].getNombre(),peso);

                               contador = 0;
                               pun[0].setColorPunto(Color.BLUE);
                               pun[1].setColorPunto(Color.BLUE);

                               lienzo.punto=false;
                               lienzo.repaint();
                       }
               }

               if(pun[0]!=null)
                       pun[0].setColorPunto(Color.BLUE);

               lienzo.repaint();
               lienzo.punto=false;
               contador=0;
               pun[0]=null;
               pun[1]=null;
       }

       public void mouseEntered(MouseEvent arg0)
       {


       }

       public void mouseExited(MouseEvent arg0)
       {

       }

       public void mouseDragged(MouseEvent e) {

               if(radioArista.isSelected())
               {
                       for (int i = 0; i < puntos.size(); i++)
                       {
                               if (puntos.get(i).ecuacionDeCirculo(e.getPoint()))
                               {
                                       pun[1] = puntos.get(i);
                                       pun[1].setColorPunto( Color.RED);
                                       break;
                               }
                               else
                                       if(pun[1]!=null&&pun[1]!=pun[0])
                                               pun[1].setColorPunto(Color.BLUE);
                       }

                       if(pun[0]!=null)
                       {
                               lienzo.setA(new Point(x,y));
                               lienzo.setB(e.getPoint());

                               lienzo.punto=true;
                               lienzo.repaint();
                       }
                       contador=2;
               }
               if(radioMod.isSelected()&&pun[0]!=null)
               {
                       Point ubicacion=new Point(e.getX()-5,e.getY()-5);
                       pun[0].setUbicacion(ubicacion);
                       lienzo.repaint();
               }
       }

       public void mouseMoved(MouseEvent e) {

               if(radioMod.isSelected())
               {
                       for (int i = 0; i < puntos.size(); i++)
                       {
                               if (puntos.get(i).ecuacionDeCirculo(e.getPoint()))
                                       puntos.get(i).pintarPunto(lienzo.getGraphics(), Color.RED);
                               else
                                       puntos.get(i).pintarPunto(lienzo.getGraphics(), Color.BLUE);
                       }

               }
       }


       public Grafo getGrafo()
       {
               return grafo;
       }


}

