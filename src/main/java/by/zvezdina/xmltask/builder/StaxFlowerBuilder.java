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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;

public class StaxFlowerBuilder extends AbstractFlowerBuilder {
    private static final Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public StaxFlowerBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        //flowers = new HashSet<>();
    }

    @Override
    public void buildSetFlowers(String filename) throws FlowerXmlException {
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
                        flowers.add(cutFlower);
                    } else if (name.equals(FlowerXmlTag.POTTED_FLOWER.toString())) {
                        PottedFlower pottedFlower = buildPottedFlower(reader);
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
        cutFlower.setId(reader.getAttributeValue(null, FlowerXmlTag.ID.toString()));
        boolean decorated = Boolean.parseBoolean(reader.getAttributeValue(null, FlowerXmlTag.DECORATED.toString()));
        cutFlower.setDecorated(decorated);

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
                            case DATE_CUT -> cutFlower.setDateCut(LocalDate.parse(getXMLText(reader)));
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

    private PottedFlower buildPottedFlower(XMLStreamReader reader) {
        PottedFlower pottedFlower = new PottedFlower();

        return pottedFlower;
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
                .replaceAll("-", "_");
    }
}
