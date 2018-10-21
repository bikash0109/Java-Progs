/*
 * Program Name: MandelbrotFX.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 * Modified the existing code, to be run by threads, based on the command line input for number of threads,
 * If no input is given, select threads based on the number of cores of the processor.
 *
 * logic : based on the threads, certain number of pixels are assigned, so complete the task in parallel fashion.
 *
 * */


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class MandelbrotFX extends Application {

    WritableImage mandelBrotSetImage;
    final int IMG_WIDTH = 800;
    final int IMG_HEIGHT = 800;
    long milliSeconds;

    public void init() {
        milliSeconds = System.currentTimeMillis();
    }

    public void end(String s) {
        System.err.println(s + ":       " + (System.currentTimeMillis() - milliSeconds) + "ms");
        System.err.println(" # of cores" + ":       " +
                Runtime.getRuntime().availableProcessors());
    }

    public void start(Stage theStage) {

        int numOfCore = Runtime.getRuntime().availableProcessors(); // getting the number of cores
        List<String> args = getParameters().getRaw();
        int numberOfThreads = numOfCore;
        if(args.size() > 0){
            numberOfThreads = Integer.parseInt(args.get(0));
        }

        MandelbrotSet aMandelbrotSet = new MandelbrotSet(IMG_WIDTH, IMG_HEIGHT, numberOfThreads);

        init();
        mandelBrotSetImage = aMandelbrotSet.createImage();
        end("Multiple Thread MandelbrotSet Test");


        ImageView aImage = new ImageView();
        aImage.setImage(mandelBrotSetImage);

        StackPane root = new StackPane();
        root.getChildren().add(aImage);

        Scene scene = new Scene(root, IMG_WIDTH, IMG_HEIGHT);

        theStage.setTitle("Mandelbrot Set");
        theStage.setResizable(false);
        theStage.setScene(scene);
        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


class MandelbrotSet extends Thread {

    private static final int MAX_COLORS = 256;
    private static final double BOUNDERY = 1000;
    private static int width;
    private static int height;
    private static WritableImage mandelBrotSetImage;
    private static PixelWriter aPixelWriter;
    private static final Color[] colors = new Color[MAX_COLORS];
    private static double minR = -2.4;
    private static double maxR = 0.9;
    private static double minI = -1.3;
    private static double maxI = 1.28;
    private static MandelbrotSet[] allThreads;
    private int threadId = 0;
    private static int numberOfThreads = 0;

    static {
        for (int index = 0; index < colors.length; index++) {
            colors[index] = Color.RED.interpolate(Color.BLUE, ((1.0 / colors.length) * index));
        }
    }

    public MandelbrotSet(int number) {
        this.threadId = number;
    }

    public MandelbrotSet(int width, int height, int numberOfThreads) {
        this.width = width;
        this.height = height;
        this.numberOfThreads = numberOfThreads;
        mandelBrotSetImage = new WritableImage(width, height);
        if (allThreads == null)
            allThreads = new MandelbrotSet[width * height];
    }

    private Color getColor(int count) {
        return count >= colors.length ? Color.BLACK : colors[count];
    }

    private int calc(double re, double img) {
        int counter = 0;
        double length;
        double aComplexNumberRe = 0;
        double aComplexNumberImg = 0;
        double real = 0;
        double imaginary = 0;

        do {
            real = aComplexNumberRe * aComplexNumberRe -
                    aComplexNumberImg * aComplexNumberImg;
            imaginary = aComplexNumberRe * aComplexNumberImg +
                    aComplexNumberImg * aComplexNumberRe;
            aComplexNumberRe = real;
            aComplexNumberImg = imaginary;
            aComplexNumberRe += re;
            aComplexNumberImg += img;
            length = aComplexNumberImg * aComplexNumberImg +
                    aComplexNumberRe * aComplexNumberRe;
            counter++;
        } while (counter < MAX_COLORS && (length < BOUNDERY));
        return counter;
    }

    public Color determineColor(int x, int y) {
        double re = (minR * (width - x) + x * maxR) / width;
        double img = (minI * (height - y) + y * maxI) / height;
        return getColor(calc(re, img));
    }

    // run method for threads
    public void run() {
        aPixelWriter = mandelBrotSetImage.getPixelWriter();
            System.out.println("Thread number : " + threadId);
            int divisibleNumber = numberOfThreads > 10 ? 5 : numberOfThreads/2; //To skip the pixels, which are already covered.
            for (int x = threadId; x < width; x+=numberOfThreads/divisibleNumber) {
                for (int y = threadId; y < height; y++) {
                    aPixelWriter.setColor(x, y, determineColor(x, y));
                }
            }
    }

    // Creating threads and starting them.
    public WritableImage createImage() {
        mandelBrotSetImage = new WritableImage(width, height);
        aPixelWriter = mandelBrotSetImage.getPixelWriter();
        allThreads = new MandelbrotSet[numberOfThreads];
        System.out.println("Total number of threads: " + numberOfThreads);
        for (int i = 0; i < allThreads.length; i++) {
            allThreads[i] = new MandelbrotSet(i);
            allThreads[i].start();
        }
        return mandelBrotSetImage;
    }
}
 