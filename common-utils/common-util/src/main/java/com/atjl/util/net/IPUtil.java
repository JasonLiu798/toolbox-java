package com.atjl.util.net;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtilEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IP utils
 *
 * @author JasonLiu798
 * @date 2015/12/29 17:30
 */
public class IPUtil {
	private IPUtil(){
		throw new UnsupportedOperationException();
	}
	
    private static final Logger logger = LoggerFactory.getLogger(IPUtil.class);

    //public static final String REGEX_IP = "^\\d+\\.\\d+\\.\\d+\\.\\d+$";
    public static final String REGEX_IP = "^((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))$";
    public static final String LOCAL_IP = "127.0.0.1";

    public static boolean isIp(String ip) {
        Pattern pattern = Pattern.compile(REGEX_IP);
        if (ip == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }


    /**
     * ip 2 long
     *
     * @param ip
     * @return
     */
    public static long ip2Long(String ip) {
        if (!isIp(ip)) {
            ip = LOCAL_IP;
        }
        String[] ipAdress = ip.split("\\.");
        if (ipAdress == null || ipAdress.length != 4) {
            return 2130706433;//127.0.0.1
        }
        long ipLong = 16777216L * Integer.parseInt(ipAdress[0]) + 65536 * Integer.parseInt(ipAdress[1]) + 256 * Integer.parseInt(ipAdress[2]) + Integer.parseInt(ipAdress[3]);
        return ipLong;
    }

    /**
     * long 2 ip
     *
     * @param ip
     * @return
     */
    public static String long2ip(long ip) {
        long mask = 0xFF;
        long ip1 = ip & mask;// 0~7
        long ip2 = (ip >> 8) & mask;// 8~15
        long ip3 = (ip >> 16) & mask;// 16~23
        long ip4 = (ip >> 24) & mask;// 24~31
        String ips = new StringBuffer().append(ip4).append(".").append(ip3).append(".").append(ip2).append(".").append(ip1).toString();
        return ips;
    }

    /**
     * check OS
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().indexOf("windows") > -1) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }


    public static long getOneRandomIpLong() {
        String ip = getOneRandomIp();
        return IPUtil.ip2Long(ip);
    }

    public static String getOneRandomIp() {
        List<String> localIps = IPUtil.getLocalIP();
        if (CollectionUtilEx.isEmpty(localIps)) {
            return LOCAL_IP;
        }
        String tgtIP = LOCAL_IP;
        for (String ip : localIps) {
            if (StringCheckUtil.equal(ip, LOCAL_IP)) {
                continue;
            } else {
                tgtIP = ip;
                break;
            }
        }
        return tgtIP;
    }

    /**
     *
     * @return String
     */
    public static List<String> getLocalIP() {
        InetAddress ip = null;
        List<String> list = null;
        try {
            if (isWindowsOS()) {//win
                list = new ArrayList<>();
                ip = InetAddress.getLocalHost();
                list.add(ip.getHostAddress());
            } else {//linux
                boolean bFindIP = false;
                Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                        .getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    if (bFindIP) {
                        break;
                    }
                    NetworkInterface ni = netInterfaces.nextElement();
                    Enumeration<InetAddress> ips = ni.getInetAddresses();
                    //ni.getName();
                    while (ips.hasMoreElements()) {
                        ip = ips.nextElement();
                        if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                                && ip.getHostAddress().indexOf(":") == -1) {
                            if (null == list) {
                                list = new ArrayList<>();
                                list.add(ip.getHostAddress());
                            } else {
                                list.add(ip.getHostAddress());
                            }
//							bFindIP = true;
//							break;
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static String getLocalOneIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException var1) {
            logger.error("get local ip UnknownHostException", var1, new Object[0]);
            return "";
        }
    }


    public static Long getLocalIPLong() {
        return Long.valueOf(ip2Long(getLocalOneIP()));
    }



    /**
     * 获取mac地址
     * @param ip
     * @return
     */
    public static String getMac(String ip) {
        String str;
        String macAddress = "00:00:00:00:00:00";
        InputStreamReader ir = null;
        LineNumberReader in = null;
        try {
            // cmd /c C:\\Windows\\sysnative\\nbtstat.exe -a
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            ir = new InputStreamReader(p.getInputStream());
            in = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = in.readLine();
                if (str != null) {
                    if (str.indexOf("MAC ") > 1) {
                        macAddress = str.substring(
                                str.indexOf("MAC ") + 14, str.length());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            logger.error("get mac IOException",e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (ir != null) {
                    ir.close();
                }
            } catch (IOException e) {
                logger.error("get mac final IOException",e);
            }
        }
        return macAddress;
    }


}
