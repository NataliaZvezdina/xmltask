package by.zvezdina.xmltask.entity;


import java.util.Objects;

public class VisualParameter {

    private Color stemColor;
    private Color leafColor;
    private int averageSize;

    public VisualParameter() {}

    public VisualParameter(Color stemColor, Color leafColor, int averageSize) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.averageSize = averageSize;
    }

    public Color getStemColor() {
        return stemColor;
    }

    public void setStemColor(Color stemColor) {
        this.stemColor = stemColor;
    }

    public Color getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(Color leafColor) {
        this.leafColor = leafColor;
    }

    public int getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(int averageSize) {
        this.averageSize = averageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualParameter that = (VisualParameter) o;
        return averageSize == that.averageSize && stemColor == that.stemColor && leafColor == that.leafColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stemColor, leafColor, averageSize);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VisualParameter{");
        sb.append("stemColor=").append(stemColor);
        sb.append(", leafColor=").append(leafColor);
        sb.append(", averageSize=").append(averageSize);
        sb.append('}');
        return sb.toString();
    }
}
