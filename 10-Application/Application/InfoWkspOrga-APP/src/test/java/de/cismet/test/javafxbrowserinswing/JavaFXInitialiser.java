package de.cismet.test.javafxbrowserinswing;

import javafx.application.Application;

import javafx.stage.Stage;

import java.lang.reflect.Method;

public class JavaFXInitialiser {
    private static final String NB_MAIN_CLASS = "de.cismet.test.javafxbrowserinswing.Frame";

    public static void main(final String[] args) {
        TmpFxLauncher.launch();

        // Hand control back to NetBeans
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        try {
            final Class<?> mainClass = Class.forName(NB_MAIN_CLASS, true, classloader);

            final Object mainObject = mainClass.newInstance();
            final Method mainMethod = mainClass.getDeclaredMethod("main", new Class[]{String[].class});
            mainMethod.invoke(mainObject, (Object) args);
        } catch (Throwable e) {
            System.err.println("BAEM!");
            e.printStackTrace();
        }
    }

    public static class TmpFxLauncher extends Application {
        @Override
        public void start(final Stage primaryStage) {
        }

        private static void launch() {
            Application.launch(null);
        }
    }
}
