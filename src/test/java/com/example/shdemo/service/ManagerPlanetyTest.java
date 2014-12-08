package com.example.shdemo.service;



import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Planeta;
import com.example.shdemo.domain.UkladPlanetarny;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ManagerPlanetyTest {
	
	@Autowired
	ManagerPlanety managerPlanety;
	//planeta
	private final String nazwa1 = "Mars";
	private final String nazwa2 = "Jowisz";
	
	private final int srednica1 = 5433;
	private final int srednica2 = 1234;
	
	private final int il_ks1 = 2;
	private final int il_ks2 = 43;
	
	private int SrednicaUnique = 565656;
	
	
	//Uklad
	private final String nazwaU1 = "Uklad Sloneczny";
	private final String nazwaU2 = "Uklad Kepler";
	
	private final int liczbaObiektow1 = 5;
	private final int liczbaObiektow2 = 7; 
	private final int liczbaObiektowUnique = 10;
	
	
	@Test
	public void addPlanetaCheck() {
		
		managerPlanety.dodaniePlanet();
		
		Planeta planeta = new Planeta();
		planeta.setNazwa(nazwa1);
		planeta.setSrednica(srednica1);
		planeta.setIl_ks(il_ks1);
		
		List<Planeta> listaPlanet = managerPlanety.getAllPlanety();
		int liczbaPlanetprzedDodaniem = listaPlanet.size();
		//System.out.println(liczbaPlanetprzedDodaniem);
		
		managerPlanety.addPlaneta(planeta);
		
		List<Planeta> listaPlanet2 = managerPlanety.getAllPlanety();
		int liczbaPlanetpoDodaniu = listaPlanet2.size();
		
		for (Planeta planetaPetla : listaPlanet) {
			if (planetaPetla.getId().equals(planeta.getId())) {
				assertEquals(nazwa1,planetaPetla.getNazwa());
				assertEquals(srednica1,planetaPetla.getSrednica());
				assertEquals(il_ks1,planetaPetla.getIl_ks());
			}
		}
		
		assertEquals(liczbaPlanetprzedDodaniem+1, liczbaPlanetpoDodaniu);
	
	}
	
	
	@Test
	public void deletePlanetaCheck() {
		
		managerPlanety.dodaniePlanet();
		
		Planeta planeta = new Planeta();
		planeta.setNazwa(nazwa1);
		planeta.setSrednica(srednica1);
		planeta.setIl_ks(il_ks1);
		managerPlanety.addPlaneta(planeta);
		List<Planeta> listaPlanet = managerPlanety.getAllPlanety();
		int liczbaPlanetprzedUsunieciem = listaPlanet.size();
		
		managerPlanety.deletePlaneta(planeta);
		
		List<Planeta> listaPlanet2 = managerPlanety.getAllPlanety();
		int liczbaPlanetpoUsunieciu = listaPlanet2.size();
		
		assertEquals(liczbaPlanetprzedUsunieciem, liczbaPlanetpoUsunieciu+1);
		
		
		assertEquals(null, managerPlanety.findPlanetaById(planeta.getId()));
		
		
			
	}
	
	@Test
	public void findPlanetaCheck() {
		managerPlanety.dodaniePlanet();
		List<Planeta> listaPlanet = managerPlanety.getAllPlanety();
		int liczbaPlanetprzedDodaniem = listaPlanet.size();
		
		Planeta planeta = new Planeta();
		planeta.setNazwa(nazwa1);
		planeta.setSrednica(srednica1);
		planeta.setIl_ks(il_ks1);
		
		managerPlanety.addPlaneta(planeta);
		assertNotNull(managerPlanety.findPlanetaById(planeta.getId()));
		
		managerPlanety.deletePlaneta(planeta);
		assertNull(managerPlanety.findPlanetaById(planeta.getId()));
		
		List<Planeta> listaPlanet2 = managerPlanety.getAllPlanety();
		int liczbaPlanetpoDodaniu = listaPlanet2.size();
		
		assertEquals(liczbaPlanetprzedDodaniem, liczbaPlanetpoDodaniu);
	}
	
	@Test
		public void findAllPlanetyCheck() {
		
		managerPlanety.dodaniePlanet();
		
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa2);
		planeta1.setSrednica(srednica2);
		planeta1.setIl_ks(il_ks2);
		
		List<Planeta> listaPlanet = managerPlanety.getAllPlanety();
		int liczbaPlanetprzedDodaniem = listaPlanet.size();
		
		Planeta planeta2 = new Planeta();
		planeta2.setNazwa(nazwa2);
		planeta2.setSrednica(srednica2);
		planeta2.setIl_ks(il_ks2);
		
		managerPlanety.addPlaneta(planeta1);
		managerPlanety.addPlaneta(planeta2);
		
		List<Planeta> listaPlanet2 = managerPlanety.getAllPlanety();
		int liczbaPlanetpoDodaniu = listaPlanet2.size();
		//assertEquals(liczbaPlanetprzedDodaniem, liczbaPlanetpoDodaniu-2);
		
		assertNotNull(listaPlanet);
		
		//int liczbaPlanet = listaPlanet.size();
		
		assertEquals(liczbaPlanetpoDodaniu, liczbaPlanetprzedDodaniem+2);
	}
	
	
	@Test
	public void editPlanetaCheck() {
		
		managerPlanety.dodaniePlanet();
		List<Planeta> listaPlanet = managerPlanety.getAllPlanety();
		int liczbaPlanetprzedDodaniem = listaPlanet.size();
		
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa2);
		planeta1.setSrednica(srednica2);
		planeta1.setIl_ks(il_ks2);
		
		Planeta planeta2 = new Planeta();
		planeta2.setNazwa(nazwa2);
		planeta2.setSrednica(srednica2);
		planeta2.setIl_ks(il_ks2);
		
		Planeta planeta3 = new Planeta();
		planeta3.setNazwa(nazwa2);
		planeta3.setSrednica(srednica2);
		planeta3.setIl_ks(il_ks2);
		
		managerPlanety.addPlaneta(planeta1);
		managerPlanety.addPlaneta(planeta2);
		managerPlanety.addPlaneta(planeta3);
		
		List<Planeta> listaPlanet2 = managerPlanety.getAllPlanety();
		int liczbaPlanetpoDodaniu = listaPlanet2.size();
		assertEquals(liczbaPlanetprzedDodaniem+3, liczbaPlanetpoDodaniu);
		
		/*int i;
		for(i=0; i < liczbaPlanetpoDodaniu; i++){
			assertEquals(nazwa2, listaPlanet.get(i).getNazwa());
			assertEquals(srednica2, listaPlanet.get(i).getSrednica());
			assertEquals(il_ks2, listaPlanet.get(i).getIl_ks());
		}*/
		
		planeta2.setNazwa(nazwa2);
		planeta2.setSrednica(srednica1);
		planeta2.setIl_ks(il_ks1);
		managerPlanety.editPlaneta(planeta2);
		
		Long idPlanety = planeta2.getId();
		//Long indeks = (long)0;
		
		Planeta pl = managerPlanety.findPlanetaById(idPlanety);
		
		assertEquals(nazwa2, pl.getNazwa());
		assertEquals(srednica1, pl.getSrednica());
		assertEquals(il_ks1, pl.getIl_ks());
		
		assertEquals(nazwa2, planeta1.getNazwa());
		assertEquals(srednica2, planeta1.getSrednica());
		assertEquals(il_ks2, planeta1.getIl_ks());
		
		assertEquals(nazwa2, planeta3.getNazwa());
		assertEquals(srednica2, planeta3.getSrednica());
		assertEquals(il_ks2, planeta3.getIl_ks());
		
	}
	
	@Test
	public void findPlanetaBySrednicaCheck() {
		
		managerPlanety.dodaniePlanet();
		List<Planeta> listaPlanet = managerPlanety.getAllPlanety();
		int liczbaPlanetprzedDodaniem = listaPlanet.size();
		
		Planeta planeta = new Planeta();
		planeta.setNazwa(nazwa1);
		planeta.setSrednica(SrednicaUnique);
		planeta.setIl_ks(il_ks1);
		
		managerPlanety.addPlaneta(planeta);
		assertNotNull(managerPlanety.findPlanetaById(planeta.getId()));
		
		List<Planeta> listaPlanet2 = managerPlanety.getAllPlanety();
		int liczbaPlanetpoDodaniu = listaPlanet2.size();
		
		assertEquals(liczbaPlanetpoDodaniu, liczbaPlanetprzedDodaniem+1);
		
		assertNotNull(managerPlanety.findPlanetaBySrednica(planeta.getSrednica()));
		List<Planeta> pl = managerPlanety.findPlanetaBySrednica(planeta.getSrednica());
		Planeta pl2 = pl.get(0);
		assertEquals(SrednicaUnique, pl2.getSrednica());
		
	}
	
	
	
	
	//Testowanie Ukladow
	
	@Test
	public void addUkladCheck() {
		
		managerPlanety.dodanieUkladow();
		
		//System.out.println(liczbaPlanetprzedDodaniem);
		
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa2);
		planeta1.setSrednica(srednica2);
		planeta1.setIl_ks(il_ks2);
		managerPlanety.addPlaneta(planeta1);
		List<Planeta> planetyTab = new ArrayList<Planeta>();
		planetyTab.add(planeta1);
		
		UkladPlanetarny uklad = new UkladPlanetarny();
		uklad.setNazwaUkladu(nazwaU1);
		uklad.setLiczbaObiektow(liczbaObiektow1);
		uklad.setPlanety(planetyTab);
		
		List<UkladPlanetarny> listaUkladow = managerPlanety.getAllUklady();
		int liczbaUkladowprzedDodaniem = listaUkladow.size();
		//System.out.println(liczbaUkladowprzedDodaniem);
		
		managerPlanety.addUkladPlanetarny(uklad);
		
		List<UkladPlanetarny> listaUkladow2 = managerPlanety.getAllUklady();
		int liczbaUkladowpoDodaniu = listaUkladow2.size();
		//System.out.println(liczbaUkladowpoDodaniu);
		
		Long id = uklad.getId();
		UkladPlanetarny uk = managerPlanety.findUkladPlanetarnyById(id);
		
		assertEquals(uk.getNazwaUkladu(), uklad.getNazwaUkladu());
		assertEquals(uk.getLiczbaObiektow(), uklad.getLiczbaObiektow());
		assertEquals(uk.getPlanety(), uklad.getPlanety());
		
		assertEquals(liczbaUkladowprzedDodaniem+1, liczbaUkladowpoDodaniu);
		
		List<Planeta> planetyUkladu = new ArrayList<Planeta>();
		planetyUkladu = uk.getPlanety();
		Planeta pl = planetyUkladu.get(0);
		assertEquals(planeta1.getNazwa(), pl.getNazwa());
		assertEquals(planeta1.getSrednica(), pl.getSrednica());
		assertEquals(planeta1.getIl_ks(), pl.getIl_ks());
	
	}
	
	
	@Test
	public void deleteUkladCheck() {
		
		managerPlanety.dodanieUkladow();
		
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa1);
		planeta1.setSrednica(srednica1);
		planeta1.setIl_ks(il_ks1);
		managerPlanety.addPlaneta(planeta1);
		
		Planeta planeta2 = new Planeta();
		planeta2.setNazwa(nazwa1);
		planeta2.setSrednica(srednica1);
		planeta2.setIl_ks(il_ks1);
		managerPlanety.addPlaneta(planeta2);
		
		List<Planeta> planetyTab = new ArrayList<Planeta>();
		planetyTab.add(planeta1);
		planetyTab.add(planeta2);
		
		UkladPlanetarny uklad = new UkladPlanetarny();
		uklad.setNazwaUkladu(nazwaU1);
		uklad.setLiczbaObiektow(liczbaObiektow1);
		uklad.setPlanety(planetyTab);
		managerPlanety.addUkladPlanetarny(uklad);
		
		List<UkladPlanetarny> listaUkladow = managerPlanety.getAllUklady();
		int liczbaUkladowprzedUsunieciem = listaUkladow.size();
		
		managerPlanety.deleteUkladPlanetarny(uklad);
		
		List<UkladPlanetarny> listaUkladow2 = managerPlanety.getAllUklady();
		int liczbaUkladowpoUsunieciu = listaUkladow2.size();
		
		assertEquals(liczbaUkladowprzedUsunieciem, liczbaUkladowpoUsunieciu+1);
		assertEquals(null, managerPlanety.findUkladPlanetarnyById(uklad.getId()));
		
		
		assertEquals(null, managerPlanety.findPlanetaById(planeta1.getId()));
		assertEquals(null, managerPlanety.findPlanetaById(planeta2.getId()));
	}
	
	
	@Test
	public void findUkladCheck() {
		
		managerPlanety.dodanieUkladow();
		
		List<UkladPlanetarny> listaUkladow = managerPlanety.getAllUklady();
		int liczbaUkladowprzedDodaniem = listaUkladow.size();
		
		Planeta planeta = new Planeta();
		planeta.setNazwa(nazwa1);
		planeta.setSrednica(srednica1);
		planeta.setIl_ks(il_ks1);
		managerPlanety.addPlaneta(planeta);
		
		List<Planeta> planetyTab = new ArrayList<Planeta>();
		planetyTab.add(planeta);
		
		UkladPlanetarny uklad = new UkladPlanetarny();
		uklad.setNazwaUkladu(nazwaU1);
		uklad.setLiczbaObiektow(liczbaObiektow1);
		uklad.setPlanety(planetyTab);
		managerPlanety.addUkladPlanetarny(uklad);
		
		assertNotNull(managerPlanety.findUkladPlanetarnyById(uklad.getId()));
		assertNotNull(managerPlanety.findPlanetaById(planeta.getId()));
		
		managerPlanety.deleteUkladPlanetarny(uklad);
		assertNull(managerPlanety.findPlanetaById(planeta.getId()));
		assertNull(managerPlanety.findUkladPlanetarnyById(uklad.getId()));
		
		List<UkladPlanetarny> listaUkladow2 = managerPlanety.getAllUklady();
		int liczbaUkladowPoUsunieciu = listaUkladow2.size();
		
		assertEquals(liczbaUkladowprzedDodaniem, liczbaUkladowPoUsunieciu);
	}
	
	@Test
	public void findAllUkladyCheck() {
	
		managerPlanety.dodanieUkladow();
	
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa2);
		planeta1.setSrednica(srednica2);
		planeta1.setIl_ks(il_ks2);
	
		Planeta planeta2 = new Planeta();
		planeta2.setNazwa(nazwa2);
		planeta2.setSrednica(srednica2);
		planeta2.setIl_ks(il_ks2);
	
		managerPlanety.addPlaneta(planeta1);
		managerPlanety.addPlaneta(planeta2);
	
		List<Planeta> planetyTab = new ArrayList<Planeta>();
		planetyTab.add(planeta1);
		planetyTab.add(planeta2);
	
		UkladPlanetarny uklad = new UkladPlanetarny();
		uklad.setNazwaUkladu(nazwaU2);
		uklad.setLiczbaObiektow(liczbaObiektow2);
		uklad.setPlanety(planetyTab);
	
	
		List<UkladPlanetarny> listaUkladow = managerPlanety.getAllUklady();
		int liczbaUkladowprzedDodaniem = listaUkladow.size();
	
		managerPlanety.addUkladPlanetarny(uklad);
	
		List<UkladPlanetarny> listaUkladow2 = managerPlanety.getAllUklady();
		int liczbaUkladowpoDodaniu = listaUkladow2.size();
		//assertEquals(liczbaPlanetprzedDodaniem, liczbaPlanetpoDodaniu-2);
	
		assertNotNull(listaUkladow);
	
		//int liczbaPlanet = listaPlanet.size();
	
		assertEquals(liczbaUkladowpoDodaniu, liczbaUkladowprzedDodaniem+1);
	}
	
	
	@Test
	public void editUkladCheck() {
		
		managerPlanety.dodanieUkladow();
		List<UkladPlanetarny> listaUkladow1 = managerPlanety.getAllUklady();
		int liczbaUkladowprzedDodaniem = listaUkladow1.size();
		
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa2);
		planeta1.setSrednica(srednica2);
		planeta1.setIl_ks(il_ks2);
		
		Planeta planeta2 = new Planeta();
		planeta2.setNazwa(nazwa2);
		planeta2.setSrednica(srednica2);
		planeta2.setIl_ks(il_ks2);
				
		managerPlanety.addPlaneta(planeta1);
		managerPlanety.addPlaneta(planeta2);
		
		List<Planeta> planetyTab1 = new ArrayList<Planeta>();
		planetyTab1.add(planeta1);
		
		UkladPlanetarny uklad1 = new UkladPlanetarny();
		uklad1.setNazwaUkladu(nazwaU1);
		uklad1.setLiczbaObiektow(liczbaObiektow1);
		uklad1.setPlanety(planetyTab1);
		managerPlanety.addUkladPlanetarny(uklad1);
		
		List<Planeta> planetyTab2 = new ArrayList<Planeta>();
		planetyTab2.add(planeta2);
		
		UkladPlanetarny uklad2 = new UkladPlanetarny();
		uklad2.setNazwaUkladu(nazwaU2);
		uklad2.setLiczbaObiektow(liczbaObiektow2);
		uklad2.setPlanety(planetyTab2);
		managerPlanety.addUkladPlanetarny(uklad2);

		
		List<UkladPlanetarny> listaUkladow2 = managerPlanety.getAllUklady();
		int liczbaUkladowpoDodaniu = listaUkladow2.size();
		assertEquals(liczbaUkladowprzedDodaniem+2, liczbaUkladowpoDodaniu);
		
		
		uklad2.setNazwaUkladu(nazwa1);
		uklad2.setLiczbaObiektow(liczbaObiektow1);
		
		managerPlanety.editUkladPlanetarny(uklad2);
		
		Long idUkladu = uklad2.getId();
		
		UkladPlanetarny uk = managerPlanety.findUkladPlanetarnyById(idUkladu);
		
		assertEquals(nazwa1, uk.getNazwaUkladu());
		assertEquals(liczbaObiektow1, uk.getLiczbaObiektow());
		
	}
	
	
	@Test
	public void findUkladByLiczbaObiektow() {
		
		managerPlanety.dodanieUkladow();
		List<UkladPlanetarny> listaUkladow1 = managerPlanety.getAllUklady();
		int liczbaUkladowprzedDodaniem = listaUkladow1.size();
		
		Planeta planeta = new Planeta();
		planeta.setNazwa(nazwa1);
		planeta.setSrednica(SrednicaUnique);
		planeta.setIl_ks(il_ks1);		
		managerPlanety.addPlaneta(planeta);
		
		List<Planeta> planetyTab1 = new ArrayList<Planeta>();
		planetyTab1.add(planeta);
		
		UkladPlanetarny uklad1 = new UkladPlanetarny();
		uklad1.setNazwaUkladu(nazwaU1);
		uklad1.setLiczbaObiektow(liczbaObiektowUnique);
		uklad1.setPlanety(planetyTab1);
		managerPlanety.addUkladPlanetarny(uklad1);
		
		
		assertNotNull(managerPlanety.findPlanetaById(planeta.getId()));
		assertNotNull(managerPlanety.findUkladPlanetarnyById(uklad1.getId()));
		
		List<UkladPlanetarny> listaUkladow2 = managerPlanety.getAllUklady();
		int liczbaUkladowpoDodaniu = listaUkladow2.size();
		
		assertEquals(liczbaUkladowpoDodaniu, liczbaUkladowprzedDodaniem+1);
		
		assertNotNull(managerPlanety.findUkladPlanetarnyByLiczbaObiektow(uklad1.getLiczbaObiektow()));
		List<UkladPlanetarny> uk = managerPlanety.findUkladPlanetarnyByLiczbaObiektow(uklad1.getLiczbaObiektow());
		UkladPlanetarny uk2 = uk.get(0);
		assertEquals(liczbaObiektowUnique, uk2.getLiczbaObiektow());
		
	}
	
	//test pobrania obiektow(x) nalezacych do y 
	@Test
	public void checkPobranieWszystkichObiektowZUkladu() {
		Planeta planeta1 = new Planeta();
		planeta1.setNazwa(nazwa1);
		planeta1.setSrednica(srednica1);
		planeta1.setIl_ks(il_ks1);
		
		Planeta planeta2 = new Planeta();
		planeta2.setNazwa(nazwa2);
		planeta2.setSrednica(srednica2);
		planeta2.setIl_ks(il_ks2);
				
		managerPlanety.addPlaneta(planeta1);
		managerPlanety.addPlaneta(planeta2);
		
		List<Planeta> planetyTab1 = new ArrayList<Planeta>();
		planetyTab1.add(planeta1);
		planetyTab1.add(planeta2);
		
		UkladPlanetarny uklad1 = new UkladPlanetarny();
		uklad1.setNazwaUkladu(nazwaU1);
		uklad1.setLiczbaObiektow(liczbaObiektow1);
		uklad1.setPlanety(planetyTab1);
		managerPlanety.addUkladPlanetarny(uklad1);
		
		List<Planeta> listaPlanetUkladu = managerPlanety.getOwnedPlanety(uklad1);
		assertNotNull(listaPlanetUkladu);
		
		assertEquals(listaPlanetUkladu.size(), 2);
		
		Planeta pl1 = listaPlanetUkladu.get(0);
		Planeta pl2 = listaPlanetUkladu.get(1);
		
		assertEquals(pl1.getNazwa(), nazwa1);
		assertEquals(pl1.getSrednica(), srednica1);
		assertEquals(pl1.getIl_ks(), il_ks1);
		
		assertEquals(pl2.getNazwa(), nazwa2);
		assertEquals(pl2.getSrednica(), srednica2);
		assertEquals(pl2.getIl_ks(), il_ks2);
		
	}
	
}
