package iterator;

import grafo.dirigido.Grafo;
import grafo.dirigido.VertexState;
import grafo.dirigido.Vertice;

import java.util.*;

public class BFSIterator<T> implements Iterator{

    private Queue<Vertice<T>> queue = new LinkedList<Vertice<T>>();
    private Grafo<T> grafo;
    private Vertice<T> fonte;


    public BFSIterator(Grafo<T> grafo, Vertice<T> fonte) {
        this.grafo = grafo;
        this.fonte = fonte;
        this.grafo.setAllVerticesUnvisited();
        this.queue.add(this.fonte);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
     public Vertice<T> next(){
        List<Vertice<T>> uAdjacentes = null;
        Vertice<T> removed = this.queue.remove();
        removed.setStatus(VertexState.Visited);
        uAdjacentes = this.grafo.incidentes(removed);
        for(Vertice<T> w : uAdjacentes){
            if (w.getStatus() == VertexState.Unvisited) {
                w.setStatus(VertexState.Visited);
                this.queue.add(w);
            }

        }
        removed.setStatus(VertexState.Finished);
        return removed;
    }

    @Override
    public void reset() {
        //Reiniciar Fila
        this.queue = new LinkedList<Vertice<T>>();
        //Reiniciar estado dos vertices
        this.grafo.setAllVerticesUnvisited();
    }
}
