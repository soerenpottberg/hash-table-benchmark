package hashtable;

import probingfunction.DoubleHashingProbingFunction;
import hashfunction.IHashFunction;


/**
 * 
 * Coalesced Hashing
 * @author Sören Pottberg
 *
 */
public class DoubleHashingHashTable extends OpenAddressingHashTable {

	public DoubleHashingHashTable(int storageSize, IHashFunction hashFunction) {
		
		super(storageSize, hashFunction, new DoubleHashingProbingFunction(hashFunction));

	}

}
