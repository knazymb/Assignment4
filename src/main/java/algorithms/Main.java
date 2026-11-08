package algorithms;

public class Main {

    public static void main(String[] args) {

        // LCS test
        String str1 = "ABCDGH";
        String str2 = "AEDFHR";

        int lcsResult = LCS.findLCS(str1, str2);

        System.out.println("=== LCS ===");
        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);
        System.out.println("LCS length: " + lcsResult);


        // Edit Distance test
        String word1 = "CAT";
        String word2 = "CUT";

        int distance = EditDistance.findDistance(word1, word2);

        System.out.println("\n=== Edit Distance ===");
        System.out.println("String 1: " + word1);
        System.out.println("String 2: " + word2);
        System.out.println("Minimum operations: " + distance);
    }
}