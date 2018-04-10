package com.og.oms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author jeff
 * @since 2017-10-25
 */
@TableName("sys_permis")
public class Permis extends Model<Permis> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 显示名称
     */
    private String name;
    /**
     * url控制
     */
    private String url;
    /**
     * 上一级权限
     */
    private Integer parent;
    /**
     * 排序下标
     */
    private Integer index;

    @TableField(exist = false)
    private List<Permis> childrenPermis = new ArrayList<>();

    /**
     * 增加子权限
     *
     * @param permis
     */
    public void addPermis(Permis permis) {
        childrenPermis.add(permis);
    }

    /**
     * 获取所有子权限
     *
     * @return
     */
    public List<Permis> getChildrenPermis() {
        return this.childrenPermis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Permis{" + "id=" + id + ", name=" + name + ", url=" + url + ", parent=" + parent + ", index=" + index + "}";
    }

//	@Override
//	public boolean equals(Object obj) {
//		if(obj instanceof Permis) {
//			Permis permis = (Permis)obj;
//			return permis.getId().intValue() == id.intValue() 
//					&& permis.getIndex().intValue() == index.intValue()
//					&& permis.getParent().intValue() == parent.intValue();
//		}
//		return false;
//	}
    
}
