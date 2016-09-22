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
 * Implements a connection between Neurons.
 * 
 * Allows the Neurons to communicate (in one direction only).
 * Contains methods 
 * 
 * @author Pedro Hoffmann Alves
 */
public class Connection {
    
    /**
     * The default value a connections's weight is increased in case of a correct result.
     */
    public static final double INCREASE_SUCCESS = 0.00001;
    
    /**
     * The default value a connections's weight is increased in case of a wrong result.
     */
    public static final double INCREASE_FAILURE = 0.00002;
    
    /**
     * Points to the Neuron sending signals into the connection.
     */
    public final Neuron origin;
    
    /**
     * Represents the weight of the connection.
     * The higher the value, the more important the transmitted signal.
     */
    private double weight;
    
    /**
     * Creates a new Connection using the given Neuron as the signal source.
     * @param origin The Neuron sending signals.
     */
    public Connection(Neuron origin) {
        this.weight = 0.0;
        this.origin = origin;
    }
    
    /**
     * Increases the connections weight by the given value.
     * Usual arguments are Connection.INCREASE_SUCCESS and Connection.INCREASE_FAILURE.
     * @param value The value to be added to the connection's weight.
     */
    public void increaseWeight(double value) {
        this.weight = this.weight + value;
    }
    
    /**
     * Returns the connections's current weight.
     * @return The current weight.
     */
    public double getWeight() {
        return this.weight;
    }
    
}
