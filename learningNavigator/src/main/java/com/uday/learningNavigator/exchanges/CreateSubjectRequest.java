package com.uday.learningNavigator.exchanges;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateSubjectRequest {
    @NotEmpty
    private String subjectName;
}
