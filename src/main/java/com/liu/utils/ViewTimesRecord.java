package com.liu.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public  class ViewTimesRecord {
    OutputStream out=null;
    public void viewController(HttpServletRequest request,ServletContext context,String fileName) {
        try {
            context=request.getServletContext();
            String path = this.getClass().getResource(fileName).getPath().replaceAll("%20"," ");
            out=new FileOutputStream(path);
            Properties properties=new Properties();
            properties.setProperty("times_of_view",context.getAttribute("times")+"");
            properties.store(out,"viewTime");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
