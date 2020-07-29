package tiposDeVuelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import empresaAerea.Avion;
import empresaAerea.Destino;
import empresaAerea.Origen;
import empresaAerea.Vuelo;


public class Charter extends Vuelo{ //template method
	
	public Charter(Avion avion, double km,LocalDate fchaVuelo, LocalDateTime tiempo,
			Origen origen, Destino destino, double precioSta) {
		super(avion, km,fchaVuelo, tiempo, origen, destino, precioSta);
	}

	private static double pesoFijo=5000;
	private  double asientosAsignados=6;

@Override 
	public double getPesoDeLaCarga(){
	  return this.getPesoFijo();
}

    public double getPesoFijo() {
	   return pesoFijo;
}
    
    public static double getCantidadAsientosAsignada() {
		return asientosAsignados;
}

    public static void setCatidadPasajerosAsignada(int asientosAsignada) {
		Charter.asientosAsignados= asientosAsignada;
}


	@Override
	public double getAsientosDisponibles() {
		return avion.getCantidadAsientos()-25-getCantidadAsientosAsignada();
	}


	

}
