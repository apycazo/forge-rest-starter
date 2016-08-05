package com.github.apycazo.forge.rest.demo.jsonViewDemo.sources;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;

/**
 * @author Andres Picazo
 */
@Data
@Builder
public class UserInfo
{
    private int id;
    @JsonView(View.Editor.class)
    private String name;
    @JsonView(View.Admin.class)
    private String password;
}
