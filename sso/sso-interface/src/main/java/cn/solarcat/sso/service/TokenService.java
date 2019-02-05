package cn.solarcat.sso.service;

import cn.solarcat.common.util.SolarCatResult;

public interface TokenService {
	SolarCatResult getUserByToken(String token);
}
