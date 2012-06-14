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
	 * 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
	 * 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271,
	 * 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379,
	 * 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479,
	 * 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599,
	 * 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701,
	 * 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823,
	 * 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941,
	 * 947, 953, 967, 971, 977, 983, 991, 997
	 */

	static final int STORAGE_SIZE = 101;

	/**
	 * Füllstände
	 */
	static double[] betas = {0.5, 0.9, 0.95, 1};
	
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
		
		for (double beta : betas) {
			
			hashTables[0] = new LinearProbingHashTable(STORAGE_SIZE, hashFunction);
			hashTables[1] = new QuadraticProbingHashTable(STORAGE_SIZE, hashFunction);
			hashTables[2] = new DoubleHashingHashTable(STORAGE_SIZE, hashFunction);
			
			keyCount = (int) (beta * STORAGE_SIZE);
			keys                 = Universum.generateKeys(keyCount);
			shuffledExistingKeys = Universum.shuffleKeys(keys);
			notExistingKeys      = Universum.generateNotExistingKeys(keyCount);

			for (IHashTable hashTable : hashTables) {
				System.out.println(hashTable.getClass().getSimpleName());
				System.out.println("Beta: " + beta);
				runTest(hashTable);
				System.out.println("----------------------------------------");
			}
			
			System.out.println("========================================");
		
		}
		
	}


	private static void runTest(IHashTable hashTable) {

		//System.out.println("Saving...");
		for (Integer key : keys) {
			/*System.out.print("Key: ");
			System.out.print(key);
			System.out.print("; ");*/
			HashTableEntry entry = new HashTableEntry(key, "Ben");
			try {
				hashTable.saveEntry(entry);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
		/*System.out.println("\n");
		System.out.println("Versuche:");
		System.out.println((hashTable.getSaveTries()) / (float)keyCount);*/
		
		//System.out.println();
		//System.out.println("Reading...");
		for (Integer key : shuffledExistingKeys) {
			HashTableEntry entry;
			entry = hashTable.readEntry(key);
			/*System.out.print("Key: ");
			System.out.print(key);
			System.out.print(" [Key: ");
			System.out.print(entry.getKey());
			System.out.print(" Data: ");
			System.out.print(entry.getData());
			System.out.print("]; ");*/

		}
		
		System.out.println("Erfolgreiches Lesen:");
		System.out.println((hashTable.getReadTries()) / (float)keyCount);
		
		hashTable.resetReadTries();
		
		
		//System.out.println();
		//System.out.println("Reading not existing...");
		for (Integer key : notExistingKeys) {
			HashTableEntry entry;
			entry = hashTable.readEntry(key);
			/*System.out.print(" Key: ");
			System.out.print(key);
			System.out.print(" [");
			System.out.print(entry);
			System.out.print("]; ");*/

		}
		
		System.out.println("Erfolgloses Lesen:");
		System.out.println((hashTable.getReadTries()) / (float)keyCount);
		
	}

}
