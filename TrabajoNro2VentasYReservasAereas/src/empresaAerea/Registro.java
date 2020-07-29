package empresaAerea;

import java.util.ArrayList;
import java.util.Collection;

public class Registro {
	protected Collection<Vuelo>vuelos=new ArrayList<>();
	
	public Registro(){};
	
	public void setVuelos(Vuelo vuelo){
    	this.vuelos.add(vuelo);
    }
	public  Collection<Vuelo> getVuelos(){
		return this.vuelos;
	}
    
    public Collection<Vuelo> valorTotal(){
    	return this.getVuelos();
    }
    
	
	
    
    
    
}