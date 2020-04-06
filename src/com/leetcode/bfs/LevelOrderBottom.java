package com.leetcode.bfs;



//import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeNode;
import java.util.*;

class TreeNode1{
    int val;
    TreeNode1 left;
    TreeNode1 right;
    TreeNode1(int x){
        val = x;
    };

}


public class LevelOrderBottom {

    public static List<List<Integer>> levelOrderBottom(TreeNode1 root) {

        List<List<Integer>> bottomUp = new ArrayList<List<Integer>>();
        List<Integer> rootLevel = new LinkedList<>();

        Queue<TreeNode1> fullNode = new LinkedList<>();
        fullNode.offer(root);
        rootLevel.add(root.val);
        bottomUp.add(0,rootLevel);
        int height = 0;

        Queue<TreeNode1> levelNodes = new LinkedList<>();

        while(!levelNodes.isEmpty() || height ==0) {
            List<Integer> levelElement = new LinkedList<>();

            while(!fullNode.isEmpty()){

                TreeNode1 child = fullNode.poll();
                levelElement.add(child.val);

                if(child.left != null){
                    levelNodes.offer(child.left);
                }
                if(child.right != null){
                    levelNodes.offer(child.right);
                }

            }
            height++;
            fullNode.addAll(levelNodes);
            bottomUp.add(height,levelElement);
            levelNodes.clear();
        }

        //  return Collections.reverse(bottomUp);
        System.out.println(Arrays.asList(bottomUp));
        return bottomUp;
    }

    public static void main(String[] args) {
        TreeNode1 root = new TreeNode1(3);
        root.left = new TreeNode1(9);
        root.right = new TreeNode1(20);
        root.right.left = new TreeNode1(15);
        root.right.right = new TreeNode1(7);

        levelOrderBottom(root);
    }

}
