package com.shijie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix="system")
public class EditorConfig {

    public String filePath;
    public String urlPath;
}
