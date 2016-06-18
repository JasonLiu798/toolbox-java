package com.jason798.collection;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * sorted collection helper
 * Created by JasonLiu798 on 16/6/3.
 */
public class SortedCollectionHelper {

    public <T> void insert2SortedArrayList(ArrayList<T> list,T tgt,Comparator<T> comparator){
        int res = SearchHelper.binarySearchExpect(list, tgt,comparator);
        list.add(res,tgt);
    }

}
