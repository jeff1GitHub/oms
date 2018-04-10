package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Linkman;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 联系人服务类接口
 * </p>
 *
 * @author jeff
 * @since 2017-11-02
 */
public interface ILinkmanService extends IService<Linkman> {
    /**
     * 获取所有的联系人
     *
     * @return
     */
    List<Linkman> getLinkmanList(Integer id);

    /**
     * 通过id获取联系人资料
     *
     * @param id 联系人id
     * @return
     */
    Linkman getLinkmanById(Integer id);

    /**
     * 增加联系人
     *
     * @param user    操作人
     * @param linkman 联系人对象
     * @return
     */
    boolean addLinkman(User user, Linkman linkman, String qq, String email, String mob, String phone, String vchat,
			String tg, String skype, String whatapp, Integer defaultContactType);

    /**
     * 修改联系人
     *
     * @param user    操作人
     * @param linkman 联系人对象
     * @return
     */
    boolean updateLinkman(User user, Linkman linkman);

    /**
     * 通过id删除联系人
     *
     * @param user 操作人
     * @param id   联系人id
     * @return
     */
    boolean delLinkman(User user, Integer id);

    /**
     * 通过删除指定供应商的联系人
     *
     * @param user 操作人
     * @param id   供应商id
     * @return
     */
    boolean delLinkmanByProducerId(User user, Integer id);

	/**
	 * @param id
	 * @return
	 */
	Linkman getLinkmanWithInfoById(Integer id);

	/**
	 * 设置联系人为默认，且其他为非默认
	 * 
	 * @param user
	 * @param linkman
	 * @return 
	 */
	boolean updateLinkmanIsDefault(User user, Linkman linkman);
}
