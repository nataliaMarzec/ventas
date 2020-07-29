package empresaAerea;


public class Pasajero {
	
	protected String DNI;
	
	public void setDNI(String dni){
		this.DNI=dni;
	}
	public String getDni(){
		return this.DNI;
	}
   public boolean tieneSacadoPasaje(){
	   return true;
   }

   

}
