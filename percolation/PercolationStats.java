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

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException("'n' <= 0 or trials <= 0");
        }
        Stopwatch stopwatch = new Stopwatch();
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            p.main(null);
        }
        stopwatch.elapsedTime();
    }

    // sample mean of percolation threshold
    public double mean() {
        return 0.0;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return 0.0;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.0;
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        Percolation p = new Percolation(10);
        System.out.println(String.format("mean\t\t\t\t\t = %f", percolationStats.mean()));
        System.out.println(String.format("stddev\t\t\t\t\t = %f", percolationStats.stddev()));
        System.out.println(String.format("95%% confidence interval\t = [%f, %f]",
                                         percolationStats.confidenceLo(), percolationStats.confidenceHi()));
    }
}
