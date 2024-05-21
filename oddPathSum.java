// An IntTree represents a binary tree of integers
public class IntTree {
    private IntTreeNode overallRoot;

    public int oddPathSum() {
        int special = overallRoot.data;
        return oddPathSum(overallRoot, special);
    }

    private int oddPathSum(IntTreeNode root, int special) {
        int current = 0;
        int count = 0;
        if (root == null) {
            return 0;
        }
        if (root != null) {
            current = special + root.data;
            if (current % 2 == 1) {  
                count++;
            } else {
                count += oddPathSum(root.left, special);
                count += oddPathSum(root.right, special);
                // if (root.data + oddPathSum(root.left) % 2 == 1) {
                //     count++;
                // }
                // if (root.data + oddPathSum(root.right) % 2 == 1) {
                //     count++;
                // }
            }
            // count += oddPathSum(root.left);
            // count += oddPathSum(root.right);
            // if (root.left != null) {
            //     IntTreeNode next = root.left;
            // } else if (root.right != null) {
            //     IntTreeNode next = root.right;
            // } 
        }
        return count;
    }

/////////////////////////////////////////////

    // Constructs a tree with default numbers
    public IntTree() {
        overallRoot = null;
    }

    // Constructs a tree from the given array representation
    public IntTree(int[] values) {
        overallRoot = buildTree(values, 0);
    }

    // Constructs a tree from the given text representation
    public IntTree(String s) {
        overallRoot = fromString(s);
    }    

    private IntTreeNode buildTree(int[] values, int index) {
        if (index >= values.length) {
            return null;
        }
        IntTreeNode root = new IntTreeNode(values[index]);
        root.left = buildTree(values, index * 2 + 1);
        root.right = buildTree(values, index * 2 + 2);
        return root;
    }

    // Construct a new binary tree from the provided string representation
    private static IntTreeNode fromString(String s) {
        s = s.trim();
        if (s.isEmpty() || s.equals("[]")) {
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
        if (s.startsWith("[]")) {
            return 2;
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

    // post: Returns true if o is an IntTree with the same values
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof IntTree)) {
            return false;
        } else {
            IntTree other = (IntTree)o;
            return toString().equals(other.toString());
        }
    }

    // post: Returns a text representation of the tree
    public String toString() {
        return toString(overallRoot);
    }

    private String toString(IntTreeNode root) {
        if (root == null) {
            return "[]";
        } else if (root.left == null && root.right == null) {
            return "[" + root.data + "]";
        } else {
            String result = "[" + root.data + ", ";
            result += toString(root.left);
            result += ", " + toString(root.right) + "]";
            return result;
        }
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
