package empresaAerea;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import criterioDeVentas.CriterioVenta;
import pasajes.Pasaje;
import politica.Politica;


public abstract class Vuelo {//template method
	
	/*1dejar solo una coleccion de pasajes--listo
	 * 2borrar pasajito--listo
	 * 3borrar pasajero --se lo calculo en pasajes--listo
	 * 4venderPasaje un solo metodo...ver en vender pasaje al comprador y vender --listo
	 * 5ver test--listo
	 * getCantAsientoLibres--listo..
	 * */
	
	
	protected Avion avion;//
	protected double cantAsientosDisponibles;
	private static CriterioVenta criteriosDeVenta;
	protected List<Pasaje>pasajesVendidos=new ArrayList<>();
	protected double precioStandard;//
	protected Politica politica;
	protected double distanciaARecorrer;
	protected Destino destino;//
	protected Origen origen;//
	protected LocalDate fechaVuelo;//
	protected LocalDateTime tiempoVuelo;
	
	
	
	public double pesoMaximoVuelo(){
    return this.avion.getPesoAvion()
    +this.getPesoTotalPasajeros()+this.getPesoDeLaCarga()+
    this.getPesoNafta()+ Asociacion.getIata().getPesoEquipamientoReglamentario();
	}	
	public double getPesoTotalPasajeros() {
		return this.getCantPasajesVendidos() * Asociacion.getIata().getPesoStandard();
	} 

    public abstract double getPesoDeLaCarga();
      
	public Vuelo(Avion avion,double km,LocalDate fechaVuelo
			,LocalDateTime tiempo,Origen origen,Destino destino,double precioSta){
		this.avion=avion;
		this.distanciaARecorrer=km;
		this.fechaVuelo=fechaVuelo;
		this.tiempoVuelo=tiempo;
		this.origen=origen;
		this.destino=destino;
		this.precioStandard=precioSta;
		
		
    }
	
    public boolean esDestinoEnFechas(LocalDate fchaIni, LocalDate fchaFin, Destino destino) {
		return this.esDestino(destino) && estaVueloEntreFechas(fchaIni, fchaFin);
	}
    public boolean esDestino(Destino destino) {
		return this.getDestino().equals(destino);
	}
	public boolean estaVueloEntreFechas(LocalDate fchaIni, LocalDate fechaFin) {
		return this.getFechaVuelo().isAfter(fchaIni) && this.getFechaVuelo().isBefore(fechaFin);
	}
    public double getPesoNafta(){
	 return (this.distanciaARecorrer)*(this.avion.getLitrosConsumidosPorKM());
   } 
    public double getPesoStandardIata(){
    	return Asociacion.getIata().getPesoStandard();
    }
    public double getCantPasajerosEnVuelo(){
    	return this.getCantPasajesVendidos();
    }
    public double getImporteTotalDePasajesVendidos() {
    	return this.getCantPasajesVendidos()*this.getPrecioStandardSegunPoliticaDeVenta();
	}
	
	public void venderPasaje(LocalDate fechaVta,String dni) {
		if(this.puedeVenderSegunCriterio()){
			pasajesVendidos.add(new Pasaje(this,fechaVta,this.getPrecioStandardSegunPoliticaDeVenta(),dni));
			
		}
		else{
			throw new RuntimeException("Error!!!..No se puede vender pasaje");
		}
		
	}
	public double getCantPasajesVendidos(){
    	return this.pasajesVendidos.size();
    }
	public List<Pasaje> PasajesVendidos() {
		return pasajesVendidos;
	}
    //strategy
    public double getPrecioStandardSegunPoliticaDeVenta(){
    	return politica.getPrecioStandarSegunPolitica(this);
    }
    public void setPoliticaVenta(Politica poli){
    	this.politica=poli;
    }
    public void setPrecioStandard(double precio){
    	this.precioStandard=precio;
    }
    public double getPrecioStandard(){
    	return this.precioStandard;
    }
       
    public CriterioVenta getCriteriosDeVenta() {
		return criteriosDeVenta;
	}
    
    //Singleton
	public static void setCriteriosDeVenta(CriterioVenta criteriosDeVenta) {
		Vuelo.criteriosDeVenta=criteriosDeVenta;
	}
	
	public boolean getEsVueloRelajado(){
		return (this.getCantidadAsientosLibres()<=100 && avion.getAlturaDeCabina()>=4);	
	}
	public double getCantidadAsientosLibres() {
		return this.getAsientosDisponibles()-this.getAsientosOcupados();
	}	
	public double getCantidadAsientosLibresSegunCriterio(){
		return criteriosDeVenta.getAsientosDisponiblesSegunCriterio(this);
	}
	public boolean puedeVenderSegunCriterio() {
		return (Vuelo.criteriosDeVenta.puedeVenderPasajes(this));
	}
	
	public double getAsientosOcupados(){
		return this.getCantPasajesVendidos();
	}
	public abstract double getAsientosDisponibles();
	
    public double getRecorreKm() {
			return distanciaARecorrer;
		}
    public void setRecorreKm(double distanciaARecorrer) {
			this.distanciaARecorrer = distanciaARecorrer;
		}
    public LocalDate getFechaVuelo() {
		return fechaVuelo;
	}
	public void setFechaVuelo(LocalDate fechaVuelo) {
		this.fechaVuelo = fechaVuelo;
	}
	public LocalDateTime getHorario(){
		return tiempoVuelo;
	}
	public void setHorario(LocalDateTime tiempo){
		this.tiempoVuelo= tiempo;
	}
    
   public double getDistanciaArecorrer(){
	   return this.distanciaARecorrer;
   }
   public void setDestino(Destino destino){
	   this.destino=destino;	   
   }
   public Destino getDestino(){
	  return this.destino; 
   }
  public boolean viajaPasajeroConDni(String dni) {                    
      return this.pasajesVendidos.stream().anyMatch(p->p.getDni().equals(dni));
}

	
}
