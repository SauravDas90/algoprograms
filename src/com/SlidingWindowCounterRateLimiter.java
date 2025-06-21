import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Map;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class SlidingWindowCounterRateLimiter {

    private final long windowSizeMillis; // e.g., 10000 for 10 seconds
    private final int requestLimit;     // e.g., 10 requests
    private final long bucketSizeMillis; // e.g., 1000 for 1 second buckets

    // Stores counts for each bucket. Key: start timestamp of the bucket (millis), Value: count
    // In a distributed system, this would be a distributed cache like Redis
    private final ConcurrentHashMap<Long, Integer> buckets = new ConcurrentHashMap<>();

    // For cleanup of old buckets (optional for simple example, crucial for production)
    private final ScheduledExecutorService cleanupScheduler = Executors.newSingleThreadScheduledExecutor();

    // Formatter for readable output
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public SlidingWindowCounterRateLimiter(long windowSizeMillis, int requestLimit) {
        this.windowSizeMillis = windowSizeMillis;
        this.requestLimit = requestLimit;
        // For simplicity, let bucket size be 1/10th of window size or 1 second, whichever is smaller
        this.bucketSizeMillis = Math.min(1000, windowSizeMillis / 10); 

        // Schedule periodic cleanup of old buckets (e.g., every windowSizeMillis)
        // Adjust initial delay and period to be slightly more than windowSizeMillis to ensure no active buckets are prematurely cleaned
        cleanupScheduler.scheduleAtFixedRate(this::cleanupOldBuckets, windowSizeMillis + bucketSizeMillis, windowSizeMillis / 2, TimeUnit.MILLISECONDS);
    }

    // Method to check and apply rate limit
    public synchronized boolean allowRequest(String userId) { // 'synchronized' for single instance, replaced by distributed lock/atomic ops in real system
        long currentTimeMillis = System.currentTimeMillis();
        
        // Determine the current bucket's start timestamp
        // e.g., if currentTimeMillis = 1718927662500 (for 23:54:22.500) and bucketSizeMillis = 1000
        // currentBucketStartTime = (1718927662500 / 1000) * 1000 = 1718927662000 (for 23:54:22.000)
        long currentBucketStartTime = (currentTimeMillis / bucketSizeMillis) * bucketSizeMillis;

        // Get the count for the current bucket, defaulting to 0 if not present
        buckets.putIfAbsent(currentBucketStartTime, 0);
        
        // Calculate the effective start time of the sliding window
        // e.g., 23:54:22.500 - 10000ms = 23:54:12.500
        long windowStartTime = currentTimeMillis - windowSizeMillis;

        // Calculate the total requests in the sliding window
        int totalRequests = 0;
        
        // Iterate through relevant buckets to sum up counts
        for (Map.Entry<Long, Integer> entry : buckets.entrySet()) {
            Long bucketStartTime = entry.getKey();
            Integer count = entry.getValue();

            // Check if the bucket is entirely within the sliding window
            // Example: bucket at 23:54:13.000 is >= windowStartTime (23:54:12.500)
            if (bucketStartTime >= windowStartTime) {
                totalRequests += count;
            } 
            // Check if the bucket partially overlaps the sliding window from the past
            // Example: bucket at 23:54:12.000. Its end (23:54:13.000) is > windowStartTime (23:54:12.500)
            else if (bucketStartTime + bucketSizeMillis > windowStartTime) {
                // Calculate the overlap duration
                // Example: (23:54:12.000 + 1000) - 23:54:12.500 = 500ms
                long overlapDuration = (bucketStartTime + bucketSizeMillis) - windowStartTime;
                double overlapRatio = (double) overlapDuration / bucketSizeMillis;
                totalRequests += (int) (count * overlapRatio); // Casting to int rounds down
            }
        }
        
        // Check if the current request would exceed the limit
        if (totalRequests < requestLimit) {
            // Allow the request and increment the current bucket's counter
            buckets.compute(currentBucketStartTime, (key, val) -> val + 1);
            System.out.println(String.format("[%s] User %s: ALLOWED. Total in window: %d, Current Bucket: %d", 
                formatTimestamp(currentTimeMillis), userId, totalRequests + 1, buckets.get(currentBucketStartTime)));
            return true;
        } else {
            System.out.println(String.format("[%s] User %s: DENIED. Total in window: %d", 
                formatTimestamp(currentTimeMillis), userId, totalRequests));
            return false;
        }
    }

    // Method to clean up old buckets
    private void cleanupOldBuckets() {
        // Remove buckets that are more than 2x windowSizeMillis older than current time
        // This ensures they are definitely out of any sliding window.
        long cleanupThreshold = System.currentTimeMillis() - (windowSizeMillis + bucketSizeMillis * 2); 
        int initialSize = buckets.size();
        buckets.entrySet().removeIf(entry -> entry.getKey() < cleanupThreshold);
        if (buckets.size() < initialSize) {
            System.out.println(String.format("[%s] Cleaned up old buckets. Remaining buckets: %d", 
                formatTimestamp(System.currentTimeMillis()), buckets.size()));
        }
    }

    // Utility for formatted time output
    private String formatTimestamp(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.of("Asia/Kolkata")).format(FORMATTER);
    }

    // Shut down the cleanup scheduler
    public void shutdown() {
        cleanupScheduler.shutdown();
        try {
            if (!cleanupScheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                cleanupScheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            cleanupScheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("Rate Limiter Shut down.");
    }

    public static void main(String[] args) throws InterruptedException {
        // Limit: 10 requests per 10 seconds
        SlidingWindowCounterRateLimiter limiter = new SlidingWindowCounterRateLimiter(10_000, 10); 

        System.out.println("--- Bursting requests for User A ---");
        for (int i = 0; i < 12; i++) { // 12 requests in quick succession
            limiter.allowRequest("userA");
            Thread.sleep(50); // Simulate rapid requests
        }

        System.out.println("\n--- Waiting 5 seconds (Current time: " + limiter.formatTimestamp(System.currentTimeMillis()) + ") ---");
        Thread.sleep(5000); // Wait for 5 seconds

        System.out.println("\n--- Sending more requests for User A ---");
        for (int i = 0; i < 8; i++) { // Another 8 requests
            limiter.allowRequest("userA");
            Thread.sleep(100);
        }
        
        System.out.println("\n--- Sending requests for User B (should be independent) ---");
        for (int i = 0; i < 5; i++) { 
            limiter.allowRequest("userB"); // Test a different user
            Thread.sleep(50);
        }


        System.out.println("\n--- Waiting for window to pass completely (Current time: " + limiter.formatTimestamp(System.currentTimeMillis()) + ") ---");
        Thread.sleep(15000); // Wait for more than one window to pass for user A

        System.out.println("\n--- Sending requests for User A after window reset ---");
        for (int i = 0; i < 5; i++) {
            limiter.allowRequest("userA");
            Thread.sleep(100);
        }

        limiter.shutdown();
    }
}
