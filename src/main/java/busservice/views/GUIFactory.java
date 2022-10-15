package busservice.views;

public class GUIFactory {
    private static GUIFactory instance;
    private GUI gui;

    private GUIFactory() {
    }

    public static GUIFactory getInstance() {
        if (instance == null) {
            instance = new GUIFactory();
        }
        return instance;
    }

    private GUI createGUI() {
        gui = GUIBuilder.buildGUI();
        return gui;
    }

    private GUI setStage(GUI.Stage stage) {

    }

    public GUI createGUI(GUI.Stage stage, Object data) {
        return new GUIBuilder().setStage(stage).setData(data).buildGUI();
    }

    public GUI createGUI(GUI.Stage stage, Object data, Object data2) {
        return new GUIBuilder().setStage(stage).setData(data).setData(data2).buildGUI();
    }
}
