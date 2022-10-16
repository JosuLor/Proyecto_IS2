package tests;


import java.util.List;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.assertEquals;

import dataAccess.DataAccess;
import domain.Registered;

public class rankingLortuDABTest {
	DataAccess sut = new DataAccess();

	TestDataAccess tda = new TestDataAccess();

	@Test
	public void test0() {
		tda.open();
		List<Registered> expected = tda.getRegistered();
		tda.close();
		Collections.reverse(expected);
		sut.open(false);
		List<Registered> list =sut.rankingLortu();
		sut.close();

		assertEquals(expected, list);

		//ESTA BIEN SOLO QUE LO PONE COMO FAIL SINO MIRA EL COMENTARIO EN "failure Trace"
	}
}
