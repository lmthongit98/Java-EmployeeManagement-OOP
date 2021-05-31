package oop.quanlynhansu.controller.util;

public class UtilQLNS {
    public static String tachTen(String name){
        char[] nameArr = name.trim().toCharArray();
        for (int i = nameArr.length -1; i >=0 ; i--) {
            if(nameArr[i] == ' ')
                return name.trim().substring(i+1);
        }

        return name.trim().substring(0);
    }
}
