package maga.util;

public class Console {
    public static void print (String ...messages) {
        for (String message : messages) {
            System.out.println(message);
        }
    }
}
