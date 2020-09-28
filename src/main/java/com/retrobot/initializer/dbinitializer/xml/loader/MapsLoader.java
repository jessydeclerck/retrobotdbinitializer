package com.retrobot.initializer.dbinitializer.xml.loader;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.retrobot.initializer.dbinitializer.xml.loader.newmaps.dto.GameMapsDto;
import com.retrobot.initializer.dbinitializer.xml.loader.oldmaps.dto.MapsDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class MapsLoader {

    public static MapsDto loadMapsData() throws URISyntaxException, IOException {
        File mapsFile = new File(MapsLoader.class.getClassLoader().getResource("maps.xml").toURI());
        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(new FileInputStream(mapsFile));
        MapsDto mapsData = xmlMapper.readValue(xml, MapsDto.class);
        return mapsData;
    }

    public static GameMapsDto loadGameMapsData() throws URISyntaxException, IOException {
        File mapsFile = new File(MapsLoader.class.getClassLoader().getResource("gameMaps.xml").toURI());
        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(new FileInputStream(mapsFile));
        GameMapsDto mapsData = xmlMapper.readValue(xml, GameMapsDto.class);
        return mapsData;
    }

    private static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

}
