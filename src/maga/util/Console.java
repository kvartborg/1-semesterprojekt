package maga.util;

public class Console {
    public static void print (String ...messages) {
        for (String message : messages) {
            if (message == null) {
                continue; 
            }
            System.out.println(message);
        }
    }
}
