package com.panoprogramowanie.boilingfrogs;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class Generator {

    private static final String PROJECT_DIR = System.getProperty("user.dir").replace("\\", "/");
    private static final String OUT_DIR = PROJECT_DIR + "/app/src/main/java";

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.panoprogramowanie.boilingfrogs.model");
        schema.enableKeepSectionsByDefault();

        Entity speaker=addSpeaker(schema);
        Entity speech=addSpeech(schema);
        Entity speechSlot=addSpeechSlot(schema);

        Property speakerInSpeechProperty=speech.addLongProperty("speakerId").getProperty();
        speaker.addToMany(speech,speakerInSpeechProperty);
        speech.addToOne(speaker,speakerInSpeechProperty);

        Property speechInSpeechSlotProperty=speechSlot.addLongProperty("favoriteSpeechId").getProperty();
        speechSlot.addToOne(speech,speechInSpeechSlotProperty,"favoriteSpeech");

        Property speechSlotInSpeechProperty=speech.addLongProperty("speechSlotId").getProperty();
        speechSlot.addToMany(speech,speechSlotInSpeechProperty);
        speech.addToOne(speechSlot,speechSlotInSpeechProperty);

        new DaoGenerator().generateAll(schema,OUT_DIR);

    }

    private static Entity addSpeaker(Schema schema) {
        Entity speaker=schema.addEntity("Speaker");
        speaker.addLongProperty("id").primaryKey();
        speaker.addStringProperty("name");
        speaker.addStringProperty("occupation");
        speaker.addStringProperty("description");
        speaker.addStringProperty("photoUrl");
        speaker.addStringProperty("twitter");
        speaker.addStringProperty("linkedin");
        speaker.addStringProperty("facebook");

        return speaker;
    }


    private static Entity addSpeech(Schema schema) {
        Entity speech=schema.addEntity("Speech");
        speech.implementsInterface("ListItemModel");
        speech.addImport("com.panoprogramowanie.boilingfrogs.ui.list.ListItemModel");

        speech.addLongProperty("id").primaryKey();
        speech.addStringProperty("title");
        speech.addStringProperty("description");
        speech.addIntProperty("path");
        speech.addStringProperty("youtubeUrl");

        return speech;
    }

    private static Entity addSpeechSlot(Schema schema) {
        Entity speechSlot=schema.addEntity("SpeechSlot");
        speechSlot.addLongProperty("id").primaryKey();
        speechSlot.addStringProperty("timeLabel");

        return speechSlot;
    }
}
