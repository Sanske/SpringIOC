package config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sanske
 * @date 2018/7/16 下午4:42
 * 存储 xml Bean节点信息
 **/
public class Bean {
    private String id;
    private String className;
    List<Property> properties = new ArrayList<Property>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }


    @Override
    public String toString() {
        return "Bean{" +
                "id='" + id + '\'' +
                ", className='" + className + '\'' +
                ", properties=" + properties +
                '}';
    }

}
