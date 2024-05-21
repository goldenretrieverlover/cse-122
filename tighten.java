// This class represents a tree of integers.
public class IntTree {
    private IntTreeNode overallRoot;

    public void tighten() {
        overallRoot = tighten(overallRoot);
    }

    private IntTreeNode tighten(IntTreeNode curr) {
        if (curr != null) { // WANT to check every time this time
            curr.left = tighten(curr.left);
            curr.right = tighten(curr.right); // i know u get confused with nested loops sometimes
            if (curr.left != null && curr.right == null) {
                curr = curr.left;
            } else if (curr.left == null && curr.right != null) {
                curr = curr.right; // bc recursive is else if
            } // valid child is base case, return it originally
        }
        return curr;
    }

    // public void tighten() {
    //     overallRoot = tighten(overallRoot);
    // }

    // private IntTreeNode tighten(IntTreeNode root) {
    //     if (root != null) {
    //         // do something
    //         if (root.left != null && root.right == null) {
    //             root = tighten(root.left);
    //         } else if (root.left == null && root.right != null) {
    //             root = tighten(root.right);
    //         } else {
    //             root.left = tighten(root.left);
    //             root.right = tighten(root.right);
    //         }
    //         // if (root.left != null && root.right == null) {
    //         //     root = root.left;
    //         // } else if (root.right != null && root.left == null) {
    //         //     root = root.right;
    //         // }
    //     }
    //     return root; // never have to worry about returning root if null, reads a little better
    // }

    // private IntTreeNode tighten(IntTreeRoot) {
    //     if (root != null) {
    //         // do something
    //         if (root.left != null && root.right == null) {
    //             root = root.left;
    //         } else if (root.left == null && root.right != null) {
    //             root = root.right;
    //         }
    //         root.left = tighten(root.left);
    //         root.right = tighten(root.right);
    //     }
    //     return root; // never have to worry about returning root if null, reads a little better
    // }

    // private IntTreeNode tighten(IntTreeNode root) {
    //     if (root == null) { // treat as a read only problem, modification later
    //         return root;
    //     } else if (root.left == null && root.right != null) {
    //         if ((root.right.left == null && root.right.right != null) || (root.right.left != null && root.right.right == null)) {
    //             return null;
    //         } else {
    //             return root.right.right;
    //         } 
    //     } else if (root.left != null && root.right == null) {
    //         if ((root.left.left == null && root.left.right != null) || (root.left.left != null && root.left.right == null)) {
    //             return null;
    //         } else {
    //             return root.left.right;
    //         }
    //     } else { // x = changeX
    //         root.left = tighten(root.left); // recursively do something to left
    //         root.right = tighten(root.right); // recursively do something to right
    //         return root;
    //     }
    // }

    // if (root == null) { // want to account for another case
    //     return root; // something is a leaf if it's null on both left and right
    // } else if (root.left == null && root.right == null) { // treating it as a read only problem first, modification later
    //     return null; // that gets rid of it
    // } else { // x = change(x);
    //     root.left = removeLeaves(root.left); // recursively do something to the left
    //     root.right = removeLeaves(root.right); // recursively do something to the left
    //     return root; // don't return left or right specifically, root has both those changes
    // }

    public static void main(String[] args) {
        IntTree tree = new IntTree("[2 [8 [7 [4] [1 null [3]]]] [9 [6 null [0 [4] [5]]]]]");
        tree.tighten();
        System.out.println(tree);
    }

//////////////////////////////////////////////

    // Constructs a tree with default numbers.
    public IntTree() {
        overallRoot = null;
    }

    public IntTree(String s) {
        overallRoot = fromString(new StringBuilder(s.toLowerCase().trim()));
    }

    public IntTreeNode getRoot() {
        return overallRoot;
    }

    public void setRoot(IntTreeNode root) {
        overallRoot = root;
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

    public boolean equals(Object o) {
        if (o instanceof IntTree) {
            IntTree other = (IntTree) o;
            return toString().equals(other.toString());
        } else {
            return false;
        }
    }

    // post: Returns a text representation of the tree.
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

    private IntTreeNode fromString(StringBuilder s) {
        String next = nextToken(s);
        if (next.length() == 0 || next.equals("null") || next.equals("/")) {
            return null;
        } else {
            next = next.substring(1, next.length() - 1).trim();  // remove [] from ends
            StringBuilder nextBuilder = new StringBuilder(next);
            String rootStr  = nextToken(nextBuilder);
            int data = Integer.parseInt(rootStr);
            String leftStr  = nextToken(nextBuilder);
            String rightStr = nextToken(nextBuilder);
            return new IntTreeNode(data,
                                   fromString(new StringBuilder(leftStr)),
                                   fromString(new StringBuilder(rightStr)));
        }
    }

    // Returns string representation of next complete node or data value from given buffer.
    private String nextToken(StringBuilder s) {
        while (s.indexOf(" ") == 0) {
            s.deleteCharAt(0);
        }
        if (s.length() == 0) {
            return "";
        }

        int i = 0;
        if (s.charAt(0) == '[' || s.charAt(0) == '(') {
            int depth = 0;
            do {
                if (s.charAt(i) == '[' || s.charAt(i) == '(') {
                    depth++;
                } else if (s.charAt(i) == ']' || s.charAt(i) == ')') {
                    depth--;
                }
                i++;
            } while (i < s.length() && depth > 0);
            if (depth > 0) {
                throw new IllegalArgumentException("missing closing bracket in " + s);
            }
        } else {
            while (i < s.length() && s.charAt(i) != ' ') {
                i++;
            }
        }

        String result = s.substring(0, i).trim();
        s.delete(0, i);
        while (s.indexOf(" ") == 0) {
            s.deleteCharAt(0);
        }
        return result;
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
