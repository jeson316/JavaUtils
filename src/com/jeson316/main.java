package com.jeson316;

import com.jeson316.pic.PicUtils;
import com.jeson316.svg.Svg2AndroidXmlUtils;

import java.io.File;

public class main {

    public static void main(String[] args) {
//        svgTest();
        picScaleTest();
    }

    private static void svgTest() {
        Svg2AndroidXmlUtils.svg2xml(new File("D:\\pictures\\svg\\404location.svg"));
    }

    private static void picScaleTest(){
        try {
            var p = new PicUtils("D:\\1000879.jpg", "_xxhdpi");
            p.zoomByScale(0.75);

             p = new PicUtils("D:\\1000879.jpg", "_xhdpi");
            p.zoomByScale(0.5);

             p = new PicUtils("D:\\1000879.jpg", "_hdpi");
            p.zoomByScale(0.357);

             p = new PicUtils("D:\\1000879.jpg", "_mhdpi");
            p.zoomByScale(0.25);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
