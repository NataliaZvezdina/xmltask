package by.zvezdina.xmltask.entity;

import java.util.Objects;

public abstract class Flower {

    private String flowerId;
    private String name;
    private Soil soil;
    private Origin origin;
    private VisualParameter visualParameter = new VisualParameter();
    private GrowingTip growingTip = new GrowingTip();
    private Multiplying multiplying;

    public Flower() {}

    public Flower(String flowerId, String name, Soil soil, Origin origin, VisualParameter visualParameter,
                  GrowingTip growingTip, Multiplying multiplying) {
        this.flowerId = flowerId;
        this.name = name;
        this.soil = soil;
        this.origin = origin;
        this.visualParameter = visualParameter;
        this.growingTip = growingTip;
        this.multiplying = multiplying;
    }

    public String getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(String flowerId) {
        this.flowerId = flowerId;
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

        if (flowerId != null ? !flowerId.equals(flower.flowerId) : flower.flowerId != null) return false;
        if (name != null ? !name.equals(flower.name) : flower.name != null) return false;
        if (soil != flower.soil) return false;
        if (origin != flower.origin) return false;
        if (visualParameter != null ? !visualParameter.equals(flower.visualParameter) : flower.visualParameter != null)
            return false;
        if (growingTip != null ? !growingTip.equals(flower.growingTip) : flower.growingTip != null) return false;
        return multiplying == flower.multiplying;
    }

    @Override
    public int hashCode() {
        int result = flowerId != null ? flowerId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (soil != null ? soil.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (visualParameter != null ? visualParameter.hashCode() : 0);
        result = 31 * result + (growingTip != null ? growingTip.hashCode() : 0);
        result = 31 * result + (multiplying != null ? multiplying.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id='" + flowerId + '\'' +
                ", name='" + name + '\'' +
                ", soil=" + soil +
                ", origin=" + origin +
                ", visualParameter=" + visualParameter +
                ", growingTip=" + growingTip +
                ", multiplying=" + multiplying +
                '}';
    }
}
