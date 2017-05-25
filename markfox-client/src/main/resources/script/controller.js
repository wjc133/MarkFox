var scrolling = false;

var sync = function () {
    window.scroller.unbindAction();
    var top = $(window).scrollTop();
    var clientHeight = getClientHeight();
    var windowHeight = getScrollHeight();
    var scrollPercent = top / (windowHeight - clientHeight);
    window.scroller.sync(scrollPercent);
    setTimeout(function () {
        if (!window.scroller.isScrolling()) {
            window.scroller.bindAction();
        }
    }, 500);
};

$(window).on("scroll", sync);

/********************
 * 取窗口可视范围的高度
 *******************/
function getClientHeight() {
    var clientHeight = 0;
    if (document.body.clientHeight && document.documentElement.clientHeight) {
        clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;
    }
    else {
        clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;
    }
    return clientHeight;
}

/********************
 * 取文档内容实际高度
 *******************/
function getScrollHeight() {
    return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
}