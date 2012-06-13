package hashfunction;

public class MultiplicativeHashFunction implements IHashFunction {

	private static final double GOLDEN_RATIO = (1 + Math.sqrt(5)) / 2;
	
	@Override
	public int hash(long key, int storageSize) {
		
		double irrationalNumber = GOLDEN_RATIO - 1;
        return (int) (storageSize * (key * irrationalNumber - (long)(key * irrationalNumber)));
	}

}
