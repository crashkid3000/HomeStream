package com.homestream.HomeStream.main.assets;

public class Properties
{
    /**
     * Generell Properties
     *
     * @author S. Schulze
     * @last_update 5.11.19
     */

    //MAIN
    public static final String TITLE = "HomeStream";
    public static final String MOTTO_MAJOR = "Stream your";
    public static final String MOTTO_MINOR = "Sofa";

    private static final byte VERSION_MAJOR = 0;
    private static final byte VERSION_MINOR = 1;
    private static final short VERSION_PATCH = 1;
    private static final String  VERSION_DATE = "07.11.2019";
    public static final String VERSION = VERSION_MAJOR + ":" + VERSION_MINOR + ":" + VERSION_PATCH + " - " + VERSION_DATE ;

    //DEFAULTS
    public static final byte LIGHT = 0;
    public static final byte DARK = 1;

    private static final String CSS_PATH = "css/";
    public static final String[] MAIN_CSS = new String[]
            {CSS_PATH + "main.css", CSS_PATH + "darkMain.css"};
    public static final String[] COLOR_CSS = new String[]
            {CSS_PATH + "color.css", CSS_PATH + "darkColor.css"};
    public static final String[] ICONS_CSS = new String[]
            {CSS_PATH + "icon.css", CSS_PATH + "darkIcon.css"};
    public static final String[] CONTENT_CSS = new String[]
            {CSS_PATH + "content.css", CSS_PATH + "darkContent.css"};
    public static final String[] NAVIGATION_CSS = new String[]
            {CSS_PATH + "navigation.css", CSS_PATH + "darkNavigation"};
    public static final String[] BACKGROUND_CSS = new String[]
            {CSS_PATH + "background.css", CSS_PATH + "darkBackground.css"};
}
