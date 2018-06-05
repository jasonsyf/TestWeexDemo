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
            <WeexSpinner class="spinner"  type="util.toJSON(list)" @refresh="onrefresh"> </WeexSpinner>
            <input class="shipper_mobile_inout" type="mobile" placeholder="发货人手机号" v-model="shipper_mobile">
        </div>
        <div class="content">
            <text class="address">省份：{{province}}</text>
            <text class="address">城市：{{city}}</text>
            <text class="address">区域：{{area}}</text>

            <text class="button" @click="citypicker">选择地址</text>

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
    .content {
        flex: 1;
        justify-content: center;
        align-items: center;
    }
    .address {
        height: 56px;
        line-height: 56px;
        text-align: center;
        font-size: 26px;
    }
    .button {
        font-size: 24px;
        text-align: center;
        margin-top: 20px;
        padding-top: 20px;
        padding-bottom: 20px;
        padding-left: 30px;
        padding-right: 30px;
        color: #ffffff;
        background-color: #00B4FF;
    }


</style>

<script>
    const modal = weex.requireModule('modal');
    const citypickerview = weex.requireModule('WXCitypickerModule');
    module.exports = {
        data() {
            return {
                list: [
                   "215","asdasd","dfgas","casd","dasd","btrbhtr"
                ],
                province: '浙江省',
                city: '杭州',
                area: '市辖区',

            }
        },
        methods: {
            onrefresh(args) {
                modal.toast({
                    message: '选择的魔戒是' + args.data,
                    duration: 0.3
                })
            },
            citypicker() {
                citypickerview.select({
                    province: this.province,
                    city: this.city,
                    area: this.area
                }, (result) => {
                    this.province = result.province;
                    this.city = result.city;
                    this.area = result.area;

                });
            }
        }
    }
</script>