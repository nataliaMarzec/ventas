package criterioDeVentas;

import empresaAerea.Vuelo;

public class CriterioVentaPorPorcentaje extends CriterioVenta{
    protected double porcentaje=0.1;
 
	@Override
	public boolean puedeVenderPasajes(Vuelo vuelo) {
		return this.getAsientosDisponiblesSegunCriterio(vuelo) >= 1;
	}
	
	@Override
	public double getAsientosDisponiblesSegunCriterio(Vuelo vuelo) {
		return (int) (vuelo.getAsientosDisponibles() * 1.01);
	}
	
}
