package com.github.apycazo.forge.rest.core.domains;

import com.github.apycazo.forge.rest.core.utility.UrlTools;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A common service info to be used along with a @ConfigurationProperties annotation.
 *
 * Sample:
 * {
 *     serviceName: ManagementServices
 *     serviceInstance: ManagementServices::3448
 *     remoteBasePath: http://127.0.0.1:8080
 *     directory: {
 *         UserService: /users
 *         AccountService: /admin/accounts
 *         SecurityService: /admin/security
 *         TestService: http://10.1.1.1:8089/test
 *     }
 *
 * }
 *
 * The directory will return remoteBasePath + directory[key] if starts by '/'.
 *
 * @author Andres Picazo
 */
@Slf4j
@Data
public class ServiceSettings
{
    private String serviceName = getClass().getSimpleName();
    private String serviceInstance = serviceName + "::" + new Random().nextInt(9999);
    private String mapping = "";
    private String remoteBasePath = "http://127.0.0.1:8080";
    private Map<String,String> directory = new HashMap<>();

    /**
     * Sanitizes base path and directory segments. If a valid URL cannot be created with base path and/or a directory
     * entry, a MalformedURLException will be thrown.
     * @throws MalformedURLException
     */
    @PostConstruct
    protected void setup () throws MalformedURLException
    {
        remoteBasePath = UrlTools.sanitizeContextPathString(remoteBasePath);
        for (String key : directory.keySet()) {
            String value = directory.get(key);
            String sanitizedValue = UrlTools.sanitizeUrlSegment(value);
            if (StringUtils.isEmpty(sanitizedValue)) {
                log.debug("Directory entry '{}' value is empty after sanitation", key);
            }
            directory.put(key, sanitizedValue);
        }
        for (String path : directory.values()) {
            String url = path.startsWith("http") ? path : remoteBasePath + path;
            new URL(url);
        }
    }
}
