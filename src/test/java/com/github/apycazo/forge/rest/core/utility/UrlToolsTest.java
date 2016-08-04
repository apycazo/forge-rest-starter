package com.github.apycazo.forge.rest.core.utility;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Andres Picazo
 */
public class UrlToolsTest
{
    private String correctPath = "http://127.0.0.1:8080";

    @Test
    public void sanitizeContextPathStringCorrectPathTest () {
        String path = correctPath;

        String sanitizedPath = UrlTools.sanitizeContextPathString(path);
        Assert.assertEquals(correctPath, sanitizedPath);
    }

    @Test
    public void sanitizeContextPathStringMissingProtocolTest () {
        String path = "127.0.0.1:8080";

        String sanitizedPath = UrlTools.sanitizeContextPathString(path);
        Assert.assertEquals(correctPath, sanitizedPath);
    }

    @Test
    public void sanitizeContextPathStringEndingInSlashTest () {
        String path = correctPath + "/";

        String sanitizedPath = UrlTools.sanitizeContextPathString(path);
        Assert.assertEquals(correctPath, sanitizedPath);
    }


    @Test
    public void sanitizeContextPathStringWithParamsTest () {
        String path = correctPath + "?value=one";

        String sanitizedPath = UrlTools.sanitizeContextPathString(path);
        Assert.assertEquals(correctPath, sanitizedPath);
    }

    @Test
    public void sanitizeContextPathStringEndingInSlashWithParamsTest () {
        String path = correctPath + "/?value=one";

        String sanitizedPath = UrlTools.sanitizeContextPathString(path);
        Assert.assertEquals(correctPath, sanitizedPath);
    }

    @Test
    public void sanitizeSegmentsTest ()
    {
        String [] expected = {"/path1", "/path2", "/path3", "/path4"};
        String [] segments = {"/path1", "path2", null, "/path3/", "path4/?test=true"};

        String [] result = UrlTools.sanitizeUrlSegments(segments);

        // I have the expected number of segments
        Assert.assertEquals(expected.length, result.length);

        // Every segment is as expected
        for (int i = 0 ; i < expected.length; i++) {
            Assert.assertEquals("Segment value does not match", expected[i], result[i]);
        }

    }

}