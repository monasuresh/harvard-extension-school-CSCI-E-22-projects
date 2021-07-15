package ps3;
/*
 * StringNode.java
 *
 * Computer Science E-22, Harvard University
 *
 * modified by:
 *   name: Monica Suresh
 *   email: monicasuresh45@gmail.com
 */

import java.io.*;
import java.util.*;

/**
 * A class for representing a string using a linked list.
 * Each character of the string is stored in a separate node.  
 *
 * This class represents one node of the linked list.  The string as a
 * whole is represented by storing a reference to the first node in
 * the linked list. The methods in this class are static methods that
 * take a reference to a string linked-list as a parameter. This
 * approach allows us to use recursion to write many of the methods,
 * and it also allows the methods to handle empty strings, which are 
 * represented using a value of null.
 */
public class StringNode {
    private char ch;
    private StringNode next;

    /**
     * Constructor
     */
    public StringNode(char c, StringNode n) {
        this.ch = c;
        this.next = n;
    }

    /**
     * getNode - private helper method that returns a reference to
     * node i in the given linked-list string.  If the string is too
     * short or if the user passes in a negative i, the method returns null.
     */
    private static StringNode getNode(StringNode str, int i) {
        if (i < 0 || str == null) {    // base case 1: not found
            return null;
        } else if (i == 0) {           // base case 2: just found
            return str;
        } else {
            return getNode(str.next, i - 1);
        }
    }

    /*****************************************************
     * Public methods (in alphabetical order)
     *****************************************************/

    /**
     * charAt - returns the character at the specified index of the
     * specified linked-list string, where the first character has
     * index 0.  If the index i is < 0 or i > length - 1, the method
     * will end up throwing an IllegalArgumentException.
     */
    public static char charAt(StringNode str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("the string is empty");
        }

        StringNode node = getNode(str, i);

