package empresaAerea;

public class Asociacion {//Singleton
	private static Asociacion Iata=new Asociacion();
	
    private double pesoStandard=30;
    private double pesoEquipamientoReglamentario=80;
    
	public static Asociacion getIata() {
		return Iata;
	}
    
    public double getPesoStandard(){
    	return pesoStandard;
    }  

    public double getPesoEquipamientoReglamentario(){
    	return pesoEquipamientoReglamentario;
    }
}
