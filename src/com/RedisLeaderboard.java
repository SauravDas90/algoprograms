import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RedisLeaderboard {

    private static final String LEADERBOARD_KEY = "game:leaderboard";
    private final Jedis jedis;

    public RedisLeaderboard(String redisHost, int redisPort) {
        this.jedis = new Jedis(redisHost, redisPort);
    }

    /**
     * Adds or updates a user's score on the leaderboard.
     * If the user already exists, their score will be updated.
     *
     * @param userId The unique ID of the user.
     * @param score The score of the user.
     */
    public void addOrUpdateScore(String userId, double score) {
        jedis.zadd(LEADERBOARD_KEY, score, userId);
        System.out.println("Score updated for " + userId + ": " + score);
    }

    /**
     * Retrieves the top N users from the leaderboard.
     *
     * @param limit The number of top users to retrieve.
     * @return A list of UserScore objects, representing the top users and their scores.
     */
    public List<UserScore> getTopNPlayers(int limit) {
        // ZREVRANGE retrieves elements in descending order of score (highest score first)
        // WITHSCORES returns both the member and its score.
        Set<Tuple> topPlayers = jedis.zrevrangeWithScores(LEADERBOARD_KEY, 0, limit - 1);

        return topPlayers.stream()
                .map(tuple -> new UserScore(tuple.getElement(), tuple.getScore()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the rank of a specific user on the leaderboard.
     * Ranks are 0-based, where 0 is the highest rank.
     *
     * @param userId The ID of the user.
     * @return The 0-based rank of the user, or -1 if the user is not found.
     */
    public Long getPlayerRank(String userId) {
        // ZREVRANK returns the 0-based rank of the member in descending order of score.
        return jedis.zrevrank(LEADERBOARD_KEY, userId);
    }

    /**
     * Retrieves the score of a specific user.
     *
     * @param userId The ID of the user.
     * @return The score of the user, or null if the user is not found.
     */
    public Double getPlayerScore(String userId) {
        return jedis.zscore(LEADERBOARD_KEY, userId);
    }

    /**
     * Removes a user from the leaderboard.
     *
     * @param userId The ID of the user to remove.
     * @return True if the user was removed, false otherwise.
     */
    public boolean removePlayer(String userId) {
        Long removedCount = jedis.zrem(LEADERBOARD_KEY, userId);
        return removedCount > 0;
    }

    public void close() {
        if (jedis != null) {
            jedis.close();
        }
    }

    // Helper class to encapsulate user ID and score
    public static class UserScore {
        private String userId;
        private double score;

        public UserScore(String userId, double score) {
            this.userId = userId;
            this.score = score;
        }

        public String getUserId() {
            return userId;
        }

        public double getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "User: " + userId + ", Score: " + score;
        }
    }

    public static void main(String[] args) {
        // Assuming a Redis instance is running on localhost:6379
        RedisLeaderboard leaderboard = new RedisLeaderboard("localhost", 6379);

        // Add/Update scores
        System.out.println("--- Adding/Updating Scores ---");
        leaderboard.addOrUpdateScore("player1", 1000);
        leaderboard.addOrUpdateScore("player2", 1500);
        leaderboard.addOrUpdateScore("player3", 800);
        leaderboard.addOrUpdateScore("player4", 2000);
        leaderboard.addOrUpdateScore("player5", 1200);
        leaderboard.addOrUpdateScore("player1", 1100); // Update player1's score

        // Get Top 3 players
        System.out.println("\n--- Top 3 Players ---");
        List<UserScore> topPlayers = leaderboard.getTopNPlayers(3);
        for (int i = 0; i < topPlayers.size(); i++) {
            UserScore user = topPlayers.get(i);
            System.out.println((i + 1) + ". " + user);
        }

        // Get rank of a specific player
        System.out.println("\n--- Player Ranks ---");
        System.out.println("Rank of player4: " + leaderboard.getPlayerRank("player4")); // Should be 0
        System.out.println("Rank of player1: " + leaderboard.getPlayerRank("player1")); // Should be 2
        System.out.println("Rank of playerX (non-existent): " + leaderboard.getPlayerRank("playerX")); // Should be null

        // Get score of a specific player
        System.out.println("\n--- Player Scores ---");
        System.out.println("Score of player2: " + leaderboard.getPlayerScore("player2"));
        System.out.println("Score of player3: " + leaderboard.getPlayerScore("player3"));

        // Remove a player
        System.out.println("\n--- Removing Player ---");
        boolean removed = leaderboard.removePlayer("player3");
        if (removed) {
            System.out.println("player3 removed from leaderboard.");
        } else {
            System.out.println("player3 not found or not removed.");
        }

        // Verify removal and new top players
        System.out.println("\n--- Top 3 Players After Removal ---");
        topPlayers = leaderboard.getTopNPlayers(3);
        for (int i = 0; i < topPlayers.size(); i++) {
            UserScore user = topPlayers.get(i);
            System.out.println((i + 1) + ". " + user);
        }


        leaderboard.close();
    }
}




Explanation of the Java Code:

RedisLeaderboard Class:

Holds a Jedis instance to connect to Redis.
LEADERBOARD_KEY: A constant string for the Redis sorted set key.
addOrUpdateScore(String userId, double score):

Uses jedis.zadd(LEADERBOARD_KEY, score, userId).
This is the core of updating the leaderboard. If userId doesn't exist in LEADERBOARD_KEY, it's added with the given score. If userId already exists, its score is updated to the new value. This operation is atomic in Redis.
getTopNPlayers(int limit):

Uses jedis.zrevrangeWithScores(LEADERBOARD_KEY, 0, limit - 1).
ZREVRANGE retrieves elements from a sorted set in reverse order (from highest score to lowest).
0 and limit - 1 define the 0-based start and end indices of the range.
WITHSCORES ensures that both the member (userId) and its score are returned.
The Set<Tuple> is then mapped to a List<UserScore> for easier consumption.
getPlayerRank(String userId):

Uses jedis.zrevrank(LEADERBOARD_KEY, userId).
ZREVRANK returns the 0-based rank of userId when sorted in descending order of score. If the user isn't found, it returns null.
getPlayerScore(String userId):

Uses jedis.zscore(LEADERBOARD_KEY, userId).
Returns the score associated with userId, or null if the user is not in the leaderboard.
removePlayer(String userId):

Uses jedis.zrem(LEADERBOARD_KEY, userId).
Removes userId from the sorted set. Returns the number of elements removed (1 if successful, 0 if not found).
UserScore Class:

A simple POJO to hold the userId and score for clarity when returning leaderboard data.
To run this example:

Have Redis Running: Ensure you have a Redis server running, typically on localhost:6379. You can download and install Redis from https://redis.io/download.

Add Jedis Dependency: If you're using Maven, add the following to your pom.xml:
