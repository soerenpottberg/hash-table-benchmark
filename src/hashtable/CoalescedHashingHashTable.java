package hashtable;

import hashfunction.IHashFunction;


/**
 * 
 * Doppeltes Hashen
 * @author Sören Pottberg
 *
 */
public class CoalescedHashingHashTable extends HashTable {

	public CoalescedHashingHashTable(int storageSize, IHashFunction hashFunction) {
		super(storageSize, hashFunction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveEntry(HashTableEntry entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashTableEntry readEntry(int key) {
		// TODO Auto-generated method stub
		return null;
	}

}
