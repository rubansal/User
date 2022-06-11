package com.startup.UserManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class ErrorMessage {

    private int status;
    private String message;
    private String description;
}
