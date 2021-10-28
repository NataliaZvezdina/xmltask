package by.zvezdina.xmltask.entity;

import java.time.LocalDate;
import java.util.Objects;

public class CutFlower extends Flower {

    private int stemLength;
    private LocalDate dateCut;
    private boolean decorated;

    public CutFlower() {}

    public CutFlower(String id, String name, Soil soil, Origin origin, VisualParameter visualParameter,
                     GrowingTip growingTip, Multiplying multiplying, int stemLength, LocalDate dateCut, boolean decorated) {
        super(id, name, soil, origin, visualParameter, growingTip, multiplying);
        this.stemLength = stemLength;
        this.dateCut = dateCut;
        this.decorated = decorated;
    }

    public int getStemLength() {
        return stemLength;
    }

    public void setStemLength(int stemLength) {
        this.stemLength = stemLength;
    }

    public LocalDate getDateCut() {
        return dateCut;
    }

    public void setDateCut(LocalDate dateCut) {
        this.dateCut = dateCut;
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
        return stemLength == cutFlower.stemLength && decorated == cutFlower.decorated && dateCut.equals(cutFlower.dateCut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stemLength, dateCut, decorated);
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
        sb.append("\nCut date: ").append(dateCut);
        sb.append("\nDecorated: ").append(decorated);
        sb.append("\nStem length: ").append(stemLength);

        return sb.toString();
    }
}
