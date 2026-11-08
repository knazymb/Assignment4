package algorithms;

public class Main {

    public static void main(String[] args) {

        String str1 = "ABCDGH";
        String str2 = "AEDFHR";

        System.out.println("=== LCS ===");
        System.out.println("Result: " + LCS.findLCS(str1, str2));


        String word1 = "CAT";
        String word2 = "CUT";

        System.out.println("\n=== Edit Distance ===");
        System.out.println("Result: " + EditDistance.findDistance(word1, word2));


        String text = "ABABCABABC";
        String pattern = "ABC";

        System.out.println("\n=== Naive String Matching ===");
        System.out.println("Text: " + text);
        System.out.println("Pattern: " + pattern);
        System.out.println("Occurrences: " + NaiveSearch.search(text, pattern));

        String text2 = "ABABCABABC";
        String pattern2 = "ABC";

        System.out.println("\n=== KMP ===");
        System.out.println("Text: " + text2);
        System.out.println("Pattern: " + pattern2);
        System.out.println("LPS: " + java.util.Arrays.toString(KMP.buildLPS(pattern2)));
        System.out.println("Occurrences: " + KMP.search(text2, pattern2));

        String text3 = "ABABCABABC";
        String pattern3 = "ABC";

        System.out.println("\n=== Rabin-Karp ===");
        System.out.println("Text: " + text3);
        System.out.println("Pattern: " + pattern3);
        System.out.println("Occurrences: " + RabinKarp.search(text3, pattern3));

        String text4 = "BANANA";

        int[] suffixArray = {5, 3, 1, 0, 4, 2};

        int[] lcp = Kasai.buildLCP(text4, suffixArray);

        System.out.println("\n=== Kasai's Algorithm ===");
        System.out.println("Text: " + text4);
        System.out.println("Suffix Array: " +
                java.util.Arrays.toString(suffixArray));
        System.out.println("LCP Array: " +
                java.util.Arrays.toString(lcp));
    }
}