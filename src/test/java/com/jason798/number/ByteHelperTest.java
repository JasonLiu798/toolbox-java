package com.jason798.number;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ByteHelperTest {

    /**
     * Method: byte2int(byte b)
     */
    @Test
    public void testByte2int() throws Exception {
        assertEquals(12, ByteUtil.byte2int((byte) 12));
        // ~0000 1100 = 1111 0011 +1 = 1111 0100 = 244
        assertEquals(244, ByteUtil.byte2int((byte) -12));

    }

    /**
     * Method: byte2Int(byte[] src)
     */
    @Test
    public void testByte2Int() throws Exception {
        assertEquals(12, ByteUtil.bytes2Int(new byte[]{12}) );
        // 12,12
        assertEquals(12, ByteUtil.bytes2Int(new byte[]{12}) );
    }

    /**
     * Method: byteToHexString(byte b)
     */
    @Test
    public void testByteToHexString() throws Exception {
        assertEquals("01", ByteUtil.byteToHexString((byte) 1));
        assertEquals("0c", ByteUtil.byteToHexString((byte) 12));
        assertEquals("ff", ByteUtil.byteToHexString((byte) 255));
        assertEquals("f4", ByteUtil.byteToHexString((byte) -12));
    }

    /**
     * Method: byteArrayToHexString(byte[] b)
     */
    @Test
    public void testByteArrayToHexString() throws Exception {

    }

    /**
     * Method: int2HexStr(int src)
     */
    @Test
    public void testInt2HexStrSrc() throws Exception {

    }

    /**
     * Method: int2FmtHexStr(int src)
     */
    @Test
    public void testInt2FmtHexStr() throws Exception {

    }

    /**
     * Method: int2HexStr(int src, int bit, boolean prefix)
     */
    @Test
    public void testInt2HexStrForSrcBitPrefix() throws Exception {

    }

    /**
     * Method: bytesToHexString(byte[] src)
     */
    @Test
    public void testBytesToHexString() throws Exception {

    }



    /**
     * Method: int2Byte(int src)
     */
    @Test
    public void testInt2Byte() throws Exception {

    }

    /**
     * Method: long2Byte(long src)
     */
    @Test
    public void testLong2Byte() throws Exception {

    }

    /**
     * Method: integer2Bytes(int src, int bytesLen, boolean littleEndian)
     */
    @Test
    public void testInteger2Bytes() throws Exception {

    }

    /**
     * Method: bytes2Integer(byte[] bs, boolean littleEndian)
     */
    @Test
    public void testBytes2Integer() throws Exception {

    }

    /**
     * Method: bytesToIntegerAccordNumber(byte[] bs, int offset, int end, boolean littleEndian)
     */
    @Test
    public void testBytesToIntegerAccordNumber() throws Exception {

    }

    /**
     * Method: shortToByte(short source)
     */
    @Test
    public void testShortToByteSource() throws Exception {

    }

    /**
     * Method: shortToByteBig(short source)
     */
    @Test
    public void testShortToByteBig() throws Exception {

    }

    /**
     * Method: shortToByteN(int source)
     */
    @Test
    public void testShortToByteN() throws Exception {

    }

    /**
     * Method: intToByte(int source)
     */
    @Test
    public void testIntToByte() throws Exception {

    }

    /**
     * Method: intToByte2(int source)
     */
    @Test
    public void testIntToByte2() throws Exception {

    }

    /**
     * Method: intToByte3(int source)
     */
    @Test
    public void testIntToByte3() throws Exception {

    }

    /**
     * Method: intToByte4(int source)
     */
    @Test
    public void testIntToByte4() throws Exception {

    }

    /**
     * Method: byteToShort(byte[] source)
     */
    @Test
    public void testByteToShort() throws Exception {

    }

    /**
     * Method: byte3ToInt(byte[] source)
     */
    @Test
    public void testByte3ToInt() throws Exception {

    }

    /**
     * Method: byte4ToInt(byte[] source)
     */
    @Test
    public void testByte4ToInt() throws Exception {

    }

    /**
     * Method: byte2ToInt(byte[] source)
     */
    @Test
    public void testByte2ToInt() throws Exception {

    }

    /**
     * Method: byte5ToLong(byte[] source)
     */
    @Test
    public void testByte5ToLong() throws Exception {

    }

    /**
     * Method: seqConvertInt(int source)
     */
    @Test
    public void testSeqConvertInt() throws Exception {

    }

    /**
     * Method: fromTime(long time)
     */
    @Test
    public void testFromTime() throws Exception {

    }

    /**
     * Method: getBCDcode(long data)
     */
    @Test
    public void testGetBCDcode() throws Exception {

    }

    /**
     * Method: getBCDcode2(String data)
     */
    @Test
    public void testGetBCDcode2() throws Exception {

    }

    /**
     * Method: shortToBCD(short src)
     */
    @Test
    public void testShortToBCD() throws Exception {

    }

    /**
     * Method: intToBCD(int src)
     */
    @Test
    public void testIntToBCD() throws Exception {

    }

    /**
     * Method: bytes2BCD(byte[] bs)
     */
    @Test
    public void testBytes2BCD() throws Exception {

    }

    /**
     * Method: bytes2BCDStr(byte[] src)
     */
    @Test
    public void testBytes2BCDStr() throws Exception {

    }

    /**
     * Method: Integer2HexBytes(int src, int bit)
     */
    @Test
    public void testInteger2HexBytes() throws Exception {

    }

    /**
     * Method: bytes2BcdStrAccordNumber(byte[] bs, int offset, int end, boolean littleEndian)
     */
    @Test
    public void testBytes2BcdStrAccordNumber() throws Exception {

    }

    /**
     * Method: getASCIIHexStr(byte[] bs, int index, int len)
     */
    @Test
    public void testGetASCIIHexStr() throws Exception {

    }

    /**
     * Method: getASCIIStr(byte[] bs, int index, int len)
     */
    @Test
    public void testGetASCIIStr() throws Exception {

    }

    /**
     * Method: convertDecimal(double t, int scale)
     */
    @Test
    public void testConvertDecimal() throws Exception {

    }

    /**
     * Method: convertDouble(double value, int scale)
     */
    @Test
    public void testConvertDouble() throws Exception {

    }

    /**
     * Method: checkDoubleLength(double div)
     */
    @Test
    public void testCheckDoubleLength() throws Exception {

    }

    /**
     * Method: floatToByte(float f)
     */
    @Test
    public void testFloatToByte() throws Exception {

    }

    /**
     * Method: doubleToByte(double d)
     */
    @Test
    public void testDoubleToByte() throws Exception {

    }

    /**
     * Method: toStringHex(String hexString)
     */
    @Test
    public void testToStringHex() throws Exception {

    }

    /**
     * Method: toUnsignInt(int src)
     */
    @Test
    public void testToUnsignInt() throws Exception {

    }

    /**
     * Method: toUngisnByte(byte src)
     */
    @Test
    public void testToUngisnByte() throws Exception {

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


}
