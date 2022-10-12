package tests;


import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import dataAccess.DataAccess;
import domain.Registered;

class rankingLortuDABTest {
	DataAccess sut = new DataAccess();
	
	TestDataAccess tda = new TestDataAccess();

	@Test
	void test0() {
		tda.open();
		List<Registered> expected = tda.getRegistered();
		tda.close();
		sut.open(false);
		List<Registered> list =sut.rankingLortu();
		sut.close();
		
		assertEquals(expected, list);
	}
	

}
