#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz;

import com.google.inject.ImplementedBy;
import ${groupId}.core.annotation.biz.Biz;
import ${groupId}.core.helper.BizResult;
import ${groupId}.core.helper.PageQuery;
import ${package}.biz.impl.BlogBizImpl;
import ${package}.dal.model.BlogDO;

@Biz
@ImplementedBy(BlogBizImpl.class)
public interface BlogBiz {

    BizResult detail(long id);

    BizResult list(PageQuery pageQuery);

    BizResult delete(long id);

    BizResult create(BlogDO blogDO);

    BizResult update(BlogDO blogDO);

}
