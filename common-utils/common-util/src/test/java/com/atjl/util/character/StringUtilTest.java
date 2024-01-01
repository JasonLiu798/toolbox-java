package com.atjl.util.character;

import com.atjl.util.collection.CollectionUtilEx;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * StringUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
public class StringUtilTest {

    /**
     * Method: isEmpty(Object str)
     */
    @Test
    public void testIsEmptyStr() throws Exception {

    }

    /**
     * Method: trim(String str)
     */
    @Test
    public void testTrim() throws Exception {

    }

    /**
     * Method: replaceAll(String inString, String oldPattern, String newPattern)
     */
    @Test
    public void testReplaceAll() throws Exception {

    }

    @Test
    public void testAddSpace() {
        String res = StringUtilEx.addSpaceFront("123", 2, 3);
        System.out.println(res + "," + res.length());
    }

    @Test
    public void testReplaceBlank() {
        String s = StringUtilEx.replaceBlank("aa\n");
        assertEquals(s, "aa");
        s = StringUtilEx.replaceBlank("\n\taa");
        assertEquals(s, "aa");
        s = StringUtilEx.replaceBlank("");
        assertEquals(s, "");
        s = StringUtilEx.replaceBlank(null);
        assertEquals(s, null);
        s = StringUtilEx.replaceBlank(" aa ");
        assertEquals(s, "aa");
        s = StringUtilEx.replaceBlank("\n\taa\n\t");
        assertEquals(s, "aa");
        s = StringUtilEx.replaceBlank("\n\ta\n\ta\n\t");
        assertEquals(s, "aa");

    }

    /**
     * Method: getUUID()
     */
    @Test
    public void testGetUUID() throws Exception {

    }

    /**
     * Method: getListId(String id)
     */
    @Test
    public void testGetListId() throws Exception {

    }

    /**
     * Method: isEqualString(String arg1, String arg2)
     */
    @Test
    public void testIsEqualString() throws Exception {

    }

    /**
     * Method: formatterString(String arg, Object... objects)
     */
    @Test
    public void testFormatterString() throws Exception {

    }

    /**
     * Method: StringToArray(String ids, String separator)
     */
    @Test
    public void testStringToArray() throws Exception {

    }

    /**
     * Method: decodeUnicode(final String dataStr)
     */
    @Test
    public void testDecodeUnicode() throws Exception {

    }

    /**
     * Method: encodeUnicode(final String dataStr)
     */
    @Test
    public void testEncodeUnicode() throws Exception {

    }

    /**
     * Method: numberInArray(int[] array, int number)
     */
    @Test
    public void testNumberInArray() throws Exception {

    }

    /**
     * Method: addZeroFront(String vSourceString, int vOutputLen)
     */
    @Test
    public void testAddZeroFront() throws Exception {

    }

    /**
     * Method: hasLength(CharSequence str)
     */
    @Test
    public void testHasLengthStr() throws Exception {

    }

    /**
     * Method: upperFirstLetter(String name)
     */
    @Test
    public void testCaptureName() throws Exception {
        assertEquals("A", StringUtilEx.toUpperCaseFirstOne("a"));
        assertEquals("Ab", StringUtilEx.toUpperCaseFirstOne("ab"));
        assertEquals("", StringUtilEx.toUpperCaseFirstOne(""));
        assertEquals(null, StringUtilEx.toUpperCaseFirstOne(null));
    }


    /**
     * Method: tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens)
     */
    @Test
    public void testTokenizeToStringArrayForStrDelimitersTrimTokensIgnoreEmptyTokens() throws Exception {

    }

    /**
     * Method: toStringArray(Collection collection)
     */
    @Test
    public void testToStringArrayCollection() throws Exception {

    }

    /**
     * Method: toStringArray(Enumeration enumeration)
     */
    @Test
    public void testToStringArrayEnumeration() throws Exception {

    }

    /**
     * Method: hasText(CharSequence str)
     */
    @Test
    public void testHasTextStr() throws Exception {

    }

    /**
     * Method: replace(String inString, String oldPattern, String newPattern)
     */
    @Test
    public void testReplace() throws Exception {

    }

    /**
     * Method: byte2int(byte b)
     */
    @Test
    public void testByte2int() throws Exception {

    }

    /**
     * Method: GG(int a, int b, int c, int d, int x, int s, int ac)
     */
    @Test
    public void testGG() throws Exception {

    }

    /**
     * Method: G(int x, int y, int z)
     */
    @Test
    public void testG() throws Exception {

    }

    /**
     * Method: ROTATE_LEFT(int x, int n)
     */
    @Test
    public void testROTATE_LEFT() throws Exception {

    }

    /**
     * Method: getContentBySep(String content, String sign)
     */
    @Test
    public void testGetContentBySep() throws Exception {

    }

    /**
     * Method: isPisubstr(String qstr, String key)
     */
    @Test
    public void testIsPisubstr() throws Exception {

    }

    /**
     * Method: getValByKey(String qstr, String key, String sign)
     */
    @Test
    public void testGetValByKey() throws Exception {

    }

