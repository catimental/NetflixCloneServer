package io.github.catimental.NetflixCloneServer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class MovieController {

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
}
