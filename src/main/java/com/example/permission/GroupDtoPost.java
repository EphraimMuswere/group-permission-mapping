package com.example.permission;

import lombok.Data;
import java.util.Set;

@Data
public class GroupDtoPost {
    private String name;
    private Set<Long> roleIds;
}
