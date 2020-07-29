package pasajes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import empresaAerea.Destino;
import empresaAerea.Vuelo;

public class Pasaje {
	
	protected LocalDate fechaDeVenta;
	protected String dni;
	protected Collection<Vuelo>vuelos=new ArrayList<>();
	protected double precioPasaje;
	protected Vuelo vuelo;
	
	


	public Pasaje(Vuelo vuelo,LocalDate fechaVta,double precio,String dni) {
		this.vuelo=vuelo;
		this.fechaDeVenta=fechaVta;
		this.precioPasaje=precio;
		this.dni=dni;
	}
	
	public List<LocalDate>getFechaConDestinoYConDni(Destino destino,String dni){//TIPO FECHA
		return this.vuelos.stream().filter(v->(v.getDestino().equals(destino) &&
			    v.viajaPasajeroConDni(dni))).collect(Collectors.toList()).
				stream().map(v->v.getFechaVuelo()).collect(Collectors.toList());
	}
	
	//ver getPesoPasajero !!!y tambien el static de iata
	public double getPesoPasajeros(Vuelo vuelo){
		return vuelo.getCantPasajerosEnVuelo()*vuelo.getPesoStandardIata();
	}
	public double getPrecioPasaje(Vuelo vuelo){
		return vuelo.getPrecioStandardSegunPoliticaDeVenta();
	}
	public double getPrecio(){
		return this.precioPasaje;
	}
		
	public void setPrecio(double precio){
		this.precioPasaje=precio;
	}
	public void setDni(String dni){
		this.dni=dni;
	}
	public String getDni(){
		return dni;
	}
	
}
