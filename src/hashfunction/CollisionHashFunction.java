package hashfunction;

public class CollisionHashFunction implements IHashFunction {

	int hashValue;

	public CollisionHashFunction(int hashValue) {
		this.hashValue = hashValue;
	}

	@Override
	public int hash(long key, int storageSize) {

		return hashValue;

	}

}
