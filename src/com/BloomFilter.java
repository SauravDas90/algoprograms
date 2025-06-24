import java.util.BitSet;
import java.util.zip.CRC32;

public class BloomFilter {
    private BitSet bitset;
    private int bitSize;
    private int[] seeds;

    public BloomFilter(int bitSize, int[] seeds) {
        this.bitSize = bitSize;
        this.bitset = new BitSet(bitSize);
        this.seeds = seeds;
    }

    // Simple hash function generator using CRC32 + seed
    private int hash(String value, int seed) {
        CRC32 crc = new CRC32();
        crc.update((value + seed).getBytes());
        return (int)(crc.getValue() % bitSize);
    }

    public void add(String value) {
        for (int seed : seeds) {
            int index = hash(value, seed);
            bitset.set(index);
            System.out.println("Setting bit at index: " + index);
        }
    }

    public boolean mightContain(String value) {
        for (int seed : seeds) {
            int index = hash(value, seed);
            if (!bitset.get(index)) {
                return false;  // Definitely not in set
            }
        }
        return true; // Possibly in set
    }

    public void printBitSet() {
        System.out.print("BitSet: ");
        for (int i = 0; i < bitSize; i++) {
            System.out.print(bitset.get(i) ? "1" : "0");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BloomFilter filter = new BloomFilter(16, new int[]{3, 7, 11});

        filter.add("apple");
        filter.printBitSet(); // See set bits

        System.out.println("Check 'apple': " + filter.mightContain("apple")); // true
        System.out.println("Check 'banana': " + filter.mightContain("banana")); // possibly false
    }
}
