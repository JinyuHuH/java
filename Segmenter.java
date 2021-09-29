import java.io.*;
import java.util.*;

/**
 * 
 * <p>
 * Title:中文分词
 * </p>
 * <p>
 * Description:对中文进行分词,保留其中的数字,英文不变
 * </p>
 * <p>
 * Copyright: IRLab.SDU (c) 2007
 * </p>
 * <p>
 * Company: IRLab.SDU
 * </p>
 * 
 * @author: CZM/ODN
 * @version 1.0
 */

public class Segmenter {

	private static HashMap wordsLib;

	private static HashSet csurname, cforeign, cnumbers, cnotname, cstopwords;

	public final static int TRAD = 0; // 繁体

	public final static int SIMP = 1; // 简体

	public final static int BOTH = 2; // 简繁体

	public final static int MAXMATCHINGLENTH = 7; // 最大匹配长度

	private static final String WORDFLAG_ORG = "1";// 标志字典中原有的词

	private static final String WORDFLAG_CUT = "2";// 标志被切割的词的部分

	static {
		loadWordLibs("-g");
	}

	private static void addNewWord(HashMap wordslib, String word, String flag) {
		if (word.indexOf("#") == -1 && word.length() <= MAXMATCHINGLENTH) {
			if (wordsLib.containsKey(word.intern()) == false)
				wordslib.put(word.intern(), flag);
			if (word.length() > 2) {
				flag = WORDFLAG_CUT;
				addNewWord(wordslib, word.substring(0, word.length() - 1)
						.intern(), flag);
			}
		}
	}

	private static void loadEntriesFromFile(HashMap wordslib, String filePath) {

		// 得到词库的相对路径
		String dataPath = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "data"
				+ System.getProperty("file.separator");

		InputStream worddata = null; // 中文词库文件

		try {
			worddata = new FileInputStream(dataPath + filePath);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					worddata, "UTF8"));
			String newword = "";

			while ((newword = in.readLine()) != null) {
				addNewWord(wordslib, newword, WORDFLAG_ORG);
			}

			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 装载词库,在分词前只需载入一次
	 * 
	 * @param encodingFlag：编码标记
	 *            "-b" Big5, "-g" GB2312, "-8" UTF-8, "-s" SIMP, "-t" TRAD
	 */
	// Charform is TRAD, SIMP or BOTH
	public static void loadWordLibs(String encodingFlag) {

		int charform = Segmenter.SIMP; // 默认是简体中文

		if (encodingFlag.equals("-b")) { // Setting to Big5, TRAD
			charform = Segmenter.TRAD;
		} else if (encodingFlag.equals("-g")) { // Setting to GB, SIMP
			charform = Segmenter.SIMP;
		} else if (encodingFlag.equals("-8")) { // UTF-8
			charform = Segmenter.BOTH;
		} else if (encodingFlag.equals("-s")) { // SIMP
			charform = Segmenter.SIMP;
		} else if (encodingFlag.equals("-t")) { // TRAD
			charform = Segmenter.TRAD;
		}

		csurname = new HashSet(); // 中文姓
		cforeign = new HashSet(); // 外文名所用的汉字
		cnumbers = new HashSet(); // 汉字数字
		cnotname = new HashSet(); // 不可能用做名字
		cstopwords = new HashSet(); // 停用词

		// 得到词库的相对路径
		String path = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "data"
				+ System.getProperty("file.separator");

		// 载入词库
		if (charform == SIMP) {
			loadSet(cnumbers, path + "snumbers_u8.txt", "UTF-8"); // 简体字库,文件是以UTF-8格式编码
			loadSet(cforeign, path + "sforeign_u8.txt", "UTF-8");
			loadSet(csurname, path + "ssurname_u8.txt", "UTF-8");
			loadSet(cnotname, path + "snotname_u8.txt", "UTF-8");
			//loadSet(cstopwords, path + "stopwords_u8.txt", "UTF-8");
			// 对stopwords_gbk.txt而言,是用GBK编码,不能用GB2312,好像GB2312的编码范围小于GBK,如"·",GB2312不能解析,而GBK可以
			// loadset(cstopwords, path + "stopwords_gbk.txt", "GBK");
			// //载入停用词,文件是以GBK编码
		} else if (charform == TRAD) {
			loadSet(cnumbers, path + "tnumbers_u8.txt", "UTF-8");
			loadSet(cforeign, path + "tforeign_u8.txt", "UTF-8");
			loadSet(csurname, path + "tsurname_u8.txt", "UTF-8");
			loadSet(cnotname, path + "tnotname_u8.txt", "UTF-8");
			//loadSet(cstopwords, path + "stopwords_u8.txt", "UTF-8");
		} else { // BOTH
			loadSet(cnumbers, path + "snumbers_u8.txt", "UTF-8");
			loadSet(cforeign, path + "sforeign_u8.txt", "UTF-8");
			loadSet(csurname, path + "ssurname_u8.txt", "UTF-8");
			loadSet(cnotname, path + "snotname_u8.txt", "UTF-8");
			loadSet(cnumbers, path + "tnumbers_u8.txt", "UTF-8");
			loadSet(cforeign, path + "tforeign_u8.txt", "UTF-8");
			loadSet(csurname, path + "tsurname_u8.txt", "UTF-8");
			loadSet(cnotname, path + "tnotname_u8.txt", "UTF-8");
			//loadSet(cstopwords, path + "stopwords_u8.txt", "UTF-8");
		}

		wordsLib = new HashMap(); // 中文词库

		loadEntriesFromFile(wordsLib, "搜狗输入法词库.txt");

		System.out.println("Total keys: " + wordsLib.size());
	}

