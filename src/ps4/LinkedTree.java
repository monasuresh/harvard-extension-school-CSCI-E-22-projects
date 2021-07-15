package ps4;
/*
 * LinkedTree.java
 *
 * Computer Science E-22
 *
 * Modifications and additions by:
 *     name: Monica Suresh
 *     username: monicasuresh45@gmail.com
 */

import ps4.LLList;

import java.util.*;

/*
 * LinkedTree - a class that represents a binary tree containing data
 * items with integer keys.  If the nodes are inserted using the
 * insert method, the result will be a binary search tree.
 */
public class LinkedTree {
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private LLList data;     // list of data values for this key
        private Node left;       // reference to the left child/subtree
        private Node right;      // reference to the right child/subtree
        private Node parent;     // reference to the parent. NOT YET MAINTAINED!

        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    // the root of the tree as a whole
    private Node root;

    public LinkedTree() {
        root = null;
    }

    /*
     * Prints the keys of the tree in the order given by a preorder traversal.
     * Invokes the recursive preorderPrintTree method to do the work.
     */
    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);
        }
        System.out.println();
    }

    /*
     * Recursively performs a preorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the
     * entire tree.
     */
    private static void preorderPrintTree(Node root) {
        System.out.print(root.key + " ");
        if (root.left != null) {
            preorderPrintTree(root.left);
        }
        if (root.right != null) {
            preorderPrintTree(root.right);
        }
    }

    /*
     * Prints the keys of the tree in the order given by a postorder traversal.
     * Invokes the recursive postorderPrintTree method to do the work.
     */
    public void postorderPrint() {
        if (root != null) {
            postorderPrintTree(root);
        }
        System.out.println();
    }

    /*
     * Recursively performs a postorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the
     * entire tree.
     */
    private static void postorderPrintTree(Node root) {
        if (root.left != null) {
            postorderPrintTree(root.left);
        }
        if (root.right != null) {
            postorderPrintTree(root.right);
        }
        System.out.print(root.key + " ");
    }

    /*
     * Prints the keys of the tree in the order given by an inorder traversal.
     * Invokes the recursive inorderPrintTree method to do the work.
     */
    public void inorderPrint() {
        if (root != null) {
            inorderPrintTree(root);
        }
        System.out.println();
    }

    /*
     * Recursively performs an inorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the
     * entire tree.
     */
    private static void inorderPrintTree(Node root) {
        if (root.left != null) {
            inorderPrintTree(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inorderPrintTree(root.right);
        }
    }

    /*
     * Inner class for temporarily associating a node's depth
     * with the node, so that levelOrderPrint can print the levels
     * of the tree on separate lines.
     */
    private class NodePlusDepth {
        private Node node;
        private int depth;

        private NodePlusDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    /*
     * Prints the keys of the tree in the order given by a
     * level-order traversal.
     */
    public void levelOrderPrint() {
        LLQueue<NodePlusDepth> q = new LLQueue<NodePlusDepth>();

        // Insert the root into the queue if the root is not null.
        if (root != null) {
            q.insert(new NodePlusDepth(root, 0));
        }

        // We continue until the queue is empty.  At each step,
        // we remove an element from the queue, print its value,
        // and insert its children (if any) into the queue.
        // We also keep track of the current level, and add a newline
        // whenever we advance to a new level.
        int level = 0;
        while (!q.isEmpty()) {
            NodePlusDepth item = q.remove();

            if (item.depth > level) {
                System.out.println();
                level++;
            }
            System.out.print(item.node.key + " ");

            if (item.node.left != null) {
                q.insert(new NodePlusDepth(item.node.left, item.depth + 1));
            }
            if (item.node.right != null) {
                q.insert(new NodePlusDepth(item.node.right, item.depth + 1));
            }
        }
        System.out.println();
    }

    /*
     * Searches for the specified key in the tree.
     * If it finds it, it returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual search.
     */
    public LLList search(int key) {
        Node n = searchTree(root, key);
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }

    /*
     * Recursively searches for the specified key in the tree/subtree
     * whose root is specified. Note that the parameter is *not*
     * necessarily the root of the entire tree.
     */
    private static Node searchTree(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchTree(root.left, key);
        } else {
            return searchTree(root.right, key);
        }
    }

    /*
     * Inserts the specified (key, data) pair in the tree so that the
     * tree remains a binary search tree.
     */
    public void insert(int key, Object data) {
        // Find the parent of the new node.
        Node parent = null;
        Node trav = root;
        while (trav != null) {
            if (trav.key == key) {
                trav.data.addItem(data, 0);
                return;
            }
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }

        // Insert the new node.
        Node newNode = new Node(key, data);
        if (parent == null) {    // the tree was empty
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
            newNode.parent = parent;
        } else {
            parent.right = newNode;
            newNode.parent = parent;
        }
    }

    /*
     * FOR TESTING: Processes the integer keys in the specified array from
     * left to right, adding a node for each of them to the tree.
     * The data associated with each key is a string based on the key.
     */
    public void insertKeys(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i], "data for key " + keys[i]);
        }
    }

    /*
     * Deletes the node containing the (key, data) pair with the
     * specified key from the tree and return the associated data item.
     */
    public LLList delete(int key) {
        // Find the node to be deleted and its parent.
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }

        // Delete the node (if any) and return the removed data item.
        if (trav == null) {   // no such key
            return null;
        } else {
            LLList removedData = trav.data;
            deleteNode(trav, parent);
            return removedData;
        }
    }

    /*
     * Deletes the node specified by the parameter toDelete.  parent
     * specifies the parent of the node to be deleted.
     */
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            // Case 3: toDelete has two children.
            // Find a replacement for the item we're deleting -- as well as
            // the replacement's parent.
            // We use the smallest item in toDelete's right subtree as
            // the replacement.
            Node replaceParent = toDelete;
            Node replace = toDelete.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }

            // Replace toDelete's key and data with those of the
            // replacement item.
            toDelete.key = replace.key;
            toDelete.data = replace.data;

            // Recursively delete the replacement item's old node.
            // It has at most one child, so we don't have to
            // worry about infinite recursion.
            deleteNode(replace, replaceParent);
        } else {
            // Cases 1 and 2: toDelete has 0 or 1 child
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;  // null if it has no children
            }

            if (toDelete == root) {
                root = toDeleteChild;
                if (toDeleteChild != null) {
                    toDeleteChild.parent = null;
                }
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
                if (toDeleteChild != null) {
                    toDeleteChild.parent = parent;
                }
            } else {
                parent.right = toDeleteChild;
                if (toDeleteChild != null) {
                    toDeleteChild.parent = parent;
                }
            }
        }
    }

    public static int getHeight(Node root) {
        if (root == null) {
            return -1;
        }

        int lH = getHeight(root.left) + 1;
        int rH = getHeight(root.right) + 1;

        if (lH > rH) {
            return lH;
        } else {
            return rH;
        }
    }

    /* Returns a preorder iterator for this tree. */
    public LinkedTreeIterator preorderIterator() {
        return new PreorderIterator();
    }

    /*
     * inner class for a preorder iterator
     * IMPORTANT: You will not be able to actually use objects from this class
     * to perform a preorder iteration until you have modified the LinkedTree
     * methods so that they maintain the parent fields in the Nodes.
     */
    private class PreorderIterator implements LinkedTreeIterator {
        private Node nextNode;

        private PreorderIterator() {
            // The traversal starts with the root node.
            nextNode = root;
        }

        public boolean hasNext() {
            return (nextNode != null);
        }

        public int next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }

            // Store a copy of the key to be returned.
            int key = nextNode.key;

            // Advance nextNode.
            if (nextNode.left != null) {
                nextNode = nextNode.left;
            } else if (nextNode.right != null) {
                nextNode = nextNode.right;
            } else {
                // We've just visited a leaf node.
                // Go back up the tree until we find a node
                // with a right child that we haven't seen yet.
                Node parent = nextNode.parent;
                Node child = nextNode;
                while (parent != null &&
                        (parent.right == child || parent.right == null)) {
                    child = parent;
                    parent = parent.parent;
                }

                if (parent == null) {
                    nextNode = null;  // the traversal is complete
                } else {
                    nextNode = parent.right;
                }
            }

            return key;
        }
    }

    /* Returns an inorder iterator for this tree. */
    public LinkedTreeIterator inorderIterator() {
        return new InorderIterator();
    }

    /*
     * Inner class for an inorder iterator
     */
    private class InorderIterator implements LinkedTreeIterator{
        private Node nextNode;
        private Node parentNode;

        private InorderIterator() {
            Node trav = root;

            while (trav != null) {
                if (trav.left == null) {
                    nextNode = trav;
                }

                trav = trav.left;
            }
        }

        @Override
        public boolean hasNext() {
            return (nextNode != null);
        }

        @Override
        public int next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }

            // Store a copy of the key to be returned.
            int key = nextNode.key;

            // Advance nextNode
            if (nextNode.right != null) {
                Node node = nextNode.right;
                parentNode = nextNode;

                while (node.left != null) {
                    node = node.left;
                }
                nextNode = node;
            } else {
                // We've just visited a leaf node.
                // Go back up the tree until we find a root node
                // that we haven't seen yet.
                Node parent = nextNode.parent;
                Node child = nextNode;

                while (parent != null && parent.right == child) {
                    child = parent;
                    parent = parent.parent;
                }

                if (parent == null) {
                    nextNode = null;
                } else {
                    nextNode = parent;
                }

            }
            return key;
        }
    }

    /*
     * "wrapper method" for the recursive depthInTree() method
     * from PS 4, Problem 2
     */
    public int depth(int key) {
        if (root == null) {    // root is the root of the entire tree
            return -1;
        }

        return depthInTree(key, root);
    }

    /*
     * original version of the recursive depthInTree() method
     * from PS 4, Problem 2. You will write a more efficient version
     * of this method.
     */
    private static int depthInTree(int key, Node root) {
        if (key == root.key) {
            return 0;     // found
        } else {
            if (root.left != null) {
                int depthInLeft = depthInTree(key, root.left);
                if (depthInLeft != -1) {
                    return depthInLeft + 1;
                }
            }

            if (root.right != null) {
                int depthInRight = depthInTree(key, root.right);
                if (depthInRight != -1) {
                    return depthInRight + 1;
                }
            }

            return -1;    // not found in either subtree
        }
    }

    public int depthIter(int key) {
        int depth = -1;
        Node trav = root;

        while (trav != null) {
            depth++;
            if (trav.key == key) {
                return depth;
            }

            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        return -1;
    }

    /*
     * sumEvensInTree - This method takes the root node of the tree as a whole or a subtree and computes the sum
     * of all the even keys in the tree or subtree.
     */
    private static int sumEvensInTree(Node root) {
        if (root == null) {
            return 0;
        }

        int sum = sumEvensInTree(root.left);
        sum += sumEvensInTree(root.right);

        if (root.key % 2 == 0) {
            sum += root.key;
        }

        return sum;
    }

    /*
     * Wrapper method for the recursive sumEvensInTree method from problem 7.
     */
    public int sumEvens() {
        if (root == null) {
            return 0;
        }

        return sumEvensInTree(root);
    }

    /*
     * deleteMax() - This method deletes the node with the largest key in the tree. It traverses the tree
     * which calls it iteratively to find the largest key. If the node doesn't have children, then the node is simply
     * then parent.right is simply set to null, otherwise, the parent.right is set to the left child of the
     * largest node.
     */

    public int deleteMax() {
        if (root == null) {
            return -1;
        }

        Node trav = root;
        Node maxNode = root;
        Node parent = null;
        int max = root.key;

        while (trav.right != null) {
            parent = trav;
            trav = trav.right;
            maxNode = trav;
            max = maxNode.key;
        }

        Node maxNodeChild;
        if (maxNode.left != null) {
            maxNodeChild = maxNode.left;
        } else {
            maxNodeChild = null;
        }

        if (maxNode == root) {
            root = maxNodeChild;
        } else {
            parent.right = maxNodeChild;
        }
        return max;
    }

    public static void main(String[] args) {

        System.out.println("--- Testing depth()/depthInTree() from Problem 2 ---");
        System.out.println();
        System.out.println("(0) Testing on tree from Problem 7, depth of 13");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);

            System.out.println(getHeight(tree.root));

            int results = tree.depth(13);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(2);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 2);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();    // include a blank line between tests

        System.out.println("--- Testing InorderIterator class, hasNext(), and next() from Problem 8 ---");
        System.out.println();
        System.out.println("(0) Testing Inorder Iterator on tree with root 10");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {10};
            tree.insertKeys(keys);

            LinkedTreeIterator inorderIterator = tree.inorderIterator();
            int[] results = new int[keys.length];

            int count = 0;
            while(inorderIterator.hasNext()) {
                results[count] = inorderIterator.next();
                count++;
            }

            System.out.println("actual results:");
            System.out.println(Arrays.toString(results));
            System.out.println("expected results:");
            System.out.println("[10]");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(Arrays.equals(results, new int[]{10}));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();

        System.out.println("(1) Testing Inorder Iterator on tree with root 31");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {31, 22, 15, 28, 26, 27, 42, 37, 48, 45};
            tree.insertKeys(keys);

            LinkedTreeIterator inorderIterator = tree.inorderIterator();
            int[] results = new int[keys.length];

            int count = 0;
            while(inorderIterator.hasNext()) {
                results[count] = inorderIterator.next();
                count++;
            }

            System.out.println("actual results:");
            System.out.println(Arrays.toString(results));
            System.out.println("expected results:");
            System.out.println("[15, 22, 26, 27, 28, 31, 37, 42, 45, 48]");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(Arrays.equals(results, new int[]{15, 22, 26, 27, 28, 31, 37, 42, 45, 48}));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();

        System.out.println("(2) Testing Inorder Iterator on tree with root 10");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {10, 20, 15, 13, 18, 14, 31, 26};
            tree.insertKeys(keys);

            LinkedTreeIterator inorderIterator = tree.inorderIterator();
            int[] results = new int[keys.length];

            int count = 0;
            while(inorderIterator.hasNext()) {
                results[count] = inorderIterator.next();
                count++;
            }

            System.out.println("actual results:");
            System.out.println(Arrays.toString(results));
            System.out.println("expected results:");
            System.out.println("[10, 13, 14, 15, 18, 20, 26, 31]");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(Arrays.equals(results, new int[]{10, 13, 14, 15, 18, 20, 26, 31}));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();

        System.out.println("(3) Testing Inorder Iterator on tree with root 42");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {42, 35, 37, 39, 32, 33, 20};
            tree.insertKeys(keys);

            LinkedTreeIterator inorderIterator = tree.inorderIterator();
            int[] results = new int[keys.length];

            int count = 0;
            while(inorderIterator.hasNext()) {
                results[count] = inorderIterator.next();
                count++;
            }

            System.out.println("actual results:");
            System.out.println(Arrays.toString(results));
            System.out.println("expected results:");
            System.out.println("[20, 32, 33, 35, 37, 39, 42]");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(Arrays.equals(results, new int[]{20, 32, 33, 35, 37, 39, 42}));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();

        System.out.println("(4) Testing Inorder Iterator on tree with root 42");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {8, 6, 9};
            tree.insertKeys(keys);

            LinkedTreeIterator inorderIterator = tree.inorderIterator();
            int[] results = new int[keys.length];

            int count = 0;
            while(inorderIterator.hasNext()) {
                results[count] = inorderIterator.next();
                count++;
            }

            System.out.println("actual results:");
            System.out.println(Arrays.toString(results));
            System.out.println("expected results:");
            System.out.println("[6, 8, 9]");
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(Arrays.equals(results, new int[]{6, 8, 9}));
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        System.out.println();

        /*
         * Add at least two unit tests for each method from Problem 7.
         * Test a variety of different cases.
         * Follow the same format that we have used above.
         *
         * IMPORTANT: Any tests for your inorder iterator from Problem 8
         * should go BEFORE your tests of the deleteMax method.
         */

        System.out.println("--- Testing depthIter() from Problem 7 ---");
        System.out.println();
        System.out.println("(0) Testing the depth of node with key 5 in an empty tree");

        try {
            LinkedTree tree = new LinkedTree();

            int results = tree.depthIter(5);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(-1);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == -1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("(1) Testing the depth of node with key 8 in a tree with root 10");

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {10, 6, 8, 7, 5};
            tree.insertKeys(keys);

            int results = tree.depthIter(8);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(2);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("(2) Testing the depth of key 7 in a tree with root 1");

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {1, 13, 10, 7, 18, 21};
            tree.insertKeys(keys);

            int results = tree.depthIter(7);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(3);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("(3) Testing the depths of nodes with keys 100 and 25 in a tree with root 10");

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {31, 40, 36, 50, 21, 25, 26, 16};
            tree.insertKeys(keys);

            int results = tree.depthIter(100);
            int results2 = tree.depthIter(25);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println(results2);
            System.out.println("expected results:");
            System.out.println(-1);
            System.out.println(2);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.print(results == -1);
            System.out.println(", " + (results2 == 2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("(4) Testing the depth of node with key 1 in a tree with root 1");

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {1};
            tree.insertKeys(keys);

            int results = tree.depthIter(1);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(0);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("--- Testing sumEvensInTree()/sumEvens() from Problem 7 ---");
        System.out.println();
        System.out.println("(0) Testing on an empty tree");

        try {
            LinkedTree tree = new LinkedTree();

            int results = tree.sumEvens();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(0);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("(1) Testing on a tree with root 19");

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {19, 18, 5, 8, 22, 21, 26};
            tree.insertKeys(keys);

            int results = tree.sumEvens();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(74);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 74);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("(2) Testing on a tree with root 2");

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {2, 16, 7, 4, 21, 30};
            tree.insertKeys(keys);

            int results = tree.sumEvens();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(52);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 52);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("(3) Testing on a tree with root 50");

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {50, 30, 29, 20, 42, 37, 48};
            tree.insertKeys(keys);

            int results = tree.sumEvens();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(190);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 190);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("(4) Testing on a tree with root 1");

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {1};
            tree.insertKeys(keys);

            int results = tree.sumEvens();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(0);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("--- Testing deleteMax() from Problem 7 ---");
        System.out.println();
        System.out.println("(0) Testing on an empty tree");

        try {
            LinkedTree tree = new LinkedTree();

            int results = tree.deleteMax();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(-1);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == -1);
            tree.levelOrderPrint();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("(1) Testing on tree with root 50");

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {50, 30, 45, 21, 7, 25, 5};
            tree.insertKeys(keys);

            int results = tree.deleteMax();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(50);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 50);
            tree.levelOrderPrint();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("(2) Testing on tree with root 10");

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {10, 32, 23, 13, 27, 49, 40, 60, 37, 43, 57};
            tree.insertKeys(keys);

            int results = tree.deleteMax();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(60);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 60);
            tree.levelOrderPrint();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println();
        System.out.println("(3) Testing on tree with root 50");

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {50, 30, 45, 21, 7, 25, 5, 52, 51, 70};
            tree.insertKeys(keys);

            int results = tree.deleteMax();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(70);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 70);
            tree.levelOrderPrint();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("(4) Testing on tree with root 1");

        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {1};
            tree.insertKeys(keys);

            int results = tree.deleteMax();
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(1);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 1);
            tree.levelOrderPrint();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}