package tutorial.service.servicelawrence.view;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Field;

/**
 * Created by lorence on 12/04/2018.
 *
 */

public class VGApplication extends Application {

    /*
     * Singleton Pattern Android
     */
    private static VGApplication sInstance;

    /**
     * FxaFaxingAPIs
     */
    public static Typeface sEfFontStyle;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        FontsOverride.setDefaultFont(this, "MONOSPACE", FontsOverride.FONT_HELVETICANEUE.LIGHT);
        FontsOverride.setDefaultFont(this, "SERIF", FontsOverride.FONT_HELVETICANEUE.BOLD);
    }

    /**
     * Read more: https://stackoverflow.com/questions/26558599/how-to-change-my-android-app-font
     */
    private static class FontsOverride {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
            sEfFontStyle = Typeface.createFromAsset(context.getAssets(),
                    fontAssetName);
            replaceFont(staticTypefaceFieldName, sEfFontStyle);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField(staticTypefaceFieldName);
                staticField.setAccessible(true);
                staticField.set(null, newTypeface);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        static class FONT_HELVETICANEUE {
            static String LIGHT = "fonts/helveticaneue/helveticaneuelight.ttf";
            static String BOLD = "fonts/helveticaneue/helveticaneuebold.ttf";
        }
    }

}
