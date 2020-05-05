package com.fangye.dataparser.json.gson;

import android.util.Log;

import com.fangye.dataparser.utils.StringEscapeUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;


/**
 * gson解析工具类
 *
 * @Package: com.fangye.dataparser.json.gson
 * @Author: fanyeren51@gmail.com
 * @UpdateDate: 2020/05/04 18:09
 */
public class GsonUtils {

    //short
    public static final int OBJECT_TYPE_SHORT = 0;
    //int
    public static final int OBJECT_TYPE_INT = 1;
    //long
    public static final int OBJECT_TYPE_LONG = 2;
    //double
    public static final int OBJECT_TYPE_DOUBLE =3;
    //float
    public static final int OBJECT_TYPE_FLOAT = 4;

    //short最小值
    private static final long SHORT_MIN = -32768L;
    //short最大值
    private static final long SHORT_MAX = 32767L;

    //int 最小值
    private static final long INT_MIN = -2147483648L;
    //int 最大值
    private static final long INT_MAX = 2147483647L;


    /**
     * 解析jsonArray
     *
     * @param in json数据
     * @throws IOException
     */
    static JSONArray readArray(JsonReader in)
            throws IOException {
        JSONArray jsonArray;
        in.beginArray();
        jsonArray = readJsonArray(in);
        in.endArray();
        return jsonArray;
    }

    /**
     * 解析jsonObject
     *
     * @param in json数据
     * @throws IOException
     */
    static JSONObject readObject(JsonReader in)
            throws IOException {
        JSONObject jsonObject;
        in.beginObject();
        jsonObject = readJsonObject(in);
        in.endObject();
        return jsonObject;
    }

