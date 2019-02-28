package cn.solarcat.sso.service.Impl;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;

import cn.solarcat.aop.Log;
import cn.solarcat.common.pojo.ACTION;
import cn.solarcat.common.pojo.LEVEL;
import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.pojo.TbUser;
import cn.solarcat.sso.service.TokenService;

@Service
@Component
public class TokenServiceImpl implements TokenService {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	private int SESSION_EXPIRE = 1800;

	@Override
	@Log(action = ACTION.SELECT, level = LEVEL.SERVICE)
	public SolarCatResult getUserByToken(String token) {
		String json = redisTemplate.opsForValue().get("SESSION:" + token);
		if (StringUtils.isBlank(json)) {
			return SolarCatResult.build(201, "用户登录已经过期");
		}
		redisTemplate.expire("SESSION:" + token, SESSION_EXPIRE, TimeUnit.SECONDS);
		TbUser user = JSONObject.parseObject(json, TbUser.class);
		return SolarCatResult.ok(user);
	}

}
