import javafx.beans.binding.ObjectExpression;

import java.lang.reflect.Field;

/**
 * @author sanske
 * @date 2018/7/17 上午10:05
 **/
public class Reflect {
    public static void main(String[] arg) {
      String sourceName = "Entity.Address";
        try {
            Class<?> className = Class.forName(sourceName);
            Object object = className.newInstance();
            Field field = className.getDeclaredField("city");
            field.setAccessible(true);
            field.set(object, "杭州");

            System.out.println(field.get(object));
            //获取属性名称 直接使用set和get方法即可；

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
