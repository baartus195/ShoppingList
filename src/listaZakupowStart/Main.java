package listaZakupowStart;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 


public class Main extends Application {
 
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = (Parent) FXMLLoader.load(getClass().getResource(
                "/listaZakupow/view/MainWindow.fxml"));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Stwórz listê zakupów");
        stage.show();

    }
     
    public static void main(String[] args) {
        launch(args);
    }
}