	/**
	 * 载入一个词典
	 * 
	 * @param targetset
	 *            目标
	 * @param sourcefile
	 *            词典文件
	 */
	/** Load a set of character data */
	private static void loadSet(HashSet targetset, String sourcefile,
			String charsetName) {
		String dataline = null;
		try {
			// InputStream setdata = getClass().getResourceAsStream(sourcefile);
			InputStream setdata = new FileInputStream(sourcefile);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					setdata, charsetName));
			while ((dataline = in.readLine()) != null) {
				if ((dataline.indexOf("#") > -1) || (dataline.length() == 0)) {
					continue;
				}
				targetset.add(dataline.intern());
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Exception loading data file: " + sourcefile + " "
					+ e);
		}
	}

	/**
	 * 判断是否是数字
	 * 
	 * @param testword
	 * @return
	 */
	private static boolean isAllNumber(String testword) {
		boolean result = true;
		for (int i = 0; i < testword.length(); i++) {
			if (cnumbers.contains(testword.substring(i, i + 1).intern()) == false) {
				result = false;
				break;
			}
		}

		return result;
	}

	private static boolean isStartWithChineseSurname(String testword) {
		if (testword.length() == 1)
			return csurname.contains(testword.substring(0, 1).intern());

		else
			return csurname.contains(testword.substring(0, 1).intern())
					|| csurname.contains(testword.substring(0, 2).intern());

	}

	/**
	 * 判断是否是英文汉字，如"托马斯"
	 * 
	 * @param testword
	 * @return
	 */
	private static boolean isAllForeign(String testword) {
		boolean result = true;
		for (int i = 0; i < testword.length(); i++) {
			if (cforeign.contains(testword.substring(i, i + 1).intern()) == false) {
				result = false;
				break;
			}
		}

		return result;
	}

	/**
	 * 实现对cline中的中文进行分词,生成的结果以separator分割(注意:这里去停用词)
	 * 
	 * @param cLine
	 * @param separator
	 * @return
	 */
	public static String segmentString(String cLine, String separator) {
		StringBuffer currentWord = new StringBuffer(); // 当前新生成的词
		StringBuffer outLine = new StringBuffer(); // 分完词的输出
		int i, cLength;
		char currentChar;
		// separator = " ";

		cLength = cLine.length();

		for (i = 0; i < cLength; i++) {
			currentChar = cLine.charAt(i); // 取出i位置的字符

			if (Character.UnicodeBlock.of(currentChar) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) { // 如果是中文字符
				// Character in CJK block
				if (currentWord.length() == 0) { // 当前词currentword中没有任何字符,start
					// looking for next word
					// System.err.println("current word length 0");
					if (i > 0
							&& (Character.isWhitespace(cLine.charAt(i - 1)) == false)) { // 防止加入多个separator
						outLine.append(separator);
					}
					currentWord.append(currentChar); // 当前字符不是空格
				} else { // 当前词currentword中已有了字符
					String currentWordWithCurrentChar = new String(currentWord
							.toString()
							+ currentChar).intern();

					boolean currentWordWithCurrentCharContainedInLib = wordsLib
							.containsKey(currentWordWithCurrentChar);

					if (currentWordWithCurrentCharContainedInLib == true
							&& ((String) (wordsLib
									.get(currentWordWithCurrentChar)))
									.equals(WORDFLAG_ORG) == true) { // 如果"currentword+cucrrentchar"在词库中存在
						// word is in lexicon
						currentWord.append(currentChar); // "currentword+cucrrentchar"组成新词

					} else if (isAllForeign(currentWord.toString())
							&& // 当前词currentword全是英文汉字
							cforeign.contains(String.valueOf(currentChar)
									.intern())
							&& i + 1 < cLength
							&& (wordsLib.containsKey(cLine.substring(i, i + 2)
									.intern()) == false)) { //
						// Possible a transliteration of a foreign name
						currentWord.append(currentChar);

					}

					else if (isAllNumber(currentWord.toString())
							&& cnumbers.contains(String.valueOf(currentChar)
									.intern()) // currentword是数字且currentchar是数字
					)
						currentWord.append(currentChar);

					else if (isStartWithChineseSurname(currentWord.toString())
							// && wordsLib.containsKey(currentWord)==false
							&& i + 1 < cLength
							&& (wordsLib.containsKey(cLine.substring(i, i + 2)
									.intern()) == false)
							&& currentWord.toString().length() < 3
							&& cnotname.contains(String.valueOf(currentChar)) == false
							&& cstopwords.contains(String.valueOf(currentChar)) == false)
						currentWord.append(currentChar);

					else if ((currentWordWithCurrentCharContainedInLib)
							&& (((String) (wordsLib
									.get(currentWordWithCurrentChar)))
									.equals(WORDFLAG_CUT) == true)
							&& i + 1 < cLength
							&& (wordsLib.containsKey(new String(currentWord
									.toString()
									+ currentChar + cLine.charAt(i + 1))
									.intern()) == true)) {

						// Starts a word in the lexicon
						currentWord.append(currentChar);

					}

					else { // Start a new
						// 去中文停用词
						if (!cstopwords.contains(currentWord.toString())) { // 如果不是停用词
							outLine.append(currentWord.toString()); // 中文输出

							if (Character.isWhitespace(currentChar) == false) {
								outLine.append(separator);
							}
						}

						currentWord.setLength(0);
						currentWord.append(currentChar);
					}
				}

			} else { // Not chinese character
				if (currentWord.length() > 0) { // 先把已经获得的currentword输出
					// 去中文停用词
					if (!cstopwords.contains(currentWord.toString())) { // 如果不是停用词
						outLine.append(currentWord.toString()); // 中文输出

						if (Character.isWhitespace(currentChar) == false) {
							outLine.append(separator);
						}
					}
					currentWord.setLength(0);
				}
				if (Character.isLetter(currentChar)) {
					outLine.append(currentChar); // 数字,英文字符,直接输出
				}
				else outLine.append(separator);
			}
		}

		outLine.append(currentWord.toString());

		return outLine.toString().replaceAll("\\s+", " ").trim(); // 将多个空格替换为一个
	}
	public static void main(String[] args) {

		//Segmenter segmenter = new Segmenter();

		// 输入编码方式标记
		String encodingFlag = "-g";

		// 载入词库,并计算时间
		System.out.println("Loading segmenter word list.  One moment please.");
		long timeStart = System.currentTimeMillis();
		Segmenter.loadWordLibs(encodingFlag);
		long timeEnd = System.currentTimeMillis();
		System.out.println("words loading time: " + (timeEnd - timeStart)
				+ "ms");

		System.out.println("最大匹配长度：" + Segmenter.MAXMATCHINGLENTH);

		// segments a single line
		String testSentence = "查询书名为白说的书";
		String result = Segmenter.segmentString(testSentence, " ");
		System.out.println(testSentence);
		System.out.println(result);


	}

}
