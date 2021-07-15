package ps3;
/*
 * IsPal.java
 */

public class IsPal {

    /**
     * isPal - takes a string as an argument and returns true if the string is a palindrome otherwise false. It
     * uses a queue and stack to test whether the string reads the same in both directions.
     */
    public static boolean isPal(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        } else if (s.length() == 1 || s.length() == 0) {
            return true;
        }

        Stack<Character> characterStack = new LLStack<>();
        Queue<Character> characterQueue = new LLQueue<>();

        for (int i = 0; i < s.length(); i++) {
            Character c = Character.toLowerCase(s.charAt(i));
            if (c >= 97 && c <= 122) {
                characterStack.push(c);
                characterQueue.insert(c);
            }
        }

        while (!characterStack.isEmpty()) {
            if (characterStack.pop() != characterQueue.remove()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println("true");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests

        /*
         * We encourage you to add more unit tests below that test a variety
         * of different cases, although doing so is not required.
         */
    }
}
