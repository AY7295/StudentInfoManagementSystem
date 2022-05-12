package model;

public class util {

    //descp check scores are legal
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


    // descp -1 user not exist , 0 pass wrong , 1 user exist and pass is right 合法
    public static int isRightUser(User[] us, User user) {
        for (var u:us) {
            if (u.id.equals(user.id)) {
                if (u.pass.equals(user.pass)) {
                    return 1;
                }
                return 0;
            }
        }
        return -1;
    }

    //descp get Address
    public static String getAddress(Address a){
        return a.province + " " + a.city + " " + a.street + " " + a.houseNmu;

    }

    public static int totalScore (int[] scores){
        int sum = 0;
        for (var i:scores){
            sum += i;
        }
        return sum;
    }
}

