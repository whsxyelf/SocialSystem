package com.whsxyelf.social.bean;

public class EssayTheme {
	private int eassayThemeNo;
	private String essayThemeContent;
	public EssayTheme() {
		
	}
	
	public EssayTheme(int eassayThemeNo, String essayThemeContent) {
		super();
		this.eassayThemeNo = eassayThemeNo;
		this.essayThemeContent = essayThemeContent;
	}

	public int getEassayThemeNo() {
		return eassayThemeNo;
	}

	public void setEassayThemeNo(int eassayThemeNo) {
		this.eassayThemeNo = eassayThemeNo;
	}

	public String getEssayThemeContent() {
		return essayThemeContent;
	}

	public void setEssayThemeContent(String essayThemeContent) {
		this.essayThemeContent = essayThemeContent;
	}

	@Override
	public String toString() {
		return "EssayTheme [eassayThemeNo=" + eassayThemeNo + ", essayThemeContent=" + essayThemeContent + "]";
	}
	
}
