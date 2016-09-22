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
package neuralnetwork.network;

/**
 * Implements a single neuron within a neuronal network.
 * 
 * @author Pedro Hoffmann Alves
 */
public class Neuron {
    
    /**
     * The {@link Connection}s to other {@code Neuron} objects.
     */
    public Connection[] connections;
    
    /**
     * The calculated output signal of the {@code Neuron}.
     * Equals 0.0 until another value is calculated.
     */
    private double output;
    
    public Neuron() {
        this.output = 0.0;
    }
    
    public double getSignal() {
        return this.output;
    }
    
    public void setSignal(double level) {
        this.output = level;
    }
    
    public void calculateSignal() {
        this.output = 0.0;
        for(Connection connection : this.connections) {
            this.output += connection.origin.getSignal() * connection.getWeight();
        }
        this.output = this.output / this.connections.length;
    }
    
}
