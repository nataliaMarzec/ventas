package criterioDeVentas;

import empresaAerea.Vuelo;

public class CriterioVentaLaxa extends CriterioVenta{
@Override
	public boolean puedeVenderPasajes(Vuelo vuelo) {
		return this.getAsientosDisponiblesSegunCriterio(vuelo)>1;
	}
	
@Override
	public double getAsientosDisponiblesSegunCriterio(Vuelo vuelo) {
		return vuelo.getAsientosDisponibles()+10;
	
	}	
}
