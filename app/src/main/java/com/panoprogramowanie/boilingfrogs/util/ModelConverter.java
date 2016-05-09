package com.panoprogramowanie.boilingfrogs.util;

import com.panoprogramowanie.boilingfrogs.model.greendao.Schedule;
import com.panoprogramowanie.boilingfrogs.model.greendao.Speaker;
import com.panoprogramowanie.boilingfrogs.model.greendao.Speech;
import com.panoprogramowanie.boilingfrogs.model.greendao.SpeechSlot;

import java.util.LinkedList;
import java.util.List;

public class ModelConverter {
    private ModelConverter() {
    }

    public static Schedule inMemoryToGreenDaoSchedule(com.panoprogramowanie.boilingfrogs.model.Schedule source){
        Schedule result=new Schedule();

        Speaker[] speakers=inMemorySpeakerArrayToGreenDaoSpeakerArray(source.getSpeakers());
        result.setSpeakers(speakers);

        List<Speech> speechesList=inMemotySpeechSlotArrayToGreenDaoSpeechList(source.getSpeechSlots());
        result.setSpeeches(speechesList.toArray(new Speech[0]));

        List<SpeechSlot> speechSlotsList=inMemotySpeechSlotArrayToGreenDaoSpeechSlotList(source.getSpeechSlots());
        result.setSpeechSlots(speechSlotsList.toArray(new SpeechSlot[0]));



        return result;
    }

    //region SpeechSlotConversion

    public static List<SpeechSlot> inMemotySpeechSlotArrayToGreenDaoSpeechSlotList(com.panoprogramowanie.boilingfrogs.model.SpeechSlot[] source){
        List<SpeechSlot> result=new LinkedList<>();

        for(com.panoprogramowanie.boilingfrogs.model.SpeechSlot slot : source){
            result.add(inMemorySpeechSlotToGreenDaoSpeechSlot(slot));
        }

        return result;
    }

    public static SpeechSlot inMemorySpeechSlotToGreenDaoSpeechSlot(com.panoprogramowanie.boilingfrogs.model.SpeechSlot source){
        SpeechSlot result=new SpeechSlot();

        result.setId(source.getId());
        result.setTimeLabel(source.getHeader());

        return result;
    }

    //endregion

    //region SpeechConversion

    public static List<Speech> inMemotySpeechSlotArrayToGreenDaoSpeechList(com.panoprogramowanie.boilingfrogs.model.SpeechSlot[] source){
        List<Speech> result=new LinkedList<>();

        for(com.panoprogramowanie.boilingfrogs.model.SpeechSlot slot : source){
         for(com.panoprogramowanie.boilingfrogs.model.Speech speech : slot.getSpeeches()){
             result.add(inMemorySpeechToGreenDaoSpeech(speech));
         }
        }

        return result;
    }

    public static Speech inMemorySpeechToGreenDaoSpeech(com.panoprogramowanie.boilingfrogs.model.Speech source){
        Speech result=new Speech();

        result.setId(source.getId());
        result.setPath(source.getPath());
        result.setTitle(source.getTitle());
        result.setDescription(source.getDescription());
        result.setYoutubeUrl(source.getYoutubeUrl());
        result.setSpeakerId((long) source.getSpeakerId());
        result.setSpeechSlotId(source.getSpeechSlotId());

        return result;
    }

    //endregion

    //region SpeakerConversion

    public static Speaker[] inMemorySpeakerArrayToGreenDaoSpeakerArray(com.panoprogramowanie.boilingfrogs.model.Speaker[] source){
        Speaker[] result=new Speaker[source.length];

        for(int i=0;i<source.length;i++){
            result[i]=inMemorySpeakerToGreenDaoSpeaker(source[i]);
        }

        return result;
    }

    public static Speaker inMemorySpeakerToGreenDaoSpeaker(com.panoprogramowanie.boilingfrogs.model.Speaker source){
        Speaker result=new Speaker();

        result.setId((long) source.getId());
        result.setName(source.getName());
        result.setOccupation(source.getOccupation());
        result.setDescription(source.getDescription());
        result.setPhotoUrl(source.getPhotoUrl());
        result.setTwitter(source.getTwitter());
        result.setLinkedin(source.getLinkedin());
        result.setFacebook(source.getFacebook());

        return result;
    }

    //endregion
}
