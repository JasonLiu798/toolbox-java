package com.jason798.tree;

import com.jason798.collection.CollectionUtil;

import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> {

    private String id;
    private T data;
    private TreeNode parent;
    private List<TreeNode> childs = new LinkedList<>();

    public TreeNode(String id) {
        this.id = id;
    }


    public boolean hasChild() {
        if (CollectionUtil.isEmpty(childs)) {
            return false;
        }
        return true;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public boolean addChild(TreeNode node) {
        return childs.add(node);
    }

    public TreeNode() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<TreeNode> getChilds() {
        return childs;
    }

    public void setChilds(List<TreeNode> childs) {
        this.childs = childs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TreeNode{").append("id='" + id);//+ '\'' +
        sb.append(", data=" + data);
        if(parent!=null) {
            sb.append(", parent=" + parent.getId());
        }else{
            sb.append(", parent= null ");
        }
        if(hasChild()){
            sb.append(", childs=");
            for(TreeNode treeNode:this.childs){
                sb.append(treeNode.getId()).append(",");
            }
        }else{
            sb.append(", childs = null");
        }
        return sb.toString();
    }
}
