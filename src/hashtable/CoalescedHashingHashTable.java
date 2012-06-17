package hashtable;

import hashfunction.IHashFunction;
import hashtableentry.HashTableEntry;
import hashtableentry.HashTableEntryWithReferenceIndex;


/**
 * 
 * Doppeltes Hashen
 * @author Sören Pottberg
 *
 */
public class CoalescedHashingHashTable extends HashTable {

	private HashTableEntryWithReferenceIndex[] hashTable;

	public CoalescedHashingHashTable(int storageSize, IHashFunction hashFunction) {
		
		super(storageSize, hashFunction);
		hashTable = new HashTableEntryWithReferenceIndex[storageSize];
		
	}

	@Override
	public void saveEntry(HashTableEntry entry) throws Exception {
		saveEntry(new HashTableEntryWithReferenceIndex(entry));
	}
	
	public void saveEntry(HashTableEntryWithReferenceIndex entry) throws Exception {
		
		// Berechne den Hashcode zum speichern
		int hashCode = hashFunction.hash(entry.getKey(), storageSize);
		int index = hashCode;
		
		// Kollisionserkennung
		HashTableEntryWithReferenceIndex currentEntry = hashTable[hashCode];
		saveTries++;
		if(currentEntry != null && currentEntry.getKey() != entry.getKey()) {
			saveTries++;
			
			// Freien Platz ermitteln
			index = getNextFreeIndex();
			
			// Letzen Eintrag in der "Liste" finden
			while(currentEntry != null && currentEntry.getKey() != entry.getKey() && currentEntry.hasReference()) {
				hashCode = currentEntry.getReferenceIndex();
				currentEntry = hashTable[hashCode];
				saveTries++;
			};
			
			// Index sichern
			currentEntry.setReferenceIndex(index);
			
		}
		hashTable[index] = entry;
		
	}

	private int getNextFreeIndex() throws Exception {
		
		int index = hashTable.length - 1;
		while(index >= 0 && hashTable[index] != null) {
			index--;
		}
		if(index == -1)	{
			throw new Exception("Kein Speicherplatz.");
		}
		return index;
				
	}

	@Override
	public HashTableEntry readEntry(int key) {

		HashTableEntryWithReferenceIndex currentEntry;
		
		// Berechne den Hashcode zum lesen
		int hashCode = hashFunction.hash(key, storageSize);

		// Kollisionserkennung
		currentEntry = hashTable[hashCode];
		readTries++;
		if(currentEntry != null && currentEntry.getKey() != key) {
			while(currentEntry != null && currentEntry.getKey() != key && currentEntry.hasReference()) {
				hashCode = currentEntry.getReferenceIndex();
				currentEntry = hashTable[hashCode];
				readTries++;
			};
			if(currentEntry.getKey() != key) {
				return null;
			}
		} else {
			 currentEntry = hashTable[hashCode];
		}
		
		return currentEntry;
		
	}

}
