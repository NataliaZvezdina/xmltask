package by.zvezdina.xmltask.builder;

import by.zvezdina.xmltask.entity.*;
import by.zvezdina.xmltask.exception.FlowerXmlException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;

public class DomFlowerBuilder extends AbstractFlowerBuilder {
    private static final Logger logger = LogManager.getLogger();
    private DocumentBuilder documentBuilder;

    public DomFlowerBuilder() throws FlowerXmlException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Error while configuring parser");
            throw new FlowerXmlException("Error while configuring parser", e);
        }
    }

    @Override
    public void buildSetFlowers(String filename) throws FlowerXmlException {
        Document document;
        try {
            document = documentBuilder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList cutFlowersList = root.getElementsByTagName("cut-flower");
            NodeList pottedFlowersList = root.getElementsByTagName("potted-flower");
            for (int i = 0; i < cutFlowersList.getLength(); i++) {
                Element cutFlowerElement = (Element) cutFlowersList.item(i);
                Flower cutFlower = buildCutFlower(cutFlowerElement);
                flowers.add(cutFlower);
            }
            for (int i = 0; i< pottedFlowersList.getLength(); i++) {
                Element pottedFlowerElement = (Element) pottedFlowersList.item(i);
                Flower pottedFlower = buildPottedFlower(pottedFlowerElement);
                flowers.add(pottedFlower);
            }

        } catch (IOException | SAXException e) {
            logger.log(Level.ERROR, "IO error or parse error occur with xml file " + filename);
            throw new FlowerXmlException("IO error or parse error occur with xml file " + filename, e);
        }
    }

    private  Flower buildCutFlower(Element flowerElement) {
        CutFlower cutFlower = new CutFlower();
        cutFlower.setId(flowerElement.getAttribute(FlowerXmlTag.ID.toString()));
        String attributeDecorated = flowerElement.getAttribute(FlowerXmlTag.DECORATED.toString());
        if (attributeDecorated != null) {
            cutFlower.setDecorated(Boolean.parseBoolean(attributeDecorated));
        }
        cutFlower.setName(getElementTextContent(flowerElement, FlowerXmlTag.NAME.toString()));
        cutFlower.setOrigin(Origin.valueOf(convertToXMLTag(getElementTextContent(flowerElement, FlowerXmlTag.ORIGIN.toString()))));
        cutFlower.setSoil(Soil.valueOf(convertToXMLTag(getElementTextContent(flowerElement, FlowerXmlTag.SOIL.toString()))));

        VisualParameter visualParameter = cutFlower.getVisualParameter();
        Element visualParameterElement = (Element) flowerElement.getElementsByTagName(FlowerXmlTag.VISUAL_PARAMETER.toString()).item(0);
        visualParameter.setStemColor(Color.valueOf(convertToXMLTag(getElementTextContent(visualParameterElement, FlowerXmlTag.STEM_COLOR.toString()))));
        visualParameter.setLeafColor(Color.valueOf(convertToXMLTag(getElementTextContent(visualParameterElement, FlowerXmlTag.LEAF_COLOR.toString()))));
        visualParameter.setAverageSize(Integer.parseInt(getElementTextContent(visualParameterElement, FlowerXmlTag.AVERAGE_SIZE.toString())));

        GrowingTip growingTip = cutFlower.getGrowingTip();
        Element growingTipElement = (Element) flowerElement.getElementsByTagName(FlowerXmlTag.GROWING_TIP.toString()).item(0);
        growingTip.setTemperature(Integer.parseInt(getElementTextContent(growingTipElement, FlowerXmlTag.TEMPERATURE.toString())));
        growingTip.setLight(Boolean.parseBoolean(getElementTextContent(growingTipElement, FlowerXmlTag.LIGHT.toString())));
        growingTip.setWatering(Integer.parseInt(getElementTextContent(growingTipElement, FlowerXmlTag.WATERING.toString())));

        cutFlower.setMultiplying(Multiplying.valueOf(convertToXMLTag(getElementTextContent(flowerElement, FlowerXmlTag.MULTIPLYING.toString()))));
        cutFlower.setCutDate(LocalDate.parse(getElementTextContent(flowerElement, FlowerXmlTag.DATE_CUT.toString())));
        cutFlower.setStemLength(Integer.parseInt(getElementTextContent(flowerElement, FlowerXmlTag.STEM_LENGTH.toString())));

        return cutFlower;
    }

    private  Flower buildPottedFlower(Element flowerElement) {
        PottedFlower pottedFlower = new PottedFlower();
        pottedFlower.setId(flowerElement.getAttribute(FlowerXmlTag.ID.toString()));
        pottedFlower.setName(getElementTextContent(flowerElement, FlowerXmlTag.NAME.toString()));
        pottedFlower.setOrigin(Origin.valueOf(convertToXMLTag(getElementTextContent(flowerElement, FlowerXmlTag.ORIGIN.toString()))));
        pottedFlower.setSoil(Soil.valueOf(convertToXMLTag(getElementTextContent(flowerElement, FlowerXmlTag.SOIL.toString()))));

        VisualParameter visualParameter = pottedFlower.getVisualParameter();
        Element visualParameterElement = (Element) flowerElement.getElementsByTagName(FlowerXmlTag.VISUAL_PARAMETER.toString()).item(0);
        visualParameter.setStemColor(Color.valueOf(convertToXMLTag(getElementTextContent(visualParameterElement, FlowerXmlTag.STEM_COLOR.toString()))));
        visualParameter.setLeafColor(Color.valueOf(convertToXMLTag(getElementTextContent(visualParameterElement, FlowerXmlTag.LEAF_COLOR.toString()))));
        visualParameter.setAverageSize(Integer.parseInt(getElementTextContent(visualParameterElement, FlowerXmlTag.AVERAGE_SIZE.toString())));

        GrowingTip growingTip = pottedFlower.getGrowingTip();
        Element growingTipElement = (Element) flowerElement.getElementsByTagName(FlowerXmlTag.GROWING_TIP.toString()).item(0);
        growingTip.setTemperature(Integer.parseInt(getElementTextContent(growingTipElement, FlowerXmlTag.TEMPERATURE.toString())));
        growingTip.setLight(Boolean.parseBoolean(getElementTextContent(growingTipElement, FlowerXmlTag.LIGHT.toString())));
        growingTip.setWatering(Integer.parseInt(getElementTextContent(growingTipElement, FlowerXmlTag.WATERING.toString())));

        pottedFlower.setMultiplying(Multiplying.valueOf(convertToXMLTag(getElementTextContent(flowerElement, FlowerXmlTag.MULTIPLYING.toString()))));
        pottedFlower.setPlantingDate(LocalDate.parse(getElementTextContent(flowerElement, FlowerXmlTag.DATE_PLANTED.toString())));

        return pottedFlower;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }

    private String convertToXMLTag(String name) {
        return name.toUpperCase()
                .replaceAll("-", "_");
    }
}