    /**
     * 解析整个json数据
     *
     * @param in json数据
     * @throws IOException
     */
    private static JSONObject readJsonObject(JsonReader in)
            throws IOException {
        String key = "";
        JSONObject jo = new JSONObject();
        while (in.hasNext()) {
            try {
                if (in.peek() == JsonToken.BEGIN_ARRAY) {
                    if (key != null) {
                        jo.put(key, readArray(in));
                    }
                } else if (in.peek() == JsonToken.NUMBER) {
                    if (key != null) {
                        jo.put(key, in.nextDouble());
                    }
                } else if (in.peek() == JsonToken.STRING) {
                    if (key != null) {
                        jo.put(key, in.nextString());
                    }
                } else if (in.peek() == JsonToken.NULL) {
                    //值为null的情况
                    in.nextNull();
                } else if (in.peek() == JsonToken.NAME) {
                    //键
                    key = in.nextName();
                } else if (in.peek() == JsonToken.BOOLEAN) {
                    if (key != null) {
                        jo.put(key, in.nextBoolean());
                    }
                } else if (in.peek() == JsonToken.BEGIN_OBJECT) {
                    if (key != null) {
                        jo.put(key, readObject(in));
                    }
                }else {
                    in.skipValue();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jo;
    }


    /**
     * 解析整个json数据
     *
     * @param in json数据
     * @throws IOException
     */
    private static JSONArray readJsonArray(JsonReader in)
            throws IOException {
        String key = "";
        JSONArray ja = new JSONArray();
        while (in.hasNext()) {
            try {
                if (in.peek() == JsonToken.BEGIN_ARRAY) {
                    ja.put(readArray(in));
                } else if (in.peek() == JsonToken.NUMBER) {
                    ja.put(in.nextDouble());
                } else if (in.peek() == JsonToken.STRING) {
                    ja.put(in.nextString());
                } else if (in.peek() == JsonToken.NULL) {
                    //为null 无需处理
                    in.nextNull();
                } else if (in.peek() == JsonToken.NAME) {
                    //array没有键，不用处理
                    key = in.nextName();
                } else if (in.peek() == JsonToken.BOOLEAN) {
                    ja.put(in.nextBoolean());
                } else if (in.peek() == JsonToken.BEGIN_OBJECT) {
                    ja.put(readObject(in));
                }else {
                    in.skipValue();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ja;
    }


    /**
     * @param type 0(int.class, Integer.class )
     *             1(short.class, Short.class)
     *             2(long.class,Long.class)
     *             3(double.class, Double.class)
     *             4(float.class, Float.class)
     * @return
     */
    public static TypeAdapter<Number> longAdapter(final int type) {

        return new TypeAdapter<Number>() {

            @Override
            public Number read(JsonReader in)
                    throws IOException {
                boolean isNot = false;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    isNot = true;
                } else if (in.peek() == JsonToken.BEGIN_OBJECT) {
                    //增加判断是错误OBJECT的类型（应该是Number）,移动in的下标到结束，移动下标的代码在下方
                    readObject(in);
                    isNot = true;
                } else if (in.peek() == JsonToken.NAME) {
                    //增加判断是错误的name的类型（应该是Number）,移动in的下标到结束，移动下标的代码在下方
                    in.nextName();
                    isNot = true;
                } else if (in.peek() == JsonToken.BOOLEAN) {
                    //增加判断是错误的boolean的类型（应该是Number）,移动in的下标到结束，移动下标的代码在下方
                    in.nextBoolean();
                    isNot = true;
                } else if (in.peek() == JsonToken.BEGIN_ARRAY) {
                    //增加判断是错误的array的类型（应该是Number）,移动in的下标到结束，移动下标的代码在下方
                    readArray(in);
                    isNot = true;
                }
                if (isNot) {
                    switch (type) {
                        case OBJECT_TYPE_INT:
                            return 0;
                        case OBJECT_TYPE_SHORT:
                            return (short) 0;
                        case OBJECT_TYPE_LONG:
                            return (long)0;
                        case OBJECT_TYPE_DOUBLE:
                            return (double) 0;
                        case OBJECT_TYPE_FLOAT:
                            return (float) 0;
                        default:
                            return 0;
                    }
                }

                try {
                    switch (type) {
                        case OBJECT_TYPE_SHORT:
                            //short
                            if (in.peek() == JsonToken.STRING) {
                                //暂不做处理
                                return toShort(in.nextString()).shortValue();
                            }else if(in.peek() == JsonToken.NUMBER){
                                //数字类型

                                // 判断是不是浮点类型，如果是则解析 返回0
                                String numberStr = in.nextString();
                                if (judgeDouble(numberStr)) {
                                    return (short) 0;
                                }

                                // 判断取值范围是否正确，如果不正确，则解析返回0
                                Long aLong = toLong(numberStr);
                                if(aLong.longValue()>=SHORT_MAX||aLong<=SHORT_MIN){
                                    return (short)0;
                                }
                                return (short)aLong.longValue();
                            }

                            return (short) in.nextInt();
                        case OBJECT_TYPE_INT:
                            //int
                            if (in.peek() == JsonToken.STRING) {
                                //暂不做处理
                                return toInt(in.nextString());
                            }else if(in.peek() == JsonToken.NUMBER){
                                //数字类型

                                // 判断是不是浮点类型，如果是则解析 返回0
                                String numberStr = in.nextString();
                                if (judgeDouble(numberStr)) {
                                    return 0;
                                }

                                // 判断取值范围是否正确，如果不正确，则解析返回0
                                Long aLong = toLong(numberStr);
                                if(aLong.longValue()>=INT_MAX||aLong<=INT_MIN){
                                    return 0;
                                }

                                return (int)aLong.longValue();

                            }

                            return in.nextInt();

                        case OBJECT_TYPE_LONG:
                            //long
                            if (in.peek() == JsonToken.STRING) {
                                //暂不做处理
                                return toLong(in.nextString()).longValue();
                            }else if(in.peek() == JsonToken.NUMBER){
                                //数字类型

                                // 判断是不是浮点类型，如果是则解析 返回0
                                String numberStr = in.nextString();
                                if (judgeDouble(numberStr)) {
                                    return (long) 0;
                                }

                                // 判断取值范围是否正确，很大，这里不需要再判断了
                                Long aLong = toLong(numberStr);

                                return aLong.longValue();
                            }
                            return in.nextLong();
                        case OBJECT_TYPE_DOUBLE:
                            //double
                            if (in.peek() == JsonToken.STRING) {
                                //暂不做处理
                                return toDouble(in.nextString()).doubleValue();
                            }
                            return in.nextDouble();
                        case OBJECT_TYPE_FLOAT:
                            //float
                            if (in.peek() == JsonToken.STRING) {
                                //暂不做处理
                                return toFloat(in.nextString()).floatValue();
                            }
                            return (float) in.nextDouble();
                    }
                    return in.nextLong();
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }

            @Override
            public void write(JsonWriter out, Number value)
                    throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                out.value(value);
            }
        };
    }

    private static boolean judgeDouble(String number){
        if(number==null||number.isEmpty()){
            return false;
        }
        if (number.contains(".") || number.contains("e")
                || number.contains("E")){
            return true;
        }
        return false;
    }

    /**
     * 处理字符的适配器
     */
    public static TypeAdapter<String> stringTypeAdapter() {

        return new TypeAdapter<String>() {

            @Override
            public String read(JsonReader in)
                    throws IOException {
                JsonToken peek = in.peek();
                if (peek == JsonToken.NULL) {
                    in.nextNull();
                    return "";
                }
                if (peek == JsonToken.BOOLEAN) {
                    return Boolean.toString(in.nextBoolean());
                }
//
                //增加判断是错误的name的类型（应该是object）,移动in的下标到结束，移动下标的代码在下方
                if (in.peek() == JsonToken.NAME) {
                    in.nextName();
                    return "";
                }

                if (in.peek() == JsonToken.BEGIN_OBJECT) {
                    JSONObject j = readObject(in);
                    if(j==null||j.length()==0){
                        return "";
                    }
                    return StringEscapeUtils.unescapeJson(j.toString());
                }

                //增加判断是错误的ARRAY的类型（应该是object）,移动in的下标到结束，移动下标的代码在下方
                if (in.peek() == JsonToken.BEGIN_ARRAY) {
                    JSONArray j = readArray(in);
                    if(j==null||j.length()==0){
                        return "";
                    }
                    return StringEscapeUtils.unescapeJson(j.toString());
                }
                return in.nextString();
//                return StringEscapeUtils.unescapeJson(in.nextString());
            }

            @Override
            public void write(JsonWriter out, String value)
                    throws IOException {
                out.value(value);
            }
        };
    }

    public static int String2Int(String data) {
        int result = -1;

        try {
            result = Integer.valueOf(data);
        } catch (Exception e) {
            //
        }

        return result;
    }

    /**
     * 整形转换
     *
     * @param data 输入
     * @return Integer
     */
    public static Short toShort(String data) {
        Short result = 0;
        try {
            result = Short.valueOf(data);
        } catch (Exception e) {
            //
        }

        return result;
    }

    /**
     * 整形转换
     *
     * @param data 输入
     * @return Integer
     */
    public static Integer toInt(String data) {
        Integer result = 0;
        try {
            result = Integer.valueOf(data);
        } catch (Exception e) {
            //
        }

        return result;
    }

    /**
     * Long转换
     *
     * @param data 输入
     * @return Long
     */
    public static Long toLong(String data) {
        Long result = 0L;

        try {
            result = Long.valueOf(data);
        } catch (Exception e) {
            //
        }

        return result;
    }

    /**
     * 浮点转换
     *
     * @param data 输入
     * @return Float
     */
    public static Float toFloat(String data) {
        Float result = 0.0f;

        if (data != null && data.length() > 0) {
            try {
                result = Float.valueOf(data);
            } catch (Exception e) {
                //
            }
        }

        return result;
    }

    /**
     * 浮点转换
     *
     * @param data 输入
     * @return Double
     */
    public static Double toDouble(String data) {
        Double result = 0.0;

        try {
            result = Double.valueOf(data);
        } catch (Exception e) {
            //
        }

        return result;
    }

    /**
     * 判断是否是有效的值，0和0.0均视为false
     *
     * @param data 输入
     * @return boolean
     */
    public static boolean isEmpty(String data) {
        String tmp = (data != null ? data.replaceAll(" ", "") : "");

        if (isStringEmpty(tmp)) {
            return true;
        } else {
            try {
                return Double.valueOf(tmp) < 0.00001;
            } catch (Exception e) {
                return false;
            }
        }
    }


    /**
     * @param c
     * @return
     */
    private static boolean isArrayEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    /**
     * @param s
     * @return
     */
    private static boolean isStringEmpty(String s) {
        return s == null || s.trim().length() == 0 || s.equals("null");
    }


}