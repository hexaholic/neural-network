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
package neuralnetwork.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.text.DefaultCaret;
import neuralnetwork.Main;
import neuralnetwork.Tester;
import neuralnetwork.Trainer;

/**
 * Implememts a graphical user interface for the neural network application.
 * Contains visual elements for user interaction and recognition visualization.
 * 
 * @author Pedro Hoffmann Alves
 */
public class GUI extends javax.swing.JFrame {
    
    /**
     * The {@code Main} object containing the program logic.
     */
    private final Main main;
    
    /**
     * The thread currently training or testing the network.
     * Should be of class {@link Trainer} or {@link Tester}.
     */
    private Thread thread;
    
    /**
     * Creates new form {@code GUI}.
     * @param main The {@code Main} object containing the program logic.
     */
    public GUI(Main main) {
        this.main = main;
        initComponents();
        DefaultCaret caret = (DefaultCaret) this.taLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.setVisible(true);
        this.reset();
    }
    
    /**
     * Returns the currently set difficulty.
     * @return An integer representing the difficulty.
     */
    public int getDifficulty() {
        return (Integer) this.spDifficulty.getValue();
    }
    
    /**
     * Checks if the status checkbox is selected.
     * @return True if selected, false otherwise.
     */
    public boolean getCbStatus() {
        return this.cbShowStatus.isSelected();
    }
    
    /**
     * Displays a log entry in the dedicated {@code JTextArea}.
     * 
     * @param msg The line of text to be logged.
     */
    public void log(String msg) {
        this.taLog.append(msg);
        this.taLog.append(System.lineSeparator());
    }
    
    /**
     * Changes the image displayed on the user interface.
     * 
     * @param img The new image to be displayed.
     */
    public void setImage(BufferedImage img) {
        this.lbImage.setIcon(new ImageIcon(img.getScaledInstance(img.getWidth() * 8, img.getHeight() * 8, Image.SCALE_DEFAULT)));
    }
    
    /**
     * Changes the recognition rate displayed on the user interface.
     * 
     * @param rate The new rate to be displayed. Should be between 0 and 1.
     */
    public void setRecognitionRate(double rate) {
        this.pbRecognitionRate.setValue((int) (rate * 100));
        this.pbRecognitionRate.setString("Erkennungsrate: " + new DecimalFormat("#0.00").format(rate * 100) + "%");
        this.pbRecognitionRate.setStringPainted(true);
    }
    
    /**
     * Changes the {@code GUI}'s appearance depending of the last answer.
     * Both the text and the color of a dedicated {@code JLabel} are changed,
     * depending on the corectness of the answer.
     * 
     * @param correct Determines whether the last answer was correct or not.
     */
    private void setLastAnswer(boolean correct) {
        if(correct) {
            this.lbCorrect.setText("Richtig");
            this.lbCorrect.setForeground(Color.GREEN.darker());
        }
        else {
            this.lbCorrect.setText("Falsch");
            this.lbCorrect.setForeground(Color.RED.darker());
        }
    }
    
    /**
     * Fills in the results of the last step.
     * @param recognized The number the network recognized.
     * @param correct The correct number contained in the processed image.
     */
    public void setStep(int recognized, int correct) {
        this.tfRecognized.setText(Integer.toString(recognized));
        this.tfCorrect.setText(Integer.toString(correct));
        this.setLastAnswer(recognized == correct);
    }
       
