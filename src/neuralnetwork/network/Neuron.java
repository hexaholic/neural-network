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
