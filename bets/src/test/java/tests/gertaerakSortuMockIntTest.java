package tests;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.business_logic.BLFacade;
import org.business_logic.BLFacadeImplementation;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import configuration.UtilDate;
import dataAccess.DataAccess;
import dataAccess.DataAccessGertaerakSortu;
import domain.Event;
import domain.EventParam;
import domain.Team;

public class gertaerakSortuMockIntTest {
	
	DataAccess da = Mockito.mock(DataAccess.class);
	
	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(false);

	Calendar today = Calendar.getInstance();
	String description;
	Date data;
	String sport;
	
	@Before
	public void Initialize() {
		description = null;
		data = null;
		sport = null;
	}
	
	@Test
	public void Test1() {
		
		boolean expected = false;
		
		description = "Atletico-Malaga";
		data = UtilDate.newDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 10);
		sport = "Futbol";
		EventParam params = new EventParam(description, data, sport);
		
		sut.gertaerakSortu(params);
		
		Mockito.doReturn(false).
			when(da).
			gertaerakSortu(params);
		
		boolean result = sut.gertaerakSortu(params);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void Test2() {
		
		boolean expected = true;
		
		description = "Atletico-Malaga";
		data = UtilDate.newDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 10);
		sport = "Futbol";
		EventParam params = new EventParam(description, data, sport);
		
		Mockito.doReturn(true).
			when(da).
			gertaerakSortu(params);
		
		boolean result = sut.gertaerakSortu(params);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void Test3() {
		
		boolean expected = false;
		
		description = "Atletico-Malaga";
		data = UtilDate.newDate(today.get(Calendar.YEAR), today.get(Calendar.MONTH), 10);
		sport = "Futbol";
		EventParam params = new EventParam(description, data, sport);
		
		sut.gertaerakSortu(params);
		
		params.sport = "UPV";
		Mockito.doReturn(false).
			when(da).
			gertaerakSortu(params);
		
		boolean result = sut.gertaerakSortu(params);
		
		assertEquals(expected, result);
	}
	
	@Test
	public void test4() {
		
		// 1. Expected value
		boolean expected = false;
		
		// 2. Set and get the result from the actual method
		description = null;
		data = null;
		sport = null;
		EventParam params = new EventParam(description, data, sport);
		
		Mockito.doReturn(false).
			when(da).
			gertaerakSortu(params);
		
		boolean result = sut.gertaerakSortu(params);
		
		// 3. Check via Assert
		assertEquals(expected, result);
		
		System.out.println("[JUnit test1]: El evento (" + description + ", " + data + ", " + sport + ") no fue registrado/creado; no se puede crear un evento nulo");
				
	}
}
