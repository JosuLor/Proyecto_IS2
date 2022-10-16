package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccess;
import dataAccess.GertaerakKopiatuParameter;
import domain.Event;
import domain.Team;

public class gertaerakKopiatuDAWTest {

	private DataAccess db = new DataAccess();
	private Event ev1;
	private boolean deleted;

	@Before
	public void initialize() {
		ev1 = null;
		deleted = false;
	}

	@Test
	/*
	 * Hay otro evento similar para la entrada
	 */
	public void Test1() {
		
		Date date = new Date(2022, 11, 1);
		String description="Atletico-Athletic";
		String teams[] = description.split("-");
		Team t1 = new Team(teams[0]);
		Team t2 = new Team(teams[1]);
		ev1 = new Event(11, description, date, t1, t2);

		boolean esperado = false;
		
		db.open(true);
		boolean obtenido = db.gertaerakKopiatu(new GertaerakKopiatuParameter(ev1, date));
		
		assertEquals(esperado, obtenido);

		for (Event ev : db.getEvents(date)) {
			if (ev.getDescription().equals(description)) {
				deleted = db.gertaeraEzabatu(ev);
				break;
			}
		}
		
		db.close();
		
		if(deleted) System.out.println("Se ha borrado el evento " + description);
		else System.out.println("No hubo evento para borrar.");
	}

	@Test
	/*
	 * No hay otros eventos para la fecha. Se copia el evento.
	 */
	public void Test2() {

		Date evDate = new Date(2022, 12, 28);
		Date input = new Date(2023, 12, 20);
		
		String description="Girona-Leganes";
		String teams[] = description.split("-");
		Team t1 = new Team(teams[0]);
		Team t2 = new Team(teams[1]);
		Event ev1 = new Event(18, description, evDate, t1, t2);
		
		GertaerakKopiatuParameter params = new GertaerakKopiatuParameter(ev1, input);

		boolean esperado = true;
		db.open(true);
		boolean obtenido = db.gertaerakKopiatu(params);
		
		assertEquals(esperado, obtenido);

		for (Event ev : db.getEvents(input)) {
			System.out.println(ev.getDescription() + ".");
			System.out.println(description + ".");
			if (ev.getDescription().equals(description)) {
				deleted = db.gertaeraEzabatu(ev);
				break;
			}
		}
		
		db.close();

		if(deleted) System.out.println("Se ha borrado el evento " + description);
		else System.out.println("No hubo evento para borrar.");
		
	}

}
