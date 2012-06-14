package test;

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
	
}
