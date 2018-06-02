<template>
    <div class="container">
        <div class="shipper_info">
            <div class="text">发货人</div>
            <input class="shipper_input" type="txt" placeholder="发货人姓名" autofocus="true" v-model="shipper">
            <input class="shipper_mobile_inout" type="mobile" placeholder="发货人手机号" v-model="shipper_mobile">
        </div>
        <div class="shipper_info">
            <div class="text">发货人</div>
            <input class="shipper_input" type="txt" placeholder="发货人姓名" autofocus="true" v-model="shipper">
            <input class="shipper_mobile_inout" type="mobile" placeholder="发货人手机号" v-model="shipper_mobile">
        </div>
        <div class="shipper_info">
            <div class="text">所在地区</div>
            <WeexSpinner class="spinner" type="list"></WeexSpinner>
            <input class="shipper_mobile_inout" type="mobile" placeholder="发货人手机号" v-model="shipper_mobile">
        </div>

    </div>
</template>

<style>
    body {
        background-color: #c3c3c3;;
    }

    .spinner {
        flex: 2;
        height: 80px;
    }

    .shipper_info {
        height: 80px;
        margin-top: 2px;
        flex-direction: row;
        justify-items: center;
        background-color: white;
    }

    .shipper_input {
        flex: 2;
        height: 80px;
        font-size: 30px;
        text-align: start;
        align-items: center;
        justify-content: flex-start;
        background: none;
    }

    .text {
        flex: 1;
        font-size: 30px;
        height: 80px;
        padding-left: 20px;
        justify-content: center;
        color: #000000;
    }

    .shipper_mobile_inout {
        flex: 2;
        height: 80px;
        text-align: start;
        align-items: center;
        justify-content: center;
        font-size: 30px;
        padding-left: 30px;
        background: none;
    }


</style>

<script>
    const modal = weex.requireModule('modal');
    module.exports = {
        data() {
            return {
                list: [
                   "215","asdasd","dfgas","casd","dasd","btrbhtr"
                ]
            }
        },
        methods: {
            onrefresh(args) {
                modal.toast({
                    message: '选择的魔戒是' + args.data,
                    duration: 0.3
                })
            },
        }
    }
</script>