/**
 * 
 */
package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Reads survey data and increments as more responses
 * match the categories
 * @author Randy Liang, Connor Smedley, and Garrett Novak
 * @version 2015.11.18
 *
 */
public class Input {

    private LList<Song> songs;

    /**
     * Constructor
     * @param fileNamePeople people
     * @param fileNameSongs songs
     * @throws FileNotFoundException
     */
    public Input(String fileNamePeople, 
            String fileNameSongs) throws FileNotFoundException {
        songs = new LList<Song>();
        readSurveyFile(fileNamePeople, fileNameSongs);
    }
    
    /**
     * Main method
     * 
     * @param args
     *            args
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) 
            throws FileNotFoundException, UnsupportedEncodingException {
        SortedLList temp;
        if (args.length > 0) {
            temp = new SortedLList(args[0], args[1]);
        } else {
            temp = new SortedLList("MusicSurveyDataTest1.csv", "SongListTest1.csv");
        }
        temp.sortGenre();
        System.out.println(temp.getSongs().toStringMod("hobby"));
        temp.sortTitle();
        System.out.println(temp.getSongs().toStringMod("hobby"));
    }

    /**
     * Reads survey file
     * @param fileNamePeople people
     * @param fileNameSongs songs
     * @throws FileNotFoundException
     */
    private void readSurveyFile(String fileNamePeople, 
            String fileNameSongs) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileNameSongs));
        scan.nextLine();
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            String[] splitLine = currentLine.split(",");
            Song currentSong = new Song(splitLine[0], splitLine[3],
                    splitLine[1], Integer.valueOf(splitLine[2]));
            songs.add(currentSong);
        }
        scan.close();
        scan = new Scanner(new File(fileNamePeople));
        scan.nextLine();
        int songIndex = 0;
        while (scan.hasNextLine()) {
            songIndex = songs.getLength() - 1;
            String currentLine = scan.nextLine();
            currentLine = currentLine.replaceAll(",,", ",something,");
            if (currentLine.endsWith(",")) {
                currentLine = currentLine + "something";
            }
            // System.out.println(currentLine);
            String[] splitLine = currentLine.split(",");
            int hobby = 12;
            int region = 12;
            int major = 12;

            if (splitLine.length == 2) {
                currentLine = scan.nextLine();
                currentLine = currentLine.replaceAll(",,", ",something,");
                splitLine = currentLine.split(",");
            }

            // System.out.println(splitLine.length);

            if (splitLine[4].equals("music")) {
                hobby = 3;
            } 
            else if (splitLine[4].equals("reading")) {
                hobby = 0;
            } 
            else if (splitLine[4].equals("sports")) {
                hobby = 2;
            } 
            else if (splitLine[4].equals("art")) {
                hobby = 1;
            }
            //Major
            if (splitLine[2].equals("Computer Science")) {
                major = 4;
            } 
            else if (splitLine[2].equals("Math or CMDA")) {
                major = 6;
            } 
            else if (splitLine[2].equals("Other Engineering")) {
                major = 5;
            } 
            else if (splitLine[2].equals("Other")) {
                major = 7;
            }
            //Region
            if (splitLine[3].equals("Southeast")) {
                region = 9;
            } 
            else if (splitLine[3].equals("Northeast")) {
                region = 8;
            } 
            else if (splitLine[3].equals("United States "
                    + "(other than Southeast or Northwest)")) {
                region = 10;
            } 
            else if (splitLine[3].equals("Outside of United States")) {
                region = 11;
            }
            for (int i = 5; i < splitLine.length - 1; i += 2) {
                if (splitLine[i].equals("Yes")) {
                    songs.getEntry(songIndex).increaseHeard(hobby);
                    songs.getEntry(songIndex).increaseHeard(major);
                    songs.getEntry(songIndex).increaseHeard(region);
                    songs.getEntry(songIndex).increaseTotal1(hobby);
                    songs.getEntry(songIndex).increaseTotal1(major);
                    songs.getEntry(songIndex).increaseTotal1(region);
                } 
                else if (splitLine[i].equals("No")) {
                    songs.getEntry(songIndex).increaseTotal1(hobby);
                    songs.getEntry(songIndex).increaseTotal1(major);
                    songs.getEntry(songIndex).increaseTotal1(region);
                }

                if (splitLine[i + 1].equals("Yes")) {
                    songs.getEntry(songIndex).increaseLiked(hobby);
                    songs.getEntry(songIndex).increaseLiked(major);
                    songs.getEntry(songIndex).increaseLiked(region);
                    songs.getEntry(songIndex).increaseTotal2(hobby);
                    songs.getEntry(songIndex).increaseTotal2(major);
                    songs.getEntry(songIndex).increaseTotal2(region);
                } 
                else if (splitLine[i + 1].equals("No")) {
                    songs.getEntry(songIndex).increaseTotal2(hobby);
                    songs.getEntry(songIndex).increaseTotal2(major);
                    songs.getEntry(songIndex).increaseTotal2(region);
                }
                songIndex--;
            }
        }
        scan.close();
    }

    /**
     * Gets songs
     * 
     * @return songs
     */
    public LList<Song> getSongs() {
        return songs;
    }
}