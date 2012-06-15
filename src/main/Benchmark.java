package main;

import hashfunction.DivisionRemainderHashFunction;
import hashfunction.IHashFunction;
import hashfunction.MultiplicativeHashFunction;
import hashtable.DoubleHashingHashTable;
import hashtable.HashTableEntry;
import hashtable.IHashTable;
import hashtable.LinearProbingHashTable;
import hashtable.QuadraticProbingHashTable;

public class Benchmark {

	/**
	 * Primzahlen
	 * 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
	 * 89, 97, 101, *103*, *107*, 109, 113, *127*, *131*, 137, *139*, 149, *151*, 157, 163, 167, 173, 179,
	 * 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271,
	 * 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379,
	 * 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479,
	 * 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599,
	 * 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701,
	 * 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823,
	 * 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941,
	 * 947, 953, 967, 971, 977, 983, 991, 997
	 * 
	 * 4 * j + 3
	 * 83, 87, 91, 95, 99, *103*, *107*, 111, 115, 119, 123, *127*, *131*, 135, *139*, 143, 147, *151*,
	 * 155, 159, 163, 167, 171, 175, 179, 183, 187, 191, 195, 199, 203
	 */
	static final int STORAGE_SIZE = 1019; // 103

	/**
	 * Füllstände
	 */
	static double[] betas = {0.5, 0.9, 0.95, 1};
	
	static final int LINEAR_PROBING_HASH_TABLE    = 0;
	static final int QUADRATIC_PROBING_HASH_TABLE = 1;
	static final int DOUBLE_HASHING_HASH_TABLE    = 2;
	
	
	static double[][] theoreticalComplexitiesForExistingKeys =
		{{1.5,  5.5,  10.5, Double.POSITIVE_INFINITY},  // LinearProbingHashTable
		 {1.44, 2.85, 3.52, Double.POSITIVE_INFINITY},  // QuadraticProbingHashTable
		 {1.39, 5.56, 3.15, Double.POSITIVE_INFINITY}}; // DoubleHashingHashTable
	
	static double[][] theoreticalComplexitiesForNonExistingKeys =
		{{2.5,  50.5, 200.5, Double.POSITIVE_INFINITY},  // LinearProbingHashTable
		 {2.19, 11.4, 22.05, Double.POSITIVE_INFINITY},  // QuadraticProbingHashTable
		 {2,    10,   20,    Double.POSITIVE_INFINITY}}; // DoubleHashingHashTable
	
	static int keyCount;
	
	static Integer[] keys;
	static Integer[] shuffledExistingKeys;
	static Integer[] notExistingKeys;


	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IHashFunction hashFunction = new DivisionRemainderHashFunction();
		// IHashFunction hashFunction = new MultiplicativeHashFunction();
		
		IHashTable[] hashTables = new IHashTable[3];
		
			
		for (int betaIndex = 0; betaIndex < betas.length; betaIndex++) {
			
			double beta = betas[betaIndex];
			
			hashTables[LINEAR_PROBING_HASH_TABLE]    = new LinearProbingHashTable(STORAGE_SIZE, hashFunction);
			hashTables[QUADRATIC_PROBING_HASH_TABLE] = new QuadraticProbingHashTable(STORAGE_SIZE, hashFunction);
			hashTables[DOUBLE_HASHING_HASH_TABLE]    = new DoubleHashingHashTable(STORAGE_SIZE, hashFunction);
			
			keyCount = (int) (beta * STORAGE_SIZE);
			keys                 = Universum.generateKeys(keyCount);
			shuffledExistingKeys = Universum.shuffleKeys(keys);
			notExistingKeys      = Universum.generateNotExistingKeys(keyCount);

			for (int hashTableIndex = 0; hashTableIndex < hashTables.length; hashTableIndex++) {
				IHashTable hashTable = hashTables[hashTableIndex];
				
				System.out.println(hashTable.getClass().getSimpleName());
				System.out.println("Beta: " + beta);
				runTest(hashTable, theoreticalComplexitiesForExistingKeys[hashTableIndex][betaIndex], theoreticalComplexitiesForNonExistingKeys[hashTableIndex][betaIndex]);
				System.out.println("----------------------------------------");
			}
			
			System.out.println("========================================");
		
		}
		
	}


	private static void runTest(IHashTable hashTable, double theoreticalComplexityForExistingKeys, double theoreticalComplexityForNonExistingKeys) {

		// Speichern
		saveEntries(hashTable);
		
		// Erfolgreiches Lesen
		double complexityForExistingKeys = readWithExistingKeys(hashTable);
		
		complexityForExistingKeys = Math.round(complexityForExistingKeys * 100) / 100.0;
		
		System.out.println("Erfolgreiches Lesen:");
		System.out.println(complexityForExistingKeys + " (" + theoreticalComplexityForExistingKeys + ")");
		
		hashTable.resetReadTries();
				
		// Erfolgloses Lesen:
        double complexityForNonExistingKeys = readWithNonExistingKeys(hashTable);
		
		complexityForNonExistingKeys = Math.round(complexityForNonExistingKeys * 100) / 100.0;
		
		System.out.println("Erfolgloses Lesen:");
		System.out.println(complexityForNonExistingKeys + " (" + theoreticalComplexityForNonExistingKeys + ")");
		
	}

	/**
	 * Liest die Einträge
	 * @param hashTable
	 * @return durchschnittliche Leseversuche
	 */
	private static double readWithNonExistingKeys(IHashTable hashTable) {

		for (Integer key : notExistingKeys) {
			hashTable.readEntry(key);
		}
		
		return hashTable.getReadTries() / (double)keyCount;
		
	}


	/**
	 * Liest die Einträge
	 * @param hashTable
	 * @return durchschnittliche Leseversuche
	 */
	private static double readWithExistingKeys(IHashTable hashTable) {

		for (Integer key : shuffledExistingKeys) {
			hashTable.readEntry(key);
		}

		return hashTable.getReadTries() / (double)keyCount;
				
	}


	/**
	 * Speichert die Einträge
	 * @param hashTable
	 */
	private static void saveEntries(IHashTable hashTable) {

		for (Integer key : keys) {
			HashTableEntry entry = new HashTableEntry(key, "Ben");
			try {
				hashTable.saveEntry(entry);
			} catch (Exception e) {
				System.err.println(e.getMessage() + " " + hashTable.getClass().getSimpleName());
			}
		}
		
	}

}
