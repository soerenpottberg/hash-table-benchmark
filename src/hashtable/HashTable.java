package hashtable;

import hashfunction.IHashFunction;


public abstract class HashTable implements IHashTable {
	
	protected int storageSize;
	protected IHashFunction hashFunction;
	protected int saveTries = 0;
	protected int readTries = 0;
	
	/**
	 * @param storageSize die Größe der Hashtable
	 * @param hashFunction die zuverwendende Hashfunktion
	 */
	public HashTable(int storageSize, IHashFunction hashFunction) {
		this.storageSize  = storageSize;
		this.hashFunction = hashFunction;
	}
	
	public int getSaveTries() {
		return saveTries;
	}

	public int getReadTries() {
		return readTries;
	}
	
	public void resetReadTries() {
		readTries = 0;
	}
	
}
