/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

//Implementación del Algoritmo voraz de Kruskal.

import java.util.ArrayList;

public class AlgoritmoKruskal {

    public Grafo aplicarKruskal(Grafo grafo) {
        Grafo arbol = new Grafo();
        ArrayList<String> nodos = grafo.getNombres();
        for (int j = 0; j < nodos.size(); j++) {
            arbol.ingresarNodo(nodos.get(j));
        }
        ArrayList<Arco> aristas = (ArrayList<Arco>) grafo.getAristas().clone();
        Arco arista = aristas.get(0);
        arbol.agregarArista(arista.getInicial(), arista.getTerminal(), arista.getPeso());
        aristas.remove(arista);
        while (aristas.size() != 0) {
            arista = aristas.get(0);
            if (existeCiclo(arbol, arista, arbol.getNodo(arista.getTerminal()), arista.getTerminal()) == false)
                arbol.agregarArista(arista.getInicial(), arista.getTerminal(), arista.getPeso());
            aristas.remove(arista);
        }
        return arbol;
    }

    public boolean existeCiclo(Grafo g, Arco aVerificar, Nodo terminal, String nombreNodo) {
        ArrayList<Enlace> caminosPosibles = terminal.getEnlaces();
        if (caminosPosibles.size() == 0)
            return false;
        if (terminal.existeEnlace(aVerificar.getInicial()) != -1)
            return true;
        for (int i = 0; i < caminosPosibles.size(); i++) {
            Enlace nodo = caminosPosibles.get(i);
            if (nodo.getDestino().equals(nombreNodo) == false)
                if (existeCiclo(g, aVerificar, g.getNodo(nodo.getDestino()), terminal.getNombre()))
                    return true;
        }
        return false;
    }
}
