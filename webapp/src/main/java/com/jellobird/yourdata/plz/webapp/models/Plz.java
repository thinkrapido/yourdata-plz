package com.jellobird.yourdata.plz.webapp.models;

import lombok.Value;

@Value(staticConstructor = "of")
public class Plz {

    String osm_id;
    String ort;
    String plz;
    String bundesland;

}
