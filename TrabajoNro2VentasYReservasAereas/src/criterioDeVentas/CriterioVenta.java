package criterioDeVentas;

import empresaAerea.Vuelo;

public abstract class CriterioVenta {
	protected double cantidad;
   
	public abstract boolean puedeVenderPasajes(Vuelo vuelo);

	public abstract double getAsientosDisponiblesSegunCriterio(Vuelo vuelo);
	
		


}
