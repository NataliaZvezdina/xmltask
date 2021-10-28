package by.zvezdina.xmltask.entity;

import java.util.Objects;

public class GrowingTip {

    private int temperature;
    private boolean light;
    private int watering;

    public GrowingTip() {}

    public GrowingTip(int temperature, boolean light, int watering) {
        this.temperature = temperature;
        this.light = light;
        this.watering = watering;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrowingTip that = (GrowingTip) o;
        return temperature == that.temperature && light == that.light && watering == that.watering;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, light, watering);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GrowingTip{");
        sb.append("temperature=").append(temperature);
        sb.append(", light=").append(light);
        sb.append(", watering=").append(watering);
        sb.append('}');
        return sb.toString();
    }
}
