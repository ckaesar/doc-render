package cn.com.sinosoft.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import cn.com.sinosoft.app.freemaker.HtmlGenerator;
import cn.com.sinosoft.app.pdf.PdfDocumentGenerator;
import cn.com.sinosoft.app.pdf.bean.OverseaVo;
import cn.com.sinosoft.app.pdf.exception.DocumentGeneratingException;
import cn.com.sinosoft.app.utils.ResourceLoader;

public class TestPdf extends TestCase {

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
		OverseaVo overseaVo = new OverseaVo();
		
		TestVo testVo = new TestVo();
		testVo.getMap().put("testKey", "testKey");
		testVo.setName("测试姓名");
		testVo.setNbsp(" ");
		Map<String, Object> dataMap = testVo.fillDataMap();
		dataMap = (Map<String, Object>) testVo.getMap().get("map");

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
		pdfGenerator.generate(template, testVo, dataMap, outputFile);

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
