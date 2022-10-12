package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.business_logic.BLFacade;
import org.business_logic.BLFacadeImplementation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import dataAccess.DataAccess;
import domain.Event;
import domain.Team;

@RunWith(MockitoJUnitRunner.class)
public class gertaerakKopiatuMockIntTest {

	DataAccess da = Mockito.mock(DataAccess.class);
	
	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(da);
	
	@Test
	/*
	 * Se supone que en la fecha data ya hay un evento. No se puede copiar.
	 */
	public void Test1() {
		/*
		 * Se puede observar que la fecha en la que se quiere copiar el evento coincide
		 * con la fecha del evento dado. 'Mockeando' el m�todo del DA, el resultado
		 * debe de ser False.
		 */
		Date input = new Date(2022, 12, 12);
		Date evDate = new Date(2022, 12, 12);
		String description = "Madrid-Sevilla";
		String teams[] = description.split("-");
		Team t1 = new Team(teams[0]);
		Team t2 = new Team(teams[1]);
		Event ev = new Event(description, evDate, t1, t2);
		
		Mockito.doReturn(false).when(da).gertaerakKopiatu(
				ev, input);
		
		boolean res = sut.gertaerakKopiatu(ev, input);
		
		assertFalse(res);
	}

	@Test
	/*
	 * Se supone que en la fecha data ya hay un evento. No se puede copiar.
	 */
	public void Test2() {
		/*
		 * Se puede observar que la fecha en la que se quiere copiar el evento es distinta
		 * a la fecha del evento dado. 'Mockeando' el m�todo del DA, el resultado
		 * debe de ser True.
		 */
		Date input = new Date(2022, 12, 25);
		Date evDate = new Date(2022, 12, 12);
		String description = "Madrid-Sevilla";
		String teams[] = description.split("-");
		Team t1 = new Team(teams[0]);
		Team t2 = new Team(teams[1]);
		Event ev = new Event(description, evDate, t1, t2);

		Mockito.doReturn(true).when(da).gertaerakKopiatu(
				ev, input);
		
		boolean res = sut.gertaerakKopiatu(ev, input);
		
		assertTrue(res);
	}
}
