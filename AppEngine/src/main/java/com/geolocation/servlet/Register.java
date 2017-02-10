package com.geolocation.servlet;


import com.geolocation.Common;
import com.geolocation.datastore.FcmToken;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by hyosang on 2017. 2. 10..
 */
public class Register extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lineNo = req.getHeader("LineNo");
        String token = req.getHeader("Token");

        if(Common.isEmpty(lineNo) || Common.isEmpty(token)) {
            resp.setStatus(400);    //Bad request
        }else {
            Key key = Key.create(FcmToken.class, lineNo);

            List<FcmToken> result = ObjectifyService.ofy().load()
                    .type(FcmToken.class)
                    .filterKey(key)
                    .list();


            FcmToken entity;
            if (result.isEmpty()) {
                //add new
                entity = new FcmToken(lineNo, token);
            } else {
                entity = result.get(0);
                entity.token = token;
            }
            ObjectifyService.ofy().save().entity(entity).now();

            resp.setStatus(200);
        }
    }
}
