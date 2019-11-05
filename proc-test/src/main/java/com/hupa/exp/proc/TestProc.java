package com.hupa.exp.proc;

import com.alibaba.fastjson.JSON;
import com.hupa.exp.help.IdCardGenerator;
import com.hupa.exp.help.RandomValueUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TestProc {

    public class doSomeThing implements Runnable{

        @Override
        public void run() {
            System.out.println(123);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void genSearch() {

        Thread t=new Thread(new doSomeThing());
        t.start();
        System.out.println(t.getState());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getState());
        new Thread(()->{

        }).start();

        Map<String,String> map=new HashMap<>();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        //循环
        map.forEach((key,value)->
            System.out.println(key+"|"+value)
        );
        List<User> userList=new ArrayList<>();
        IdCardGenerator idCardGenerator=new IdCardGenerator();
        for(int i=0;i<100;i++)
        {
            User user=new User();
            user.setEmail(RandomValueUtil.getEmail(8,12));
            user.setIdNum(idCardGenerator.generate());
            user.setPhone(RandomValueUtil.getTelephone());
            user.setUserName(RandomValueUtil.getChineseName());
            Random random = new Random();
            int ends = random.nextInt(99);
            user.setAge(String.valueOf(ends));
            System.out.println(JSON.toJSONString(user));
            userList.add(user);
        }
        //集合排序
        userList.sort(Comparator.comparing(User::getPhone).reversed());
        userList.forEach(user ->
                System.out.println(JSON.toJSONString(user)));
        //List转Map
        Map<String,User> userMap= userList.stream().collect(Collectors.toMap(User::getAge,a->a,(k1,k2)->k1));
        userMap.forEach((k,v)->
        {
            System.out.println(k);
            System.out.println(JSON.toJSONString(v));
        });

        //List分组
        Map<String,List<User>> userGroupMap= userList.stream().collect(Collectors.groupingBy(User::getAge));
        userGroupMap.forEach((k,v)->
        {
            System.out.println(k+"|"+v.size());
        });
        Map<String,String> sortMap=userList.stream().collect(Collectors.toMap(User::getAge,a->a.getUserName(),(k1,k2)->k1));
        //Map排序
        Map<String,String> resultMap=new LinkedHashMap<>();
        sortMap.entrySet().stream().
                sorted(Map.Entry.<String, String>comparingByKey().reversed()).
                forEachOrdered(m->resultMap.put(m.getKey(),m.getValue()));
        System.out.println(resultMap);
    }

    public class User{
        private String userName;
        private String idNum;
        private String phone;
        private String email;
        private String age;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getIdNum() {
            return idNum;
        }

        public void setIdNum(String idNum) {
            this.idNum = idNum;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

}
