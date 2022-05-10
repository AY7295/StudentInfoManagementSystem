import model.Models;
import login.loginWindow;

public class StartSystem {
    public static void main(String[] args) {
        new loginWindow(new Models()).displayWindow();
    }
}
