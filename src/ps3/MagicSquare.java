package ps3;
/*
 * MagicSquare.java
 *
 * Computer Science E-22
 *
 * Modified by: <Monica Suresh>, <monicasuresh45@gmail.com>
 */

import java.util.*;

public class MagicSquare {
    // the current contents of the cells of the puzzle values[r][c]
    // gives the value in the cell at row r, column c
    private int[][] values;

    // the order (i.e., the dimension) of the puzzle
    private int order;
    private int[] rowSum;
    private int[] colSum;
    private int magicSum;
    private boolean[] isNumberTakenArray;

    /*
     * Creates a MagicSquare object for a puzzle with the specified
     * dimension/order.
     */
    public MagicSquare(int order) {
        this.values = new int[order][order];
        this.order = order;

        // Add code to this constructor as needed to initialize
        // the fields that you add to the object.
        this.rowSum = new int[order];
        this.colSum = new int[order];
        this.magicSum = (int) (Math.pow(order, 3) + order) / 2;
        this.isNumberTakenArray = new boolean[order * order];
    }

    /*
    This method places the number into the specified square indicated by the row and column numbers
     */

    private void placeNumber(int row, int col, int number) {
        this.values[row][col] = number;
        this.rowSum[row] += number;
        this.colSum[col] += number;
    }

    /*
    This method removes the number from the specified square indicated by the row and column numbers
     */

    private void removeNumber(int row, int col, int number) {
        this.values[row][col] = 0;
        this.rowSum[row] -= number;
        this.colSum[col] -= number;
    }

    /*
    This method returns false if the sum of the row or column is not equal to the magic sum and true if the sum
    of the row and column is equal to the magic sum
     */

    private boolean isValid(int row, int col, int number) {
        if ((col == this.order - 1) && (this.rowSum[row] + number != this.magicSum))  {
            return false;
        } else if ((row == this.order - 1) && (this.colSum[col] + number != this.magicSum)) {
            return false;
        } else if (this.rowSum[row] + number > this.magicSum) {
            return false;
        } else if (this.colSum[col] + number > this.magicSum) {
            return false;
        }

        return true;
    }

    /*
     * This method should call the separate recursive-backtracking method
     * that you will write, passing it the appropriate initial parameter(s).
     * It should return true if a solution is found, and false otherwise.
     */
    public boolean solve() {
        // Replace the line below with your implementation of this method.
        // REMEMBER: The recursive-backtracking code should NOT go here.
        // See the comments above.
        return solve(0, 0, true);
    }

    /*
    This method uses recursive backtracking to fill a magic square of the specified order. The fills the magic square
    with numbers from 1 to order * order. It cycles through the list of numbers and checks to see if the number is not
    already used. It then checks to see if the number is valid. If the number is valid the method places the number
    in the specified row and column. If the method reaches a dead-end, it backtracks and removes the number from the
    square
     */

    public boolean solve(int row, int column, boolean isFillRow) {
        if (row == this.order){
            return true;
        }

        for (int number = 1; number <= this.isNumberTakenArray.length; number++) {
            if (isNumberTakenArray[number - 1] == false) {

                // Checks to see if the number is valid and if it's valid places it into the square
                if (this.isValid(row, column, number)) {
                    this.placeNumber(row, column, number);
                    this.isNumberTakenArray[number - 1] = true;

                    /*
                    Checks to see if the program has reached the end of a row or column. If the program has reached
                    the end of a row or column it moves on to the next row or column depending on the isFillRow variable.
                    Otherwise it proceeds with filling the current row or column.
                     */

                    int nextColumn = column;
                    int nextRow = row;

                    if (isFillRow) {
                        nextColumn = column + 1;
                        nextRow = row;
                        if (nextColumn == this.order) {
                            nextColumn = row;
                            nextRow = nextRow + 1;
                            isFillRow = false;
                        }
                    } else {
                        nextColumn = column;
                        nextRow = row + 1;
                        if (nextRow == this.order) {
                            nextColumn = nextColumn + 1;
                            nextRow = nextColumn;
                            isFillRow = true;
                        }
                    }

                    if (this.solve(nextRow, nextColumn, isFillRow)) {
                        return true;
                    }

                    this.removeNumber(row, column, number);
                    this.isNumberTakenArray[number - 1] = false;
                }
            }
        }
        return false;
    }

    /*
     * Displays the current state of the puzzle.
     * You should not change this method.
     */
    public void display() {
        for (int r = 0; r < order; r++) {
            printRowSeparator();
            for (int c = 0; c < order; c++) {
                System.out.print("|");
                if (values[r][c] == 0) {
                    System.out.print("   ");
                } else {
                    if (values[r][c] < 10) {
                        System.out.print(" ");
                    }
                    System.out.print(" " + values[r][c] + " ");
                }
            }
            System.out.println("|");
        }
        printRowSeparator();
    }

    // A private helper method used by display()
    // to print a line separating two rows of the puzzle.
    private void printRowSeparator() {
        for (int i = 0; i < order; i++) {
            System.out.print("-----");
        }
        System.out.println("-");
    }

    public static void main(String[] args) {
        /*
         * You should NOT change any code in this method
         */

        Scanner console = new Scanner(System.in);
        System.out.print("What order Magic Square? ");
        int order = console.nextInt();

        MagicSquare puzzle = new MagicSquare(order);
        if (puzzle.solve()) {
            System.out.println("Here's the solution:");
            puzzle.display();
        } else {
            System.out.println("No solution found.");
        }
    }
}