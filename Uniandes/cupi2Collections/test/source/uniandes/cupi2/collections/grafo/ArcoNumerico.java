package uniandes.cupi2.collections.grafo;

public class ArcoNumerico implements IArco {

	private int peso;
	
	public ArcoNumerico(int peso) {
		this.peso = peso;
	}
	
	public int darPeso() {
		return peso;
	}

}
