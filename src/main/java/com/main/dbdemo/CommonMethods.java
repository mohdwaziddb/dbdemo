package com.main.dbdemo;

import javax.servlet.http.HttpServletRequest;

public class CommonMethods {

    public static String getLimit(Integer limit, HttpServletRequest req) {
        if (limit != null) {
            int constantlimit = Constants.LIMIT11;
            int ilimit = limit;
            int startlimit = 0;
            int endlimit = 0;
            if (ilimit > 1) {
                endlimit = limit * constantlimit;
                startlimit = endlimit - constantlimit;

            } else {
                startlimit = 0;
                endlimit = constantlimit;
            }
            if (req != null) {
                req.setAttribute("startindex", startlimit + 1);
                req.setAttribute("endindex", endlimit);
            }
            String str = " limit " + startlimit + " , " + constantlimit;
            return str;
        }

        return "";
    }
}
