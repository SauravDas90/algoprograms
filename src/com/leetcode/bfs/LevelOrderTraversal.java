package com.leetcode.bfs;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

public class LevelOrderTraversal {




/**
 * Definition for a binary tree node.
 * public
 */

    int maxLevel=0,height=0;
    List<Integer> rightView = new LinkedList<>();

    public List<Integer> rightSideView(TreeNode root) {

        if(root == null)
            return Collections.emptyList();

        rightView.add(root.val);

        dfsRightView(root.right,height+1);
        dfsRightView(root.left,height+1);


        return rightView;
    }

    public void dfsRightView(TreeNode node,int height){

        if(node == null)
            return ;

        if(height>maxLevel){

            rightView.add(node.val);
            maxLevel = height;

        }

        dfsRightView(node.right,height+1);
        dfsRightView(node.left,height+1);



    }

    public static void main(String[] args) {
         LevelOrderTraversal lv = new LevelOrderTraversal();



        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        List<Integer> rightvw = lv.rightSideView(root);

        System.out.println(rightvw);

    }
}
