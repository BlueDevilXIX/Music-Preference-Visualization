package prj5;

import student.TestCase;

/**
 * Tests CompareSongs class methods
 * @author Randy Liang, Connor Smedley, and Garrett Novak
 * @version 2015.11.20
 *
 */
public class CompareSongsTest extends TestCase {

    private Song song1;
    private Song song2;
    private Song song3;
    private Song song4;
    private Song song5;
    private Song songSame;
    private Song songNull;

    /**
     * Sets up any needed variables for test methods
     */
    public void setUp() {
        song1 = new Song("Taco", "Rock", "People", 1000);
        song2 = new Song("Taco", "Rock", "SomeoneElse", 2000);
        song3 = new Song("Taco", "Country", "People", 2000);
        song4 = new Song("Fish", "Rock", "People", 2000);
        song5 = new Song("Taco", "Rock", "People", 2000);
        songSame = song1;
        songNull = null;
        @SuppressWarnings("unused")
        CompareSongs comp = new CompareSongs();
    }

    /**
     * Test compareTitle() method
     */
    public void testCompareTitle() {
        Exception e = null;
        try {
            CompareSongs.compareTitle(song1, songNull);
        } 
        catch (Exception exception) {
            e = exception;
        }
        e = null;
        try {
            CompareSongs.compareTitle(songNull, song1);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
        assertEquals(0, CompareSongs.compareTitle(song1, songSame));
        assertEquals(14, CompareSongs.compareTitle(song1, song4));
        assertEquals(-14, CompareSongs.compareTitle(song4, song1));
    }

    /**
     * Tests compareAuthor() method
     */
    public void testCompareAuthor() {
        Exception e = null;
        try {
            CompareSongs.compareAuthor(song1, songNull);
        } 
        catch (Exception exception) {
            e = exception;
        }
        e = null;
        try {
            CompareSongs.compareAuthor(songNull, song1);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
        assertEquals(0, CompareSongs.compareAuthor(song1, songSame));
        assertEquals(3, CompareSongs.compareAuthor(song2, song1));
        assertEquals(-3, CompareSongs.compareAuthor(song1, song2));
    }

    /**
     * Tests compareGenre() method
     */
    public void testCompareGenre() {
        Exception e = null;
        try {
            CompareSongs.compareGenre(song1, songNull);
        } 
        catch (Exception exception) {
            e = exception;
        }
        e = null;
        try {
            CompareSongs.compareGenre(songNull, song1);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
        assertEquals(0, CompareSongs.compareGenre(song1, songSame));
        assertEquals(15, CompareSongs.compareGenre(song1, song3));
        assertEquals(-15, CompareSongs.compareGenre(song3, song1));
    }

    /**
     * Tests compareDate() method
     */
    public void testCompareDate() {
        Exception e = null;
        try {
            CompareSongs.compareDate(song1, songNull);
        } 
        catch (Exception exception) {
            e = exception;
        }
        e = null;
        try {
            CompareSongs.compareDate(songNull, song1);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
        assertEquals(0, CompareSongs.compareDate(song1, songSame));
        assertEquals(1, CompareSongs.compareDate(song5, song1));
        assertEquals(-1, CompareSongs.compareDate(song1, song5));
    }
}