    /**
     * Method: setValByKey(String qstr, String key, String val, String sign)
     */
    @Test
    public void testSetValByKey() throws Exception {

    }

    /**
     * Method: delStrKey(String qstr, String key, String sign)
     */
    @Test
    public void testDelStrKey() throws Exception {

    }

    /**
     * Method: firstChar(String str)
     */
    @Test
    public void testFirstChar() throws Exception {

    }

    /**
     * Method: lastChar(String str)
     */
    @Test
    public void testLastChar() throws Exception {

    }

    /**
     * Method: isNull(String str)
     */
    @Test
    public void testIsNull() throws Exception {

    }

    /**
     * Method: isIvrNull(String str)
     */
    @Test
    public void testIsIvrNull() throws Exception {

    }

    /**
     * Method: isNumberNull(int number)
     */
    @Test
    public void testIsNumberNullNumber() throws Exception {

    }

    /**
     * Method: upperCaseFirstChar(String str)
     */
    @Test
    public void testUpperCaseFirstChar() throws Exception {

    }

    /**
     * Method: arrayToString(Object array[])
     */
    @Test
    public void testArrayToString() throws Exception {

    }

    /**
     * Method: preComplement(String raw, int length, char complement)
     */
    @Test
    public void testPreComplement() throws Exception {

    }

    /**
     * Method: sufComplement(String raw, int length, char complement)
     */
    @Test
    public void testSufComplement() throws Exception {

    }

    /**
     * Method: toBytesRaw(String string)
     */
    @Test
    public void testToBytesRaw() throws Exception {

    }

    /**
     * Method: toBytesHex(String hex)
     */
    @Test
    public void testToBytesHex() throws Exception {

    }

    /**
     * Method: integerToZhCn(int i)
     */
    @Test
    public void testIntegerToZhCn() throws Exception {

    }

    /**
     * Method: decode(String infos[])
     */
    @Test
    public void testDecodeInfos() throws Exception {

    }

    /**
     * Method: decode(String info, String decodeType)
     */
    @Test
    public void testDecodeForInfoDecodeType() throws Exception {

    }

    /**
     * Method: encode(String info, String decodeType)
     */
    @Test
    public void testEncodeForInfoDecodeType() throws Exception {

    }

    /**
     * Method: decode(String info)
     */
    @Test
    public void testDecodeInfo() throws Exception {

    }

    /**
     * Method: encode(String info)
     */
    @Test
    public void testEncodeInfo() throws Exception {

    }

    /**
     * Method: encodeStr(String str)
     */
    @Test
    public void testEncodeStr() throws Exception {

    }

    /**
     * Method: formatDate(Date date, SimpleDateFormat sf)
     */
    @Test
    public void testFormatDate() throws Exception {

    }

    /**
     * Method: getNullString(String str)
     */
    @Test
    public void testGetNullString() throws Exception {

    }

    /**
     * Method: getIVRNullString(String str)
     */
    @Test
    public void testGetIVRNullString() throws Exception {

    }

    /**
     * Method: decode(String str, String dim, int num)
     */
    @Test
    public void testDecodeForStrDimNum() throws Exception {

    }

    /**
     * Method: string2Date(String source)
     */
    @Test
    public void testString2Date() throws Exception {

    }

    /**
     * Method: date2String(Date date)
     */
    @Test
    public void testDate2String() throws Exception {

    }

    /**
     * Method: date2HHmmss(Date date)
     */
    @Test
    public void testDate2HHmmss() throws Exception {

    }

    /**
     * Method: date2yyyyMMdd(Date date)
     */
    @Test
    public void testDate2yyyyMMdd() throws Exception {

    }

    /**
     * Method: date2HHmm(Date date)
     */
    @Test
    public void testDate2HHmm() throws Exception {

    }

    /**
     * Method: isInteger(String str)
     */
    @Test
    public void testIsInteger() throws Exception {

    }

    /**
     * Method: cutDouble2point(double value, int afterpoint)
     */
    @Test
    public void testCutDouble2point() throws Exception {

    }

    /**
     * Method: clean(StringBuffer buf)
     */
    @Test
    public void testClean() throws Exception {

    }

    /**
     * Method: getNotEmpty(String str)
     */
    @Test
    public void testGetNotEmpty() throws Exception {

    }

    /**
     * Method: encodeNotEmpty(String str)
     */
    @Test
    public void testEncodeNotEmpty() throws Exception {

    }


