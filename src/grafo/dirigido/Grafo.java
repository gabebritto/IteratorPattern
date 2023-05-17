package grafo.dirigido;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import iterator.BFSIterator;
import iterator.DFSIterator;
import iterator.Iterator;



/**
 * Classe que disponibiliza as funcionalidades de um grafo direcionado.
 * @author Alex
 * Data de criação: 06/06/2013
 * Atualização usando Generics: 24/03/2023
 */
public class Grafo<T> {

	public static final int INFINITY = 99999;

	/**
	 * Colecao de vertices do grafo
	 */
	private List<Vertice<T>> vertices;
	private List<Aresta<T>> arestas;


	public Grafo(){
			this.vertices = new ArrayList<Vertice<T>>();
			this.arestas = new ArrayList<Aresta<T>>();
	}
	public Grafo(List <Vertice<T>> vertices , List <Aresta<T>> arestas){
			 this.vertices = vertices;
			 this.arestas = arestas;
	}

	public BFSIterator<T> createBfsIterator(T source) {
        Vertice<T> v = this.getVertice(source);
        return new BFSIterator<>(this, v);
	}

	public DFSIterator<T> createDfsIterator(T source) {
		Vertice<T> v = this.getVertice(source);
		return new DFSIterator<>(this, v);
	}


			/**
	 * Obtem um List de Vertices do grafo
	 *
	 * @return the vertices
	 */
	public List<Vertice<T>> getVertices() {
			return vertices;
			}


	/**
	 * Adiciona um vertice ao grafo.
	 * Se o Vertice ja existir (pelo nome), o objeto nao � criado. Caso contrario, um
	 * novo objeto do tipo Vertice � criado e retornado.
	 *
	 * @param *nome - Um String que representa o conteudo do vertice
	 * @return O vertice criado
	 */
	public Vertice<T> addVertice(T carga) {
			Vertice<T> v;
			if ((v = (Vertice<T>) getVertice(carga)) == null) {
			v = new Vertice<>(carga);
			vertices.add(v);
			}
			return v;
			}

	/**
	 * M�todo que localiza e retorna a referencia para um vertice existente no grafo.
	 *
	 * @param *nome O conteudo para identificacao do vertice
	 * @return A referencia para o vertice (quando existente) ou null.
	 */
	public Vertice<T> getVertice(T carga) {

			for (Vertice<T> u : vertices) {
			if (u.getCarga().equals(carga))
			return u;
			}
			return null;
			}

	/**
	 * Verifica a existencia de um vertice no grafo.
	 *
	 * @param v - O vertice desejado
	 * @return <b>true</b>, caso o vertice perten�a ao grafo, ou <b>false</b> caso contrario.
	 */


	public boolean exists(Vertice<T> v) {

			for (Vertice<T> u : vertices) {
			if (u.getCarga().equals(v.getCarga()))
			return true;
			}
			return false;
			}

	/**
	 * Obtem o n�mero de vertices incidentes ao vertice u.
	 *
	 * @param u O vertice o qual se deseja obter a quantidade de incidentes
	 * @return Um numero inteiro que quantifica o numero de vertices incidentes.
	 */
	public int getNumIncidentes(Vertice<T> u) {
			int count = 0;
			for (Aresta<T> arco : arestas) {
			if (arco.getDestino().equals(u))
			count++;
			}
			return count;
			}


	@SuppressWarnings("unchecked")
	public List<Vertice<T>> incidentes(Vertice<T> u) {
			List<Vertice<T>> vertex = new ArrayList<Vertice<T>>();
			for (Aresta<T> arco : arestas) {
			if (arco.getDestino().equals(u))
			vertex.add((Vertice<T>) arco.getOrigem());
			else if (arco.getOrigem().equals(u))
			vertex.add((Vertice<T>) arco.getDestino());

			}
			return vertex;
			}


	/**
	 * Adiciona uma aresta ao grafo.
	 * O m�todo se encarreda de adcionar a aresta como ajacente ao vertice de origem.
	 *
	 * @param origem  - String que identifica o vertice de origem
	 * @param destino - String que identifica o vertice de destino
	 * @param peso    - O peso da aresta
	 * @return Uma referencia para a aresta criada caso os vertices ja existam. Caso contrario,
	 * retorna null.
	 */
	@SuppressWarnings("unchecked")
	public Aresta<T> addAresta(T origem, T destino, int peso) {
			Vertice<T> u, v;
			Aresta<T> arco = null;
			if ((u = getVertice(origem)) == null || (v = getVertice(destino)) == null)
			return arco;

			return (Aresta<T>) addAresta(u, v, peso);
			}

