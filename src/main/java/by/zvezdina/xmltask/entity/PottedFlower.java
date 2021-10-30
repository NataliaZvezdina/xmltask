package by.zvezdina.xmltask.entity;

import java.time.LocalDate;

public class PottedFlower extends Flower {

    private LocalDate plantingDate;

    public PottedFlower() {}

    public PottedFlower(String id, String name, Soil soil, Origin origin, VisualParameter visualParameter,
                        GrowingTip growingTip, Multiplying multiplying, LocalDate plantingDate) {
        super(id, name, soil, origin, visualParameter, growingTip, multiplying);
        this.plantingDate = plantingDate;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PottedFlower that = (PottedFlower) o;

        return plantingDate != null ? plantingDate.equals(that.plantingDate) : that.plantingDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (plantingDate != null ? plantingDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nFlower: potted flower");
        sb.append("\nName: ").append(getName());
        sb.append("\nId: ").append(getId());
        sb.append("\nOrigin: ").append(getOrigin());
        sb.append("\nSoil: ").append(getSoil());
        sb.append("\nVisual parameters: \n\tStem color: ").append(getVisualParameter().getStemColor());
        sb.append("\n\tLeaf color: ").append(getVisualParameter().getLeafColor());
        sb.append("\n\tAverage size: ").append(getVisualParameter().getAverageSize());
        sb.append("\nGrowing tips: \n\tTemperature: ").append(getGrowingTip().getTemperature());
        sb.append("\n\tLight: ").append(getGrowingTip().isLight());
        sb.append("\n\tWatering: ").append(getGrowingTip().getWatering());
        sb.append("\nMultiplying: ").append(getMultiplying());
        sb.append("\nPlanting date: ").append(getPlantingDate());

        return sb.toString();
    }
}
