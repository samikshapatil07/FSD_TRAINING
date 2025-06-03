package com.springboot.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.lms.exception.InvalidInputException;
import com.springboot.lms.model.CModule;
import com.springboot.lms.model.Video;
import com.springboot.lms.repository.ModuleRepository;
import com.springboot.lms.repository.VideoRepository;

@Service
public class VideoService {

    private VideoRepository videoRepository;
    private ModuleRepository moduleRepository;

    public VideoService(VideoRepository videoRepository, ModuleRepository moduleRepository) {
        this.videoRepository = videoRepository;
        this.moduleRepository = moduleRepository;
    }

    @Transactional
    public void batchInsert(List<Video> list, int moduleId) {
        CModule module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new InvalidInputException("Module Id Invalid"));

        if (list.isEmpty())
            throw new InvalidInputException("No Data Found");

        list.parallelStream().forEach(v -> v.setModule(module));
        videoRepository.saveAll(list);
    }
}