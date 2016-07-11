package com.jason798.file;

import com.jason798.performance.RunTimeHelper;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Collections;

/**
 * FileHelperNio Tester.
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
//		FileHelperNio.appendFile("Y:\\aaa1.txt","aaa");
		FileHelperNio.writeFile("Y:\\aaa.txt","bbb");

	}

	/**
	 * Method: readBigFile(String filepath)
	 */
	@Test
	public void testReadBigFile() throws Exception {
		RunTimeHelper.addTime();
//		FileHelperNio.readBigFile("Y:\\img\\IMG_0003.CR2");
		RunTimeHelper.addTime();
		System.out.println(RunTimeHelper.getFmtTimeForStdout());

	}


} 
