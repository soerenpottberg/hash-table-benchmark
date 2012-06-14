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
	
	/**
	 * Berechnet den physikalischen Index in der Hashtable
	 * @param hashCode der HashCode
	 * @param storageSize die Größe der Hashtable
	 * @return der Index
	 */
	public static int calculateHashTableIndex(int hashCode, int storageSize) {
		
		int index = hashCode % storageSize;
		if (index < 0) {
			index += storageSize;
		}
		return index;
		
	}
	
}
