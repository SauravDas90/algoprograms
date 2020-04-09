package com.leetcode.dfs;



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class MaximumBinaryTree

{
    public TreeNode constructMaximumBinaryTree(int[] nums) {

        TreeNode root =  dfsPreorderTailRecursionMaximumElem(null,0,nums.length-1,nums);

        return root;

    }



    public TreeNode dfsPreorderTailRecursionMaximumElem(TreeNode node,int start,int end,int[] nums){
        if(end-start <0)
            return null;

        int maxElem  = nums[start];
        int maxIndex = start;

        for(int i =start; i<=end;i++){
            if(nums[i] > maxElem ){
                maxElem = nums[i];
                maxIndex = i;
            }

        }

        node = new TreeNode(maxElem);

        if(maxIndex-start-1>=0)
            node.left = dfsPreorderTailRecursionMaximumElem(node.left,start,maxIndex-1,nums);
        if(end-maxIndex-1 >= 0)
            node.right = dfsPreorderTailRecursionMaximumElem(node.right,maxIndex+1,end,nums);

        return node;
    }


    public static void main(String[] args) {

        MaximumBinaryTree tree = new MaximumBinaryTree();
        int nums[] = {3,2,1,6,0,5};
        int nums1[] = {8,7,6,5,4,3,2,1};
        int nums2[] = {1,3,5,9,11,13};
       // int nums[] = {6,0,5};
       TreeNode root =  tree.constructMaximumBinaryTree(nums2);
    }



}