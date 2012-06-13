package hashfunction;

public class DivisionRemainderHashFunction implements IHashFunction {

	@Override
	public int hash(long key, int storageSize) {
		
		return (int) (key % storageSize);
		
	}

}
