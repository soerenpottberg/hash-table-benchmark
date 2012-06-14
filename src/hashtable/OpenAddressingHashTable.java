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
	public void saveEntry(HashTableEntry entry) throws Exception {
		
		// Berechne den Hashcode zum speichern
		int hashCode = hashFunction.hash(entry.getKey(), storageSize);
		int firstHashCode = hashCode;
		
		// Kollisionserkennung
		while(hashTable[hashCode] != null && hashTable[hashCode].getKey() != entry.getKey()) {
			saveTries++;
			hashCode = (hashCode + probingFunction.probe(entry.getKey(), storageSize, saveTries)) % storageSize;
			if(hashCode == firstHashCode) {
				throw new Exception("Sondierung fehlgeschlagen.");
			}
		}
		hashTable[hashCode] = entry;
		
	}
	
	@Override
	public HashTableEntry readEntry(int key){
		
		// Berechne den Hashcode zum lesen
		int hashCode = hashFunction.hash(key, storageSize);
		int firstHashCode = hashCode;
		
		// Kollisionserkennung
		while(hashTable[hashCode] != null && hashTable[hashCode].getKey() != key) {
			readTries++;
			hashCode = (hashCode + probingFunction.probe(key, storageSize, readTries)) % storageSize;
			if(hashCode == firstHashCode) {
				return null;
			}
		}
		
		return hashTable[hashCode];
		
	}
	
}
