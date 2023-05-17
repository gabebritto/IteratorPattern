package grafo.dirigido;

import java.util.ArrayList;
import java.util.List;

public class Vertice<T> /*implements Comparable<Vertice> */{
	
	private T carga;
	private List<Aresta<T>> adj;
	// Estágios de visitação 
	private VertexState status;
	
	private int distance; // distancia para um vértice v0
	
	
	public Vertice(T carga) {
		this.carga = carga;
		this.adj = new ArrayList<Aresta<T>>();
	}

	
	/**
	 * @return the status
	 */
	public VertexState getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(VertexState status) {
		this.status = status;
	}
	
	public T getCarga() {
		return carga;
	}

	public void setCarga(T carga) {
		this.carga = carga;
	}

	//Armazenando todos os vertices adj
	public void addAdj(Aresta<T> aresta) {
		adj.add(aresta);
	}
	
	public List<Aresta<T>> getAdj() {
		return adj;
	}

	public void setAdj(List<Aresta<T>> adj) {
		this.adj = adj;
	}
	
//	public int compareTo( Vertice outro){
//		return  this.tag.compareTo( outro.tag);
//	}
	
	 public boolean equals(Vertice<T> v)
	 {
	        return this.carga.equals(v.carga);
	 }
	 
	 @SuppressWarnings("unchecked")
	public boolean equals(Object o)
	 {
	        return this == o || equals((Vertice<T>) o);
	 }
	 
	 public String toString(){
		 return this.carga.toString();
	 }


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}

}
