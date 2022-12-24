package com.group10.TalesOfWonder;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "user-photos";

        Path userPhotoPath = Paths.get(dirName);

        String userPhotosPath = userPhotoPath.toFile().getAbsolutePath();

        registry.addResourceHandler("/"+dirName+"/**")
                .addResourceLocations("file:/"+userPhotosPath+"/");

        dirName = "comic-poster";
        userPhotoPath = Paths.get(dirName);
        userPhotosPath = userPhotoPath.toFile().getAbsolutePath();
        registry.addResourceHandler("/"+dirName+"/**")
                .addResourceLocations("file:/"+userPhotosPath+"/");

        dirName = "chapter-poster";
        userPhotoPath = Paths.get(dirName);
        userPhotosPath = userPhotoPath.toFile().getAbsolutePath();
        registry.addResourceHandler("/"+dirName+"/**")
                .addResourceLocations("file:/"+userPhotosPath+"/");
    }
}
