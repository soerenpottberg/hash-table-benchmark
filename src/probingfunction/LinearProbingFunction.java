package probingfunction;

public class LinearProbingFunction implements IProbingFunction {

	@Override
	public int probe(long key, int storageSize, int tries) {
		
		return tries;
		
	}

}
