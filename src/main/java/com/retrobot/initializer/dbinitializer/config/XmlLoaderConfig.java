package com.retrobot.initializer.dbinitializer.config;

import com.retrobot.initializer.dbinitializer.xml.loader.MapsLoader;
import com.retrobot.initializer.dbinitializer.xml.loader.newmaps.dto.GameMapsDto;
import com.retrobot.initializer.dbinitializer.xml.loader.oldmaps.dto.MapsDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URISyntaxException;

@Configuration
public class XmlLoaderConfig {

    @Bean
    GameMapsDto gameMaps() throws IOException, URISyntaxException {
        return MapsLoader.loadGameMapsData();
    }

    @Bean
    MapsDto maps() throws IOException, URISyntaxException {
        return MapsLoader.loadMapsData();
    }

}
