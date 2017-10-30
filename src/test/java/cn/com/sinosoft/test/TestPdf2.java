package cn.com.sinosoft.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import cn.com.sinosoft.app.freemaker.HtmlGenerator;
import cn.com.sinosoft.app.pdf.PdfDocumentGenerator;
import cn.com.sinosoft.app.pdf.bean.OverseaVo;
import cn.com.sinosoft.app.pdf.exception.DocumentGeneratingException;
import cn.com.sinosoft.app.utils.ResourceLoader;

public class TestPdf2 extends TestCase {

	private static HtmlGenerator htmlGenerator = new HtmlGenerator();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String logConfigPath = ResourceLoader.getPath("log4j.properties");
		PropertyConfigurator.configure(logConfigPath);
	}

	public void test(String outputFile) throws DocumentGeneratingException {

		long start = System.currentTimeMillis();

		// 模板数据
		List<OverseaVo> overseaVos = new ArrayList<OverseaVo>();
		OverseaVo overseaVo = new OverseaVo();
		overseaVo.setDestination("目标");
		overseaVo.setHolderName("姓名");
		OverseaVo overseaVo2 = new OverseaVo();
		overseaVo2.setDestination("目标2");
		overseaVo2.setHolderName("姓名2");
		overseaVos.add(overseaVo);
		overseaVos.add(overseaVo2);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("test", "jgierjairgaerjgiriair结果IE日高噶几家而过");
		String[] strs = new String[]{"aa", "bb"};  
		List<String> strList = new ArrayList<String>();
		strList.add("ccc");
		strList.add("ddd");
		dataMap.put("strs", strList);
		dataMap.put("overseaVos", overseaVos);
		dataMap.put("rows", 3);
		// classpath 中模板路径
		String template = "config/templates/demo.html";
		// classpath 路径
		String outputFileClass = ResourceLoader.getPath("");
		// 生成pdf路径
		outputFile = outputFile == null ? new File(outputFileClass)
				.getParentFile().getParent()
				+ "/tmp/"
				+ System.currentTimeMillis() + ".pdf" : outputFile;

		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		pdfGenerator.generate(template, overseaVo, dataMap, outputFile);

		System.err.println(" \n pdf生成成功  IS OK path=\n" + outputFile);
		System.err.println("耗时time=" + (System.currentTimeMillis() - start)
				/ 1000);

	}
	
	@Test
	public void testGenerate() {
		String outputFileClass = ResourceLoader.getPath("");
		String outputFile = new File(outputFileClass)
		.getParentFile().getParent()
		+ "/tmp/"
		+ System.currentTimeMillis() + ".pdf" ;
		try {
			test(outputFile);
		} catch (Exception ex) {
			System.err.println(" \n pdf生成失败");
			ex.printStackTrace();
		}
		
		File file = new File(outputFile);
		
		assertNotNull("生成pdf文件为空", file);
		assertTrue("pdf文件不存在", file.exists());
		assertTrue("pdf生成文件大小错误", file.getFreeSpace()>178000);
		
	}
	
	
}
