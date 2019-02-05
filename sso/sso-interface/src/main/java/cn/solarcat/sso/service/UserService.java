package cn.solarcat.sso.service;

import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.pojo.TbUser;

public interface UserService {

	SolarCatResult checkData(String param, int type);

	SolarCatResult register(TbUser user);

	SolarCatResult login(String username, String password);
}
