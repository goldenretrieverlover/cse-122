// An IntTree represents a binary tree of integers
public class IntTree {
    private IntTreeNode overallRoot;

    // post: Returns true if o is an IntTree with the same values
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof IntTree)) {
            return false;
        } else {
            IntTree other = (IntTree) o;
            return equals(overallRoot, other.overallRoot); // wanna keep track of this 
            // root and other root, so doesn't make sense to 
            // do recursion in this method specifically
        }
    }


    private boolean equals(IntTreeNode root, IntTreeNode o) { // makes more sense to do recursion in this method specifically
        if (root == null || o == null) { // more handles structure
            return root == o; // private helper method
        } else { // if (root != null && o.root != null)
            if (root.data == o.data) {
                return (equals(root.left, o.left) && equals(root.right, o.right)); // recursive case also handles that data property check
            } else {
                return false; // false;
            }
        }
        
    }

    public static void main(String[] args) {
        IntTree t1 = new IntTree("[1 [2] [3]]");
        IntTree t2 = new IntTree("[1 [2] [7]]");
        System.out.println(t1.equals(t2));
    }

////////////////////////////////////////////////

    // Constructs a tree with default numbers
    public IntTree() {
        overallRoot = null;
    }

    // Constructs a tree from the given text representation
    public IntTree(String s) {
        overallRoot = fromString(s);
    }

    // post: Prints the numbers in this tree in a pre-order fashion.
    public void print() {
        print(overallRoot);
    }

    private void print(IntTreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            print(root.left);
            print(root.right);
        }
    }

    // post: Returns a text representation of the tree
    public String toString() {
        return toString(overallRoot);
    }

    private String toString(IntTreeNode root) {
        if (root == null) {
            return "null";
        } else if (root.left == null && root.right == null) {
            return "[" + root.data + "]";
        } else {
            return "[" + root.data + " "
                + toString(root.left) + " "
                + toString(root.right) + "]";
        }
    }

    private static IntTreeNode fromString(String s) {
        s = s.trim();
        if (s.isEmpty() || s.equals("null")) {
            return null;
        }
        s = s.substring(1, s.length() - 1);
        try {
            return new IntTreeNode(Integer.parseInt(s.trim()));
        } catch (NumberFormatException e) {
            String[] pair = s.trim().split(" +", 2);
            int data = Integer.parseInt(pair[0]);
            int index = splitIndex(pair[1]);
            String left = pair[1].substring(0, index);
            String right = pair[1].substring(index);
            return new IntTreeNode(data, fromString(left), fromString(right));
        }
    }

    private static int splitIndex(String s) {
        if (s.startsWith("null")) {
            return 4;
        }
        int brackets = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                brackets += 1;
            } else if (c == ']') {
                brackets -= 1;
            }
            if (brackets == 0) {
                return i + 1;
            } else if (brackets < 0) {
                throw new IllegalArgumentException("bad brackets: " + s);
            }
        }
        throw new IllegalArgumentException("bad brackets: " + s);
    }

    // Class that represents a single node in the tree.
    private static class IntTreeNode {
        public int data;
        public IntTreeNode left;
        public IntTreeNode right;

        // Constructs a leaf node with the given data.
        public IntTreeNode(int data) {
            this(data, null, null);
        }

        // Constructs a leaf or branch node with the given data and links.
        public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
