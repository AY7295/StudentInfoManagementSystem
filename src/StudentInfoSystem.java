import data.selectI;
import model.Models;
import interfaces.loginWindow;

public class StudentInfoSystem {
    public static void main(String[] args) {
        new loginWindow(new Models()).displayWindow();
    }
}
