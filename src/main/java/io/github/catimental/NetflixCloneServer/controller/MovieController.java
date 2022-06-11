package io.github.catimental.NetflixCloneServer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.core.io.*;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@RestController
public class MovieController {

    private static final String SAMPLE_MOVIE_NAME = "sample";
    @GetMapping(value = "/video/{name}")
    public ResponseEntity<ResourceRegion> getVideo (@RequestHeader HttpHeaders headers, @PathVariable String name) throws IOException {
        var video = new UrlResource("classpath:movies/"+name+".mp4");
        if(!video.exists()) {
            video = new UrlResource("classpath:movies/"+"123"+".mp4");
        }

        ResourceRegion resourceRegion;
        final long chunkSize = 1000000L;
        long contentLength = video.contentLength();
        Optional<HttpRange> optional = headers.getRange().stream().findFirst();
        HttpRange httpRange;
        if (optional.isPresent()) {
            httpRange = optional.get();
            long start = httpRange.getRangeStart(contentLength) ;
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end - start + 1);

            resourceRegion = new ResourceRegion(video, start, rangeLength);
        } else {
            long rangeLength = Long.min(chunkSize, contentLength);
            resourceRegion = new ResourceRegion(video, 0, rangeLength);
        }

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resourceRegion);

    }

    @GetMapping("/region/{fileName}")
    public ResponseEntity<ResourceRegion> videoRegion(@PathVariable String fileName, @RequestHeader HttpHeaders headers) throws IOException {
        System.out.println(headers.keySet());
        var resource = new ClassPathResource("movies/%s.mp4".formatted(fileName));
        if(!resource.exists()) {
            resource = new ClassPathResource("movies/%s.mp4".formatted(SAMPLE_MOVIE_NAME));
        }

        final long chunkSize = 1024 * 1024 * 1;
        long contentLength = resource.contentLength();
        ResourceRegion region;
        try {
            HttpRange httpRange = headers.getRange().stream().findFirst().get();
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end - start + 1);
            region = new ResourceRegion(resource, start, rangeLength);
        } catch (Exception e) {
            long rangeLength = Long.min(chunkSize, contentLength);
            region = new ResourceRegion(resource, 0, rangeLength);
        }
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .header("Accept-Ranges", "bytes")
                .eTag(fileName)
                .body(region);
    }
}
