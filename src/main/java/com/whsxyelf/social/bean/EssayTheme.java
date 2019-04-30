package com.whsxyelf.social.bean;

public class EssayTheme {
	private int essayThemeId;
	private String essayThemeContent;
	public int getEssayThemeId() {
		return essayThemeId;
	}
	public void setEssayThemeId(int essayThemeId) {
		this.essayThemeId = essayThemeId;
	}
	public String getEssayThemeContent() {
		return essayThemeContent;
	}
	public void setEssayThemeContent(String essayThemeContent) {
		this.essayThemeContent = essayThemeContent;
	}
	@Override
	public String toString() {
		return "EssayTheme [essayThemeId=" + essayThemeId + ", essayThemeContent=" + essayThemeContent + "]";
	}
	
	
	
}
