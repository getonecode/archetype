#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.biz.form;

import ${groupId}.core.helper.Form;
import ${package}.dal.model.BlogDO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class BlogForm extends Form {

    @NotEmpty(message = "标题不能为空")
    private String blogTitle;

    @NotEmpty(message = "内容不能为空")
    private String blogContent;

    @NotNull(message = "状态不能为空")
    private Integer blogStatus;


    public BlogDO toDO() {
        BlogDO blogDO = new BlogDO();
        blogDO.setBlogContent(this.blogContent);
        blogDO.setBlogTitle(this.blogTitle);
        blogDO.setBlogStatus(blogStatus);
        return blogDO;
    }

    public @NotNull(message = "状态不能为空") Integer getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(@NotNull(message = "状态不能为空") Integer blogStatus) {
        this.blogStatus = blogStatus;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

}


