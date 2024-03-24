package org.tetra.topsis;
import java.util.ArrayList;
import java.lang.Math;
/**
 * Class implementing the TOPSIS (Technique for Order of Preference by Similarity to Ideal Solution) algorithm.
 * 
 * TOPSIS is a multi-criteria decision-making method used to determine the best alternative among a set of alternatives,
 * based on their similarity to ideal and anti-ideal solutions.
 * 
 * @author Sri Vibhu Paruchuri
 *
 */
public class TopsisHelper {
    
    // computes the denominator for the vector normalization method
    public double vectorNormalizationHelper(ArrayList<ArrayList<Double>> matrix,int idx)
    {
        double total = 0;

        for (int i = 0; i < matrix.size(); i ++)
        {
            double value = matrix.get(i).get(idx);
            total += value * value;

        }

        return Math.sqrt(total);

    }

    // computes the normalized matrix
    public void vectorNormalization(ArrayList<ArrayList<Double>> matrix)
    {

        int attributeSize = matrix.get(0).size();

        double [] denominator = new double[attributeSize];

        for (int i = 0; i < attributeSize; i ++)
        {
            denominator[i] = vectorNormalizationHelper(matrix, i);
        }

        for ( int i = 0; i < matrix.size(); i ++ )
        {
            for ( int j = 0 ; j < attributeSize; j ++)
            {
                double performanceScore = matrix.get(i).get(j);

                // calculate the new performance score

                performanceScore /= denominator[j];

                matrix.get(i).set(j,performanceScore);


            }
        }

    }

    // computes the weighted normalized matrix
    public void weightedNormalization(ArrayList<ArrayList<Double>> matrix, ArrayList<Double> weights)
    {

        int attributeSize = matrix.get(0).size();

        for ( int i = 0; i < matrix.size(); i ++ )
        {
            for ( int j = 0 ; j < attributeSize; j ++)
            {
                double performanceScore = matrix.get(i).get(j);

                // calcualte the new performance score

                performanceScore *= weights.get(j);

                matrix.get(i).set(j,performanceScore);


            }
        }
    }

    // computes the ideal best for each attribute
    public void computeIdealBest(ArrayList<ArrayList<Double>> matrix,ArrayList<Integer> beneficial,ArrayList<Double> idealBest)
    {

        int attributeSize = matrix.get(0).size();

        for ( int i = 0; i < attributeSize; i ++ )
        {
            // Case 1 - Non-beneficial Attribute - Minimum value is the ideal best
            if (beneficial.get(i).compareTo(0) == 0)
            {
                double idealBestValue = Double.MAX_VALUE;
                for ( int j = 0 ; j < matrix.size(); j ++)
                {

                    if (matrix.get(j).get(i) < idealBestValue)
                    {
                        idealBestValue = matrix.get(j).get(i);
                    }
    
                }

                idealBest.set(i,idealBestValue);
            }
            // Case 2 - Beneficial Attribute - Maximum value is the ideal best
            else
            {
                double idealBestValue = Double.MIN_VALUE;
                for ( int j = 0 ; j < matrix.size(); j ++)
                {

                    if (matrix.get(j).get(i) > idealBestValue)
                    {
                        idealBestValue = matrix.get(j).get(i);
                    }

                }

                idealBest.set(i,idealBestValue);
            }

        }
    }

    // computes the ideal worst for each attribute
    public void computeIdealWorst(ArrayList<ArrayList<Double>> matrix,ArrayList<Integer> beneficial,ArrayList<Double> idealWorst)
    {
        int attributeSize = matrix.get(0).size();

        for ( int i = 0; i < attributeSize; i ++ )
        {
            // Case 1 - Non-beneficial Attribute - Maximum value is the ideal worst
            if (beneficial.get(i).compareTo(0) == 0)
            {
                double idealWorstValue = Double.MIN_VALUE;
                for ( int j = 0 ; j < matrix.size(); j ++)
                {

                    if (matrix.get(j).get(i) > idealWorstValue)
                    {
                        idealWorstValue = matrix.get(j).get(i);
                    }
    
                }

                idealWorst.set(i,idealWorstValue);
            }
            // Case 2 - Beneficial Attribute - Minimum value is the ideal worst
            else
            {
                double idealWorstValue = Double.MAX_VALUE;
                for ( int j = 0 ; j < matrix.size(); j ++)
                {

                    if (matrix.get(j).get(i) < idealWorstValue)
                    {
                        idealWorstValue = matrix.get(j).get(i);
                    }

                }

                idealWorst.set(i,idealWorstValue);
            }

        }
    }

