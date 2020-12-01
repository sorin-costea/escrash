package test.sc.escrash;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.jboss.logging.Logger;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@QuarkusMain
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class App {
  private static Logger logger = Logger.getLogger(App.class);

  public static void main(final String[] args) throws IOException {
    logger.debug("Starting...");
    Quarkus.run(args);
  }
}
