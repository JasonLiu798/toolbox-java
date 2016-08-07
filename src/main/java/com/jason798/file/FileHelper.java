package com.jason798.file;

import com.jason798.character.StringHelper;
import com.jason798.constant.SystemConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * file helper
 *
 */
public final class FileHelper {

    private static Logger log = LoggerFactory.getLogger(FileHelper.class);
    public static final String DFT_FILE = "/opt/logs/test.dat";

    /**
     * file exist
     * @param filepath
     * @return
     */
    public static boolean fileExist(String filepath){
        File file=new File(filepath);
        return file.exists();
    }

	public static String className2FilePath(String className){
		if(StringHelper.isEmpty(className)){
			return "";
		}
		// className.replaceAll(StringHelper.DOT_SEP_NO_REX,SystemConstant.FILE_SEP);
		String[] arr = className.split(StringHelper.DOT_SEP_NO_REX);
		//System.out.println("arr size "+arr.length+","+className);
		if(arr.length==1){
			return className;
		}
		int lenMinus1= arr.length-1;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<arr.length;i++){
			sb.append(arr[i]);
			if(i!=lenMinus1){
				sb.append(SystemConstant.FILE_SEP);
			}
		}
		return sb.toString();
	}

	/**
	 * file size
	 * @param filepath
	 * @return
	 */
	public static long getFileSize(String filepath){
		long res = -1;
		FileChannel fc = null;
		File f = new File(filepath);
		if(f.exists() && f.isFile()){
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
				fc = fis.getChannel();
				res = fc.size();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(null!=fc){
					try{
						fc.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return res;
	}

    /**
     * 文件拷贝
     *
     * @param srcFile  {@link File} 待拷贝的文件
     * @param destPath {@link String} 目的地
     * @return
     * @throws Exception
     */
    public static boolean copy(File srcFile, String destPath) throws Exception {
        File dir = new File(destPath);
        return srcFile.renameTo(new File(dir.getPath(), dir.getName()));

    }


	/**
	 ************************ read  file apis ************************
	 */

    /**
     * read file content to List<String>
     *
     * @param path
     * @return
     */
    public static List<String> readFile2StringList(String path) {
        List<String> res = new ArrayList<>();
        String line;
        try {
            BufferedReader in = new BufferedReader(new FileReader(path));
            line = in.readLine();
            while (line != null) {
                if (line.indexOf("--") != 0) {
                    res.add(line);
                }
                line = in.readLine();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

	/**
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFile2ByteArray(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		fi.close();
		return buffer;
	}

	public static byte[] readFile2ByteArray() throws IOException {
		return readFile2ByteArray(DFT_FILE);
	}

	/**
	 * only read int.max_value byte
	 * @param filepath
	 * @return
	 */
	public static byte[] readFile2ByteArr2(String filepath){
		int size = (int) getFileSize(filepath);
		log.debug("file size {}",size);
		byte[] res = new byte[size];
		try {
			DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filepath)));
//            FileInputStream in = new FileInputStream(filepath);
			for(int i=0;i<size;i++) {
				res[i] = in.readByte();
			}
//            while ((c=in.read())!=-1) {
//                byte tmpByte = in.readByte();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static List<String> readFilesByLine2StringList(String filepath) throws IOException {
		File file = new File(filepath);
		BufferedReader reader = null;
		List<String> res = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				res.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(reader!=null)
				reader.close();
		}
		return res;
	}

	public static String readFilesByLine2String(String filepath) throws IOException {
		File file = new File(filepath);
		BufferedReader reader = null;
		StringBuilder res = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
//				String codeced = new String(tempString.getBytes("utf8"));
				res.append(tempString).append(SystemConstant.LINE_SEP);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(reader!=null)
				reader.close();
		}
		return res.toString();
	}


	/**
	 ************************  write file apis ************************
	 */

	/**
	 * write file
	 * @param content
	 */
    public static void write2File(byte[] content){
        write2File(DFT_FILE,content);
    }

    public static void write2File(String filepath,byte[] content){
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(filepath));
            for(int i=0;i<content.length;i++){
                out.writeByte(content[i]);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("write to file {} finish,bin mode", filepath);
        return;
    }


    public static void write2File(List<String> content){
        write2File("/opt/logs/perf/fav.log", content);
    }
    public static void write2File(String content){
        List<String> list = new LinkedList<>();
        list.add(content);
        write2File(list);
    }

    public static void write2File(String filepath,String content){
        List<String> list = new LinkedList<>();
        list.add(content);
        write2File(filepath, list);
    }
    /**
     * write contents to file
     *
     * @param filepath file path
     * @param contents content
     */
    public static void write2File(String filepath, List<String> contents) {
        FileWriter writer = null;
        try {
            if(fileExist(filepath)){
                writer = new FileWriter(filepath,true);
            }else {
                writer = new FileWriter(filepath,false);//if file exist ,it will delete the file then create
            }
            for (String str : contents) {
                writer.write(str + "\n");
            }
            log.debug("write to file,file {},content {}", filepath, contents);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }




    /**
     * read file use PathMatchingResourcePatternResolver
     *
     * @return
     * @throws IOException
     *
    public static List<String> getFilesStr(String filepath) throws IOException {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = patternResolver.getResources(filepath);
        List<String> res = new ArrayList<>();
        if (resources == null) {
            log.error("load file {} fail,file null or not exist", filepath);
            return res;
        }
        for (int i = 0; i < resources.length; i++) {
            log.debug("get resource: {}", resources[i].getFilename());
            InputStreamReader inputStreamReader = new InputStreamReader(resources[i].getInputStream());
            BufferedReader breader = new BufferedReader(inputStreamReader);
            String temp = breader.readLine();
            String xmlStr = "";
            while (temp != null) {
                xmlStr += temp + "\n";
                temp = breader.readLine();
            }
            res.add(xmlStr);
        }
        return res;
    }*/
	/*
    public static String getFileStr(String filepath) throws IOException {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = patternResolver.getResource(filepath);
        String res = "";
        if (resource == null) {
            log.error("load file {} fail,file null or not exist", filepath);
            return res;
        }
        log.debug("get resource: {}", resource.getFilename());
        InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
        BufferedReader breader = new BufferedReader(inputStreamReader);
        String temp = breader.readLine();
        String xmlStr = "";
        while (temp != null) {
            xmlStr += temp + "\n";
            temp = breader.readLine();
        }
        res = xmlStr;
        return res;
    }*/


}
