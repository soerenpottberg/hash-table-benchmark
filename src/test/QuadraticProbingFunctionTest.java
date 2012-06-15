package test;

import hashtable.HashTable;

import java.util.Arrays;
import java.util.TreeSet;

import org.junit.Test;

import probingfunction.IProbingFunction;
import probingfunction.QuadraticProbingFunction;

import junit.framework.TestCase;

public class QuadraticProbingFunctionTest extends TestCase {

	@Test public void testProbe() {
		
		IProbingFunction probingFunction = new QuadraticProbingFunction();
		assertEquals( 1, probingFunction.probe(0, 0, 1));
		assertEquals(-1, probingFunction.probe(0, 0, 2));
		assertEquals( 4, probingFunction.probe(0, 0, 3));
		assertEquals(-4, probingFunction.probe(0, 0, 4));
		assertEquals( 9, probingFunction.probe(0, 0, 5));
		assertEquals(-9, probingFunction.probe(0, 0, 6));
		
	}
	
	@Test public void testProbe2() {
		
		IProbingFunction probingFunction = new QuadraticProbingFunction();
		
		int storageSize = 103;
		int hashCode = 25;
		int firstHashCode = hashCode;
		
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(hashCode);
		
		int tries = 0;
		while(tries < 1*storageSize - 1) {
			tries ++;
			hashCode = HashTable.calculateHashTableIndex(firstHashCode + probingFunction.probe(0, storageSize, tries), storageSize);
			set.add(hashCode);
		}
		
		for (int i = 0; i < storageSize; i++) {
			assertTrue("Zahl " + i + " nicht enthalten.", set.contains(i));
		}
		
	}
	
}
