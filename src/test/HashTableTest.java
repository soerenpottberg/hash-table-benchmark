package test;

import hashtable.HashTableEntry;
import hashtable.IHashTable;

import junit.framework.TestCase;

public class HashTableTest extends TestCase {

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
