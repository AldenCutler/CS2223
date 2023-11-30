import java.util.*;

public class Hashing {


    // hash function: h = 0; for i=0 to s-1 do h = (h*C + x[i]) mod m
    // h is the computed hash value
    // s is the length of the word being hashed
    // x[i] is the numerican value of the i-th character of the word
    // C is a constant chosen by the programmer
    // m is the size of the hash table

    static final int C = 123;
    static int m;

    /**
     * Gets the number of empty slots in the table as well as the load factor
     * @param hashTable the hash table to be analyzed
     */
    static void getNumEmpty(Hashtable<Integer, String> hashTable) {
        int numEmpty = 0;
        for (int i = 0; i < m; i++) {
            if (!hashTable.containsKey(i)) {
                numEmpty++;
            }
        }
        System.out.println("\nNumber of empty slots: " + numEmpty);
        System.out.println("Load factor: " + (double)(m - numEmpty) / m);
    }

    /**
     * Gets the length of the longest chain of empty slots in the table as well as its location
     * @param hashTable the hash table to be analyzed
     */
    static void getLongestEmptyChain(Hashtable<Integer, String> hashTable) {
        int longestChain = 0;
        int longestChainStart = 0;
        int currentChain = 0;
        int currentChainStart = 0;
        for (int i = 0; i < m; i++) {
            if (!hashTable.containsKey(i)) {
                if (currentChain == 0) {
                    currentChainStart = i;
                }
                currentChain++;
            } else {
                if (currentChain > longestChain) {
                    longestChain = currentChain;
                    longestChainStart = currentChainStart;
                }
                currentChain = 0;
            }
        }
        System.out.println("\nLongest chain of empty slots: " + longestChain);
        System.out.println("Longest chain of empty slots starts at: " + longestChainStart);
    }

    /**
     * Gets the hash value that results from the largest number of distinct words, as well as how many words hash to that value
     * @param hashTable the hash table to be analyzed
     */
    static void getLargestDistinct(Hashtable<Integer, String> hashTable) {
        Hashtable<Integer, Integer> distinctHashes = new Hashtable<Integer, Integer>();
        for (int i = 0; i < m; i++) {
            if (hashTable.containsKey(i)) {
                int hashValue = hash(hashTable.get(i));
                if (distinctHashes.containsKey(hashValue)) {
                    distinctHashes.put(hashValue, distinctHashes.get(hashValue) + 1);
                } else {
                    distinctHashes.put(hashValue, 1);
                }
            }
        }
        int largestDistinct = 0;
        int largestDistinctCount = 0;
        for (int i = 0; i < m; i++) {
            if (distinctHashes.containsKey(i)) {
                if (distinctHashes.get(i) > largestDistinctCount) {
                    largestDistinct = i;
                    largestDistinctCount = distinctHashes.get(i);
                }
            }
        }
        System.out.println("\nHash value that results from the largest number of distinct words: " + largestDistinct);
        System.out.println("Number of words that hash to that value: " + largestDistinctCount);
    }

    /**
     * Gets the hash value that is farthest from its actual value, as well as how far it is from its actual value
     * @param hashTable the hash table to be analyzed
     */
    static void getFarthestFromActual(Hashtable<Integer, String> hashTable) {
        int farthestFromActual = 0;
        int farthestFromActualDistance = 0;
        for (int i = 0; i < m; i++) {
            if (hashTable.containsKey(i)) {
                int hashValue = hash(hashTable.get(i));
                int distance = Math.abs(hashValue - i);
                if (distance > farthestFromActualDistance) {
                    farthestFromActual = hashValue;
                    farthestFromActualDistance = distance;
                }
            }
        }
        System.out.println("\nHash value that is farthest from its actual value: " + farthestFromActual);
        System.out.println("How far it is from its actual value: " + farthestFromActualDistance);
    }

    /**
     * Gets the length of the longest chain of non-empty slots in the table as well as its location (note: this could wrap from the end of the table to the beginning)
     * @param hashTable the hash table to be analyzed
     */
    static void getLongestChain(Hashtable<Integer, String> hashTable) {
        int longestChain = 0;
        int longestChainStart = 0;
        int currentChain = 0;
        int currentChainStart = 0;
        for (int i = 0; i < m; i++) {
            if (hashTable.containsKey(i)) {
                if (currentChain == 0) {
                    currentChainStart = i;
                }
                currentChain++;
            } else {
                if (currentChain > longestChain) {
                    longestChain = currentChain;
                    longestChainStart = currentChainStart;
                }
                currentChain = 0;
            }
        }
        System.out.println("\nLongest chain of non-empty slots: " + longestChain);
        System.out.println("Longest chain of non-empty slots starts at: " + longestChainStart);
    }

    /**
     * Hashes a word using the hash function: for i=0 to s-1 do h = (h*C + x[i]) mod m
     * @param word the word to be hashed
     * @return the hash value of the word
     */
    public static int hash(String word) {
        int h = 0;
        for (int i = 0; i < word.length(); i++) {
            h = (h * C + (int)word.charAt(i)) % m;
        }
        return h;
    }

    public static void main(String[] args) {

        try {

            // Question 4:
            // Scanner scan = new Scanner(System.in);
            // System.out.println("Enter the path to the file to be read: ");
            // String fileName = scan.nextLine();
            // Scanner file = new Scanner(new java.io.File(fileName));
            
            // System.out.println("Enter the size of the hash table: ");
            // m = scan.nextInt();
            // scan.close();

            // TODO: comment out these two lines before submitting
            m = 997;
            Scanner file = new Scanner(new java.io.File("C:/Users/Alden/Downloads/DeclarationOfIndependence.txt"));

            // build a hash table of size m from the hash values computed.
            Hashtable<Integer, String> hashTable = new Hashtable<Integer, String>();
            
            // hash the words using the hash function above, then load them
            // into the table in the order they occur in the file, discarding
            // duplicates.
            while (file.hasNext()) {
                String word = file.next();
                int hashValue = hash(word);
                if (!hashTable.contains(word)) {
                    hashTable.put(hashValue, word);
                }
            }
            file.close();

            // display the table, starting with table entry 0, in lines of the form
            // Hash Address, Hashed Word, Hash Value of Word
            for (int i = 0; i < m; i++) {
                if (hashTable.containsKey(i)) {
                    System.out.printf("%-6.6s %-20.20s %-20.20s\n", i, hashTable.get(i), hash(hashTable.get(i)));
                }
            }

            getNumEmpty(hashTable);     // get the number of empty slots in the table as well as the load factor
            getLongestEmptyChain(hashTable);    // get the length of the longest chain of empty slots in the table as well as its location
            getLongestChain(hashTable);     // get the length of the longest chain of non-empty slots in the table as well as its location (note: this could wrap from the end of the table to the beginning)
            getLargestDistinct(hashTable);  // get the hash value that results from the largest number of distinct words, as well as how many words hash to that value
            getFarthestFromActual(hashTable);   // get the hash value that is farthest from its actual value, as well as how far it is from its actual value
            
        } catch (java.io.FileNotFoundException e) { System.out.println("File not found");}

    }
}