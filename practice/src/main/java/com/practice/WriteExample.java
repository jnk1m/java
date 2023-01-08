package com.practice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteExample {
  public static void main(String[] args) {
    try (OutputStream os = new FileOutputStream("/Users/jieunkim/Documents/test1.db")) {
      //데이터 도착지를 test1.db 파일로 하는 바이트 출력 스트림 생성
      //try-with-resource 사용하여 OutputStream이 정상적으로 닫히고 메모리가 해제될 수 있도록 함

      byte a = 10; //1 바이트씩 출력
      byte b = 20;
      byte c = 30;

      os.write(a);
      os.write(b);
      os.write(c);

      os.flush(); //내부 버퍼에 잔류하는 바이트를 출력하고 버퍼를 비움
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
