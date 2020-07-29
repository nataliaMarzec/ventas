package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;

import criterioDeVentas.CriterioPorAmenazaTerrorista;
import criterioDeVentas.CriterioVenta;
import criterioDeVentas.CriterioVentaLaxa;
import criterioDeVentas.CriterioVentaPorPorcentaje;
import criterioDeVentas.CriterioVentaSegura;
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
import vueloStore.VueloStore;


 public class TestVuelos {
VueloStore store;
CriterioVenta porPorcentaje;CriterioVenta laxa;CriterioVenta segura;CriterioVenta terrorista;
Politica estricta;Politica remate;Politica ventaAnticipada;
Avion avion1;Avion avion2;Avion avion3;Avion avion4;Avion avion5;
Vuelo vuelo1;Vuelo vuelo2;Vuelo vuelo3;Vuelo vuelo4;Vuelo vuelo5;Vuelo vuelo6;Vuelo vuelo7;


@Before
	public void setUp()throws Exception{
	
	porPorcentaje=new CriterioVentaPorPorcentaje();
	laxa= new CriterioVentaLaxa();
	segura= new CriterioVentaSegura();
	terrorista= new CriterioPorAmenazaTerrorista();
	
	estricta=new Estricta();
	remate= new Remate();
	ventaAnticipada= new VentaAnticipada();
	
	avion1=new Avion(7, 200, 10000, 100);
	avion2=new Avion(3.7, 12000, 10000, 200);
	avion3=new Avion(6.8, 50000, 10008, 25);
	avion4=new Avion(3.9, 4000, 50000, 345);
	avion5=new Avion(5.7, 120, 32000, 129);
	//new Avion(alturaCabina, nafta, peso, cantAsientos)
	

	vuelo1=new Normal(avion1, 45, LocalDate.of(2019,Month.APRIL,13), null,Origen.BuenosAires,Destino.Tokio,23333);
	vuelo2=new Charter(avion2, 700, LocalDate.of(14, Month.FEBRUARY, 23), null,Origen.Rosario,Destino.Pekin,50555);
	vuelo3=new DeCarga(avion3, 456, LocalDate.of(2018, Month.DECEMBER, 24), null, Origen.MarDelPlata, Destino.Paris, 4444);
	vuelo4=new Normal(avion4, 800, LocalDate.of(2020, Month.SEPTEMBER,1), null, Origen.BuenosAires, Destino.Roma, 60666);
	vuelo5=new Charter(avion5,900,LocalDate.of(23, Month.MARCH, 20),null,Origen.Tandil,Destino.Roma,9990);
	vuelo6=new Normal(avion1,7899, LocalDate.of(2018, Month.JANUARY, 5), LocalDateTime.now(),Origen.Rosario,Destino.Pekin,7888);
	vuelo7=new Charter(avion5,900,LocalDate.of(23, Month.MARCH, 20),null,Origen.Tandil,Destino.Roma,9990);
	//new Normal(avion, asientosOcu, km, fchaVuelo, tiempo, origen, destino, precioSta)

}

//Ejercicio1
@Test
public void cantidadAsientosVueloNormal() {
	assertEquals(100,vuelo1.getCantidadAsientosLibres(),0.001);//125-50
} 
@Test
public void cantAsientosVueloCharter(){
    vuelo2.setPoliticaVenta(remate);
	assertEquals(169, vuelo2.getCantidadAsientosLibres(),0.001);//(200-25-6)-45=124
}
@Test
public void cantAsientosVueloDeCarga(){
	assertEquals(30, vuelo3.getCantidadAsientosLibres(),0.001);//30-10=5
}
//-------------------------------------------------------------------------------------------
//Ejercicio2--cabina mas de 4 y menos de 100 disponibles
@Test
	public void esVueloRelajado(){
	vuelo1.setPoliticaVenta(estricta);
	vuelo1.setCriteriosDeVenta(segura);
	vuelo1.venderPasaje(LocalDate.now(),"333");
	 assertTrue(vuelo1.getEsVueloRelajado());//cabina 7 y dispo 125
}
@Test 
	public void noEsVueloRelajado(){
	assertFalse(vuelo2.getEsVueloRelajado());//cabina 3.7 y dispo 200
}
//-------------------------------------------------------------------------------------------
//Ejercicio3 --si llamo directamente a la clase no funciona
@Test
	public void puedeVenderPasajesSegunCriterioVentaSegura(){
	vuelo1.setCriteriosDeVenta(segura);
	assertTrue(segura.puedeVenderPasajes(vuelo1));
	assertTrue(vuelo1.puedeVenderSegunCriterio());	
}
@Test
	public void puedeVenderPasajesSegunCriterioVentaLaxa(){
	vuelo2.setCriteriosDeVenta(laxa);
	assertTrue(laxa.puedeVenderPasajes(vuelo2));
	assertTrue(vuelo2.puedeVenderSegunCriterio());
}
@Test
	public void puedeVenderPasajesSegunCriterioVentaPorPorcentaje(){
	vuelo3.setCriteriosDeVenta(porPorcentaje);
	assertTrue(porPorcentaje.puedeVenderPasajes(vuelo3));
	assertTrue(vuelo3.puedeVenderSegunCriterio());
	
}
@Test
	public void noPuedeVenderPasajesSegunCriterioPorAmenazaTerrorista(){
	vuelo4.setCriteriosDeVenta(terrorista);
	assertFalse(terrorista.puedeVenderPasajes(vuelo4));
	assertFalse(vuelo4.puedeVenderSegunCriterio());
}
//------------------------------------------------------------------------------------------
//Ejercicio 4
@Test
	public void precioDeVentaEstrictaDePasaje(){
    vuelo4.setPoliticaVenta(estricta);
    assertEquals(60666, vuelo4.getPrecioStandardSegunPoliticaDeVenta(),0.001);
    assertNotEquals(888, vuelo4.getPrecioStandardSegunPoliticaDeVenta());
}
@Test 
	public void precioDeVentaAnticipasaDePasaje(){
	vuelo5.setPoliticaVenta(ventaAnticipada);
	assertEquals(2997, vuelo5.getPrecioStandardSegunPoliticaDeVenta(),0.001);
}
@Test
	public void precioDeVentaPorRemate(){
	vuelo3.setPoliticaVenta(estricta);
	assertEquals(30.0,vuelo3.getCantidadAsientosLibres(),0.001);
	//assertEquals(2222, vuelo3.getPrecioStandardSegunPoliticaDeVenta(),0.001);//--precioSt 4444*0.50=2222
}
//--------------------------------------------------------------------------------------------------
//Ejercicio 5
@Test
	public void ventaDeUnPasajeParaUnVueloVentaLaxa()throws Exception{
	vuelo5.setCriteriosDeVenta(laxa);
	assertTrue(vuelo5.puedeVenderSegunCriterio());
	//vuelo5.venderPasaje(LocalDate.of(2019, Month.NOVEMBER,4),"44444444");	
}
@Test
	public void ventaDeUnoPasajeParaUnVueloVentaSegura()throws Exception{
	vuelo6.setCriteriosDeVenta(segura);//debe ser <=3 y es 0
	//vuelo6.venderPasaje(LocalDate.of(2017, Month.DECEMBER, 27), "30444777");//no tira error
}

//--------------------------------------------------------------------------------------------
//Ejercicio 6
@Test
	public void importeTotalParaPasajesVendidos() throws Exception{
	 vuelo2.setPoliticaVenta(ventaAnticipada);
	 vuelo2.venderPasaje(LocalDate.of(2020, Month.APRIL, 23),"66000888");
	 vuelo2.venderPasaje(LocalDate.of(2019, Month.NOVEMBER, 23), "78999000");
	 vuelo2.venderPasaje(LocalDate.of(2020, Month.APRIL, 23),"60777444");
	 
	 assertEquals(3,vuelo2.getCantPasajesVendidos(),0.001);
	 assertEquals(45499.50, vuelo2.getImporteTotalDePasajesVendidos(),0.001);	 
}
//------------------------------------------------------------------------------------------------
//Ejercicio 7
@Test
	public void pesoMaximoEnVueloCharter() throws Exception{
	vuelo7.setPoliticaVenta(estricta);
	vuelo7.venderPasaje(LocalDate.of(2018, Month.APRIL,14), "25000486");
	vuelo7.venderPasaje(LocalDate.of(2018,Month.MARCH,3),"22999555");
	
	assertEquals(2, vuelo7.getCantPasajesVendidos(),0.001);
	//32000+90+5000+108000+80
	assertEquals(145140, vuelo7.pesoMaximoVuelo(),0.001);
}

 }
