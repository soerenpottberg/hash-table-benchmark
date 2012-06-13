package hashtable;

public class HashTableEntry {
	
	private int key;
	protected Object data;
	
	/**
	 * @param key der Schlüssel des Eintrages
	 * @param data die Daten des Eintrages
	 */
	public HashTableEntry(int key, Object data) {
		this.key  = key;
		this.data = data;
	}

	public int getKey() {
		return key;
	}
	
}
