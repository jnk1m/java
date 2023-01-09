package com.practice;

import java.io.*;

public class CopyExample {
  public static void main(String[] args) {
    String originalFileName = "/Users/jieunkim/Documents/test.jpg";
    String targetFileName = "/Users/jieunkim/Documents/test3.jpg";

    try (InputStream is = new FileInputStream(originalFileName);
         OutputStream os = new FileOutputStream(targetFileName)) {
/*
      byte[] data = new byte[100];

      while (true) {
        int num = is.read(data);
        if (num == -1) break;
        os.write(data, 0, num);
      }
*/
      is.transferTo(os);
      os.flush();
      System.out.println("복사 완료");

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
