package window;

import model.Models;
import javax.swing.*;

public class studentInfoWindow {
    Models models;

    public studentInfoWindow(Models ms) {
        this.models = ms;
    }

    public void displayWindow() {
        displayFrame dF = new displayFrame(models);
        dF.showFrame();
    }

}

class displayFrame extends JFrame {
    Models models;
    boolean whichDisplay = false; // false for studentI, true for studentII
    public displayFrame(Models ms) {
        this.models = ms;
    }
    public void showFrame() {
        setTitle("Student Info");





    }
}
