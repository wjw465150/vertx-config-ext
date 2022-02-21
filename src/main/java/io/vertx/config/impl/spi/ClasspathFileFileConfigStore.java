package io.vertx.config.impl.spi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    try(BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(getConfigStream(this.path))))) {
      String line;
      StringBuilder sb = new StringBuilder();
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
      
      Buffer buffer = Buffer.buffer(sb.toString(), "UTF-8");
      
      return Future.succeededFuture(buffer);
    } catch (IOException ioex) {
      return Future.failedFuture(ioex);
    }
  }

  @Override
  public Future<Void> close() {
    return vertx.getOrCreateContext().succeededFuture();
  }
  
  private InputStream getConfigStream(String resourceLocation) {
    ClassLoader ctxClsLoader = Thread.currentThread().getContextClassLoader();
    InputStream is = null;
    if (ctxClsLoader != null) {
      is = ctxClsLoader.getResourceAsStream(resourceLocation);
    }
    return is;
  }
  
}
