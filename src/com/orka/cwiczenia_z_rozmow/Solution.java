package com.orka.cwiczenia_z_rozmow;

import java.util.HashMap;

public class Solution {


    public static void main(String[] args) {
        Tree tree = newTree(4);
        tree.left= newTree(2);
        tree.right = newTree(7);
        tree.left.left=newTree(1);
        tree.left.right= newTree(3);
        tree.right.left= newTree(4);
        tree.left.left.left= newTree(3);

        System.out.println(largestUinquePath(tree));

    }



    public static Tree newTree(int data)
    {
        Tree temp = new Tree(data);
        temp.left = temp.right = null;
        return temp;
    }

    public static int largestUniquePathUtil(Tree node, HashMap<Integer, Integer> m)
    {
        if (node == null)
            return m.size();

        // put this node into hash
        if(m.containsKey(node.x))
        {
            m.put(node.x, m.get(node.x) + 1);
        }
        else
        {
            m.put(node.x, 1);
        }

        int max_path = Math.max(largestUniquePathUtil(node.left, m),
                largestUniquePathUtil(node.right, m));

        // remove current node from path "hash"
        if(m.containsKey(node.x))
        {
            m.put(node.x, m.get(node.x) - 1);
        }

        // if we reached a condition where all duplicate value
        // of current node is deleted
        if (m.get(node.x) == 0)
            m.remove(node.x);

        return max_path;
    }

    // A utility function to find long unique value path
    static int largestUinquePath(Tree node)
    {
        if (node == null)
            return 0;

        // hash that store all node value
        HashMap<Integer,
                Integer> hash = new HashMap<Integer,
                Integer>();

        // return max length unique value path
        return largestUniquePathUtil(node, hash);
    }


}

class Tree {

    public int x;
    public Tree left;
    public Tree right;

    public Tree(int x) {
        this.x = x;
    }
}
