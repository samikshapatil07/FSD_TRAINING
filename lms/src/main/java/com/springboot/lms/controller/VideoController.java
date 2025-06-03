
package com.springboot.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Video;
import com.springboot.lms.service.VideoService;

@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * @path /api/video/add
     * @param @RequestBody List<Video>
     * @return ResponseEntity
     * @method POST
     */
    @PostMapping("/add/{moduleId}")
    public ResponseEntity<?> batchInsert(@RequestBody List<Video> list,
            @PathVariable int moduleId) {
        videoService.batchInsert(list, moduleId);
        return ResponseEntity.ok().body("Operation Comleted!!!");
    }

}
