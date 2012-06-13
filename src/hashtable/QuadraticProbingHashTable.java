package hashtable;

import hashfunction.IHashFunction;
import probingfunction.QuadraticProbingFunction;


/**
 * 
 * Lineares Sondieren
 * @author S�ren Pottberg
 *
 */
public class QuadraticProbingHashTable extends OpenAddressingHashTable {

	public QuadraticProbingHashTable(int storageSize, IHashFunction hashFunction) {
		
		super(storageSize, hashFunction, new QuadraticProbingFunction());
		
	}

}
