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
