package hashtable;

import hashfunction.IHashFunction;
import probingfunction.LinearProbingFunction;


/**
 * 
 * Quadratisches Sondieren
 * @author S�ren Pottberg
 *
 */
public class LinearProbingHashTable extends OpenAddressingHashTable {

	public LinearProbingHashTable(int storageSize, IHashFunction hashFunction) {
		
		super(storageSize, hashFunction, new LinearProbingFunction());
		
	}

}
