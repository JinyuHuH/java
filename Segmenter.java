import java.io.*;
import java.util.*;

/**
 * 
 * <p>
 * Title:���ķִ�
 * </p>
 * <p>
 * Description:�����Ľ��зִ�,�������е�����,Ӣ�Ĳ���
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

	public final static int TRAD = 0; // ����

	public final static int SIMP = 1; // ����

	public final static int BOTH = 2; // ����

	public final static int MAXMATCHINGLENTH = 7; // ���ƥ�䳤��

	private static final String WORDFLAG_ORG = "1";// ��־�ֵ���ԭ�еĴ�

	private static final String WORDFLAG_CUT = "2";// ��־���и�ĴʵĲ���

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

		// �õ��ʿ�����·��
		String dataPath = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "data"
				+ System.getProperty("file.separator");

		InputStream worddata = null; // ���Ĵʿ��ļ�

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
	 * װ�شʿ�,�ڷִ�ǰֻ������һ��
	 * 
	 * @param encodingFlag��������
	 *            "-b" Big5, "-g" GB2312, "-8" UTF-8, "-s" SIMP, "-t" TRAD
	 */
	// Charform is TRAD, SIMP or BOTH
	public static void loadWordLibs(String encodingFlag) {

		int charform = Segmenter.SIMP; // Ĭ���Ǽ�������

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

		csurname = new HashSet(); // ������
		cforeign = new HashSet(); // ���������õĺ���
		cnumbers = new HashSet(); // ��������
		cnotname = new HashSet(); // ��������������
		cstopwords = new HashSet(); // ͣ�ô�

		// �õ��ʿ�����·��
		String path = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "data"
				+ System.getProperty("file.separator");

		// ����ʿ�
		if (charform == SIMP) {
			loadSet(cnumbers, path + "snumbers_u8.txt", "UTF-8"); // �����ֿ�,�ļ�����UTF-8��ʽ����
			loadSet(cforeign, path + "sforeign_u8.txt", "UTF-8");
			loadSet(csurname, path + "ssurname_u8.txt", "UTF-8");
			loadSet(cnotname, path + "snotname_u8.txt", "UTF-8");
			//loadSet(cstopwords, path + "stopwords_u8.txt", "UTF-8");
			// ��stopwords_gbk.txt����,����GBK����,������GB2312,����GB2312�ı��뷶ΧС��GBK,��"��",GB2312���ܽ���,��GBK����
			// loadset(cstopwords, path + "stopwords_gbk.txt", "GBK");
			// //����ͣ�ô�,�ļ�����GBK����
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

		wordsLib = new HashMap(); // ���Ĵʿ�

		loadEntriesFromFile(wordsLib, "�ѹ����뷨�ʿ�.txt");

		System.out.println("Total keys: " + wordsLib.size());
	}

	/**
	 * ����һ���ʵ�
	 * 
	 * @param targetset
	 *            Ŀ��
	 * @param sourcefile
	 *            �ʵ��ļ�
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
	 * �ж��Ƿ�������
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
	 * �ж��Ƿ���Ӣ�ĺ��֣���"����˹"
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
	 * ʵ�ֶ�cline�е����Ľ��зִ�,���ɵĽ����separator�ָ�(ע��:����ȥͣ�ô�)
	 * 
	 * @param cLine
	 * @param separator
	 * @return
	 */
	public static String segmentString(String cLine, String separator) {
		StringBuffer currentWord = new StringBuffer(); // ��ǰ�����ɵĴ�
		StringBuffer outLine = new StringBuffer(); // ����ʵ����
		int i, cLength;
		char currentChar;
		// separator = " ";

		cLength = cLine.length();

		for (i = 0; i < cLength; i++) {
			currentChar = cLine.charAt(i); // ȡ��iλ�õ��ַ�

			if (Character.UnicodeBlock.of(currentChar) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) { // ����������ַ�
				// Character in CJK block
				if (currentWord.length() == 0) { // ��ǰ��currentword��û���κ��ַ�,start
					// looking for next word
					// System.err.println("current word length 0");
					if (i > 0
							&& (Character.isWhitespace(cLine.charAt(i - 1)) == false)) { // ��ֹ������separator
						outLine.append(separator);
					}
					currentWord.append(currentChar); // ��ǰ�ַ����ǿո�
				} else { // ��ǰ��currentword���������ַ�
					String currentWordWithCurrentChar = new String(currentWord
							.toString()
							+ currentChar).intern();

					boolean currentWordWithCurrentCharContainedInLib = wordsLib
							.containsKey(currentWordWithCurrentChar);

					if (currentWordWithCurrentCharContainedInLib == true
							&& ((String) (wordsLib
									.get(currentWordWithCurrentChar)))
									.equals(WORDFLAG_ORG) == true) { // ���"currentword+cucrrentchar"�ڴʿ��д���
						// word is in lexicon
						currentWord.append(currentChar); // "currentword+cucrrentchar"����´�

					} else if (isAllForeign(currentWord.toString())
							&& // ��ǰ��currentwordȫ��Ӣ�ĺ���
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
									.intern()) // currentword��������currentchar������
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
						// ȥ����ͣ�ô�
						if (!cstopwords.contains(currentWord.toString())) { // �������ͣ�ô�
							outLine.append(currentWord.toString()); // �������

							if (Character.isWhitespace(currentChar) == false) {
								outLine.append(separator);
							}
						}

						currentWord.setLength(0);
						currentWord.append(currentChar);
					}
				}

			} else { // Not chinese character
				if (currentWord.length() > 0) { // �Ȱ��Ѿ���õ�currentword���
					// ȥ����ͣ�ô�
					if (!cstopwords.contains(currentWord.toString())) { // �������ͣ�ô�
						outLine.append(currentWord.toString()); // �������

						if (Character.isWhitespace(currentChar) == false) {
							outLine.append(separator);
						}
					}
					currentWord.setLength(0);
				}
				if (Character.isLetter(currentChar)) {
					outLine.append(currentChar); // ����,Ӣ���ַ�,ֱ�����
				}
				else outLine.append(separator);
			}
		}

		outLine.append(currentWord.toString());

		return outLine.toString().replaceAll("\\s+", " ").trim(); // ������ո��滻Ϊһ��
	}
	public static void main(String[] args) {

		//Segmenter segmenter = new Segmenter();

		// ������뷽ʽ���
		String encodingFlag = "-g";

		// ����ʿ�,������ʱ��
		System.out.println("Loading segmenter word list.  One moment please.");
		long timeStart = System.currentTimeMillis();
		Segmenter.loadWordLibs(encodingFlag);
		long timeEnd = System.currentTimeMillis();
		System.out.println("words loading time: " + (timeEnd - timeStart)
				+ "ms");

		System.out.println("���ƥ�䳤�ȣ�" + Segmenter.MAXMATCHINGLENTH);

		// segments a single line
		String testSentence = "��ѯ����Ϊ��˵����";
		String result = Segmenter.segmentString(testSentence, " ");
		System.out.println(testSentence);
		System.out.println(result);


	}

}