        if (node != null) {
            return node.ch;
        } else {
            throw new IllegalArgumentException("invalid index: " + i);
        }
    }

    /**
     * compareAlpha - compares two linked-list strings to determine
     * which comes first alphabetically (i.e., according  to the ordering 
     * used for words in a dictionary). 
     *
     * It returns:
     *    1 if str1 comes first alphabetically
     *    2 if str2 comes first alphabetically
     *    0 if str1 and str2 represent the same string
     *
     * The empty string comes before any non-empty string, 
     * and the prefix of a string comes before the string
     * itself (e.g., "be" comes before "become").
     */
    public static int compareAlpha(StringNode str1, StringNode str2) {
        if (str1 == null && str2 == null) {
            return 0;
        } else if (str1 == null) {
            return 1;
        } else if (str2 == null) {
            return 2;
        } else {
            StringNode trav1 = str1;
            StringNode trav2 = str2;

            while (trav1 != null && trav2 != null) {
                if (trav1.ch < trav2.ch) {
                    return 1;
                } else if (trav2.ch < trav1.ch) {
                    return 2;
                }
                trav1 = trav1.next;
                trav2 = trav2.next;
            }

            if (trav1 != null && trav2  == null) {
                return 2;
            } else if (trav2 != null && trav1 == null) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * convert - converts a standard Java String object to a linked-list
     * string and returns a reference to the linked-list string
     */
    public static StringNode convert(String s) {
        if (s.length() == 0) {
            return null;
        }

        StringNode firstNode = new StringNode(s.charAt(0), null);
        StringNode prevNode = firstNode;
        StringNode nextNode;

        for (int i = 1; i < s.length(); i++) {
            nextNode = new StringNode(s.charAt(i), null);
            prevNode.next = nextNode;
            prevNode = nextNode;
        }

        return firstNode;
    }

    /**
     * copy - returns a copy of the given linked-list string
     */
    public static StringNode copy(StringNode str) {
        if (str == null) {
            return null;
        }

        StringNode trav = str;
        StringNode copy = new StringNode(str.ch, null);
        StringNode trav2 = copy;

        while (trav.next != null) {
            trav2.next = new StringNode(trav.next.ch, null);
            trav2 = trav2.next;
            trav = trav.next;
        }

        return copy;
    }

    /**
     * deleteChar - deletes character i in the given linked-list string and
     * returns a reference to the resulting linked-list string
     */
    public static StringNode deleteChar(StringNode str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("string is empty");
        } else if (i < 0) {
            throw new IllegalArgumentException("invalid index: " + i);
        } else if (i == 0) {
            str = str.next;
        } else {
            StringNode prevNode = getNode(str, i-1);
            if (prevNode != null && prevNode.next != null) {
                prevNode.next = prevNode.next.next;
            } else {
                throw new IllegalArgumentException("invalid index: " + i);
            }
        }

        return str;
    }

    /**
     * insertBefore - inserts the specified new character (newChar) 
     * before the first occurrence of the specified character (afterChar)
     * in the linked-list string to which str refers.
     * If beforeChar is not in the string, the method adds the character
     * to the end of the string. Returns a reference to the first node
     * in the modified linked list, because the first node can change.
     */
    public static StringNode insertBefore(StringNode str, char newChar,
                                          char beforeChar)
    {
        StringNode newNode = new StringNode(newChar, null);

        // If the string is empty or beforeChar is in the current first node, 
        // return the new node, which is the new first node.
        if (str == null) {
            return newNode;
        } else if (str.ch == beforeChar) {
            newNode.next = str;
            return newNode;
        }

        StringNode node = insertBefore(str.next, newChar, beforeChar);
        str.next = node;
        return str;
    }

    /**
     * insertChar - inserts the character ch before the character
     * currently in position i of the specified linked-list string.
     * Returns a reference to the resulting linked-list string.
     */
    public static StringNode insertChar(StringNode str, int i, char ch) {
        StringNode newNode, prevNode;

        if (i < 0) {
            throw new IllegalArgumentException("invalid index: " + i);
        } else if (i == 0) {
            newNode = new StringNode(ch, str);
            str = newNode;
        } else {
            prevNode = getNode(str, i - 1);
            if (prevNode != null) {
                newNode = new StringNode(ch, prevNode.next);
                prevNode.next = newNode;
            } else {
                throw new IllegalArgumentException("invalid index: " + i);
            }
        }

        return str;
    }

    /**
     * insertSorted - inserts character ch in the correct position
     * in a sorted list of characters (i.e., a sorted linked-list string)
     * and returns a reference to the resulting list.
     */
    public static StringNode insertSorted(StringNode str, char ch) {
        StringNode newNode, trail, trav;

        // Find where the character belongs.
        trail = null;
        trav = str;
        while (trav != null && trav.ch < ch) {
            trail = trav;
            trav = trav.next;
        }

        // Create and insert the new node.
        newNode = new StringNode(ch, trav);
        if (trail == null) {
            // We never advanced the prev and trav references, so
            // newNode goes at the start of the list.
            str = newNode;
        } else {
            trail.next = newNode;
        }

        return str;
    }

    /**
     * length - recursively determines the number of characters in the
     * linked-list string to which str refers
     */
    public static int length(StringNode str) {
        if (str == null) {
            return  0;
        } else {
            return 1 + length(str.next);
        }
    }

    /**
     * numOccur - find the number of occurrences of the character
     * ch in the linked list to which str refers
     */
    public static int numOccur(StringNode str, char ch) {
        StringNode trav = str;
        int count = 0;
        while (trav != null)  {
            if (trav.ch == ch) {
                count++;
            }
            trav = trav.next;
        }

        return count;
    }

    /**
     * print - recursively writes the specified linked-list string to System.out
     */
    public static void print(StringNode str) {
        if (str == null) {
            return;
        } else {
            System.out.print(str.ch);
            print(str.next);
        }
    }

    /**
     * read - reads a string from an input stream and returns a
     * reference to a linked list containing the characters in the string
     */
    public static StringNode read(InputStream in) throws IOException {
        char ch = (char)in.read();

        if (ch == '\n') {    // the string ends when we hit a newline character
            return null;
        } else {
            StringNode restOfString = read(in);
            StringNode first = new StringNode(ch, restOfString);
            return first;
        }
    }

    /*
     * toString - creates and returns the Java string that
     * the current StringNode represents.  Note that this
     * method -- unlike the others -- is a non-static method.
     * Thus, it will not work for empty strings, since they
     * are represented by a value of null, and we can't use
     * null to invoke this method.
     */
    public String toString() {
        String str = "";

        StringNode trav = this;   // start trav on the current node    
        while (trav != null) {
            str = str + trav.ch;
            trav = trav.next;
        }

        return str;
    }

    /**
     * toUpperCase - converts all of the characters in the specified
     * linked-list string to upper case.  Modifies the list itself,
     * rather than creating a new list.
     */
    public static void toUpperCase(StringNode str) {
        if (str == null) {
            return;
        }

        toUpperCase(str.next);
        str.ch = Character.toUpperCase(str.ch);
    }

    /**
     * reverseInPlace reverses the passed string modifying the references in the original nodes so that that
     * go in reverse direction.
     */
    public static StringNode reverseInPlace(StringNode str) {
        if (str == null || str.next == null) {
            return str;
        }

        StringNode reversed = reverseInPlace(str.next);
        str.next.next = str;
        str.next = null;
        return reversed;
    }

    /**
     * lastIndexOf - returns the index of the last occurrence of the passed char in the passed StringNode. If the char
     * does not appear in the StringNode, the method returns -1.
     */
    public static int lastIndexOf(StringNode str, char ch) {
        if (str == null) {
            return -1;
        } else if (str.next == null && str.ch == ch) {
            return 0;
        }

        int lastOccurence = lastIndexOf(str.next, ch);
        if (str.ch == ch) {
            return lastOccurence + 1;
        } else if (lastOccurence > -1){
            return lastOccurence + 1;
        } else {
            return lastOccurence;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        // toUpperCase
        StringNode str = StringNode.convert("be");
        System.out.print("Here's a string: ");
        System.out.println(str);    // implicit toString call
        System.out.print("Here it is in upper-case letters: ");
        StringNode.toUpperCase(str);
        System.out.println(str);

        // numOccur
        System.out.print("Enter a string: ");
        String s = in.nextLine();
        StringNode str1 = StringNode.convert(s);
        System.out.print("\nWhat character to count? ");
        char ch = in.nextLine().charAt(0);
        int count = StringNode.numOccur(str1, ch);
        System.out.println("There are " + count + " occurrences of " + ch);

        // copy and deleteChar
        int n = -1;
        while (n < 0) {
            System.out.print("\nWhat # character to delete (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        StringNode copyStr1 = StringNode.copy(str1);
        try {
            str1 = StringNode.deleteChar(str1, n);
            StringNode.print(str1);
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }

        // The copy should be unchanged!
        str1 = copyStr1;
        System.out.print("\nReturning to the unchanged copy: ");
        System.out.println(copyStr1);

        // compareAlpha
        System.out.print("\nType another string: ");
        s = in.nextLine();
        StringNode str2 = StringNode.convert(s);
        System.out.print("\ncomparing " + str1 + " and " + str2 + " gives: ");
        System.out.println(StringNode.compareAlpha(str1, str2));

        // insertBefore
        System.out.print("What character to insert? ");
        ch = in.nextLine().charAt(0);
        System.out.print("\nWhat character to insert before? ");
        char before = in.nextLine().charAt(0);
        str1 = StringNode.insertBefore(str1, ch, before);
        System.out.println(str1);
    }
}