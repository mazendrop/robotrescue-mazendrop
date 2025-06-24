package model;

/*
 * @author Mazen Abdallah
 * @author Hassan Ibrahim
 */

public abstract class Enemy {
    protected String name;
    protected int lifePoints;

    public Enemy(String name, int lifePoints) {
        this.name = name;
        this.lifePoints = lifePoints;
    }

    public void takeDamage(int amount) {
        lifePoints -= amount;
        if (lifePoints < 0) lifePoints = 0;
        System.out.println(name + " hat " + amount + " Schaden erlitten.");
        System.out.println(name + " hat noch " + lifePoints + " Lebenspunkte.");
    }

    public boolean isDefeated() {
        return lifePoints <= 0;
    }

    public abstract void fight(Robot robot);
}
