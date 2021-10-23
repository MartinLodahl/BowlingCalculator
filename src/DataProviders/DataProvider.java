/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataProviders;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author Marti
 */
public class DataProvider {
    
    int[][] rounds;
    
    public int[][] getRound(){
        return getRound(10);
    }
    
    public int[][] getRound(int numberOfRounds ){
        rounds = new int[numberOfRounds][2];
        
        for (int i = 0; i < numberOfRounds; i++) {
            var firstHit = getRandomInteger(10);
            var secondHit= 10 - firstHit;//getRandomInteger(10-firstHit);
            
            rounds[i] = new int[]{firstHit, secondHit};
        }
        
        return rounds;
    }
    
    //Random.nextInt(value) returns a pseudorandom int value between zero (inclusive) and the specified bound (exclusive).
    private static int getRandomInteger(int max){
        return ThreadLocalRandom.current().nextInt(max+1);
    }    
}
