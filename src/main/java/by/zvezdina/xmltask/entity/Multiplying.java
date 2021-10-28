package by.zvezdina.xmltask.entity;

public enum Multiplying {

    LEAF("leaf"),
    CUTTING("cutting"),
    SEED("seed");

    private String name;

    Multiplying(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
