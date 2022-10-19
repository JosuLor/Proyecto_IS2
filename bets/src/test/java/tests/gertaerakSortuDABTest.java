package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccessGertaerakSortu;
import domain.Event;
import domain.EventParam;

public class gertaerakSortuDABTest {

	private DataAccessGertaerakSortu dt = new DataAccessGertaerakSortu();
	Calendar today = Calendar.getInstance();
	private String description;
	private Date data;
	private String sport;
	private boolean borrado;
	
	@Before
	public void initialize() {
		description = "";
		data = null;
		sport = "";
		borrado = false;
	}
	
	@Test
	public void test1() {
		
		// 1. Expected value
		boolean expected = false;
		
		// 2. Set and get the result from the actual method
		description = "Atletico-Malaga";
		data = UtilDate.newDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 10);
		sport = "Futbol";
		EventParam params = new EventParam(description, data, sport);
		
		dt.gertaerakSortu(params);
		boolean result = dt.gertaerakSortu(params);
		
		// 3. Check via Assert
		assertEquals(expected, result);
				
		// 4. Re-establish previous state
		for (Event ev : dt.getEvents(data)) {
			if (ev.getDescription().equals(description)) {
				dt.gertaeraEzabatu(ev);
				borrado = true;
				break;
			}
		}
		
		System.out.println("[JUnit test1]: El evento (" + description + ", " + data.toString() + ", " + sport + ") ya fue registrado/creado; no se puede volver a crear");
		
		if (borrado)
			System.out.println("[JUnit test1]: Evento borrado (" + description + ", " + data.toString() + ", " + sport + ")");
		else
			System.out.println("[JUnit test1]: Evento no borrado; no se ha encontrado ningun evento (" + description + ", " + data + ", " + sport + ")");
				
	}
	
	
	@Test
	public void test2() {
		// 1. Expected value
		boolean expected = true;
				
		// 2. Set and get the result from the actual method
		description = "Atletico-Malaga";
		data = UtilDate.newDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 10);
		sport = "Futbol";
		EventParam params = new EventParam(description, data, sport);
		
		dt.gertaerakSortu(params);		
		
		params.description = "Eibar-Malaga";
		data = UtilDate.newDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 12);
		boolean result = dt.gertaerakSortu(params);
		
		// 3. Check via Assert
		assertEquals(expected, result);
		
		// 4. Re-establish previous state
		for (Event ev : dt.getEvents(data)) {
			if (ev.getDescription().equals(description)) {
				dt.gertaeraEzabatu(ev);
				borrado = true;
				break;
			}
		}
		
		System.out.println("[JUnit test2]: El evento (" + description + ", " + data.toString() + ", " + sport + ") ha sido registrado/creado correctamente");
		
		if (borrado)
			System.out.println("[JUnit test2]: Evento borrado (" + description + ", " + data.toString() + ", " + sport + ")");
		else
			System.out.println("[JUnit test2]: Evento no borrado; no se ha encontrado ningun evento (" + description + ", " + data + ", " + sport + ")");
		
		description = "Atletico-Malaga";
		data = UtilDate.newDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 10);
		borrado = false;
		for (Event ev : dt.getEvents(data)) {
			if (ev.getDescription().equals(description)) {
				dt.gertaeraEzabatu(ev);
				borrado = true;
				break;
			}
		}
		
		if (borrado)
			System.out.println("[JUnit test2]: Evento borrado (" + description + ", " + data.toString() + ", " + sport + ")");
		else
			System.out.println("[JUnit test2]: Evento no borrado; no se ha encontrado ningun evento (" + description + ", " + data + ", " + sport + ")");
	}

	/*
	@Test
	public void test3() {
		
		// 1. Expected value
		boolean expected = false;
		
		// 2. Set and get the result from the actual method
		description = "Atletico-Malaga";
		data = UtilDate.newDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 10);
		sport = "Futbol";
		EventParam params = new EventParam(description, data, sport);
		
		dt.gertaerakSortu(params);
		params.sport = "UPV";
		boolean result = dt.gertaerakSortu(params);
		
		// 3. Check via Assert
		assertEquals(expected, result);
				
		// 4. Re-establish previous state
		for (Event ev : dt.getEvents(data)) {
			if (ev.getDescription().equals(description)) {
				dt.gertaeraEzabatu(ev);
				borrado = true;
				break;
			}
		}
		
		System.out.println("[JUnit test3]: El evento (" + description + ", " + data.toString() + ", " + sport + ") no fue registrado/creado. El deporte no existe");	
	}
	*/
	
	/*
	@Test
	public void test4() {
		
		// 1. Expected value
		boolean expected = false;
		
		// 2. Set and get the result from the actual method
		description = null;
		data = null;
		sport = null;
		EventParam params = new EventParam(description, data, sport);
		
		boolean result = dt.gertaerakSortu(params);
		
		// 3. Check via Assert
		assertEquals(expected, result);
		
		System.out.println("[JUnit test1]: El evento (" + description + ", " + data.toString() + ", " + sport + ") no fue registrado/creado; no se puede crear un evento nulo");
				
	}
	*/
}

