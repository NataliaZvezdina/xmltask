package by.zvezdina.xmltask.entity;

public enum Color {

    WHITE("white"),
    RED("red"),
    YELLOW("yellow"),
    GREEN("green"),
    CYAN("cyan"),
    BLUE("blue"),
    MAGENTA("magenta"),
    ALTERNATIVE("alternative");

    private String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
