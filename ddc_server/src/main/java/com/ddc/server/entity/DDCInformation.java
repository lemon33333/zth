package com.ddc.server.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.*;

import javax.print.DocFlavor;
import java.io.Serializable;

/**
 * <p>
 * 管理员
 * </p>
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("ddc_information")

public class DDCInformation extends Model<DDCInformation> {

    private static final long serialVersionUID = 1L;

    /**
     * 资讯主键
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

//    @TableField(value = "article_title")
    private String articleTitle;

    //@TableField(value = "simple_title")
    private String simpleTitle;

    //@TableField(value = "classify_id")
    private long classifyId;

    //@TableField(value = "title_type")
    private String titleType;

    private Integer sort;

    private String keyword;

    @TableField(value = "abstract")
    private String abs;

    /**
     * 文章作者
     */
    private String autor;

    /**
     * 文章来源
     */
    private String source;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Long createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Long updateTime;

    /**
     * 删除标志 0 未删除 1 已删除 默认 0
     */
    @TableField(value = "del_flag")
    private Integer delFlag;



    public DDCInformation(String articleTitle, String simpleTitle, long classifyId, String titleType, Integer sort, String keyword) {
        this.articleTitle = articleTitle;
        this.simpleTitle = simpleTitle;
        this.classifyId = classifyId;
        this.titleType = titleType;
        this.sort = sort;
        this.keyword = keyword;
    }

    public DDCInformation(Long id,String articleTitle, String simpleTitle, long classifyId, String titleType, Integer sort, String keyword,String abs,String autor,String source,String content,Long createBy,Long createTime,Long updateBy,Long updateTime) {
        this.id=id;
        this.articleTitle = articleTitle;
        this.simpleTitle = simpleTitle;
        this.classifyId = classifyId;
        this.titleType = titleType;
        this.sort = sort;
        this.keyword = keyword;
        this.abs=abs;
        this.autor=autor;
        this.source=source;
        this.content=content;
        this.createBy=createBy;
        this.createTime=createTime;
        this.updateBy=updateBy;
        this.updateTime=updateTime;
    }

    public DDCInformation(String simpleTitle){
        this.simpleTitle=simpleTitle;
    }
}



