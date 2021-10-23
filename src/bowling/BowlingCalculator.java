/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bowling;

import Calculators.PointCalculator;
import DataProviders.DataProvider;
import java.util.Arrays;

/**
 *
 * @author Marti
 */
public class BowlingCalculator {

    public static void main(String[] args) {
        DataProvider dataProvider = new DataProvider();
        var round = dataProvider.getRound();
       
        System.out.println(Arrays.deepToString(round));
        
        PointCalculator calculator = new PointCalculator();
        var scores = calculator.calculateScores(round);
        
        System.out.print(Arrays.toString(scores));
    }
    
}
