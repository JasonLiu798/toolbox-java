package com.jason798.tree;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree<T> {
    private TreeNode<T> root;
    Map<String, TreeNode> treeMap;

    public Tree() {
        treeMap = new HashMap<>();
    }

    public void addRoot(TreeNode<T> treeNode) {
        root = treeNode;
        treeMap.put(treeNode.getId(),treeNode);
    }

    public boolean add(String parent, TreeNode<T> child) {
        String id = child.getId();
        if (treeMap.containsKey(id)) {
            return false;
        }
        TreeNode parentNode = treeMap.get(parent);
        if (parentNode == null) {
            return false;
        }
        child.setParent(parentNode);
        treeMap.put(child.getId(), child);
        return parentNode.addChild(child);
    }

    public String preOrderTraverse(){
        StringBuilder sb = new StringBuilder();
        preOrderTraverseRecu(sb,this.root);
        return sb.toString();
    }

    public void preOrderTraverseRecu(StringBuilder sb ,TreeNode treeNode){
        sb.append(treeNode).append("\n");
        if(treeNode.hasChild()){
            List<TreeNode> childs =  treeNode.getChilds();
            for(TreeNode treeNodeChild : childs){
                preOrderTraverseRecu(sb,treeNodeChild);
            }
        }
        return;
    }


    public void dfs(TreeNode<T> treeNode){

    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public Map<String, TreeNode> getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(Map<String, TreeNode> treeMap) {
        this.treeMap = treeMap;
    }
}
