package prj5;

import CS2114.Window;
import CS2114.WindowSide;
import java.awt.Color;
import java.io.FileNotFoundException;

import CS2114.Button;
import CS2114.Shape;
import CS2114.TextShape;

/**
 * Front-end class displays data and categories that are sorted accordingly
 *
 * @author Randy Liang, Connor Smedley, and Garrett Novak
 * @version 2015.11.18
 *
 */
public class GUIMusicGraphWindow {

    private static int BAR_HEIGHT = 10;
    private boolean category;
    private boolean sortStyle;
    private int sortMult;
    private int sortTitle;
    private int index;
    private boolean initialized;
    private String topClicked;
    private Window myWindow;
    private SortedLList myMusic;
    private LList<Shape> legendItems;
    private LList<Shape> bars;
    private Shape startingInst;

    /**
     * Constructor
     */
    public GUIMusicGraphWindow(String fileNamePeople, String fileNameSongs) throws FileNotFoundException {
        myWindow = new Window();
        myWindow.setTitle("Music Preference Visualization");
        legendItems = new LList<Shape>();
        bars = new LList<Shape>();
        category = false;
        sortStyle = false;
        initialized = false;
        sortMult = 0;
        sortTitle = 0;
        index = 0;
        myMusic = new SortedLList(fileNamePeople, fileNameSongs);

        // Buttons
        Button prev = new Button("← Prev");
        myWindow.addButton(prev, WindowSide.NORTH);
        prev.onClick(this);
        Button artist = new Button("Sort by Artist Name");
        myWindow.addButton(artist, WindowSide.NORTH);
        artist.onClick(this);
        Button song = new Button("Sort by Song Name");
        myWindow.addButton(song, WindowSide.NORTH);
        song.onClick(this);
        Button year = new Button("Sort by Release Year");
        myWindow.addButton(year, WindowSide.NORTH);
        year.onClick(this);
        Button genre = new Button("Sort by Genre");
        myWindow.addButton(genre, WindowSide.NORTH);
        genre.onClick(this);
        Button next = new Button("Next →");
        myWindow.addButton(next, WindowSide.NORTH);
        next.onClick(this);
        Button hobby = new Button("Represent Hobby");
        myWindow.addButton(hobby, WindowSide.SOUTH);
        hobby.onClick(this);
        Button major = new Button("Represent Major");
        myWindow.addButton(major, WindowSide.SOUTH);
        major.onClick(this);
        Button region = new Button("Represent Region");
        myWindow.addButton(region, WindowSide.SOUTH);
        region.onClick(this);
        Button quit = new Button("Quit");
        myWindow.addButton(quit, WindowSide.SOUTH);
        quit.onClick(this);

        startingInst = new TextShape(275, 135, "Please select how to represent songs.");
        startingInst.setBackgroundColor(Color.RED);
        myWindow.addShape(startingInst);
    }

    /**
     * Gets the window display
     * 
     * @return myWindow
     */
    public Window getWindow() {
        return myWindow;
    }

    /**
     * Creates a click-able quit button
     * 
     * @param button
     *            the button that is created
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }

    /**
     * Moves to the next page of songs
     * 
     * @param button
     *            the next button
     */
    public void clickedNext(Button button) {
        if (initialized) {
            clearBars();
            barsSetUp(sortMult);
        }
    }

    /**
     * moves to the previous page of songs
     * 
     * @param button
     *            the previous button
     */
    public void clickedPrev(Button button) {
        if (initialized) {
            clearBars();
            index -= 18;
            if (index < 0) {
                index = index + myMusic.getSongs().getLength();
            }
            barsSetUp(sortMult);
        }
    }

    /**
     * Changes the represented data to be based upon peoples major
     * 
     * @param button
     *            major button
     */
    public void clickedRepresentMajor(Button button) {
        category = true;
        topClicked = "Major";
        sortMult = 2;
        index = 0;
        if (sortStyle && !initialized) {
            instanctiate();
        }
        if (initialized) {
            legendSetUp();
            clearBars();
            barsSetUp(sortMult);
        }
    }

    /**
     * Changes the data to representations based on regions of people
     * 
     * @param button
     *            region button
     */
    public void clickedRepresentRegion(Button button) {
        category = true;
        topClicked = "Region";
        sortMult = 3;
        index = 0;
        if (sortStyle && !initialized) {
            instanctiate();
        }
        if (initialized) {
            legendSetUp();
            clearBars();
            barsSetUp(sortMult);
        }
    }

