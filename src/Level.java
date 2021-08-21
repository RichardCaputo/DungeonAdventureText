public class Level {

    private int floor;
    private String subBoss;

    Level(int floor, String subBoss) {
        this.floor = floor;
        this.subBoss = subBoss;
    }

    public int getFloor() { return floor; }

    public String getSubBoss() { return subBoss; }

    public int floorUp() { return floor++; }

    public int floorDown() { return floor--; }

}