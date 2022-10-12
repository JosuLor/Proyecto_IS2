package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import org.business_logic.BLFacade;
import org.business_logic.BLFacadeImplementation;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import dataAccess.DataAccess;

import domain.Registered;



@RunWith(MockitoJUnitRunner.class)

class rankingLortuMockIntTest {

	@Mock
	DataAccess da = Mockito.mock(DataAccess.class);

	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(da);

	@Test
	void Test1() {

		Mockito.doReturn(new ArrayList<Registered>()).when(da).rankingLortu();

		da.open(false);
		ArrayList<Registered> list = (ArrayList<Registered>)da.rankingLortu();
		da.close();

		assertEquals(list, new ArrayList<Registered>());
	}

	@Test
	void Test2() {
		ArrayList<Registered> elem = new ArrayList<Registered>();
		elem.add(new Registered("UserTest", "123", 0));

		Mockito.doReturn(elem).when(da).rankingLortu();

		Registered r1 = new Registered("UserTest", "123", 0);
		List<Registered> expected = new ArrayList<Registered>();	
		expected.add(r1);

		da.open(false);
		ArrayList<Registered> obtained = (ArrayList<Registered>)da.rankingLortu();
		da.close();

		assertTrue(expected.get(0).getUsername().equals(obtained.get(0).getUsername()) && expected.size() == obtained.size());
	}
	
	@Test
	void Test3() {

		Registered r1 = new Registered("UserTest1", "123", 0);
		Registered r2 = new Registered("UserTest2", "321", 0);
		r2.setIrabazitakoa(2.00);
		List<Registered> expected = new ArrayList<Registered>();	
		expected.add(r2);
		expected.add(r1);
		
		Mockito.doReturn(expected).when(da).rankingLortu();
		
		ArrayList<Registered> obtained = (ArrayList<Registered>)da.rankingLortu();

		assertTrue(obtained.get(0).getUsername().equals(expected.get(0).getUsername()) && (obtained.size() == 2) );
	
	}
	
	
	@Test
	void Test4() {
		Registered r1 = new Registered("UserTest1", "123", 0);
		Registered r2 = new Registered("UserTest2", "321", 0);
		r2.setIrabazitakoa(2.00);
		List<Registered> expected = new ArrayList<Registered>();	
		expected.add(r1);
		expected.add(r2);
		
		Mockito.doReturn(expected).when(da).rankingLortu();
		
		ArrayList<Registered> obtained = (ArrayList<Registered>)da.rankingLortu();

		assertTrue(obtained.get(0).getUsername().equals(expected.get(0).getUsername()) && (obtained.size() == 2) );
	}
}