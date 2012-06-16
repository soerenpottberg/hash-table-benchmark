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
	static final int STORAGE_SIZE = 1019; // 103, 1019, 10007

	/**
	 * Füllstände
	 */
	static double[] betas = {0.5, 0.9, 0.95, 1};
	
	/**
	 * Anzahl der Testdurchläufe
	 */
	static final int TESTRUNS = 10;

	static final int LINEAR_PROBING_HASH_TABLE    = 0;
	static final int QUADRATIC_PROBING_HASH_TABLE = 1;
	static final int DOUBLE_HASHING_HASH_TABLE    = 2;

	static final int EXISTING_KEY     = 0;
	static final int NON_EXISTING_KEY = 1;

	static double[][] complexitiesForExistingKeys =
		{{0, 0, 0, 0},  // LinearProbingHashTable
		{0, 0, 0, 0},   // QuadraticProbingHashTable
		{0, 0, 0, 0}};  // DoubleHashingHashTable

	static double[][] complexitiesForNonExistingKeys =
		{{0, 0, 0, 0},  // LinearProbingHashTable
		{0, 0, 0, 0},   // QuadraticProbingHashTable
		{0, 0, 0, 0}};  // DoubleHashingHashTable

	static double[][] theoreticalComplexitiesForExistingKeys =
		{{1.5,  5.5,  10.5, Double.POSITIVE_INFINITY},  // LinearProbingHashTable
		{1.44, 2.85, 3.52, Double.POSITIVE_INFINITY},   // QuadraticProbingHashTable
		{1.39, 5.56, 3.15, Double.POSITIVE_INFINITY}};  // DoubleHashingHashTable

	static double[][] theoreticalComplexitiesForNonExistingKeys =
		{{2.5,  50.5, 200.5, Double.POSITIVE_INFINITY},  // LinearProbingHashTable
		{2.19, 11.4, 22.05, Double.POSITIVE_INFINITY},   // QuadraticProbingHashTable
		{2,    10,   20,    Double.POSITIVE_INFINITY}};  // DoubleHashingHashTable

	static int keyCount;

	static Integer[] keys;
	static Integer[] shuffledExistingKeys;
	static Integer[] notExistingKeys;

	static IHashTable[] hashTables = new IHashTable[3];
	
	static IHashFunction hashFunction = new DivisionRemainderHashFunction();
	// IHashFunction hashFunction = new MultiplicativeHashFunction();		



	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for (int testrun = 0; testrun < TESTRUNS; testrun++) {
			for (int betaIndex = 0; betaIndex < betas.length; betaIndex++) {
				double beta = betas[betaIndex];

				initializeHashTables(hashFunction);
				
				initializeKeys(beta);

				for (int hashTableIndex = 0; hashTableIndex < hashTables.length; hashTableIndex++) {
					IHashTable hashTable = hashTables[hashTableIndex];

					outputProgressBar(testrun, betaIndex, hashTableIndex);
					System.out.println(hashTable.getClass().getSimpleName());
					System.out.println("Beta: " + beta);

					double[] complexities = runTest(hashTable, theoreticalComplexitiesForExistingKeys[hashTableIndex][betaIndex], theoreticalComplexitiesForNonExistingKeys[hashTableIndex][betaIndex]);
					complexitiesForExistingKeys[hashTableIndex][betaIndex]    += complexities[EXISTING_KEY];
					complexitiesForNonExistingKeys[hashTableIndex][betaIndex] += complexities[NON_EXISTING_KEY];

					System.out.println("----------------------------------------");
				}
				System.out.println("\n========================================");
			}
		}

		outputProgressBar(TESTRUNS - 1, betas.length - 1, hashTables.length);
		System.out.println("\n\n");

		outputTable();

		System.out.println("\n\n");

	}


	private static void outputProgressBar(int testrun, int betaIndex,
			int hashTableIndex) {
		
		    double hashTablePercentage = hashTableIndex                    / (double)hashTables.length;
		    double betaPercentage      = (betaIndex + hashTablePercentage) / (double)betas.length;
		    double percentage          = (testrun   + betaPercentage)      / (double)TESTRUNS;
		    System.out.format("%2.2f", percentage);
		    System.out.println("%");
		
	}


	/**
	 * Erstellt zufällige Schlüssel für die Tests
	 * @param beta der zu ereichende Füllstand
	 */
	private static void initializeKeys(double beta) {

		keyCount             = (int) (beta * STORAGE_SIZE);
		keys                 = Universum.generateKeys(keyCount);
		shuffledExistingKeys = Universum.shuffleKeys(keys);
		notExistingKeys      = Universum.generateNotExistingKeys(keyCount);
		
	}


	/**
	 * Initialisiert die Hashtables
	 * @param hashFunction
	 */
	private static void initializeHashTables(IHashFunction hashFunction) {

        hashTables[LINEAR_PROBING_HASH_TABLE]    = new LinearProbingHashTable(STORAGE_SIZE, hashFunction);
		hashTables[QUADRATIC_PROBING_HASH_TABLE] = new QuadraticProbingHashTable(STORAGE_SIZE, hashFunction);
		hashTables[DOUBLE_HASHING_HASH_TABLE]    = new DoubleHashingHashTable(STORAGE_SIZE, hashFunction);
		
	}


	private static void outputTable() {
		
		outputSettings();

		outputBigLine();
		outputLeftLine();
		outputSeperator();

		for (int betaIndex = 0; betaIndex < betas.length; betaIndex++) {

			double beta = betas[betaIndex];
			outputBeta(beta);
			outputSeperator();

		}

		for (int hashTableIndex = 0; hashTableIndex < hashTables.length; hashTableIndex++) {
			IHashTable hashTable = hashTables[hashTableIndex];

			outputBigLine();
			outputLeftLine();
			outputSeperator();

			for (int betaIndex = 0; betaIndex < betas.length; betaIndex++) {

				outputComplexity(complexitiesForExistingKeys[hashTableIndex][betaIndex]);
				outputTheoreticalComplexity(theoreticalComplexitiesForExistingKeys[hashTableIndex][betaIndex]);
				outputSeperator();

			}

			outputLeftLine(hashTable.getClass().getSimpleName());
			outputSmallLine();
			outputLeftLine();
			outputSeperator();

			for (int betaIndex = 0; betaIndex < betas.length; betaIndex++) {

				outputComplexity(complexitiesForNonExistingKeys[hashTableIndex][betaIndex]);
				outputTheoreticalComplexity(theoreticalComplexitiesForNonExistingKeys[hashTableIndex][betaIndex]);
				outputSeperator();

			}
		}

		outputBigLine();	

	}


	private static void outputSettings() {
		
		System.out.print("   Speicherkapazität: " + STORAGE_SIZE);
		System.out.print("   Testläufe: " + TESTRUNS);
		System.out.print("   Hashfunktion: " + hashFunction.getClass().getSimpleName() + "\n");
		
	}


	private static void outputBeta(double beta) {

		System.out.format("%21s", beta);

	}


	private static void outputTheoreticalComplexity(double theoreticalComplexity) {

		System.out.format("%-11s", " (" + theoreticalComplexity + ")");

	}


	private static void outputComplexity(double complexity) {

		System.out.format("%10.2f", complexity / (double)TESTRUNS);				

	}


	private static void outputSeperator() {

		System.out.print(" | ");

	}


	private static void outputLeftLine() {

		outputLeftLine("");

	}

	private static void outputLeftLine(String text) {

		System.out.format("%-29s", "\n | " + text);

	}


	private static void outputSmallLine() {

		System.out.print(" |-----------------------|-----------------------|-----------------------|-----------------------|");

	}


	private static void outputBigLine() {

		System.out.print("\n |===========================|=======================|=======================|=======================|=======================|");

	}


	private static double[] runTest(IHashTable hashTable, double theoreticalComplexityForExistingKeys, double theoreticalComplexityForNonExistingKeys) {

		// Speichern
		saveEntries(hashTable);

		// Erfolgreiches Lesen
		double complexityForExistingKeys = readWithExistingKeys(hashTable);

		System.out.println("Erfolgreiches Lesen:");
		System.out.format("%2.2f", complexityForExistingKeys);
		System.out.println(" (" + theoreticalComplexityForExistingKeys + ")");

		hashTable.resetReadTries();

		// Erfolgloses Lesen:
		double complexityForNonExistingKeys = readWithNonExistingKeys(hashTable);

		System.out.println("Erfolgloses Lesen:");
		System.out.format("%2.2f", complexityForNonExistingKeys);
		System.out.println(" (" + theoreticalComplexityForNonExistingKeys + ")");

		double[] complexities = {complexityForExistingKeys, complexityForNonExistingKeys};

		return complexities;

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
