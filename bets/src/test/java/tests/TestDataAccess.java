package tests;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import domain.Event;
import domain.Question;
import domain.Registered;
import domain.Team;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {

		System.out.println("Creating TestDataAccess instance");

		open();

	}


	public void open(){

		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			db = emf.createEntityManager();
		}

	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
			return false;
	}

	public Event addEventWithQuestion(String desc, Date d, String question, float qty, Team t1, Team t2) {
		System.out.println(">> DataAccessTest: addEvent");
		Event ev=null;
		db.getTransaction().begin();
		try {
			ev=new Event(desc,d, t1,t2);
			ev.addQuestion(question, qty);
			db.persist(ev);
			db.getTransaction().commit();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return ev;
	}
	public boolean existQuestion(Event ev,Question q) {
		System.out.println(">> DataAccessTest: existQuestion");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			return e.DoesQuestionExists(q.getQuestion());
		} else 
			return false;

	}

	public boolean removeRegistered(String usrName, String password, int bank) {
		TypedQuery<Registered> Rquery = db.createQuery("SELECT r FROM Registered r WHERE r.usrname=?1 AND r.password=?2 AND r.bankAccount=?3", Registered.class);
		Rquery.setParameter(1, usrName);
		Rquery.setParameter(2, password);
		Rquery.setParameter(3, bank);
		List<Registered> listR = Rquery.getResultList();
		if(listR.size() > 0) {
			db.getTransaction().begin();
			for(Registered r :listR) {
				db.remove(r);
			}	
			db.getTransaction().commit();
			return true;
		}else {
			return false;
		}		
	}
	
	public List<Registered> getRegistered(){
		TypedQuery<Registered> Rquery = db.createQuery("SELECT r FROM Registered r", Registered.class);
		List<Registered> listR = Rquery.getResultList();
		
		return listR;
	}
	
	public void removeAllRegistered(){
		TypedQuery<Registered> Rquery = db.createQuery("SELECT r FROM Registered r", Registered.class);
		List<Registered> listR = Rquery.getResultList();
		db.getTransaction().begin();
		for(Registered r :listR) {
			db.remove(r);
		}
		db.getTransaction().commit();
	}
	
	public void addAllRegisteredBase(List<Registered> list) {
		db.getTransaction().begin();
		for(Registered r :list) {
			Registered rr = new Registered(r.getUsername(), r.getPassword(), r.getBankAccount());
			db.persist(rr);
		}
		db.getTransaction().commit();
	}
	
	public void storeRegistered(String username, String password, Integer bankAccount, double irabazitakoa) {
		db.getTransaction().begin();
		Registered ad = new Registered(username, password, bankAccount);
		ad.setIrabazitakoa(irabazitakoa);
		db.persist(ad);
		db.getTransaction().commit();
	}

}