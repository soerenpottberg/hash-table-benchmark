package test;

import hashfunction.CollisionHashFunction;
import hashfunction.DivisionRemainderHashFunction;
import hashtable.SeparateChainingHashTable;

import org.junit.Test;

public class SeparateChainingHashTableTest extends HashTableTest {

	@Test public void testCollision1() {
		
		runCollisionTest(new SeparateChainingHashTable(3, new CollisionHashFunction(2)));
		
	}
	
	@Test public void testDataLoss1() {
		
		runDataLossTest(new SeparateChainingHashTable(5, new CollisionHashFunction(3)));
		
	}
	
	@Test public void testDataLoss2() {
		
		runDataLossTest(new SeparateChainingHashTable(5, new DivisionRemainderHashFunction()));
		
	}
	
}
