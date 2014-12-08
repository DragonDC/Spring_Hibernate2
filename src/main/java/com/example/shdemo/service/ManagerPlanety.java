package com.example.shdemo.service;

import java.util.List;


//import com.example.shdemo.domain.Car;
//import com.example.shdemo.domain.Person;
import com.example.shdemo.domain.Planeta;
import com.example.shdemo.domain.UkladPlanetarny;

public interface ManagerPlanety {
	
	void addPlaneta(Planeta planeta);
	List<Planeta> getAllPlanety();
	Planeta findPlanetaById(Long id);	
	void editPlaneta(Planeta planeta);
	void deletePlaneta(Planeta planeta);
	List<Planeta> findPlanetaBySrednica(int srednica);
	
	void dodaniePlanet();
	void dodanieUkladow();
	
	void addUkladPlanetarny(UkladPlanetarny uklad);
	List<UkladPlanetarny> getAllUklady();
	UkladPlanetarny findUkladPlanetarnyById(Long id);
	void editUkladPlanetarny(UkladPlanetarny uklad);
	void deleteUkladPlanetarny(UkladPlanetarny uklad);
	//void disposeCar(UkladPlanetarny uklad, Planeta planeta);
	List<UkladPlanetarny> findUkladPlanetarnyByLiczbaObiektow(int liczbaObiektow);

	List<Planeta> getOwnedPlanety(UkladPlanetarny uklad);
	//void sellCar(Long personId, Long carId);
}
