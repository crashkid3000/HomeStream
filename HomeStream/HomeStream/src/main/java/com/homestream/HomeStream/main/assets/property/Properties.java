package com.homestream.HomeStream.main.assets.property;

import com.homestream.HomeStream.main.assets.Loader;
import com.homestream.HomeStream.web.assets.TEMP;

public class Properties
{
    /**
     * General Properties
     *
     * @author S. Schulze
     * @last_update 15.11.19
     */

    private static Loader loader = new Loader();

    //MAIN
    public static final String TITLE = "HomeStream";
    public static final String[] MOTTO = new String[] {
            "Stream your Sofa", "I have a Stream",
            "Stream your Fantasy", "Stream your Holidays",
            "Stream your Family"};

    public static final String DOMAIN = "homestream.com";
    public static String[] LANGUAGES = loader.readDirectory("./res/lang/");
    public static String LANG = "de_DE";

    private static final byte VERSION_MAJOR = 0;
    private static final byte VERSION_MINOR = 1;
    private static final short VERSION_PATCH = 5;
    private static final String  VERSION_DATE = "28.11.2019";
    public static final String VERSION = VERSION_MAJOR + ":" + VERSION_MINOR + ":" + VERSION_PATCH + " - " + VERSION_DATE ;

    //DEFAULTS
    private static final String SCRIPT_PATH = ".\\res\\scripts\\";
    public static final String[] SCRIPTS = new String[]{
                    SCRIPT_PATH + "defaultStart.script",
                    SCRIPT_PATH + "defaultGallery.script"};

    public static final byte LIGHT = 0;
    public static final byte DARK = 1;

    private static final String CSS_PATH = "css/";
    public static final String[] MAIN_CSS = new String[]
            {CSS_PATH + "main.css", CSS_PATH + "darkMain.css"};
    public static final String[] COLOR_CSS = new String[]
            {CSS_PATH + "color.css", CSS_PATH + "darkColor.css"};
    public static final String[] ICONS_CSS = new String[]
            {"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css", CSS_PATH + "darkIcon.css"};
    public static final String[] CONTENT_CSS = new String[]
            {CSS_PATH + "content.css", CSS_PATH + "darkContent.css"};
    public static final String[] NAVIGATION_CSS = new String[]
            {CSS_PATH + "navigation.css", CSS_PATH + "darkNavigation"};
    public static final String[] BACKGROUND_CSS = new String[]
            {CSS_PATH + "background.css", CSS_PATH + "darkBackground.css"};

    private static final String JS_PATH = "js/";
    public static final String MAIN_JS = JS_PATH + "main.js";

    private static final String TEMP_PATH = "./res/template/";
    public static final TEMP[] CONTENT_NAVIGATION = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultNav")};
    public static final TEMP[] CONTENT_TITLE = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultTitle")};
    public static final TEMP[] CONTENT_ELEMENT = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultContentElement")};
    public static final TEMP[] CONTENT_ALL = new TEMP[]
            {new TEMP(TEMP_PATH + "defaultContentAll")};
}