    /**
     * Changes the data to representations based on hobbies of people
     * 
     * @param button
     *            hobby button
     */
    public void clickedRepresentHobby(Button button) {
        category = true;
        topClicked = "Hobby";
        sortMult = 1;
        index = 0;
        if (sortStyle && !initialized) {
            instanctiate();
        }
        if (initialized) {
            legendSetUp();
            clearBars();
            barsSetUp(sortMult);
        }
    }

    /**
     * Sorts the song data list based on titles
     * 
     * @param button
     *            song name button
     */
    public void clickedSortbySongName(Button button) {
        sortStyle = true;
        sortTitle = 1;
        index = 0;
        instanctiate();
        myMusic.sortTitle();
        if (initialized) {
            clearBars();
            barsSetUp(sortMult);
        }
    }

    /**
     * sorts the song data list based on genres
     * 
     * @param button
     *            sort genre button
     */
    public void clickedSortbyGenre(Button button) {
        sortStyle = true;
        sortTitle = 2;
        index = 0;
        instanctiate();
        myMusic.sortGenre();
        if (initialized) {
            clearBars();
            barsSetUp(sortMult);
        }
    }

    /**
     * Sorts the song data list based on artists names
     * 
     * @param button
     *            sort artist
     */
    public void clickedSortbyArtistName(Button button) {
        sortStyle = true;
        sortTitle = 1;
        index = 0;
        instanctiate();
        myMusic.sortAuthor();
        if (initialized) {
            clearBars();
            barsSetUp(sortMult);
        }
    }

    /**
     * Sorts the song data list based on release date
     * 
     * @param button
     *            sort year button
     */
    public void clickedSortbyReleaseYear(Button button) {
        sortStyle = true;
        sortTitle = 0;
        index = 0;
        instanctiate();
        myMusic.sortRelease();
        if (initialized) {
            clearBars();
            barsSetUp(sortMult);
        }
    }

    /**
     * Creates the original graphical representation only after the two choices
     * have been made
     */
    private void instanctiate() {
        if (category && sortStyle && !initialized) {
            // Glyph
            Shape minibar = new Shape(105, 60, 4, 40, Color.BLACK);
            myWindow.addShape(minibar);
            minibar = new Shape(315, 60, 4, 40, Color.BLACK);
            myWindow.addShape(minibar);
            minibar = new Shape(525, 60, 4, 40, Color.BLACK);
            myWindow.addShape(minibar);

            minibar = new Shape(105, 150, 4, 40, Color.BLACK);
            myWindow.addShape(minibar);
            minibar = new Shape(315, 150, 4, 40, Color.BLACK);
            myWindow.addShape(minibar);
            minibar = new Shape(525, 150, 4, 40, Color.BLACK);
            myWindow.addShape(minibar);

            minibar = new Shape(105, 240, 4, 40, Color.BLACK);
            myWindow.addShape(minibar);
            minibar = new Shape(315, 240, 4, 40, Color.BLACK);
            myWindow.addShape(minibar);
            minibar = new Shape(525, 240, 4, 40, Color.BLACK);
            myWindow.addShape(minibar);

            Shape title = new TextShape(myWindow.getGraphPanelWidth() - 100, 160, "Song Title");
            myWindow.addShape(title);
            title.setBackgroundColor(Color.WHITE);

            minibar = new Shape(myWindow.getGraphPanelWidth() - 65, 175, 4, 30, Color.BLACK);
            myWindow.addShape(minibar);

            Shape heard = new TextShape(myWindow.getGraphPanelWidth() - 62, 180, "liked");
            myWindow.addShape(heard);
            heard.setBackgroundColor(Color.WHITE);
            Shape liked = new TextShape(myWindow.getGraphPanelWidth() - 106, 180, "heard");
            myWindow.addShape(liked);
            liked.setBackgroundColor(Color.WHITE);

            // right
            Shape legendBottom1 = new Shape(myWindow.getGraphPanelWidth() - 3, 15, 3, 200, Color.BLACK); // x,
                                                                                                         // y,
                                                                                                         // width,
                                                                                                         // height
            myWindow.addShape(legendBottom1);
            // top
            Shape legendOver1 = new Shape(myWindow.getGraphPanelWidth() - 113, 15, 113, 3, Color.BLACK);
            myWindow.addShape(legendOver1);
            // left
            Shape legendBottom2 = new Shape(myWindow.getGraphPanelWidth() - 113, 15, 3, 203, Color.BLACK);
            myWindow.addShape(legendBottom2);
            // bottom
            Shape legendOver2 = new Shape(myWindow.getGraphPanelWidth() - 113, 215, 113, 3, Color.BLACK);
            myWindow.addShape(legendOver2);

            legendSetUp();

            myWindow.removeShape(startingInst);

            initialized = true;
        }
    }

