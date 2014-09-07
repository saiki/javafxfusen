package jp.saiki;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

import javax.imageio.*;
import javax.swing.JOptionPane;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.*;
import java.text.*;
import java.util.*;

public class Main extends Application {

    private static final Tray tray = new Tray();

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private static final FXMLLoader LOADER = new FXMLLoader(Main.class.getResource("Fusen.fxml"));

    @Override
    public void start(final Stage primaryStage) throws Exception{
    }

    public static void main(String[] args) {
        try {
            Main.tray.init();
        } catch (IOException | AWTException e) {
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
            this.icon.addActionListener((java.awt.event.ActionEvent e) -> {
                icon.displayMessage("クリック", "クリックされました。", TrayIcon.MessageType.INFO);
            });
            PopupMenu menu = new PopupMenu();
            MenuItem addMenu = new MenuItem("追加");
            addMenu.addActionListener((java.awt.event.ActionEvent e) -> {
                Platform.runLater(() -> {
                    final Stage stage = new Stage(StageStyle.TRANSPARENT);
                    Group rootGroup = null;
                    try {
                        rootGroup = LOADER.load();
                    } catch (IOException e1) {
                        System.exit(9);
                    }
                    stage.show();
                });
            });
            menu.add(addMenu);
            MenuItem exitMenu = new MenuItem("終了");
            exitMenu.addActionListener((java.awt.event.ActionEvent e) -> {
               System.exit(0);
            });
            menu.add(exitMenu);
            icon.setPopupMenu(menu);
            SystemTray.getSystemTray().add(icon);
        }
    }
}
