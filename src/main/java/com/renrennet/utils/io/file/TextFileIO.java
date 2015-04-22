package com.renrennet.utils.io.file;

import java.io.*;

/**
 * Created by leiguorui on 12/23/14.
 *
 * text 文件读写
 */
public class TextFileIO {
    /**
     * 读文件
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String getTxt(String filePath) throws IOException {
        String everything;
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        }  finally {
            br.close();
        }
        return everything;
    }

    /**
     * 写文件
     * @param data
     * @param filePath
     */
    public static void writeToFile(String data, String filePath){
        try {

            String content = data;

            File file = new File(filePath);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String everything = getTxt("/home/leiguorui/Downloads/cityinfo");
        writeToFile(everything, "/home/leiguorui/Downloads/citysql");
    }
}
