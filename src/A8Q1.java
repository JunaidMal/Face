
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author ${user}
 */
public class A8Q1 extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;


    // GAME VARIABLES WOULD GO HERE
    
    // VARIABLES USED TO MOVE TONGUE, STARTING TONGUE POSITION IS 30
int tongueX = 30;
    // -1 - Up
    // +1 - Down
    // TONGUE WILL START BY GOING DOWN
    int tongueDirection = 1;
    
    // USED TO CREATE BLINKING (IN DEV)
    int blinkTimeDelay = 3000;
    int blinkTimeDelay2 = 1000;
    long nextBlink = 0;
    long nextBlink2 = 0;
    
    // DRAWS IN EYELID VARIABLES TO BE USED FOR BLINKING (IN DEV)
    int eyelid1 = 2320;
    int eyebrow1 = 2790;
    int eyebrow2 = 2820;
    
    
    
    // EYEBROW MOVEMENT VARIABLES(IN DEV)
//    int eyebrowX = 440;
//    int eyebrow2X = 520;
//    int eyebrow3X = 555;
//    int eyebrow4X = 450;
//    int eyebrowDrop1 = 220;
//    int eyebrowDrop2 = 230;
//    int eyebrowRise1 = 220;
//    int eyebrowRise2 = 230;
//    // -1 - left
//    // +1 - right
//    int eyebrowDirection = -1;
//    int eyebrowvertDirection = 1;
    
    


    // GAME VARIABLES END HERE   

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE 
        
        // SKIN COLOUR WITH FACE OUTLINE AND SHAPE
        Color skin = new Color(112, 57, 42);
        g.setColor(skin);
        g.fillOval(150, 75, 500, 500);
        
        
        // EARS
        g.fillOval(625, 275, 50, 100);
        g.fillOval(125, 275, 50, 100);
        
        
        g.setColor(Color.WHITE);
        g.fillOval(270, 232, 70, 100);
        g.fillOval(470, 232, 70, 100);
        
        // DRAWING THE NOSE WITH A BOLDER OUTLINE BY USING 2 ARCS
        g.setColor(Color.BLACK);
        g.drawArc(375, 350, 50, 25, 180, 180);
        g.drawArc(375, 350, 49, 24, 180, 180);
        
        // EYEBROWS
        int[] xPoints = {440, 520, 555, 450};
        int[] yPoints = {220, 220, 230, 230};
        // x positions, y positions, how many points
        g.fillPolygon(xPoints, yPoints, 4);
        int[] x2Points = {360, 270, 240, 340};
        int[] y2Points = {220, 220, 230, 230};
        // x positions, y positions, how many points
        g.fillPolygon(x2Points, y2Points, 4);
       
        
        // DRAWING MOUTH WITH NEW COLOUR
        Color mouth = new Color(196, 22, 22);
        g.setColor(mouth);
        g.fillArc(350, 400, 110, 70, 180, 180);
        
        // DRAWING TONGUE
        Color tongue = new Color(224, 63, 98);
        g.setColor(tongue);
        g.fillOval(365, 440, 80, tongueX);
        
        // DRAWING IRIS
        Color iris = new Color(204, 150, 51);
        g.setColor(iris);
        g.fillOval(276, 250, 55, 60);
        g.fillOval(476, 250, 55, 60);
        
        // DRAWS THE PUPIL
        g.setColor(Color.BLACK);
        g.fillOval(287, 265, 30, 30);
        g.fillOval(489, 265, 30, 30);
        
        // DRAWS THE EYELIDS
        Color eyelid = new Color(158, 80, 41);
        g.setColor(eyelid);
        g.fillOval(270, eyelid1, 70, 100);
        g.fillOval(470, eyelid1, 70, 100);
        
        

        
        
        
        
        
        
        
        
        

        // GAME DRAWING ENDS HERE
    }


    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void  preSetup(){
       // Any of your pre setup before the loop starts should go here

    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;

        preSetup();

        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            
            // CAUSES TONGUE TO GO UP AND DOWN
            if (tongueX < 30) {
                tongueDirection = 1;
            }
            if (tongueX > 80) {
                tongueDirection = -1;
            }

            tongueX = tongueX + tongueDirection * 1;
            
            // blink timer (in dev)
            if(startTime > nextBlink){
                nextBlink = startTime + blinkTimeDelay;
                System.out.println("Tick: " + startTime);
                
            }
           
                
                
            
            

            
            
            
            
            
            
            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        A8Q1 game = new A8Q1();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        
        // add listeners for keyboard and mouse
        frame.addKeyListener(new Keyboard());
        game.addMouseListener(new Mouse());
        
        // starts the game loop
        game.run();
    }

    // Used to implement any of the Mouse Actions
    private static class Mouse extends MouseAdapter {
        // if a mouse button has been pressed down
        @Override
        public void mousePressed(MouseEvent e){
            
        }
        
        // if a mouse button has been released
        @Override
        public void mouseReleased(MouseEvent e){
            
        }
        
        // if the mouse has moved positions
        @Override
        public void mouseMoved(MouseEvent e){
            
        }
    }
    
    // Used to implements any of the Keyboard Actions
    private static class Keyboard extends KeyAdapter{
        // if a key has been pressed down
        @Override
        public void keyPressed(KeyEvent e){
            
        }
        
        // if a key has been released
        @Override
        public void keyReleased(KeyEvent e){
            
        }
    }
}