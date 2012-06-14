package hashtable;

public class HashTableEntry {
	
	private int key;
	private Object data;
	
	/**
	 * @param key der Schlüssel des Eintrages
	 * @param data die Daten des Eintrages
	 */
	public HashTableEntry(int key, Object data) {
		this.key  = key;
		this.setData(data);
	}

	public int getKey() {
		return key;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
