package test;

import hashfunction.CollisionHashFunction;
import hashtable.HashTableEntry;
import hashtable.IHashTable;
import hashtable.LinearProbingHashTable;

import org.junit.Test;

import junit.framework.TestCase;

public class LinearProbingHashTableTest extends TestCase {

	@Test public void testCollision1() {
		
		IHashTable hashTable = new LinearProbingHashTable(5, new CollisionHashFunction(3));
		try {
			hashTable.saveEntry(new HashTableEntry(0, null));
			hashTable.saveEntry(new HashTableEntry(1, null));
			hashTable.saveEntry(new HashTableEntry(2, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(6, hashTable.getSaveTries());
		
	}
}
