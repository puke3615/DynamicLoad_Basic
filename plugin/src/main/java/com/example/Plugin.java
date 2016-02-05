package com.example;

public class Plugin implements IPlugin {

    @Override
    public int compute(int min, int max) {
        if (min == max) {
            return min;
        } else if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        int result = 0;
        for (int i = min; i <= max; i++) {
            result += i;
        }
        return result;
    }

}