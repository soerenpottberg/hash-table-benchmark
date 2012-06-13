package hashtable;

import probingfunction.IProbingFunction;
import hashfunction.IHashFunction;

public abstract class OpenAddressingHashTable extends HashTable{

	protected HashTableEntry[] hashTable;
	protected IProbingFunction probingFunction;
	
	public OpenAddressingHashTable (int storageSize, IHashFunction hashFunction, IProbingFunction probingFunction) {
		
		super(storageSize, hashFunction);
		this.probingFunction = probingFunction;
		hashTable = new HashTableEntry[storageSize];
		
	}
	
	@Override
	public void saveEntry(HashTableEntry entry) {
		
		// Berechne den Hashcode zum speichern
		int hashCode = hashFunction.hash(entry.getKey(), storageSize);
		
		// Kollisionserkennung
		int tries = 0;
		while(hashTable[hashCode] != null && hashTable[hashCode].getKey() != entry.getKey()) {
			tries++;
			hashCode = (hashCode + probingFunction.probe(entry.getKey(), storageSize, tries)) % storageSize;
		}
		hashTable[hashCode] = entry;
		
	}
	
	@Override
	public HashTableEntry readEntry(int key){
		
		// Berechne den Hashcode zum lesen
		int hashCode = hashFunction.hash(key, storageSize);
		
		// Kollisionserkennung
		int tries = 0;
		while(hashTable[hashCode] != null && hashTable[hashCode].getKey() != key) {
			tries++;
			hashCode = (hashCode + probingFunction.probe(key, storageSize, tries)) % storageSize;
		}
		
		return hashTable[hashCode];
		
	}
	
}
