package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;

import student.TestCase;

/**
 * @author Randy Liang, Connor Smedley, and Garrett Novak
 * @version 2015.11.26
 *
 */
public class LListTest extends TestCase {

    private LList<String> list;


    /**
     * Sets up any needed variables for test methods
     */
    public void setUp() {
        list = new LList<String>();
    }

    /**
     * Tests add() method
     */
    public void testAdd() {
        assertEquals(0, list.getLength());
        list.add("A");
        assertEquals(1, list.getLength());
        list.add("B");
        assertEquals(2, list.getLength());
        assertEquals("B", list.getEntry(0));
    }

    /**
     * Tests addIndex() method
     */
    public void testAddIndex() {
        list.add("B");
        list.add(0, "A");
        assertEquals("A", list.getEntry(0));
        assertEquals(2, list.getLength());
        list.add(2, "D");
        assertEquals("D", list.getEntry(2));
        list.add(2, "C");
        assertEquals("C", list.getEntry(2));
    }

    /**
     * Tests add() for null exception case
     */
    public void testAddNullException() {
        Exception e = null;
        try {
            list.add(null);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }

    /**
     * Test addIndex() with null exception case
     */
    public void testAddIndexNullException() {
        Exception e = null;
        try {
            list.add(0, null);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }

    /**
     * Tests add() with exception 
     */
    public void testAddException() {
        list.add("A");
        Exception e = null;
        try {
            list.add(3, "B");
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        e = null;
        try {
            list.add(-1, "B");
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }

    /**
     * Tests clear() method
     */
    public void testClear() {
        list.add("A");
        list.clear();
        assertEquals(0, list.getLength());
        assertFalse(list.contains("A"));
    }

    /**
     * Tests contains() method
     */
    public void testContains() {
        assertFalse( list.contains("A"));
        list.add("A");
        assertTrue(list.contains("A"));
        assertFalse(list.contains("B"));
        list.add("B");
        assertTrue(list.contains("B"));
        assertTrue(list.contains("A"));
        String nullString = null;
        assertFalse(list.contains(nullString));
    }

    /**
     * Tests getEntry() method
     */
    public void testGetEntry() {
        list.add("A");
        assertEquals("A", list.getEntry(0));
        assertNull(list.getEntry(2));
        assertNull(list.getEntry(-2));
    }

    /**
     * Tests getLength() method
     */
    public void testGetLength() {
        assertEquals(0, list.getLength());
        String obj = "whwh";
        list.add(obj);
        assertEquals(1, list.getLength());
        list.remove(obj);
        assertEquals(0, list.getLength());
    }   

    /**
     * Tests isEmpty() method
     */
    public void testIsEmpty() {
        assertTrue( list.isEmpty());
        list.add("A");
        assertFalse(list.isEmpty());
    }

    /**
     * Tests removeIndex()
     */
    public void testRemoveIndex() {
        list.add("A");
        list.add("B");
        assertEquals(list.remove(1), "A");
        assertEquals(1, list.getLength());
        list.add("B");
        assertEquals(list.remove(0), "B");
        assertEquals(1, list.getLength());
    }

    /**
     * Tests removeObj()
     */
    public void testRemoveObj() {
        String what = null;
        assertEquals(list.remove(what), null);
        list.add("A");
        list.add("B");
        assertEquals(list.remove("A"), "A");
        assertEquals("B", list.getEntry(0));
        assertEquals(1, list.getLength());
        list.add("C");
        assertEquals(list.remove("C"), "C");
        assertEquals("B", list.getEntry(0));
        list.add("C");
        list.add("A");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("G");
        list.add("H");
        assertEquals(list.remove("E"), "E");
        assertEquals(list.remove("H"), "H");
        assertNull(list.remove("P"));
    }

    /**
     * Tests remove() with exception
     */
    public void testRemoveException() {
        list.add("A");
        Exception e = null;
        try {
            list.remove(2);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        e = null;
        try {
            list.remove(-1);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }

    /**
     * Tests remove() from empty
     */
    public void testRemoveFromEmpty() {
        list.add("dance");
        list.add(0, "safety");
        list.clear();
        Exception exception;
        exception = null;
        try {
            list.remove(0);
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(  exception instanceof IndexOutOfBoundsException);

        LList<String> emptyList = new LList<String>();
        exception = null;
        try {
            emptyList.remove(0);
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue( exception instanceof IndexOutOfBoundsException);
    }

    /**
     * Tests replace() method
     */
    public void testReplace() {
        list.add("A");
        assertEquals("A", list.getEntry(0));
        list.replace(0, "B");
        assertEquals("B", list.getEntry(0));    
        Exception exception;
        exception = null;
        try {
            list.replace(50, "pi");
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(  exception instanceof IndexOutOfBoundsException);
        try {
            list.replace(-5, "pi");
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(  exception instanceof IndexOutOfBoundsException);
    }

    /**
     * Tests toArray() method
     */
    public void testToArray() {
        list.add("A");
        list.add("B");
        list.add("C");
        Object[] listContents = new Object[list.toArray().length];
        listContents = list.toArray();
        assertEquals(3, listContents.length);
        assertEquals("C", listContents[0]);
        assertEquals("B", listContents[1]);
        assertEquals("A", listContents[2]);
    }
    
    /**
     * Tests equals() method
     */
    public void testEquals() {
        assertTrue(list.equals(list));
        LList<String> listN = null;
        assertFalse(list.equals(listN));
        Object test2 = new Object();
        assertFalse(list.equals(test2));
        LList<String> list2 = new LList<String>();
        list.add("A");
        assertFalse(list.equals(list2));    
    }

    /**
     * Tests toString() method
     */
    public void testToString() {
        assertEquals("[]", list.toString());
        list.add("A");
        assertEquals("[A]", list.toString());
        list.add("B");
        assertEquals("[BA]", list.toString());
    }
    /**
     * Tests toStrigMod() method
     */
    public void testToStringMod() {
        LList<Song> song = new LList<Song>();
        Song newSong = new Song("A", "B", "C", 1999);
        newSong.increaseHeard(1);
        newSong.increaseTotal1(1);
        song.add(newSong);
        song.add(newSong);
        String test = "Song Title: A\n" + "Song Artist: C\n" 
                + "Song Genre: B\n" + "Song Year: 1999\n" + 
                "Heard\n" + "reading0 art100 sports0 music0\n" 
                + "Likes\n" + "reading0 art0 sports0 music0\n";
        test = test + "\nSong Title: A\n" + "Song Artist: C\n" 
                + "Song Genre: B\n" + "Song Year: 1999\n" + 
                "Heard\n" + "reading0 art100 sports0 music0\n" 
                + "Likes\n" + "reading0 art0 sports0 music0\n";
        assertEquals(test, song.toStringMod("hobby"));
    }

    /**
     * Tests hasNext() method in iterator 
     */
    public void testIterHasNext() {
        list.add("test1");
        Iterator<String> iter1 = list.iterator();
        assertTrue(iter1.hasNext());
        iter1.next();
        assertFalse(iter1.hasNext());
        list.add("test2");
        assertTrue(list.iterator().hasNext());
    }

    /**
     * Tests next() method in iterator
     */
    public void testIterNext() {
        list.add("test1");
        Iterator<String> iter = list.iterator();
        iter.next();
        Exception exception = null;
        try {
            iter.next();
            fail("There are no nodes left in the list");
        } 
        catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof NoSuchElementException);
        
        list.add("test2");
        Iterator<String> iter2 = list.iterator();
        assertEquals("test2", iter2.next()); 
    }
}