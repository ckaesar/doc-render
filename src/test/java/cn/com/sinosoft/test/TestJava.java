package cn.com.sinosoft.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import cn.com.sinosoft.app.utils.ResourceLoader;

public class TestJava {
	
	@Test
	public void test() {
		String testString = "1aaaaaaaaaa1<table>1aaaaaaaaaa1</table>1aaaaaaaaaa1";
		if(testString.contains("table")){
			System.out.println("yes");
		}else{
			System.out.println("no");
		}
		
	}
	
	public static void main(String[] args) {
		String path = ResourceLoader.getPath("config/templates/");
		String filePath = path + "demo.html";
		String string = read(filePath);
		System.out.println(string);
		
	}
	
	/**
     * 主要是输入流的使用，最常用的写法
     * @param filePath
     * @return
     */
    public static String read(String filePath)
    {
        // 读取txt内容为字符串
        StringBuffer txtContent = new StringBuffer();
        // 每次读取的byte数
        byte[] b = new byte[8 * 1024];
        InputStream in = null;
        try
        {
            // 文件输入流
            in = new FileInputStream(filePath);
            while (in.read(b) != -1)
            {
                // 字符串拼接
                txtContent.append(new String(b));
            }
            // 关闭流
            in.close();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return txtContent.toString();
    }
}
