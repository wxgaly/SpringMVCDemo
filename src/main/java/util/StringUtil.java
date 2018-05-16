/**
 * @Title:  StringUtil.java
 * @Package nova.java.util
 * @Description:TODO
 * @author  leesa<www.enjoymylife@163.com>
 * @date    May 18, 2016 10:23:48 AM
 * @version V1.0
 */
package util;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: StringUtil
 * Description: StringUtil implements some method that process String.
 * @author leesa<www.enjoymylife@163.com>
 *
 */
public class StringUtil {
	/**
	 * the method is like String.trim, but support trim the char user defines.
	 * @param sourceStr
	 * @param ch
	 * @return
	 */
	public static String trim(String sourceStr, char ch) {
		if (sourceStr != null && !sourceStr.isEmpty()) {
			 
			int count = sourceStr.length();
			int offset = 0;
			int start = offset;
			int last = offset + count - 1;
	        int end = last;
	        while ((start <= end) && (sourceStr.charAt(start) == ch)) {
	            start++;
	        }
	        while ((end >= start) && (sourceStr.charAt(end) == ch)) {
	            end--;
	        }
	        if (start == offset && end == last) {
	            return sourceStr;
	        }
	        return sourceStr.substring(start, end + 1);
		} else {
			return sourceStr;
		}
	}
	
	/**
	 * 
	 * @param string
	 * @return if the string is not null and not empty return true otherwise return false.
	 */
	public static boolean hasContent(String string) {
		if (string != null && !string.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * judge whether the string array has content.
	 * @param strings
	 * @return true if not null and length greater than zero, otherwise false.
	 */
	public static boolean hasContent(String [] strings) {
		if (strings != null) {
			if (strings.length > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * if the strings's content count equals to the count pass in,the func returns true,
	 * otherwise return false.
	 * @param strings
	 * @param count
	 * @return true or false.
	 */
	public static boolean judgeCount(String[] strings, int count) {
		if (strings != null) {
			if (strings.length == count) {
				return true;
			}
		}
		return false;
	}

	/**
     * Returns true if the string is null or 0-length.
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
	
	/**
	 * 
	 * @param count
	 * @param source
	 * @return the last four character of string or empty string.
	 */
	public static String getSuffix(int count, String source) {
		String suffix = "";
		if (!isEmpty(source) && source.length() >= count) {
			suffix = source.substring(source.length() - count, source.length());
		}
		return suffix;
	}
	
	/**
	 * 
	 * @param count
	 * @param source
	 * @return the last four character of sn or empty string.
	 */
	public static String getPreffix(int count, String source) {
		String preffix = "";
		if (!isEmpty(source) && source.length() >= count) {
			preffix = source.substring(0, count);
		}
		return preffix;
	}
	
	// 判断手机格式是否正确
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	// 判断email格式是否正确
	public static boolean isEmail(String email) {
		String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	
	//用户名 匹配规则：3-32位字母、数字、下划线，需以字母开头
    public static boolean isUsername(String username){
        String regex = "^([a-zA-Z])([a-zA-Z0-9_]{2,31})";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(username);
        return m.matches();
    }


	
	//密码 匹配规则：6-18位字母、数字、下划线，
    public static boolean isPassword(String pswd){
        String regex = "^([a-zA-Z0-9_]{6,18})";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pswd);
        return m.matches();
    }
    
    
    /**
     * List转换String 
     * @param list:需要转换的List            
     * @return String转换后的字符串
     */ 
    public static String listToString(List<?> list) { 
    	String SEP1 = " "; 
        StringBuffer sb = new StringBuffer(); 
        if (list != null && list.size() > 0) { 
            for (int i = 0; i < list.size(); i++) { 
                if (list.get(i) == null || list.get(i) == "") { 
                    continue; 
                } 
                // 如果值是list类型则调用自己 
                if (list.get(i) instanceof List) { 
                    sb.append(listToString((List<?>) list.get(i))); 
                    sb.append(SEP1); 
                } else { 
                    sb.append(list.get(i)); 
                    sb.append(SEP1); 
                } 
            } 
        } 
        return  sb.toString(); 
    }  
    
}
