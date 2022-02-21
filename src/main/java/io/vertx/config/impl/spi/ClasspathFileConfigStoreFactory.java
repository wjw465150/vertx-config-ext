package io.vertx.config.impl.spi;

import io.vertx.config.spi.ConfigStore;
import io.vertx.config.spi.ConfigStoreFactory;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * The factory creating Json File configuration stores.
 *
 * @author <a href="https://github.com/wjw465150">WStone</a>
 */
public class ClasspathFileConfigStoreFactory implements ConfigStoreFactory {

  @Override
  public String name() {
    return "classpath";
  }

  @Override
  public ConfigStore create(Vertx vertx, JsonObject configuration) {
    return new ClasspathFileFileConfigStore(vertx, configuration);
  }
}
