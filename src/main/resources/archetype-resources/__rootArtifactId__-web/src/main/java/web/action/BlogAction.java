#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.action;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ${groupId}.core.annotation.action.Action;
import ${groupId}.core.annotation.action.Req;
import ${groupId}.core.annotation.action.ReqParam;
import ${groupId}.core.annotation.action.View;
import ${groupId}.core.enums.ViewTypeEnum;
import ${groupId}.core.helper.BizResult;
import ${groupId}.core.helper.JsonResult;
import ${groupId}.core.helper.PageQuery;

import ${package}.biz.BlogBiz;
import ${package}.biz.form.BlogEditForm;
import ${package}.biz.form.BlogForm;
import ${package}.dal.model.BlogDO;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by well on 2017/3/24.
 */
@Action
@Req(value = "/blog")
@Singleton
public class BlogAction {

    @Inject
    private BlogBiz blogBiz;

    @Req(value = "/index", method = HttpMethod.GET)
    public String index(RoutingContext context, PageQuery pageQuery) {
        pageQuery.setTotalCount(100);
        BizResult list = blogBiz.list(pageQuery);
        context.data().putAll(list.data);
        return "blog/index.ftl";
    }

    @Req(value = "/create", method = HttpMethod.GET)
    public String create() {
        return "blog/create.ftl";
    }

    @Req(value = "/doCreate", method = HttpMethod.POST)
    public String doCreate(BlogForm blogForm) {
        if (blogForm.validate()) {
            return "blog/create.ftl";
        }
        BizResult bizResult = blogBiz.create(blogForm.toDO());
        if (bizResult.success) {
            return "redirect:/blog/index";
        } else {
            return "error.ftl";
        }

    }

    @Req(value = "/edit", method = HttpMethod.GET)
    public String edit(@ReqParam("blogId") Long blogId,BlogEditForm blogEditForm) {
        BizResult detail = blogBiz.detail(blogId);
        blogEditForm.init((BlogDO)detail.data.get("blogDO"));
        return "blog/edit.ftl";
    }

    @Req(value = "/doUpdate", method = HttpMethod.POST)
    public String doUpdate(BlogEditForm blogEditForm) {
        if (blogEditForm.validate()) {
            return "blog/edit.ftl";
        }
        BizResult bizResult = blogBiz.update(blogEditForm.toDO());
        if (bizResult.success) {
            return "redirect:/blog/index";
        } else {
            return "error.ftl";
        }

    }

    @Req(value = "/doDel", method = HttpMethod.POST)
    @View(type = ViewTypeEnum.json)
    public JsonResult doDel(@ReqParam("blogId") Long blogId) {
        JsonResult jsonResult = JsonResult.getInstance();
        if (blogId == null) {
            jsonResult.setMsg("参数不能为空");
            return jsonResult;
        }
        BizResult bizResult = blogBiz.delete(blogId);
        if (bizResult.success) {
            jsonResult.setSuccess(true);
            return jsonResult;
        } else {
            jsonResult.setMsg(bizResult.msg);
            return jsonResult;
        }


    }
}
