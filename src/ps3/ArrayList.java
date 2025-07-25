package ps3;
/*
 * ArrayList.java
 *
 * Computer Science E-22
 *
 */

/*
 * A class that implements our simple List interface using an array.
 */
public class ArrayList implements List {
    private Object[] items;     // the items in the list
    private int length;         // # of items in the list

    /*
     * Constructs an ArrayList object with the specified maximum size
     * for a list that is initially empty.
     */
    public ArrayList(int maxSize) {
        items = new Object[maxSize];
        length = 0;
    }

    /*
     * Constructs an ArrayList object containing the items in the specified
     * array, and with a max size that is twice the size of that array
     * (to allow room for growth).
     */
    public ArrayList(Object[] initItems) {
        items = new Object[2 * initItems.length];
        for (int i = 0; i < initItems.length; i++) {
            items[i] = initItems[i];
        }

        length = initItems.length;
    }

    /*
     * length - returns the number of items in the list
     */
    public int length() {
        return length;
    }

    /*
     * isFull - returns true if the list is full, and false otherwise
     */
    public boolean isFull() {
        return (length == items.length);
    }

    /* getItem - returns the item at position i in the list */
    public Object getItem(int i) {
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }

        return items[i];
    }

    /*
     * addItem - adds the specified item at position i in the list,
     * shifting the items that are currently in positions i, i+1, i+2,
     * etc. to the right by one.  Returns false if the list is full,
     * and true otherwise.
     */
    public boolean addItem(Object item, int i) {
        if (i < 0 || i > length) {
            throw new IndexOutOfBoundsException();
        } else if (isFull()) {
            return false;
        }

        // make room for the new item
        for (int j = length - 1; j >= i; j--) {
            items[j + 1] = items[j];
        }

        items[i] = item;
        length++;
        return true;
    }

    /*
     * removeItem - removes the item at position i in the list,
     * shifting the items that are currently in positions i+1, i+2,
     * etc. to the left by one.  Returns a reference to the removed
     * object.
     */
    public Object removeItem(int i) {
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException();
        }

        Object removed = items[i];

        // fill in the "hole" left by the removed item
        for (int j = i; j < length - 1; j++) {
            items[j] = items[j + 1];
        }
        items[length - 1] = null;

        length--;
        return removed;
    }

    /*
     * removeAll - removes all occurrences of the passed item if it occurs in the arraylist shifting the
     * items to the left one by one each time. If the item occurs in the arraylist,
     * the method returns true otherwise it returns false.
     */
    public boolean removeAll(Object item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else if (this.items.length == 0) {
            return false;
        }

        boolean isOccur = false;

        int i = 0;
        while (items[i] != null) {
            if (this.items[i] != null && this.items[i].equals(item)) {
                for (int j = i; j < this.items.length - 1; j++) {
                    this.items[j] = this.items[j + 1];
                }
                this.items[this.items.length - 1] = null;
                length--;
                isOccur = true;
            } else {
                i++;
            }
        }
        return isOccur;
    }

    /*
     * toString - converts the list into a String of the form
     * {item0, item1, ...}
     */
    public String toString() {
        String str = "{";

        for (int i = 0; i < length; i++) {
            str = str + items[i];
            if (i < length - 1) {
                str = str + ", ";
            }
        }

        str = str + "}";
        return str;
    }

    /*
     * iterator - returns an iterator for this list
     */
    public ListIterator iterator() {
        // still needs to be implemented
        return null;
    }

    public static void main(String[] args) {
        Character[] helloArray = {'h', 'e', 'l', 'l', 'o'};
        ArrayList list = new ArrayList(helloArray);
        System.out.println(list.removeAll('l'));
        System.out.println(list.toString());

        String[] abcArray = {"Apple", "Banana", "Carrot", "Carrot"};
        ArrayList list2 = new ArrayList(abcArray);
        System.out.println(list2.removeAll("Pear"));
        System.out.println(list2.toString());

        Integer[] intArray = {1, 5, 1, 7, 10, 1};
        ArrayList list3 = new ArrayList(intArray);
        System.out.println(list3.removeAll(1));
        System.out.println(list3.toString());
    }
}
