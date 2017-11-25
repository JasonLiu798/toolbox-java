package com.atjl.util.reflect;

import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import com.atjl.util.reflect.dto.DestLong2IntegerDto;
import com.atjl.util.reflect.dto.SrcDto;
import org.junit.Test;

/**
 * Created by async on 2017/11/23.
 */
public class CopyTest {
	
	
	@Test
	public void testCopy(){
		
		SrcDto src = new SrcDto();
		src.setB(true);
		src.setI(1);
		src.setSs("xxxxx");
		src.setL(123L);
		
//		DestInteger2LongDto dest = new DestInteger2LongDto();
		DestLong2IntegerDto dest = new DestLong2IntegerDto();
		
		System.out.println("bf:"+ JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(src))+","+JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dest)));
		
		ReflectFieldUtil.copyFieldUsePU(src,dest);
		
		System.out.println("af:"+ JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(src))+","+JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dest)));
		
		
	}
	
	@Test
	public void testCopyBU(){
		
		SrcDto src = new SrcDto();
		src.setB(true);
		src.setI(1);
		src.setSs("xxxxx");
		src.setL(123L);

//		DestInteger2LongDto dest = new DestInteger2LongDto();
		DestLong2IntegerDto dest = new DestLong2IntegerDto();
		
		System.out.println("bf:"+ JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(src))+","+JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dest)));
		
		ReflectFieldUtil.copyFieldUseBU(src,dest);
		
		System.out.println("af:"+ JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(src))+","+JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dest)));
		
		
	}
	
}
