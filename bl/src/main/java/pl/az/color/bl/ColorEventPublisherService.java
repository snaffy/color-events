package pl.az.color.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Objects;

@Singleton
class ColorEventPublisherService {

    private static final Logger LOG = LoggerFactory.getLogger(ColorEventPublisherService.class);
    private final RouteMapping routeMapping;
    private final ColorPublisherClient colorPublisherClient;

    @Inject
    public ColorEventPublisherService(RouteMapping routeMapping, ColorPublisherClient colorPublisherClient) {
        this.routeMapping = routeMapping;
        this.colorPublisherClient = colorPublisherClient;
    }

    void publish(List<ColorEvent> colorEvents) {
        for (ColorEvent event : colorEvents) {
            String route = routeMapping.getRouteByKey(event.getColor());

            if (Objects.isNull(route)){
                LOG.error("Unable to determine router, please check configuration...");
                continue;
            }

            colorPublisherClient.publish(route, event.getColor());
        }
    }
}
