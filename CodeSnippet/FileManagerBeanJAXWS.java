package com.ibm.migration.samples.document.ejb;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ibm.migration.samples.document.entities.FileBean;
import com.ibm.migration.samples.document.util.Document;

/**
 * Bean implementation class for Enterprise Bean: FileManager
 */
@Stateless
@LocalBean
@WebService(serviceName="FileManagerService")
public class FileManagerBean {

	static final long serialVersionUID = 3206093459760846163L;

	@PersistenceContext(unitName="DocumentManagerJPA") 
	EntityManager em; 
	
	public FileManagerBean() {	
	}
	
	
	/***
	 * Business implementation
	 */
	@WebMethod
	@WebResult(name="uploadReturn")
	public String upload(@WebParam(name="userid") String userId, @WebParam(name="filename") String filename) {
		if(filename == null || filename.length() == 0){
			return "No file was selected for upload!";
		}
		addUserFile(userId, filename);
		return "File successfully uploaded!";
	}

	@WebMethod(exclude=true)
	public List<Document> getFilesByUserid(String userid) {
		List<Document> list = new ArrayList<Document>();
		Query query=em.createNamedQuery("findFileByUserid");
		query.setParameter(1, userid);
		
		if (list != null) {
			int i = 0;
			try {
				for (Object object : query.getResultList()) {
					FileBean file = (FileBean) object;
					list.add(new Document(i, file.getTimestamp(), file.getFilename(), file.getUserId()));
				}
			} catch (ClassCastException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@WebMethod(exclude=true)
	public List<Document> getAllFiles() {
		List<Document> list = new ArrayList<Document>();
		Query query=em.createNamedQuery("findAll");
		
		if (list != null) {
			int i = 0;
			try {
				for (Object object : query.getResultList()) {
					FileBean file = (FileBean) object;
					list.add(new Document(i, file.getTimestamp(), file.getFilename(), file.getUserId()));
				}
			} catch (ClassCastException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	private void addUserFile(String userId, String filename) {
		if(userId == null || userId.isEmpty() || filename == null || filename.isEmpty()){
			return;
		}

		long curTime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(curTime);

		try {
			FileBean file = new FileBean(userId, filename, timestamp.toString());
			em.persist(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
