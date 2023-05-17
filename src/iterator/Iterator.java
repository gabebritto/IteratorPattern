package iterator;

import grafo.dirigido.Vertice;

public interface Iterator<T> {
    Vertice<T> next();
    boolean hasNext();
    void reset();
}
