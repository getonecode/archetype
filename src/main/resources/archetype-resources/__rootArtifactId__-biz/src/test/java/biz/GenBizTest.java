#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz;

import ${groupId}.gen.GenBiz;
import ${groupId}.gen.GenContext;

import java.io.File;

/**
 * Created by well on 2017/3/27.
 */
public class GenBizTest {

    public static void main(String[] args) throws Exception {
        String baseDir = System.getProperties().get("user.dir") + File.separator + "${parentArtifactId}-biz";

        GenContext genContext = new GenContext(baseDir, "${package}.dal.model.BlogDO", "${package}.biz");

        System.out.println(baseDir);
        GenBiz genBiz = new GenBiz(genContext);

        genBiz.gen();
    }
}
