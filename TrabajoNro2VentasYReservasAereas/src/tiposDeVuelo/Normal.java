package tiposDeVuelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import empresaAerea.Asociacion;
import empresaAerea.Avion;
import empresaAerea.Destino;
import empresaAerea.Origen;
import empresaAerea.Vuelo;

public class Normal extends Vuelo{//template method

	
public Normal(Avion avion, double km, LocalDate fchaVuelo, LocalDateTime tiempo,
			Origen origen, Destino destino, double precioSta) {
		super(avion, km, fchaVuelo, tiempo, origen, destino, precioSta);
		
	}

@Override
	public double getPesoDeLaCarga(){
		return this.getCantPasajerosEnVuelo()*Asociacion.getIata().getPesoEquipamientoReglamentario();
	}

@Override
    public double getAsientosDisponibles() {
	  return avion.getCantidadAsientos();
}
	    	

}
