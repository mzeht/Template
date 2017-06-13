package com.wpmac.androidnougatframework.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.text.format.DateUtils;
import android.util.TypedValue;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * 
 */
public class TempUtils {

	
	public static  final  double EARTH_RADIUS = 6378.137;
	static final String LOG_TAG = "PullToRefresh";
	public static void warnDeprecation(String depreacted, String replacement) {
		
	}
	public static String getUUID(){
		
		return UUID.randomUUID().toString().replace("-", "");
	}
    /**
     * dp转换PX
     * @param res
     * @param dp 
     * @return
     */
	public static int dpToPx(Resources res, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
	}

	/**
	 * 转换long型日期格式
	 * 
	 * @param context
	 * @param date
	 * @return
	 */
	public static String formatDate(Context context, long date) {
		@SuppressWarnings("deprecation")
		int format_flags = DateUtils.FORMAT_NO_NOON_MIDNIGHT
				| DateUtils.FORMAT_ABBREV_ALL | DateUtils.FORMAT_CAP_AMPM
				| DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_DATE
				| DateUtils.FORMAT_SHOW_TIME;
		return DateUtils.formatDateTime(context, date, format_flags);
	}

	/**
	 * 转换long型日期格式
	 * 
	 * @param date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String formatDate(long date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date(date));
	}
	
	

	/**
	 * 转换long型日期格式
	 * 
	 * @param date
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String formatHour(long date) {
		SimpleDateFormat format = new SimpleDateFormat("HH");
		return format.format(new Date(date));
	}


	/**
	 * 获取当前的时间
	 * 
	 * @param context
	 * @return
	 */
	public static String getTime(Context context) {
		return formatDate(context, System.currentTimeMillis());
	}

	/**
	 * 获取当前的时间
	 * 
	 * @return
	 */
	public static String getTime() {
		return formatDate(System.currentTimeMillis());
	}
	
	

	/**
	 * 判断手机号码格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isCellphone(String str) {
		Pattern pattern = Pattern.compile("1[34578][0-9]{9}");
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 获取版本号
	 * @param context
	 * @return
	 */
	public static String getAppVersion(Context context)
	
	{
		PackageInfo info=null;
		try {
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return info.versionName;

	}
	
	/**
	 * 判断是否是有效值
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValidValue(String str) {
		boolean b = false;
		if(str==null){
			b= false;
		}else if("".equals(str)){
			b=false;
		}else if("null".equals(str)){
			b=false;
		}else if("NULL".equals(str)){
			b=false;
		}else if("0".equals(str)){
			b = false;
		}else{
			b = true;
		}
		return b;
	}
	
	
	/**
	 * 判断是否是有效值
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValidValueString(String str) {
		boolean b = false;
		if(str==null){
			b= false;
		}else if("".equals(str)){
			b=false;
		}else if("null".equals(str)){
			b=false;
		}else if("NULL".equals(str)){
			b=false;
		}else{
			b = true;
		}
		return b;
	}
	
	/** 
	    * 将json格式的字符串解析成Map对象 <li> 
	    * json格式：{"name":"admin","retries":"3fff","testname" 
	    * :"ddd","testretries":"fffffffff"} 
	    */  
	public static HashMap<String, String> toHashMap(String object)
	   {  
	       HashMap<String, String> data = new HashMap<String, String>();
	       // 将json字符串转换成jsonObject  
	       JSONObject singleObject;
		try {
			singleObject = new JSONObject(object);
		
	       //JSONObject jsonObject = JSONObject.fromObject(object);  
	       Iterator it = singleObject.keys();
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());
	           
	           String value = singleObject.getString(key).toString();
	           data.put(key, value);  
	       }  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return data;  
	   }  
	
	
	/**
	 *  分页信息
	 * @author 陈玉伟
	 * 2015-11-20 下午1:36:12
	 * @param object
	 * @return
	 */
	public static Map<String,String> pageSizeJsonToHashMap(String object)
	   {  
	       Map<String,String> data = new HashMap<String,String>();
	       // 将json字符串转换成jsonObject  
	       JSONObject singleObject;
		try {
			singleObject = new JSONObject(object);
	       Iterator it = singleObject.keys();
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());
	           String paramvalue =  singleObject.getString(key).toString();
	           data.put(key, paramvalue); 
	       }  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return data;  
	   }

	public static String mapToJsonString(Map<String,String> map)
	{
		String jsonStr = "";
		try {
		JSONObject singleObject = new JSONObject();
		for(Map.Entry<String,String> entry:map.entrySet()){
			singleObject.put(entry.getKey(), entry.getValue());

		}
			jsonStr = singleObject.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}


	
	
	/** 
	    * 将json格式的字符串解析成Map对象 <li> 
	    * json格式：{"name":"admin","retries":"3fff","testname" 
	    * :"ddd","testretries":"fffffffff"} 
	    */  
	public static HashMap<String, Map<String,String>> jsonToHashMap(String object)
	   {  
	       HashMap<String, Map<String,String>> data = new HashMap<String, Map<String,String>>();
	      // Map<String, String> map = new HashMap<String, String>();
	       // 将json字符串转换成jsonObject  
	       JSONObject singleObject;
		try {
			singleObject = new JSONObject(object);
	       //JSONObject jsonObject = JSONObject.fromObject(object);  
	       Iterator it = singleObject.keys();
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {  
	           String key = String.valueOf(it.next());
	           Map<String, String> paramMap = new HashMap<String, String>();
	           if(TempUtils.isValidValueString(singleObject.getString(key))){
	        	   JSONObject paramObject= new JSONObject(singleObject.getString(key));
	        	   Iterator prarmIt = paramObject.keys();
	        	   while(prarmIt.hasNext()){
	        		   String paramkey = String.valueOf(prarmIt.next());
	        		   String paramvalue =  paramObject.getString(paramkey).toString();
	        		   paramMap.put(paramkey, paramvalue);
	        	   }
	        	   data.put(key, paramMap);
	           } 
	       }  
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return data;  
	   }  

	public static Map<String, String> CompanyIdtoHashMap(String CompanyId)
	   {  
		Map<String,String> param = new HashMap<String,String>();
		String[] s=CompanyId.split(",");
		for(int i=0;i<s.length;i++){
			param.put(s[i].split("=")[0], s[i].split("=")[1]);
		}
	       return  param;  
	   }  
	
	public static List<String> arrToList(String[] arr)
	   {  
		List<String> list = new ArrayList<String>();
		for(int i=0;i<arr.length;i++){
			list.add(arr[i]);
		}
	       return  list;  
	   }
	

}
