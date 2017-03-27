#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal;

import ${groupId}.gen.GenContext;
import ${groupId}.gen.GenDAO;

import java.io.File;

/**
 * Created by well on 2017/3/27.
 */
public class GenDAOTest {

    private static String jdbUrl = "jdbc:mysql://127.0.0.1:3306/${parentArtifactId}?useUnicode=true&characterEncoding=utf8";

    public static void main(String[] args){
        String baseDir = System.getProperties().get("user.dir") + File.separator + "${parentArtifactId}-dal";
        GenContext genContext = new GenContext("com.mysql.jdbc.Driver", jdbUrl, "${parentArtifactId}_user", "${parentArtifactId}_pwd", baseDir, "${package}.dal");

        System.out.println(baseDir);
        GenDAO genDAO = new GenDAO(genContext);
        genDAO.genBatch("b_blog", "b_");
    }
}
