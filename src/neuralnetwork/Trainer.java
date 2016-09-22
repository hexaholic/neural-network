/*
 * Copyright (C) 2014 Pedro Hoffmann Alves <github@hexaholic.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package neuralnetwork;

/**
 * Trains the neural network.
 * 
 * @author Pedro Hoffmann Alves
 */
public class Trainer implements Runnable {
    
    /**
     * The Main object containing the neural network and logic.
     */
    private final Main main;
    
    /**
     * The digit currently being processed.
     */
    private int currentDigit;
    
    /**
     * Creates a new Trainer object.
     * 
     * @param main Contains the neural network to train.
     */
    public Trainer(Main main) {
        this.main = main;
        this.currentDigit = 0;
    }
    
    /**
     * Starts the training process.
     * Runs until the {@code training} flag in the {@link Main} object is set to false.
     */
    @Override
    public void run() {
        this.main.gui.log("Training gestartet...");
        while(this.main.training) {
            int recognized = this.main.performSingleStep(this.currentDigit, true);
            if(this.main.gui.getCbStatus()) {
                this.main.gui.setStep(recognized, this.currentDigit);
                this.main.gui.setImage(this.main.image);
            }
            if(++this.currentDigit > 9) {
                this.currentDigit = 0;
            }
            this.main.gui.setRecognitionRate((double)this.main.correctSteps / (double)this.main.totalSteps);
        }
        this.main.gui.log("Training beendet");
    }
}
