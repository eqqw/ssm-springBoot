package com.cn.demo;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Node root;
    private List<Node> list = new ArrayList<Node>();

    public Tree() {
        init();
    }
    //树的初始化：先从叶节点开始，由叶到根
    /*
              定义的二叉树为下
                    a
                b       c
             d        e   f
           x   y
     */
    public void init(){
        Node x = new Node("X",null,null);
        Node y = new Node("Y",null,null);
        Node d = new Node("D",x,y);
        Node e = new Node("E",null,null);
        Node f = new Node("F",null,null);
        Node b = new Node("B",d,null);
        Node c = new Node("C",e,f);
        Node a = new Node("A",b,c);
        root =a;
    }


    //定义节点类
    private class Node{
        private String data;
        private Node lchild;//定义左子树的指针
        private Node rchild;//定义右子树的指针

        public Node(String data, Node lchild, Node rchild) {
            this.data = data;
            this.lchild = lchild;
            this.rchild = rchild;
        }
    }

    /**
     * 前序遍历 将结果保留到list集合中 遍历后：ABDXYCEF
     * @param node
     */
    public void proOrder(Node node){
        list.add(node);//先将根节点存入list
        //如果左子树不为空就继续往左找，在递归调用方法的时候一直会将子树的根存入list,这就能做到先遍历根节点
        if (node.lchild!=null){
            proOrder(node.lchild);
        }
        //无论在那一层，只要当前节点的左子树为空，那么就可以在右子树上遍历，保证了根左、右节点的遍历顺序
        if (node.rchild!=null){
            proOrder(node.rchild);
        }
    }

    /**
     * 对二叉树进行中序遍历 结果存到list
     * @param node
     */
    public void inOrder(Node node){
        if (node.lchild!=null){
            inOrder(node.lchild);
        }
        list.add(node);
        if (node.rchild!=null){
            inOrder(node.rchild);
        }
    }
    /**
     * 对二叉树进行后序遍历 结果存到list
     * @param node
     */
    public void postOrder(Node node){
        if (node.lchild!=null){
            postOrder(node.lchild);
        }
        if (node.rchild!=null){
            postOrder(node.rchild);
        }
        list.add(node);
    }

    public int getTreeDepth(Node node){
        if (node.lchild == null && node.rchild == null){
            return 1;
        }
        int left = 0;
        int right = 0;
        if (node.lchild!=null){
            left = getTreeDepth(node.lchild);
        }
        if (node.rchild!=null){
            right = getTreeDepth(node.rchild);
        }
        return left>right?left+1:right+1;
    }
    //获得遍历后的结果
    public List<Node> getResult(){
        return list;
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        System.out.println("根节点："+ tree.root);
        //前序
        //tree.proOrder(tree.root);
        //中序
        //tree.inOrder(tree.root);
        //后序
        tree.postOrder(tree.root);
        for (Node node: tree.getResult()){
            System.out.print(node.data+" ");

        }
        System.out.println(" ");
        System.out.println("节点数：" + tree.list.size());
        System.out.println("树的深度：" + tree.getTreeDepth(tree.root));
    }

}
