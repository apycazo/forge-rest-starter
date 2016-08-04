package com.github.apycazo.forge.rest.core.utility;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Andres Picazo
 */
public class UrlTools
{
    /**
     * Context path must not end in '/', nor have any parameters
     * @param cp The original string context path. Ex: http://127.0.0.1:8000/test/?verbose=3
     * @return The sanitized path. Ex: http://127.0.0.1:8000/test
     */
    public static String sanitizeContextPathString (String cp)
    {
        if (cp == null) {
            return "";
        }
        if (!cp.startsWith("http")) {
            cp = "http://" + cp;
        }
        if (cp.contains("?")) {
            cp = cp.substring(0, cp.indexOf("?"));
        }
        if (cp.endsWith("/")) {
            cp = cp.substring(0, cp.length() - 1);
        }

        return cp;
    }

    public static String sanitizeUrlSegment (String segment)
    {
        String [] sanitized = sanitizeUrlSegments(segment);
        if (sanitized.length == 0) {
            return "";
        }
        else {
            return sanitized[0];
        }
    }

    public static String [] sanitizeUrlSegments (String ... segments)
    {
        if (segments == null) {
            return new String[0];
        }
        else if (segments.length != 0) {
            List<String> list = new ArrayList<>(Arrays.asList(segments));
            ListIterator<String> it = list.listIterator();
            while (it.hasNext()) {
                String segment = it.next();
                if (StringUtils.isEmpty(segment)) {
                    it.remove();
                }
                else {
                    if (segment.contains("?")) {
                        segment = segment.substring(0, segment.indexOf("?"));
                    }
                    if (segment.endsWith("/")) {
                        segment = segment.substring(0, segment.length() - 1);
                    }
                    if (!segment.startsWith("/")) {
                        segment = "/" + segment;
                    }
                    it.set(segment);
                }
            }

            return list.toArray(new String[list.size()]);
        }
        else {
            return segments;
        }
    }


}