	/**
	 * Adiciona uma aresta ao grafo.
	 *
	 * @param origem  - O objeto vertice de origem
	 * @param destino - O objeto vertice de destino
	 * @param peso    - o peso da aresta
	 * @return Uma referencia para a aresta criada caso os vertices ja existam. Caso contrario,
	 * retorna null.
	 */
	@SuppressWarnings("unchecked")
	public Aresta<T> addAresta(Vertice<T> origem, Vertice<T> destino, int peso) {
			Aresta<T> e;
			/* Se a aresta j� existir, inclusive com o mesmo peso, n�o cria  */
			if ((e = (Aresta<T>) getAresta(origem, destino)) == null) {

			e = new Aresta<T>(origem, destino, peso);
			origem.addAdj(e);
			arestas.add(e);

			}
			return e;
			}

	/**
	 * Obtem a aresta espec�fica para um determinado par de vertices origem/destino.
	 *
	 * @param origem  - O v�rtice de origem
	 * @param destino - O v�rtice de destino
	 * @return Uma referencia para a aresta criada caso os vertices ja existam. Caso contrario,
	 * retorna null
	 */
	public Aresta<T> getAresta(Vertice<T> origem, Vertice<T> destino) {
			Aresta<T> arc = null;
			for (int i = 0; i < arestas.size(); i++) {
			arc = arestas.get(i);
			if (arc.getOrigem().equals(origem) && arc.getDestino().equals(destino))
			return arc;
			}
			return null;
			}

	/**
	 * Obtem a colecao de arestas do grafo.
	 *
	 * @return As arestas.
	 */
	public List<Aresta<T>> getArestas() {
			return arestas;
			}


	/**
	 * Verifica se o grafo est� vazio.
	 *
	 * @return true - se o grafo est� vazio, false caso contr�rio.
	 */
	//public boolean isEmpty() {
	//        return vertices.size() == 0;
	//        }

	/**
	 * Esvazia o grafo.
	 */
	/*
	public void reset() {
			vertices.clear();
			arestas.clear();
			}*/

	public String toString() {
		String r = "";
		for (Vertice<T> u : vertices) {
			if (u.getAdj().size() != 0) {
			r += u.getCarga().toString() + " -> ";
			} else
			continue;

		for (Aresta<T> e : u.getAdj()) {
	//            	r += u.tag + " -> ";
			Vertice<T> v = (Vertice<T>) e.getDestino();
			r += v.getCarga().toString() + ", ";
		}
			r += "\n";
			}
			return r;
		}

	public void checkDistanceIsInfinity(){
			for(int i=0; i < vertices.size(); i++ ){
					if(this.vertices.get(i).getDistance() == INFINITY )
							continue;
					System.out.print("(v)=" + vertices.get(i).getCarga());
					System.out.println(", dist(v)="  +  vertices.get(i).getDistance());
			}
	}

	public void setAllVerticesUnvisited(){
			for (int i = 0; i < this.vertices.size(); i++) {
					this.vertices.get(i).setStatus(VertexState.Unvisited);
			}
	}

	public void showMarked() {
			for (int i = 0; i < vertices.size(); i++) {
					if (vertices.get(i).getStatus() == VertexState.Visited)
					System.out.print(vertices.get(i).getCarga());
			}
	}

	public int getDistance(Vertice<T> start, Vertice<T> end)
	{
			List<Aresta<T>> arcos = start.getAdj();

			for( Aresta<T> a : arcos){
					if( a.getDestino().getCarga().equals( end.getCarga()) )
							return a.getPeso();

			}
			return 0 ;
	}

	@SuppressWarnings("unchecked")
	public Object clone() {
			try {
					Grafo<T> clone = (Grafo<T>)super.clone();

					//Clona o resto.
					clone.arestas.addAll(arestas);
					clone.vertices.addAll(vertices);
					return clone;
			} catch (CloneNotSupportedException e) {

					e.printStackTrace();
					return null;
			} // Clona os tipos primitivos;

	}

	@SuppressWarnings("unchecked")
	public Grafo<T> getSubGrafo(T carga){
			Vertice<T> source,target, element;

			if( (source=getVertice(carga))==null )
					return null;
			Grafo<T> grafo = new Grafo<T>();

			Queue<Vertice<T>> fila = new LinkedList<Vertice<T>>();
			fila.add(source);


			while( !fila.isEmpty() ) {
					element = fila.poll();
					source = grafo.addVertice( element.getCarga());

					for(Aresta edge: element.getAdj()){
							target = grafo.addVertice( (T) edge.getDestino().getCarga());
							grafo.addAresta(source, target, edge.getPeso());
							fila.add((Vertice<T>) edge.getDestino());
					}

			}

			return grafo;
	}
}
