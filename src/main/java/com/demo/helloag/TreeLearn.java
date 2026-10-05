package com.demo.helloag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class TreeLearn {
    public static TreeNode build(String[] vals) {
        int n = vals.length;
        assert n >= 1;

        int i = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        queue.offer(root);

        while (i < n && !queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!vals[i].equals("null")) {
                int leftval = Integer.parseInt(vals[i]);
                TreeNode leftNode = new TreeNode(leftval);
                node.left = leftNode;
                queue.offer(leftNode);
            }

            ++i;
            if (i >= n) {
                break;
            }

            if (!vals[i].equals("null")) {
                int rightval = Integer.parseInt(vals[i]);
                TreeNode rightNode = new TreeNode(rightval);
                node.right = rightNode;
                queue.offer(rightNode);
            }
            ++i;
        }
        return root;
    }

    public static void layorTraverse(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] vals = line.split(" ");
        TreeNode root = build(vals);
        layorTraverse(root);
        sc.close();
    }
}

class ArrayBinaryTree {
    private List<Integer> tree;

    public ArrayBinaryTree(List<Integer> arr) {
        tree = new ArrayList<>(arr);
    }

    public int size() {
        return tree.size();
    }

    public Integer val(int i) {
        if (i < 0 || i > size())
            return null;
        return tree.get(i);
    }

    /* get index for left, right and parent */
    public int left(int i) {
        return 2 * i + 1;
    }

    public int right(int i) {
        return 2 * i + 2;
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public List<Integer> layorOrder() {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < size(); ++i) {
            if (val(i) != null) {
                res.add(val(i));
            }
        }
        return res;
    }

    public void dfs(Integer i, String order, List<Integer> res) {
        if (val(i) == null) {
            return;
        }

        if (order.equals("pre"))
            res.add(val(i));

        dfs(left(i), order, res);
        if (order.equals("in"))
            res.add(val(i));
        dfs(right(i), order, res);
        if (order.equals("post"))
            res.add(val(i));
    }

    public List<Integer> preOrder() {
        List<Integer> res = new ArrayList<>();
        dfs(0, "pre", res);
        return res;
    }

    public List<Integer> inOrder() {
        List<Integer> res = new ArrayList<>();
        dfs(0, "in", res);
        return res;
    }

    public List<Integer> postOrder() {
        List<Integer> res = new ArrayList<>();
        dfs(0, "post", res);
        return res;
    }
}

class DSTree {
    private TreeNode root;

    public DSTree(int val) {
        root = new TreeNode(val);
    }

    public TreeNode search(int num) {
        if (root == null)
            return null;

        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == num)
                return cur;
            else if (cur.val < num)
                cur = cur.right;
            else
                cur = cur.left;
        }
        return null;
    }

    public void insert(int num) {
        if (root == null){
            root = new TreeNode(num);
            return;
        }

        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (cur.val == num)
                return; // 已经存在的不再添加
            pre = cur;
            if (cur.val < num)
                cur = cur.right;
            else
                cur = cur.left;
        }
        TreeNode node = new TreeNode(num);
        if (pre.val < num)
            pre.right = node;
        else
            pre.left = node;
    }

    public void remove(int num) {
        // get pre and cur
        if (root == null)
            return;

        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (cur.val == num)
                break;
            pre = cur;
            if (cur.val < num)
                cur = cur.right;
            else
                cur = cur.left;
        }

        if (cur == null)
            return; // 不存在这个节点

        // three cases: 1. no child; 2. one child; 3. two children
        if (cur.left == null || cur.right == null) {
            TreeNode child = cur.left == null ? cur.right : null;
            if (cur != root) {
                if (pre.left == cur)
                    pre.left = child;
                else 
                    pre.right = child;
            } else {
                root = child;
            }
        } else {
            TreeNode tmp = cur.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            remove(tmp.val); // 转变为删除叶子节点
            cur.val = tmp.val;
        }
    }
}