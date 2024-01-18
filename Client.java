import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		GuiConstructor gui = new GuiConstructor();
		Scene scene = gui.getScene();
		
		stage.setScene(scene);
		stage.show();
	}


}
