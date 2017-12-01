package maga.highscore;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
    
    /**
     * A method to compare scores to see which is biggest. 
     * @param score1
     * @param score2
     * @return int
     */
    @Override
    public int compare(Score score1, Score score2) {
        int a = score1.getScore();
        int b = score2.getScore();
            
        if (a > b) {
            return -1;
        } else if (a < b) {
            return +1;
        } else {
            return 0;
        }
    }
}