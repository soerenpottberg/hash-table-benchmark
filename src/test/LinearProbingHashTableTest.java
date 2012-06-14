package test;

import hashfunction.CollisionHashFunction;
import hashfunction.DivisionRemainderHashFunction;
import hashtable.LinearProbingHashTable;

import org.junit.Test;

public class LinearProbingHashTableTest extends HashTableTest {

	@Test public void testCollision1() {
		
		runCollisionTest(new LinearProbingHashTable(3, new CollisionHashFunction(2)));
		
	}
	
	@Test public void testDataLoss1() {
		
		runDataLossTest(new LinearProbingHashTable(5, new CollisionHashFunction(3)));
		
	}
	
	@Test public void testDataLoss2() {
		
		runDataLossTest(new LinearProbingHashTable(5, new DivisionRemainderHashFunction()));
		
	}
	
}
