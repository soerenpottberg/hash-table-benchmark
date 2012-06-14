package test;

import hashfunction.CollisionHashFunction;
import hashfunction.DivisionRemainderHashFunction;
import hashtable.HashTableEntry;
import hashtable.IHashTable;
import hashtable.LinearProbingHashTable;

import org.junit.Test;

import junit.framework.TestCase;

public class LinearProbingHashTableTest extends HashTableTest {

	@Test public void testCollision1() {
		
		IHashTable hashTable = new LinearProbingHashTable(3, new CollisionHashFunction(2));
		try {
            assertEquals(0, hashTable.getSaveTries());
			hashTable.saveEntry(new HashTableEntry(0, null));
			assertEquals(1, hashTable.getSaveTries());
			hashTable.saveEntry(new HashTableEntry(1, null));
			assertEquals(3, hashTable.getSaveTries());
			hashTable.saveEntry(new HashTableEntry(2, null));
            assertEquals(6, hashTable.getSaveTries());
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
	@Test public void testDataLoss1() {
		
		runDataLossTest(new LinearProbingHashTable(5, new CollisionHashFunction(3)));
		
	}
	
	@Test public void testDataLoss2() {
		
		runDataLossTest(new LinearProbingHashTable(5, new DivisionRemainderHashFunction()));
		
	}
	
}
