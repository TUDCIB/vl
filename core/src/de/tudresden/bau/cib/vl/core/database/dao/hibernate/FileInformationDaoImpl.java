package de.tudresden.bau.cib.vl.core.database.dao.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;

import de.tudresden.bau.cib.vl.core.database.dao.FileInformationDao;
import de.tudresden.bau.cib.vl.core.database.domain.FileInformation;

public class FileInformationDaoImpl implements FileInformationDao {
	
	private SessionFactory sessionFactory;
	
	
	private static final String TABLE = FileInformation.class.getSimpleName();
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer insertFileEntry(FileInformation fmi) {
    	Integer ret = (Integer) sessionFactory.getCurrentSession().save(fmi);
        return ret;
	}

	@Override
	public void deleteFileEntry(Integer fileInformationId) {
		String hql = "from "+TABLE+" as fi where fi.id = :fileId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("fileId", fileInformationId);

		FileInformation fp = null;
		
		@SuppressWarnings("unchecked")
		List<FileInformation> list = query.list();
		if (list.size() == 0) {
			throw new DataAccessResourceFailureException("No FileInformation with file identifier "+fileInformationId+" available.");
		} else if (list.size() == 1) {
			fp = list.get(0);
		} else {
			throw new DataIntegrityViolationException(
					"There exists more file paths with the same fileId.");
		}
		sessionFactory.getCurrentSession().delete(fp);
	}

	@Override
	public boolean existFileEntry(Integer fileInformationId) {
		String hql = "from "+TABLE+" as fi where fi.id = :fileId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("fileId", fileInformationId);
		
		@SuppressWarnings("unchecked")
		List<FileInformation> resultList = query.list();
		if(resultList.size() >= 1) {
			return true;
		} 
        return false;
	}

	@Override
	public Set<Integer> getAllFileIdentifiers() {
		String hql = "from "+TABLE+"";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<FileInformation> list = query.list();
		Set<Integer> resultList = new HashSet<Integer>();
		for(FileInformation fmi : list) {
			resultList.add(fmi.getId());
		}
		return resultList;
	}

	@Override
	public String getFilePath(Integer fileInformationId) {
		FileInformation found = (FileInformation) sessionFactory.getCurrentSession().get(FileInformation.class,
				fileInformationId);
		if(found != null) {
			return found.getFilePath();
		}
        return null;
	}

	@Override
	public FileInformation getFileInformationByFilePath(String filePath) {
		String hql = "from "+TABLE+" as fi where fi.filePath = :filePath";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("filePath", filePath);
		
		@SuppressWarnings("unchecked")
		List<FileInformation> resultList = query.list();
		if(resultList.size() >= 1) {
			FileInformation fi = resultList.get(0);			
			return fi;
		} 
        return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FileInformation> getFileInformationByOwnerId(Integer ownerId) {
		String hql = "from "+TABLE+" as fi where fi.ownerId = :ownerId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("ownerId", ownerId);
		
		List<FileInformation> list = (List<FileInformation>)query.list();		
		return list;
	}

	@Override
	public FileInformation getFileInformation(Integer id) {
		String hql = "from "+TABLE+" as fi where fi.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		List<FileInformation> resultList = query.list();
		if(resultList.size() >= 1) {
			FileInformation fi = resultList.get(0);
			
			return fi;
		} 
        return null;
	}

	@Override
	public FileInformation getFileInformationByFilePathAndOwnerId(String filePath,
			Integer ownerId) {
		String hql = "from "+TABLE+" as fi where fi.ownerId = :ownerId AND fi.filePath = :filePath";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("ownerId", ownerId);
		query.setParameter("filePath", filePath);
		
		@SuppressWarnings("unchecked")
		List<FileInformation> resultList = query.list();
		if(resultList.size() == 1) {
			FileInformation fi = resultList.get(0);			
			return fi;
		} else {
			throw new DataIntegrityViolationException("There exists more file information with the same owner and file path.");
		}
	}
	
	public void deleteFileInformation(FileInformation fileInfo) {
		sessionFactory.getCurrentSession().delete(fileInfo);
	}

}
