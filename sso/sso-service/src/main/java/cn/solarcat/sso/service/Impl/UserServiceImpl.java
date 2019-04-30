package cn.solarcat.sso.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
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
import cn.solarcat.common.util.MD5Utils;
import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.mapper.TbUserMapper;
import cn.solarcat.pojo.TbUser;
import cn.solarcat.pojo.TbUserExample;
import cn.solarcat.pojo.TbUserExample.Criteria;
import cn.solarcat.sso.service.UserService;

@Service
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	private Integer SESSION_EXPIRE = 1800;

	@Override
	@Log(action = ACTION.SELECT, level = LEVEL.SERVICE)
	public SolarCatResult checkData(String param, int type) {
		// 1、从tb_user表中查询数据
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		// 2、查询条件根据参数动态生成。
		// 1、2、3分别代表username、phone、email
		if (type == 1) {
			criteria.andUsernameEqualTo(param);
		} else if (type == 2) {
			criteria.andPhoneEqualTo(param);
		} else if (type == 3) {
			criteria.andEmailEqualTo(param);
		} else {
			return SolarCatResult.build(400, "非法的参数");
		}
		// 执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		// 3、判断查询结果，如果查询到数据返回false。
		if (list == null || list.size() == 0) {
			// 4、如果没有返回true。
			return SolarCatResult.ok(true);
		}
		// 5、使用e3Result包装，并返回。
		return SolarCatResult.ok(false);
	}

	@Override
	@Log(action = ACTION.ADD, level = LEVEL.SERVICE)
	public SolarCatResult register(TbUser user) {
		// 数据有效性校验
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())
				|| StringUtils.isBlank(user.getPhone())) {
			return SolarCatResult.build(400, "信息不完整，注册失败");
		}
		SolarCatResult result = checkData(user.getUsername(), 1);
		if (!(boolean) result.getData()) {
			return SolarCatResult.build(400, "此用户名已经被占用");
		}
		result = checkData(user.getPhone(), 2);
		if (!(boolean) result.getData()) {
			return SolarCatResult.build(400, "手机号已经被占用");
		}
		user.setCreated(new Date());
		user.setUpdated(new Date());
		String md5Pass = MD5Utils.MD5EncodeUtf8(user.getPassword());
		user.setPassword(md5Pass);
		userMapper.insert(user);
		return SolarCatResult.ok();
	}

	@Override
	@Log(action = ACTION.SELECT, level = LEVEL.SERVICE)
	public SolarCatResult login(String username, String password) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return SolarCatResult.build(400, "用户名或密码错误");
		}
		TbUser user = list.get(0);
		// 校验密码
		String md5Pass = MD5Utils.MD5EncodeUtf8(password);
		if (!user.getPassword().equals(md5Pass)) {
			return SolarCatResult.build(400, "用户名或密码错误");
		}
		// 2、登录成功后生成token。Token相当于原来的jsessionid，字符串，可以使用uuid。
		String token = UUID.randomUUID().toString();
		// 3、把用户信息保存到redis。Key就是token，value就是TbUser对象转换成json。
		// 4、使用String类型保存Session信息。可以使用“前缀:token”为key
		user.setPassword(null);
		redisTemplate.opsForValue().set("SESSION:" + token, JSONObject.toJSONString(user));
		// 5、设置key的过期时间。模拟Session的过期时间。一般半个小时。
		redisTemplate.expire("SESSION:" + token, SESSION_EXPIRE, TimeUnit.SECONDS);
		// 6、返回e3Result包装token。
		return SolarCatResult.ok(token);
	}

}
