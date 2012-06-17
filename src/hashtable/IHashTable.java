package hashtable;

import hashtableentry.HashTableEntry;

public interface IHashTable {

	/**
	 * Speichert einen Eintrag in der Hashtable
	 * @param HashTableEntry der Eintrag
	 * @throws Exception 
	 */
	void saveEntry(HashTableEntry entry) throws Exception;
	
	/**
	 * Liest einen Eintrag aus der Hashtable
	 * @param key der Schlüssel des Eintrages
	 * @return der Eintrag
	 */
	HashTableEntry readEntry(int key);
	
	/**
	 * 
	 * @return Speicherversuche
	 */
	int getSaveTries();

    /**
     * 
     * @return Leseversuche
     */
    int getReadTries();

    /**
     * Leseversuche zurücksetzen
     */
	void resetReadTries();
	
	
}
