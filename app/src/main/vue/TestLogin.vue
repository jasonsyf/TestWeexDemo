<template>
    <div class="login" id="login">
        <div class="log-email">
            <input type="text" placeholder="请输入帐号"
                   class="'log-input' + (account==''?' log-input-empty':'')"
                   v-model="abc">
            <input type="password" placeholder="请输入密码" class="'log-input' + (password==''?' log-input-empty':'')"
                   v-model="bcd"
            >
            <wxc-button text="确定"
                        @wxcButtonClicked="wxcButtonClicked"></wxc-button>
        </div>
    </div>
</template>

<style>
    body {
        background-color: #ffffff;
    }
</style>

<script>
    import {WxcButton} from 'weex-ui';

    const modal = weex.requireModule('modal');
    const stream = weex.requireModule('stream');
    const globalEvent = weex.requireModule('globalEvent');
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
            };

        },

        methods: {

            methods: {
                // 获取 native的传参
                getOptions: function () {
                    this.abc = this.$getConfig().account;
                    this.bcd = this.$getConfig().pwd;
                }
            },

            mounted: function () {
                const self = this;
                globalEvent.addEventListener('params', {}, function (e) {

                });
            },
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
                const POST_URL = 'http://123.52.40.57:2018/oa/login';
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
                        if (me.getResult.code === "200") {
                            msg = "请求成功"
                        } else {
                            msg = "请求失败"
                        }
                        modal.toast({
                            message: msg + me.getResult.toString(),
                            duration: 2.0
                        });
                        Weex2NativeNavigationModule.startActivity();
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