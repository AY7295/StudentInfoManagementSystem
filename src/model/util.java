package model;

public class util {
    public static boolean isScoresRight(int[] scores,int num) {
        var bool1 = scores.length == num;
        var bool2 = true;
        for (var i = 0; i < num; i++) {
            if (scores[i] < 0 || scores[i] > 100) {
                bool2 = false;
                break;
            }
        }
        return bool1 && bool2;
    }
}

