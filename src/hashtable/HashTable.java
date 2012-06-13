package hashtable;

import hashfunction.IHashFunction;


public abstract class HashTable implements IHashTable {
	
	protected int storageSize;
	protected IHashFunction hashFunction;
	
	/**
	 * @param storageSize die Größe der Hashtable
	 * @param hashFunction die zuverwendende Hashfunktion
	 */
	public HashTable(int storageSize, IHashFunction hashFunction) {
		this.storageSize  = storageSize;
		this.hashFunction = hashFunction;
	}
	
}
