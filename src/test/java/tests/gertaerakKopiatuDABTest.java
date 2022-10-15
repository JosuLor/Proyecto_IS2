package tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Vector;

import org.junit.Test;
import org.mockito.internal.matchers.Any;

import dataAccess.DataAccess;
import dataAccess.GertaerakKopiatuParameter;
import domain.Event;
import domain.Team;

public class gertaerakKopiatuDABTest {

	static DataAccess sut = new DataAccess();
	private Event ev;
	private Date date;

	@Test
	/*
	 * Algún parámetro es nulo.
	 */
	public void Test1() {
		try {
			ev = null;
			date = new Date(2022, 11, 19);

			// 1. Dado un parámetro nulo, el programa no debe de continuar. (CE 2)
			sut.open(false);
			sut.gertaerakKopiatu(new GertaerakKopiatuParameter(ev, date));
			sut.close();
			fail("Null pointer exception expected.");
		} catch (Exception e) {
			assertTrue(true);
		}


	}

	@Test
	/*
	 * Formato Date no válido.
	 */
	public void Test2() {
		try {
			ev = new Event(1, "Hola-Prueba", new Date(2022, 11, 11), new Team("Hola"), new Team("Prueba"));
			date = new Date(2022, 13, 19);

			// 1. Dado un formato no válido de fecha, el programa no debe de continuar. (CE 4)
			sut.open(false);
			sut.gertaerakKopiatu(new GertaerakKopiatuParameter(ev, date));
			sut.close();
			fail("Incorrect date format, program should've stopped.");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	/*
	 * Evento copiado (CE 1, 3, 5)
	 */
	public void Test3() {
		// 1. Se establece la fecha de input en la que no existe el evento dado.
		Date date = new Date(2023, 12, 12);

		Date eventDate = new Date(2022, 11, 17);
		String description="Atletico-Athletic";
		String teams[] = description.split("-");
		Team t1 = new Team(teams[0]);
		Team t2 = new Team(teams[1]);
		
		// 2. Se crea un evento igual a uno ya existente en la base de datos.
		ev = new Event(1, description, eventDate, t1, t2);

		sut.open(false);
		boolean devuelto = sut.gertaerakKopiatu(new GertaerakKopiatuParameter(ev, date));
		sut.close();
		
		// 3. Según el funcionamiento del método, se debe crear una copia del evento. Debe devolver True.
		assertTrue(devuelto);
		
		sut.open(false);
		boolean deleted = false;
		Vector<Event> eventsOfDate = sut.getEvents(date);
		for(Event ev: eventsOfDate) {
			String evDesc = ev.getDescription();
			System.out.println(evDesc);
			System.out.println(description);
			if(evDesc.equals(ev.getDescription())) {
				deleted = sut.gertaeraEzabatu(ev);
				break;
			}
		}
		sut.close();
		
		if(deleted) System.out.println("Se ha borrado el evento copiado " + description);
		else System.out.println("No hubo evento para borrar.");
	}

	@Test
	/*
	 * Evento no copiado (CE 1, 3, 6)
	 */
	public void Test4() {
		// 1. Se establece la fecha de input en la que no existe el evento dado.
		Date date = new Date(2022, 11, 17);
		String description="Atletico-Athletic";
		String teams[] = description.split("-");
		Team t1 = new Team(teams[0]);
		Team t2 = new Team(teams[1]);
		
		// 2. Se crea un evento igual a uno ya existente en la base de datos.
		ev = new Event(1, description, date, t1, t2);
		
		// 3. Según el funcionamiento del método, no se puede hacer una copia de un evento el mismo día. Debe devolver False.
		sut.open(false);
		boolean devuelto = sut.gertaerakKopiatu(new GertaerakKopiatuParameter(ev, date));
		sut.close();
		
		assertFalse(devuelto);
		
		sut.open(false);
		boolean deleted = false;
		Vector<Event> eventsOfDate = sut.getEvents(date);
		for(Event ev: eventsOfDate) {
			String evDesc = ev.getDescription();
			System.out.println(evDesc);
			System.out.println(description);
			if(evDesc.equals(ev.getDescription())) {
				deleted = sut.gertaeraEzabatu(ev);
				break;
			}
		}
		sut.close();
		
		if(deleted) System.out.println("Se ha borrado el evento copiado " + description);
		else System.out.println("No hubo evento para borrar.");
	}

}
