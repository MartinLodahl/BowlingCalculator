/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calculators;

import java.util.Arrays;

/**
 *
 * @author Marti
 */
public class PointCalculator implements IPointCalculator {

    private boolean strike2RoundsAgo = false;
    private boolean strikeLastRound = false;
    private boolean spareLastRound = false;
    private int[] scores;
    private int scoreThisRound;
    private final int noOfPins = 10;

    @Override
    public int[] calculateScores(int[][] rounds) {

        scores = new int[rounds.length];
        scoreThisRound = 0;

        for (int i = 0; i < rounds.length; i++) {
            int sumOfRound = Arrays.stream(rounds[i]).sum();
            if (sumOfRound == noOfPins) {
                if (rounds[i][0] == noOfPins) {
                    //Handle strike
                    if (strike2RoundsAgo == true) {
                        CatchupOnPreviousStikesAndSpares(rounds, i);
                    } else if (strikeLastRound == true) {
                        strike2RoundsAgo = true;
                    } else {
                        if(spareLastRound){
                            CatchupOnPreviousStikesAndSpares(rounds, i);
                        }
                        strikeLastRound = true;
                    }
                } else {
                    //Handle spares
                    CatchupOnPreviousStikesAndSpares(rounds, i);
                    
                        spareLastRound = true;
                }
                continue;
            }

            CatchupOnPreviousStikesAndSpares(rounds, i);
            scoreThisRound += sumOfRound;
            scores[i] = scoreThisRound;
        }
        CatchupOnPreviousStikesAndSpares(rounds, rounds.length - 1);

        return scores;
    }

    private void CatchupOnPreviousStikesAndSpares(int[][] rounds, int currentRound) {

        if (strike2RoundsAgo) {
            //Calculate 2 strikes. 
            //We know the previous 2 rounds had strikes.
            //If it's the last round, they will be worth 30(10+10+10), 20(10+10+0) and 10(10+0+0). 
            if (currentRound == rounds.length - 1) {
                //If the value is 0, we know it haven't been populated, which means it was a strike.
                if (scores[currentRound - 2] == 0) {
                    scores[currentRound - 2] = scoreThisRound += noOfPins * 3;
                }
                scores[currentRound - 1] = scoreThisRound += noOfPins * 2;
                scores[currentRound] = scoreThisRound += noOfPins;
                strike2RoundsAgo = false;
                strikeLastRound = false;

            } else {
                scores[currentRound - 2] = scoreThisRound += (noOfPins * 2 + rounds[currentRound][0]);
                if (rounds[currentRound][0] != noOfPins) {
                    strike2RoundsAgo = false;
                    calculateStrikeLastRound(rounds, currentRound);
                }
            }

        } else if (strikeLastRound) {
            //Calculate 1 strike
            //If last round is a spare, we will get in here - So check if this round was a strike, if not then round[currentRound-1] is a strike
            if (currentRound == rounds.length - 1 && rounds[currentRound][0] == noOfPins) {
                scores[currentRound] = scoreThisRound += noOfPins;
            } else {
                calculateStrikeLastRound(rounds, currentRound);
            }
        } else if (spareLastRound) {
            //Calculate spare
            if (currentRound == rounds.length - 1 && rounds[currentRound][0] != noOfPins) {
                if(scores[currentRound] == 0){
                    //If last round had a spare, calculate spare.
                    if(Arrays.stream(rounds[currentRound-1]).sum() == noOfPins && rounds[currentRound-1][0] != noOfPins){
                         scores[currentRound - 1] = scoreThisRound += (noOfPins + rounds[currentRound][0]);    
                    }
                    scores[currentRound] = scoreThisRound += noOfPins;
                    spareLastRound = false;
                }
            } else {
                scores[currentRound - 1] = scoreThisRound += (noOfPins + rounds[currentRound][0]);
                spareLastRound = false;
            }
        }
    }

    private void calculateStrikeLastRound(int[][] rounds, int currentRound) {
        scores[currentRound - 1] = scoreThisRound += (noOfPins + Arrays.stream(rounds[currentRound]).sum());
        strikeLastRound = false;
    }
}
