package by.zvezdina.xmltask.builder;

import by.zvezdina.xmltask.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class FlowerHandler extends DefaultHandler {

    private Set<Flower> flowers;
    private Flower current;
    private CutFlower currentCutFlower;
    private PottedFlower currentPottedFlower;
    private FlowerXmlTag currentXmlTag;
    private EnumSet<FlowerXmlTag> withText;
    private static final String ELEMENT_CUT_FLOWER = "cut-flower";
    private static final String ELEMENT_POTTED_FLOWER = "potted-flower";

    public FlowerHandler() {
        flowers = new HashSet<>();
        withText = EnumSet.range(FlowerXmlTag.NAME, FlowerXmlTag.WATERING);
    }

    public Set<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (ELEMENT_CUT_FLOWER.equals(qName)) {
            currentCutFlower = new CutFlower();
            String id = attributes.getValue(FlowerXmlTag.ID.toString());
            currentCutFlower.setId(id);

            String decoratedValue = attributes.getValue(FlowerXmlTag.DECORATED.toString());
            if (decoratedValue != null) {
                boolean decorated = Boolean.parseBoolean(decoratedValue);
                //CutFlower cutFlower = (CutFlower) current;
                currentCutFlower.setDecorated(decorated);
            }

        } else {
            FlowerXmlTag temp = FlowerXmlTag.valueOf(qName.toUpperCase().replaceAll("-", "_"));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (ELEMENT_CUT_FLOWER.equals(qName)) {
            flowers.add(currentCutFlower);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME -> currentCutFlower.setName(data);
                case SOIL -> currentCutFlower.setSoil(Soil.valueOf(data.toUpperCase().replaceAll("-", "_")));
                case ORIGIN -> currentCutFlower.setOrigin(Origin.valueOf(data.toUpperCase().replaceAll("-", "_")));
                case STEM_COLOR -> currentCutFlower.getVisualParameter().setStemColor(Color.valueOf(data.toUpperCase().replaceAll("-", "_")));
                case LEAF_COLOR -> currentCutFlower.getVisualParameter().setLeafColor(Color.valueOf(data.toUpperCase().replaceAll("-", "_")));
                case AVERAGE_SIZE -> currentCutFlower.getVisualParameter().setAverageSize(Integer.parseInt(data));
                case TEMPERATURE -> currentCutFlower.getGrowingTip().setTemperature(Integer.parseInt(data));
                case LIGHT -> currentCutFlower.getGrowingTip().setLight(Boolean.parseBoolean(data));
                case WATERING -> currentCutFlower.getGrowingTip().setWatering(Integer.parseInt(data));
                case MULTIPLYING -> currentCutFlower.setMultiplying(Multiplying.valueOf(data.toUpperCase().replaceAll("-", "_")));
                case STEM_LENGTH -> {
                   // CutFlower cutFlower = (CutFlower) current;
                    currentCutFlower.setStemLength(Integer.parseInt(data));
                }
                case DATE_CUT -> {
                    //CutFlower cutFlower = (CutFlower) current;
                    currentCutFlower.setDateCut(LocalDate.parse(data));
                }
                case DATE_PLANTED -> {
                    PottedFlower pottedFlower = (PottedFlower) current;
                    pottedFlower.setDatePlanted(LocalDate.parse(data));

                }
                default -> throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}