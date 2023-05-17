package grafo.dirigido;

public class Aresta<T> {

	private Vertice<T> origem;
	private Vertice<T> destino;
	private int peso; /* coocorrencia */

	public Aresta(Vertice<T> origem, Vertice<T> destino, int peso) {
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}

	public Vertice<T> getOrigem() {
		return origem;
	}

	public void setOrigem(Vertice<T> origem) {
		this.origem = origem;
	}

	public Vertice<T> getDestino() {
		return destino;
	}

	public void setDestino(Vertice<T> destino) {
		this.destino = destino;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public String toString(){
		return origem.getCarga().toString() + " --(" + peso + ")--> " + destino.getCarga().toString();
	}
	

}
