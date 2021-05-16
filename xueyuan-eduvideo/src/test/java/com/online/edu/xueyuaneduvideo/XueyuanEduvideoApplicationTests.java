package com.online.edu.xueyuaneduvideo;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class XueyuanEduvideoApplicationTests {

    @Test
    static void test1() {
        List ids = new ArrayList();
        ids.add("12");
        ids.add("34");
        Object[] idArr =  ids.toArray();
        String str = StringUtils.join(idArr, ',');
        System.out.println(str);
    }

    public static void main(String[] args) {
        test1();
    }

}
