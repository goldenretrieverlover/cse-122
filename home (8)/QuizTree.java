import java.util.*;
import java.io.*;

public class QuizTree {
    private Scanner fileScanner;
    private QuizTreeNode root;

    public QuizTree(Scanner fileScanner) {
         
        this.fileScanner = fileScanner;

        root = quizHelper(fileScanner);
    }

    private QuizTreeNode quizHelper(Scanner fileScanner) {
        String nextLine = fileScanner.nextLine();
        if (nextLine.startsWith("END:")) { // base case is 
            String[] arrOfStr = nextLine.split("-");
            String result = arrOfStr[0];
            String numericalResult = arrOfStr[1]; // magic number?
            int score = Integer.parseInt(numericalResult);
            QuizTreeNode root = new QuizTreeNode(result);
            root.setScore(score); // reads page L to R, T to B
            // base case is the end
            // if you have red or blue you need to build up 
            // your left and your right subtree until you get to end
            // if only you had a method that would build up a 
            // subtree given a scanner
            // returns a quiz tree node
            // quiz tree method, return left subchild, right subchild
        } else {
           // 2 ways of thinking about problem
            // 1 store both choices and the score within the node
            // or 2 i will store this one node and deal with splitting it later
            String[] arrOfStr = nextLine.split("/");
            String option1 = arrOfStr[0]; // 0 for left
            String option2 = arrOfStr[1]; // 1 for right
            String[] arrOption2 = option2.split("-");
            String actualOption2 = arrOption2[0];
            int score = Integer.parseInt(arrOption2[1]);

            QuizTreeNode aNode = new QuizTreeNode(option1);
            aNode.setScore(score); // what is equivalent?
            aNode.left = quizHelper(fileScanner);
            aNode.right = quizHelper(fileScanner);

            // if (type == 1 || type == 3) { // starting from scratch
                
            // }
            // if (type == 2 || type == 3) {
                
            // }
            
            // if (type == 1 || type == 3) { // starting from scratch
            //     root.left = writeTreeHelper(input);
            // }
            // if (type == 2 || type == 3) {
            //     root.right = writeTreeHelper(input);
            // }
        }
    return root;


            
            // confused about sort of how to make nodes and if i'm on the right track
            // confused about, what is the equivalent of left node and right node?
            // confused about, magic number check? 


            // for (String part : arrOfStr) {
                // System.out.println(part);
                // substring: +1 = label
                // substring - +1 
                // create node
                // then not in base case
            
            // }
        

    }

        // IntTreeNode overallRoot = new IntTreeNode();
        // overallRoot = quizHelper(overallRoot, fileScanner);
        // String data = fileScanner.next(); 
        // // // parse for word?
        // int type = input.nextInt();
        // IntTreeNode root = new IntTreeNode(data); // starting from scratch
        // if (type == 1 || type == 3) {
        //     root.left = writeTreeHelper(input);
        // }
        // if (type == 2 || type == 3) {
        //     root.right = writeTreeHelper(input);
        // }
        // return root;
        // // make a quiz decision binary search tree
        // how to make ? 

        // writeTree

    public void takeQuiz(Scanner console) {
        System.out.println("Do you prefer red or blue? ");
        String colorA = console.next();
        if (/* */.contains()) {
            
        }
        // System.out.println("Do you prefer yellow or green? ");
        // String colorB = console.next();
        // System.out.println("Your result is: ");

        // System.out.println("Your score is: ");

        // overallRoot = limitPathSum(overallRoot, max, 0);
    }

    // private IntTreeNode limitPathSum(IntTreeNode root, int max, int current) {
    //     if (root != null) {
    //         current += root.data;
    //         if (current > max) {
    //             root = null;
    //         } else {
    //             root.left = limitPathSum(root.left, max, current);
    //             root.right = limitPathSum(root.right, max, current);
    //         }
    //     }
    //     return root;
    // }

    
        // int result = Integer.parseInt(String s); // trees are basically more complicated linkedlists

        // if (!next.equals("/" && !next.equals("-") && !next.equals("END:"))) {
        //     root.left = quizHelper(root.left, console);
        // }
        // String nextRight = console.next();
        // if (!nextRight.equals("/" && !nextRight.equals("-") && !nextRight.equals("END:"))) {
        //     root.right = quizHelper(root.right, console);
        // }


    public void creativeExtension() {

    }

    public void addQuestion(String toReplace, String choices, 
                            String leftResult, String rightResult) {

    }

    public void export(PrintStream outFile) {
        // export(overallRoot, outFile);
    }

    // private void readTree(IntTreeNode root) {
        // if (root != null) {
        //     int type = 0;
        //     if (root.left != null) {
        //         type++;
        //     }
        //     if (root.right != null) {
        //         type += 2;
        //     }

        //     System.out.println(type + " " + root.data);

        //     readTree(root.left);
        //     readTree(root.right);
        // }
    // }

    
    // PROVIDED
    // Returns the given percent rounded to two decimal places.
    private double roundTwoPlaces(double percent) {
        return (double) Math.round(percent * 100) / 100;
    }


    private static class QuizTreeNode {
        public String data;
        public QuizTreeNode left;
        public QuizTreeNode right;
        public int score;

        public QuizTreeNode() {
            this("", null, null);
        }

        public QuizTreeNode(String data) {
            this(data, null, null);
        }

        public QuizTreeNode(String data, QuizTreeNode left, QuizTreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public void setScore(int score) {
            this.score = score;
        }

    }
}   

