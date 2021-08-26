import java.util.Random;

public class Level {

    private int floor;
    private String subBoss;
    private String weapon;
    private int subBossHealth;
    private int maxSubBossHealth;
    private int attackDamage;
    private boolean successfulAttack;
    private boolean isBlocking;

    Level(int floor, String subBoss, String weapon, int subBossHealth, int attackDamage,
          boolean successfulAttack, boolean isBlocking) {
        this.floor = floor;
        this.subBoss = subBoss;
        this.weapon = weapon;
        this.subBossHealth = subBossHealth;
        this.maxSubBossHealth = this.subBossHealth;
        this.attackDamage = attackDamage;
        this.successfulAttack = successfulAttack;
        this.isBlocking = isBlocking;
    }

    public int getFloor() { return floor; }
    public String getSubBoss() { return subBoss; }
    public String getWeapon() { return weapon; }
    public int floorUp() { return floor++; }
    public int floorDown() { return floor--; }
    public int getSubBossHealth() { return subBossHealth; }
    public int setSubBossHealth(int playerAttackDamage) { return subBossHealth -= playerAttackDamage; }
    public int getMaxSubBossHealth() { return maxSubBossHealth; }
    public int getAttackDamage() { return attackDamage; }
    public boolean getSuccessfulAttack() { return successfulAttack; }
    public boolean setSuccessfulAttack() { Random rand = new Random(); return successfulAttack = rand.nextBoolean(); }
    public boolean getIsBlocking() {return isBlocking;}
    public boolean setIsBlocking() {Random rand = new Random(); return isBlocking = rand.nextBoolean();}

}