    // computes the euclidean distance for each alternative from the ideal best
    public void computeEuclideanDistanceBest(ArrayList<ArrayList<Double>> matrix,ArrayList<Double> idealBest,ArrayList<Double> euclideanDistanceBest)
    {
        int attributeSize = matrix.get(0).size();

        for ( int i = 0; i < matrix.size(); i ++ )
        {
            for ( int j = 0 ; j < attributeSize; j ++)
            {
                double tempValue = matrix.get(i).get(j) - idealBest.get(j);

                tempValue *= tempValue;

                tempValue += euclideanDistanceBest.get(i);
                euclideanDistanceBest.set(i,tempValue);
            }

            euclideanDistanceBest.set(i,Math.sqrt(euclideanDistanceBest.get(i)));
        }
    }

    // computes the euclidean distance for each alternative from the ideal worst
    public void computeEuclideanDistanceWorst(ArrayList<ArrayList<Double>> matrix,ArrayList<Double> idealWorst,ArrayList<Double> euclideanDistanceWorst)
    {
        int attributeSize = matrix.get(0).size();

        for ( int i = 0; i < matrix.size(); i ++ )
        {
            for ( int j = 0 ; j < attributeSize; j ++)
            {
                double tempValue = matrix.get(i).get(j) - idealWorst.get(j);

                tempValue *= tempValue;
                tempValue += euclideanDistanceWorst.get(i);
                euclideanDistanceWorst.set(i,tempValue);
            }

            euclideanDistanceWorst.set(i,Math.sqrt(euclideanDistanceWorst.get(i)));
        }
    }

    public void computePerformanceScore( ArrayList<Double> euclideanDistanceBest, ArrayList<Double> euclideanDistanceWorst,  ArrayList<Double> performanceScore)
    {
        for (int i = 0; i < euclideanDistanceBest.size(); i ++)
        {
            double tempDenominator = euclideanDistanceBest.get(i) + euclideanDistanceWorst.get(i);

            performanceScore.set(i,euclideanDistanceWorst.get(i)/tempDenominator);

        }
    }
    // computes the score for each alternative using the TOPSIS algorithm 
    public ArrayList<Double> computeTopsisScore(ArrayList<ArrayList<Double>> matrix,ArrayList<Double> weights,ArrayList<Integer> beneficial )
    {

        // Step 1 - Vector Normalization
        vectorNormalization(matrix);

        // Step 2 - Weighted Vector Normalizaiton
        weightedNormalization(matrix, weights);

        // Step 3 - Calculate the ideal best and ideal worst for each attribute

        ArrayList<Double> idealBest = new ArrayList<Double> ();
        ArrayList<Double> idealWorst = new ArrayList<Double> ();

        for ( int i = 0; i < weights.size(); i++)
        {
            idealBest.add(0.0);
            idealWorst.add(0.0);
        }
        computeIdealBest(matrix,beneficial,idealBest);
        computeIdealWorst(matrix,beneficial,idealWorst);

        // Step 4 - Calculate the euclidean distance from the ideal best and ideal worst 

        ArrayList<Double> euclideanDistanceBest = new ArrayList<Double> ();
        ArrayList<Double> euclideanDistanceWorst = new ArrayList<Double> ();

        for ( int i = 0; i < matrix.size(); i++)
        {
            euclideanDistanceBest.add(0.0);
            euclideanDistanceWorst.add(0.0);
        }

        computeEuclideanDistanceBest(matrix,idealBest,euclideanDistanceBest);
        computeEuclideanDistanceWorst(matrix,idealWorst,euclideanDistanceWorst);

        // Step 5 - Calculate the performance score

        ArrayList<Double> performanceScore = new ArrayList<Double> ();

        for ( int i = 0; i < matrix.size(); i++)
        {
            performanceScore.add(0.0);
        }
        computePerformanceScore(euclideanDistanceBest,euclideanDistanceWorst, performanceScore);

        // return the performance score of all the alternatives
        return performanceScore;
        
    }
}
