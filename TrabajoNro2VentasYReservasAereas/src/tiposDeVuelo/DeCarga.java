package tiposDeVuelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import empresaAerea.Avion;
import empresaAerea.Destino;
import empresaAerea.Origen;
import empresaAerea.Vuelo;

public class DeCarga extends Vuelo{//template method
	
    public DeCarga(Avion avion, double km, LocalDate fchaVuelo, LocalDateTime tiempo,
			Origen origen, Destino destino, double precioSta) {
		super(avion, km,fchaVuelo, tiempo, origen, destino, precioSta);
	}

	protected static double pesoEquipamientoSeguridad=700;
    protected double pesoCarga;
    
	
	protected double getPesoEquipamientoSeguridad() {
		return pesoEquipamientoSeguridad;
	}

	@Override 
	public double getPesoDeLaCarga(){
		return this.getPesoCarga()+this.getPesoEquipamientoSeguridad();
		
	}
	
	protected void setPesoCarga(double pesoC){
		this.pesoCarga=pesoC;
	}
	protected double getPesoCarga(){
		return pesoCarga;
	}
	
	@Override
	public double getAsientosDisponibles() {
		return 30;
	}
	
}
