import greenfoot.*;

public class Rocket2 extends SmoothMover
{
    private static final int gunReloadTime = 5;         // The minimum delay between firing the gun.
    private static final int protonReloadTime = 500;    // The minimum delay between proton wave bursts.

    private int reloadDelayCount;               // How long ago we fired the gun the last time.
    private int protonDelayCount;               // How long ago we fired the proton wave the last time.
    private boolean isTeleporting = false;
    public int lives;
    
    
    private GreenfootImage rocket = new GreenfootImage("rocket2.png");    
    private GreenfootImage rocketWithThrust = new GreenfootImage("rocketWithThrust2.png");
    private GreenfootImage rocketSlowingDown = new GreenfootImage("rocketSlowingDown2.png");

    /**
     * Initialise this rocket.
     */
    public Rocket2()
    {
        reloadDelayCount = 5;
        protonDelayCount = 500;
        addToVelocity(new Vector(13, 0.7));    // initially slowly drifting
        lives = 3;
    }

    /**
     * Do what a rocket's gotta do. (Which is: mostly flying about, and turning,
     * accelerating and shooting when the right keys are pressed.)
     */
    public void act()
    {
        move();
        checkKeys();
        checkCollision();
        reloadDelayCount++;
        protonDelayCount++;
    }
    
    /**
     * Check whether there are any key pressed and react to them.
     */
    private void checkKeys() 
    {
        ignite(Greenfoot.isKeyDown("w"), Greenfoot.isKeyDown("s"));
        desacelerate(Greenfoot.isKeyDown("s"), Greenfoot.isKeyDown("w"));
        teleport(Greenfoot.isKeyDown("v"));
        if (Greenfoot.isKeyDown("a")) 
        {
            turn(-5);
        }
        if (Greenfoot.isKeyDown("d")) 
        {
            turn(5);
        }
        if (Greenfoot.isKeyDown("x")) 
        {
            fire();
        }
        if (Greenfoot.isKeyDown("z")) 
        {
            startProtonWave();
        }
    }
    
    /**
     * Check whether we are colliding with an asteroid.
     */
    private void checkCollision() 
    {
        Asteroid a = (Asteroid) getOneIntersectingObject(Asteroid.class);
        RockDorada r = (RockDorada) getOneIntersectingObject(RockDorada.class);
        if (a != null || r!=null) 
        {
            Space space = (Space) getWorld();
            space.addObject(new Explosion(), getX(), getY());
            if (lives > 1) {
                lives--;
                space.resetGame();
            } else {
            space.removeObject(this);
            space.gameOver();
        }
        }
    }
    
    /**
     * Should the rocket be ignited?
     */
    private void ignite(boolean boosterOn, boolean frenandoOn) 
    {
        if (boosterOn) {
            setImage(rocketWithThrust);
            addToVelocity(new Vector(getRotation(), 0.3));
        } else if (!frenandoOn){
            setImage(rocket);
        }
    }
    
    private void desacelerate(boolean frenandoOn, boolean boosterOn) {
        if (frenandoOn) {
            setImage(rocketSlowingDown);
            addToVelocity(new Vector(getRotation(), -0.3));
        } else if (!boosterOn) {
            setImage(rocket);
        }
    }
    
    private void teleport(boolean teleportOn) {
        if (teleportOn && !isTeleporting) {
            this.setLocation(600 - getX(), 500 - getY());
            isTeleporting = true;
        } else if (!teleportOn) {
            isTeleporting = false;
        }
    }
    
    /**
     * Fire a bullet if the gun is ready.
     */
    private void fire() 
    {
        if (reloadDelayCount >= gunReloadTime) 
        {
            Bullet_dos bullet = new Bullet_dos (getVelocity(), getRotation());
            getWorld().addObject (bullet, getX(), getY());
            bullet.move ();
            reloadDelayCount = 0;
        }
    }
    
    /**
     * Release a proton wave (if it is loaded).
     */
    private void startProtonWave() 
    {
        if (protonDelayCount >= protonReloadTime) 
        {
            ProtonWave wave = new ProtonWave();
            getWorld().addObject (wave, getX(), getY());
            protonDelayCount = 0;
        }
    }

}