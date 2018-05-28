<template>
    <div style="padding-top: 150px">
        <input class="input" type="tel" placeholder="请输入帐号" autofocus="true"
               v-model="abc">
        <input class="input" type="password" placeholder="请输入密码"
               v-model="bcd"
        >
        <wxc-button class="wxbtn" text="登录"
                    @wxcButtonClicked="wxcButtonClicked"></wxc-button>
        <text class="output">{{abc}}</text>
        <text class="output">{{bcd}}</text>
    </div>
</template>

<style>
    body {
        background-color: #ffffff;
    }

    .input {
        font-size: 45px;
        width: 600px;
        margin-top: 50px;
        margin-left: 75px;
        padding: 25px;
        color: #666666;
        border-width: 2px;
        border-style: solid;
        border-color: #41B883;
    }

    .wxbtn {
        margin: 20px;
        color: #00B4FF;
        background-color: #00B4FF;
    }

    .output {
        font-size: 45px;
        width: 600px;
        padding: 25px;
        color: #666666;
    }
</style>

<script>
    import {WxcButton} from 'weex-ui';

    const modal = weex.requireModule('modal');
    const stream = weex.requireModule('stream');
    const Weex2NativeNavigationModule = weex.requireModule('Weex2NativeNavigationModule');
    module.exports = {
        components: {WxcButton},

        data: function () {
            return {
                getResult: {
                    code: '',
                    msg: '',
                    pageNumber: '',
                    total: '',
                    rows: Object,
                },
                postResult: 'loading...',
                abc: '',
                bcd: '',
            }
        },
        methods: {

            toParams(obj) {
                let param = "";
                for (let name in obj) {
                    if (typeof obj[name] != 'function') {
                        param += "&" + name + "=" + encodeURI(obj[name])
                    }
                }
                return param.substring(1)
            },
            wxcButtonClicked() {
                let me = this;
                modal.toast({
                    message: me.abc + me.bcd,
                    duration: 0.3
                });
//                const POST_URL = 'http://123.52.40.57:2018/oa/login';
                const POST_URL = 'http://192.168.1.81:8088/login';
                stream.fetch({
                    method: 'POST',
                    url: POST_URL,
                    type: 'json',
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    body: this.toParams({
                        userCode: me.abc,
                        password: me.bcd,
                        terminal: 'Android',
                    })
                }, function (ret) {
                    if (!ret.ok) {
                        modal.toast({
                            message: "接口连接失败",
                            duration: 2.0
                        })
                    } else {
                        me.getResult = JSON.stringify(ret.data);
                        let msg = "";
                        if (me.getResult.code == "200") {
                            msg = "请求成功";
                            Weex2NativeNavigweeationModule.startActivity();
                        } else {
                            msg = "请求失败";
                        }
                        modal.toast({
                            message: msg + me.getResult.toString(),
                            duration: 2.0
                        });
                    }
                }, function (response) {
                    me.postResult = "bytes received:" + response.length;
                    modal.toast({
                        message: JSON.stringify('get in progress:' + response.length + me.postResult),
                        duration: 2.0
                    })
                });
            }
        }
    };

</script>