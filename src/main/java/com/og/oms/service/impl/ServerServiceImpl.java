package com.og.oms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.ServerMapper;
import com.og.oms.exception.OmsException;
import com.og.oms.model.Server;
import com.og.oms.model.User;
import com.og.oms.service.IServerService;
import com.og.oms.utils.PasswordUtil;

/**
 * <p>
 * 服务器信息服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-04
 */
@Service
public class ServerServiceImpl extends ServiceImpl<ServerMapper, Server> implements IServerService {

	@Override
    public List<Server> getServerList() {
        EntityWrapper<Server> wrapper = new EntityWrapper<Server>();
        return this.selectList(wrapper);
    }
	
	@Override
    public List<Server> getServerListForDropDown() {
        return this.baseMapper.getServerListForDropDown();
    }
    
	@Override
	public List<Server> getServerList(String serverCode, String startDay, String endDay, String linkAddr) {
		Map<String, String> map = new HashMap<String, String>();
		if(!StringUtils.isEmpty(serverCode)) {
			map.put("serverCode", serverCode);
		}
		
		if(!StringUtils.isEmpty(linkAddr)) {
			map.put("linkAddr", linkAddr.trim());
		}
		
		if(!StringUtils.isEmpty(startDay)) {
			map.put("startDay", startDay + " 00:00:01");
		}
		
		if(!StringUtils.isEmpty(endDay)) {
			map.put("endDay", endDay + " 23:59:59");
		}
		
        return this.baseMapper.getServerList(map);
	}

    @Override
    public Server getServerById(Integer id) {
        Server server = this.selectById(id);

        // 解密普通用户登录密码
        if(!StringUtils.isEmpty(server.getPassword())) {
            server.setPassword(PasswordUtil.decrypt(server.getPassword()));
        }
        // 解密超级用户密码
        if(!StringUtils.isEmpty(server.getPassword())) {
            server.setSuperPassword(PasswordUtil.decrypt(server.getSuperPassword()));
        }
        // 解密IDARC密码：
        if(!StringUtils.isEmpty(server.getIdarcPassword())) {
            server.setIdarcPassword(PasswordUtil.decrypt(server.getIdarcPassword()));
        }
        return server;
    }

    @Override
    public boolean addServer(User user, Server server) {
    	// 判断连接地址是否重复
    	String linkAddr = server.getLinkAddr();
    	if(!StringUtils.isEmpty(linkAddr)) {
    		EntityWrapper<Server> wrapper = new EntityWrapper<>();
    		server.setLinkAddr(server.getLinkAddr().trim());
    		wrapper.eq("link_addr", server.getLinkAddr());
    		int selectCount = this.selectCount(wrapper);
    		if(selectCount > 0) {
    			throw new OmsException("服务器连接地址重复！");
    		}
    	}
    	
        // 加密普通用户登录密码
        if(!StringUtils.isEmpty(server.getPassword())) {
            server.setPassword(PasswordUtil.encrypt(server.getPassword()));
        }
        // 加密超级用户密码
        if(!StringUtils.isEmpty(server.getSuperPassword())) {
            server.setSuperPassword(PasswordUtil.encrypt(server.getSuperPassword()));
        }

        // 加密IDARC密码：
        if(!StringUtils.isEmpty(server.getIdarcPassword())) {
            server.setIdarcPassword(PasswordUtil.encrypt(server.getIdarcPassword()));
        }

        server.setCreateTime(new Date());
        server.setCreateUser(user.getAccount());
        
        
        return this.insert(server);
    }

    @Override
    public boolean updateServer(User user, Server server) {
    	// 判断连接地址是否重复
    	String linkAddr = server.getLinkAddr();
    	if(!StringUtils.isEmpty(linkAddr)) {
    		EntityWrapper<Server> wrapper = new EntityWrapper<>();
    		server.setLinkAddr(server.getLinkAddr().trim());
    		wrapper.eq("link_addr", server.getLinkAddr());
    		wrapper.ne("id", server.getId());
    		int selectCount = this.selectCount(wrapper);
    		if(selectCount > 0) {
    			throw new OmsException("服务器连接地址重复！");
    		}
    	}
    	
        // 加密普通用户登录密码
        if(!StringUtils.isEmpty(server.getPassword())) {
            server.setPassword(PasswordUtil.encrypt(server.getPassword()));
        }
        // 加密超级用户密码
        if(!StringUtils.isEmpty(server.getSuperPassword())) {
            server.setSuperPassword(PasswordUtil.encrypt(server.getSuperPassword()));
        }

        // 加密IDARC密码：
        if(!StringUtils.isEmpty(server.getIdarcPassword())) {
            server.setIdarcPassword(PasswordUtil.encrypt(server.getIdarcPassword()));
        }

        server.setUpdateCreate(new Date());
        server.setUpdateUser(user.getAccount());
        return this.updateAllColumnById(server);
    }

    @Override
    public boolean delServer(User user, Integer id) {
        return this.deleteById(id);
    }
}
