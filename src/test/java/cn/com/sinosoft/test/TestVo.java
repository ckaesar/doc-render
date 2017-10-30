package cn.com.sinosoft.test;

import cn.com.sinosoft.app.pdf.AbstractDocumentVo;

public class TestVo extends AbstractDocumentVo {

	private String name;
	private String nbsp;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String findPrimaryKey() {
		return this.name;
	}

	public String getNbsp() {
		return nbsp;
	}

	public void setNbsp(String nbsp) {
		this.nbsp = nbsp;
	}
	

}
