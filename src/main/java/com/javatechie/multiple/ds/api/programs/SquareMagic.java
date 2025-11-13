package com.javatechie.multiple.ds.api.programs;

public class SquareMagic {

    private static boolean checkMagicSquare(int[][] arr) {
        int rowSum, colSum;
        int primaryDiagonalSum = 0, secondaryDiagonalSum = 0;
        for (int i = 0; i < arr.length; i++) {
            primaryDiagonalSum += arr[i][i];
            secondaryDiagonalSum += arr[i][arr.length - 1];
        }
        System.out.println("primaryDiagonalSum = " + primaryDiagonalSum);
        System.out.println("secondaryDiagonalSum = " + secondaryDiagonalSum);
        if (primaryDiagonalSum != secondaryDiagonalSum)
            return false;

        for (int i = 0; i < arr.length; i++) {
            rowSum = 0;
            colSum = 0;
            for (int j = 0; j < arr.length; j++) {
                rowSum += arr[i][j];
                colSum += arr[j][i];
            }
            System.out.println("rowSum = " + rowSum);
            System.out.println("colSum = " + colSum);
            if (rowSum != colSum || rowSum != primaryDiagonalSum)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {

        int[][] arr = {
                {2, 7, 6},
                {9, 5, 1},
                {4, 3, 8}
        };
        //0,2   1,1   2,0

        //0,0  1,0  ,2,0
        String result = checkMagicSquare(arr) ? "Magic Square" : "NOT Magic Square";
        System.out.println("Is magic square ? " + result);
    }


}
