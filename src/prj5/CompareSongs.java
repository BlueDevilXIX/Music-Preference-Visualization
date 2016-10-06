package prj5;

/**
 * Compares songs by category
 * 
 * @author Randy Liang, Connor Smedley, and Garrett Novak
 * @version 2015.11.20
 */
public class CompareSongs {

    /**
     * Compares titles
     * 
     * @param song1
     *            first song
     * @param song2
     *            second song
     * @return 0 if they're equal
     */
    public static int compareTitle(Song song1, Song song2) {
        if (song1 == null || song2 == null) {
            throw new IllegalArgumentException();
        }
        if (song1.equals(song2)) {
            return 0;
        }
        return song1.getTitle().compareTo(song2.getTitle());
    }

    /**
     * Compares authors
     * 
     * @param song1
     *            first song
     * @param song2
     *            second song
     * @return 0 if they're equal
     */
    public static int compareAuthor(Song song1, Song song2) {
        if (song1 == null || song2 == null) {
            throw new IllegalArgumentException();
        }

        if (song1.equals(song2)) {
            return 0;
        }

        return song1.getAuthor().compareTo(song2.getAuthor());
    }

    /**
     * Compares genres
     * 
     * @param song1
     *            first song
     * @param song2
     *            second song
     * @return 0 if they're equal
     */
    public static int compareGenre(Song song1, Song song2) {
        if (song1 == null || song2 == null) {
            throw new IllegalArgumentException();
        }
        return song1.getGenre().compareTo(song2.getGenre());
    }

    /**
     * Compares release dates
     * 
     * @param song1
     *            first song
     * @param song2
     *            second song
     * @return 0 if they're equal
     */
    public static int compareDate(Song song1, Song song2) {
        if (song1 == null || song2 == null) {
            throw new IllegalArgumentException();
        }
        if (song1.equals(song2)) {
            return 0;
        }
        else if (song1.getDate() > song2.getDate()) {
            return 1;
        }
        else {
            return -1;
        }
    }
}