import by.zvezdina.xmltask.builder.StaxFlowerBuilder;
import by.zvezdina.xmltask.exception.FlowerXmlException;

public class Main {
    public static void main(String[] args) throws FlowerXmlException {

//        SaxFlowerBuilder saxBuilder = new SaxFlowerBuilder();
//        saxBuilder.buildSetFlowers("dataxml/flowers.xml");
//        System.out.println(saxBuilder.getFlowers());

        StaxFlowerBuilder staxBuilder = new StaxFlowerBuilder();
        staxBuilder.buildSetFlowers("dataxml/flowers.xml");
        System.out.println(staxBuilder.getFlowers());



    }
}