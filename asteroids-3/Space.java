import greenfoot.*;

/**
 * Space. Something for rockets to fly in.
 * 
 * @author Michael Kölling
 * @version 1.2
 */
public class Space extends World
{
    private Counter scoreCounter;
    private int startAsteroids = 3;
    public int points;

    /**
     * Create the space and all objects within it.
     */
    public Space() 
    {
        super(600, 500, 1);
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
        createStars(300);
        
        Rocket rocket = new Rocket();
        addObject(rocket, getWidth()/2 + 100, getHeight()/2);
        Rocket2 rocket2 = new Rocket2();
        addObject(rocket2, getWidth()/2 + 200, getHeight()/2);
        
        addAsteroids(startAsteroids);
        
        scoreCounter = new Counter("Score: ");
        addObject(scoreCounter, 60, 480);

        Explosion.initializeImages();
        ProtonWave.initializeImages();
    }
    
    /**
     * Add a given number of asteroids to our world. Asteroids are only added into
     * the left half of the world.
     */
    private void addAsteroids(int count) 
    {
        for(int i = 0; i < count; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth()/2);
            int y = Greenfoot.getRandomNumber(getHeight()/2);
            addObject(new Asteroid(), x, y);
        }
        int x = Greenfoot.getRandomNumber(getWidth()/2);
        int y = Greenfoot.getRandomNumber(getHeight()/2);
        addObject(new RockDorada(), x, y);
    }

    /**
     * Crete a given number of stars in space.
     */
    private void createStars(int number) 
    {
        GreenfootImage background = getBackground();             
        for(int i=0; i < number; i++) 
        {
             int x = Greenfoot.getRandomNumber( getWidth() );
             int y = Greenfoot.getRandomNumber( getHeight() );
             int color = 120 - Greenfoot.getRandomNumber(100);
             background.setColor(new Color(color,color,color));
             background.fillOval(x, y, 2, 2);
        }
    }
    
    public void addPoints(int pointsAdd) {
        scoreCounter.add(pointsAdd);
    }
    
    public void removeAsteroids() {
        removeObjects (getObjects(Asteroid.class));
        removeObjects (getObjects(RockDorada.class));
    }
    
    public void resetGame() {
        removeAsteroids();
        for (Rocket jugador1: getObjects(Rocket.class)) {
            jugador1.setLocation(450, 450);
            jugador1.accelerate(0);
            int vidasj1 = jugador1.lives;
            String vidas1 = "Jugador 1: " + Integer.toString(vidasj1) + " vidas.";
            showText(vidas1,225,475);
        }
        for (Rocket2 jugador2: getObjects(Rocket2.class)) {
            jugador2.setLocation(450, 475);
            jugador2.accelerate(0);
            int vidasj2 = jugador2.lives;
            String vidas2 = "Jugador 2: " + Integer.toString(vidasj2) + " vidas.";
            showText(vidas2,475,475);
        }
        addAsteroids(3);
    }
    
    /**
     * This method is called when the game is over to display the final score.
     */
    public void gameOver() 
    {
        addObject(new ScoreBoard(999), getWidth()/2, getHeight()/2);
    }
}