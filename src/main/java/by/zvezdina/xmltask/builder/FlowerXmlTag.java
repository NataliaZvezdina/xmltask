package by.zvezdina.xmltask.builder;

public enum FlowerXmlTag {

    FLOWERS,
    POTTED_FLOWER,
    CUT_FLOWER,
    FLOWER,
    ID,
    ORIGIN,
    VISUAL_PARAMETER,
    GROWING_TIP,
    NAME,
    SOIL,
    MULTIPLYING,
    PLANTING_DATE,
    CUT_DATE,
    STEM_LENGTH,
    STEM_COLOR,
    LEAF_COLOR,
    AVERAGE_SIZE,
    TEMPERATURE,
    LIGHT,
    WATERING,
    DECORATED;

    private static final char REPLACE_CHAR = '_';
    private static final char NEW_CHAR = '-';

    @Override
    public String toString() {
        return name()
                .toLowerCase()
                .replace(REPLACE_CHAR, NEW_CHAR);
    }
}
