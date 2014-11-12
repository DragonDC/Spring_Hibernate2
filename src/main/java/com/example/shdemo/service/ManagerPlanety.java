package com.example.shdemo.service;

import java.util.List;

//import com.example.shdemo.domain.Car;
//import com.example.shdemo.domain.Person;
import com.example.shdemo.domain.Planeta;

public interface ManagerPlanety {
	void addPlaneta(Planeta planeta);
	List<Planeta> getAllPlanety();
	Planeta findPlanetaById(Long id);	
	void editPlaneta(Planeta planeta);
	void deletePlaneta(Planeta planeta);
	
	/*Long addNewCar(Car car);
	List<Car> getAvailableCars();
	void disposeCar(Person person, Car car);
	Car findCarById(Long id);

	List<Car> getOwnedCars(Person person);
	void sellCar(Long personId, Long carId);*/
}
