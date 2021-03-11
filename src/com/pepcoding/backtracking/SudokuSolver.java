package com.pepcoding.backtracking;

public class SudokuSolver {
    public static void main(String[] args) {
        int n = 9;
       //int[][] board = new int[n][n];
       int[][] board = {{5,3,0,0,7,0,0,0,0},{6,0,0,1,9,5,0,0,0},{0,9,8,0,0,0,0,6,0},{8,0,0,0,6,0,0,0,3},{4,0,0,8,0,3,0,0,1},{7,0,0,0,2,0,0,0,6},{0,6,0,0,0,0,2,8,0},{0,0,0,4,1,9,0,0,5},{0,0,0,0,8,0,0,7,9}};
        sudokuSolver(board, 0,0, n);

    }

    public static boolean sudokuSolver(int[][] board, int currRow, int currCol, int n) {
         if (currRow < n && currCol == n) {
            // move to next row and start col from 0
            currCol = 0;
            currRow++;

             if (currRow == n) { // all the rows and the columns have been filled up
                 for (int i = 0; i < n; i++) {
                     for (int j = 0; j < n; j++) {
                         if (j % 3 == 0)
                             System.out.print("\t");
                         System.out.print(" " + board[i][j]);
                     }
                     System.out.println("\n");
                 }
                 return true;
             }
        }
        // if the position is already filled up, call the next col
        if (board[currRow][currCol] != 0) {
           return sudokuSolver(board, currRow, currCol + 1, n);
        } else {
            for (int choice = 0; choice < 9; choice++) {
                board[currRow][currCol] = choice + 1; // trying the values 1 to 9
                if (isValid(board, currRow, currCol, n)) {
                    if(sudokuSolver(board, currRow, currCol + 1, n))
                        return true;
                }
                board[currRow][currCol] = 0; // removing the choice
            }
            return false;
        }
    }

    public static boolean isValid(int[][] board, int currRow, int currCol, int n) {
        // check in a col
        int choice = board[currRow][currCol];
        for (int row = 0; row < n; row++) {
            if(board[row][currCol] == choice && row!=currRow)
                return false;
        }

        // check in a row
        for (int col = 0; col < n; col++) {
            if(board[currRow][col] == choice && col!= currCol)
                return false;
        }

        int rowGridSt,colGridSt;
        rowGridSt = currRow - currRow % 3;
        colGridSt = currCol - currCol % 3;
        for (int i = rowGridSt; i < (rowGridSt+3); i++) {
            for (int j = colGridSt; j < (colGridSt+3); j++) {
                if(board[i][j] == choice && ( i != currRow && j != currCol))
                    return false;
            }
        }

        return true;
    }
}

/*
* Mistakes made by line no
* 1. when using isValid, its better to first check if the placement is valid, then place it, as more logic is required after placing
*    if(board[row][currCol] == choice && row!=currRow) --- extra row placement row!=currRow
* 2. keeping track of row no, when n-1 is increased to n, then ensure that (check is placed, else throws @link ArrayIndexOutOfBoundsException
* 3. use the choice properly, tie it to the input range
* 4. Sudoku is all about the Only 1 permutation out of like 9^m (m choices), so once you reach the base case just return true,
*   if you dont return from the function, it keeps exploring other options, which is not incorrect but it will cause StackOverFlowError
* */


/*concise solution --- https://leetcode.com/problems/sudoku-solver/discuss/599990/Backtracking-Concise-Solution-in-Java-(With-appropriate-Comments)
* class Solution {
public boolean canPlace(char mat[][], int i, int j, int n, char num) {
    // Row and Column check
    for (int x = 0; x < n; x++) {
        if (mat[x][j] == num || mat[i][x] == num) {   // look at how a single loop can handle the return condition
            return false;
        }
    }
    // Sub matrix check
    int rn = (int)Math.sqrt(n);
    int sx = (i/rn)*rn; // starting coordinate of sub-grid (abscissa)
    int sy = (j/rn)*rn; // starting coordinate of sub-grid (ordinate)
    for (int x = sx; x < sx+rn; x++) {
        for (int y = sy; y < sy+rn; y++) {
            if (mat[x][y] == num) {
                return false;
            }
        }
    }
    // if number is not found then we can place this num value
    return true;
}
public boolean solveSudoku(char mat[][], int i, int j,int n) {
    // Base Case
    if (i == n) return true;
    // Row End Case
    if (j == n) return solveSudoku(mat,i+1,0,n);  // how gracefully the edge case can be handled by calling the sudokusolver again from the beginning
    // Skip already filled matrix
    if (mat[i][j] != '.') return solveSudoku(mat,i,j+1,n);
    // Check for every Possible value, backtrack only when you get false from next steps
    for (int num = 1; num <= n; num++) {
        if (canPlace(mat,i,j,n,(char)(num+48))) {
            mat[i][j] = (char)(num+48);
            boolean couldWeSolve = solveSudoku(mat,i,j+1,n);
            if (couldWeSolve) {
                return true;
            }
        }
    }
    // Apply Backtracking
    mat[i][j] = '.';
    return false;
}
public void solveSudoku(char[][] board) {
    solveSudoku(board,0,0,9);
   }
 }*/