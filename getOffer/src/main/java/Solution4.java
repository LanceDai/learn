
//Definition for binary tree

import java.util.ArrayDeque;

class TreeNode_1 {
    int val;
    TreeNode_1 left;
    TreeNode_1 right;

    TreeNode_1(int x) {
        val = x;
    }
}

public class Solution4 {
    public static void main(String[] args) {
        int[] pre = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = new int[]{4, 7, 2, 1, 5, 3, 8, 6};
        showTree(new Solution4().reConstructBinaryTree(pre, in));
    }

    public static void showTree(TreeNode_1 root) {
        ArrayDeque<TreeNode_1> queue = new ArrayDeque<TreeNode_1>();
        queue.push(root);
        System.out.println(root.val);
        while (!queue.isEmpty()) {
            TreeNode_1 tmp = queue.getFirst();
            queue.pop();
            if (tmp.left != null) {
                System.out.print(tmp.left.val);
                queue.push(tmp.left);
            } else {
                System.out.print("\t");
            }
            System.out.print("\t");
            if (tmp.right != null) {
                System.out.print(tmp.right.val);
                queue.push(tmp.right);
            } else {
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public TreeNode_1 reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0) return null;
        TreeNode_1 root = new TreeNode_1(pre[0]);
        int index = 0;
        for (int i = 0; i < in.length; ++i) {
            if (pre[0] == in[i]) {
                index = i;
                break;
            }
        }
        int[] leftPre = new int[index];
        int[] leftIn = new int[index];
        int[] rightPre = new int[in.length - index - 1];
        int[] rightIn = new int[in.length - index - 1];
        for (int i = 0; i < index; i++) {
            leftIn[i] = in[i];
            leftPre[i] = pre[1 + i];
        }
        for (int i = 0; i < in.length - index - 1; i++) {
            rightIn[i] = in[index + i + 1];
            rightPre[i] = pre[1 + index + i];
        }
        root.left = reConstructBinaryTree(leftPre, leftIn);
        root.right = reConstructBinaryTree(rightPre, rightIn);
        return root;
    }
}