#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ${groupId}.core.helper.BizResult;
import ${groupId}.core.helper.PageQuery;
import ${package}.biz.BlogBiz;
import ${package}.dal.BlogDAO;
import ${package}.dal.model.BlogDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class BlogBizImpl implements BlogBiz{

    private final  Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private BlogDAO blogDAO;

    @Override
    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            BlogDO blogDO = blogDAO.selectById(id);
            bizResult.data.put("blogDO", blogDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query blog error",e);
        }
        return bizResult;
    }

    @Override
    public BizResult list(PageQuery pageQuery) {
        BizResult bizResult = new BizResult();
        try {
            int totalCount = blogDAO.countForPage(pageQuery);
            pageQuery.setTotalCount(totalCount);
            List<BlogDO> blogList = blogDAO.selectForPage(pageQuery);
            bizResult.data.put("blogList", blogList);
            bizResult.data.put("query", pageQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view blog list error", e);
        }
        return bizResult;
    }

    @Override
    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            blogDAO.delById(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete blog error", e);
        }
        return bizResult;
    }

    @Override
    public BizResult create(BlogDO blogDO) {
        BizResult bizResult = new BizResult();
        try {
            long id = blogDAO.insert(blogDO);
            bizResult.data.put("id", id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create blog error", e);
        }
        return bizResult;
    }

    @Override
    public BizResult update(BlogDO blogDO) {
        BizResult bizResult = new BizResult();
        try {
            blogDAO.updateByIdSelective(blogDO);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update blog error", e);
        }
        return bizResult;
    }

}

