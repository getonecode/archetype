#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.server;

import ${groupId}.core.eventbus.EventBusVerticle;
import ${groupId}.core.eventbus.context.AppContext;
import ${groupId}.core.eventbus.msg.HttpEventMsg;
import ${groupId}.core.eventbus.msg.HttpMsgConvert;
import ${groupId}.core.util.JsonConfigUtil;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.impl.Args;
import io.vertx.core.impl.VertxFactoryImpl;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.VertxFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by well on 2017/3/25.
 */
public class EventServerStart {

    private static Logger log= LoggerFactory.getLogger(EventServerStart.class);

    public static void main(String[] sargs){
        Args args = new Args(sargs);
        String confArg = args.map.get("-conf");
        if(confArg==null){
            confArg="dev";
        }

        log.warn("server start use conf:"+confArg);

        JsonObject config= JsonConfigUtil.getConfig(EventServerStart.class).getJsonObject(confArg);
        JsonObject sys = config.getJsonObject("sys");
        sys.forEach(entry->{
            System.getProperties().put(entry.getKey(),entry.getValue());
        });


        AppContext appContext= AppContext.create(config);

        VertxFactory factory = new VertxFactoryImpl();
        final Vertx vertx = factory.vertx();

        final DeploymentOptions deploymentOptions = new DeploymentOptions();
        deploymentOptions.setConfig(config);

        EventBusVerticle eventBusVerticle=new EventBusVerticle(appContext);
        vertx.deployVerticle(eventBusVerticle,deploymentOptions,res -> {
            if (res.succeeded()) {
                System.out.println("Deployment main eventbusVerticle id is: " + res.result());
            } else {
                System.out.println("Deployment failed!");
                res.cause().printStackTrace();
            }
        });


        vertx.deployVerticle("${groupId}.core.eventbus.HttpConsumerVerticle",readConsumeOpts(),res -> {
            if (res.succeeded()) {
                System.out.println("Deployment id is: " + res.result());
            } else {
                System.out.println("Deployment failed!");
                res.cause().printStackTrace();
            }
        });



        vertx.eventBus().registerDefaultCodec(HttpEventMsg.class, new HttpMsgConvert());

    }



    public static DeploymentOptions readConsumeOpts(){
        final DeploymentOptions options = new DeploymentOptions();
        //options.setHa(false);
        options.setInstances(5);
        options.setWorker(true);
        options.setMultiThreaded(true);
        return options;

    }
}
