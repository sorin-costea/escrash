package test.sc.escrash;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Api {
  private static Logger logger = Logger.getLogger(Api.class);

  @GET
  @Path("test")
  public Response getTest() {
    logger.infov("Getting test");
    try {
      final HttpHost host = new HttpHost("localhost", 443, "https");
      final RestClientBuilder clientBuilder = RestClient.builder(host);
      final RestHighLevelClient client = new RestHighLevelClient(clientBuilder);
      final BulkProcessor bulkProcessor = BulkProcessor
          .builder((request, bulkListener) -> client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener),
              new BulkProcessor.Listener() {
                @Override
                public void beforeBulk(final long executionId, final BulkRequest request) {
                  // TODO Auto-generated method stub
                }

                @Override
                public void afterBulk(final long executionId, final BulkRequest request, final BulkResponse response) {
                  // TODO Auto-generated method stub
                }

                @Override
                public void afterBulk(final long executionId, final BulkRequest request, final Throwable failure) {
                  // TODO Auto-generated method stub
                }
              })
          .build();
      bulkProcessor.close();
      client.close();
    } catch (final Exception e) {
      logger.infov("Failed test");
      return Response.serverError().entity("{\"answer\": \"" + e.getLocalizedMessage() + "\"}")
          .type(MediaType.APPLICATION_JSON).build();
    }
    logger.infov("Done test");
    return Response.ok().entity("{\"answer\": \"ok\"}").type(MediaType.APPLICATION_JSON).build();
  }

}
