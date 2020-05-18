/* *****************************************************************************
 *  Name:    Peng
 *  NetID:   NAN
 *  Precept: P00
 *
 *  Description:  Description:  Implement the Percolation data type using the weighted quick
 *      union algorithm in WeightedQuickUnionUF
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF quickFindUF;
    private boolean[] openSites;
    private static int[] blockedSites;
    private static int squareN;
    public static double threshold = 0.0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        squareN = n;
        if(n <= 0) throw new IllegalArgumentException("'n' cannot be less than 0");
        int num = n * n;

        quickFindUF = new WeightedQuickUnionUF(num);
        openSites = new boolean[num];
        blockedSites = new int[num];

        for (int i = 0; i < num; i++) {
            openSites[i] = false;
            blockedSites[i] = i;
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int myself = row*squareN + col;
        openSites[myself] = true;
        delete(myself, blockedSites);
        //connect it to all of its adjacent open sites.
        //up to 4 calls to union()
        //up
        int x = row-1;
        int y = col;
        if (0 <= x && x < squareN && 0 <= y && y < squareN && openSites[x*squareN + y]) {
            quickFindUF.union(myself, x*squareN + y);
        }

        //right
        x = row;
        y = col+1;
        if (0 <= x && x < squareN && 0 <= y && y < squareN && openSites[x*squareN + y]) {
            quickFindUF.union(myself, x*squareN + y);
        }

        //down
        x = row+1;
        y = col;
        if (0 <= x && x < squareN && 0 <= y && y < squareN && openSites[x*squareN + y]) {
            quickFindUF.union(myself, x*squareN + y);
        }

        //left
        x = row;
        y = col-1;
        if (0 <= x && x < squareN && 0 <= y && y < squareN && openSites[x*squareN + y]) {
            quickFindUF.union(myself, x*squareN + y);
        }
    }

    // delete one element from a array
    private int[] delete(int index, int array[]) {
        //数组的删除其实就是覆盖前一位
        int[] arrNew = new int[array.length - 1];
        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        System.arraycopy(array, 0, arrNew, 0, arrNew.length);
        return arrNew;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        // quickFindUF.connected() Replace with two calls to {@link #find(int)}.
        return openSites[row*squareN + col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return quickFindUF.count()==1;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return quickFindUF.count();
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i=0; i < squareN; i++) {
            for (int j = squareN*squareN-squareN; j < squareN*squareN; j++) {
                if (quickFindUF.find(i) ==  quickFindUF.find(j)) {
                    return true;
                }
            }
        }

        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        //System.out.println("Percolation.main()");

        //squareN = 10;  //  StdIn.readInt();
        int count = 0;
        // Initialize all sites to be blocked.
        Percolation percolation = new Percolation(squareN);

        // int p = (int) (StdRandom.uniform(0, N));
        // int q = (int) (StdRandom.uniform(0,  N));

        // Repeat the following until the system percolates:
        while (!percolation.percolates()) {
            //      Choose a site uniformly at random among all blocked sites.
            int length = (StdRandom.uniform(0, blockedSites.length));
            int p = length / squareN;
            int q = length % squareN;

            // int p = StdIn.readInt();
            // int q = StdIn.readInt();

            //      Open the site.oppo
                if (!percolation.isOpen(p, q)) {
                    percolation.open(p, q);
                    count++;
                    //StdOut.println("open " + p + " " + q + ", count = " + percolation.quickFindUF.count());
                }
        }
        //StdOut.println("count = " + count);
        threshold =  (double)count/(squareN*squareN);
        StdOut.println("threshold =" + threshold);
    }

    /**
     * getThreshold
     * @return
     */
    public double getThreshold() {
        return threshold;
    }
}
