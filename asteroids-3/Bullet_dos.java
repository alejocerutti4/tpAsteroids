import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class bullet_dos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet_dos extends SmoothMover
{
    /** The damage this bullet will deal */
    private static final int damage = 16;
    
    /** A bullet looses one life each act, and will disappear when life = 0 */
    private int life = 30;
    
    public Bullet_dos() {
        
    }
    
    public Bullet_dos(Vector speed, int rotation)
    {
        super(speed);
        setRotation(rotation);
        addToVelocity(new Vector(rotation, 15));
        Greenfoot.playSound("EnergyGun.wav");
    }
    
    /**
     * Act - do whatever the bullet_dos wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(life <= 0) {
            getWorld().removeObject(this);
        } 
        else {
            life--;
            move();
            checkAsteroidHit();
        }
    }
    
    private void checkAsteroidHit()
    {
        Asteroid asteroid = (Asteroid) getOneIntersectingObject(Asteroid.class);
        RockDorada rockDorada = (RockDorada) getOneIntersectingObject(RockDorada.class);
        if (asteroid != null) 
        {
            getWorld().removeObject(this);
            asteroid.hit(damage);
        }else if (rockDorada != null) 
        {
            getWorld().removeObject(this);
            rockDorada.hit(damage);
        }
    }
}
