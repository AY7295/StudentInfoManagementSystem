import model.Models;
import window.loginWindow;

public class StartSystem {
    public static void main(String[] args) {
        loginWindow loginWindow = new loginWindow(new Models());
        loginWindow.displayWindow();

    }
}
