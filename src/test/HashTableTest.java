package test;

import hashtable.HashTable;
import hashtable.HashTableEntry;
import hashtable.IHashTable;

import junit.framework.TestCase;

public class HashTableTest extends TestCase {

	public void testCalculateHashTableIndex() {
		
		assertEquals(0, HashTable.calculateHashTableIndex(0, 3));
		assertEquals(1, HashTable.calculateHashTableIndex(1, 3));
		assertEquals(2, HashTable.calculateHashTableIndex(2, 3));
		assertEquals(0, HashTable.calculateHashTableIndex(3, 3));
		assertEquals(1, HashTable.calculateHashTableIndex(4, 3));
		assertEquals(2, HashTable.calculateHashTableIndex(5, 3));
		
		assertEquals(2, HashTable.calculateHashTableIndex(-1, 3));
		assertEquals(1, HashTable.calculateHashTableIndex(-2, 3));
		assertEquals(0, HashTable.calculateHashTableIndex(-3, 3));
		assertEquals(2, HashTable.calculateHashTableIndex(-4, 3));
		assertEquals(1, HashTable.calculateHashTableIndex(-5, 3));
		
		for (int i = 1; i <= 1000; i++) {
			for (int j = -1000; j <= 1000; j++) {
				assertTrue(HashTable.calculateHashTableIndex(j, i) < i);
				assertTrue(HashTable.calculateHashTableIndex(j, i) >= 0);
			}
		}
		
	}
	
	public void runCollisionTest(IHashTable hashTable) {
		
		try {
            assertEquals(0, hashTable.getSaveTries());
            assertEquals(0, hashTable.getReadTries());
			hashTable.saveEntry(new HashTableEntry(0, null));
			hashTable.readEntry(0);
			assertEquals(1, hashTable.getSaveTries());
			assertEquals(1, hashTable.getReadTries());
			hashTable.saveEntry(new HashTableEntry(1, null));
			hashTable.readEntry(1);
			assertEquals(3, hashTable.getSaveTries());
			assertEquals(3, hashTable.getReadTries());
			hashTable.saveEntry(new HashTableEntry(2, null));
			hashTable.readEntry(2);
            assertEquals(6, hashTable.getSaveTries());
            assertEquals(6, hashTable.getReadTries());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	public void runDataLossTest(IHashTable hashTable) {
		
		try {
			hashTable.saveEntry(new HashTableEntry(3, "A"));
			hashTable.saveEntry(new HashTableEntry(4, "B"));
			hashTable.saveEntry(new HashTableEntry(0, "C"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals("B", hashTable.readEntry(4).getData());
		assertEquals("C", hashTable.readEntry(0).getData());
		assertEquals("A", hashTable.readEntry(3).getData());
		
	}
}
