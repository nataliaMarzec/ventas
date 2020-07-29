package politica;

import empresaAerea.Vuelo;

public class Estricta extends Politica{

	@Override
	public double getPrecioStandarSegunPolitica(Vuelo vuelo) {
		return this.getPrecioSegunPoliticaEstricta(vuelo);
	}

	private double getPrecioSegunPoliticaEstricta(Vuelo vuelo) {
		return vuelo.getPrecioStandard();
	}
        

	
	
}
