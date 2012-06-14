package test;

import hashfunction.CollisionHashFunction;
import hashfunction.DivisionRemainderHashFunction;
import hashtable.QuadraticProbingHashTable;

import org.junit.Test;

public class QuadraticProbingHashTableTest extends HashTableTest {

	@Test public void testCollision1() {
		
		runCollisionTest(new QuadraticProbingHashTable(3, new CollisionHashFunction(2)));
		
	}
	
    @Test public void testCollision2() {
		
		runCollisionTest(new QuadraticProbingHashTable(4, new CollisionHashFunction(1)));
		
	}
	
	@Test public void testDataLoss1() {
		
		runDataLossTest(new QuadraticProbingHashTable(5, new CollisionHashFunction(3)));
		
	}
	
	@Test public void testDataLoss2() {
		
		runDataLossTest(new QuadraticProbingHashTable(5, new DivisionRemainderHashFunction()));
		
	}
	
    @Test public void testDataLoss3() {
		
		runDataLossTest(new QuadraticProbingHashTable(3, new DivisionRemainderHashFunction()));
		
	}
	
}
