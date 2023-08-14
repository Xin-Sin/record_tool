package top.xinsin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import top.xinsin.service.RecordService;

import java.util.Objects;

/**
 * @author xinsin
 * Created On 2023/8/14 15:54
 * @version 1.0
 */
public class LaunchMain extends Application {
    private RecordService recordService = new RecordService();
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main_screen.fxml")));
        primaryStage.setTitle("record_tool");
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
        recordService.findAll().forEach(System.out::println);
    }
}