package com.uday.learningNavigator.exchanges;



import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateExamRequest {
    @NotNull
    private long subjectId;
}
