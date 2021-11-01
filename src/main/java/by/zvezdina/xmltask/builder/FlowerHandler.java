package by.zvezdina.xmltask.builder;

import by.zvezdina.xmltask.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class FlowerHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();
    private Set<Flower> flowers;
    private Flower current;
    private CutFlower currentCutFlower;
    private PottedFlower currentPottedFlower;
    private FlowerXmlTag currentXmlTag;
    private EnumSet<FlowerXmlTag> withText;
    private static final String ELEMENT_CUT_FLOWER = "cut-flower";
    private static final String ELEMENT_POTTED_FLOWER = "potted-flower";
    private static final String CHAR_TO_REPLACE = "[-\\s]";
    private static final String NEW_CHAR = "_";

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
            current = new CutFlower();
            String id = attributes.getValue(FlowerXmlTag.ID.toString());
            current.setFlowerId(id);

            String decoratedValue = attributes.getValue(FlowerXmlTag.DECORATED.toString());
            if (decoratedValue != null) {
                boolean decorated = Boolean.parseBoolean(decoratedValue);
                CutFlower currentCutFlower = (CutFlower) current;
                currentCutFlower.setDecorated(decorated);
            }
        } else if (ELEMENT_POTTED_FLOWER.equals(qName)) {
            current = new PottedFlower();
            String id = attributes.getValue(FlowerXmlTag.ID.toString());
            current.setFlowerId(id);
        } else {
            FlowerXmlTag temp = FlowerXmlTag.valueOf(qName.toUpperCase().replaceAll(CHAR_TO_REPLACE, NEW_CHAR));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (ELEMENT_POTTED_FLOWER.equals(qName) || ELEMENT_CUT_FLOWER.equals(qName)) {
            logger.log(Level.INFO, "Built flower: " + current);
            flowers.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME -> current.setName(data);
                case ORIGIN -> current.setOrigin(Origin.valueOf(convertToXMLTag(data)));
                case SOIL -> current.setSoil(Soil.valueOf(convertToXMLTag(data)));
                case STEM_COLOR -> current.getVisualParameter().setStemColor(Color.valueOf(convertToXMLTag(data)));
                case LEAF_COLOR -> current.getVisualParameter().setLeafColor(Color.valueOf(convertToXMLTag(data)));
                case AVERAGE_SIZE -> current.getVisualParameter().setAverageSize(Integer.parseInt(data));
                case TEMPERATURE -> current.getGrowingTip().setTemperature(Integer.parseInt(data));
                case LIGHT -> current.getGrowingTip().setLight(Boolean.parseBoolean(data));
                case WATERING -> current.getGrowingTip().setWatering(Integer.parseInt(data));
                case MULTIPLYING -> current.setMultiplying(Multiplying.valueOf(convertToXMLTag(data)));
                case STEM_LENGTH -> {
                    CutFlower currentCutFlower = (CutFlower) current;
                    currentCutFlower.setStemLength(Integer.parseInt(data));
                }
                case CUT_DATE -> {
                    CutFlower currentCutFlower = (CutFlower) current;
                    currentCutFlower.setCutDate(LocalDate.parse(data));
                }
                case PLANTING_DATE -> {
                    PottedFlower currentPottedFlower = (PottedFlower) current;
                    currentPottedFlower.setPlantingDate(LocalDate.parse(data));
                }
                default -> throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }

    private String convertToXMLTag(String name) {
        return name.toUpperCase()
                .replaceAll(CHAR_TO_REPLACE, NEW_CHAR);
    }
}
