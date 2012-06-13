package main;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Universum {
	
	public Integer[] generateKeys(int count) {
		
		Set<Integer> keySet = new TreeSet<Integer>();
		Random random       = new Random();
		Integer[] keys      = new Integer[count];
		
		while (keySet.size() < count) {
			keySet.add(random.nextInt(Integer.MAX_VALUE));
		}
		
		keys = keySet.toArray(keys);
		return keys;
		
	}

}
