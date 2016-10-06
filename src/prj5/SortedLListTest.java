package prj5;

import student.TestCase;

/**
 * Tests SortedLList class methods
 * 
 * @author Randy Liang, Connor Smedley, and Garrett Novak
 * @version 2015.11.20
 */
public class SortedLListTest extends TestCase {

    private LList<Song> list;
    private SortedLList sorted;

    /**
     * Sets up variables for testing
     */
    public void setUp() {
        list = new LList<Song>();
        Song song1;
        song1 = new Song("Taco", "Rock", "People", 1000);
        list.add(song1);
        Song song2;
        song2 = new Song("Burrito", "Hip Hop", "SomeoneElse", 2000);
        list.add(song2);
        Song song3;
        song3 = new Song("Enchilada", "Country", "Band", 3000);
        list.add(song3);
        Song songSame;
        songSame = song1;
        list.add(songSame);
        sorted = new SortedLList(list);
    }
    
    /**
     * Tests sortTitle() method
     */
    public void testSortTitle() {
        sorted.sortTitle();
        assertEquals(15, CompareSongs.compareTitle(sorted.getSongs()
                .getEntry(2), sorted.getSongs().getEntry(1)));
        assertEquals(0, CompareSongs.compareTitle(sorted.getSongs()
                .getEntry(2), sorted.getSongs().getEntry(3)));
        assertEquals(-15, CompareSongs.compareTitle(sorted.getSongs()
                .getEntry(1), sorted.getSongs().getEntry(2)));
    }

    /**
     * Tests sortAuthor() method
     */
    public void testSortAuthor() {
        sorted.sortAuthor();
        assertEquals(0, CompareSongs.compareAuthor(sorted.getSongs()
                .getEntry(2), sorted.getSongs().getEntry(1)));
        assertEquals(-3, CompareSongs.compareAuthor(sorted.getSongs()
                .getEntry(2), sorted.getSongs().getEntry(3)));
        assertEquals(14, CompareSongs.compareAuthor(sorted.getSongs()
                .getEntry(1), sorted.getSongs().getEntry(0)));
    }

    /**
     * Tests sortRelease() method
     */
    public void testSortRelease() {
        sorted.sortRelease();
        assertEquals(1, CompareSongs.compareDate(sorted.getSongs()
                .getEntry(2), sorted.getSongs().getEntry(1)));
        assertEquals(-1, CompareSongs.compareDate(sorted.getSongs()
                .getEntry(2), sorted.getSongs().getEntry(3)));
        assertEquals(0, CompareSongs.compareDate(sorted.getSongs()
                .getEntry(1), sorted.getSongs().getEntry(0)));
    }

    /**
     * Tests sortGenre() method
     */
    public void testSortGenre() {
        sorted.sortGenre();
        assertEquals(10, CompareSongs.compareGenre(sorted.getSongs()
                .getEntry(2), sorted.getSongs().getEntry(1)));
        assertEquals(0, CompareSongs.compareGenre(sorted.getSongs()
                .getEntry(2), sorted.getSongs().getEntry(3)));
        assertEquals(-5, CompareSongs.compareGenre(sorted.getSongs()
                .getEntry(0), sorted.getSongs().getEntry(1)));
    }

    /**
     * Tests getSongs() method
     */
    public void testGetSongs() {
        assertEquals(list, sorted.getSongs());
    }
}