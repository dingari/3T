package main.util;

import java.util.List;

public class Util {

    public static int randomIndex(List<?> list) {
        return (int) (Math.random()*list.size());
    }
}
