import java.util.Random;

public class CountMinSketch {
    private int[][] table;
    private int width;
    private int depth;
    private int[] hashSeeds;

    public CountMinSketch(int width, int depth) {
        this.width = width;
        this.depth = depth;
        this.table = new int[depth][width];
        this.hashSeeds = new int[depth];
        Random rand = new Random();

        // Initialize hash seeds for independent hash functions
        for (int i = 0; i < depth; i++) {
            hashSeeds[i] = rand.nextInt(Integer.MAX_VALUE);
        }
    }

    // Basic hash function using seed variation
    private int hash(String key, int seed) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash = 31 * hash + c;
        }
        hash ^= seed;
        return Math.abs(hash % width);
    }

    // Increment count for an item
    public void add(String item) {
        for (int i = 0; i < depth; i++) {
            int index = hash(item, hashSeeds[i]);
            table[i][index]++;
        }
    }

    // Estimate frequency of an item
    public int estimate(String item) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < depth; i++) {
            int index = hash(item, hashSeeds[i]);
            min = Math.min(min, table[i][index]);
        }
        return min;
    }

    // Print internal table (for debugging)
    public void printTable() {
        for (int i = 0; i < depth; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 0; j < width; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Main function for testing
    public static void main(String[] args) {
        CountMinSketch cms = new CountMinSketch(10, 4);

        cms.add("apple");
        cms.add("banana");
        cms.add("apple");

        cms.printTable();

        System.out.println("\nEstimate for 'apple': " + cms.estimate("apple"));   // Should be ~2
        System.out.println("Estimate for 'banana': " + cms.estimate("banana")); // Should be ~1
        System.out.println("Estimate for 'cherry': " + cms.estimate("cherry")); // Likely 0
    }
}
