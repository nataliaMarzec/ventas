package criterioDeVentas;

import empresaAerea.Vuelo;

public class CriterioPorAmenazaTerrorista extends CriterioVenta{

	@Override
	public boolean puedeVenderPasajes(Vuelo vuelo) {
		return false;
	}
	@Override
	public double getAsientosDisponiblesSegunCriterio(Vuelo vuelo) {
		return 0;
	}
	
	
	
	
	
	
}
