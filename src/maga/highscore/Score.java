/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maga.highscore;

/**
 *
 * @author ander
 */
public class Score {
    
    /** 
     * The name for the player. 
     */
    private String name; 
    
    /**
     * The int score for our HighScore.
     */
    private int score; 
    
    /**
     * Constrcutor with name and score. 
     * @param name
     * @param score 
     */
    public Score(String name, int score) {
        this.name = name;
        this.score = score; 
    }
    
    /**
     * This method is a accessor method for name.
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * This method is a accessor method for score. 
     * @return score
     */
    public int getScore() {
        return score;
    } 
    
    /**
     * This method is a mutator method for name. 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * This method is a mutator method for score.
     * @param score 
     */
    public void setScore(int score) {
        this.score = score;
    } 
    
}
