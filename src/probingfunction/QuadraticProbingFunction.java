package probingfunction;

public class QuadraticProbingFunction implements IProbingFunction {

	@Override
	public int probe(long key, int storageSize, int tries) {
		
		return (int) (Math.pow(-1, tries + 1) * Math.pow(Math.ceil(tries / 2.0), 2));
		
	}

}
