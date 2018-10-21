import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;

@SuppressWarnings("javadoc")
public class Mandelbrot {

    public static final class Window {
        private final double centerX, centerY, zoom;

        /**
         * Create a window centered at the given logical location and zoom
         * level.
         *
         * @param centerX
         *            The X location
         * @param centerY
         *            The Y location
         * @param zoom
         *            The zoom degree (1.0 to 1.79e308 or so)
         */
        public Window(double centerX, double centerY, double zoom) {
            if (zoom < 1.0) {
                throw new IllegalArgumentException("Illegal zoom " + zoom);
            }
            this.centerX = centerX;
            this.centerY = centerY;
            this.zoom = zoom;
        }

        public double getCenterX() {
            return centerX;
        }

        public double getCenterY() {
            return centerY;
        }

        public double getZoom() {
            return zoom;
        }

    }

    /**
     * Compute a matrix of iterations representing a window in to the Mandelbrot set.
     *
     * @param pixWidth The width of the matrix to compute
     * @param pixHeight The height of the matrix to compute
     * @param limit The limit at which computations assume the coordinate is included in the set.
     * @param window The definition of the location and zoom degree in to the set.
     * @return A matrix containing the computational iterations
     */
    public static final int[][] mandelbrot(final int pixWidth, final int pixHeight,
                                           final int limit, final Window window) {

        final double mandWidth = 3.5 / window.getZoom();
        final double mandHeight = 2.0 / window.getZoom();
        final double xStep = mandWidth / pixWidth;
        final double yStep = mandHeight / pixHeight;

        final double left = window.getCenterX() - mandWidth / 2.0;
        final double bottom = window.getCenterY() - mandHeight / 2.0;

        final double[] scaleX = IntStream.range(0, pixWidth)
                .mapToDouble(x -> left + x * xStep).toArray();
        final double[] scaleY = IntStream.range(0, pixHeight)
                .mapToDouble(y -> bottom + y * yStep).toArray();

        return IntStream
                .range(0, pixHeight)
                .parallel()
                .mapToObj(
                        y -> IntStream.range(0, pixWidth)
                                .map(x -> countIterations(limit, scaleX[x], scaleY[y]))
                                .toArray()).toArray(s -> new int[s][]);

    }

    private static int countIterations(final int limit, final double x0, final double y0) {
        double x = 0.0;
        double y = 0.0;
        int iterations = 0;
        while (x * x + y * y < 4.0 && iterations < limit) {
            double xt = x * x - y * y + x0;
            y = 2 * x * y + y0;
            x = xt;
            iterations++;
        }
        return iterations;
    }

    /**
     * Create a buffered image mapping the iterations of the mandelbrot to the color palette
     * @param mand The matrics to map.
     * @param color The color to map for the matrix.
     * @return A BufferedImage containing the mapped mandelbrot.
     */
    public static BufferedImage mapMandelbrot(int[][] mand, final int[] color) {
        final int width = mand[0].length;
        final int height = mand.length;
        final BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        int[] pixels = IntStream.range(0, width * height)
                .map(p -> color[mand[p / width][p % width] % color.length]).toArray();

        image.setRGB(0, 0, width, height, pixels, 0, width);
        return image;
    }

    /**
     * Produce an increasingly bright sequence of colors that spiral out through the colour wheel.
     *
     * @param maxIndex the number of colors to produce (inclusive)
     * @return an array of aRGB values that represent the unique colors.
     */
    public static final int[] buildColors(final int maxIndex) {
        final float root = (float) Math.sqrt(maxIndex);
        return IntStream
                .rangeClosed(0, maxIndex)
                .map(c -> maxIndex - c)
                .mapToObj(
                        c -> Color
                                .getHSBColor((c % root) / root, 1.0f, (c / root) / root))
                .mapToInt(c -> c.getRGB()).toArray();
    }

    /**
     * For a given width, return a height that matches the ratio of a Mandelbrot
     * image. A complete Mandelbrot is contained within -2.5 to 1.0 and -1.0 to
     * 1.0 which gives a useful ratio of height to width of 3.5/2.0.
     * <p>
     * The returned height will always be odd which allows the center of the
     * image to be on an exact row.
     *
     * @param width
     *            the width to compute a height for.
     * @return the corresponding height.
     */
    public static final int getAppropriateHeight(final int width) {
        int height = (int) ((width / 3.5) * 2.0);
        // an odd-numbered height is useful for presentation - especially at
        // zoom 1.0
        return height % 2 == 0 ? height + 1 : height;
    }

    /**
     * Convenience method for testing the mandelbrot function.
     * <p>
     * This method coordinates a complete computation for a mandelbrot of given dimensions.
     *
     * @param width The resulting image width
     * @param height The resulting image height
     * @param limit The limit of iteration testing for the mandelbrot inclusion
     * @param window The position and scale within the mandelbrot set to compute.
     * @param outdir The location in which to store the resulting image.
     * @throws IOException if the image could not be stored.
     */
    public static final void buildBrot(int width, int height, int limit, Window window,
                                       File outdir) throws IOException {
        int[] colormap = buildColors(limit);

        long start = System.nanoTime();
        final int[][] mand = mandelbrot(width, height, limit, window);

        long built = System.nanoTime();
        BufferedImage image = mapMandelbrot(mand, colormap);

        long rendered = System.nanoTime();

        File outfile = new File(outdir, String.format(
                "mandelbrot_w%d_x%d_c%.3fms_r%.3fms.png", width, limit,
                (built - start) / 1000000.0, (rendered - built) / 1000000.0));
        ImageIO.write(image, "png", outfile);
        System.out.println("Created " + outfile);
    }

    public static void main(String[] args) throws IOException {
        File outdir = new File("output");
        outdir.mkdirs();

        // simple window centered on the basic set, and at basic zoom
        Window window = new Window(-2.5 + 3.5 / 2, 0.0, 1.0);

        buildBrot(2048, getAppropriateHeight(2048), 1000, window, outdir);
    }

}