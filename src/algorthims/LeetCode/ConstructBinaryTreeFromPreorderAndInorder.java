package algorthims.LeetCode;

public class ConstructBinaryTreeFromPreorderAndInorder {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return construct(0, 0, inorder.length - 1, preorder, inorder);
    }

    private static TreeNode construct(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {

        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (root.val == inorder[i]) {
                inIndex = i;
            }
        }

        root.left = construct(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = construct(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);

        return root;
    }

    private static void inOrder(TreeNode node) {

        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    public static void main(String[] args) {
        TreeNode node = buildTree(new int[]{
                3, 9, 20, 15, 7
        }, new int[]{
                9, 3, 15, 20, 7
        });

        inOrder(node);


    }
}
