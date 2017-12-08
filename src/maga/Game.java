package maga;

import maga.environment.Room;
import maga.environment.Environment;
import maga.character.Cook;
import maga.character.Trump;
import maga.character.Player;
import maga.command.Parser;
import maga.command.Command;
import maga.command.CommandWord;
import maga.util.Helper;
import maga.highscore.HighScore;
import acq.IGame;
import acq.IData;
import acq.ILoadable;
import acq.ISerializable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Game implements ISerializable, ILoadable {

    /**
     * Accesser for the data layer
     */
    private IData data;

    /**
     * parser attribute, an instance from the Parser class.
     */
    private Parser parser;

    /**
     * The environment which contains all rooms
     */
    private Environment environment;

    /**
     * Player
     */
    private Player player;

    /**
     * Trump
     */
    private Trump trump;

    /**
     * Cook
     */
    private Cook cook;

    /**
     * Steps
     */
    private int steps;

    /**
     * Start Time
     */
    private long startTime;

    /**
     * Bonus time
     */
    private long bonusTime;

    /**
    * Save Time
    */
    private long saveTime;

    /**
     * Integer that sets the point the player starts with.
     */
    private int points;

    /**
     * Creates an instance of highscore.
     */
    private HighScore highScore = new HighScore();

    /**
     * Create new instance of game
     */
    public Game() {
        play();
    }

    /**
     * Inject data layer
     * @param data
     */
    public void injectData (IData data) {
        this.data = data;
        data.load("highScore.xml", highScore);
    }

    /**
     * This method process the different commands and decide what they do
     *
     * @param command
     * @return boolean (wantToQuit, false or true)
     */
    public boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case GO:
                step();
                player.goRoom(command);
                break;

            case PICKUP:
                player.pickupItems(command);
                break;

            case DROP:
                player.dropItems(command);
                break;

            case SEARCH:
                step();
                break;

            case USE:
                player.useItem(command);
                break;

            case CALLTRUMP:
                points += trump.findSteak(environment.getRoom("Cleaning room"), player);
                bonusTime += points / 333;
                break;

            case WAIT:
                step();
                break;

            case SAVE:
                this.save();
                break;

            case LOAD:
                data.load("gameState.xml", this);
                fixTime();
                break;

        }

        return wantToQuit;
    }

    /**
     * Every time Player makes a move, the counter "steps" increments, and Trump
     * moves to a new random location.
     *
     * @param command
     */
    private void step() {
        randomizeTrump();
        steps++;
    }

    /**
     * Method to randomize the movement of the "Trump" character. The method
     * uses "Math" to choose a random direction.
     */
    private void randomizeTrump() {

        String[] possibleDirections = {"east", "west", "north", "south"};

        String direction = possibleDirections[(int) Math.floor(Math.random() * 4)];

        trump.goRoom(parser.createCommand("go", direction));

    }

    /**
     * This method checks if the player is in the same room as Trump. If you are
     * then the game is lost.
     *
     * @return boolean
     */
    public boolean youLose() {
        return player.getCurrentRoom() == trump.getCurrentRoom();
    }

    /**
     * This method checks if the player has reached the press briefing room
     * after tweeting in order to win.
     *
     * @return true or false.
     */
    public boolean youWin() {
        return (
            player.getCurrentRoom() == environment.getRoom("Press briefing room") &&
            player.hasTweeted()
        );
    }

    /**
     * Calls the gamestate save method
     */
    public void save() {
        saveTime = System.currentTimeMillis() / 1000L;
        data.save("gameState.xml", this);
    }

    /**
     * Getter for the player
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getter for trump
     *
     * @return trump
     */
    public Trump getTrump() {
        return trump;
    }

    /**
     * Getter for cook
     *
     * @return cook
     */
    public Cook getCook() {
        return cook;
    }

    /**
     * Setter for steps
     *
     * @param steps
     */
    public void setSteps(int steps) {
        this.steps = steps;
    }

    /**
     * Setter for startTime
     *
     * @param startTime
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Setter for bonusTime
     *
     * @param bonusTime
     */
    public void setBonusTime(long bonusTime) {
        this.bonusTime = bonusTime;
    }

    /**
     * Setter for saveTime
     * @param saveTime
     */
    public void setSaveTime(long saveTime){
        this.saveTime = saveTime;
    }

    /**
     * Setter for points
     *
     * @param points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Getter for environment
     *
     * @return environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Getter for parser
     *
     * @return parser
     */
    public Parser getParser() {
        return parser;
    }

    /**
     * Getter for steps
     *
     * @return steps
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Fixes the time after saving and loading
     * so you dont spend the time not playing in the game
     */
    public void fixTime(){
        long difference = this.saveTime - System.currentTimeMillis() / 1000L;
        this.startTime -= difference;
    }

    /**
     * This method restarts the game when called
     */
    public void restart() {
        this.play();
    }

    /**
     * This method starts the game
     */
    private void play() {
        steps = 0;
        startTime = System.currentTimeMillis() / 1000L;
        bonusTime = 0L;
        points = 5000;
        environment = new Environment();
        player = new Player();
        trump = new Trump();
        cook = new Cook();
        parser = new Parser();

        player.setCurrentRoom(environment.getRoom("Press briefing room"));
        trump.setCurrentRoom(environment.getRoom("Oval office"));
        cook.setCurrentRoom(environment.getRoom("Kitchen"));
    }

    /**
     * Accessor method for highscore
     * @return highscore
     */
    public HighScore getHighscore() {
        return highScore;
    }

    /**
     * A method to get score
     * @return
     */
    public int getScore() {
        long endTime = System.currentTimeMillis() / 1000L;
        long elapsedTime = endTime - startTime;
        long finalScore = points - ((elapsedTime - bonusTime) * steps);
        return (int) finalScore;
    }

    /**
     * Serialize state of the game
     * @param  Document doc
     * @return xml document
     */
    @Override
    public Document serialize(Document doc) {
        Element game = doc.createElement("game");
        doc.appendChild(game);

        game.appendChild(
            Helper.createTextNode(doc, "steps", Integer.toString(steps))
        );

        game.appendChild(
            Helper.createTextNode(doc, "startTime", Long.toString(startTime))
        );

        game.appendChild(
            Helper.createTextNode(doc, "bonusTime", Long.toString(bonusTime))
        );

        game.appendChild(
            Helper.createTextNode(doc, "saveTime", Long.toString(saveTime))
        );

        game.appendChild(
            Helper.createTextNode(doc, "points", Long.toString(points))
        );

        player.serialize(doc);
        trump.serialize(doc);
        cook.serialize(doc);
        environment.serialize(doc);

        return doc;
    }

    /**
     * Load and parse xml document
     * @param doc
     */
    @Override
    public void load(Document doc, Game game) {
        Helper.collectItems(this);
        this.getPlayer().removeItems();

        for (Room room : this.environment.getRooms().values()) {
            room.empty();
            room.unlock();
        }

        this.setSteps(Integer.parseInt(Helper.findElementByName(doc, "steps").getTextContent()));
        this.setPoints(Integer.parseInt(Helper.findElementByName(doc, "points").getTextContent()));
        this.setStartTime(Long.parseLong(Helper.findElementByName(doc, "startTime").getTextContent()));
        this.setBonusTime(Long.parseLong(Helper.findElementByName(doc, "bonusTime").getTextContent()));
        this.setSaveTime(Long.parseLong(Helper.findElementByName(doc, "saveTime").getTextContent()));

        this.getPlayer().load(doc, this);
        this.getTrump().load(doc, this);
        this.getCook().load(doc, this);

        this.getEnvironment().load(doc, this);
    }

    /**
     * Add score to highscore
     * @param name
     * @param score
     */
    public void addScore(String name, int score) {
        highScore.add(name, score);
        data.save("highScore.xml", highScore);
    }
}
