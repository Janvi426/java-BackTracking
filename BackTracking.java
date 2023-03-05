import java.io.FileNotFoundException;
import java.lang.module.FindException;

import javax.sound.sampled.ReverbType;

public class BackTracking {
    
    // print array
    public static void printArr(int arr[]) {
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 1 first fill empty array with 1 2 3 4 5 
    // then (-2) from all index while returning 
    public static void changeArr(int arr[], int i, int val){
        // base case
        if(i == arr.length){
            printArr(arr);
            return;
        }
        // recurtion
        arr[i] = val;
        changeArr(arr, i+1, val+1);   // function call
        arr[i] = arr[i] - 2;          // backTracking step
    }

    // 2 find subsets
    public static void findSubsets(String str, String ans, int i){
        // base case
        if(i == str.length()){
            System.out.println( "'" + ans + "'");
            return;
        }
        // yes choice
        findSubsets(str, ans+str.charAt(i), i+1);
        // No choice
        findSubsets(str, ans, i+1);
    }

    // 3 find permutations , possible arangement
    public static void findPermutation(String str, String ans){
        if(str.length() == 0){
            System.out.println(ans);
            return;
        }
        //recursion
        for(int i=0; i<str.length(); i++){
            char curr = str.charAt(i);
            // "abcd" --> "ab" + "d" = "abd" remove c 
            // remove character at i position
            String newStr = str.substring(0, i) + str.substring(i+1, str.length());
            findPermutation(newStr, ans+curr);
        }
    } 

    // 4 possible combination to put one queen in each row (nxn chess board)
    public static void nQueen(char board[][], int row){
        if(row == board.length){
            System.out.println("-----chess board----");
            printBoard(board);
            return;
        }
        for(int j=0; j<board.length; j++){
            board[row][j] = 'Q';   // Q = queen
            nQueens(board, row+1); // fun call
            board[row][j] = 'x';   // backTracking step - x = empty space
        }
    }

    // 5 put N queens on NxN board such as all are safe from each other
    public static void nQueens(char board[][], int row){
        if(row == board.length){
            System.out.println("-----chess board----");
            printBoard(board);
            //count++;
            return;
        }
        // colum loop
        for(int j=0; j<board.length; j++){
            if(isSafe(board, row, j)){
                board[row][j] = 'Q';   // Q = queen
                nQueens(board, row+1); // fun call
                board[row][j] = 'x';   // backTracking step - x = empty space
            }
        }
    }

    // 5 isSafe function for N Queen que
    public static boolean isSafe(char board[][], int row, int col){
        // vertical up
        for(int i=row-1; i>=0; i--){
            if(board[i][col] == 'Q'){
                return false;
            }
        }
        // left diagonal up
        for(int i=row-1, j=col-1; i>=0 && j>=0; i--, j--){
            if(board[i][j] == 'Q'){
                return false;
            }
        }
        // right diagonal up
        for(int i=row-1, j=col+1; i>=0 && j<board.length; i--, j++){
            if(board[i][j] == 'Q'){
                return false;
            }
        }
        return true;
    }

    // 6 Grid ways
    public static int gridWays(int i, int j, int n, int m) {
        if(i == n-1 && j == m-1){ // already at target
            return 1;
        } else if(i == n || j == m){ // out of boundry of array
            return 0;
        }
        int w1 = gridWays(i+1, j, n, m);
        int w2 = gridWays(i, j+1, n, m);
        return w1+w2;
    }

    // print board
    public static void printBoard(char board[][]){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board.length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 7 SUDOKU Solver 
    public static boolean sudokuSolver(int sudoku[][], int row, int col){
        // base case
        if(row == 9){
            return true;
        }
        // recursion 
        int nextRow = row, nextCol = col+1;
        if(col+1 == 9){
            nextRow = row+1;
            nextCol = 0;
        }
        if(sudoku[row][col] != 0){
            return sudokuSolver(sudoku, nextRow, nextCol);
        }
        for(int digit=1; digit<=9; digit++){
            if(IsSafe(sudoku, row, col, digit)){
                sudoku[row][col] = digit;
                if(sudokuSolver(sudoku, nextRow, nextCol)){
                    return true;
                }
                sudoku[row][col] = 0;
            }
        }
        return false;
    }

    // 7 Sudoku solver IsSafe function
    public static boolean IsSafe(int sudoku[][], int row, int col, int digit){
        // check column
        for(int i=0; i<9; i++){
            if(sudoku[i][col] == digit){
                return false;
            }
        }
        // check row
        for(int j=0; j<9; j++){
            if(sudoku[row][j] == digit){
                return false;
            }
        }
        // check in grid
        int sr = (row/3) * 3;
        int sc = (col/3) * 3;
        for(int i=sr; i<sr+3; i++){
            for(int j=sc; j<sc+3; j++){
                if(sudoku[i][j] == digit){
                    return false;
                }
            }
        }
        return true;
    }

    // 7 Print sudoku
    public static void printSudoku(int sudoku[][]){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    //static int count = 0;
    public static void main(String...arg) {
        
        // 1
        // int arr[] = new int[5];
        // changeArr(arr, 0, 1);
        // printArr(arr);

        // 2 
        // String str = "abc";
        //findSubsets(str, "", 0);

        // 3
        //findPermutation(str, "");

        // 4 
        // int n = 2;
        // char board[][] = new char[n][n];
        // // initialize
        // for(int i=0; i<n; i++){
        //     for(int j=0; j<n; j++){
        //         board[i][j] = 'x';
        //     }
        // }
        //nQueen(board, 0);

        // 5
        // int n = 4;  // o/p = 2 chess board for n=5 o/p = 10 cb
        // char board[][] = new char[n][n];
        // // initialize
        // for(int i=0; i<n; i++){
        //     for(int j=0; j<n; j++){
        //         board[i][j] = 'x';
        //     }
        // } 
        // nQueens(board, 0);
        //System.out.println("count " + count);

        // 6
        // int n = 3, m = 3;
        // System.out.print("Total ways to reach target : ");
        // System.out.println(gridWays(0, 0, n, m));

        // 7
        int sudoku[][] = {{0, 0, 8, 0, 0, 0, 0, 0, 0},
                          {4, 9, 0, 1, 5, 7, 0, 0, 2},
                          {0, 0, 3, 0, 0, 4, 1, 9, 0},
                          {1, 8, 5, 0, 6, 0, 0, 2, 0},
                          {0, 0, 0, 0, 2, 0, 0, 6, 0},
                          {9, 6, 0, 4, 0, 5, 3, 0, 0},
                          {0, 3, 0, 0, 7, 2, 0, 0, 4},
                          {0, 4, 9, 0, 3, 0, 0, 5, 7},
                          {8, 2, 7, 0, 0, 9, 0, 1, 3}};
        if(sudokuSolver(sudoku, 0, 0)){
            System.out.println("Solution exist");
            System.out.println("==================");
            printSudoku(sudoku);
        } else{
            System.out.println("Solution doesn't exist");
        }
    } 
}
