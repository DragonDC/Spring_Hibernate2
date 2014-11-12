package com.example.shdemo.service;



import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Planeta;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ManagerPlanetyTest {
	
	@Autowired
	ManagerPlanety managerPlanety;
	
	private final String nazwa1 = "Mars";
	private final String nazwa2 = "Jowisz";
	
	private final int srednica1 = 5433;
	private final int srednica2 = 1234;
	
	private final int il_ks1 = 2;
	private final int il_ks2 = 43;
	@Test
	public void addPrawnikCheck() {
		
		Planeta planeta = new Planeta();
		planeta.setNazwa(nazwa1);
		planeta.setSrednica(srednica1);
		planeta.setIl_ks(il_ks1);
		
		managerPlanety.addPlaneta(planeta);
		
		List<Planeta> listaPlanet = managerPlanety.getAllPlanety();
		
		for (Planeta planetaPetla : listaPlanet) {
			if (planetaPetla.getId().equals(planeta.getId())) {
				assertEquals(nazwa1,planetaPetla.getNazwa());
				assertEquals(srednica1,planetaPetla.getSrednica());
				assertEquals(il_ks1,planetaPetla.getIl_ks());
			}
		}
	
	}
	
	
	@Test
	public void deletePlanetaCheck() {
		Planeta planeta = new Planeta();
		planeta.setNazwa(nazwa1);
		planeta.setSrednica(srednica1);
		planeta.setIl_ks(il_ks1);
		managerPlanety.addPlaneta(planeta);
		
		managerPlanety.deletePlaneta(planeta);
		
		assertEquals(null, managerPlanety.findPlanetaById(planeta.getId()));
		
		Planeta planeta1 = new Planeta();
		planeta.setNazwa(nazwa1);
		planeta.setSrednica(srednica1);
		planeta.setIl_ks(il_ks1);
		
		Planeta planeta2 = new Planeta();
		planeta.setNazwa(nazwa2);
		planeta.setSrednica(srednica2);
		planeta.setIl_ks(il_ks2);
		
		managerPlanety.addPlaneta(planeta1);
		managerPlanety.addPlaneta(planeta2);
		
		List<Planeta> listaPlanet = managerPlanety.getAllPlanety();
		int liczbaPlanet = listaPlanet.size();
		assertEquals(2, liczbaPlanet);
		
		managerPlanety.deletePlaneta(planeta1);
		
		assertEquals(null, managerPlanety.findPlanetaById(planeta1.getId()));
		
		List<Planeta> listaPlanet2 = managerPlanety.getAllPlanety();
		int liczbaPlanet2 = listaPlanet2.size();
		assertEquals(1, liczbaPlanet2);
			
	}
	
	@Test
	public void findPlanetaCheck() {
		Planeta planeta = new Planeta();
		planeta.setNazwa(nazwa1);
		planeta.setSrednica(srednica1);
		planeta.setIl_ks(il_ks1);
		managerPlanety.addPlaneta(planeta);
		assertNotNull(managerPlanety.findPlanetaById(planeta.getId()));
		managerPlanety.deletePlaneta(planeta);
		assertNull(managerPlanety.findPlanetaById(planeta.getId()));
	}
	
	@Test
		public void findAllPlanetyCheck() {
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
		
		List<Planeta> listaPlanet = managerPlanety.getAllPlanety();
		assertNotNull(listaPlanet);
		
		int liczbaPlanet = listaPlanet.size();
		
		assertEquals(2, liczbaPlanet);
	}
	
	
	@Test
	public void editPrawnikCheck() {
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
		
		List<Planeta> listaPlanet = managerPlanety.getAllPlanety();
		int liczbaPlanet = listaPlanet.size();
		assertEquals(3, liczbaPlanet);
		
		int i;
		for(i=0; i < liczbaPlanet; i++){
			assertEquals(nazwa2, listaPlanet.get(i).getNazwa());
			assertEquals(srednica2, listaPlanet.get(i).getSrednica());
			assertEquals(il_ks2, listaPlanet.get(i).getIl_ks());
		}
		
		planeta2.setNazwa(nazwa1);
		planeta2.setSrednica(srednica1);
		planeta2.setIl_ks(il_ks1);
		managerPlanety.editPlaneta(planeta2);
		
		Long idPlanety = planeta2.getId();
		Long indeks = (long)0;
		for(i=0; i < liczbaPlanet; i++){
			if (indeks.equals(idPlanety)) {
				assertEquals(nazwa1, listaPlanet.get(i).getNazwa());
				assertEquals(srednica1, listaPlanet.get(i).getSrednica());
				assertEquals(il_ks1, listaPlanet.get(i).getIl_ks());
			}
		indeks++;
		}
		
		assertEquals(nazwa2, planeta1.getNazwa());
		assertEquals(srednica2, planeta1.getSrednica());
		assertEquals(il_ks2, planeta1.getIl_ks());
		
		assertEquals(nazwa2, planeta3.getNazwa());
		assertEquals(srednica2, planeta3.getSrednica());
		assertEquals(il_ks2, planeta3.getIl_ks());
		
	}
	
	
}