    /**
     * Completely resets the GUI and all displayed values.
     */
    public void reset() {
        this.setStep(0, 0);
        this.setRecognitionRate(0);
        this.lbCorrect.setForeground(Color.BLUE.darker());
        this.lbCorrect.setText("Nicht trainiert");
        this.lbSpeed.setText(this.slSpeed.getValue() + " Bilder pro Sekunde");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbImage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfRecognized = new javax.swing.JTextField();
        tfCorrect = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lbCorrect = new javax.swing.JLabel();
        btTraining = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taLog = new javax.swing.JTextArea();
        pbRecognitionRate = new javax.swing.JProgressBar();
        cbShowStatus = new javax.swing.JCheckBox();
        btTest = new javax.swing.JButton();
        btReset = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        spDifficulty = new javax.swing.JSpinner();
        slSpeed = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbSpeed = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Künstliches Neuronales Netz");
        setLocationByPlatform(true);
        setResizable(false);

        lbImage.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setText("Erkannte Ziffer:");

        tfRecognized.setEditable(false);
        tfRecognized.setText("0");

        tfCorrect.setEditable(false);
        tfCorrect.setText("0");

        jLabel2.setText("Richtige Ziffer:");

        lbCorrect.setForeground(new java.awt.Color(0, 0, 204));
        lbCorrect.setText("Nicht trainiert");

        btTraining.setText("Training starten");
        btTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTrainingActionPerformed(evt);
            }
        });

        taLog.setColumns(20);
        taLog.setRows(5);
        jScrollPane1.setViewportView(taLog);

        cbShowStatus.setSelected(true);
        cbShowStatus.setText("Status zeigen");

        btTest.setText("Test starten");
        btTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTestActionPerformed(evt);
            }
        });

        btReset.setText("Netz zurücksetzen");
        btReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetActionPerformed(evt);
            }
        });

        jLabel3.setText("Schwierigkeit:");

        spDifficulty.setModel(new javax.swing.SpinnerNumberModel(0, 0, 3, 1));

        slSpeed.setMaximum(16);
        slSpeed.setMinimum(1);
        slSpeed.setPaintLabels(true);
        slSpeed.setPaintTicks(true);
        slSpeed.setSnapToTicks(true);
        slSpeed.setValue(4);
        slSpeed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slSpeedStateChanged(evt);
            }
        });

        jLabel4.setText("Geschwindigkeit:");

        lbSpeed.setText("0 Bilder pro Sekunde");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbCorrect, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbShowStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfCorrect)
                                    .addComponent(tfRecognized, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                    .addComponent(spDifficulty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btTraining, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btTest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(pbRecognitionRate, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbSpeed)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(slSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfRecognized, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btTraining))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfCorrect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(btTest))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btReset)
                            .addComponent(jLabel3)
                            .addComponent(spDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbShowStatus)
                            .addComponent(lbCorrect))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pbRecognitionRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbSpeed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Called when {@code btTraining} is clicked.
     * Starts or stops the training process and handles the other buttons.
     * 
     * @param evt The click event.
     */
    private void btTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTrainingActionPerformed
        if(!this.main.testing) {
            if(this.main.training) {
                this.main.training = false;
                try {
                    this.thread.join();
                } catch (InterruptedException ex) {
                    System.out.println("Warning: Training not stopped");
                }
                this.btTraining.setText("Training starten");
                this.btTest.setEnabled(true);
                //this.btReset.setEnabled(true);
            }
            else {
                this.btTest.setEnabled(false);
                //this.btReset.setEnabled(false);
                this.btTraining.setText("Training stoppen");
                this.main.training = true;
                this.thread = new Thread(new Trainer(this.main));
                this.thread.start();
            }
        }
    }//GEN-LAST:event_btTrainingActionPerformed
    
    /**
     * Called when {@code btTest} is clicked.
     * Starts or stops the test process and handles the other buttons.
     * @param evt The click event.
     */
    private void btTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTestActionPerformed
        if(!this.main.training) {
            if(this.main.testing) {
                this.main.testing = false;
                try {
                    this.thread.join();
                } catch (InterruptedException ex) {
                    System.out.println("Warning: Testing not stopped");
                }
                this.btTest.setText("Test starten");
                this.btTraining.setEnabled(true);
                //this.btReset.setEnabled(true);
            }
            else {
                this.btTraining.setEnabled(false);
                //this.btReset.setEnabled(false);
                this.btTest.setText("Test stoppen");
                this.main.testing = true;
                this.thread = new Thread(new Tester(this.main));
                this.thread.start();
            }
        }
    }//GEN-LAST:event_btTestActionPerformed
    
    /**
     * Called when {@code btReset} is clicked.
     * Resets both the neural network and the {@code GUI}.
     * @param evt The click event.
     */
    private void btResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetActionPerformed
        this.main.reset();
    }//GEN-LAST:event_btResetActionPerformed

    /**
     * Called when {@code slSpeed} is changed.
     * Changes the neural networks speed and displays the new value.
     * @param evt The click event.
     */
    private void slSpeedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slSpeedStateChanged
        if(this.slSpeed.getValue() == this.slSpeed.getMaximum()) {
            this.main.delay = 0;
            this.lbSpeed.setText("Unbegrenzt");
        }
        else {
            this.main.delay = 1000 / this.slSpeed.getValue();
            if(this.slSpeed.getValue() == 1) {
                this.lbSpeed.setText("1 Bild pro Sekunde");
            }
            else {
                this.lbSpeed.setText(this.slSpeed.getValue() + " Bilder pro Sekunde");
            }
        }
    }//GEN-LAST:event_slSpeedStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btReset;
    private javax.swing.JButton btTest;
    private javax.swing.JButton btTraining;
    private javax.swing.JCheckBox cbShowStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbCorrect;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbSpeed;
    private javax.swing.JProgressBar pbRecognitionRate;
    private javax.swing.JSlider slSpeed;
    private javax.swing.JSpinner spDifficulty;
    private javax.swing.JTextArea taLog;
    private javax.swing.JTextField tfCorrect;
    private javax.swing.JTextField tfRecognized;
    // End of variables declaration//GEN-END:variables
}
