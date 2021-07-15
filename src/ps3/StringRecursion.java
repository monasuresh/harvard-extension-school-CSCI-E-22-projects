package ps3;
/*
 * StringRecursion.java
 *
 * Computer Science E-22
 *
 * Modified by: <Monica Suresh>, <monicasuresh45@gmail.com>
 */


public class StringRecursion {
    /*
    This method prints each of the letters of the specified string followed by a comma and a space
    */

    public static void printLetters(String str) {
        if (str == null || str.equals("")) {
            return;
        }

        if (str.length() > 1) {
            System.out.print(str.charAt(0) + ", ");
        } else {
            System.out.println(str.charAt(0));
        }
        printLetters(str.substring(1));
    }

    /*
    This method replaces the specified characters of a string with the specified newChar. If the string is null, the method
    returns null.
     */

    public static String replace(String str, char oldChar, char newChar) {
        if (str.equals("")) {
            return "";
        } else if (str == null) {
            return null;
        } else {
            String replace_rest = replace(str.substring(1), oldChar, newChar);
            if (str.charAt(0) != oldChar) {
                return str.charAt(0) + replace_rest;
            } else {
                return newChar + replace_rest;
            }
        }
    }

    /*
    This method returns the first occurrence of the specified char in the specified string. It returns -1 if the char
    is never found, the string is an empty string, or the string is null
     */

    public static int indexOf(char ch, String str) {
        if (str == null || str.equals("")) {
            return -1;
        } else if (str.charAt(0) == ch) {
            return 0;
        } else {
            int index = indexOf(ch, str.substring(1));
            if (index == -1) {
                return -1;
            } else {
                return index + 1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("printLetters test: ");
        printLetters("Rabbit");
        printLetters("I like to recurse!");
        System.out.println();

        System.out.println("replace test: ");
        System.out.println(replace("base case", 'e', 'y'));
        System.out.println(replace("base case", 'r', 'y'));
        System.out.println();

        System.out.println("indexOf test: ");
        System.out.println(indexOf('b', "Rabbit"));
        System.out.println(indexOf('P', "Rabbit"));
    }
}
