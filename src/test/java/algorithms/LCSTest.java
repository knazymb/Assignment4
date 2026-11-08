package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LCSTest {

    @Test
    public void testSameStrings() {
        assertEquals(3, LCS.findLCS("ABC", "ABC"));
    }

    @Test
    public void testNoCommonCharacters() {
        assertEquals(0, LCS.findLCS("ABC", "XYZ"));
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, LCS.findLCS("", "ABC"));
    }
}