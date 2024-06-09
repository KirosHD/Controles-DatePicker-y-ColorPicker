package picker;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DatePickerColorPickerApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label dateLabel = new Label("fecha:");
        DatePicker datePicker = new DatePicker();
        
        Label colorLabel = new Label("color:");
        ColorPicker colorPicker = new ColorPicker();
        
        Button confirmButton = new Button("Confirmar");
        Button exitButton = new Button("Exit");
        Text resultText = new Text();

        dateLabel.setFont(new Font("Times New Roman", 14));
        colorLabel.setFont(new Font("Times New Roman", 14));
        resultText.setFont(new Font("Times New Roman", 14));
        
        confirmButton.getStyleClass().add("confirm-button");
        exitButton.getStyleClass().add("exit-button");
        dateLabel.getStyleClass().add("label");
        colorLabel.getStyleClass().add("label");

        VBox vbox = new VBox(15, dateLabel, datePicker, colorLabel, colorPicker, confirmButton, resultText, exitButton);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #f0f0f0;");

        confirmButton.setOnAction(e -> {
            String selectedDate = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "No seleccionada";
            Color selectedColor = colorPicker.getValue();

            System.out.println("Fecha seleccionada: " + selectedDate);
            System.out.println("Color seleccionado: " + selectedColor);

            resultText.setText("Fecha seleccionada: " + selectedDate + "\nColor seleccionado: " + selectedColor.toString());
            resultText.setFill(selectedColor.grayscale().getBrightness() < 0.5 ? Color.WHITE : Color.BLACK);
            vbox.setStyle("-fx-background-color: #" + selectedColor.toString().substring(2, 8) + ";");
        });

        exitButton.setOnAction(e -> {
            primaryStage.close();
        });

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Archivo");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> primaryStage.close());
        fileMenu.getItems().add(exitMenuItem);
        menuBar.getMenus().add(fileMenu);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(vbox);

        Scene scene = new Scene(root, 400, 350);
        scene.getStylesheets().add("style.css");

        primaryStage.setTitle("Interface DatePicker y ColorPicker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}