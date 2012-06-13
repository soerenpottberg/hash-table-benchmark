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
	
	public void saveEntry(HashTableEntry entry) {
		
		// Berechne den Hashcode zum speichern
		int hashCode = hashFunction.hash(entry.key, storageSize);
		
		// Kollisionserkennung
		int tries = 0;
		while(hashTable[hashCode] != null && hashTable[hashCode].key != entry.key) {
			tries++;
			hashCode = (hashCode + probingFunction.probe(entry.key, storageSize, tries)) % storageSize;
		}
		hashTable[hashCode] = entry;
		
	}
	
	public HashTableEntry readEntry(long key){
		
		// Berechne den Hashcode zum lesen
		int hashCode = hashFunction.hash(key, storageSize);
		
		// Kollisionserkennung
		int tries = 0;
		while(hashTable[hashCode] != null && hashTable[hashCode].key != key) {
			tries++;
			hashCode = (hashCode + probingFunction.probe(key, storageSize, tries)) % storageSize;
		}
		
		return hashTable[hashCode];
		
	}
	
}
