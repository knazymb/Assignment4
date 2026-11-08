package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EditDistanceTest {

    @Test
    public void testSameStrings() {
        assertEquals(0, EditDistance.findDistance("CAT", "CAT"));
    }

    @Test
    public void testReplaceCharacter() {
        assertEquals(1, EditDistance.findDistance("CAT", "CUT"));
    }

    @Test
    public void testInsertCharacter() {
        assertEquals(1, EditDistance.findDistance("CAT", "CART"));
    }

    @Test
    public void testDeleteCharacter() {
        assertEquals(1, EditDistance.findDistance("CART", "CAT"));
    }

    @Test
    public void testEmptyString() {
        assertEquals(3, EditDistance.findDistance("", "CAT"));
    }
}