package com.exadel.amc.mc.source.youtube.rest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;


public class YoutubeDateAdapter extends TypeAdapter<Date> {
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        String ds = in.nextString();
        try {
            return formatter.parse(ds);
        } catch (ParseException e) {
            throw new JsonParseException(e.getMessage(), e);
        }
    }

}
