// Name: Ollie Le 
// Class: CSE 123
// TA: James Try
// Assignment: P1: Mini-Git
// Class header: This class represents a version control history which is capable of 
// operations such as tracking a history of changes over time, getting repository metadata,
// and adding/removing changes.

import java.util.*;
import java.text.SimpleDateFormat;

public class Repository {
    private String name;
    private Commit front;
    private int size;

    // B:
    // E:
    // R:
    // P:
    public Repository(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        // this.front = name;
    }

    // B:
    // E:
    // R:
    // P:
    public String getRepoHead() {
        if (front == null) {
            return null;
        } else {
            return front.id;
        }
        // return front.message; // how to get id of current head if no head?
        // can change head later?

    }

    // B:
    // E:
    // R:
    // P:
    public int getRepoSize() {
        /* Return the number of commits in the repository

         */
        // do something
        return size;
    }

    // B: Returns a String representation of this repository. If no commits have been made,
    // represents as "No commits".
    // E: N/A
    // R: Returns a String representation of this repository. If no commits have been made,
    // represents as "No commits".
    // P: N/A
    public String toString() {
            if (front == null) { 
                return name + " - No commits";
            } else {
                return name + " - Current head: " + front;
            }
    }

    // is there a separate head commit object from repository?
    // how is calling toString separate/is it recursive?


    // B: Determines if a committed change is contained within the repository.
    // E: N/A
    // R: Returns true if the operation was successfully completed, and false if not.
    // P: Accepts a targetId of a change to locate.
    public boolean contains(String targetId) {
        // traverse all nodes
        // if targetID == curr, return true
        // if proceed beyond, return false
        /* Return true if the commit with ID targetId is in the repository, false if not.

            */
        // front = null;
        // if (curr == null) {
        //     return false;
        // } 
        // else {
        // curr = front;
        Commit curr = front;
        while (curr != null) {
            if (curr.id.equals(targetId)) { // getID / how to access data of Commits
                return true; // check capitalization
            }
            curr = curr.past;
        }
        // }
        // return false;
        // do something
        return false;
    }
 
    // B:
    // E:
    // R:
    // P:
    public String getHistory(int n) { 
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        if (n > size) {
            n = size;
        }
        String hist = "";
        Commit curr = front;
        for (int i = 0; i < n; i++) { 
            if (curr != null) {
                    hist += curr.toString() + "\n"; // changed from front.toString() to curr.toString() 
                    curr = curr.past; // does this go to next or to previous?
                }
        }
        return hist;
    }
        // if (size == 0) {
        //     // do something
        // }
            // if (front != null) {
            //     hist += front.toString() + "\n";
            //     curr = front.past;
            // }
            // if (curr == ul)

        // }
            // if (curr == null) {
            //     return null;
            // }
            // return all commits (what is a commit?)
        // }
        // } else {

        // }
        /* Return a string consisting of the String representations of the most recent n commits in this repository, with the most recent first. Commits should be separated by a newline (\n) character.

            If there are fewer than n commits in this repository, return them all.

            If there are no commits in this repository, return the empty string.

            If n is non-positive, throw an IllegalArgumentException.

            */

    // B:
    // E:
    // R:
    // P:
    public String commit(String message) {
        // message = front;
        // while (curr != null && curr.past != null) {
        //     curr = curr.past;
        // }

        Commit newCommit = new Commit(message, front);
        size++;
        // if (front == null) {
        //     front = newCommit;
        // }
        front = newCommit;
        // Commit curr = front; // go backwards?
        // if (newCommit.next == null) {
            
        // } 
        // else {
        
        // while (curr.past != null) {
        //     curr = curr.past;
        // } 
            // curr = newCommit;
        // }
        
        return newCommit.id; // returns id (from spec)
        /* Create a new commit with the given message, add it to this repository.

            The new commit should become the new head of this repository, preserving the history behind it.

            Return the ID of the new commit. */
    }

    // B:
    // E:
    // R:
    // P:
    public boolean drop(String targetId) {
        // int count = 0;
        if (front != null && front.id.equals(targetId)) {
            front = front.past;
            size--;
            return true;
        }
        Commit curr = front;

        while (curr != null && curr.past != null) {
            if (curr.past != null && curr.past.id.equals(targetId)) { // priority matters
                size--;
                curr.past = curr.past.past; // how to keep data?
                return true;
            }
            curr = curr.past;
        } // bigger loop with a wee loop, both are important checks
        // size -= count; // this is just for overall program's sustainability
        return false;

        
        // Commit curr = front;
        // if (front.id.equals(targetId)) {
        //     front.past = null;
        //     size--;
        //     return true;
        // }
        // while (curr != null && curr.past != null) {
        //     // while (curr != null && curr.past != null) {
        //     if (curr.id.equals(targetId)) { // getID / how to access data of Commits
        //         curr = curr.past; // check capitalization
        //         size--;
        //         return true;
        //     }
        //     // }
        //     curr = curr.past;
        // }
        // return false;
        /* Remove the commit with ID targetId from this repository, maintaining the rest of the history.

            Returns true if the commit was successfully dropped, and false if there is no commit that matches the given ID in the repository.

            */
    }

