package com.example.demo.tool;

public class StringMatcher {
    public static void main(String[] args) {
        String a = "在干嘛";
        String b = "你在干嘛";
        System.err.println(match(a,b));
    }

    public static double match(String input, String origin) {
        int accumulation = 0;
        String o = origin;
        for (int i = 0; i < input.length(); i++) {
            String current = input.substring(i, i + 1);
            if (!origin.contains(current)) {
                continue;
            } else {
                int index = o.indexOf(current);
//                System.out.println(index);
                accumulation += index;
                o = o.replace(current, "");
            }
        }
//        System.err.println(accumulation);
        return (double)accumulation/input.length();
    }
}
