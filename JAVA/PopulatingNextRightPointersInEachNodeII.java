/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root != null) {
            List<TreeLinkNode> nodes = new ArrayList<TreeLinkNode>(Arrays.asList(root));
            List<TreeLinkNode> temp = new ArrayList<TreeLinkNode>();
            int i = 0;
            while (i < nodes.size()) {
                while (i < nodes.size()) {
                    if (i != nodes.size() - 1)
                        nodes.get(i).next = nodes.get(i + 1);
                    if (nodes.get(i).left != null)
                        temp.add(nodes.get(i).left);
                    if (nodes.get(i).right != null)
                        temp.add(nodes.get(i).right);
                    i++;
                }
                nodes.addAll(temp);
                temp.clear();
            }
        }
    }
}

// constant space
public class Solution2 {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode pre = root, cur = null, prePointer = null;
        while (pre != null) {
            cur = pre;
            while (cur != null) {
                if (cur.left != null) {
                    if (prePointer == null) prePointer = cur.left;
                    else {
                        prePointer.next = cur.left;
                        prePointer = prePointer.next;
                    }
                }
                if (cur.right != null) {
                    if (prePointer == null) prePointer = cur.right;
                    else {
                        prePointer.next = cur.right;
                        prePointer = prePointer.next;
                    }
                }
                cur = cur.next;
            }
            prePointer = null;
            pre = (pre.left == null) ? pre.right : pre.left;
            while (pre != null && pre.left == null && pre.right == null)
                pre = pre.next;
        }
    }
}

// level by level, recursive
public class Solution {
    public void connect(TreeLinkNode root) {
        while (root != null && root.left == null && root.right == null) root = root.next;
        if (root == null) return;
        TreeLinkNode node, pre;
        if (root.left == null) pre = root.right;
        else {
            pre = root.left;
            if (root.right != null) {
                pre.next = root.right;
                pre = pre.next;
            }
        }
        node = root.next;
        while (node != null) {
            if (node.left != null) {
                pre.next = node.left;
                pre = pre.next;
            }
            if (node.right != null) {
                pre.next = node.right;
                pre = pre.next;
            }
            node = node.next;
        }
        if (root.left != null) connect(root.left);
        else connect(root.right);
    }
}