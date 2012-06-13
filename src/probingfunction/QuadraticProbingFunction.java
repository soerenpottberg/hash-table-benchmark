package probingfunction;

public class QuadraticProbingFunction implements IProbingFunction {

	@Override
	public int probe(long key, int storageSize, int tries) {
		
		return tries * tries;
		
	}

}
