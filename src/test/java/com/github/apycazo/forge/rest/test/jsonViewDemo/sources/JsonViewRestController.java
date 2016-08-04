package com.github.apycazo.forge.rest.test.jsonViewDemo.sources;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andres Picazo
 */
@Slf4j
@RestController
public class JsonViewRestController
{

    private UserInfo userInfo;

    public JsonViewRestController()
    {
        userInfo = UserInfo.builder()
                .id(100)
                .name("john doe")
                .password("secret")
                .build();
    }

    public UserInfo getUserInfo()
    {
        return userInfo;
    }

    @GetMapping("public")
    public UserInfo getPublicView()
    {
        return userInfo;
    }

    @JsonView(View.Editor.class)
    @GetMapping("editor")
    public UserInfo getEditorView()
    {
        return userInfo;
    }

    @JsonView(View.Admin.class)
    @GetMapping("admin")
    public UserInfo getAdminView()
    {
        return userInfo;
    }

}
