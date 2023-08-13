package MonteCarloMini;

import java.util.concurrent.*;

public class SearchParallel extends RecursiveTask<Integer[]> {

    private int low, high;
    private Search[] searches;
    private final int THRESHOlD = 10000;
    private final Integer[] results = new Integer[2];
    // Constructor

    public SearchParallel(int low, int high, Search[] searches) {
        this.high = high;
        this.low = low;
        this.searches = searches;
    }

    public Integer[] perform() {
        int min = Integer.MAX_VALUE;
        int local_min = Integer.MAX_VALUE;
        int finder = -1;

        for (int i = low; i < high; i++) {
            local_min = searches[i].find_valleys();
            if ((!searches[i].isStopped()) && (local_min < min)) { // don't look at those who stopped because                                                      // hit exisiting path
                min = local_min;
                finder = i; // keep track of who found it
            }// end timer
        }

        results[0] = min;
        results[1] = finder;

        return results;
    }

    protected Integer[] compute() {

        // below the threshold
        if (high - low <= THRESHOlD) {
            return perform(); 
        }
        else {
            int mid = (high - low) / 2;
            SearchParallel left = new SearchParallel(low, mid, searches);
            SearchParallel right = new SearchParallel(mid, high, searches);
            left.fork();
            Integer[] rightRes = right.compute();
            Integer[] leftRes = left.join();


            if (leftRes[0] >= rightRes[0]) {
                return rightRes;
            }
            return leftRes;
        }
    } 
}
