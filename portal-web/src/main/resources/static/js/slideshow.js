/*
* @Author: 超级无敌可爱的小悦悦
* @Date:   2019-03-28 13:41:31
* @Last Modified by:   超级无敌可爱的小悦悦
* @Last Modified time: 2019-03-28 14:06:47
*/
window.onload = function(){

    var imageCount = 5; //页面中用来轮播的图片有5张不同的
    //轮播图大盒子
    var banner = document.querySelector('.banner');
    //图片的宽度
    var width = banner.offsetWidth;
    //图片盒子
    var imageBox = banner.querySelector('ul:first-child');
    //点盒子
    var pointBox = banner.querySelector('ul:last-child');
    //所有的点
    var points = pointBox.querySelectorAll('li');

    //公用方法
    //加过渡
    var addTransition = function(){
        imageBox.style.transition = "all 0.3s";
        imageBox.style.webkitTransition = "all 0.3s";/*做兼容*/
    };
    //清除过渡
    var removeTransition = function(){
        imageBox.style.transition = "none";
        imageBox.style.webkitTransition = "none";
    }
    //定位
    var setTranslateX = function(translateX){
        imageBox.style.transform = "translateX("+translateX+"px)";
        imageBox.style.webkitTransform = "translateX("+translateX+"px)";
    }

    //功能实现
    //自动轮播  定时器  无缝衔接  动画结束瞬间定位
    var index = 1;
    var timer = setInterval(function(){
        index++ ;   //自动轮播到下一张
        //改变定位  动画的形式去改变  transition transform translate
        addTransition();    //加过渡动画
        setTranslateX(-index * width);  //定位
    },3000);

    //等过渡结束之后来做无缝衔接
    my.transitionEnd(imageBox, function(){
        //处理事件结束后的业务逻辑
        if(index > imageCount ){
            index = 1;
        }else if(index <= 0){
            index = imageCount;
        }
        removeTransition(); //清除过渡
        setTranslateX(-index * width);  //定位
        setPoint(); //设置底部显示当前图片对应的圆角
    });

    //改变当前样式  当前图片的索引
    var setPoint = function(){
        //清除上一次的now
        for(var i = 0 ; i < points.length ; i++){
            points[i].className = " ";
        }
        //给图片对应的点加上样式
        points[index-1].className = "now";
    }
    //touch事件
    var startX = 0; //记录起始  刚刚触摸的点的位置 x的坐标
    var moveX = 0;  //滑动的时候x的位置
    var distanceX = 0;  //滑动的距离
    var isMove = false; //是否滑动过

    imageBox.addEventListener('touchstart', function(e){
        clearInterval(timer);   //清除定时器
        startX = e.touches[0].clientX;  //记录起始X
    });

    imageBox.addEventListener('touchmove',function(e){
        moveX = e.touches[0].clientX;   //滑动时候的X
        distanceX = moveX - startX; //计算移动的距离
        //计算当前定位  -index*width+distanceX
        removeTransition(); //清除过渡
        setTranslateX(-index * width + distanceX);  //实时的定位
        isMove = true;  //证明滑动过
    });

    //在模拟器上模拟的滑动会有问题 丢失的情况  最后在模拟器的时候用window
    imageBox.addEventListener('touchend', function(e){
        // 滑动超过 1/3 即为滑动有效，否则即为无效，则吸附回去
        if(isMove && Math.abs(distanceX) > width/3){
            //5.当滑动超过了一定的距离  需要 跳到 下一张或者上一张  （滑动的方向）*/
            if(distanceX > 0){  //上一张
                index --;
            }
            else{   //下一张
                index ++;
            }
        }
        addTransition();    //加过渡动画
        setTranslateX(-index * width);    //定位

        if(index > imageCount ){
            index = 1;
        }else if(index <= 0){
            index = imageCount;
        }
        setPoint();

        //重置参数
        startX = 0;
        moveX = 0;
        distanceX = 0;
        isMove = false;
        //加定时器
        clearInterval(timer);   //严谨 再清除一次定时器
        timer= setInterval(function(){
            index++ ;  //自动轮播到下一张
            addTransition();    //加过渡动画
            setTranslateX(-index * width);    //定位
        },3000);
    });
};