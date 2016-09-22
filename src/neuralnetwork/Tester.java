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

/**
 * Trains the neural network.
 * 
 * @author Pedro Hoffmann Alves
 */
public class Tester implements Runnable {
    
    /**
     * The Main object containing the neural network and logic.
     */
    private final Main main;
    
    /**
     * The digit currently being processed.
     */
    private int currentDigit;
    
    /**
     * Creates a new Tester object.
     * 
     * @param main Contains the neural network to test.
     */
    public Tester(Main main) {
        this.main = main;
        this.currentDigit = 0;
    }

    /**
     * Starts the testing process.
     * Runs until the {@code testing} flag in the {@link Main} object is set to false.
     */
    @Override
    public void run() {
        this.main.gui.log("Test gestartet...");
        while(this.main.testing) {
            int recognized = this.main.performSingleStep(this.currentDigit, false);
            if(this.main.gui.getCbStatus()) {
                this.main.gui.setStep(recognized, this.currentDigit);
                this.main.gui.setImage(this.main.image);
            }
            if(++this.currentDigit > 9) {
                this.currentDigit = 0;
            }
            this.main.gui.setRecognitionRate((double)this.main.correctSteps / (double)this.main.totalSteps);
        }
        this.main.gui.log("Test beendet");
    }
    
}
