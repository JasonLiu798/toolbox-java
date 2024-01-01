package com.atjl.util.test;

import com.atjl.util.file.FileUtilEx;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by async on 2016/8/28.
 */
public class TestUtil {
	private TestUtil(){
		throw new UnsupportedOperationException();
	}

	/**
	 * compare file1's data is equal to file2's data
	 * @param file1 producer send data
	 * @param file2 consumer received data
	 * @return lost data
	 */
	public static List<String> fileDataCompare(String file1, String file2){
//        Map<String,List<String>> res = new HashMap<>();
//        res.put(file1,);
		List<String> lostData = new LinkedList<>();
		List<String> fileList1 = FileUtilEx.cat2list(file1);
		List<String> fileList2 = FileUtilEx.cat2list(file2);
		Set<String> file1Set = new HashSet<>();
		file1Set.addAll(fileList2);
		for(String f1Str:fileList1){
			if(  !file1Set.contains(f1Str)){
				lostData.add(f1Str);
			}
		}
		return lostData;
	}
}
