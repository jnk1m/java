package com.practice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReaderExample {
  public static void main(String[] args) {
    try {
      Reader reader = null;

      //1 문자씩 읽기
      reader = new FileReader("/Users/jieunkim/Documents/test.txt");

      while(true){
        int data = reader.read();
        if(data == -1) break;
        System.out.println((char)data);
      }
      reader.close();
      System.out.println();

      //문자 배열로 읽기
      reader = new FileReader("/Users/jieunkim/Documents/test.txt");
      char[] data = new char[100];

      while(true){
        int num = reader.read(data);
        if(num == -1) break;
        for (int i = 0; i < num; i++) {
          System.out.println(data[i]);
        }
      }
      reader.close();
      System.out.println();

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
