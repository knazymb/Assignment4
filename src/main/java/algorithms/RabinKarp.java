package algorithms;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {

    private static final int BASE = 256;
    private static final int MOD = 101;

    public static List<Integer> search(String text, String pattern) {

        List<Integer> result = new ArrayList<>();

        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            for (int i = 0; i <= n; i++) {
                result.add(i);
            }
            return result;
        }

        if (m > n) {
            return result;
        }

        int patternHash = 0;
        int textHash = 0;
        int highestPower = 1;

        for (int i = 0; i < m - 1; i++) {
            highestPower = (highestPower * BASE) % MOD;
        }

        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * BASE + pattern.charAt(i)) % MOD;
            textHash = (textHash * BASE + text.charAt(i)) % MOD;
        }

        for (int i = 0; i <= n - m; i++) {

            if (patternHash == textHash) {

                boolean match = true;

                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    result.add(i);
                }
            }

            if (i < n - m) {

                textHash = (BASE * (textHash
                        - text.charAt(i) * highestPower)
                        + text.charAt(i + m)) % MOD;

                if (textHash < 0) {
                    textHash += MOD;
                }
            }
        }

        return result;
    }
}