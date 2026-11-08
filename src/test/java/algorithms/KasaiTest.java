package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KasaiTest {

    @Test
    public void testBanana() {
        String text = "BANANA";
        int[] suffixArray = {5, 3, 1, 0, 4, 2};
        int[] expected = {1, 3, 0, 0, 2, 0};

        assertArrayEquals(expected,
                Kasai.buildLCP(text, suffixArray));
    }

    @Test
    public void testSingleCharacter() {
        String text = "A";
        int[] suffixArray = {0};
        int[] expected = {0};

        assertArrayEquals(expected,
                Kasai.buildLCP(text, suffixArray));
    }
}