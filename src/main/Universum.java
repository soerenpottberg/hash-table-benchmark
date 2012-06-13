package main;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Universum {
	
	public Integer[] generateKeys(int count) {
		
		Set<Integer> keySet = new HashSet<Integer>();
		Random random       = new Random();
		Integer[] keys      = new Integer[count];
		
		while (keySet.size() < count) {
			keySet.add(random.nextInt(Integer.MAX_VALUE));
		}
		
		keys = keySet.toArray(keys);
		return keys;
		
	}

}
