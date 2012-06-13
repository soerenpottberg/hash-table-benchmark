package hashfunction;

public class MultiplicativeHashFunction implements IHashFunction {

	private static final double GOLDEN_RATIO = (1 + Math.sqrt(5)) / 2;
	
	@Override
	public int hash(long key, int storageSize) {
		
        return storageSize * (int)(key * GOLDEN_RATIO - (int)(key * GOLDEN_RATIO));
        
	}

}
