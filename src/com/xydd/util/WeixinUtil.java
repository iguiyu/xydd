package com.xydd.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xydd.weixin.pojo.AccessToken;
import com.xydd.weixin.pojo.Menu;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;



/**
 * 公众平台通用接口工具类
 * 
 * @author vincent
 * @date 2013-08-09
 */
public class WeixinUtil {
//	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	// 菜单创建（POST） 限100（次/天）   
	public  String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"; 
	
	// 获取access_token的接口地址（GET） 限200（次/天）   
	public  String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
	  
	
	public String get_openid_url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";
	
	public String get_userinfo_url="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public String get_userinfo_url2="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public String send_template_msg="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	public String deliver_notify_url="https://api.weixin.qq.com/pay/delivernotify?access_token=ACCESS_TOKEN";
	
	public static HashMap tokenMap=new HashMap();
	/** 
	 * 获取access_token 
	 *  
	 * @param appid 凭证 
	 * @param appsecret 密钥 
	 * @return 
	 */  
	public AccessToken getAccessToken(String appid, String appsecret) {  
	    AccessToken accessToken = null;
	    Date d=new Date();
	    if(tokenMap.get("accessToken")!=null&&(d.getTime()-((Date)tokenMap.get("date")).getTime())<7000000)
	    {
	    	accessToken=(AccessToken)tokenMap.get("accessToken");
	    }
	    else
	    {
		    String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
		    JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
		    // 如果请求成功   
		    if (null != jsonObject) {  
		        try {  
		            accessToken = new AccessToken();  
		            accessToken.setToken(jsonObject.getString("access_token"));  
		            accessToken.setExpiresIn(jsonObject.getInt("expires_in"));  
		        } catch (JSONException e) {  
		            accessToken = null;  
		            e.printStackTrace();
		            // 获取token失败   
	//	            log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
		        }
		        tokenMap.put("accessToken", accessToken);
		        tokenMap.put("date", d);
		    }  
	    }
	    return accessToken;  
	} 

	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
//			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
//			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	
	/**
	 * 创建菜单
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public int createMenu(Menu menu, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// 调用接口创建菜单
		WeixinUtil util=new WeixinUtil();
		JSONObject jsonObject = util.httpRequest(url, "POST", jsonMenu);

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				//log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		return result;
	}
	
	
	public int sendTempMsg(String appid, String appsecret,String openid,String tempId,String url,Map data )
	{
		int result=0;
		AccessToken accessToken=getAccessToken(appid,appsecret);
		
		//System.out.println("Token::::::::::"+accessToken.getToken()+"|||||"+accessToken.getExpiresIn());
		String requestUrl = send_template_msg.replace("ACCESS_TOKEN", accessToken.getToken());  
		
		Map map = new HashMap();
		map.put("touser", openid);
		map.put("template_id",tempId);
		map.put("url", url);
		map.put("topcolor", "json");
		map.put("data", data);

		
		JSONObject json = JSONObject.fromObject(map);
		
		JSONObject jsonObject = httpRequest(requestUrl, "POST", json.toString());

		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				//log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}

		
		return result;
	}
	
	public String getOpenId(String appid, String appsecret,String code)
	{
		try{
			String result="";
			String requestUrl = get_openid_url.replace("APPID", appid).replace("APPSECRET", appsecret).replace("CODE", code);  
		    JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
		    System.out.println(jsonObject);
		    if (null != jsonObject) {
				
				result = jsonObject.getString("openid");
					
			}
			return result;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
		
	}
	
	public UserInfo getUserInfo(String appid, String appsecret,String openid)
	{
		try{
		UserInfo user=new UserInfo();
		AccessToken accessToken=getAccessToken(appid,appsecret);
		
		//System.out.println("Token::::::::::"+accessToken.getToken()+"|||||"+accessToken.getExpiresIn());
		String requestUrl = get_userinfo_url.replace("ACCESS_TOKEN", accessToken.getToken()).replace("OPENID", openid);  
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null); 
		System.out.println(jsonObject);
	    if (null != jsonObject) {
			
			user.setSubscribe(jsonObject.getString("subscribe"));
			user.setOpenid(jsonObject.getString("openid"));
			if(user.getSubscribe().equals("1"))
			{
				user.setNickname(jsonObject.getString("nickname"));
				user.setSex(jsonObject.getString("sex"));
				user.setLanguage(jsonObject.getString("language"));
				user.setCity(jsonObject.getString("city"));
				user.setProvince(jsonObject.getString("province"));
				user.setCountry(jsonObject.getString("country"));
				user.setHeadimgurl(jsonObject.getString("headimgurl"));
				user.setSubscribe_time(jsonObject.getString("subscribe_time"));
			}
			else
			{
				return null;
			}
		}
		
		return user;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public UserInfo getUserInfo2(String appid, String appsecret,String code)
	{
		try{
		UserInfo user=new UserInfo();
		String openId="";
		String accessToken="";
		String requestUrl = get_openid_url.replace("APPID", appid).replace("APPSECRET", appsecret).replace("CODE", code);  
	    JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
	    //System.out.println(jsonObject);
	    if (null != jsonObject) {
			
			openId = jsonObject.getString("openid");
			accessToken=jsonObject.getString("access_token");
				
		}
	    
	    requestUrl = get_userinfo_url2.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);  
		jsonObject = httpRequest(requestUrl, "GET", null); 
		//System.out.println(jsonObject);
	    if (null != jsonObject) {
			
			user.setOpenid(jsonObject.getString("openid"));
			
				user.setNickname(jsonObject.getString("nickname"));
				user.setSex(jsonObject.getString("sex"));
				user.setLanguage(jsonObject.getString("language"));
				user.setCity(jsonObject.getString("city"));
				user.setProvince(jsonObject.getString("province"));
				user.setCountry(jsonObject.getString("country"));
				user.setHeadimgurl(jsonObject.getString("headimgurl"));
			
		}
		return user;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	

	
	
	
	public static void main(String[] args) {
		//sendDeliverNotify("wxf18af3ff6713b7d7","25d96497b855396018842ab3e4b036b1","Qmds0NrFrbRmdBkcSdJH7KnySQJNOVkIZmcU2PTSfQ8gARdK4tetrnp7C6eGyjPbSIz9QN8F3aZ1b68TMjMr9siGVmSVKacnLtlG7lly5k8O3sQoU3BrqS1zK41oH25D","ohdC9tyqtygTiMm3Ip5W9SBWw4G8","1219111701201408103204253313","VdV6cra4BcXZeEFc");
		//getUserInfo("wxf18af3ff6713b7d7","25d96497b855396018842ab3e4b036b1","ohdC9tyqtygTiMm3Ip5W9SBWw4G8");
		WeixinUtil util1=new WeixinUtil();
		System.out.println(util1.get_userinfo_url.replace("ACCESS_TOKEN", "112121"));
		System.out.println(util1.get_userinfo_url);
		
	}
}