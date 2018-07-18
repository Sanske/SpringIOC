package config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sanske
 * @date 2018/7/16 下午4:54
 * //解析 xml 多个 Bean 对象解析到map 中
 **/
public class XmlConfig {

    public static Map<String, Bean> getConfig(String path){

        Map<String, Bean> configMap = new HashMap<String, Bean>();
        //使用dom4j和xpath读取xml文件
        Document doc = null;
        SAXReader reader = new SAXReader();
        try {
            doc = reader.read(path);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("请检查您的xml配置文件路径是否正确！");
        }
        //定义xpath，取出所有的bean
        String xpath = "//bean";
        //对bean进行遍历
        List<Element> list = doc.selectNodes(xpath);
        if(list!=null){
            for (Element beanEle : list) {
                Bean bean = new Bean();
                //bean节点的id
                String id = beanEle.attributeValue("id");
                //bean节点的class属性
                String className = beanEle.attributeValue("class");
                //封装到bean对象中
                bean.setId(id);
                bean.setClassName(className);

                //获取bean节点下所有的property节点
                List<Element> proList = beanEle.elements("property");
                if(proList != null){
                    for (Element proEle : proList) {
                        Property prop = new Property();
                        String propName = proEle.attributeValue("name");
                        String propValue = proEle.attributeValue("value");
                        String propRef = proEle.attributeValue("ref");
                        //封装到property属性中
                        prop.setName(propName);
                        prop.setValue(propValue);
                        prop.setRef(propRef);

                        bean.getProperties().add(prop);
                    }
                }
                //id是不应重复的
                if(configMap.containsKey(id)){
                    throw new RuntimeException("bean节点ID重复：" + id);
                }
                //将bean封装到map中
                configMap.put(id, bean);
            }
        }
        return configMap;
    }

}
