/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.item;
import worldofzuul.character.Player;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author mikkellarsen
 */
public class Computer extends Item {
    /**
     * Creating an Array with Trump Tweets
     */
    private final String[] tweets = {
        "I loved beating these two terrible human beings. I would never recommend that anyone use her lawyer, he is a total loser!",
        "There is no longer a Bernie Sanders \"political revolution.\" He is turning out to be a weak and somewhat pathetic figure,wants it all to end!",
        "A dishonest slob of a reporter, who doesn't understand my sarcasm when talking about him or his wife, wrote a foolish & boring Trump \"hit\""
    };
    /**
     * Constructor creating an instance of the class Computer
     * Extending the class Item
     */
    public Computer() {
        super("Computer", false);
    }
    /**
     * A method for tweeting from the Computer
     */
    public void tweet(){
        Random random = new Random();
        int randomIndex = random.nextInt(tweets.length - 1);
        System.out.println(tweets[randomIndex]);
    }
    @Override
    public void use(Player player){
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to twitter, write a tweet President Trump: ");
        String tweet = input.nextLine();
        
        if(tweet.length() > 0){
            System.out.println("\n" + tweet);
        } else {
            this.tweet();
        }
    }
}
