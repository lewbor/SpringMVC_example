package org.mvc.controller.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mvc.lesson.Lesson;

@Mapper(
        componentModel = "spring"
)
public interface LessonMapper {
    LessonDto lessonToLessonDto(Lesson lesson);

    void lessonDtoToLesson(@MappingTarget Lesson entity, LessonDto lessonDto);
}
