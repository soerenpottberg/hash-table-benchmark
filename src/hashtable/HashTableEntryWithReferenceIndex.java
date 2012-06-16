package hashtable;

public class HashTableEntryWithReferenceIndex extends HashTableEntry {

	/**
	 * Die Referenz auf einen Weiteren Tabelleneintrag
	 */
	private Integer referenceIndex;

	public HashTableEntryWithReferenceIndex(int key, Object data, Integer referenceIndex) {
		
		super(key, data);
		this.setReferenceIndex(referenceIndex);

	}
	
	/**
	 * @param entry Der Eintrag
	 * @param referenceIndex Der referenzierte Array-Index
	 */
    public HashTableEntryWithReferenceIndex(HashTableEntry entry, Integer referenceIndex) {
		
		this(entry.getKey(), entry.getData(), referenceIndex);

	}
    
    /**
	 * @param entry Der Eintrag
	 */
    public HashTableEntryWithReferenceIndex(HashTableEntry entry) {
		
    	this(entry, null);

	}

	/**
	 * @return Der referenzierte Array-Index
	 */
	public Integer getReferenceIndex() {
		return referenceIndex;
	}

	/**
	 * @param referenceIndex Der referenzierte Array-Index
	 */
	public void setReferenceIndex(Integer referenceIndex) {
		this.referenceIndex = referenceIndex;
	}

	public boolean hasReference() {
		return referenceIndex != null;
	}

}
