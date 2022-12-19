package io.exiled.auto24tg.service.impl;

import org.w3c.dom.Document;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class JavaFXWebPageDownloader extends Application{
    // Entry point to the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }

    // Start called by the launch() method
    @Override
    public void start(final Stage stage) {

        // WebView automatically creates WebEngine
        final WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();

        // Set webView to render in application window
        Pane root = new Pane(webView);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // Load webpage - webEngine automatically sends get request
//        webEngine.load("https://iq.opengenus.org//");
        webEngine.load("https://www.auto24.ee/kasutatud/nimekiri.php?b=12&ae=8&bw=154&f2=2011&f1=2005&ssid=80201607");

        // Get reference to the DOM document of the webpage
        Document page = webEngine.getDocument();
        System.err.println(page);
        // possible solution: https://ashley-tharp.medium.com/solved-error-javafx-runtime-components-are-missing-and-are-required-to-run-this-application-ec4779eb796d
        // all possible ways : https://iq.opengenus.org/download-webpage-in-java/
    }
}
