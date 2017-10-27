/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.item;
import maga.character.Player;
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
        "“I want everyone to know that I am stepping down as president, "
            + "\nand that I secretly love Hillary and Bernie!",
        "I want to apologize to all the reporters I have accused of reporting “FAKE NEWS”. They are great! "
            + "\nWhat they do have a yuuuge importance to the world!”",
        "I have a secret love affair with Vladimir Putin, "
            + "\nhe gives me the kind of love that I always failed to get from my father!",
        "“I… am… a….. DEMOCRAT! #FuckNRA”"
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
    
    /**
     * A "use" method for the item "computer", allows player to tweet a message,
     * and generate a random message if nothing is entered.
     * After tweeting it changes the state of tweeted to true.
     * @param player 
     */
    @Override
    public void use(Player player){
        player.tweeted();
        Scanner input = new Scanner(System.in);
        String[] twitterPage = {
            "",
            "@realdonaldtrump",
            "Wow, so many Fake News stories today. No matter what I do or say, "
                + "\nthey will not write or speak truth. The Fake News Media is out of control!",
            "",
            "@realdonaldtrump",
            "Heading back to Washington after working hard and watching some of the "
                + "\nworst and most dishonest Fake News reporting I have ever seen!",
            "",
            "@realdonaldttrump",
            "Only the Fake News Media and Trump enemies want me to stop using Social "
                + "\nMedia (110 million people). Only way for me to get the truth out!"
        };
        
        for(int i = 0; i < twitterPage.length; i++) {
            System.out.println(twitterPage[i]);
        }
        
        System.out.println("  ");
        System.out.println("@realdonaldtrump");
        System.out.println("Enter a new tweet (press enter to generate a random tweet): ");
        String tweet = input.nextLine();
        
        if(tweet.length() > 0){
            System.out.println("-------------------------------------");
            System.out.println("\n" + "@realdonaldtrump" + "\n" + tweet);
            System.out.println("-------------------------------------");
        } else {
            this.tweet();
        }
    }
}
