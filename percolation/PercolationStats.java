/* *****************************************************************************
 *  Name:    Peng
 *  NetID:   NAN
 *  Precept: P00
 *
 *  Description:  PercolationStats
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private double[] thresholdArray;

    private double time = 0.0;

    private double total = 0.0;

    private int t;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException("'n' <= 0 or trials <= 0");
        }
        t = trials;
        thresholdArray = new double[trials];

        // perform independent trials
        Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            p.main(null);
            thresholdArray[i] = p.getThreshold();
            total += p.getThreshold();
        }
        time = stopwatch.elapsedTime();
    }

    // sample mean of percolation threshold
    public double mean() {
        return total / t;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        double standardDeviation = 0.0;
        int length = thresholdArray.length;

        for(double num: thresholdArray) {
            standardDeviation += Math.pow(num - mean(), 2);
        }

        return Math.sqrt(standardDeviation/length);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean()-(1.96*stddev()/Math.sqrt(t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean()+(1.96*stddev()/Math.sqrt(t));
    }

    // test client (see below)
    public static void main(String[] args) {
        Integer n = Integer.valueOf(args[0]);
        Integer trials = Integer.valueOf(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);

        // print results
        System.out.println(String.format("mean\t\t\t\t\t = %f", percolationStats.mean()));
        System.out.println(String.format("stddev\t\t\t\t\t = %f", percolationStats.stddev()));
        System.out.println(String.format("95%% confidence interval\t = [%f, %f]",
                                         percolationStats.confidenceLo(), percolationStats.confidenceHi()));
    }
}
