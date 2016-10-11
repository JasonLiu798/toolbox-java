package com.jason798.file;

import com.jason798.collection.CollectionUtil;
import com.jason798.common.PathUtil;
import com.jason798.serialize.fst.FstSerializer1;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * FileUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 21, 2016</pre>
 */
public class FileHelperTest {

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    /**
     * Method: fileExist(String filepath)
     */
    @Test
    public void testFileExist() throws Exception {

    }

    /**
     * Method: copy(File srcFile, String destPath)
     */
    @Test
    public void testCopy() throws Exception {

    }

    /**
     * Method: readFile(String path)
     */
    @Test
    public void testReadFile() throws Exception {

    }

    /**
     * Method: write2File(byte[] content)
     */
    @Test
    public void testWrite2FileContent() throws Exception {
        /*
        FileDto fd = new FileDto("aaa","bbb");
        byte[] content = SerializerUtil.serialize(fd);
        FileUtil.write2File(content);


        byte[] readByte = FileUtil.readBinaryFile();
        for(int i=0;i<readByte.length;i++){
            char c=(char)readByte[i];
            System.out.print(c+",");
        }

        System.out.println("");
        FileDto fda = (FileDto) SerializerUtil.deserialize(readByte);
        System.out.println(fda);
        */

    }

    @Test
    public void testWrite2FileS2() throws Exception {
        FstSerializer1 fs = new FstSerializer1();

//        TestDto fd = new TestDto(1111,2222,333);
//        byte[] content = fs.serialize(fd);
//        FileUtil.write2File(content);
		/*
        byte[] readByte = FileUtil.readBinaryFile();
        StringBuffer csb = new StringBuffer();
        StringBuffer bsb = new StringBuffer();
        for(int i=0;i<readByte.length;i++){
            char c=(char)readByte[i];
            csb.append(c).append(",");
            bsb.append(readByte[i]).append(",");
            //System.out.print(c+",");
        }
        System.out.println(csb.toString());
        System.out.println(bsb.toString());
        System.out.println("byte length "+readByte.length);

        System.out.println("");
        //TestDto fda = (TestDto) fs.deserialize(readByte);
        TestDto fda = (TestDto)SerializerUtil.deserialize(readByte);
        System.out.println(fda);
		*/
    }

    /**
     * Method: write2File(String filepath, byte[] content)
     */
    @Test
    public void testWrite2FileForFilepathContent() throws Exception {

    }

    /**
     * Method: write2File(String filepath, List<String> contents)
     */
    @Test
    public void testWrite2FileForFilepathContents() throws Exception {

    }


	@Test
	public void testSepClassName(){
//		System.out.println(FileUtil.className2FilePath(this.getClass().getName()));
		assertEquals("", FileUtil.className2FilePath(""));
		assertEquals("", FileUtil.className2FilePath(null));
		assertEquals("haha", FileUtil.className2FilePath("haha"));
		assertEquals("com\\haha", FileUtil.className2FilePath("com.haha"));
		assertEquals("com\\haha\\HUJSDF", FileUtil.className2FilePath("com.haha.HUJSDF"));
	}


    /**
     * Method: getFilesStr(String filepath)
     */
    @Test
    public void testreadFilesByLine() throws Exception {
		File dir = new File(this.getClass().getResource("/").getPath());
		String filename = dir+ PathUtil.DIR_SEP + FileUtil.className2FilePath(this.getClass().getName())+".class";
		System.out.println(filename);
		List<String> list = FileUtil.readFilesByLine2StringList(filename);
		CollectionUtil.printList(list);
	}

    /**
     * Method: getFileStr(String filepath)
     */
    @Test
    public void testreadFilesByLine2String() throws Exception {
		String res = FileUtil.readFilesByLine2String("Y:\\yp\\JL.txt");
		System.out.println(res);
	}

	@Test
    public void testDelete(){
	    String file = "D:\\t1";
	    FileUtil.writeLines2File(file,"aaa");

        boolean exist = FileUtil.fileExist(file);
        System.out.println("file exist "+exist);
        FileUtil.delete(file);

        exist = FileUtil.fileExist(file);
        System.out.println("file exist "+exist);
    }

    @Test
    public void testDeletDir(){
        String dir = "D:\\aaa\\bbb\\ccc";
        FileUtil.makeDirs(dir);
        String dirA = "D:\\aaa";
        FileUtil.delete(dirA);
    }

}
