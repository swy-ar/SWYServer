package com.swy.server.template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.swy.server.utils.Log;

import javolution.util.FastMap;
import freemarker.template.Configuration;  
import freemarker.template.DefaultObjectWrapper;  
import freemarker.template.Template;  
import freemarker.template.TemplateException;

/** 
 *  
 * 模板工具类 :使用 FreeMarkert 解析模板
 */  
public class FreeMarkertUtil {

	private static Logger logger = LoggerFactory.getLogger(FreeMarkertUtil.class);
	
	public static Map<String, Object> parseTemplate ( String templatePath , String templateName,  Map< ? , ? > root )
	{
		return parseTemplate ( templatePath, templateName, "UTF-8", root );
	}

	/**
	 * @param templatePath 模板文件存放目录
	 * @param templateName 模板文件名称
	 * @param root 数据模型根对象
	 * @param templateEncoding 模板文件的编码方式
	 */
	public static Map<String, Object> parseTemplate ( String templatePath , String templateName , String templateEncoding , Map< ? , ? > root)
	{
		Map<String, Object> result = FastMap.newInstance ( );
		try {
			Configuration config = new Configuration ( );
			File file = new File ( templatePath );
			// 设置要解析的模板所在的目录，并加载模板文件
			config.setDirectoryForTemplateLoading ( file );
			// 设置包装器，并将对象包装为数据模型
			config.setObjectWrapper ( new DefaultObjectWrapper ( ) );

			//获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			Template template = config.getTemplate ( templateName , templateEncoding );
			// Writer out = new StringWriter ( 2048 );
			Writer out = new StringWriter ( );
			template.process ( root , out );
			String content = out.toString ( );
			result.put ( "content" , content );
			out.flush ( );
			out.close ( );
		} catch ( TemplateException e ) {
			Log.error ( e );
			result.put("content", e.getLocalizedMessage());
		} catch ( IOException e ) {
			Log.error(e);
			result.put("content", e.getLocalizedMessage());
		}

		return result;
	}

	public static String parseFileContent(String filePath, String fileName)
	{
		int len = 0;

		StringBuffer str = new StringBuffer("");
		File file = new File(filePath + "/" + fileName);
		try {
			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);

			BufferedReader in = new BufferedReader(isr);
			String line = null;
			while ((line=in.readLine()) != null) {
				if (len != 0) {  // 处理换行符的问题
					str.append("\r\n"+line);
				} else {
					str.append(line);
				}
				len++;
			}
			in.close();
			is.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return str.toString();
	}

}  