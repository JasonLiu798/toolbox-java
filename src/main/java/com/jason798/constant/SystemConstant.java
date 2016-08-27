package com.jason798.constant;

/**
 * system constant
 * Created by async on 2016/7/1.
 */
public class SystemConstant {
	/**
	 *  Line separator ("\n" on UNIX)
	 */
	public static final String LINE_SEP = System.getProperty("line.separator");
	/**
	 * File separator ("/" on UNIX)
	 */
	public static final String FILE_SEP = System.getProperty("file.separator");
	/**
	 * Path separator (":" on UNIX)
	 */
	public static final String PATH_SEP = System.getProperty("path.separator");

	public static final String OS_NAME = System.getProperty("os.name");
	public static final String OS_ARCH = System.getProperty("os.arch");
	public static final String OS_VERSION = System.getProperty("os.version");

	public static final String TMP_DIR = System.getProperty("java.io.tmpdir");

	public static final String UPPER_PATH = "..";
	public static final String CURRENT_PATH = ".";

	public static final char EXTENSION_SEPARATOR = 46;

}
