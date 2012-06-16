package hashtable;

import hashfunction.IHashFunction;


/**
 * Direkte Verkettung
 * @author Sören Pottberg
 *
 */
public class DirectChainingHashTable extends SeparateChainingHashTable {

	public DirectChainingHashTable(int storageSize,
			IHashFunction hashFunction) {
		super(storageSize, hashFunction);
	}

	@Override
	public HashTableEntry readEntry(int key) {
		// Berechne den Hashcode zum lesen
		int hashCode = hashFunction.hash(key, storageSize);
		if(hashTable[hashCode] == null){
			return null;
		}
		return super.readEntry(key);
	}
	

}
