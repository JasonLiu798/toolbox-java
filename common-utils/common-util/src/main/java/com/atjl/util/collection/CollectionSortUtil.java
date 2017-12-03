package com.atjl.util.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 排序相关
 * Created by JasonLiu798 on 16/5/18.
 */
public class CollectionSortUtil {
	private CollectionSortUtil(){
		throw new UnsupportedOperationException();
	}
    private static final Logger log = LoggerFactory.getLogger(CollectionSortUtil.class);
	
	public static <T extends Comparable<? super T>> List<T> sort(List<T> list) {
		Collections.sort(list);
		return list;
	}
	
	public static <T> List<T> sort(List<T> list, Comparator<? super T> c) {
		Collections.sort(list,c);
		return list;
	}
	

    /**
     * for insert sort,get first
     * @param list
     * @param comparator
     * @param addItem
     * @param <T>
     * @return
     */
    public static <T>  int getFirstEqualBigerOrSmallerIndex(List<T> list,Comparator<T> comparator,T addItem){
        return getFirstEqualBigerOrSmallerIndex(list,0,list.size(),comparator,addItem);
    }

    /**
     *
     * @param list
     * @param addItem
     * @param start
     * @param end
     * @param comparator
     * @param <T>
     * @return start ~ end+1 ATTENTION: if user insert
     */
    public static <T>  int getFirstEqualBigerOrSmallerIndex(List<T> list,int start,int end,Comparator<T> comparator,T addItem){
        int len = list.size();
        //int searchLen = end-start+1;
        int insertIdx = -1;
        if(start>len || end>len){
            return insertIdx;
        }
        boolean allBelow = true;
        boolean isEqual = false;
        log.debug("get index len {},start {},end {}",list.size(),start,end);
        log.debug("list:");
        CollectionUtil.printList(list, 0);
        log.debug("item {}",addItem);
        for(int i=start;i<end;i++){
            T item = list.get(i);
            int compRetCode = comparator.compare(item, addItem);
            // log.debug(i + ",item " + item + ",addItem " +addItem+" comp code "+compRetCode);
            if(compRetCode >=0){
                allBelow = false;
                insertIdx = i;
                break;
            }
        }
        log.debug("insert index "+insertIdx);
//        if(!isEqual){
//            if(insertIdx!=start){
//                insertIdx=insertIdx-1;
//            }
//        }
        if(allBelow){
            return end+1;//
        }else{
            return insertIdx;
        }
    }

}
