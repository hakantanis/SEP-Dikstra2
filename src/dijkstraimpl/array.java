package dijkstraimpl;

public class array {



    public static void main(String[] args) {

        int[][] matrix = new int[26][10]; // Erstellung der Matrix , evtl boolean?!


        matrix[0][1] = 0;
        matrix[0][2] = 0;
        matrix[0][3] = 0;
        matrix[0][4] = 0;
        matrix[0][5] = 2;
        matrix[1][2] = 3;
        matrix[3][8] = 5;
        matrix[4][0] = 1;
        //Werte auslesen


        System.out.print(matrix[0][1] +  " ");
        System.out.print(matrix[0][2] +  " ");
        System.out.print(matrix[0][3] +  " ");
        System.out.print(matrix[0][4] +  " ");
        System.out.println(matrix[0][5] +  " ");
        System.out.println(matrix[1][2]);
        System.out.println(matrix[3][8]);
        System.out.println(matrix[4][0]);

    }
}
