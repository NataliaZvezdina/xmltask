package by.zvezdina.xmltask.entity;

import java.time.LocalDate;
import java.util.Objects;

public class PottedFlower extends Flower {

    private LocalDate datePlanted;

    public PottedFlower() {}

    public PottedFlower(String id, String name, Soil soil, Origin origin, VisualParameter visualParameter,
                        GrowingTip growingTip, Multiplying multiplying, LocalDate datePlanted) {
        super(id, name, soil, origin, visualParameter, growingTip, multiplying);
        this.datePlanted = datePlanted;
    }

    public LocalDate getDatePlanted() {
        return datePlanted;
    }

    public void setDatePlanted(LocalDate datePlanted) {
        this.datePlanted = datePlanted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PottedFlower that = (PottedFlower) o;
        return Objects.equals(datePlanted, that.datePlanted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), datePlanted);
    }

    @Override
    public String toString() {
        return "PottedFlower{" +
                "datePlanted=" + datePlanted +
                "} " + super.toString();
    }
}
