package probingfunction;

import hashfunction.IHashFunction;

public class DoubleHashingProbingFunction implements IProbingFunction {

    protected IHashFunction hashFunction;
	
	/**
	 * @param hashFunction die zuverwendende Hashfunktion
	 */
	public DoubleHashingProbingFunction(IHashFunction hashFunction) {
		
		this.hashFunction = hashFunction;
		
	}
	
	@Override
	public int probe(long key, int storageSize, int tries) {

		// storageSize - 2 ist prim zu storageSize
		return tries * (hashFunction.hash(key, storageSize - 2) + 1);
		
	}

}
