package com.jason798.number;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * ByteHelper Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>七月 11, 2016</pre>
 */
public class ByteHelperTest {

	@Test
	public void testInt2Hex(){
		String res = ByteHelper.int2HexStr(123, 1, false);
		System.out.println(res);
		assertEquals("b", res);

		res = ByteHelper.int2HexStr(123,2,false);
		System.out.println(res);
		assertEquals("7b",res);

		res = ByteHelper.int2HexStr(123,4,false);
		System.out.println(res);
		assertEquals("007b",res);


		res = ByteHelper.int2HexStr(-123,8,false);
		System.out.println(res);
		assertEquals("ffffff85",res);
	}

	/**
	 * Method: integer2HexString(int src, int bit)
	 */
	@Test
	public void testInteger2HexStringForSrcBit() throws Exception {
	}

	/**
	 * Method: bytesToHexString(byte[] src)
	 */
	@Test
	public void testBytesToHexString() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: byte2Int(byte[] src)
	 */
	@Test
	public void testByte2Int() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: int2Byte(int src)
	 */
	@Test
	public void testInt2Byte() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: long2Byte(long src)
	 */
	@Test
	public void testLong2Byte() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: integer2Bytes(int src, int bytesLen, boolean littleEndian)
	 */
	@Test
	public void testInteger2Bytes() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: bytes2Integer(byte[] bs, boolean littleEndian)
	 */
	@Test
	public void testBytes2Integer() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: bytesToIntegerAccordNumber(byte[] bs, int offset, int end, boolean littleEndian)
	 */
	@Test
	public void testBytesToIntegerAccordNumber() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: shortToByte(short source)
	 */
	@Test
	public void testShortToByteSource() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: shortToByteBig(short source)
	 */
	@Test
	public void testShortToByteBig() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: shortToByteN(int source)
	 */
	@Test
	public void testShortToByteN() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: intToByte(int source)
	 */
	@Test
	public void testIntToByte() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: intToByte2(int source)
	 */
	@Test
	public void testIntToByte2() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: intToByte3(int source)
	 */
	@Test
	public void testIntToByte3() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: intToByte4(int source)
	 */
	@Test
	public void testIntToByte4() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: byteToShort(byte[] source)
	 */
	@Test
	public void testByteToShort() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: byte3ToInt(byte[] source)
	 */
	@Test
	public void testByte3ToInt() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: byte4ToInt(byte[] source)
	 */
	@Test
	public void testByte4ToInt() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: byte2ToInt(byte[] source)
	 */
	@Test
	public void testByte2ToInt() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: byte5ToLong(byte[] source)
	 */
	@Test
	public void testByte5ToLong() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: seqConvertInt(int source)
	 */
	@Test
	public void testSeqConvertInt() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: fromTime(long time)
	 */
	@Test
	public void testFromTime() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: getBCDcode(long data)
	 */
	@Test
	public void testGetBCDcode() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: getBCDcode2(String data)
	 */
	@Test
	public void testGetBCDcode2() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: shortToBCD(short src)
	 */
	@Test
	public void testShortToBCD() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: intToBCD(int src)
	 */
	@Test
	public void testIntToBCD() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: bytes2BCD(byte[] bs)
	 */
	@Test
	public void testBytes2BCD() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: bytes2BCDStr(byte[] src)
	 */
	@Test
	public void testBytes2BCDStr() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: Integer2HexBytes(int src, int bit)
	 */
	@Test
	public void testInteger2HexBytes() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: bytes2BcdStrAccordNumber(byte[] bs, int offset, int end, boolean littleEndian)
	 */
	@Test
	public void testBytes2BcdStrAccordNumber() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: getASCIIHexStr(byte[] bs, int index, int len)
	 */
	@Test
	public void testGetASCIIHexStr() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: getASCIIStr(byte[] bs, int index, int len)
	 */
	@Test
	public void testGetASCIIStr() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: convertDecimal(double t, int scale)
	 */
	@Test
	public void testConvertDecimal() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: convertDouble(double value, int scale)
	 */
	@Test
	public void testConvertDouble() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: checkDoubleLength(double div)
	 */
	@Test
	public void testCheckDoubleLength() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: floatToByte(float f)
	 */
	@Test
	public void testFloatToByte() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: doubleToByte(double d)
	 */
	@Test
	public void testDoubleToByte() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: toStringHex(String hexString)
	 */
	@Test
	public void testToStringHex() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: toUnsignInt(int src)
	 */
	@Test
	public void testToUnsignInt() throws Exception {
//TODO: Test goes here... 
	}

	/**
	 * Method: toUngisnByte(byte src)
	 */
	@Test
	public void testToUngisnByte() throws Exception {
//TODO: Test goes here... 
	}


} 
