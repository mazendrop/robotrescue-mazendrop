package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

public class DroneEnemy extends Enemy {

    public DroneEnemy(String name, int lifePoints) {
        super(name, lifePoints);
    }

    @Override
    public void fight(Robot robot) {
        int damage = 10;
        System.out.println(name + " greift den Roboter an!");
        robot.takeDamage(damage);
    }
}
