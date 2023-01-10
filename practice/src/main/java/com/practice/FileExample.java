package com.practice;

import java.io.*;

public class FileExample {
  public static void main(String[] args) throws IOException {
    //File 객체 생성
    File dir = new File("/Users/jieunkim/Documents");
    File file = new File(dir+"/novemberRain.txt");
    File copiedFile = new File(dir+"/novemberRain3.txt");

    if(!dir.exists()){
      dir.mkdirs();
    }
    if(!file.exists()){
      file.createNewFile();
    }

    try(InputStream is = new BufferedInputStream(new FileInputStream(file));
        OutputStream os = new BufferedOutputStream(new FileOutputStream(copiedFile))){
      is.transferTo(os);
      os.flush();

    }catch (IOException e){
      throw new IOException(e);
    }


  }
}
