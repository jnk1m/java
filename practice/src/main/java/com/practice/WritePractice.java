package com.practice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WritePractice {
  public static void main(String[] args) {
    try (OutputStream os = new FileOutputStream("/Users/jieunkim/Documents/test2.db")) {
      byte[] array = {10, 20, 30};
      os.write(array); //배열의 모든 바이트를 출력

      /*배열의 일부분을 출력하고 싶다면 write(byte[] b, int off, int len)메소드를 사용한다.
      * 이 메소드는 b[off]부터 len개의 바이트를 출력한다.*/

      byte[] array2 = {10,20,30,40,50};
      os.write(array2,1,3);

      os.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
