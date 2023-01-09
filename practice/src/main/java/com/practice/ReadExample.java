package com.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadExample {
  public static void main(String[] args) {
    try(InputStream is = new FileInputStream("/Users/jieunkim/Documents/test1.db")){
      //데이터 출발지를 test1 으로 하는 입력 스트림 생성
      //try-with-resources 로 try문이 종료 되었을 때 입력 스트림을 닫고 사용 메모리 해제
      while(true){
        int data = is.read(); //int는 4byte! 1 바이트씩 읽기
        if(data == -1)break; //파일 끝에 도달했을 경우
        System.out.println(data);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
