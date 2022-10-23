package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccessRankingLortu;
import domain.Registered;

public class rankingLortuDAB2 {

	//Tengo que usar los métodos de caja blanca ya a que no tengo casi
	//test de la caja negra
	
DataAccessRankingLortu sut = new DataAccessRankingLortu();
	
	TestDataAccess tda = new TestDataAccess();

	List<Registered> registradosList;
	
	@Before
	public void initialize() {
		tda.open();
		registradosList = tda.getRegistered();
		tda.close();
		
		for(Registered r : registradosList) {
			System.out.print(r.getUsername() + " ");
		}
		System.out.println();
		
		
		tda.open();
		tda.removeAllRegistered();
		tda.close();
	}
	
	@After
	public void finalize() {
		tda.open();
		tda.removeAllRegistered();
		tda.addAllRegisteredBase(registradosList);
		tda.close();
	}
	
	@Test
	public void test0() { //Lista vacia pk no se mete en el for
		try {			
			sut.open(false);
			List<Registered> list = sut.rankingLortu();
			sut.close();
			
			assertEquals(list, new ArrayList<Registered>()); //Lista vacia
			
		}catch (Exception e) {
			System.out.println(e.getClass());
			fail("Nope");
		}
	}
	
	
	@Test
	public void test1() {
		try {
			Registered r1 = new Registered("UserTest", "123", 0);
			List<Registered> expected = new ArrayList<Registered>();	
			expected.add(r1);
						
			sut.open(false);
			sut.storeRegistered("UserTest", "123", 0);
			List<Registered> obtained = sut.rankingLortu();
			sut.close();
			
			System.out.println("HOLA");
			System.out.println(expected.get(0).getUsername());
			System.out.println(obtained.get(0).getUsername());
			
			assertTrue(expected.get(0).getUsername().equals(obtained.get(0).getUsername()) && expected.size() == obtained.size());
		}catch(Exception e) {
			fail();
		}
	}
	
//	@Test
//	public void test2() {
//		try {
//			
//			Registered r1 = new Registered("UserTest1", "123", 0);
//			Registered r2 = new Registered("UserTest2", "321", 0);
//			r2.setIrabazitakoa(2.00);
//			List<Registered> expected = new ArrayList<Registered>();	
//			expected.add(r2);
//			expected.add(r1);
//			
//			tda.open();
//			tda.storeRegistered("UserTest1", "123", 0, 1.00);
//			tda.storeRegistered("UserTest2", "321", 0, 2.00);
//			tda.close();
//		
//			sut.open(false);
//			List<Registered> obtained = sut.rankingLortu();
//			sut.close();
//			
//
//			for(Registered r : expected) {
//				System.out.print(r.getUsername() + " ");
//			}
//			System.out.println();
//			for(Registered r : obtained) {
//				System.out.print(r.getUsername() + " ");
//			}
//			System.out.println();
//			
//			assertTrue(obtained.get(0).getUsername().equals(expected.get(0).getUsername()) && (obtained.size() == 2) );
//		}catch(Exception e) {
//			fail();
//		}
//		
//	}
	
//	
//	@Test
//	public void test3() {
//		try {
//			
//			Registered r1 = new Registered("UserTest1", "123", 0);
//			Registered r2 = new Registered("UserTest2", "321", 0);
//			r2.setIrabazitakoa(2.00);
//			List<Registered> expected = new ArrayList<Registered>();	
//			expected.add(r1);
//			expected.add(r2);
//			
//			tda.open();
//			tda.storeRegistered("UserTest1", "123", 0, 1.00);
//			tda.storeRegistered("UserTest2", "321", 0, 0.00);
//			tda.close();
//		
//			sut.open(false);
//			List<Registered> obtained = sut.rankingLortu();
//			sut.close();
//			
//
//			for(Registered r : expected) {
//				System.out.print(r.getUsername() + " ");
//			}
//			System.out.println();
//			for(Registered r : obtained) {
//				System.out.print(r.getUsername() + " ");
//			}
//			System.out.println();
//			
//			assertTrue(obtained.get(0).getUsername().equals(expected.get(0).getUsername()) && (obtained.size() == 2) );
//		}catch(Exception e) {
//			fail();
//		}
//		
//	}
	


		


	}
