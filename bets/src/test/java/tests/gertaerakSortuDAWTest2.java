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

public class gertaerakSortuDAWTest2 {

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
		description = "Atletico-Eibar";
		data = UtilDate.newDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 11);
		sport = "Eclipse";
		EventParam params = new EventParam(description, data, sport);
		
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
		
		System.out.println("[JUnit test1]: \"" + sport + "\" no es un Sport");
		
		if (borrado)
			System.out.println("[JUnit test1]: Evento borrado (" + description + ", " + data + ", " + sport + ")");
		else
			System.out.println("[JUnit test1]: Evento no borrado; no se ha encontrado ningun evento (" + description + ", " + data + ", " + sport + ")");
				
	}
//		
//	@Test
//	public void test2() {
//		
//		// 1. Expected value
//		boolean expected = false;
//		
//		// 2. Set and get the result from the actual method
//		description = "Barcelona-Levante";
//		data = UtilDate.newDate((today.get(Calendar.YEAR) + 1), today.get(Calendar.MONTH), 12);	// no hay eventos en esta fecha (año siguiente, por lo que no se va a ejecutar el foreach de los eventos con misma fecha)
//		sport = "Futbol";
//		EventParam params = new EventParam(description, data, sport);
//		
//		dt.gertaerakSortu(params);	// crear el evento con anterioridad, para que despues el sistema detecte que ya exite un evento con esa descripcion, y de esta forma conseguir que el if de dentro del foreach sea true
//		boolean result = dt.gertaerakSortu(params);
//		
//		// 3. Check via Assert
//		assertEquals(expected, result);
//				
//		// 4. Re-establish previous state
//		for (Event ev : dt.getEvents(data)) {
//			if (ev.getDescription().equals(description)) {
//				dt.gertaeraEzabatu(ev);
//				borrado = true;
//				break;
//			}
//		}
//		
//		if (borrado)
//			System.out.println("[JUnit test2]: Evento borrado (" + description + ", " + data + ", " + sport + ")");
//		else
//			System.out.println("[JUnit test2]: Evento no borrado; no se ha encontrado ningun evento (" + description + ", " + data + ", " + sport + ")");
//			
//	}
//	
//	@Test
//	public void test3() {
//		
//		// 1. Expected value
//		boolean expected = true;
//		
//		// 2. Set and get the result from the actual method
//		description = "Atletico-Levante";
//		data = UtilDate.newDate(today.get(Calendar.YEAR) + 1, today.get(Calendar.MONTH), 11);
//		sport = "Futbol";
//		EventParam params = new EventParam(description, data, sport);
//		
//		boolean result = dt.gertaerakSortu(params);
//		
//		// 3. Check via Assert
//		assertEquals(expected, result);
//				
//		// 4. Re-establish previous state
//		for (Event ev : dt.getEvents(data)) {
//			if (ev.getDescription().equals(description)) {
//				dt.gertaeraEzabatu(ev);
//				borrado = true;
//				break;
//			}
//		}
//		
//		if (borrado)
//			System.out.println("[JUnit test3]: Evento borrado (" + description + ", " + data + ", " + sport + ")");
//		else
//			System.out.println("[JUnit test3]: Evento no borrado; no se ha encontrado ningun evento (" + description + ", " + data + ", " + sport + ")");
//			
//	}
//	
//	@Test
//	public void test4() {
//		
//		// 1. Expected value
//		boolean expected = false;
//		
//		// 2. Set and get the result from the actual method
//		description = "Atletico-Levante";
//		data = UtilDate.newDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 11);
//		sport = "Futbol";
//		EventParam params = new EventParam(description, data, sport);
//		
//		dt.gertaerakSortu(params);	// crear el evento con anterioridad, para que despues el sistema detecte que ya exite un evento con esa descripcion, y de esta forma conseguir que el if de dentro del foreach sea true
//		boolean result = dt.gertaerakSortu(params);
//		
//		// 3. Check via Assert
//		assertEquals(expected, result);
//				
//		// 4. Re-establish previous state
//		for (Event ev : dt.getEvents(data)) {
//			if (ev.getDescription().equals(description)) {
//				dt.gertaeraEzabatu(ev);
//				borrado = true;
//				break;
//			}
//		}
//		
//		if (borrado)
//			System.out.println("[JUnit test4]: Evento borrado (" + description + ", " + data + ", " + sport + ")");
//		else
//			System.out.println("[JUnit test4]: Evento no borrado; no se ha encontrado ningun evento (" + description + ", " + data + ", " + sport + ")");
//			
//	}
	
//	@Test
//	public void test5() {
//		
//		// 1. Expected value
//		boolean expected = true;
//		
//		// 2. Set and get the result from the actual method
//		description = "Atletico-Levante";
//		data = UtilDate.newDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 11);
//		sport = "Futbol";
//		EventParam params = new EventParam(description, data, sport);
//		
//		params.description = "Atletico-Malaga";
//		dt.gertaerakSortu(params);
//		params.description = "Atletico-Levante";
//		boolean result = dt.gertaerakSortu(params);
//		
//		// 3. Check via Assert
//		assertEquals(expected, result);
//		
//		// 4. Re-establish previous state
//		description = "Atletico-Malaga";
//		for (Event ev : dt.getEvents(data)) {
//			if (ev.getDescription().equals(description)) {
//				dt.gertaeraEzabatu(ev);
//				borrado = true;
//				System.out.println("[JUnit test5]: Evento borrado (" + description + ", " + data + ", " + sport + ")");
//				break;
//			}
//		}
//		description = "Atletico-Levante";
//		for (Event ev : dt.getEvents(data)) {
//			if (ev.getDescription().equals(description)) {
//				dt.gertaeraEzabatu(ev);
//				borrado = true;
//				System.out.println("[JUnit test5]: Evento borrado (" + description + ", " + data + ", " + sport + ")");
//				break;
//			}
//		}
//		
//		if (!borrado)
//			System.out.println("[JUnit test5]: Evento no borrado; no se ha encontrado ningun evento (" + description + ", " + data + ", " + sport + ")");
//			
//	}

}
