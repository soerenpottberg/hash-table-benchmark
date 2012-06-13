package hashtable;

public interface IHashTable {

	/**
	 * Speichert einen Eintrag in der Hashtable
	 * @param HashTableEntry der Eintrag
	 */
	void saveEntry(HashTableEntry entry);
	
	/**
	 * Liest einen Eintrag aus der Hashtable
	 * @param key der Schlüssel des Eintrages
	 * @return der Eintrag
	 */
	HashTableEntry readEntry(long key);
	
}
