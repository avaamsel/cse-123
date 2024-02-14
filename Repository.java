// Ava Nunes
// 01/27/24
// CSE 123
// TA: Ben Wang
// this class represents ...

import java.util.*;
import java.text.SimpleDateFormat;

public class Repository {
    private Commit head;
    private String name;

    // behavior: contructs a new repository with the given name
    // parameters: name - name to name the new repository
    // returns: n/a, contructor
    // exceptions: throws new IllegalArgumentExeption when the given name is empty or null
    public Repository(String name) {
        if(name == "" || name == null) {
            throw new IllegalArgumentException("The name of your repository can't be null!");
        }

        this.name = name;

    }

    // behavior: returns the id for the current repository head
    // parameters: n/a
    // returns: head.id - the id for the current repository head, returns null if the current
    //                    repository's id is null
    public String getRepoHead() {
        if(head.id == null) {
            return null;
        }

        return head.id;
    }

    // behavior: returns the current number of commits in the repository
    // parameters: n/a, void method
    // returns: counter - number of commits in the repository
    public int getRepoSize() {
        if (head == null) {
            return 0;
        }
        
        int counter = 1;
        Commit temp = head;
        while (temp.past != null) {
            counter++;
            temp = temp.past;
        }

        return counter;
    }

    // behavior: returns the string representation of the current head in the repository
    // parameters: n/a, void method
    // returns: the string representation of the current head in the repository, states there are
    //          no commits if the repository is empty
    public String toString() {
        if(getRepoSize() == 0) {
            return name + " - No commits";
        }

        return name + " - Current head: " + head.toString();
    }

    // behavior: returns whether or not the commit with the given targetId is within the repository
    // parameters: targetId - id of the commit to look for
    // returns: true - if the repository contains the given targetId, false if otherwise
    public boolean contains(String targetId) {
        Commit temp = head;
        while (temp != null) {
            String tempId = temp.id;
            if(tempId.equals(targetId)) {
                return true;
            }

            temp = temp.past;
        }

        return false;
    }

    // behavior: returns the number of commits specified by the given n, if there are less commits
    //           than n, all commits in the repository are returned
    // parameters: n - number of commits to return
    // returns: the number of commits specified by the client
    // exceptions: throws a new IllegalArgumentException when n is less than 0
    public String getHistory(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        else if (getRepoSize() == 0) {
            return "";
        }

        else if (getRepoSize() < n) {
            String returns = "";
            Commit temp = head;
            while(temp != null) {
                returns += temp.toString() + "\n";
                temp = temp.past;
            }

            return returns;
        }

        String returns = "";
        Commit temp = head;
        for (int i = 0; i < n; i++) {
            returns += temp.toString() + "\n"; 
            temp = temp.past;    
        } 
       
        return returns;
    }

    // behavior: creates a new commit with the given message to add to the repository
    // parameters: message - message to add to the new commit
    // returns: id of the new commit, which is now the head of the repository
    public String commit(String message) {
        Commit newCommit = new Commit(message);
        if (head == null) {
            head = newCommit;
        }

        else {
            newCommit.past = head;
            head = newCommit; 
        }

        return getRepoHead();
    }

    // behavior: removes the commit with the given targetId if it's found within the repository
    // parameters: targetId - id of the commit to search for and possibly drop
    // returns: true if a commit was successfully dropped from the repository, false if no commit
    //          in the repository has an id that matches the given targetid
    public boolean drop(String targetId) {
        if(!contains(targetId)) {
            return false;
        }
        
        if (head.id.equals(targetId)) {
            head = head.past;
        } 
        
        else {
            Commit temp = head;
            while (temp != null && temp.past != null) {
                if (temp.past.id.equals(targetId)) {
                    if(temp.past.past == null) {
                        temp.past = null;
                    }

                    else {
                       temp.past = temp.past.past; 
                    }
                    
                }

                temp = temp.past;
            }

        }

        return true;
    }

    // behavior: meshes two repositories together into one of the exitsting repositories, sorting
    //           them into chronological order
    // parameters: other - other repository to synchronize with the current repository
    // returns: n/a, void method
    // exceptions: 
    public void synchronize(Repository other) {
        // empty
        if (this.head == null) {
            this.head = other.head;
            other.head = null;
        }

        else {

            if(this.head.timeStamp < other.head.timeStamp) {
                Commit temp1 = other.head;
                while (temp1.past != null && this.head != null) {
                    Commit temp = this.head;
                    this.head = this.head.past;
                    temp.past = temp1.past;
                    temp1.past = temp;
                    temp1 = temp.past;
                }

                if (temp1.past == null) {
                    temp1.past = other.head;
                    other.head = null;
                } 
            }

            else {
                Commit temp1 = this.head;
                while (temp1.past != null && other.head != null) {
                    Commit temp = other.head;
                    other.head = other.head.past;
                    temp.past = temp1.past;
                    temp1.past = temp;
                    temp1 = temp.past;
                } 

                if (temp1.past == null) {
                    temp1.past = other.head;
                    other.head = null;
                } 
            }
        
        }

    }

    // behavior:
    // parameters:
    // returns: n/a, void helper method
    // private void append(Repository other) {
    //     Commit temp1 = this.head;
    //     while (temp1.past != null) {
    //         temp1 = temp1.past;
    //     }

    //     temp1.past = other.head;
    //     other.head = null;
    // }

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
