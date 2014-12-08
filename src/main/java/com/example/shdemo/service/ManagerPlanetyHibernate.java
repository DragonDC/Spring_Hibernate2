package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Planeta;
import com.example.shdemo.domain.UkladPlanetarny;


@Component
@Transactional
public class ManagerPlanetyHibernate implements ManagerPlanety{
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void dodaniePlanet(){
		int i;
		for(i=1;i<7;i++){
			Planeta planeta = new Planeta();
			String nazwa = "nazwa"+i  ;
			int srednica = i*1000;
			int il_ks =i*2;
			planeta.setNazwa(nazwa);
			planeta.setSrednica(srednica);
			planeta.setIl_ks(il_ks);
			addPlaneta(planeta);
		}		
	}	
	
	@Override
	public void dodanieUkladow(){
		int i,j;
		
			for(i=1;i<4;i++){		
				List<Planeta> planetyTab = new ArrayList<Planeta>();
				
				//planetyTab.clear();
				
				for(j=3;j<7;j++){
					Planeta planeta = new Planeta();
					String nazwa = "planetaUkladu"+j;
					int srednica = j*1000;
					int il_ks =j*2;
					planeta.setNazwa(nazwa);
					planeta.setSrednica(srednica);
					planeta.setIl_ks(il_ks);
					addPlaneta(planeta);
					planetyTab.add(planeta);
				}
				UkladPlanetarny uklad = new UkladPlanetarny();	
				String nazwa = "uklad"+i;
				int liczbaObiektow = i+2;
				uklad.setNazwaUkladu(nazwa);
				uklad.setLiczbaObiektow(liczbaObiektow);
				uklad.setPlanety(planetyTab);
				addUkladPlanetarny(uklad);
			 
			}	
		
	}
	
	
	@Override
	public void addPlaneta(Planeta planeta) {
		planeta.setId(null);
		sessionFactory.getCurrentSession().persist(planeta);
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Planeta> getAllPlanety() {
		return sessionFactory.getCurrentSession().getNamedQuery("planeta.all")
				.list();
	}
	
	@Override
	public Planeta findPlanetaById(Long id) {
		return (Planeta) sessionFactory.getCurrentSession().getNamedQuery("planeta.byId").setLong("id", id).uniqueResult();
	}
	
	@Override
	public void editPlaneta(Planeta planeta)
	{
		if(planeta.getId() != null){
			sessionFactory.getCurrentSession().persist(planeta);
		}
	}
	
	@Override
	public void deletePlaneta(Planeta planeta) {
		planeta = (Planeta) sessionFactory.getCurrentSession().get(Planeta.class,
				planeta.getId());
	
		sessionFactory.getCurrentSession().delete(planeta);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Planeta> findPlanetaBySrednica(int srednica) {
		return sessionFactory.getCurrentSession().getNamedQuery("planeta.bySrednica").setInteger("srednica", srednica).list();
	}
	
	

	
	
	@Override
	public void addUkladPlanetarny(UkladPlanetarny uklad) {
		uklad.setId(null);
		sessionFactory.getCurrentSession().persist(uklad);
	}
	
	@Override
	public void deleteUkladPlanetarny(UkladPlanetarny uklad) {
		uklad = (UkladPlanetarny) sessionFactory.getCurrentSession().get(UkladPlanetarny.class,uklad.getId());
	// lazy loading here
		for (Planeta planeta : uklad.getPlanety()) {
			sessionFactory.getCurrentSession().delete(planeta);
			//deletePlaneta(planeta);
			/*car.setSold(false);
			sessionFactory.getCurrentSession().update(car);*/
		}
		
		sessionFactory.getCurrentSession().delete(uklad);
	}
	
	@Override
	public List<Planeta> getOwnedPlanety(UkladPlanetarny uklad) {
		uklad = (UkladPlanetarny) sessionFactory.getCurrentSession().get(UkladPlanetarny.class,
				uklad.getId());
	// lazy loading here - try this code without (shallow) copying
		List<Planeta> planety = new ArrayList<Planeta>(uklad.getPlanety());
		return planety;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UkladPlanetarny> getAllUklady() {
		return sessionFactory.getCurrentSession().getNamedQuery("uklady.all")
				.list();
	}
	
	@Override
	public UkladPlanetarny findUkladPlanetarnyById(Long id) {
		return (UkladPlanetarny) sessionFactory.getCurrentSession().getNamedQuery("uklady.byId").setLong("id", id).uniqueResult();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<UkladPlanetarny> findUkladPlanetarnyByLiczbaObiektow(int liczbaObiektow) {
		return sessionFactory.getCurrentSession().getNamedQuery("uklady.byLiczbaObiektow").setInteger("liczbaObiektow", liczbaObiektow).list();
	}
	
	@Override
	public void editUkladPlanetarny(UkladPlanetarny uklad)
	{
		if(uklad.getId() != null){
			sessionFactory.getCurrentSession().persist(uklad);
		}
	}
}
