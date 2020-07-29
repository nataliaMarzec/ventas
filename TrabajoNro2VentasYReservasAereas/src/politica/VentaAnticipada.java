package politica;

import empresaAerea.Vuelo;

public class VentaAnticipada extends Politica{

	@Override
	public double getPrecioStandarSegunPolitica(Vuelo vuelo) {
		return this.getPrecioSegunPoliticaVtaAnticipada(vuelo);
	}

	private double getPrecioSegunPoliticaVtaAnticipada(Vuelo vuelo) {
		if( vuelo.getCantPasajesVendidos()<40){
			return  (vuelo.getPrecioStandard()*0.30);
		}else
			if(vuelo.getCantPasajesVendidos()>40 && vuelo.getCantPasajesVendidos()<79){
				return (vuelo.getPrecioStandard()*0.60);
			}else
				return vuelo.getPrecioStandard();
	}

}
