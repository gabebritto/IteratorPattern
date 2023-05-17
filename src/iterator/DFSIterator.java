package iterator;

import java.util.List;
import java.util.Stack;

import grafo.dirigido.Aresta;
import grafo.dirigido.Grafo;
import grafo.dirigido.VertexState;
import grafo.dirigido.Vertice;

public class DFSIterator<T> implements Iterator{

    private Grafo<T> grafo;
    private Stack<Vertice<T>> pilha;
    private Vertice<T> fonte;

    public DFSIterator(Grafo<T> grafo, Vertice<T> fonte){
        this.grafo = grafo;
        this.fonte = fonte;
        this.pilha = new Stack<>();
        this.grafo.setAllVerticesUnvisited();
        this.pilha.push(fonte);
    }

    @Override
    public boolean hasNext() {
        boolean isNotEmpty = !(this.pilha.isEmpty());
        if (!isNotEmpty) {
            this.reset();
        }
        return isNotEmpty;
    }

    @Override
    public Vertice<T> next() {
        Vertice<T> w = null;
        List<Aresta<T>> adjacentes = null;
        Vertice<T> u = this.pilha.pop();
        u.setStatus(VertexState.Visited);
        adjacentes = u.getAdj();

        for(Aresta<T> arco: adjacentes){
            w = arco.getDestino();
            if( w.getStatus() == VertexState.Unvisited && !this.pilha.contains(w) ) {
                this.pilha.push(w);
            }
        }
        return u;
    }

    @Override
    public void reset() {
        this.pilha.clear();
        this.grafo.setAllVerticesUnvisited();
    }

}
