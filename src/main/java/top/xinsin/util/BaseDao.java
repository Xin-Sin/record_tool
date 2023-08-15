package top.xinsin.util;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xinsin
 * Created On 2023/8/15 11:45
 * @version 1.0
 */
public class BaseDao {
    private final static JDBCDataSource dataSource = new JDBCDataSource();

    public static int updateForOne(String sql,Object... params){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1,params[i]);
            }
            System.out.println("执行sql语句为: " + preparedStatement.toString().substring(43));
            return preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @SneakyThrows
    public static <T> List<T> selectForList(String sql,Class<T> clazz,Object... params){
        JDBCDataSource jdbcDataSource = new JDBCDataSource();
        Connection connection = jdbcDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i,params[i]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<T> returnData = new ArrayList<>();
        while (resultSet.next()){
            returnData.add(toEntity(resultSet,clazz));
        }
        return returnData;
    }

    public static <T> T toEntity(ResultSet resultSet,Class<T> clazz) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        T t = clazz.getDeclaredConstructor().newInstance();
        for (Field field : fields) {
            String name = field.getName();
            String DbName = field.getName();
//            命名规范转换小驼峰转下划线
            for (int i = 0; i < name.length(); i++) {
                char c = name.charAt(i);
                if (Character.isUpperCase(c)){
                    String preNameFix = name.substring(0, i);
                    String endNameFix = name.substring(i).toLowerCase();
                    DbName = preNameFix + "_" + endNameFix;
                }
            }
            String typeName = field.getType().getSimpleName().toLowerCase();
            switch (typeName){
                case "integer" -> typeName = "int";
                case "date" -> typeName = "object";
            }

            char upperCase = Character.toUpperCase(typeName.charAt(0));
            typeName = upperCase + typeName.substring(1);

            Class<ResultSet> resultSetClass = ResultSet.class;
            Method method = resultSetClass.getMethod("get" + typeName, String.class);
            Object invoke;
            try {
                invoke = method.invoke(resultSet, DbName);
            } catch (InvocationTargetException | IllegalAccessException e) {
                System.err.println(DbName);
                System.err.println(typeName);
                continue;
            }
            Class<?> aClass;
            if (field.getType().getSimpleName().equalsIgnoreCase("date")){
                if (invoke != null && !invoke.toString().isEmpty()){
                    invoke = new SimpleDateFormat("yyyy-MM-dd").parse((String) invoke);
                }else {
                    invoke = null;
                }
                aClass = Class.forName("java.util.Date");
            }else{
                aClass = Class.forName(field.getType().getName());
            }
            Method method1 = clazz.getMethod("set" + Character.toUpperCase(name.charAt(0)) + name.substring(1),aClass);
            method1.invoke(t,invoke);
        }
        return t;
    }
}
