package com.jason798.number;

/**
 * Created by JasonLiu798 on 16/6/18.
 */
public class ByteHelper {

    static int byte2int(byte b) {
        if (b < 0) {

            return (int) b + 0x100;
        }

        return b;
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
