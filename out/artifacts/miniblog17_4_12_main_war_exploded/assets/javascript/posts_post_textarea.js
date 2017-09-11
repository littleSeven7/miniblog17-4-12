//在页面加载完后立即执行多个函数。
function addloadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != "function") {
        window.onload = func;
    } else {
        window.onload = function () {
            if (oldonload) {
                oldonload();
            }
            func();
        }
    }
}
addloadEvent(b);

function b() {
    var pn = document.getElementById("pn");
    var lists = pn.children;

    //遍历所有状态消息
    for (var i = 0; i < lists.length; i++) {
        //全部事件代理
        lists[i].onclick = function (e) {
            //获取当前点击事件
            var e = e || window.event;
            var el = e.srcElement;
            if (!el) {
                el = e.target; //兼容火狐
            }
            /*//判断点击的类名
             switch(el.className) {
             //回复评论
             case "hf-btn hf-btn-on":
             reply(el.parentNode.parentNode.parentNode);
             break;
             }*/
        }
        var textarea = lists[i].getElementsByClassName("hf-text")[0];
        //焦点事件
        textarea.onfocus = function () {
            this.parentNode.className = 'hf hf-on';
            this.value = this.value == '评论…' ? '' : this.value;
        }
        //失焦事件
        textarea.onblur = function () {
            if (this.value == '') {
                this.parentNode.className = 'hf';
                this.value = '评论…';
            }
        }
        //键盘事件
        textarea.onkeyup = function () {
            var len = this.value.length;
            var textParentNode = this.parentNode;
            var textBtn = textParentNode.children[1];
            var textNub = textParentNode.children[2];
            if (len == 0 /*|| len>100*/) {
                textBtn.className = "hf-btn";
            } else {
                textBtn.className = "hf-btn hf-btn-on";
                this.style.color = "#333";
            }
            textNub.innerHTML = len + "/500";
        }
    }
    //遍历结束
}