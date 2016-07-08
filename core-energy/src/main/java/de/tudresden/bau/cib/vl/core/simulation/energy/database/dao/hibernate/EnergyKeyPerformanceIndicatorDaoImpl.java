package de.tudresden.bau.cib.vl.core.simulation.energy.database.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import de.tudresden.bau.cib.vl.core.simulation.energy.database.dao.EnergyKeyPerformanceIndicatorDao;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyKeyPerformanceIndicators;

public class EnergyKeyPerformanceIndicatorDaoImpl implements EnergyKeyPerformanceIndicatorDao {

	private SessionFactory sessionFactory;
	private static final String TABLE = EnergyKeyPerformanceIndicators.class.getSimpleName();
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public EnergyKeyPerformanceIndicators getEKPIBySimulationId(Integer simulationId) {
		String hql = "from "+TABLE+" as eKPI where eKPI.simulationId = :simulationId  ORDER BY eKPI.id DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("simulationId", simulationId);
		
		@SuppressWarnings("unchecked")
		List<EnergyKeyPerformanceIndicators> resultList = query.list();
		if(resultList.size() >= 1) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public Integer insertEnergyKeyPerformanceIndicators(
			EnergyKeyPerformanceIndicators eKPIs) {
    	Integer ret = (Integer) sessionFactory.getCurrentSession().save(eKPIs);
    	return ret;
	}

	@Override
	public void updateEnergyKeyPerformanceIndicators(
			EnergyKeyPerformanceIndicators eKPIs) {
		sessionFactory.getCurrentSession().update(eKPIs);
	}

	@Override
	public List<EnergyKeyPerformanceIndicators> getAllEnergyKeyPerformanceIndicators() {
		String hql = "from "+TABLE+" as eKPI ORDER BY eKPI.id DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<EnergyKeyPerformanceIndicators> resultList = query.list();
		if(resultList.size() >= 1) {
			return resultList;
		}
		return null;
	}

}
