package com.example.shdemo.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Planeta;


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
	
}
