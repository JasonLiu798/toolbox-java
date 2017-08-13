package com.atjl.util.file;

import com.atjl.util.constant.SystemConstant;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

/**
 * Created by async on 2016/7/1.
 */
public class FileNioUtil {
	private FileNioUtil(){
		throw new UnsupportedOperationException();
	}
    /**
     ************************ read file apis ************************
     */
    /**
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] readFile2ByteArray(String filename) throws IOException {
        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
                if (fs != null) {
                    fs.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] readBigFile2ByteArray(String filename) throws IOException {
        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(filename, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size()).load();
            //System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (fc != null) {
                    fc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * read file
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static List<String> readFile(String file) throws IOException {
        return readFile(file, StandardCharsets.UTF_8);
    }

    /**
     * read file
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static List<String> readFile(String file, Charset c) throws IOException {
        Path path = Paths.get(file);
        List<String> lines = Files.readAllLines(path, c);//StandardCharsets.UTF_8);
        return lines;
    }

    /**
     * read binary file
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] readBinaryFile(String file) throws IOException {
        Path path = Paths.get(file);
        byte[] res = Files.readAllBytes(path);
        return res;
    }

    /**
     * *********************** write file apis ************************
     */
    public static boolean writeFile(String file, String content) {
        return writeFile(file, content, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
    }

    public static boolean appendLine(String file, String content) {
        return append(file, content + SystemConstant.LINE_SEP);
    }

    public static boolean append(String file, String content) {
        return writeFile(file, content, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
    }

    public static boolean writeFile(String file, String content, Charset c, OpenOption o) {
        boolean res = true;
        if (!FileUtil.fileExist(file)) {
            o = StandardOpenOption.CREATE_NEW;
        }
        Path path = Paths.get(file);
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(path, c, o);
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            res = false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }


    /**
     ************************ complex apis ************************
     */
    /**
     * copy file from in to out
     *
     * @param in
     * @param out
     * @throws IOException
     */
    public static void copy(File in, File out) throws IOException {
        FileChannel inChannel = new FileInputStream(in).getChannel();
        FileChannel outChannel = new FileOutputStream(out).getChannel();
        try {
            //inChannel.transferTo(0, inChannel.size(), outChannel);      // original -- apparently has trouble copying large files on Windows
            // magic number for Windows, 64Mb - 32Kb)
            int maxCount = (64 * 1024 * 1024) - (32 * 1024);
            long size = inChannel.size();
            long position = 0;
            while (position < size) {
                position += inChannel.transferTo(position, maxCount, outChannel);
            }
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }
    }


    /**
     *
     * @param filepath
     *
    public static void readBigFile(String filepath){
    File file = new File(filepath);
    FileInputStream in = null;
    try {
    in = new FileInputStream(file);
    FileChannel channel = in.getChannel();
    MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0,
    channel.size());
    byte[] b = new byte[1024];
    long len = (int) file.length();
    for (long offset = 0; offset < len; offset += 1024) {
    if (len - offset > BUFFER_SIZE) {
    buff.get(b);
    } else {
    long remainSizeLong = len - offset;
    int  remainSizeInt = NumberUtil.long2Int(remainSizeLong);
    buff.get(new byte[remainSizeInt]);
    }
    }
    }catch (FileNotFoundException e) {
    e.printStackTrace();
    } catch (IOException e) {
    e.printStackTrace();
    }finally {
    if (in != null) {
    try {
    in.close();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    }
    }
     */
}
