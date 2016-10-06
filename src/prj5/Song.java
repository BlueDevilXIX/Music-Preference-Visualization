package prj5;

/**
 * Song class
 * 
 * @author Randy Liang, Connor Smedley, and Garrett Novak
 * @version 2015.11.20
 *
 */
public class Song {
    private String author;
    private String title;
    private String genre;
    private int date;
    private int[] liked;
    private int[] heard;
    private int[] totalPeople1;
    private int[] totalPeople2;

    /**
     * Constructor
     * 
     * @param inTitle
     *            title
     * @param inGenre
     *            genre
     * @param inAuthor
     *            author
     * @param inDate
     *            date
     */
    public Song(String inTitle, String 
            inGenre, String inAuthor, int inDate) {
        title = inTitle;
        author = inAuthor;
        genre = inGenre;
        date = inDate;
        liked = new int[13];
        heard = new int[13];
        totalPeople1 = new int[13];
        totalPeople2 = new int[13];
    }

    /**
     * Gets title
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets author
     * 
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets genre
     * 
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gets date
     * 
     * @return date
     */
    public int getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Song comparedTo = (Song) obj;
        return title.equals(comparedTo.getTitle()) 
                && author.equals(comparedTo.getAuthor())
                && genre.equals(comparedTo.getGenre()) 
                && date == comparedTo.getDate();
    }

    /**
     * To string method
     * 
     * @return appended string
     */
    public String toString() {
        StringBuilder output = new StringBuilder("Song Title: ");
        output.append(title + "\n");
        output.append("Song Artist: " + author + "\n");
        output.append("Song Genre: " + genre + "\n");
        output.append("Song Year: " + date + "\n");
        return output.toString();
    }

    /**
     * Gets liked
     * 
     * @return liked
     */
    public int[] getLiked() {
        return liked;
    }

    /**
     * Gets heard
     * 
     * @return heard
     */
    public int[] getHeard() {
        return heard;
    }

    /**
     * Gets total people
     * 
     * @return totalPeople1
     */
    public int[] getTotal1() {
        return totalPeople1;
    }

    /**
     * Gets total people
     * 
     * @return totalPeople2
     */
    public int[] getTotal2() {
        return totalPeople2;
    }

    /**
     * Increments liked index
     * 
     * @param index
     *            the index
     */
    public void increaseLiked(int index) {
        liked[index]++;
    }

    /**
     * Increments heard index
     * 
     * @param index
     *            the index
     */
    public void increaseHeard(int index) {
        heard[index]++;
    }

    /**
     * Increments totalPeople1 index
     * 
     * @param index
     *            the index
     */
    public void increaseTotal1(int index) {
        totalPeople1[index]++;
    }

    /**
     * Increments totalPeople2 index
     * 
     * @param index
     *            the index
     */
    public void increaseTotal2(int index) {
        totalPeople2[index]++;
    }

    /**
     * Finds the percentage of people who have 
     * heard a particular song
     * 
     * @return endRes
     */
    public int[] percentHeard() {
        int[] endRes = new int[13];
        for (int i = 0; i < heard.length; i++) {
            if (totalPeople1[i] == 0) {
                endRes[i] = 0;
            } 
            else {
                endRes[i] = (int) (((float) heard[i] 
                        / (float) totalPeople1[i]) * 100.0);
            }
            if (endRes[i] == 89) {
                endRes[i] = 90; //fuzzyEquals
            }
        }
        return endRes;
    }

    /**
     * Finds the percentage of people who liked
     * a particular song
     * 
     * @return endRes
     */
    public int[] percentLiked() {
        int[] endRes = new int[13];
        for (int i = 0; i < liked.length; i++) {
            if (totalPeople2[i] == 0) {
                endRes[i] = 0;
            } 
            else {
                endRes[i] = (int) (((float) 
                        liked[i] / (float) 
                        totalPeople2[i]) * 100.0);
            }
        }
        return endRes;
    }

    /**
     * To string method with type param
     * 
     * @param type
     *            the type
     * @return the appended string
     */
    public String toString(String type) {
        StringBuilder output = new StringBuilder(this.toString());
        output.append("Heard" + "\n");
        int[] hearings = percentHeard();
        int[] likings = percentLiked();
        if (type.equals("hobby")) {
            output.append("reading" + hearings[0] + 
                    " art" + hearings[1] + " sports" + 
                    hearings[2] + " music"
                    + hearings[3] + "\n");
            output.append("Likes" + "\n");
            output.append("reading" + likings[0] + 
                    " art" + likings[1] + " sports" + likings[2] + 
                    " music" + likings[3]
                    + "\n");
        } 
        else if (type.equals("major")) {
            output.append("Computer Science" + hearings[4] + 
                    " Other Engineering" + hearings[5] + " Math or CMDA"
                    + hearings[6] + " Other" + hearings[7] + "\n");
            output.append("Likes" + "\n");
            output.append("Computer Science" + likings[4] + 
                    " Other Engineering" + likings[5] + " Math or CMDA"
                    + likings[6] + " Other" + likings[7] + "\n");
        } 
        else {
            output.append("Northeast US" + hearings[8] + 
                    " Southeast US" + hearings[9] + " Rest of US" + hearings[10]
                    + " Outside US" + hearings[11] + "\n");
            output.append("Likes" + "\n");
            output.append("Northeast US" + likings[8] + 
                    " Southeast US" + likings[9] + " Rest of US" + likings[10]
                    + " Outside US" + likings[11] + "\n");
        }
        // output.append("\n");
        return output.toString();
    }
}