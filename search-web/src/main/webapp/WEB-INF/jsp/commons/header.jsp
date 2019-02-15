<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--shortcut start-->
<jsp:include page="shortcut.jsp" />
<!--shortcut end-->
<div id="header">
	<div class="header_inner">
		<div class="logo">
			<div class="logo-text">
				<img src="/images/html/logo_word.jpg" alt="">
			</div>
		</div>
		<div class="index_promo"></div>
		<div class="search">
			<form action="http://search.view.solarcat.cn/search" id="searchForm" name="query" method="GET">
				<input type="text" class="text keyword ac_input" name="keyword" id="keyword" value="${query }" style="color: rgb(153, 153, 153);" onkeydown="javascript:if(event.keyCode==13) search_keys('searchForm');" autocomplete="off"> <input type="button" value="" class="submit" onclick="search_keys('searchForm')">
			</form>
			<div class="search_hot">
				<a target="_blank" href="#">大闸蟹</a>
				<a target="_blank" href="#">石榴</a>
				<a target="_blank" href="#">松茸</a>
				<a target="_blank" href="#">牛排</a>
				<a target="_blank" href="#">白虾</a>
				<a target="_blank" href="#">全脂牛奶</a>
				<a target="_blank" href="#">洋河</a>
				<a target="_blank" href="#">绿豆</a>
				<a target="_blank" href="#">一品玉</a>
			</div>
		</div>
		<div class="shopingcar" id="topCart">
			<s class="setCart"></s>
			<a href="http://cart.view.solarcat.cn" class="t" rel="nofollow">我的购物车</a>
			<b id="cartNum">0</b>
			<span class="outline"></span>
			<span class="blank"></span>
			<div id="cart_lists">
				<!--cartContent-->
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
  	function search_keys(formName){
	   $('#'+formName).submit();
	}
  </script>
</div>