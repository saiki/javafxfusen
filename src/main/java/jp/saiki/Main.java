package jp.saiki;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {

    private static final Tray tray = new Tray();

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public void start(final Stage primaryStage) throws Exception{
    }

    public static void main(String[] args) {
        try {
            Main.tray.init();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(9);
        } catch (AWTException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(9);
        }
        launch(args);
    }

    public static class Tray {

        private TrayIcon icon;

        public TrayIcon getIcon() {
            return icon;
        }

        public Tray() {
        }

        public void init() throws IOException, AWTException {
            Image image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("post_it.png"));
            this.icon = new TrayIcon(image);
            this.icon.addActionListener((ActionEvent e) -> {
                icon.displayMessage("クリック", "クリックされました。", TrayIcon.MessageType.INFO);
            });
            PopupMenu menu = new PopupMenu();
            MenuItem addMenu = new MenuItem("追加");
            addMenu.addActionListener((ActionEvent e) -> {
                Platform.runLater(() -> {
                    final Stage stage = new Stage();
                    Group rootGroup = new Group();
                    Scene scene = new Scene(rootGroup, 200, 200, Color.WHITESMOKE);
                    stage.setScene(scene);

                    AnchorPane rootPane = new AnchorPane();
                    VBox vbox = new VBox();
                    rootPane.getChildren().add(vbox);

                    AnchorPane labelAnchor = new AnchorPane();
                    Label label = new Label(sdf.format(new Date()));
                    labelAnchor.getChildren().add(label);
                    vbox.getChildren().add(labelAnchor);

                    AnchorPane textAnchor = new AnchorPane();
                    TextArea textArea = new TextArea();
                    textAnchor.getChildren().add(textArea);
                    vbox.getChildren().add(textAnchor);

                    stage.show();

                    rootGroup.getChildren().add(rootPane);
                });
            });
            menu.add(addMenu);
            MenuItem exitMenu = new MenuItem("終了");
            exitMenu.addActionListener((ActionEvent e) -> {
               System.exit(0);
            });
            menu.add(exitMenu);
            icon.setPopupMenu(menu);
            SystemTray.getSystemTray().add(icon);
        }
    }
}
