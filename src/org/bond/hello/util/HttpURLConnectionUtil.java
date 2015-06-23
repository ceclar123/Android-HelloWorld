package org.bond.hello.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Administrator on 2015-6-20.
 */
public class HttpURLConnectionUtil {
    private static final String SERVLET_POST = "POST";
    private static final String SERVLET_GET = "GET";
    private static final String SERVLET_DELETE = "DELETE";
    private static final String SERVLET_PUT = "PUT";

    private static String prepareParam(Map<String, Object> paramMap) {
        StringBuffer sb = new StringBuffer();
        if (paramMap == null || paramMap.isEmpty()) {
            return "";
        } else {
            for (String key : paramMap.keySet()) {
                String value = (String) paramMap.get(key);
                if (sb.length() < 1) {
                    sb.append(key).append("=").append(value);
                } else {
                    sb.append("&").append(key).append("=").append(value);
                }
            }
            return sb.toString();
        }
    }

    /**
     * Post请求
     *
     * @param urlStr
     * @param paramMap
     * @throws Exception
     */
    public static String doPost(String urlStr, Map<String, Object> paramMap) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        String paramStr = prepareParam(paramMap);

        conn.setRequestMethod(SERVLET_POST);
        conn.setDoInput(true);//允许输入
        conn.setDoOutput(true);//允许输出
        conn.setConnectTimeout(6 * 1000);//设置连接超时
        conn.setUseCaches(false);//设置不使用缓存
        conn.setRequestProperty("Connection", "Keep-Alive");//维持长连接
        conn.setRequestProperty("Charset", "UTF-8");//设置字符集
        conn.setRequestProperty("Content-Length", String.valueOf(paramStr.length()));//设置文件的总长度
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置文件类型
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; U; Android 2.2.1; en-us; Nexus One Build/FRG83) AppleWebKit/533.1(KHTML, like Gecko)Version / 4.0 Mobile Safari/533.1");

        OutputStream os = conn.getOutputStream();
        os.write(paramStr.toString().getBytes("UTF-8"));
        os.close();

        String result = "";
        BufferedReader br = null;
        try {
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = br.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(HttpURLConnectionUtil.class.toString(), e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
        }
        conn.disconnect();

        return result;
    }

    /**
     * Get请求
     *
     * @param urlStr
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String doGet(String urlStr, Map<String, Object> paramMap) throws Exception {
        String paramStr = prepareParam(paramMap);
        if (paramStr == null || paramStr.trim().length() < 1) {
            //
        } else {
            urlStr += "?" + paramStr;
        }

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(SERVLET_GET);
        conn.setRequestProperty("Content-Type", "text/html; charset=UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");//维持长连接
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; U; Android 2.2.1; en-us; Nexus One Build/FRG83) AppleWebKit/533.1(KHTML, like Gecko)Version / 4.0 Mobile Safari/533.1");
        conn.connect();

        String result = "";
        BufferedReader br = null;
        try {
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = br.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(HttpURLConnectionUtil.class.toString(), e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
        }

        conn.disconnect();

        return result;
    }

    /**
     * Put请求
     *
     * @param urlStr
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String doPut(String urlStr, Map<String, Object> paramMap) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(SERVLET_PUT);
        String paramStr = prepareParam(paramMap);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(paramStr.toString().getBytes("utf-8"));
        os.close();

        String result = "";
        BufferedReader br = null;
        try {
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = br.readLine()) != null) {
                result += "\n" + line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(HttpURLConnectionUtil.class.toString(), e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
        }

        return result;

    }

    public static void doDelete(String urlStr, Map<String, Object> paramMap) throws Exception {
        String paramStr = prepareParam(paramMap);
        if (paramStr == null || paramStr.trim().length() < 1) {

        } else {
            urlStr += "?" + paramStr;
        }
        System.out.println(urlStr);
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod(SERVLET_DELETE);
        //屏蔽掉的代码是错误的，java.net.ProtocolException: HTTP method DELETE doesn't support output
/*		OutputStream os = conn.getOutputStream();
        os.write(paramStr.toString().getBytes("utf-8"));
		os.close();  */

        if (conn.getResponseCode() == 200) {
            System.out.println("成功");
        } else {
            System.out.println(conn.getResponseCode());
        }
    }


}
