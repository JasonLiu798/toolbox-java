package com.jason798.file;

import com.jason798.performance.RunTimeUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * FileNioUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>七月 2, 2016</pre>
 */
public class FileHelperNioTest {

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
	}


	@Test
	public void testWrite(){
//		FileNioUtil.appendFile("Y:\\aaa1.txt","aaa");
		FileNioUtil.writeFile("Y:\\aaa.txt","bbb");

	}

	/**
	 * Method: readBigFile(String filepath)
	 */
	@Test
	public void testReadBigFile() throws Exception {
		RunTimeUtil.addTime();
//		FileNioUtil.readBigFile("Y:\\img\\IMG_0003.CR2");
		RunTimeUtil.addTime();
		System.out.println(RunTimeUtil.getFmtTimeForStdout());

	}


} 
