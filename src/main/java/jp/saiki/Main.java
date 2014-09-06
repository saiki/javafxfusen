package jp.saiki;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main extends Application {

    private static final Tray tray = new Tray();



    static {

    }

    @Override
    public void start(final Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
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
                // TODO 追加処理
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
