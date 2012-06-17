package hashtableentry;


public class HashTableEntryWithReference extends HashTableEntry {
	
	/**
	 * Die Referenz auf einen Weiteren Tabelleneintrag
	 */
	private HashTableEntryWithReference reference;

	public HashTableEntryWithReference(int key, Object data, HashTableEntryWithReference reference) {
		
		super(key, data);
		this.setReference(reference);

	}
	
	/**
	 * @param entry Der Eintrag
	 * @param reference Die Referenz
	 */
    public HashTableEntryWithReference(HashTableEntry entry, HashTableEntryWithReference reference) {
		
		this(entry.getKey(), entry.getData(), reference);

	}
    
    /**
	 * @param entry Der Eintrag
	 */
    public HashTableEntryWithReference(HashTableEntry entry) {
		
    	this(entry, null);

	}

	/**
	 * @return Die Referenz
	 */
	public HashTableEntryWithReference getReference() {
		return reference;
	}

	/**
	 * @param reference Die Referenz
	 */
	public void setReference(HashTableEntryWithReference reference) {
		this.reference = reference;
	}

	public boolean hasReference() {
		return reference != null;
	}

}
