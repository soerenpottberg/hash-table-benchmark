package hashtable;

import hashfunction.IHashFunction;


/**
 * Separate Verkettung
 * @author Sören Pottberg
 *
 */
public class SeparateChainingHashTable extends HashTable {

	protected HashTableEntryWithReference[] hashTable;

	public SeparateChainingHashTable(int storageSize, IHashFunction hashFunction) {
		
		super(storageSize, hashFunction);
		hashTable = new HashTableEntryWithReference[storageSize];
		
	}

	@Override
	public void saveEntry(HashTableEntry entry) {
		saveEntry(new HashTableEntryWithReference(entry));
	}
	
	public void saveEntry(HashTableEntryWithReference entry) {
		
		// Berechne den Hashcode zum speichern
		int hashCode = hashFunction.hash(entry.getKey(), storageSize);
		
		// Kollisionserkennung
		saveTries++;
		if(hashTable[hashCode] != null && hashTable[hashCode].getKey() != entry.getKey()) {
			HashTableEntryWithReference currentEntry = hashTable[hashCode];
			saveTries++;
			while(currentEntry.hasReference()) {
				currentEntry = currentEntry.getReference();
				saveTries++;
			}
			currentEntry.setReference(entry);
		} else {
			hashTable[hashCode] = entry;
		}
		
	}

	@Override
	public HashTableEntry readEntry(int key) {

		HashTableEntryWithReference entry;
		
		// Berechne den Hashcode zum lesen
		int hashCode = hashFunction.hash(key, storageSize);

		// Kollisionserkennung
		readTries++;
		if(hashTable[hashCode] != null && hashTable[hashCode].getKey() != key) {
			entry = hashTable[hashCode];
			while(entry.getKey() != key && entry.hasReference()) {
				entry = entry.getReference();
				readTries++;
			}
			if(entry.getKey() != key) {
				return null;
			}
		} else {
			entry = hashTable[hashCode];
		}
		
		return entry;
		
	}

}
