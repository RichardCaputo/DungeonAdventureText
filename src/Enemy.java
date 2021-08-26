import java.util.Random;

public class Enemy {

    Random rand = new Random();
    private String type;
    private String weapon;
    private int level;
    private int enemyHealth;
    private int enemyAttackDamage;
    private boolean enemyAttack;
    private boolean angry;
    private boolean scared;
    private boolean isBlocking;
    private int startingHP;

    Enemy(String type, String weapon, int level, int enemyHealth, int enemyAttackDamage,
          boolean enemyAttack, boolean angry, boolean scared, boolean isBlocking) {
        this.type = type;
        this.weapon = weapon;
        this.level = level;
        this.enemyHealth = rand.nextInt(enemyHealth - 4) + 5;
        this.startingHP = this.enemyHealth;
        this.enemyAttackDamage = rand.nextInt(enemyAttackDamage);
        this.enemyAttack = enemyAttack;
        this.angry = angry;
        this.scared = scared;
        this.isBlocking = isBlocking;
    }

    public int getStartingHP() {
        return startingHP;
    }
    public int setStartingHP() { return startingHP += (startingHP / 4); }
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
        return this.enemyAttackDamage;
    }
    public int setEnemyAttackDamage() { return enemyAttackDamage; }
    public boolean setEnemyAttack() {
        return this.enemyAttack = rand.nextBoolean();
    }
    public boolean getEnemyAttack() {
        return this.enemyAttack;
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
