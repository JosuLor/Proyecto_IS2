package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import domain.Team;

public class gertaerakKopiatuDAWTest {

	private DataAccess db = new DataAccess();
	private Date date;
	private Team t1;
	private Team t2;
	private String description;
	private Event ev1;
	private boolean deleted;

	@Before
	public void initialize() {
		date = null;
		description = "";
		t1 = null;
		t2 = null;
		ev1 = null;
		deleted = false;
	}

	@Test
	/*
	 * Hay otro evento similar para la entrada
	 */
	public void Test1() {
		
		date = new Date(2022, 11, 1);
		description="Atletico-Athletic";
		String teams[] = description.split("-");
		t1 = new Team(teams[0]);
		t2 = new Team(teams[1]);
		ev1 = new Event(21, description, date, t1, t2);

		boolean esperado = false;
		boolean obtenido = db.gertaerakKopiatu(ev1, date);

		assertEquals(esperado, obtenido);

		for (Event ev : db.getEvents(date)) {
			if (ev.getDescription().equals(description)) {
				db.gertaeraEzabatu(ev);
				deleted = true;
				break;
			}
		}
		
		if(deleted) System.out.println("Se ha borrado el evento " + description);
		else System.out.println("No hubo evento para borrar.");
	}

	@Test
	/*
	 * No hay otros eventos para la fecha. Se copia el evento.
	 */
	public void Test2() {

		date = new Date(2022, 11, 5);
		description="Atletico-Athletic";
		String teams[] = description.split("-");
		t1 = new Team(teams[0]);
		t2 = new Team(teams[1]);
		Event ev1 = new Event(21, description, date, t1, t2);

		boolean esperado = true;
		boolean obtenido = db.gertaerakKopiatu(ev1, date);

		assertEquals(esperado, obtenido);

		for (Event ev : db.getEvents(date)) {
			if (ev.getDescription().equals(description)) {
				db.gertaeraEzabatu(ev);
				deleted = true;
				break;
			}
		}
		
		if(deleted) System.out.println("Se ha borrado el evento " + description);
		else System.out.println("No hubo evento para borrar.");
		
	}

}
