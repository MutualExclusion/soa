package cn.solarcat.cart.service;

import java.util.List;

import cn.solarcat.common.util.SolarCatResult;
import cn.solarcat.pojo.TbItem;

public interface CartService {

	SolarCatResult addCart(long userId, long itemId, int num);

	SolarCatResult mergeCart(long userId, List<TbItem> itemList);

	List<TbItem> getCartList(long userId);

	SolarCatResult updateCartNum(long userId, long itemId, int num);

	SolarCatResult deleteCartItem(long userId, long itemId);
}