    @Test
    public void testTrimQuote() {
        assertEquals(null, StringUtilEx.trimQuote(null));
        assertEquals("", StringUtilEx.trimQuote(""));
        assertEquals("", StringUtilEx.trimQuote("   "));
        //no quote
        assertEquals("a", StringUtilEx.trimQuote("a"));
        assertEquals("a", StringUtilEx.trimQuote("a  "));
        assertEquals("a", StringUtilEx.trimQuote("  a"));

        //only single quote
        assertEquals("", StringUtilEx.trimQuote("\""));
        assertEquals("", StringUtilEx.trimQuote("   \""));
        assertEquals("", StringUtilEx.trimQuote("\"  "));
        assertEquals("", StringUtilEx.trimQuote("   \"  "));

        //only double quote
        assertEquals("", StringUtilEx.trimQuote("\"\""));
        assertEquals("", StringUtilEx.trimQuote("\"   \""));
        assertEquals("", StringUtilEx.trimQuote("\"\"   "));
        assertEquals("", StringUtilEx.trimQuote("   \"\""));
        assertEquals("", StringUtilEx.trimQuote("   \"\"   "));
        assertEquals("", StringUtilEx.trimQuote("   \"   \"   "));
        //normal
        assertEquals("a", StringUtilEx.trimQuote("\"a\""));
        //got right
        assertEquals("a", StringUtilEx.trimQuote("\"a"));
        assertEquals("a", StringUtilEx.trimQuote("\"a   "));
        assertEquals("a", StringUtilEx.trimQuote("   \"a"));
        assertEquals("a", StringUtilEx.trimQuote("   \"a   "));
        //got left
        assertEquals("a", StringUtilEx.trimQuote("a\""));
        assertEquals("a", StringUtilEx.trimQuote("\"  a   \""));
        assertEquals("a", StringUtilEx.trimQuote("\"a   \""));
        assertEquals("a", StringUtilEx.trimQuote("\"   a\""));
        assertEquals("asdfsasdfsdfd", StringUtilEx.trimQuote("\" asdfsasdfsdfd  \""));
        assertEquals("asdfsasdfsdfd", StringUtilEx.trimQuote("   \" asdfsasdfsdfd  \"    "));
        assertEquals("asdfsasdfsdfd", StringUtilEx.trimQuote("   \" asdfsasdfsdfd  \""));
        assertEquals("asdfsasdfsdfd", StringUtilEx.trimQuote("\" asdfsasdfsdfd  \"   "));
        assertEquals("asdf  sasdfsdfd", StringUtilEx.trimQuote("\" asdf  sasdfsdfd  \""));
    }


    @Test
    public void testSimplifyClassName() throws Exception {
        String raw = "com.atjl.log.api";
        int tgt = 8;
        System.out.println("raw:" + raw + ",len:" + raw.length() + ",tgt:" + tgt);
        String min = "c.a.l.a";
        System.out.println("min:" + min + ",len:" + min.length());
        System.out.println("minus:" + (raw.length() - min.length()));

        String res = StringUtilEx.simplifyFullClassName(raw, tgt);
//        String res = LogFmtUtil.simplifyFullClassName("12.3", 1);

        System.out.println("res:" + res + ",reslen:" + res.length());
    }

    @Test
    public void testSimplifyClassName2() throws Exception {
        assertEquals("12345678901", StringUtilEx.simplifyFullClassName("12345678901", 10));
        assertEquals("1234567", StringUtilEx.simplifyFullClassName("1234567", 10));
        assertEquals("1.1", StringUtilEx.simplifyFullClassName("123456790.1", 10));
        assertEquals(null, StringUtilEx.simplifyFullClassName(null, 10));
        assertEquals("123", StringUtilEx.simplifyFullClassName("123", 0));
        assertEquals("123", StringUtilEx.simplifyFullClassName("123", 1));
        assertEquals("1.3", StringUtilEx.simplifyFullClassName("12.3", 1));
        assertEquals("1.3.4", StringUtilEx.simplifyFullClassName("12.3.4", 1));
        assertEquals("1.32.4", StringUtilEx.simplifyFullClassName("12.32.4", 6));
//        assertEquals(,LogFmtUtil.simplifyFullClassName(null, 10));

    }


    @Test
    public void testgetFirstUpperBefore() {
        assertEquals("asdf", StringUtilEx.getFirstUpperBefore("asdfAA"));
        assertEquals("", StringUtilEx.getFirstUpperBefore("AA"));
        assertEquals("aaa", StringUtilEx.getFirstUpperBefore("aaa"));
        assertEquals(null, StringUtilEx.getFirstUpperBefore(null));
        assertEquals("a", StringUtilEx.getFirstUpperBefore("a"));
        assertEquals("", StringUtilEx.getFirstUpperBefore("A"));
    }


    @Test
    public void testFilterEmpty() {
        String[] s = {};
        String[] res = StringUtilEx.filterEmpty(s);
        System.out.println("res:" + res);

        res = StringUtilEx.filterEmpty(new String[]{"123", null, "234"});
        System.out.println("res:" + CollectionUtilEx.array2List(res));

        res = StringUtilEx.filterEmpty(new String[]{"", "123", null, "234"});
        System.out.println("res:" + CollectionUtilEx.array2List(res));

    }

    @Test
    public void testFilterEmptyList() {
        List<String> res = StringUtilEx.filterEmpty((List) null);
        System.out.println("res:" + res);

        res = StringUtilEx.filterEmpty(CollectionUtilEx.newList("123", null, "234"));
        System.out.println("res:" + res);

        res = StringUtilEx.filterEmpty(CollectionUtilEx.newList("", "123", null, "234"));
        System.out.println("res:" + res);

    }
}
