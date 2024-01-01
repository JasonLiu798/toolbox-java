package com.atjl.util.file;

import com.atjl.util.collection.CollectionUtilEx;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
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
public class FileUtilExTest {

    @Test
    public void testGetMd5() {
        String f = "D:\\a.pdf";
        String md5 = FileUtilEx.getMd5(f);
        System.out.println("res:" + md5);
    }

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
        String f = FileUtilEx.getFilePathFromClasspath("input.xls");
        System.out.println("res:" + f);

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
        String fileName = "D:\\a.log";
        FileUtilEx.write(fileName, CollectionUtilEx.newList("aaa"));

        String res = FileUtilEx.cat(fileName);
        System.out.println("res:" + res);

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
    public void testCatCp() {
        String res = FileUtilEx.catFromClassPath("jdbc.properties");
        System.out.println("res:" + res);
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
        assertEquals("", FileUtilEx.className2FilePath(""));
        assertEquals("", FileUtilEx.className2FilePath(null));
        assertEquals("haha", FileUtilEx.className2FilePath("haha"));
        assertEquals("com\\haha", FileUtilEx.className2FilePath("com.haha"));
        assertEquals("com\\haha\\HUJSDF", FileUtilEx.className2FilePath("com.haha.HUJSDF"));
    }


    /**
     * Method: getFilesStr(String filepath)
     */
    @Test
    public void testreadFilesByLine() throws Exception {
        File dir = new File(this.getClass().getResource("/").getPath());
        String filename = dir + PathUtil.DIR_OS_SEP + FileUtilEx.className2FilePath(this.getClass().getName()) + ".class";
        System.out.println(filename);
        List<String> list = FileUtilEx.cat2list(filename);
        // CollectionUtilEx.printList(list);
    }

    /**
     * Method: getFileStr(String filepath)
     */
    @Test
    public void testreadFilesByLine2String() throws Exception {
        String res = FileUtilEx.cat("Y:\\yp\\JL.txt");
        System.out.println(res);
    }

    @Test
    public void testDelete() {
        String file = "D:\\t1";
        FileUtilEx.append(file, "aaa");

        boolean exist = FileUtilEx.fileExist(file);
        System.out.println("file exist " + exist);
        FileUtilEx.rm(file);

        exist = FileUtilEx.fileExist(file);
        System.out.println("file exist " + exist);
    }

    @Test
    public void testRmDir() {
        String dir = "D:\\aaa\\bbb\\ccc";
        FileUtilEx.mkDir(dir);
        String dirA = "D:\\aaa";
        FileUtilEx.rm(dirA);
    }

    @Test
    public void testTraverse() throws IOException {
        String dirP = "D:\\d1";
        FileUtilEx.rm(dirP);
        String dir = "D:\\d1\\d2";
        FileUtilEx.mkDir(dir);
        String f1 = PathUtil.join(dirP, "f1");
        String f2 = PathUtil.join(dirP, "f2");
        String f3 = PathUtil.join(dir, "f3");
        FileUtilEx.touch(f1);
        FileUtilEx.touch(f2);
        FileUtilEx.touch(f3);

        List<String> files = FileUtilEx.tree(dirP);
        System.out.println(files);
        for (String f : files) {
            assertEquals(true, FileUtilEx.fileExist(f));
        }

        String notExistDir = "D:\\notexistdir";
        FileUtilEx.rm(notExistDir);
        files = FileUtilEx.tree(notExistDir);
        assertEquals(null, files);
    }


    @Test
    public void testMkdir() {
        //mkdir xx
        String dir = "D:\\d1";
        FileUtilEx.rmDir(dir);
//        String dir = "D:\\d1\\d2";
        FileUtilEx.mkDir(dir);
        assertEquals(true, FileUtilEx.dirExist(dir));

        //mkdir -p d1/d2
        String dir2 = "D:\\d1\\d2";
        FileUtilEx.rmDir(dir);
        FileUtilEx.mkDir(dir2);
        assertEquals(true, FileUtilEx.dirExist(dir2));
    }

    @Test
    public void testTouch() throws IOException {
        String d = "D:\\d1";
        String f1 = PathUtil.join(d, "f1");
        System.out.println(f1);
        FileUtilEx.rm(d);
        FileUtilEx.mkDir(d);
        FileUtilEx.touch(f1);
        assertEquals(true, FileUtilEx.fileExist(f1));
    }

    @Test
    public void testLs() {
        String dir = "D:\\fortest";
        List l = FileUtilEx.ls(dir);
        System.out.println("ls " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l)));
        l = FileUtilEx.ll(dir);
        System.out.println("ll " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l)));
        l = FileUtilEx.ls(dir, "-l -h");
        System.out.println("llh " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l)));
    }


    @Test
    public void testLsRecu() {
        List l = FileUtilEx.ls("D:\\fortest", "-l -h -R");
        System.out.println("llhR " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l)));
    }

    @Test
    public void testLR() {
        List l = FileUtilEx.ls("D:\\fortest", "-l -R");
        System.out.println("lR " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l)));
    }


}
