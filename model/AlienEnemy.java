package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

public class AlienEnemy extends Enemy {

    public AlienEnemy(String name, int lifePoints) {
        super(name, lifePoints);
    }

    @Override
    public void fight(Robot robot) {
        int energyDrain = 15;
        System.out.println(name + " entzieht dem Roboter Energie!");
        robot.drainEnergy(energyDrain);
    }
}
