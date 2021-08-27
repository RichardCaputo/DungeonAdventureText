package org.example;

import java.util.Random;

public class Enemy {

    Random rand = new Random();
    private String type;
    private String weapon;
    private int level;
    private int enemyHealth;
    private int enemyAttackDamage;
    private int maxAttackDamage;
    private boolean angry;
    private boolean scared;
    private boolean isBlocking;
    private int startingHP;

    Enemy(String type, String weapon, int level, int enemyHealth, int enemyAttackDamage, boolean angry, boolean scared, boolean isBlocking) {
        this.type = type;
        this.weapon = weapon;
        this.level = level;
        this.enemyHealth = getRandomInt(5, enemyHealth);
        this.startingHP = this.enemyHealth;
        this.enemyAttackDamage = rand.nextInt(enemyAttackDamage - 1) + 2;
        this.angry = angry;
        this.scared = scared;
        this.isBlocking = isBlocking;
        this.maxAttackDamage = enemyAttackDamage; // Max per iteration
    }

    /**
     * Returns an int between minBound (inclusive) maxBound (inclusive).
     *
     * @param minBound the minimum number that will be returned.
     * @param maxBound the maximum number that will be returned.
     * @return an integer between maxBound and minBound.
     */
    protected int getRandomInt(int minBound, int maxBound) {
        return rand.nextInt(maxBound) + minBound;
    }

    public int getStartingHP() {
        return startingHP;
    }

    public int setStartingHP() {
        return startingHP += (startingHP / 4);
    }

    public String getType() {
        return type;
    }

    public String getWeapon() {
        return weapon;
    }

    public int getLevel() {
        return this.level;
    }

    public void incrementLevel() {
        this.level++;
        this.enemyAttackDamage += (this.enemyAttackDamage / 10);
    }

    public void decrementLevel() {
        if (level > 0) {
        this.level--;

        }
    }

    public int getEnemyHealth() {
        return this.enemyHealth;
    }

    public int setEnemyHealth(int healthSet) {
        return this.enemyHealth = healthSet;
    }

    public int decreaseEnemyHealth(int attackDamage) {
        return this.enemyHealth -= attackDamage;
    }

    public int getEnemyAttackDamage() {
        enemyAttackDamage = rand.nextInt(maxAttackDamage);
        return this.enemyAttackDamage;
    }

    public int setEnemyAttackDamage() {
        return enemyAttackDamage;
    }

    public boolean getEnemyAttack() {
        return rand.nextBoolean();
    }

    public void setAngry() {
        this.angry = true;
    }

    public void setScared() {
        this.scared = true;
    }

    public boolean getIsBlocking() {
        return this.isBlocking;
    }

    public boolean setIsBlocking() {
        return this.isBlocking = rand.nextBoolean();
    }

}
