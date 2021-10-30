package by.zvezdina.xmltask.builder;

import by.zvezdina.xmltask.entity.*;
import by.zvezdina.xmltask.exception.FlowerXmlException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.time.LocalDate;


public class StaxFlowerBuilder extends AbstractFlowerBuilder {
    private static final Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;
    private static final String CHAR_TO_REPLACE = "[-\\s]";
    private static final String NEW_CHAR = "_";

    public StaxFlowerBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetFlowers(String filename) throws FlowerXmlException {
        logger.log(Level.INFO, "Start parsing xml file " + filename);
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(FlowerXmlTag.CUT_FLOWER.toString())) {
                        CutFlower cutFlower = buildCutFlower(reader);
                        logger.log(Level.INFO, "Built cut flower: " + cutFlower);
                        flowers.add(cutFlower);
                    } else if (name.equals(FlowerXmlTag.POTTED_FLOWER.toString())) {
                        PottedFlower pottedFlower = buildPottedFlower(reader);
                        logger.log(Level.INFO, "Built potted flower: " + pottedFlower);
                        flowers.add(pottedFlower);
                    }
                }
            }
        } catch (IOException | XMLStreamException e) {
            logger.log(Level.ERROR, "Error while parsing file " + filename);
            throw new FlowerXmlException("Error while parsing file " + filename, e);
        }
    }

    private CutFlower buildCutFlower(XMLStreamReader reader) throws FlowerXmlException {
        CutFlower cutFlower = new CutFlower();
        cutFlower.setFlowerId(reader.getAttributeValue(null, FlowerXmlTag.ID.toString()));
        String attributeDecorated = reader.getAttributeValue(null, FlowerXmlTag.DECORATED.toString());
        if (attributeDecorated != null) {
            cutFlower.setDecorated(Boolean.parseBoolean(attributeDecorated));
        }

        String name;
        try {
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (FlowerXmlTag.valueOf(convertToXMLTag(name))) {
                            case NAME -> cutFlower.setName(getXMLText(reader));
                            case SOIL -> cutFlower.setSoil(Soil.valueOf(convertToXMLTag(getXMLText(reader))));
                            case ORIGIN -> cutFlower.setOrigin(Origin.valueOf(convertToXMLTag(getXMLText(reader))));
                            case VISUAL_PARAMETER -> cutFlower.setVisualParameter(getXMLVisualParameter(reader));
                            case GROWING_TIP -> cutFlower.setGrowingTip(getXMLGrowingTip(reader));
                            case MULTIPLYING -> cutFlower.setMultiplying(Multiplying.valueOf(convertToXMLTag(getXMLText(reader))));
                            case STEM_LENGTH -> cutFlower.setStemLength(Integer.parseInt(getXMLText(reader)));
                            case CUT_DATE -> cutFlower.setCutDate(LocalDate.parse(getXMLText(reader)));
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (FlowerXmlTag.valueOf(convertToXMLTag(name)) == FlowerXmlTag.CUT_FLOWER) {
                            return cutFlower;
                        }
                }
            }
        } catch (XMLStreamException e) {
            throw new FlowerXmlException("Error while parsing <cut-flower> tag", e);
        }
        throw new FlowerXmlException("Unknown element in tag <cut-flower>");
    }

    private PottedFlower buildPottedFlower(XMLStreamReader reader) throws FlowerXmlException {
        PottedFlower pottedFlower = new PottedFlower();
        pottedFlower.setFlowerId(reader.getAttributeValue(null, FlowerXmlTag.ID.toString()));
        String name;
        try {
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (FlowerXmlTag.valueOf(convertToXMLTag(name))) {
                            case NAME -> pottedFlower.setName(getXMLText(reader));
                            case SOIL -> pottedFlower.setSoil(Soil.valueOf(convertToXMLTag(getXMLText(reader))));
                            case ORIGIN -> pottedFlower.setOrigin(Origin.valueOf(convertToXMLTag(getXMLText(reader))));
                            case VISUAL_PARAMETER -> pottedFlower.setVisualParameter(getXMLVisualParameter(reader));
                            case GROWING_TIP -> pottedFlower.setGrowingTip(getXMLGrowingTip(reader));
                            case MULTIPLYING -> pottedFlower.setMultiplying(Multiplying.valueOf(convertToXMLTag(getXMLText(reader))));
                            case PLANTING_DATE -> pottedFlower.setPlantingDate(LocalDate.parse(getXMLText(reader)));
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (FlowerXmlTag.valueOf(convertToXMLTag(name)) == FlowerXmlTag.POTTED_FLOWER) {
                            return pottedFlower;
                        }
                }
            }
        } catch (XMLStreamException e) {
            logger.log(Level.ERROR, "Error while parsing <potted-flower> tag");
            throw new FlowerXmlException("Error while parsing <potted-flower> tag", e);
        }
        logger.log(Level.ERROR, "Unknown element in tag <potted-flower> tag");
        throw new FlowerXmlException("Unknown element in tag <potted-flower>");
    }

    private VisualParameter getXMLVisualParameter(XMLStreamReader reader) throws FlowerXmlException {
        VisualParameter visualParameter = new VisualParameter();
        int type = 0;
        String name;
        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (FlowerXmlTag.valueOf(convertToXMLTag(name))) {
                            case STEM_COLOR -> visualParameter.setStemColor(Color.valueOf(convertToXMLTag(getXMLText(reader))));
                            case LEAF_COLOR -> visualParameter.setLeafColor(Color.valueOf(convertToXMLTag(getXMLText(reader))));
                            case AVERAGE_SIZE -> visualParameter.setAverageSize(Integer.parseInt(getXMLText(reader)));
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (FlowerXmlTag.valueOf(convertToXMLTag(name)) == FlowerXmlTag.VISUAL_PARAMETER) {
                            return visualParameter;
                        }
                }
            }
        } catch (XMLStreamException e) {
            logger.log(Level.ERROR, "Error while parsing tag <visual-parameter>");
            throw new FlowerXmlException("Error while parsing tag <visual-parameter>", e);
        }
        logger.log(Level.ERROR, "Unknown element in tag <visual-parameter>");
        throw new FlowerXmlException("Unknown element in tag <visual-parameter>");
    }

    private GrowingTip getXMLGrowingTip(XMLStreamReader reader) throws FlowerXmlException {
        GrowingTip growingTip = new GrowingTip();
        int type = 0;
        String name;
        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (FlowerXmlTag.valueOf(convertToXMLTag(name))) {
                            case TEMPERATURE -> growingTip.setTemperature(Integer.parseInt(getXMLText(reader)));
                            case LIGHT -> growingTip.setLight(Boolean.parseBoolean(getXMLText(reader)));
                            case WATERING -> growingTip.setWatering(Integer.parseInt(getXMLText(reader)));
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (FlowerXmlTag.valueOf(convertToXMLTag(name)) == FlowerXmlTag.GROWING_TIP) {
                            return growingTip;
                        }
                }
            }
        } catch (XMLStreamException e) {
            logger.log(Level.ERROR, "Error while parsing tag <growing-tip");
            throw new FlowerXmlException("Error while parsing tag <growing-tip", e);
        }
        logger.log(Level.ERROR, "Unknown element in tag <growing-tip>");
        throw new FlowerXmlException("Unknown element in tag <growing-tip>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private String convertToXMLTag(String name) {
        return name.toUpperCase()
                .replaceAll(CHAR_TO_REPLACE, NEW_CHAR);
    }
}
