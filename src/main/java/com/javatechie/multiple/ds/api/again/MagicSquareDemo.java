package com.javatechie.multiple.ds.api.again;

public class MagicSquareDemo {

    private static boolean checkMagicSquare(int[][] valid) {

        //diagonal
        int primDiagonalSum = 0, secondDiagonalSum = 0;
        for (int i = 0; i < valid.length; i++) {
            primDiagonalSum += valid[i][i];
            secondDiagonalSum += valid[i][valid.length - i - 1];
        }
        System.out.println("primDiagonalSum = " + primDiagonalSum);
        if (primDiagonalSum != secondDiagonalSum)
            return false;

        for (int i = 0; i < valid.length; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < valid.length; j++) {
                rowSum += valid[i][j];
                colSum += valid[j][i];
            }
            if (rowSum != primDiagonalSum || rowSum != colSum)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {

        int[][] valid = {
                {2, 7, 6},
                {9, 5, 1},
                {4, 3, 8}
        };

        int[][] invalid = {
                {4, 9, 2},
                {3, 5, 7},
                {8, 2/*1*/, 6}
        };

        String result = checkMagicSquare(valid) ? "Magic square" : "Not Magic square";
        System.out.println("result = " + result);

        //🧩 1️⃣ valid.length
        //
        //➡️ Gives the number of rows in the 2D array.
        //
        //Here:
        //
        //System.out.println(valid.length); // 3
        //
        //
        //There are 3 rows:
        //
        //Row 0 → {2, 7, 6}
        //Row 1 → {9, 5, 1}
        //Row 2 → {4, 3, 8}
        //
        //🧩 2️⃣ valid[0].length
        //
        //➡️ Gives the number of columns in the first row (i.e., the “width”).
        //
        //Here:
        //
        //System.out.println(valid[0].length); // 3
        //
        //
        //Each row has 3 columns.
        //
        //🧠 Summary
        //Expression	Meaning	Value in this example
        //valid.length	Number of rows	3
        //valid[0].length	Number of columns in row 0	3
        //valid[i][j]	Element at row i, column j	e.g., valid[1][2] = 1
        //⚙️ Optional: Iterate all elements
        //for (int i = 0; i < valid.length; i++) {             // rows
        //    for (int j = 0; j < valid[i].length; j++) {      // columns
        //        System.out.print(valid[i][j] + " ");
        //    }
        //    System.out.println();
        //}
        //
        //
        //✅ Output:
        //
        //2 7 6
        //9 5 1
        //4 3 8
        //
        //
        //✅ In short:
        //
        //valid.length → number of rows (height)
        //valid[0].length → number of columns (width)
    }


}
