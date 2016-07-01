package com.jason798.file;

import com.jason798.serialize.SerializerHelper;
import com.jason798.serialize.fst.FstSerializer1;
import com.jason798.test.TestDto;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * FileHelper Tester.
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
        byte[] content = SerializerHelper.serialize(fd);
        FileHelper.write2File(content);


        byte[] readByte = FileHelper.readBinaryFile();
        for(int i=0;i<readByte.length;i++){
            char c=(char)readByte[i];
            System.out.print(c+",");
        }

        System.out.println("");
        FileDto fda = (FileDto) SerializerHelper.deserialize(readByte);
        System.out.println(fda);
        */

    }

    @Test
    public void testWrite2FileS2() throws Exception {
        FstSerializer1 fs = new FstSerializer1();

//        TestDto fd = new TestDto(1111,2222,333);
//        byte[] content = fs.serialize(fd);
//        FileHelper.write2File(content);

        byte[] readByte = FileHelper.readBinaryFile();
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
        TestDto fda = (TestDto)SerializerHelper.deserialize(readByte);
        System.out.println(fda);

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

    /**
     * Method: getFilesStr(String filepath)
     */
    @Test
    public void testGetFilesStr() throws Exception {

    }

    /**
     * Method: getFileStr(String filepath)
     */
    @Test
    public void testGetFileStr() throws Exception {

    }

}
