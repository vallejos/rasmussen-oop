import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class BallDemo   
{
    private Canvas myCanvas;
    // The list of Bouncing Balls
    private ArrayList<BouncingBall> balls;
    private Canvas box;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);        
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce(int n)
    {
        int ground = 400;   // position of the ground line
        // set a few variables to handle each ball
        int red;
        int blue;
        int green;
        int x, y, diam;
        Random rand = new Random();
        
        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        balls = new ArrayList<>();
        for (int i=0; i<n; i++) {
            // set random position and diameter
            x = rand.nextInt(600);
            y = rand.nextInt(250);
            diam = rand.nextInt(21) + 10;
            
            // generate random color
            red = rand.nextInt(256);
            green = rand.nextInt(256);
            blue = rand.nextInt(256);
            Color color = new Color(red, green, blue);
            
            // add the ball to the list
            balls.add(new BouncingBall(x, y, diam, color, ground, myCanvas));
            balls.get(i).draw();
            myCanvas.wait(10);           // small delay
        }
        
        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            for (int i=0; i<balls.size(); i++) {
                balls.get(i).move();
            }
            // stop when the last ball in the list has travelled a certain distance on x axis
            if (balls.get(balls.size() - 1).getXPosition() >= 550) {
                finished = true;
            }    
        }
    }
    
    /**
     * Bounces a ball inside a box
     */
    public void boxBounce() {
        int height = 500; // box height
        int width = 800; // box width
        int maxBalls = 40; // how many balls to show in the box
        int times = 500; // times to bounce the balls
        int x, y, diam;
        int red, green, blue;
        Random rand = new Random();
        ArrayList<BoxBall> boxBalls = new ArrayList<>();

        // create and display box
        box = new Canvas("Box Bounce", width, height);
        box.setVisible(true);

        // create and show the balls
        for (int i=0; i<maxBalls; i++) {
            // set random position and diameter
            diam = rand.nextInt(21) + 10;
            x = rand.nextInt(width - diam);
            y = rand.nextInt(height - diam);

            // generate random color
            red = rand.nextInt(256);
            green = rand.nextInt(256);
            blue = rand.nextInt(256);
            Color color = new Color(red, green, blue);

            // add the ball to the list
            boxBalls.add(new BoxBall(x, y, diam, color, width, height, box));
            boxBalls.get(i).draw();
        }
        
        // make them bounce
        int bounces = 0;
        int xSpeed, ySpeed;

        // initialize each ball with random speeds
        for(BoxBall ball : boxBalls) {
            xSpeed = rand.nextInt(20);
            ySpeed = rand.nextInt(20);
            ball.move(xSpeed, ySpeed);
        }
        
        while (bounces < times) {            
            // stop when the ball has bounced at least 5 times
            for(BoxBall ball : boxBalls) {
                xSpeed = ball.getXSpeed();
                ySpeed = ball.getYSpeed();

                // check if it has hit top or bottom sides of the box
                if ((ball.getYPosition() >= (height - ball.getDiameter())) ||
                    (ball.getYPosition() <= (ball.getDiameter()))) {
                    ySpeed = -ball.getYSpeed();
                    bounces++;
                }
        
                // check if it has hit left or right sides of the box
                if ((ball.getXPosition() >= (width - ball.getDiameter())) ||
                    (ball.getXPosition() <= (ball.getDiameter()))) {
                    xSpeed = -ball.getXSpeed();
                    bounces++;
                }

                ball.move(xSpeed, ySpeed);
            }

            myCanvas.wait(50);           // small delay
        }
    }
    
}
