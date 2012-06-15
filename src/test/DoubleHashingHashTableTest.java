package test;

import hashfunction.CollisionHashFunction;
import hashfunction.DivisionRemainderHashFunction;
import hashtable.DoubleHashingHashTable;

import org.junit.Test;

public class DoubleHashingHashTableTest extends HashTableTest {

	@Test public void testDataLoss1() {
		
		runDataLossTest(new DoubleHashingHashTable(5, new CollisionHashFunction(3)));
		
	}
	
	@Test public void testDataLoss2() {
		
		runDataLossTest(new DoubleHashingHashTable(5, new DivisionRemainderHashFunction()));
		
	}
	
    @Test public void testDataLoss3() {
		
		runDataLossTest(new DoubleHashingHashTable(3, new DivisionRemainderHashFunction()));
		
	}
	
}
