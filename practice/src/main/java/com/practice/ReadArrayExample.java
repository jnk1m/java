package com.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadArrayExample {
  public static void main(String[] args) {

    try(InputStream is = new FileInputStream("/Users/jieunkim/Documents/test2.db")){
      byte[] data = new byte[100];

      while(true){
        int num = is.read(data); //최대 100 바이트를 읽음
        //InputStream으로 test2 파일을 읽어서 data 바이트 배열에 적재. 리턴 되는 num은 읽은 수를 뜻함.

        if(num == -1) break;
        //파일 끝에 도달했을 경우

        for (int i = 0; i < num; i++) { //읽은 바이트 수를 출력
          System.out.println(data[i]);
        }
      }
    }catch (FileNotFoundException e){
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
