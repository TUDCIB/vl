package de.tudresden.bau.cib.vl.core.simulation.energy.database.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;

import de.tudresden.bau.cib.vl.core.simulation.energy.database.dao.EnergyResultsDao;
import de.tudresden.bau.cib.vl.core.simulation.energy.database.domain.EnergyResults;

public class EnergyResultsDaoImpl implements EnergyResultsDao {

	private SessionFactory sessionFactory;
	private static final String TABLE = EnergyResults.class.getSimpleName();
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public EnergyResults getEnergyResults(Integer id) {
		String hql = "from "+TABLE+" as er where er.id = :id  ORDER BY er.id DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		List<EnergyResults> resultList = query.list();
		if(resultList.size() >= 1) {
			return resultList.get(0);
		}
		return null;
	}
	
	@Override
	public EnergyResults getEnergyResultsBySimulationId(Integer simulationId) {
		String hql = "from "+TABLE+" as er where er.simulationId = :simulationId  ORDER BY er.id DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("simulationId", simulationId);
		
		@SuppressWarnings("unchecked")
		List<EnergyResults> resultList = query.list();
		if(resultList.size() >= 1) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public List<EnergyResults> getAllEnergyResults() {
		String hql = "from "+TABLE+" as er ORDER BY er.id DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<EnergyResults> resultList = query.list();
		if(resultList.size() >= 1) {
			return resultList;
		}
		return null;
	}

	@Override
	public Integer insertEnergyResults(EnergyResults results) {
	    Integer ret = (Integer) sessionFactory.getCurrentSession().save(results);
	    return ret;
	}

	@Override
	public void updateEnergyResults(EnergyResults results) {
		sessionFactory.getCurrentSession().update(results);
	}

	@Override
	public Map<Long, Double> getResultsBetween(Integer simulationId, long startTime, long endTime) {
		EnergyResults results = getEnergyResultsBySimulationId(simulationId);
		if(results == null) throw new DataAccessResourceFailureException("There are no simulation results for simulation ID "+simulationId);
		
//		Map<Long, Double> resultValues = results.getTimeToValue();
//		if(resultValues == null) throw new DataAccessResourceFailureException("No result values for simulation ID: "+simulationId);
//		
//		Map<Long, Double> valuesBetweenRequestedTime = new TreeMap<Long, Double>();
//		
//		for (Map.Entry<Long, Double> entry : resultValues.entrySet()) {
//			Long key = entry.getKey();
//			Double value = entry.getValue();
//			if(key >= startTime) {
//				
//			}
//			
//		}
		return null;
	}

}
