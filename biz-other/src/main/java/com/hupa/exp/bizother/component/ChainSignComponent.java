package com.hupa.exp.bizother.component;


import com.hupa.exp.util.security.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
public class ChainSignComponent {


    public static String signSecret="6SKW49tZX12uyvNMoG";



    public String buildSign(Map<String,Object> param){



        List<Map.Entry<String,Object>> listBySort = new ArrayList<>(param.entrySet());


        listBySort.sort(new Comparator<Map.Entry<String, Object>>() {

            @Override
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });


        StringBuilder content = new StringBuilder();
        content.append("{");

        for(int i=0;i < listBySort.size();i++) {

            Map.Entry<String,Object> mod = listBySort.get(i);

            content.append("\""+mod.getKey()+"\"");
            content.append(":");

            if(mod.getValue() instanceof  String){
                content.append("\""+mod.getValue()+"\"");
            }else {
                content.append(mod.getValue());
            }



            if (i + 1 < listBySort.size()) {
                content.append(",");
            }
        }

        content.append("}");

        System.out.println(content.toString());


        String sign = StringUtils.EMPTY;
        try {
            sign = SecurityUtil.hmacSha256(signSecret, content.toString());
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        } catch (InvalidKeyException e) {

            e.printStackTrace();
        }

        System.out.println(sign);
        return sign;
    }

}
