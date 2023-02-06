package cz.los;

import java.util.ArrayList;
import java.util.List;

public class SandBox {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("Kek", "lol", "Karvalol"));
        noModEx(list);
        concurentModEx(list);
    }

    private static void noModEx(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("lol")) {
                list.add("ololo");
            }
            System.out.println(list.get(i));
        }
        System.out.println("LOL!! NO CONCURRENT MOD EXCEPTION. I TOLD YOU!!!");
    }

    private static void concurentModEx(List<String> list) {
        try {
            for (String s : list) {
                if (s.equals("lol")) {
                    list.add("ololo");
                }
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println("KEK! HERE WAS CONCURRENT MOD EXCEPTION!");
        }
    }
}