    /**
     * sets up a legend based on current displays
     */
    private void legendSetUp() {
        if (initialized) {
            clearLegend();
        }
        // Info box
        Shape myTextLegend = new TextShape(myWindow.getGraphPanelWidth() - 111, 20, topClicked + " Legend");
        legendItems.add(myTextLegend);
        myWindow.addShape(myTextLegend);
        myTextLegend.setBackgroundColor(Color.WHITE);
        Shape thing1;
        Shape thing2;
        Shape thing3;
        Shape thing4;
        // Info box text shapes
        if (topClicked.equals("Hobby")) {
            thing1 = new TextShape(myWindow.getGraphPanelWidth() - 111, 40, "Read", Color.MAGENTA);
            thing2 = new TextShape(myWindow.getGraphPanelWidth() - 111, 60, "Art", Color.BLUE);
            thing3 = new TextShape(myWindow.getGraphPanelWidth() - 111, 80, "Sport", Color.ORANGE);
            thing4 = new TextShape(myWindow.getGraphPanelWidth() - 111, 100, "Music", Color.GREEN);
        } 
        else if (topClicked.equals("Major")) {
            thing1 = new TextShape(myWindow.getGraphPanelWidth() - 111, 40, "Computer", Color.MAGENTA);
            Shape thing12 = new TextShape(myWindow.getGraphPanelWidth() - 111, 60, "Science", Color.MAGENTA);
            myWindow.addShape(thing12);
            thing12.setBackgroundColor(Color.WHITE);
            legendItems.add(thing12);
            thing2 = new TextShape(myWindow.getGraphPanelWidth() - 111, 80, "Other", Color.BLUE);
            Shape thing22 = new TextShape(myWindow.getGraphPanelWidth() - 111, 100, "Engineering", Color.BLUE);
            myWindow.addShape(thing22);
            thing22.setBackgroundColor(Color.WHITE);
            legendItems.add(thing22);
            thing3 = new TextShape(myWindow.getGraphPanelWidth() - 111, 120, "Math or CMDA", Color.ORANGE);
            thing4 = new TextShape(myWindow.getGraphPanelWidth() - 111, 140, "Other", Color.GREEN);
        } 
        else {
            thing1 = new TextShape(myWindow.getGraphPanelWidth() - 111, 40, "Northeast", Color.MAGENTA);
            thing2 = new TextShape(myWindow.getGraphPanelWidth() - 111, 60, "Southeast", Color.BLUE);
            thing3 = new TextShape(myWindow.getGraphPanelWidth() - 111, 80, "Other US", Color.ORANGE);
            Shape thing12 = new TextShape(myWindow.getGraphPanelWidth() - 111, 100, "Region", Color.ORANGE);
            myWindow.addShape(thing12);
            thing12.setBackgroundColor(Color.WHITE);
            legendItems.add(thing12);
            thing4 = new TextShape(myWindow.getGraphPanelWidth() - 111, 120, "Outside US", Color.GREEN);
        }
        myWindow.addShape(thing1);
        thing1.setBackgroundColor(Color.WHITE);
        myWindow.addShape(thing2);
        thing2.setBackgroundColor(Color.WHITE);
        myWindow.addShape(thing3);
        thing3.setBackgroundColor(Color.WHITE);
        myWindow.addShape(thing4);
        thing4.setBackgroundColor(Color.WHITE);
        legendItems.add(thing1);
        legendItems.add(thing2);
        legendItems.add(thing3);
        legendItems.add(thing4);
    }

    /**
     * gets rid of all the shapes in the legend that can be changes
     */
    private void clearLegend() {
        for (int i = 0; i < legendItems.getLength(); i++) {
            myWindow.removeShape(legendItems.getEntry(i));
        }
        legendItems.clear();
    }

    /**
     * clears all the graph bars on the screen
     */
    private void clearBars() {
        for (int i = 0; i < bars.getLength(); i++) {
            myWindow.removeShape(bars.getEntry(i));
        }
        bars.clear();
    }

    /**
     * places bars in appropriate graphs in the 3x3 grid
     * 
     * @param mult
     *            the section of data to be used in bars
     */
    private void barsSetUp(int mult) {
        int x = 1;
        int y = 1;
        for (int i = 0; i < 9; i++) {
            setGraph(myMusic.getSongs().getEntry(index).percentHeard(),
                    myMusic.getSongs().getEntry(index).percentLiked(), mult, x, y, myMusic.getSongs().getEntry(index));
            x = (x + 1) % 4;
            if (x == 0) {
                y++;
                x++;
            }
            index = (index + 1) % myMusic.getSongs().getLength();
        }
    }

