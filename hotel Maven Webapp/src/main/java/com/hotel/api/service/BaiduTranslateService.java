package com.hotel.api.service;

/**
 * 
 * @author yuanhaohe
 *
 */
public interface BaiduTranslateService {
	public static final String CHINESE = "zh";
	public static final String ENGLISH = "en";
	public static final String JAPANESE = "jp";

	/**
	 * 
	 * @param from
	 *            源语言
	 * @return 目标语言
	 */
	public String translateToEnglish(String from);

	/**
	 * 
	 * @param from
	 *            源语言
	 * @return 目标语言
	 */
	public String translateToChinese(String from);

	/**
	 * 
	 * @param from
	 *            源语言
	 * @return 目标语言
	 */
	public String translateToJapanese(String from);

	/**
	 * 
	 * @param from
	 *            源语言
	 * @param toLanguage
	 *            源语言语种
	 * @return 目标语言
	 */
	public String translate(String from, String toLanguage);

}