    public void synchronize(Repository other) { // 9461
        if (other.front != null) { // only enter synchronize method the moment other isn't empty
            if (front == null) { // synchronize other to an empty this
                front = other.front;
                other.front = null;
                // adjust arrows?
            } else { // synchronize other to contents in this, front != null
                // if (other.front.timeStamp < front.timeStamp) { // front case
                //     Commit temporary = this.front.past; // store head's arrow
                //     this.front = other.front; // make head's arrow point to other head's arrow
                //     this.front.past = temporary; // make other head's arrow point to next
                // }
                // Commit curr = front; // scope is just one
                // Commit otherCurr = other.front; 
                // while (curr.past != null && otherCurr.past != null) { // middle and end cases
                //     if (otherCurr.timeStamp < curr.past.timeStamp) {
                //         Commit temp = otherCurr.past;
                //         otherCurr.past = curr.past;
                //         curr.past = otherCurr;
                //         otherCurr = temp;
                //     }
                //     curr = curr.past;
                // }
                // if (other.front != null) {
                //     curr.past = other.front;
                // }
                // other.front = null;
            }
            size += other.size;
            other.size = 0;
        }
    }
        /* Takes all the commits in the other repository and moves them into this repository, combining the two repository histories such that chronological order is preserved. That is, after executing this method, this repository should contain all commits that were from this and other, and the commits should be ordered in timestamp order from most recent to least recent.

            If the other repository is empty, this repository should remain unchanged.

            If this repository is empty, all commits in the other repository should be moved into this repository.

            At the end of this method's execution, other should be an empty repository in all cases.

            You should not construct any new Commit objects to implement this method. You may however create as many references as you like.

            */
            
        // empty case:
            // guarantee that curr != null <- Make sure this is handled
            // this is handled because we're guaranteed that front != null in this else branch
            // middle (and front) case:
                // if (temp.timeStamp < curr.timeStamp) {
                // }
            // end case

    /**
     * DO NOT MODIFY
     * A class that represents a single commit in the repository.
     * Commits are characterized by an identifier, a commit message,
     * and the time that the commit was made. A commit also stores
     * a reference to the immediately previous commit if it exists.
     *
     * Staff Note: You may notice that the comments in this 
     * class openly mention the fields of the class. This is fine 
     * because the fields of the Commit class are public. In general, 
     * be careful about revealing implementation details!
     */
    public class Commit {

        private static int currentCommitID;

        /**
         * The time, in milliseconds, at which this commit was created.
         */
        public final long timeStamp;

        /**
         * A unique identifier for this commit.
         */
        public final String id;

        /**
         * A message describing the changes made in this commit.
         */
        public final String message;

        /**
         * A reference to the previous commit, if it exists. Otherwise, null.
         */
        public Commit past;

        /**
         * Constructs a commit object. The unique identifier and timestamp
         * are automatically generated.
         * @param message A message describing the changes made in this commit.
         * @param past A reference to the commit made immediately before this
         *             commit.
         */
        public Commit(String message, Commit past) {
            this.id = "" + currentCommitID++;
            this.message = message;
            this.timeStamp = System.currentTimeMillis();
            this.past = past;
        }

        /**
         * Constructs a commit object with no previous commit. The unique
         * identifier and timestamp are automatically generated.
         * @param message A message describing the changes made in this commit.
         */
        public Commit(String message) {
            this(message, null);
        }

        /**
         * Returns a string representation of this commit. The string
         * representation consists of this commit's unique identifier,
         * timestamp, and message, in the following form:
         *      "[identifier] at [timestamp]: [message]"
         * @return The string representation of this collection.
         */
        @Override
        public String toString() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(timeStamp);

            return id + " at " + formatter.format(date) + ": " + message;
        }

        /**
        * Resets the IDs of the commit nodes such that they reset to 0.
        * Primarily for testing purposes.
        */
        public static void resetIds() {
            Commit.currentCommitID = 0;
        }
    }
}