    /**
     * creates the 8 appropriate bars for a given song
     * 
     * @param heard
     *            percent heard array
     * @param liked
     *            percent liked array
     * @param currentSorted
     *            the current sorting method to get correct data
     * @param x
     *            location in x on 3x3 grid
     * @param y
     *            location in y on 3x3 grid
     * @param song
     *            current song to get title
     */
    private void setGraph(int[] heard, int[] liked, int currentSorted, int x, int y, Song song) {
        x = 210 * (x - 1) + 105;
        y = 90 * (y - 1) + 90;
        Shape bar11 = new Shape(x - heard[1 * currentSorted], y - 30, heard[1 * currentSorted], BAR_HEIGHT,
                Color.MAGENTA);
        myWindow.addShape(bar11);
        bars.add(bar11);
        Shape bar12 = new Shape(x + 4, y - 30, liked[1 * currentSorted], BAR_HEIGHT, Color.MAGENTA);
        myWindow.addShape(bar12);
        bars.add(bar12);
        Shape bar21 = new Shape(x - heard[2 * currentSorted], y - 20, heard[2 * currentSorted], BAR_HEIGHT,
                Color.BLUE);
        myWindow.addShape(bar21);
        bars.add(bar21);
        Shape bar22 = new Shape(x + 4, y - 20, liked[2 * currentSorted], BAR_HEIGHT, Color.BLUE);
        myWindow.addShape(bar22);
        bars.add(bar22);
        Shape bar31 = new Shape(x - heard[3 * currentSorted], y - 10, heard[3 * currentSorted], BAR_HEIGHT, Color.ORANGE);
        myWindow.addShape(bar31);
        bars.add(bar31);
        Shape bar32 = new Shape(x + 4, y - 10, liked[3 * currentSorted], BAR_HEIGHT, Color.ORANGE);
        myWindow.addShape(bar32);
        bars.add(bar32);
        Shape bar41 = new Shape(x - heard[4 * currentSorted], y, heard[4 * currentSorted], BAR_HEIGHT, Color.GREEN);
        myWindow.addShape(bar41);
        bars.add(bar41);
        Shape bar42 = new Shape(x + 4, y, liked[4 * currentSorted], BAR_HEIGHT, Color.GREEN);
        myWindow.addShape(bar42);
        bars.add(bar42);

        String title = song.getTitle();
        if (title.length() > 25) {
            String title2 = title.substring(21);
            title = title.substring(0, 21);
            Shape songTitle = new TextShape(x - 100, y - 80, title);
            myWindow.addShape(songTitle);
            songTitle.setBackgroundColor(Color.WHITE);
            bars.add(songTitle);
            Shape songTitle2 = new TextShape(x - 100, y - 65, title2);
            myWindow.addShape(songTitle2);
            songTitle2.setBackgroundColor(Color.WHITE);
            bars.add(songTitle2);
        } 
        else {
            Shape songTitle = new TextShape(x - 100, y - 65, song.getTitle());
            myWindow.addShape(songTitle);
            songTitle.setBackgroundColor(Color.WHITE);
            bars.add(songTitle);
        }
        if (sortTitle == 1) {
            Shape songAuthor = new TextShape(x - 100, y - 50, "by, " + song.getAuthor());
            myWindow.addShape(songAuthor);
            songAuthor.setBackgroundColor(Color.WHITE);
            bars.add(songAuthor);
        } 
        else if (sortTitle == 2) {
            Shape songAuthor = new TextShape(x - 100, y - 50, "Genre: " + song.getGenre());
            myWindow.addShape(songAuthor);
            songAuthor.setBackgroundColor(Color.WHITE);
            bars.add(songAuthor);
        } 
        else {
            Shape songAuthor = new TextShape(x - 100, y - 50, "Released: " + song.getDate());
            myWindow.addShape(songAuthor);
            songAuthor.setBackgroundColor(Color.WHITE);
            bars.add(songAuthor);
        }
    }

    /**
     * Main method to display program
     * 
     * @param args
     *            files
     * @throws FileNotFoundException
     *             if the files don't have the right name
     */
    public static void main(String[] args) throws FileNotFoundException {
        @SuppressWarnings("unused")
        GUIMusicGraphWindow window;
        if (args.length > 0) {
            window = new GUIMusicGraphWindow(args[0], args[1]);
        } 
        else {
            window = new GUIMusicGraphWindow("MusicSurveyData.csv", "SongList.csv");
        }
    }
}