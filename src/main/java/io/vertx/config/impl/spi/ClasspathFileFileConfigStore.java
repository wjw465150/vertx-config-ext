package io.vertx.config.impl.spi;

import io.vertx.config.spi.ConfigStore;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.json.JsonObject;

/**
 * A configuration store loading a file from classpath.
 *
 * @author <a href="https://github.com/wjw465150">WStone</a>
 */
public class ClasspathFileFileConfigStore implements ConfigStore {
  private final VertxInternal vertx;
  private final String path;

  public ClasspathFileFileConfigStore(Vertx vertx, JsonObject configuration) {
    this.vertx = (VertxInternal) vertx;
    this.path = configuration.getString("path");
    if (this.path == null) {
      throw new IllegalArgumentException("The `path` configuration is required.");
    }
  }

  @Override
  public Future<Buffer> get() {
    return vertx.fileSystem().readFile(this.path);
  }

  @Override
  public Future<Void> close() {
    return vertx.getOrCreateContext().succeededFuture();
  }
   
}
