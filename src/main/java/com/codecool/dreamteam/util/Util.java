package com.codecool.dreamteam.util;

import java.util.Random;

public class Util {

    public static int randomSkill() {
        Random rand = new Random();
        // min 1 ,max 100
        return rand.nextInt(101)+1;
    }

    public static int randomNumMax(int num){
        Random rand = new Random();
        // min 0
        return rand.nextInt(num);
    }
}
