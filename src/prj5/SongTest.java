package prj5;

import java.util.Arrays;

import student.TestCase;

/**
 * Tests Song class methods
 * 
 * @author Randy Liang, Connor Smedley, and Garrett Novak
 * @version 2015.11.20
 */
public class SongTest extends TestCase {
    private Song test1;

    /**
     * Sets up variables for testing
     */
    public void setUp() {
        test1 = new Song("Taco", "Rock", "People", 2000);
    }

    /**
     * Tests getTitle() method
     */
    public void testGetTitle() {
        assertEquals(test1.getTitle(), "Taco");
    }

    /**
     * Tests getAuthor() method
     */
    public void testGetAuthor() {
        assertEquals(test1.getAuthor(), "People");
    }

    /**
     * Tests getGenre() method
     */
    public void testGetGenre() {
        assertEquals(test1.getGenre(), "Rock");
    }

    /**
     * Tests getDate() method
     */
    public void testGetDate() {
        assertEquals(test1.getDate(), 2000);
    }

    /**
     * Test equals() method
     */
    public void testEquals() {
        Song test2 = null;
        assertFalse(test1.equals(test2));
        Song test3 = test1;
        assertTrue(test1.equals(test3));
        String test4 = "Something";
        assertFalse(test1.equals(test4));
        Song test5 = new Song("Taco", "Rock", "People", 1000);
        assertFalse(test1.equals(test5));
        Song test6 = new Song("Taco", "Rock", "SomeoneElse", 2000);
        assertFalse(test1.equals(test6));
        Song test7 = new Song("Taco", "Country", "People", 2000);
        assertFalse(test1.equals(test7));
        Song test8 = new Song("Fish", "Rock", "People", 2000);
        assertFalse(test1.equals(test8));
        Song test9 = new Song("Taco", "Rock", "People", 2000);
        assertTrue(test1.equals(test9));
    }

    /**
     * Tests toString() method
     */
    public void testToString() {
        StringBuilder output = new StringBuilder("Song Title: ");
        output.append("Taco" + "\n");
        output.append("Song Artist: " + "People" + "\n");
        output.append("Song Genre: " + "Rock" + "\n");
        output.append("Song Year: " + 2000 + "\n");
        assertTrue(test1.toString().equals(output.toString()));
        for (int i = 0; i < 90; i++) {
            test1.increaseHeard(1);
        }
        test1.increaseLiked(1);
        for (int i = 0; i < 100; i++) {
            test1.increaseTotal1(1);
        }
        test1.increaseTotal2(1);
        test1.increaseHeard(1);
        test1.increaseLiked(1);
        test1.increaseTotal1(1);
        test1.increaseTotal2(1);
        output.append("Heard" + "\n" + "reading" + 0 + " art" + 90
                + " sports" + 0 + " music" + 0 + "\n" + "Likes" + "\n" +
                "reading" + 0 + " art" + 100 + " sports" + 0 
                + " music" + 0 + "\n");
        assertEquals(test1.toString("hobby"), output.toString());
        StringBuilder output2 = new StringBuilder("Song Title: ");
        output2.append("Taco" + "\n");
        output2.append("Song Artist: " + "People" + "\n");
        output2.append("Song Genre: " + "Rock" + "\n");
        output2.append("Song Year: " + 2000 + "\n");
        test1.increaseHeard(5);
        test1.increaseLiked(5);
        test1.increaseTotal1(5);
        test1.increaseTotal2(5);
        output2.append("Heard" + "\n" + "Computer Science" + 0 
                + " Other Engineering" + 100
                + " Math or CMDA" + 0 + " Other" + 0 + "\n" + "Likes" 
                + "\n" +
                "Computer Science" + 0 + " Other Engineering" + 100 
                + " Math or CMDA" + 0 
                + " Other" + 0 + "\n");
        assertTrue(test1.toString("major")
                .equals(output2.toString()));
        StringBuilder output3 = new StringBuilder("Song Title: ");
        output3.append("Taco" + "\n");
        output3.append("Song Artist: " + "People" + "\n");
        output3.append("Song Genre: " + "Rock" + "\n");
        output3.append("Song Year: " + 2000 + "\n");
        test1.increaseHeard(9);
        test1.increaseLiked(9);
        test1.increaseTotal1(9);
        test1.increaseTotal2(9);
        output3.append("Heard" + "\n" + "Northeast US" + 0 
                + " Southeast US" + 100
                + " Rest of US" + 0 + " Outside US" + 0 + "\n" 
                + "Likes" + "\n" +
                "Northeast US" + 0 + " Southeast US" + 100 
                + " Rest of US" + 0 
                + " Outside US" + 0 + "\n");
        assertTrue(test1.toString("region")
                .equals(output3.toString()));
    }

    /**
     * Tests getLiked() method
     */
    public void testGetLiked() {
        int[] testArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertTrue(Arrays.equals(testArray, test1.getLiked()));
        test1.increaseLiked(3);
        assertFalse(Arrays.equals(testArray, test1.getLiked()));
        testArray[3] = 1;
        assertTrue(Arrays.equals(testArray, test1.getLiked()));
    }

    /**
     * Tests getHeard() method
     */
    public void testGetHeard() {
        int[] testArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertTrue(Arrays.equals(testArray, test1.getHeard()));
        test1.increaseHeard(3);
        assertFalse(Arrays.equals(testArray, test1.getHeard()));
        testArray[3] = 1;
        assertTrue(Arrays.equals(testArray, test1.getHeard()));
    }

    /**
     * Tests getTotal() method
     */
    public void testGetTotal() {
        int[] testArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertTrue(Arrays.equals(testArray, test1.getTotal1()));
        test1.increaseTotal1(3);
        assertFalse(Arrays.equals(testArray, test1.getTotal1()));
        testArray[3] = 1;
        assertTrue(Arrays.equals(testArray, test1.getTotal1()));
        int[] testArray1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertTrue(Arrays.equals(testArray1, test1.getTotal2()));
        test1.increaseTotal2(3);
        assertFalse(Arrays.equals(testArray1, test1.getTotal2()));
        testArray1[3] = 1;
        assertTrue(Arrays.equals(testArray1, test1.getTotal2()));
    }
}