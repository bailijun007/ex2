//package com.hupa.exp.proc;
//
//import com.alibaba.fastjson.JSON;
//import com.hupa.exp.daoex2.dao.expv2.def.IMongoDbDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class TestProc {
//
//    @Autowired
//    private IMongoDbDao iMongoDbDao;
//
//    @PostConstruct
//    public void genSearch() {
//        try {
//            //查询条件
//            Map<String, Map<String, Object>> filedMap = new HashMap<>();
//
//
//            //动态获取类
//            Class getClass = Class.forName("com.hupa.exp.base.entity.bo.pc.PcTradeBo");
//            //传进来的参数
//            String searchStr = "id|=|95640596424689664,visibleFlag|=|1,time|>|1569074495113";
//            String[] searchArr = searchStr.split(",");
//            //构造查询条件
//            for (String search : searchArr) {
//                Map<String, Object> searchMap = new HashMap<>();
//                Object ret = null;
//                String[] strArr = search.split("[|]");
//                try {
//                    //获取字段属性
//                    Field field = getClass.getDeclaredField(strArr[0]);
//                    if (field.getType().getSimpleName().equals("BigDecimal")) {
//                        getBigDecimal(strArr[2]);
//                    } else {
//                        Class<?> class1 = Class.forName(getType(field.getType().getName()));
//                        Method method = null;
//                        try {
//                            method = class1.getMethod("parse" + fixparse(field.getType().getSimpleName()), String.class);
//                            if (method != null) {
//
//                                try {
//                                    ret = method.invoke(null, strArr[2]);
//                                    if (ret != null)
//                                        System.out.println(ret);
//                                } catch (IllegalAccessException e) {
//                                    e.printStackTrace();
//                                } catch (InvocationTargetException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        } catch (NoSuchMethodException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                } catch (NoSuchFieldException e) {
//                    e.printStackTrace();
//                }
//                searchMap.put(strArr[1], ret);
//                //替换下Id
//                String fieldStr=strArr[0].equals("id")?"_id":strArr[0];
//                filedMap.put(fieldStr, searchMap);
//            }
//            //表名
//            String collectionName = "pc_trade_BTC_USD";
//            //查询
//            List<Object> list = iMongoDbDao.findAll(collectionName, filedMap, getClass);
//
//            System.out.println(JSON.toJSONString(list));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String fixparse(String fstype) {
//        switch (fstype) {
//            case "Integer":
//                return "Int";
//            case "long":
//                return "Long";
//            default:
//                return fstype;
//        }
//    }
//
//    private static String getType(String fstype) {
//        switch (fstype) {
//            case "long":
//                return "java.lang.Long";
//            case "int":
//                return "java.lang.Integer";
//            default:
//                return fstype;
//        }
//    }
//
//    public static BigDecimal getBigDecimal(Object value) {
//        BigDecimal ret = null;
//        if (value != null) {
//            if (value instanceof BigDecimal) {
//                ret = (BigDecimal) value;
//            } else if (value instanceof String) {
//                ret = new BigDecimal((String) value);
//            } else if (value instanceof BigInteger) {
//                ret = new BigDecimal((BigInteger) value);
//            } else if (value instanceof Number) {
//                ret = new BigDecimal(((Number) value).doubleValue());
//            } else {
//                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
//            }
//        }
//        return ret;
//    }
//}
