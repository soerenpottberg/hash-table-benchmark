package hashtable;

import hashfunction.IHashFunction;


/**
 * Direkte Verkettung
 * @author Sören Pottberg
 *
 */
public class SeparateChainingWithListHeadsHashTable extends HashTable {

	public SeparateChainingWithListHeadsHashTable(int storageSize,
			IHashFunction hashFunction) {
		super(storageSize, hashFunction);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveEntry(HashTableEntry entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashTableEntry readEntry(long key) {
		// TODO Auto-generated method stub
		return null;
	}

}
