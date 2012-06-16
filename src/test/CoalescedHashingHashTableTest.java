package test;

import hashfunction.CollisionHashFunction;
import hashfunction.DivisionRemainderHashFunction;
import hashtable.CoalescedHashingHashTable;

import org.junit.Test;

public class CoalescedHashingHashTableTest extends HashTableTest {

	@Test public void testCollision1() {
		
		runCollisionTest(new CoalescedHashingHashTable(3, new CollisionHashFunction(2)));
		
	}
	
	@Test public void testDataLoss1() {
		
		runDataLossTest(new CoalescedHashingHashTable(5, new CollisionHashFunction(3)));
		
	}
	
	@Test public void testDataLoss2() {
		
		runDataLossTest(new CoalescedHashingHashTable(5, new DivisionRemainderHashFunction()));
		
	}
	
}
