package com.springboot.lms.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.lms.model.Learner;

@Component
public class LearnerDto {
    private int id;
    private String name;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LearnerDto> convertLeanerIntoDto(List<Learner> list) {
        List<LearnerDto> listDto = new ArrayList<>();
        list.stream().forEach(learner -> {
            LearnerDto dto = new LearnerDto();
            dto.setId(learner.getId());
            dto.setName(learner.getName());
            dto.setUsername(learner.getUser().getUsername());
            listDto.add(dto);
        });

        return listDto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}