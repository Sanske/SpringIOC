import Entity.Address;
import Entity.User;
import config.Bean;
import config.XmlConfig;
import core.BeanFactory;
import core.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author sanske
 * @date 2018/7/17 下午4:19
 **/
public class springIOC {
    public static void main(String[] arg) {
        BeanFactory bf = new ClassPathXmlApplicationContext("./src/main/java/ApplicationContext.xml");
        User user = (User) bf.getBean("user");
        System.out.println(user);
        System.out.println("address hashcode:"+user.getAddress().hashCode());

        Address address = (Address) bf.getBean("address");
        System.out.println(address);
        System.out.println("address hashcode:"+address.hashCode());
        System.out.println("*********************");
        testConfig();
    }

    private static void testConfig(){
        Map<String, Bean> map = XmlConfig.getConfig("./src/main/java/ApplicationContext.xml");
        for (Map.Entry<String, Bean> entry : map.entrySet()) {
            // 再输出的时候重写toString不写toString的区别
            System.out.println(entry.getKey()+"==="+entry.getValue());
        }
    }
}
