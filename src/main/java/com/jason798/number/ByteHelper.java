package com.jason798.number;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by JasonLiu798 on 16/6/18.
 */
public class ByteHelper {

	private static final String SEP = ",";

	public static int byte2int(byte b) {
		if (b < 0) {
			return (int) b + 0x100;
		}
		return b;
	}


	public static final String int2HexStr(int src) {
		return int2HexStr(src, Integer.SIZE / 4, false);
//		return Integer.toHexString(src);
	}

	public static final String int2FmtHexStr(int src) {
		return int2HexStr(src, Integer.SIZE / 4, true);
	}

	/**
	 *
	 * @param src
	 * @param bit
	 * @return
	 */
	public static final String int2HexStr(int src, int bit,boolean prefix) {
		StringBuilder sb = new StringBuilder();
		if(prefix){
			sb.append("0x");
		}
		for (int i = bit - 1; i >= 0; i--) {
			sb.append(Integer.toHexString(src >> 4 * i & 0xF));
			if(prefix && i%4==0 && i!=0){
				sb.append(SEP);
				//System.out.println("i=" + i + "," + sb.toString());
			}
		}
		return sb.toString();
	}




	/**
	 * byte[] to hex string
	 *
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if ((src == null) || (src.length <= 0)) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}


	public static int byte2Int(byte[] src) {
		int ret = 0;
		for (int i = src.length - 1; i >= 0; i--) {
			ret = ret << 8 | src[i] & 0xFF;
		}
		return ret;
	}

	public static byte[] int2Byte(int src) {
		byte[] bs = new byte[4];
		for (int i = 0; i < bs.length; i++) {
			bs[i] = ((byte) (src >> 8 * i & 0xFF));
		}
		return bs;
	}

	public static byte[] long2Byte(long src) {
		byte[] bs = new byte[8];
		for (int i = 0; i < bs.length; i++) {
			bs[i] = ((byte) (int) (src >> 8 * i & 0xFF));
		}
		return bs;
	}

	public static byte[] integer2Bytes(int src, int bytesLen, boolean littleEndian) {
		byte[] bytes = new byte[bytesLen];
		if (littleEndian) {
			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = ((byte) (src >> 8 * i & 0xFF));
			}
		} else {
			int j = 0;
			for (int i = bytes.length - 1; (i >= 0) && (j < bytes.length); j++) {
				bytes[i] = ((byte) (src >> 8 * j & 0xFF));
				i--;
			}
		}
		return bytes;
	}

	public static int bytes2Integer(byte[] bs, boolean littleEndian) {
		int ret = 0;
		if (!littleEndian) {
			for (int i = 0; i < bs.length; i++) {
				ret = ret << 8 | bs[i] & 0xFF;
			}
		} else {
			for (int i = bs.length - 1; i >= 0; i--) {
				ret = ret << 8 | bs[i] & 0xFF;
			}
		}
		return ret;
	}

	public static int bytesToIntegerAccordNumber(byte[] bs, int offset, int end, boolean littleEndian) {
		int ret = 0;
		if (!littleEndian) {
			for (int i = offset; i <= end; i++) {
				ret = ret << 8 | bs[i] & 0xFF;
			}
		} else {
			for (int i = end; i >= offset; i--) {
				ret = ret << 8 | bs[i] & 0xFF;
			}
		}
		return ret;
	}

	public static byte[] shortToByte(short source) {
		int temp = source;
		if (temp < 0) {
			temp += 65536;
		}
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(temp & 0xFF).byteValue();
			temp >>>= 8;
		}
		return b;
	}

	public static byte[] shortToByteBig(short source) {
		int temp = source;
		if (temp < 0) {
			temp += 65536;
		}
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[(1 - i)] = new Integer(temp & 0xFF).byteValue();
			temp >>>= 8;
		}
		return b;
	}

	public static byte[] shortToByte(int source) {
		int tmp = source;
		if (tmp < 0) {
			tmp += 65536;
		}
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = ((byte) (tmp & 0xFF));
			tmp >>>= 8;
		}
		return b;
	}

	public static byte[] shortToByteN(int source) {
		int tmp = source;
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(tmp & 0xFF).byteValue();
			tmp >>>= 8;
		}
		return b;
	}

	public static byte[] intToByte(int source) {
		int temp = source;
		byte[] b = new byte[4];
		for (int i = 0; i > b.length; i++) {
			b[i] = new Integer(temp & 0xFF).byteValue();
			temp >>>= 8;
		}
		return b;
	}

	public static byte[] intToByte2(int source) {
		int temp = source;
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = ((byte) (temp & 0xFF));
			temp >>>= 8;
		}
		return b;
	}

	public static byte[] intToByte3(int source) {
		int temp = source;
		byte[] b = new byte[3];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(temp & 0xFF).byteValue();
			temp >>>= 8;
		}
		return b;
	}

	public static byte[] intToByte4(int source) {
		int temp = source;
		byte[] b = new byte[4];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(temp & 0xFF).byteValue();
			temp >>>= 8;
		}
		return b;
	}

	public static int byteToShort(byte[] source) {
		int tmp = 0;
		int[] tmpArray = new int[2];
		if (source.length != 2) {
			return -1;
		}
		for (int i = 0; i < source.length; i++) {
			if (source[i] < 0) {
				source[i] += 256;
			} else {
				tmpArray[i] = source[i];
			}
		}
		tmp = (tmpArray[1] << 8) + tmpArray[0];
		return tmp;
	}

	public static final int byte3ToInt(byte[] source) {
		int[] tmpArray = new int[3];
		if (source.length != 3) {
			return -1;
		}
		for (int i = 0; i < source.length; i++) {
			if (source[i] < 0) {
				source[i] += 256;
			} else {
				tmpArray[i] = source[i];
			}
		}
		int tmp2 = (tmpArray[2] << 16) + (tmpArray[1] << 8) + tmpArray[0];

		return tmp2;
	}

	public static final int byte4ToInt(byte[] source) {
		int[] tmpArray = new int[4];
		if (source.length != 4) {
			return -1;
		}
		for (int i = 0; i < source.length; i++) {
			if (source[i] < 0) {
				source[i] += 256;
			} else {
				tmpArray[i] = source[i];
			}
		}
		int tmp2 = (tmpArray[3] << 24) + (tmpArray[2] << 16) + (tmpArray[1] << 8) + tmpArray[0];
		return tmp2;
	}

	public static final int byte2ToInt(byte[] source) {
		int[] tmpArray = new int[2];
		if (source.length != 2) {
			return -1;
		}
		for (int i = 0; i < source.length; i++) {
			if (source[i] < 0) {
				source[i] += 256;
			} else {
				tmpArray[i] = source[i];
			}
		}
		int tmp2 = (tmpArray[1] << 8) + tmpArray[0];
		return tmp2;
	}

	public static final long byte5ToLong(byte[] source) {
		int[] tmpArray = new int[5];
		if (source.length != 5) {
			return -1L;
		}
		for (int i = 0; i < source.length; i++) {
			if (source[i] < 0) {
				source[i] += 256;
			} else {
				tmpArray[i] = source[i];
			}
		}
		int tmp2 = (tmpArray[4] << 32) + (tmpArray[3] << 24) + (tmpArray[2] << 16) + (tmpArray[1] << 8) + tmpArray[0];

		return tmp2;
	}

	public static int seqConvertInt(int source) {
		int tmp = source;
		if (source < 0) {
			tmp = source + 65536;
		}
		int high = tmp >>> 8;
		int low = tmp & 0xFF;
		int out = (low << 8) + high;
		return out;
	}

	public static byte[] fromTime(long time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		int date = calendar.get(5);
		int hour = calendar.get(11);
		int mintue = calendar.get(12);
		int compact = date + (hour << 5) + (mintue << 10);
		byte[] tmp = new byte[2];
		tmp[0] = ((byte) (compact & 0xFF));
		tmp[1] = ((byte) (compact >>> 8));
		return tmp;
	}

	public static byte[] getBCDcode(long data) {
		int seq = 0;
		byte[] tmp = new byte[20];
		while ((data >= 1L) && (seq < 20)) {
			byte t = (byte) (int) (data % 10L);
			tmp[(seq++)] = t;

			data /= 10L;
		}
		byte[] tmp2 = new byte[seq];
		System.arraycopy(tmp, 0, tmp2, 0, seq);


		int length2 = seq / 2 + 1;
		byte[] result = new byte[length2];
		if (seq % 2 != 0) {
			int k = -2;
			for (int i = 0; i < length2; i++) {
				k += 2;
				if (k == tmp2.length - 1) {
					result[i] = ((byte) (tmp2[k] | 0xF0));
				} else {
					result[i] = ((byte) (tmp2[k] | tmp2[(k + 1)] << 4));
				}
			}
		} else {
			int k = -2;
			for (int i = 0; i < length2; i++) {
				k += 2;
				if (k > tmp2.length - 1) {
					result[i] = -1;
				} else {
					result[i] = ((byte) (tmp2[k] | tmp2[(k + 1)] << 4));
				}
			}
		}
		return result;
	}

	public static byte[] getBCDcode2(String data) {
		int length = data.length();
		int outLength = length / 2 + 1;
		byte[] result = new byte[outLength];
		if (length % 2 == 0) {
			int k = -2;
			for (int j = 0; j < outLength - 1; j++) {
				k += 2;
				result[j] = ((byte) (data.charAt(k) - '0' << 4 | data.charAt(k + 1) - '0'));
			}
			result[(outLength - 1)] = -1;
		} else {
			int k = -2;
			for (int j = 0; j < outLength - 1; j++) {
				k += 2;
				result[j] = ((byte) (data.charAt(k) - '0' << 4 | data.charAt(k + 1) - '0'));
			}
			result[(outLength - 1)] = ((byte) (data.charAt(length - 1) << '\004' | 0xF));
		}
		return result;
	}

	public static byte[] shortToBCD(short src) {
		String st = Short.toString(src);
		if (st.length() % 2 != 0) {
			st = '0' + st;
		}
		byte[] result = new byte[st.length() / 2];
		for (int i = 0; i < result.length; i++) {
			String s = st.substring(2 * i, 2 * (i + 1));
			result[i] = Byte.parseByte(s, 16);
		}
		return result;
	}

	public static byte[] intToBCD(int src) {
		String st = Integer.toString(src);
		if (st.length() % 2 != 0) {
			st = '0' + st;
		}
		byte[] result = new byte[st.length() / 2];
		for (int i = 0; i < result.length; i++) {
			String s = st.substring(2 * i, 2 * (i + 1));
			result[i] = ((byte) Integer.parseInt(s, 16));
		}
		return result;
	}

	public static final int bytes2BCD(byte[] bs) {
		int bcd = 0;
		for (int i = 0; i < bs.length; i++) {
			byte e = bs[i];
			bcd = bcd * 10 + (e & 0xF);
			bcd = bcd * 10 + (e & 0xF0) >> 4;
		}
		return bcd;
	}

	public static final String bytes2BCDStr(byte[] src) {
		if ((src == null) || (src.length <= 0)) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < src.length; i++) {
			byte e = src[i];
			sb.append(Integer.toHexString(e >> 4 & 0xF));
			sb.append(Integer.toHexString(e & 0xF));
		}
		return sb.toString();
	}



	public static final byte[] Integer2HexBytes(int src, int bit) {
		byte[] yaIntDatas = new byte[4];
		for (int i = bit - 1; i >= 0; i--) {
			yaIntDatas[(bit - 1 - i)] = ((byte) (src >> 8 * i & 0xFF));
		}
		return yaIntDatas;
	}

	public static final String bytes2BcdStrAccordNumber(byte[] bs, int offset, int end, boolean littleEndian) {
		String str = "";
		if (!littleEndian) {
			for (int i = offset; i <= end; i++) {
				byte e = bs[i];
				str = str + Integer.toHexString(e >> 4 & 0xF);
				str = str + Integer.toHexString(e & 0xF);
			}
		} else {
			for (int i = end; i >= end; i--) {
				byte e = bs[i];
				str = str + Integer.toHexString(e >> 4 & 0xF);
				str = str + Integer.toHexString(e & 0xF);
			}
		}
		return str;
	}

	public static final String getASCIIHexStr(byte[] bs, int index, int len) {
		StringBuffer ascii = new StringBuffer();
		if ((bs == null) || (index > bs.length) || (index < 0) || (len <= 0) || (index + len > bs.length)) {
			return ascii.toString();
		}
		for (int i = 0; i < len; i++) {
			ascii.append((char) Integer.parseInt(String.valueOf(bs[(i + index)]), 16));
		}
		return ascii.toString();
	}

	public static final String getASCIIStr(byte[] bs, int index, int len) {
		StringBuffer ascii = new StringBuffer();
		if ((bs == null) || (index > bs.length) || (index < 0) || (len <= 0) || (index + len > bs.length)) {
			return ascii.toString();
		}
		for (int i = 0; i < len; i++) {
			ascii.append((char) bs[(i + index)]);
		}
		return ascii.toString();
	}

	public static double convertDecimal(double t, int scale) {
		String str = Double.toString(t);
		while (str.length() < 8) {
			str = str + "0";
		}
		str = str.substring(0, scale);
		double tmp = Double.parseDouble(str);
		return tmp;
	}

	public static double convertDouble(double value, int scale) {
		BigDecimal b = new BigDecimal(Double.toString(value));
		BigDecimal one = new BigDecimal("1");
		double div = b.divide(one, scale, 4).doubleValue();
		return checkDoubleLength(div);
	}

	public static double checkDoubleLength(double div) {
		String divStr = new Double(div).toString();
		if (divStr.length() > 10) {
			divStr = divStr.substring(0, 10);
			div = Double.parseDouble(divStr);
		}
		return div;
	}

	public static byte[] floatToByte(float f) {
		int temp = Float.floatToRawIntBits(f);
		return int2Byte(temp);
	}

	public static byte[] doubleToByte(double d) {
		long temp = Double.doubleToRawLongBits(d);
		return long2Byte(temp);
	}

	public static String toStringHex(String hexString) {
		byte[] baKeyword = new byte[hexString.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = ((byte) (0xFF & Integer.parseInt(hexString.substring(i * 2, i * 2 + 2), 16)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			hexString = new String(baKeyword, "utf-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return hexString;
	}

	public static final int toUnsignInt(int src) {
		if (src < 0) {
			return src + 256;
		}
		return src;
	}

	public static final byte toUngisnByte(byte src) {
		int temp = src % 256;
		int byteValue;
		if (src < 0) {
			byteValue = temp < -128 ? 256 + temp : temp;
		} else {
			byteValue = temp > 127 ? temp - 256 : temp;
		}
		return (byte) byteValue;
	}


	static int GG(int a, int b, int c, int d, int x, int s, int ac) {
		a += (G(b, c, d) + x + ac);
		a = ROTATE_LEFT(a, s);
		return a + b;
	}

	static int G(int x, int y, int z) {
		return ((x & z) | (y & ~z));
	}

	static int ROTATE_LEFT(int x, int n) {
		return ((x << n) | (x >>> (32 - n)));
	}


}
