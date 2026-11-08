package algorithms;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StringMatchingTest {

    private void checkAllAlgorithms(String text, String pattern,
                                    List<Integer> expected) {

        assertEquals(expected, NaiveSearch.search(text, pattern));
        assertEquals(expected, KMP.search(text, pattern));
        assertEquals(expected, RabinKarp.search(text, pattern));
    }

    @Test
    public void testPatternAtBeginning() {
        checkAllAlgorithms("ABCXYZ", "ABC",
                Arrays.asList(0));
    }

    @Test
    public void testPatternInMiddle() {
        checkAllAlgorithms("XXABCYY", "ABC",
                Arrays.asList(2));
    }

    @Test
    public void testPatternAtEnd() {
        checkAllAlgorithms("XYZABC", "ABC",
                Arrays.asList(3));
    }

    @Test
    public void testMultipleOccurrences() {
        checkAllAlgorithms("ABCABCABC", "ABC",
                Arrays.asList(0, 3, 6));
    }

    @Test
    public void testPatternNotFound() {
        checkAllAlgorithms("ABCDEFG", "XYZ",
                Arrays.asList());
    }

    @Test
    public void testPatternLongerThanText() {
        checkAllAlgorithms("AB", "ABCDE",
                Arrays.asList());
    }

    @Test
    public void testEmptyPattern() {
        checkAllAlgorithms("ABC", "",
                Arrays.asList(0, 1, 2, 3));
    }
}