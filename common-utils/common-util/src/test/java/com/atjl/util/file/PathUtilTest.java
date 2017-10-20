package com.atjl.util.file;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class PathUtilTest {


    @Test
    public void testGetPathSep() throws Exception {
//        String fp = "d:\\ahah";
        assertEquals("", PathUtil.getFileName(""));
        assertEquals("ahah", PathUtil.getFileName("D:\\ahah"));
        assertEquals("ahah", PathUtil.getFileName("ahah"));
        assertEquals("ahah", PathUtil.getFileName("D:\\shshhs\\ahah"));
        assertEquals("ahah", PathUtil.getFileName("/shshhs/ahah"));
        assertEquals("", PathUtil.getFileName("/shshhs/ahah/"));

    }

    @Test
    public void testIsUnknownPathSep() throws Exception {

    }

    @Test
    public void testParseInner() throws Exception {

    }

    @Test
    public void testGetFileName() throws Exception {

    }

    @Test
    public void testParseExt() throws Exception {

    }

    @Test
    public void testNormalize() throws Exception {

    }

    @Test
    public void testRelative() throws Exception {

    }

    @Test
    public void testJoin() throws Exception {

    }

    @Test
    public void testJoinInnerRelative() throws Exception {

    }

    @Test
    public void testJoinInnerAbs() throws Exception {

    }

    @Test
    public void testJoinInner() throws Exception {

    }

    @Test
    public void testFilterPathForSepPaths() throws Exception {

    }

    @Test
    public void testFilterPathForSepS() throws Exception {

    }

    @Test
    public void testMergeBasePart() throws Exception {

    }

    @Test
    public void testBuildUnixRoot() throws Exception {

    }

    @Test
    public void testBuildWinRoot() throws Exception {

    }

    @Test
    public void testBuildUnix() throws Exception {

    }

    @Test
    public void testGetType() throws Exception {

    }

    @Test
    public void testSetType() throws Exception {

    }

    @Test
    public void testGetRoot() throws Exception {

    }

    @Test
    public void testSetRoot() throws Exception {

    }

    @Test
    public void testGetDir() throws Exception {

    }

    @Test
    public void testSetDir() throws Exception {

    }

    @Test
    public void testGetBase() throws Exception {

    }

    @Test
    public void testSetBase() throws Exception {

    }

    @Test
    public void testGetExt() throws Exception {

    }

    @Test
    public void testSetExt() throws Exception {

    }

    @Test
    public void testGetName() throws Exception {

    }

    @Test
    public void testSetName() throws Exception {

    }


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
