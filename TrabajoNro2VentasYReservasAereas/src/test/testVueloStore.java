package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import empresaAerea.Destino;
import vueloStore.VueloStore;

public class testVueloStore {

	private VueloStore vueloStore;

	@Before
	public void setUp() throws Exception {

		vueloStore = VueloStore.getStore();
		vueloStore.setVuelosIniciales();
	}
//Ejercicio 8--------------------------------------------------------------------------------------
	
	@Test
	public void fechasDelPasajeConDestinoYDni() {
	    List<LocalDate>fchas= new ArrayList<>();
	    LocalDate fcha1 = LocalDate.of(2022, Month.APRIL, 17);//esta es la fecha que coincide
	    
	    fchas.add(fcha1);
	    
    assertEquals(fchas,vueloStore.fechaConDestinoYDni(Destino.Paris, "29000999")); 
	    
//Ejercicio 9 -----------------------------------------------------------------------------------	   	    
 }
	@Test
	public void asientosParaDestino(){
		//vuelo2--charter 200asientos ocupados-->av2--390asientosDisponibles
		//public double getAsientosDisponibles() {
		//	return avion.390()-25-6()=359;
		//disponibles 359 - ocupados 200= 159
				
		assertEquals(166, vueloStore.cantAsientosParaDestino(LocalDate.of(2017, Month.JUNE, 12),LocalDate.of(2017, Month.JUNE, 18), Destino.Roma));
	}
//Ejercicio 10--------------------------------------------------------------------------------------
	//comparten 3 o mas vuelos
	@Test
	public void dosPersonasSonCompanieras() {
		assertTrue(vueloStore.sonCompanieras("34000000","55000555"));
	}
	@Test
	public void dosPersonasNoSonCompanieras(){
		assertFalse(vueloStore.sonCompanieras("29000999", "12444999"));
	}
	
	

}