import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// TASK: Create a class called Shape which extends Thread
class Shape extends Thread {
    // Set the variables
    private Color shapeColor;
    private int a, b;
    private int shapeWidth, shapeHeight;
    private int currentASpeed, currentBSpeed;
    private JPanel panel;

    // Write get methods to call the variables at any time and any location
    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getShapeWidth() {
        return shapeWidth;
    }

    public int getShapeHeight() {
        return shapeHeight;
    }

    public Color getShapeColor() {
        return shapeColor;
    }

    // Determine the values of the variables
    public Shape(JPanel panel) {
        this.panel = panel;
        shapeColor = getRandomColor();
        // Our shape is a square due to its width and height values
        shapeWidth = 125;
        shapeHeight = 125;
        // Connect the random methods to actual variables
        a = getRandomCoordinate(panel.getWidth() - shapeWidth);
        b = getRandomCoordinate(panel.getHeight() - shapeHeight);
        currentASpeed = getRandomSpeed();
        currentBSpeed = getRandomSpeed();
    }

    // TASK: Update the movement of the object in the overriden run method
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            // Update the position of the shape
            a += currentASpeed;
            b += currentBSpeed;

            // Get the size of the panel
            int panelWidth = panel.getWidth();
            int panelHeight = panel.getHeight();

            // TASK: Make sure that if the shapes hit the edge of the animation frame, they bounce back
            if (a <= 0 || a >= panelWidth - shapeWidth) {
                currentASpeed = currentASpeed * -1;
            }
            if (b <= 0 || b >= panelHeight - shapeHeight) {
                currentBSpeed = currentBSpeed * -1;
            }

            // TASK: Use repaint in the run method to paint the shapes on the frame continuously
            panel.repaint();

            try {
                // Set the delay to control the speed of the animation
                Thread.sleep(30);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    // TASK: Initialize all objects on random locations, with random speeds, and random colors inside the frame.
    private Color getRandomColor() {
        Random random = new Random();
        // Set the maximum color choice range in the color method, so it is 256 in the java
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    // Get random coordinates to initialize the shapes' animations on different locations
    private int getRandomCoordinate(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    // Get random speed values to initialize the shapes' animations in different movement speeds
    private int getRandomSpeed() {
        Random random = new Random();
        return random.nextInt(5) + 1; // Speed should be at least 1 to make the shapes always moving
    }
}

// TASK: Create a class called Animation which again extends Thread or implements Runnable
class Animation extends Thread {
    private List<Shape> shapes;

    public Animation(JPanel panel, int numberOfShapes) {
        shapes = new ArrayList<>();

        // Count the number of shapes in the loop and add them to the thread
        for (int i = 0; i < numberOfShapes; i++) {
            Shape shape = new Shape(panel);
            shapes.add(shape);
        }

        // Start all the Shape threads after creating them
        for (Shape shape : shapes) {
            shape.start();
        }
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                // Adjust the delay to control the speed of the animation
                Thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    // Method to stop the animation by interrupting and clearing it
    public void stopAnimation() {
        for (Shape shape : shapes) {
            shape.interrupt();
        }
        shapes.clear();
    }
}

// Method to use the buttons effectively and getting the user input
public class MovingShapeAnimation extends JPanel {
    private Animation animation;

    public MovingShapeAnimation() {
        new ArrayList<>();
        setLayout(null);

        // TASK: Add button to start the animation.
        JButton startButton = new JButton("START");
        startButton.setBounds(250, 600, 150, 50);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animation == null || !animation.isAlive()) {
                    // TASK: Get an input from user to determine the number of objects in the animation.
                    int numberOfShapes = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of objects:"));
                    animation = new Animation(MovingShapeAnimation.this, numberOfShapes);
                    animation.start(); // Start the animation after getting the input for number of objects
                }
            }
        });

        // TASK: Add button to stop the animation.
        JButton stopButton = new JButton("STOP");
        stopButton.setBounds(415, 600, 150, 50);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animation != null) {
                    animation.stopAnimation(); // Stop the animation on an action event
                    animation = null; // Clear the animation
                }
            }
        });

        // Add the buttons to the frame
        add(startButton);
        add(stopButton);
    }

    // Class for painting the shapes while maintaining their widths and heights
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw all shapes from the Animation class
        if (animation != null) {
            List<Shape> animationShapes = animation.getShapes();
            for (Shape shape : animationShapes) {
                // Call the variables with get methods
                g.setColor(shape.getShapeColor());
                g.fillRect(shape.getA(), shape.getB(), shape.getShapeWidth(), shape.getShapeHeight());
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        // Set the properties of JFrame
        JFrame frame = new JFrame("Moving Shape Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 700);

        // Add the animation panel to the frame and set it visible
        MovingShapeAnimation animationPanel = new MovingShapeAnimation();
        frame.add(animationPanel);
        frame.setVisible(true);
    }
}
