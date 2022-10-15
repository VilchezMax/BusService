package busservice.views;

import java.util.List;

public class GUIBuilder<T> {

    private GUI gui;

    public GUI buildGUI() {
        gui = new GUI();
        return gui;
    }

    public GUI setStage(GUI.Stage stage) {
        gui.setStage(stage);
        return gui;
    }

    public GUI setData(List<T> data) {
        gui.setData(data);
        return gui;
    }

}
