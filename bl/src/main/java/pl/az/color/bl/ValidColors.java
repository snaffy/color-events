package pl.az.color.bl;

import io.micronaut.context.annotation.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Singleton
@Getter
@AllArgsConstructor
public class ValidColors {

    private Set<String> validColors;

    @Inject
    public void setValidColors(@Property(name = "mappping") Map<String, String> mapping) {
        this.validColors = new HashSet<>(mapping.values());
    }
}
