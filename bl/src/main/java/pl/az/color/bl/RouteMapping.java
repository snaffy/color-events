package pl.az.color.bl;

import io.micronaut.context.annotation.Property;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Singleton
@Getter
public class RouteMapping {

    private Map<String, String> mapping;

    @Inject
    public void setMapping(@Property(name = "mappping") Map<String, String> mapping) {
        this.mapping = createWithValueAsKey(mapping);
    }

    public String getRouteByKey(String key) {
        return mapping.get(key);
    }

    public List<String> getRoutes() {
        return new ArrayList<>(mapping.values());
    }

    private Map<String, String> createWithValueAsKey(Map<String, String> mapping) {
        Map<String, String> map = new HashMap<>();
        mapping.forEach((key, value) -> map.put(value, key));
        return map;
    }
}
