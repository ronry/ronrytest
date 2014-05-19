package com.ronrytest.regx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupRegxTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String reg = "(A(B)C)D";
        String input = "ABCD";
        Pattern patter = Pattern.compile(reg);

        Matcher result = patter.matcher(input);
        System.out.println(result.find());
        System.out.println(result.group());

    }

}
