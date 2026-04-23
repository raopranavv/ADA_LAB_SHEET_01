#0/1 Knapsack problem 
public class Knapsack01 {

    public static int knapsack(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]],
                                        dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] wt = {1, 3, 4, 5};
        int[] val = {1, 4, 5, 7};
        int W = 7;

        System.out.println(knapsack(W, wt, val, wt.length));
    }
}

#fractional knapsack problem 
import java.util.*;

class Item {
    int weight, value;

    Item(int w, int v) {
        weight = w;
        value = v;
    }
}

public class FractionalKnapsack {

    public static double fractionalKnapsack(int W, List<Item> items) {
        items.sort((a, b) -> Double.compare((double)b.value / b.weight,
                                            (double)a.value / a.weight));

        double total = 0.0;

        for (Item item : items) {
            if (W >= item.weight) {
                W -= item.weight;
                total += item.value;
            } else {
                total += item.value * ((double) W / item.weight);
                break;
            }
        }

        return total;
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(10, 60));
        items.add(new Item(20, 100));
        items.add(new Item(30, 120));

        System.out.println(fractionalKnapsack(50, items));
    }
}


#matrix multiplication problem 
public class MatrixMultiplication {

    public static int[][] multiply(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};

        int[][] result = multiply(A, B);

        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
