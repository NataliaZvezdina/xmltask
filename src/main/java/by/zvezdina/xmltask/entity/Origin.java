package by.zvezdina.xmltask.entity;

public enum Origin {

    ASIA("Asia"),
    AFRICA("Africa"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    SOUTH_AMERICA("South America"),
    AUSTRALIA("Australia");

    private String name;

    Origin(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
