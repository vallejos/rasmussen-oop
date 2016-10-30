import java.awt.*;
import java.awt.geom.*;

/**
 * Class BoxBall - a graphical ball that bounces inside a box.
 * 
 * @author Leonardo Vallejos
 *
 * @version 2016.10.30
 */

public class BoxBall
{
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int width, height;
    private Canvas canvas;
    private int xSpeed, ySpeed;

    /**
     * Constructor for objects of class BoxBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int boxHeight, int boxWidth, Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        height = boxHeight;
        width = boxWidth;
        canvas = drawingCanvas;
        xSpeed = 0;
        ySpeed = 0;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move(int speedX, int speedY)
    {
        // remove from canvas at the current position
        erase();

        xSpeed = speedX;
        ySpeed = speedY;
        
        // compute new position
        yPosition += ySpeed;
        xPosition += xSpeed;

        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }

    /**
     * return the diameter of this ball
     */
    public int getDiameter() {
        return diameter;
    }    
    
    /**
     * return X Speed
     */
    public int getXSpeed() {
        return xSpeed;
    }
    
    /**
     * return Y Speed
     */
    public int getYSpeed() {
        return ySpeed;
    }
}
