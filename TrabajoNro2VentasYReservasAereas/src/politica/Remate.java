package politica;

import empresaAerea.Vuelo;

public class Remate extends Politica{

	@Override
	public double getPrecioStandarSegunPolitica(Vuelo vuelo) {
		return this.getPrecioStandarSegunPoliticaRemate(vuelo);
	}

	public double getPrecioStandarSegunPoliticaRemate(Vuelo vuelo) {
		 if(vuelo.getCantidadAsientosLibres()>=30){
			  return (vuelo.getPrecioStandard()*0.25);
			  }else
				 return(vuelo.getPrecioStandard()*0.50);
		 }
		 
	}


