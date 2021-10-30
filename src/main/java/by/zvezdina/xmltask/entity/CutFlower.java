package by.zvezdina.xmltask.entity;

import java.time.LocalDate;
import java.util.Objects;

public class CutFlower extends Flower {

    private int stemLength;
    private LocalDate cutDate;
    private boolean decorated;

    public CutFlower() {}

    public CutFlower(String id, String name, Soil soil, Origin origin, VisualParameter visualParameter,
                     GrowingTip growingTip, Multiplying multiplying, int stemLength, LocalDate cutDate, boolean decorated) {
        super(id, name, soil, origin, visualParameter, growingTip, multiplying);
        this.stemLength = stemLength;
        this.cutDate = cutDate;
        this.decorated = decorated;
    }

    public int getStemLength() {
        return stemLength;
    }

    public void setStemLength(int stemLength) {
        this.stemLength = stemLength;
    }

    public LocalDate getCutDate() {
        return cutDate;
    }

    public void setCutDate(LocalDate cutDate) {
        this.cutDate = cutDate;
    }

    public boolean isDecorated() {
        return decorated;
    }

    public void setDecorated(boolean decorated) {
        this.decorated = decorated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CutFlower cutFlower = (CutFlower) o;

        if (stemLength != cutFlower.stemLength) return false;
        if (decorated != cutFlower.decorated) return false;
        return cutDate != null ? cutDate.equals(cutFlower.cutDate) : cutFlower.cutDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + stemLength;
        result = 31 * result + (cutDate != null ? cutDate.hashCode() : 0);
        result = 31 * result + (decorated ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nFlower: cut flower");
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
        sb.append("\nCut date: ").append(getCutDate());
        sb.append("\nDecorated: ").append(isDecorated());
        sb.append("\nStem length: ").append(getStemLength());

        return sb.toString();
    }
}
