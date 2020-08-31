package com.mmall.util;

import java.util.ArrayList;
import java.util.Random;

/** 生成四位验证码工具类
 * 随机生成：0-9 a-z A-Z
 * 提示：a-z范围65-90
 * A-Z范围97-122
 * random.nextInt();不带参数，随机生成一个整数;带参数，生成数是参数范围内的整数。
 */
public class RandomUtil {
    public static void main(String[] args) {
        ArrayList<Object> array = new ArrayList<Object>();
        Random random = new Random();
       // int test = random.nextInt(3);
      for(int i = 0;i<4;i++){
          int choice = random.nextInt(3);
          switch (choice){
              case 0:
                  int num = random.nextInt(9);
                  array.add(num);
                  break;
              case 1:
                  int num2 = random.nextInt(25)+65;
                  char low = (char)num2;
                  array.add(low);
                  break;
              case 2:
                  int num3 = random.nextInt(25)+97;
                  char high = (char)num3;
                  array.add(high);
                  break;
              default:
                  break;
          }
      }
        System.out.println("得到四位验证码");
        for(Object object : array){
            System.out.print(object);
        }
    }
}
