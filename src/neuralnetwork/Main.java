/*
 * The MIT License (MIT)
 * 
 * Copyright © 2014 Pedro Hoffmann Alves
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the “Software”), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package neuralnetwork;

import neuralnetwork.gui.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import neuralnetwork.network.*;

/**
 * The main class containing most of the program logic. Handles and creates the
 * neural network.
 *
 * @author Pedro Hoffmann Alves
 */
public final class Main {

    /**
     * Defines the width of the input bitmaps.
     */
    public static final int IMAGE_WIDTH = 15;

    /**
     * Defines the height of the input bitmaps.
     */
    public static final int IMAGE_HEIGHT = 15;

    /**
     * Contains the input neurons receiving data from outside the network.
     */
    private final Neuron[] inputNeurons = new Neuron[Main.IMAGE_WIDTH * Main.IMAGE_HEIGHT];

    /**
     * Contains the output neurons giving data to the outside.
     */
    private final Neuron[] outputNeurons = new Neuron[10];

    /**
     * The image currently being processed.
     */
    public BufferedImage image;

    /**
     * The delay time between steps.
     * Can be modified using the GUI.
     */
    public int delay;

    /**
     * Determines whether the network should continue the training process.
     */
    public boolean training = false;

    /**
     * Determines whether the network should continue the test process.
     */
    public boolean testing = false;

    /**
     * Counts the images already processed.
     */
    public int totalSteps = 0;

    /**
     * Counts the correctly recognized images.
     */
    public int correctSteps = 0;

    /**
     * The {@link GUI} object handling user inputs and graphical outputs.
     */
    public GUI gui;

    /**
     * Called at program start. Creates a new {@code Main} object containing the core
     * program logic.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Unable to set System Look and Feel");
        }
        new Main();
    }

    /**
     * Creates a new {@code Main} object. Fills the input and output {@link Neuron} arrays
     * and displays a {@link GUI}.
     */
    public Main() {
        
        this.createNetwork();
        
        int[] pixels = this.getPixels("img/digit0-0.bmp");
        image = this.getImageFromPixels(pixels);

        this.gui = new GUI(this);
        this.gui.setIconImage(new ImageIcon(this.getClass().getResource("img/icon.png")).getImage());
        
        gui.setImage(image);
        this.gui.log("Program gestartet");
        this.gui.log("Bereit" + System.lineSeparator());
    }

    /**
     * Reads the pixels from a given image file.
     * Also adds random color pixels with a chance of 10 percent each.
     *
     * @param filename The image file to read.
     * @return An array of integer RGB values.
     */
    public int[] getPixels(String filename) {
        int[] pixels = new int[Main.IMAGE_HEIGHT * Main.IMAGE_WIDTH];
        Random rand = new Random();
        try {
            image = ImageIO.read(getClass().getResource(filename));
            int count = 0;
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    pixels[count++] = image.getRGB(i, j);
                    if (this.gui != null && this.gui.getDifficulty() > 0 && rand.nextInt(10) == 0) {
                        pixels[count - 1] = rand.nextInt(16777216) - 16777216; // RGB range
                    }
                }
            }
        } catch (IOException ex) {
            this.gui.log("Datei konnte nicht gelesen werden!");
        }
        return pixels;
    }

    /**
     * Converts an array of RGB values into an {@link BufferedImage}.
     *
     * @param pixels An array of integer RGB values.
     * @return A {@link BufferedImage} representing the array.
     */
    public BufferedImage getImageFromPixels(int[] pixels) {
        BufferedImage img = new BufferedImage(Main.IMAGE_WIDTH, Main.IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        int c = 0;
        for (int i = 0; i < Main.IMAGE_WIDTH; i++) {
            for (int j = 0; j < Main.IMAGE_HEIGHT; j++) {
                img.setRGB(i, j, pixels[c++]);
            }
        }
        return img;
    }

    /**
     * Creates all {@link Neuron}s and {@link Connection}s of the neural network.
     *
     * Sets up the defined number of input and output neurons and connects each
     * input neuron to each output neuron.
     */
    private void createNetwork() {
        for (int i = 0; i < this.inputNeurons.length; i++) {
            this.inputNeurons[i] = new Neuron();
        }
        for (int i = 0; i < this.outputNeurons.length; i++) {
            this.outputNeurons[i] = new Neuron();
            Connection[] connections = new Connection[this.inputNeurons.length];
            for (int j = 0; j < connections.length; j++) {
                connections[j] = new Connection(this.inputNeurons[j]);
            }
            this.outputNeurons[i].connections = connections;
        }
    }

    /**
     * Creates {@link Neuron} objects related to the given color values.
     *
     * @param pixels Array of integer RGB values for the input neurons.
     */
    private void fillInputNeurons(int[] pixels) {
        for (int i = 0; i < this.inputNeurons.length; i++) {
            if (pixels[i] < -1) {
                this.inputNeurons[i].setSignal(1.0);
            } else {
                this.inputNeurons[i].setSignal(0.0);
            }
        }
    }

    /**
     * Performs a single step, processing an image file containing a number.
     * Also trains the neural network by adjusting the {@link Connection}s' weights.
     * Uses the difficulty selected in the {@link GUI}.
     * 
     * @param digit The digit to process. Image file is loaded automatically.
     * @param training If true, weights are adjusted after the step.
     * @return The recognized number, no matter if correct or not.
     */
    public int performSingleStep(int digit, boolean training) {
        int[] pixels = this.getPixels("img/digit" + digit + "-" + this.gui.getDifficulty() + ".bmp");
        this.image = this.getImageFromPixels(pixels);
        this.fillInputNeurons(pixels);
        int highest = 0;
        for (Neuron out : this.outputNeurons) {
            out.calculateSignal();
        }
        for (int i = 0; i < this.outputNeurons.length; i++) {
            if (this.outputNeurons[i].getSignal() > this.outputNeurons[highest].getSignal()) {
                highest = i;
            }
        }
        boolean correct = highest == digit;
        if (training) {
            for (Connection c : this.outputNeurons[digit].connections) {
                if (correct) {
                    c.increaseWeight(Connection.INCREASE_SUCCESS * c.origin.getSignal());
                } else {
                    c.increaseWeight(Connection.INCREASE_FAILURE * c.origin.getSignal());
                }
            }
        }
        this.totalSteps++;
        if (correct) {
            this.correctSteps++;
        }

        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException ex) {
            this.gui.log("Netz kann nicht verlangsamt werden");
        }
        this.gui.log(this.correctSteps + " von " + this.totalSteps + " Bildern erkannt (" + new DecimalFormat("#0.00").format((double) this.correctSteps / (double) this.totalSteps * 100) + "%)");
        return highest;
    }

    /**
     * Completely resets the neural network and {@link GUI}.
     */
    public void reset() {
        this.correctSteps = 0;
        this.totalSteps = 0;
        this.createNetwork();
        this.gui.reset();
        this.gui.log("Netz wurde zurückgesetzt");
    }
}
