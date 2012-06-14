package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Universum {
	
	private static Set<Integer> keySet = new HashSet<Integer>();
	

	public static Integer[] generateKeys(int count) {

		keySet.clear();
		Random random       = new Random();
		Integer[] keys      = new Integer[count];

		while (keySet.size() < count) {
			keySet.add(random.nextInt(Integer.MAX_VALUE));
		}

		keys = keySet.toArray(keys);
		return keys;

	}

	public static Integer[] shuffleKeys(Integer[] keys) {

		List<Integer>  keyList =  Arrays.asList(keys);
		Collections.shuffle(keyList);
		return keyList.toArray(keys);

	}

	public static Integer[] generateNotExistingKeys(int count) {

		Set<Integer> notExistingKeySet = new HashSet<Integer>();
		Random random                  = new Random();
		Integer[] notExistingKeys      = new Integer[count];

		while (notExistingKeySet.size() < count) {
			int randomKey = random.nextInt(Integer.MAX_VALUE);
			if(!keySet.contains(randomKey)) {
				notExistingKeySet.add(randomKey);
			}
		}

		notExistingKeys = notExistingKeySet.toArray(notExistingKeys);
		return notExistingKeys;

	}

}
