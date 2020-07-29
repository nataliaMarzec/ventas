package vueloStore;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import criterioDeVentas.CriterioVenta;
import criterioDeVentas.CriterioVentaLaxa;
import empresaAerea.Avion;
import empresaAerea.Destino;
import empresaAerea.Origen;
import empresaAerea.Vuelo;
import politica.Estricta;
import politica.Politica;
import politica.Remate;
import politica.VentaAnticipada;
import tiposDeVuelo.Charter;
import tiposDeVuelo.DeCarga;
import tiposDeVuelo.Normal;


public class VueloStore {
	
	protected Politica politica;
	
	private static VueloStore store= new VueloStore();
	
	
	private List<Vuelo>listaVuelos;


		
	public static VueloStore getStore() {
		return store;
	}

	public static void setStore(VueloStore store) {
		VueloStore.store = store;
	}

	public VueloStore (){
		
		this.listaVuelos = new ArrayList<>();
	}
	
	public List <Vuelo>getListaVuelos(){
		return listaVuelos;
	}
	
	public void setVuelosIniciales() throws Exception{
		this.listaVuelos.removeAll(listaVuelos);
		
		LocalDate fchaVuelo1 = LocalDate.of(2022, Month.APRIL,17);
		LocalDate fchaVuelo2 = LocalDate.of(2017,Month.JUNE,15);
		LocalDate fchaVuelo3 = LocalDate.of(2018, Month.FEBRUARY,9);
		
		Politica remate=new Remate();Politica ventaAntic= new VentaAnticipada();Politica estricta=new Estricta();
		
		CriterioVenta crit1= new CriterioVentaLaxa();
		
		Avion avi01=new Avion(15, 400, 700, 390);
		Avion avi02=new Avion(23, 890, 400, 200);
		Avion avi03=new Avion(12, 500, 900, 245);
		//new Avion(alturaCabina, nafta, peso, cantAsientos)
		//new Normal(avion, asientosOcu, km, fchaVuelo, tiempo, origen, destino, precioSta)
		
		Vuelo vuelo1= new Normal(avi01, 567, fchaVuelo1, null,Origen.BuenosAires, Destino.Paris, 5999);
		Vuelo vuelo2= new Charter(avi02, 700, fchaVuelo2, null,Origen.Rosario, Destino.Roma, 7900);
		Vuelo vuelo3= new DeCarga(avi03, 798, fchaVuelo3, null,Origen.Tandil, Destino.Pekin, 5000);
		
		vuelo1.setPoliticaVenta(remate);
		vuelo2.setPoliticaVenta(ventaAntic);
		vuelo3.setPoliticaVenta(estricta);
		
		Vuelo.setCriteriosDeVenta(crit1);
		
		vuelo1.venderPasaje(LocalDate.of(2022, Month.APRIL, 17), "29000999");
		vuelo1.venderPasaje(LocalDate.now(), "34000000");
		vuelo1.venderPasaje(LocalDate.now(),"55000555");
		
		vuelo2.venderPasaje(LocalDate.of(2017,Month.MAY,2),"55000555");
		vuelo2.venderPasaje(LocalDate.now().minusDays(2),"12444999");
		vuelo2.venderPasaje(LocalDate.of(2017, Month.MAY, 2),"34000000");
		
		vuelo3.venderPasaje(LocalDate.now().minusWeeks(4), "49999999");
		vuelo3.venderPasaje(LocalDate.now().minusMonths(7), "60000000");
		vuelo3.venderPasaje(LocalDate.of(2019, Month.SEPTEMBER, 21), "55000555");
		vuelo3.venderPasaje(LocalDate.of(2019, Month.SEPTEMBER, 21), "34000000");
		
		listaVuelos.add(vuelo1); 
		listaVuelos.add(vuelo2);
		listaVuelos.add(vuelo3);
							
		}

	public List<LocalDate> fechaConDestinoYDni(Destino desti, String dni) {
		return listaVuelos.stream().filter(v -> v.getDestino().equals(desti) && v.viajaPasajeroConDni(dni))
				.collect(Collectors.toList()).stream().map(p -> p.getFechaVuelo()).collect(Collectors.toList());

	}

	public int cantAsientosParaDestino(LocalDate fchaIni, LocalDate fchaFin, Destino desti) {
		return (int) listaVuelos.stream().filter(v->v.esDestinoEnFechas(fchaIni, fchaFin, desti))
				.collect(Collectors.toList()).stream().mapToDouble(v->v.getCantidadAsientosLibres()).sum();

	}

	public boolean sonCompanieras(String dni1, String dni2) {
		return listaVuelos.stream().filter(v-> v.viajaPasajeroConDni(dni1) && v.viajaPasajeroConDni(dni2))
				.collect(Collectors.toList()).size() >= 3;
	}
	
}
