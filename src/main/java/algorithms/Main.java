package algorithms;

public class Main {

    public static void main(String[] args) {

        String str1 = "ABCDGH";
        String str2 = "AEDFHR";

        int result = LCS.findLCS(str1, str2);

        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);
        System.out.println("LCS length: " + result);
    }
}