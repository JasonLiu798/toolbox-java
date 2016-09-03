package com.jason798.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by JasonLiu798 on 16/6/3.
 */
public class SearchHelper {

	public static <T> int binarySearchRaw(ArrayList<T> srcArray, T tgt, Comparator<T> comparator){
		return Collections.binarySearch(srcArray,tgt,comparator);
	}

    /**
     * find the tgt element index
     * @param srcArray
     * @param tgt
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> int binarySearch(ArrayList<T> srcArray, T tgt, Comparator<T> comparator) {
        return binarySearchRaw(srcArray,tgt,comparator,false);
    }

    /**
     *
     * @param srcArray
     * @param tgt
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> int binarySearchExpect(ArrayList<T> srcArray, T tgt, Comparator<T> comparator) {
        return binarySearchRaw(srcArray,tgt,comparator,true);
    }
    public static <T> int binarySearchRaw(ArrayList<T> srcArray, T tgt, Comparator<T> comparator,boolean findExpect) {
        int low = 0;
        int high = srcArray.size() - 1;
        while (low <= high) {
            //中间位置计算,low+ 最高位置减去最低位置,右移一位,相当于除2.也可以用(high+low)/2
            int middle = low + ((high - low) >> 1);
            //与最中间的数字进行判断,是否相等,相等的话就返回对应的数组下标.
            int cmpRes = comparator.compare(tgt, srcArray.get(middle));
            if (cmpRes == 0) {
                return middle;
                //如果小于的话则移动最高层的"指针"
            } else if (cmpRes < 0) {
                high = middle - 1;
                //移动最低的"指针"
            } else {
                low = middle + 1;
            }
        }
        if (findExpect) {
            if(low==high)
                return low;
            else{
                return Math.max(low, high);
            }
        }
        return -1;//not find
    }


    public static <T> int binarySearchRecu(ArrayList<T> srcArray,int low, int high, T tgt, Comparator<T> comparator) {
        if (low <= high) {
            int mid = (low + high) / 2;
            int cmpRes = comparator.compare(tgt, srcArray.get(mid));
            if (cmpRes == 0)
                return mid;
            else if (cmpRes < 0)
                //移动low和high
                return binarySearchRecu(srcArray, low, mid - 1, tgt,comparator);
            else
                return binarySearchRecu(srcArray, mid + 1, high, tgt,comparator);
        } else
            return -1;
    }



    public static <T> int partition(T[] a, Comparator<? super T> c, int p, int r) {
        T t = a[r - 1];
        int i = p - 1;
        for (int j = p; j < r - 1; j++) {
            if (c.compare(a[j], t) <= 0) {
                i++;
                T tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        T tmp = a[i + 1];
        a[i + 1] = a[r - 1];
        a[r - 1] = tmp;
        return i + 1;
    }
    public static <T> int randomizedPartition(T[] a, Comparator<? super T> c,
                                              int p, int r) {
        int i = new Random().nextInt(r - p) + p;
        T tmp = a[i];
        a[i] = a[r - 1];
        a[r - 1] = tmp;
        return partition(a, c, p, r);
    }
    public static <T> T randomizedSelect(T[] t,
                                         Comparator<? super T> comparator, int p, int r, int i) {
        if (p == r)
            return t[p];
        int q = randomizedPartition(t, comparator, p, r);
        int k = q - p + 1;
        if (i <= k)
            return randomizedSelect(t, comparator, p, q, i);
        else
            return randomizedSelect(t, comparator, q + 1, r, i - k);
    }
    public static <T> T randomizedSelect(T[] t,
                                         Comparator<T> comparator, int i) {
        return randomizedSelect(t, comparator, 0, t.length, i);
    }




}
