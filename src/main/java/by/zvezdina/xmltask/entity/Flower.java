package by.zvezdina.xmltask.entity;

import java.util.Objects;

public abstract class Flower {

    private String id;
    private String name;
    private Soil soil;
    private Origin origin;
    private VisualParameter visualParameter = new VisualParameter();
    private GrowingTip growingTip = new GrowingTip();
    private Multiplying multiplying;

    public Flower() {}

    public Flower(String id, String name, Soil soil, Origin origin, VisualParameter visualParameter,
                  GrowingTip growingTip, Multiplying multiplying) {
        this.id = id;
        this.name = name;
        this.soil = soil;
        this.origin = origin;
        this.visualParameter = visualParameter;
        this.growingTip = growingTip;
        this.multiplying = multiplying;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public VisualParameter getVisualParameter() {
        return visualParameter;
    }

    public void setVisualParameter(VisualParameter visualParameter) {
        this.visualParameter = visualParameter;
    }

    public GrowingTip getGrowingTip() {
        return growingTip;
    }

    public void setGrowingTip(GrowingTip growingTip) {
        this.growingTip = growingTip;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        return id.equals(flower.id) &&
                name.equals(flower.name) &&
                soil == flower.soil &&
                origin == flower.origin &&
                visualParameter.equals(flower.visualParameter) &&
                growingTip.equals(flower.growingTip) &&
                multiplying == flower.multiplying;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, soil, origin, visualParameter, growingTip, multiplying);
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", soil=" + soil +
                ", origin=" + origin +
                ", visualParameter=" + visualParameter +
                ", growingTip=" + growingTip +
                ", multiplying=" + multiplying +
                '}';
    }
}
