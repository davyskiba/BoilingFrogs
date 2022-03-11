package com.panoprogramowanie.boilingfrogs.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AssetJsonLoader {

    private static final String SCHEDULE_ASSET_FILENAME = "agenda.json";

    public static String readScheduleJsonFromFile(Context context) {
        return readAssetsFile(context, SCHEDULE_ASSET_FILENAME);
    }

    public static String readAssetsFile(Context context, String fileName) {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(fileName), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                builder.append(mLine);
            }
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        return builder.toString();
    }

}
