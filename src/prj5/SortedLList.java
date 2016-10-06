package prj5;

import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * Sorted Linked list class
 * 
 * @author Randy Liang, Connor Smedley, and Garrett Novak
 * @version 2015.11.20
 *
 */
public class SortedLList {
    private LList<Song> song;

    
    /**
     * Constructor
     * 
     * @param fileNamePeople
     *            people
     * @param fileNameSongs
     *            songs
     * @throws FileNotFoundException
     */
    public SortedLList(String fileNamePeople, 
            String fileNameSongs) throws FileNotFoundException {
        Input read = new Input(fileNamePeople, fileNameSongs);
        song = read.getSongs();
    }
    
    /**
     * Constructor
     * 
     * @param temp
     *            song
     */
    public SortedLList(LList<Song> temp) {
        song = temp;
    }

    /**
     * Sorts by title of song
     */
    public void sortTitle() {
        Iterator<Song> iter1 = song.iterator();
        for (int i = 0; i < song.getLength(); i++) {
            Song current = iter1.next();
            Iterator<Song> iter2 = song.iterator();
            for (int j = 0; j <= i; j++) {
                Song tempSong = iter2.next();
                if (CompareSongs.compareTitle(current, tempSong) < 0) {
                    Song movement = song.replace(i, tempSong);
                    current = song.replace(j, movement);
                }
            }
        }
    }

    /**
     * Sorts by author of song
     */
    public void sortAuthor() {
        Iterator<Song> iter1 = song.iterator();
        for (int i = 0; i < song.getLength(); i++) {
            Song current = iter1.next();
            Iterator<Song> iter2 = song.iterator();
            for (int j = 0; j <= i; j++) {
                Song tempSong = iter2.next();
                if (CompareSongs.compareAuthor(current, tempSong) < 0) {
                    Song movement = song.replace(i, tempSong);
                    current = song.replace(j, movement);
                }
            }
        }
    }

    /**
     * Sorts by release year
     */
    public void sortRelease() {
        Iterator<Song> iter1 = song.iterator();
        for (int i = 0; i < song.getLength(); i++) {
            Song current = iter1.next();
            Iterator<Song> iter2 = song.iterator();
            for (int j = 0; j <= i; j++) {
                Song tempSong = iter2.next();
                if (CompareSongs.compareDate(current, tempSong) < 0) {
                    Song movement = song.replace(i, tempSong);
                    current = song.replace(j, movement);
                }
            }
        }
    }

    /**
     * Sorts by genre
     */
    public void sortGenre() {
        Iterator<Song> iter1 = song.iterator();
        for (int i = 0; i < song.getLength(); i++) {
            Song current = iter1.next();
            Iterator<Song> iter2 = song.iterator();
            for (int j = 0; j <= i; j++) {
                Song tempSong = iter2.next();
                if (CompareSongs.compareGenre(current, tempSong) < 0) {
                    Song movement = song.replace(i, tempSong);
                    current = song.replace(j, movement);
                }
            }
        }
    }

    /**
     * Gets songs
     * 
     * @return songs
     */
    public LList<Song> getSongs() {
        return song;
    }
}