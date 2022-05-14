import interfaces.loginWindow;
import model.Models;

public class StudentInfoSystem {
    public static void main(String[] args) {
        new loginWindow(new Models()).displayWindow();
    }
}
