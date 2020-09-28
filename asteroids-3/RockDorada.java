import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class rockDorada here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RockDorada extends SmoothMover
{
    private int points = 25;
    private int stability;
    private int size;
    
    public RockDorada()
    {
        this(50);
    }
    
    /**
     * Create an asteroid with a given size and random direction of movement.
     */
    public RockDorada(int size)
    {
        this(size, new Vector(size, Greenfoot.getRandomNumber(5) + 5));
    }
    
    public RockDorada(int size, Vector velocity)
    {
        super(velocity);
        setSize(size);
    }
    
    public void act() {
        move();
    }
    
    public void setSize(int size) 
    {
        stability = size;
        this.size = size;
        GreenfootImage image = getImage();
        image.scale(size, size);
    }
    
    public void hit(int damage) 
    {
        stability = stability - damage;
        if (stability <= 0) 
        {
            breakUp();
        }
    }
    
    private void breakUp() 
    {
        Greenfoot.playSound("Explosion.wav");
        Space space = (Space) getWorld();
        space.addPoints(this.points);
        getWorld().removeObject(this);
    }
}
