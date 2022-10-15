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
//        gui = GUIBuilder.buildGUI();
        return gui;
    }

/*    private GUI setStage(GUI.Stage stage) {

    }*/
}
