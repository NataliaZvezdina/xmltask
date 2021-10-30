package by.zvezdina.xmltask.builder;

import by.zvezdina.xmltask.entity.Flower;
import by.zvezdina.xmltask.exception.FlowerXmlException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxFlowerBuilder extends AbstractFlowerBuilder {
    private static final Logger logger = LogManager.getLogger();
    private FlowerHandler handler = new FlowerHandler();
    private XMLReader reader;

    public SaxFlowerBuilder() throws FlowerXmlException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.log(Level.ERROR, "Error while configuring xml reader");
            throw new FlowerXmlException("Error while configuring xml reader", e);
        }
        reader.setErrorHandler(new FlowerErrorHandler());
        reader.setContentHandler(handler);
    }

    @Override
    public void buildSetFlowers(String filename) throws FlowerXmlException {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            logger.log(Level.ERROR, "Error while parsing xml file " + filename);
            throw new FlowerXmlException("Error while parsing xml file " + filename, e);
        }
        flowers = handler.getFlowers();
    }
}
