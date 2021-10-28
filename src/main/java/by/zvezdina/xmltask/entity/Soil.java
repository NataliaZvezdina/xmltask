package by.zvezdina.xmltask.entity;

public enum Soil {

    PODZOLIC("podzolic"),
    SOD_PODZOLIC("sod-podzolic"),
    GLEY("gley"),
    GROUND("ground");

    private String name;

    Soil(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
