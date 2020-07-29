package empresaAerea;

public class Avion {
 
 protected double cantidadAsientos;
 protected double alturaDeCabina;
 protected double pesoDeAvion;
 protected double distanciaMax;
 protected double litrosNaftaQueConsumePorKm;
 
 
 
   public Avion(double alturaCabina,double nafta,double peso,double cantAsientos){
	   this.alturaDeCabina=alturaCabina;   
	   this.litrosNaftaQueConsumePorKm=nafta;
	   this.pesoDeAvion=peso;
	   this.cantidadAsientos=cantAsientos;
	   
   }  
   
   public double getLitrosConsumidosPorKM() {
	   return litrosNaftaQueConsumePorKm;
   }

   public double getPesoAvion(){
	   return pesoDeAvion;
   }
   
   public double getCantidadAsientos() {
		return cantidadAsientos;
	}
	public double getAlturaDeCabina() {
		return alturaDeCabina;
	}
	
	
	
	
}
