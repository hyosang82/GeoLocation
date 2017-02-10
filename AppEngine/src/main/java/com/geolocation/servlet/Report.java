package com.geolocation.servlet;


import com.geolocation.Common;
import com.geolocation.datastore.FcmToken;
import com.geolocation.datastore.LocationLog;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by hyosang on 2017. 2. 10..
 */
public class Report extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger logger = Logger.getLogger(Report.class.getName());

        String lineNo = req.getHeader("LineNo");
        String lat = req.getHeader("Lat");
        String lng = req.getHeader("Lng");

        double longitude = Common.parseDouble(lng);
        double latitude = Common.parseDouble(lat);

        if(!Double.isNaN(longitude) && !Double.isNaN(latitude)) {
            if (!Common.isEmpty(lineNo)) {
                Key key = Key.create(FcmToken.class, lineNo);
                List<FcmToken> list = ObjectifyService.ofy().load()
                        .type(FcmToken.class)
                        .filterKey(key)
                        .list();
                if (!list.isEmpty()) {
                    FcmToken token = list.get(0);

                    LocationLog item = new LocationLog(token.lineNo, System.currentTimeMillis(), longitude, latitude);

                    ObjectifyService.ofy().save().entity(item).now();

                    resp.setStatus(200);
                    return;
                }else {
                    logger.log(Level.WARNING, "Cannot find parent key = " + lineNo);
                }
            }else {
                logger.log(Level.WARNING, "LineNo is empty");
            }
        }else {
            logger.log(Level.WARNING, "Lat/Lng is NaN: Lat=" + latitude + " / Lng=" + longitude);
            logger.log(Level.WARNING, "Input lat=" + lat + ", lng=" + lng);
        }

        resp.setStatus(400);

    }
}
