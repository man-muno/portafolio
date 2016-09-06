package uniandes.cupi2.collections.grafo;

public class VerticeNumerico implements IVertice<Integer> {

	private int valor;
	
	public VerticeNumerico(int valor) {
		this.valor = valor; 
	}
	
	public Integer darId() {
		return valor;
	}

}
