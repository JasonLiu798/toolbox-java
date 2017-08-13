package com.atjl.util.net.http;

import com.atjl.util.json.JSONFastJsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http client
 */
public final class HttpClientUtil {
	private HttpClientUtil(){
		throw new UnsupportedOperationException();
	}

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static final String ACCEPT_LANGUAGE = "Accept-Language";
    private static final String ZH_CN = "zh-cn";
    private static final String UTF_8 = "UTF-8";
    private static final int SEC_3K_MILE = 3000;

    /**
     * 获取HttpClient对象
     *
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    protected static HttpClient getHttpClient() throws KeyManagementException, NoSuchAlgorithmException {
        HttpClient httpclient = new DefaultHttpClient();
        return httpclient;
    }

    /**
     * 得到post方式的Http请求对象
     *
     * @param uri
     * @return HttpPost
     */
    protected static HttpPost getHttpPost(String uri) {
        /** 创建post请求 **/
        return new HttpPost(uri);
    }

    /**
     * 得到get方式的Http请求对象
     *
     * @param uri
     * @return HttpGet
     */
    protected static HttpGet getHttpGet(String uri) {
        /** 创建get请求 **/
        return new HttpGet(uri);
    }

    /**
     * 设置请求报头
     *
     * @param httpGet
     * @param headerMap
     * @return HttpGet
     */
    protected static HttpGet setHeader(HttpGet httpGet, Map<String, String> headerMap) {
        if (headerMap != null) {
            for (Map.Entry<String, String> map : headerMap.entrySet()) {
                httpGet.setHeader(map.getKey(), map.getValue());
            }
        }
        /** 设置接收语言 **/
        httpGet.setHeader(ACCEPT_LANGUAGE, ZH_CN);
        return httpGet;
    }

    /**
     * 设置请求报头
     *
     * @param httpPost
     * @param headerMap
     * @return HttpPost
     */
    protected static HttpPost setHeader(HttpPost httpPost, Map<String, String> headerMap) {
        if (headerMap != null) {
            for (Map.Entry<String, String> map : headerMap.entrySet()) {
                httpPost.setHeader(map.getKey(), map.getValue());
            }
        }
        /** 设置接收语言 **/
        httpPost.setHeader(ACCEPT_LANGUAGE, ZH_CN);
        return httpPost;
    }

