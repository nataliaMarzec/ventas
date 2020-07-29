package criterioDeVentas;

import empresaAerea.Vuelo;

public class CriterioVentaSegura extends CriterioVenta{
	protected double cantidad=0;
    
@Override
	public boolean puedeVenderPasajes(Vuelo vuelo) {
		return (vuelo.getAsientosDisponibles()>=3);
	}
@Override
	public double getAsientosDisponiblesSegunCriterio(Vuelo vuelo) {
		if(vuelo.getAsientosDisponibles()>0 && vuelo.getAsientosDisponibles()<3){
			return this.cantidad=vuelo.getAsientosDisponibles();
		}
		return cantidad;		
}
	

}
