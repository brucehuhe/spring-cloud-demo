package org.spring.cloud.order.service.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 添加网络打印机打印操作
 * 
 * @author Administrator 2017年12月20日
 */
public class PrinterServer {

	public static final String URL = "http://api.feieyun.cn/Api/Open/";// 不需要修改
	public static final String USER = "506986339@qq.com";// *必填*：账号名
	public static final String UKEY = "v8WC6WtshCT62gAq";// *必填*: 注册账号后生成的UKEY
	public static final String SN = "917511885";// *必填*：打印机编号，必须要在管理后台里添加打印机或调用API接口添加之后，才能调用API

	// **********测试时，打开下面注释掉方法的即可,更多接口文档信息,请访问官网开放平台查看**********
	public static void main(String[] args) throws Exception {
		String method1 = print(SN);
		System.out.println(method1);
	}
	// =====================以下是函数实现部分================================================
	private static String print(String sn) {
		// 标签说明：
		// 单标签:
		// "<BR>"为换行,"<CUT>"为切刀指令(主动切纸,仅限切刀打印机使用才有效果)
		// "<LOGO>"为打印LOGO指令(前提是预先在机器内置LOGO图片),"<PLUGIN>"为钱箱或者外置音响指令
		// 成对标签：
		// "<CB></CB>"为居中放大一倍,"<B></B>"为放大一倍,"<C></C>"为居中,<L></L>字体变高一倍
		// <W></W>字体变宽一倍,"<QR></QR>"为二维码,"<BOLD></BOLD>"为字体加粗,"<RIGHT></RIGHT>"为右对齐
		// 拼凑订单内容时可参考如下格式
		// 根据打印纸张的宽度，自行调整内容的格式，可参考下面的样例格式
		String content;
		content = "<C>小七，你好</C><BR>";
		content += "名称　      数量  金额  积分<BR>";
		// content += "------------------------<BR>";
		content += "--------------------------------<BR>";
		content += "qt123123456789";
		// content +=" "+"2.0";
		content += "       " + "10";
		content += "       " + "2.0";
		content += "       " + "1.0";
		content += "<BR>";
		content += "qt123123456789";
		// content +=" "+"5.0";
		content += "       " + "50";
		content += "       " + "3.0";
		content += "       " + "1.0";
		content += "<BR>";
		content += "         总价格:" + "$20.0+100积分" + "<BR>";
		content += "订餐给钱：2016-08-08 08:08:08<BR>";
		// content += "<QR>http://www.dzist.com</QR>";
		// 通过POST请求，发送打印信息到服务器
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000)// 读取超时
				.setConnectTimeout(30000)// 连接超时
				.build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		HttpPost post = new HttpPost(URL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("user", USER));
		String STIME = String.valueOf(System.currentTimeMillis() / 1000);
		nvps.add(new BasicNameValuePair("stime", STIME));
		nvps.add(new BasicNameValuePair("sig", signature(USER, UKEY, STIME)));
		nvps.add(new BasicNameValuePair("apiname", "Open_printMsg"));// 固定值,不需要修改
		nvps.add(new BasicNameValuePair("sn", sn));
		nvps.add(new BasicNameValuePair("content", content));
		nvps.add(new BasicNameValuePair("times", "1"));// 打印联数
		CloseableHttpResponse response = null;
		String result = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			response = httpClient.execute(post);
			int statecode = response.getStatusLine().getStatusCode();
			if (statecode == 200) {
				HttpEntity httpentity = response.getEntity();
				if (httpentity != null) {
					// 服务器返回的JSON字符串，建议要当做日志记录起来
					result = EntityUtils.toString(httpentity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				post.abort();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	// 生成签名字符串
	private static String signature(String USER, String UKEY, String STIME) {
		String s = DigestUtils.sha1Hex(USER + UKEY + STIME);
		return s;
	}
}