    /**
     * 发送POST请求
     *
     * @param uri
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sendPostRequest(String uri) throws KeyManagementException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return sendPostRequest(uri, null);
    }

    /**
     * 发送POST请求,默认超时时间3000MS
     *
     * @param uri
     * @param paramMap
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sendPostRequest(String uri, Map<String, String> paramMap) throws KeyManagementException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return sendPostRequest(uri, paramMap, null, UTF_8, SEC_3K_MILE, SEC_3K_MILE);
    }

    /**
     * 发送POST请求
     *
     * @param uri
     * @param paramMap
     * @param connTime
     * @param soTime
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sendPostRequest(String uri, Map<String, String> paramMap, int connTime, int soTime) throws KeyManagementException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return sendPostRequest(uri, paramMap, null, UTF_8, connTime, soTime);
    }

    /**
     * 发送POST请求
     *
     * @param uri
     * @param paramMap
     * @param headerMap
     * @param connTime
     * @param soTime
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sendPostRequest(String uri, Map<String, String> paramMap, Map<String, String> headerMap, int connTime, int soTime) throws KeyManagementException, NoSuchAlgorithmException,
            UnsupportedEncodingException {
        return sendPostRequest(uri, paramMap, headerMap, UTF_8, connTime, soTime);
    }

    /**
     * 发送POST请求
     *
     * @param uri
     * @param paramMap
     * @param headerMap
     * @param code
     * @param connTime
     * @param soTime
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sendPostRequest(String uri, Map<String, String> paramMap, Map<String, String> headerMap, String code, int connTime, int soTime) throws KeyManagementException,
            NoSuchAlgorithmException, UnsupportedEncodingException {
        logger.debug("execute sendPostRequest begin");
        long startTime = System.currentTimeMillis();
        /** 创建客户端 **/
        HttpClient httpclient = getHttpClient();
        HttpParams params = httpclient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, connTime);
        HttpConnectionParams.setSoTimeout(params, soTime);
        logger.info("sendRequest url = " + uri);
        HttpPost post = getHttpPost(uri);
        setHeader(post, headerMap);
        String responseBody = null;
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if (paramMap != null) {
            /** 设置post参数 **/
            for (Map.Entry<String, String> m : paramMap.entrySet()) {
                paramList.add(new BasicNameValuePair(m.getKey(), m.getValue()));
                logger.info("Param KEY = [" + m.getKey() + "] & VALUE = [" + m.getValue() + "]");
            }
            if (paramList != null && paramList.size() > 0) {
                UrlEncodedFormEntity uef = new UrlEncodedFormEntity(paramList, code);
                post.setEntity(uef);
            }
        }
        try {
            HttpResponse response = httpclient.execute(post);
            responseBody = readInputStream(response.getEntity().getContent());
            logger.info("\n" + responseBody + "\n");
            logger.info("sendPostRequest method execute time is [" + (System.currentTimeMillis() - startTime) + "] ms");
        } catch (Exception e) {
            logger.error("execute sendPostRequest exception ", e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        logger.debug("execute sendPostRequest end");
        return responseBody;
    }

    /**
     * 发送 json post
     * @param url
     * @param param
     * @param connectionTimeOut
     * @param socketTimeOut
     * @return
     * @throws Exception
     */
//    public static Map<String, Object> sendPostJson(String url, String param, int connectionTimeOut, int socketTimeOut) throws Exception {
		public static String sendPostJson(String url, String param, int connectionTimeOut, int socketTimeOut) throws Exception {
        try {
            logger.info("send post{},url :{}", param, url);
            HttpClient defaultHttpClient = getHttpClient();
            StringEntity entity = new StringEntity(param, "UTF-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");

            HttpPost httpost = new HttpPost(url);
            httpost.setEntity(entity);
            // 请求超时
            defaultHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeOut);
            // 读取超时
            defaultHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, socketTimeOut);
            HttpResponse execute = defaultHttpClient.execute(httpost);
            String resultData = EntityUtils.toString(execute.getEntity(), "UTF-8");
            logger.info("send post {}, result:{}", param, resultData);
            return resultData;//JSONFastJsonUtil.jsonToObject(resultData);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("sendPostJson {}", e);
            }
            logger.error("发送 POST 请求出现异常！ param: {} ,url:{}", e.getMessage(), param, url);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 发送POST请求
     *
     * @param uri
     * @param entity
     * @param headerMap
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sendPostRequest(String uri, UrlEncodedFormEntity entity, Map<String, String> headerMap) throws KeyManagementException, NoSuchAlgorithmException, UnsupportedEncodingException {
        logger.debug("execute sendPostRequest begin");
        long startTime = System.currentTimeMillis();
        /** 创建客户端 **/
        HttpClient httpclient = getHttpClient();
        logger.info("sendRequest url = " + uri);
        HttpPost post = getHttpPost(uri);
        String responseBody = null;
        try {
            post.setEntity(entity);
            setHeader(post, headerMap);
            HttpResponse response = httpclient.execute(post);
            responseBody = readInputStream(response.getEntity().getContent());
            logger.info("\n" + responseBody + "\n");
            logger.info("sendPostRequest method execute time is [" + (System.currentTimeMillis() - startTime) + "] ms");
        } catch (Exception e) {
            logger.error("execute sendPostRequest exception ", e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        logger.debug("execute sendPostRequest end");
        return responseBody;
    }

    /**
     * 发送GET请求
     *
     * @param uri
     * @return String
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public static String sendGetRequest(String uri) throws KeyManagementException, NoSuchAlgorithmException {
        return sendGetRequest(uri, null);
    }

    /**
     * 发送GET请求
     *
     * @param uri
     * @param headerMap
     * @return String
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public static String sendGetRequest(String uri, Map<String, String> headerMap) throws KeyManagementException, NoSuchAlgorithmException {
        logger.debug("execute sendGetRequest begin");
        long startTime = System.currentTimeMillis();
        /** 创建客户端 **/
        HttpClient httpclient = getHttpClient();
        logger.info("sendGetRequest url = " + uri);
        HttpGet get = getHttpGet(uri);
        setHeader(get, headerMap);
        String responseBody = null;
        try {
            HttpResponse response = httpclient.execute(get);
            responseBody = readInputStream(response.getEntity().getContent());
            logger.info("\n" + responseBody + "\n");
            logger.info("sendGetRequest method execute time is [" + (System.currentTimeMillis() - startTime) + "] ms");
        } catch (Exception e) {
            logger.error("execute sendGetRequest exception ", e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        logger.debug("execute sendGetRequest end");
        return responseBody;
    }

    /**
     * 发送GET请求
     *
     * @param uri
     * @return byte[]
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public static byte[] sendGetRequestStream(String uri) throws KeyManagementException, NoSuchAlgorithmException {
        logger.debug("execute sendGetRequestStream begin");
        long startTime = System.currentTimeMillis();
        /** 创建客户端 **/
        HttpClient httpclient = getHttpClient();
        logger.info("sendGetRequestStream url = " + uri);
        HttpGet get = getHttpGet(uri);
        setHeader(get, null);
        byte imgdata[] = null;
        try {
            HttpResponse response = httpclient.execute(get);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
                int ch;
                while ((ch = instream.read()) != -1) {
                    bytestream.write(ch);
                }
                imgdata = bytestream.toByteArray();
                bytestream.close();
                instream.close();
            }
            logger.info("sendGetRequestStream method execute time is [" + (System.currentTimeMillis() - startTime) + "] ms");
        } catch (Exception e) {
            logger.error("execute sendGetRequestStream exception ", e);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        logger.debug("execute sendGetRequestStream end");
        return imgdata;
    }

    /**
     * 发送xml字符串请求
     *
     * @param uri
     * @param xmlStr
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public static String sendPostFormatRequest(String uri, String xmlStr) throws KeyManagementException, NoSuchAlgorithmException {
        return sendPostFormatRequest(uri, xmlStr, "text/xml");
    }

    /**
     * 发送指定格式字符串请求
     *
     * @param uri
     * @param formatStr
     * @param reqFormat
     * @return String
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    public static String sendPostFormatRequest(String uri, String formatStr, String reqFormat) throws KeyManagementException, NoSuchAlgorithmException {
        logger.debug("execute sendGetRequest begin");
        long startTime = System.currentTimeMillis();
        /** 创建客户端 **/
        HttpClient httpclient = getHttpClient();
        logger.info("sendGetRequest url = " + uri);
        HttpPost post = getHttpPost(uri);
        String retStr = "";
        InputStreamReader reader = null;
        try {
            StringEntity myEntity = new StringEntity(formatStr, "UTF-8");
            post.addHeader("Content-Type", reqFormat);
            post.setEntity(myEntity);
            HttpResponse response = httpclient.execute(post);
            HttpEntity resEntity = response.getEntity();
            reader = new InputStreamReader(resEntity.getContent(), "UTF-8");
            char[] buff = new char[1024];
            int length = 0;
            while ((length = reader.read(buff)) != -1) {
                retStr = new String(buff, 0, length);
            }
            logger.info("\n" + retStr + "\n");
            logger.info("sendGetRequest method execute time is [" + (System.currentTimeMillis() - startTime) + "] ms");
        } catch (Exception e) {
            logger.error("execute sendGetRequest exception ", e);
        } finally {
            httpclient.getConnectionManager().shutdown();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.debug("execute sendGetRequest end");
        return retStr;
    }

    /**
     * 处理返回文件流
     *
     * @param inputStream
     * @return String
     * @throws IOException
     */
    private static String readInputStream(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, UTF_8));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null)
            buffer.append(line + "\n");
        inputStream.close();
        return buffer.toString();
    }

    /**
     * 获取参数对象
     *
     * @param paramMap
     * @param code
     * @return UrlEncodedFormEntity
     * @throws UnsupportedEncodingException
     */
    public static UrlEncodedFormEntity getHttpParamLength(Map<String, String> paramMap, String code) throws UnsupportedEncodingException {
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        UrlEncodedFormEntity uef = null;
        if (paramMap != null) {
            /** 设置post参数 **/
            for (Map.Entry<String, String> m : paramMap.entrySet()) {
                paramList.add(new BasicNameValuePair(m.getKey(), m.getValue()));
                logger.info("Param KEY = [" + m.getKey() + "] & VALUE = [" + m.getValue() + "]");
            }
            if (paramList != null && paramList.size() > 0) {
                uef = new UrlEncodedFormEntity(paramList, code);
            }
        }
        return uef;
    }
}