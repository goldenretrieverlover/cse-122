// Name: Ollie Le 
// Class: CSE 123
// TA: James Try 
// Assignment: BrunelleFeed Quiz
// Class header: This class represents a quiz where it is possible to upload a file, export
import java.util.*;
import java.io.*;

public class QuizTree {
    private QuizTreeNode root;
    private int overallScore;

    public QuizTree(Scanner fileScanner) {
        root = quizHelper(fileScanner);
    }

// This returns a QuizTreeNode given a file scanner
    private QuizTreeNode quizHelper(Scanner fileScanner) {
        // String next = "";
        // if (fileScanner.hasNextLine()) {
        //     next = fileScanner.nextLine();
        // }
        String next = fileScanner.nextLine();
        // next = next.substring(4);
        String[] arrOfStr = next.split("-");
        String result = arrOfStr[0];
        String numericalResult = arrOfStr[1]; // magic number?
        int score = Integer.parseInt(numericalResult);
        QuizTreeNode root = new QuizTreeNode(result, score);
        if (!next.startsWith("END:")) {  
            String overallOption = next;
            root.left = quizHelper(fileScanner);
            root.right = quizHelper(fileScanner);
            // String[] arrOfStr = next.split("-");
            // String overallOption = arrOfStr[0];
            // String numericalResult = arrOfStr[1];
            // int score = Integer.parseInt(numericalResult);

            // QuizTreeNode newNode = new QuizTreeNode(overallOption, -1, quizHelper(fileScanner), quizHelper(fileScanner));

            // QuizTreeNode aNode = new QuizTreeNode(overallOption, score, 
            //         quizHelper(fileScanner), quizHelper(fileScanner));
            // aNode.setScore(score); // what is equivalent?
            // return newNode;
        }
        return root;
    }

    // write a case

    


    public void takeQuiz(Scanner console) {
        int overallScore = 0;
        takeQuiz(console, root);
    }

    private void takeQuiz(Scanner console, QuizTreeNode root) {
        if (root != null) {
            overallScore += root.score;
            String result = root.data;
            if (root.data.contains("END:")) {
                // System.out.println("Reach END case");
                System.out.println("Your result is: " + result.substring(4));
                System.out.println("Your score is: " + overallScore);
            } else {
                String data = result;
                String[] splice = data.split("/");
                String option1 = splice[0];
                String[] arrOption2 = splice[1].split("-");
                String option2 = arrOption2[0];
                System.out.print("Do you prefer " + option1 + " or " + option2 + "? ");
                String response = console.nextLine(); // automatically nextlines
                // if left
                if (response.equalsIgnoreCase(option1)) {
                    takeQuiz(console, root.left); // go lef

                } else if (response.equalsIgnoreCase(option2)) {
                    takeQuiz(console, root.right);

                } else {
                    System.out.println("  Invalid response; try again.");
                    takeQuiz(console, root);
                }
                // if right
                
                
                // get input from client
                // depending on input recurse in the direction given
            }
        }
        // if (root != null && (root.left != null || root.right != null)) {
        //     // recurse
        // } else if (root != null && (root.left == null && root.right == null)) {
        //     String result = root.data;

        //     System.out.println("Your result is: " + result);
        //     System.out.println("Your score is: " + root.score);
        // }
        
    }

    
    public void creativeExtension() {

    }

    public void addQuestion(String toReplace, String choices, 
                            String leftResult, String rightResult) {
        root = addQuestion(toReplace, choices, leftResult, rightResult, root);               
    }

    private QuizTreeNode addQuestion(String toReplace, String choices,
                String leftResult, String rightResult, QuizTreeNode curr) {
        if (curr != null) {
            // String data = curr.data;
            if (curr.data.equalsIgnoreCase("END:" + toReplace)) { // at end anyway
                // option0 is leftResult, option1 is right
                String[] option0 = leftResult.split("-");
                // String option0String = option0[0];
                // int score0 = Integer.parseInt(option0[0]);
                String[] option1 = rightResult.split("-");
                // String option1String = option1[0];
                // int score1 = Integer.parseInt(option1[0]);
                String[] result = choices.split("-");
                // String choice0 = result[0];
                // String choice1 = result[0];
                // int choiceScore = Integer.parseInt(choice1);
                QuizTreeNode leftNode = new QuizTreeNode("END:" + option0[0], Integer.parseInt(option0[0]));
                QuizTreeNode rightNode = new QuizTreeNode("END:" + option1[0], Integer.parseInt(option1[0]));
                QuizTreeNode newChoice = new QuizTreeNode(result[0], Integer.parseInt(choice1), leftNode, rightNode);
                return newChoice;
            } else {
                curr.left = addQuestion(toReplace, choices, leftResult, rightResult, curr.left);
                curr.right = addQuestion(toReplace, choices, leftResult, rightResult, curr.right);
            }
        }
        return curr;

    }

    public void export(PrintStream outFile) {
        exportHelper(outFile, root);
    }

    private void exportHelper(PrintStream outFile, QuizTreeNode curr) {
        
        if (curr != null) {
            // System.out.println("reach END case");
            String result = curr.data + "-" + curr.score;
            outFile.println(result);
            // if (curr.left == null && curr.right == null) {
            // } else {
            //     System.out.println("reach non-END case");
            //     String result = curr.data + "-" + curr.score;
            //     outFile.println(result);
                if (curr.left != null) {
                    exportHelper(outFile, curr.left);

                }
                if (curr.right != null) {
                    exportHelper(outFile, curr.right);

                }
            }
        }
        // while (root != null) {
        //     outFile.println(root.data);
        //     curr.left = exportHelper(outFile, curr.left);
        //     root.right;
        // }

        // base cases:
        // root is null
        // root is a leaf node 
    // }
    
    // PROVIDED
    // Returns the given percent rounded to two decimal places.
    private double roundTwoPlaces(double percent) {
        return (double) Math.round(percent * 100) / 100;
    }


    private static class QuizTreeNode {
        public final String data;
        public QuizTreeNode left;
        public QuizTreeNode right;
        public final int score;

        // public QuizTreeNode() {
        //     this("", 0, null, null);
        // }

        // public QuizTreeNode(String data) {
        //     this(data, null, null);
        // }

        // public QuizTreeNode(String data, int score) {
        //     this(data, null);
        // }

        public QuizTreeNode(String data, int score, QuizTreeNode left, QuizTreeNode right) {
            this.data = data;
            this.score = score;
            this.left = left;
            this.right = right;
        }

        public QuizTreeNode(String data, int score) {
            this(data, score, null, null);
        }

        // public void setScore(int score) {
        //     this.score = score;
        // } // don't want user able to modify score

        // 4 

    }
}   

