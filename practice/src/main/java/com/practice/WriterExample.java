package com.practice;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterExample {
  public static void main(String[] args){
    try(Writer writer = new FileWriter("/Users/jieunkim/Documents/test.txt")){

      //한 문자씩 출력
      char a = 'A';
      writer.write(a);
      char b = 'B';
      writer.write(b);

      //char 배열 출력
      char[] arr = {'C','D','E'};
      writer.write(arr);

      //문자열 출력
      writer.write("FGH");
      writer.flush();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
