#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal;


import java.util.List;

import ${groupId}.core.helper.PageQuery;
import ${package}.dal.model.BlogDO;

public interface BlogDAO {

        Long insert(BlogDO blogDO);

        BlogDO selectById(Long blogId);

        boolean delById(Long blogId);

        List<BlogDO> selectByIds(List idsList);

        List<BlogDO> selectForPage(PageQuery pageQuery);

        int countForPage(PageQuery pageQuery);

        void updateByIdSelective(BlogDO  blogDO);
}


