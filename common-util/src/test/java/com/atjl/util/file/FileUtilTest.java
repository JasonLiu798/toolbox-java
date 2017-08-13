package com.atjl.util.file;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.PathUtil;
import com.atjl.util.serialize.fst.FstSerializer1;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * FileUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 21, 2016</pre>
 */
public class FileUtilTest {

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
        String f = FileUtil.getFileFromClasspath("input.xls");
        System.out.println("res:"+f);

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

    @Test
    public void testWrite2FileForFilepathContent() throws Exception {

    }

    @Test
    public void testWrite2FileForFilepathContents() throws Exception {

    }

    @Test
    public void testSepClassName() {
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
        String filename = dir + PathUtil.DIR_SEP + FileUtil.className2FilePath(this.getClass().getName()) + ".class";
        System.out.println(filename);
        List<String> list = FileUtil.cat2List(filename);
        CollectionUtil.printList(list);
    }

    /**
     * Method: getFileStr(String filepath)
     */
    @Test
    public void testreadFilesByLine2String() throws Exception {
        String res = FileUtil.cat("Y:\\yp\\JL.txt");
        System.out.println(res);
    }

    @Test
    public void testDelete() {
        String file = "D:\\t1";
        FileUtil.append(file, "aaa");

        boolean exist = FileUtil.fileExist(file);
        System.out.println("file exist " + exist);
        FileUtil.rm(file);

        exist = FileUtil.fileExist(file);
        System.out.println("file exist " + exist);
    }

    @Test
    public void testRmDir() {
        String dir = "D:\\aaa\\bbb\\ccc";
        FileUtil.mkDir(dir);
        String dirA = "D:\\aaa";
        FileUtil.rm(dirA);
    }

    @Test
    public void testTraverse() throws IOException {
        String dirP = "D:\\d1";
        FileUtil.rm(dirP);
        String dir = "D:\\d1\\d2";
        FileUtil.mkDir(dir);
        String f1 = PathUtil.join(dirP, "f1");
        String f2 = PathUtil.join(dirP, "f2");
        String f3 = PathUtil.join(dir, "f3");
        FileUtil.touch(f1);
        FileUtil.touch(f2);
        FileUtil.touch(f3);

        List<String> files = FileUtil.tree(dirP);
        System.out.println(files);
        for (String f : files) {
            assertEquals(true, FileUtil.fileExist(f));
        }

        String notExistDir = "D:\\notexistdir";
        FileUtil.rm(notExistDir);
        files = FileUtil.tree(notExistDir);
        assertEquals(null, files);
    }


    @Test
    public void testMkdir() {
        //mkdir xx
        String dir = "D:\\d1";
        FileUtil.rmDir(dir);
//        String dir = "D:\\d1\\d2";
        FileUtil.mkDir(dir);
        assertEquals(true, FileUtil.dirExist(dir));

        //mkdir -p d1/d2
        String dir2 = "D:\\d1\\d2";
        FileUtil.rmDir(dir);
        FileUtil.mkDir(dir2);
        assertEquals(true, FileUtil.dirExist(dir2));
    }

    @Test
    public void testTouch() throws IOException {
        String d = "D:\\d1";
        String f1 = PathUtil.join(d, "f1");
        System.out.println(f1);
        FileUtil.rm(d);
        FileUtil.mkDir(d);
        FileUtil.touch(f1);
        assertEquals(true, FileUtil.fileExist(f1));
    }

    @Test
    public void testLs(){
        List l = FileUtil.ls("D:\\");
        System.out.println(l);
        l = FileUtil.ll("D:\\");
        System.out.println(l);

    }


}
