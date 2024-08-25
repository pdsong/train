package com.pds.train.generator.server;


import com.pds.train.generator.util.FreeMarkerUtil;
import freemarker.template.TemplateException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ServerGenerator {
    static  String toPath="generator/src/main/java/com/pds/train/generator/test/";
    static {
        new File(toPath).mkdirs();
    }
    public static void main(String[] args) throws Exception {
        FreeMarkerUtil.initConfig("test.ftl");
        Map<String,Object> param=new HashMap<>();
        param.put("domain","Test");
        FreeMarkerUtil.generator(toPath+"Test.java",param);
    }
}
