// { "framework": "Vue"} 

/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(1)
)

/* script */
__vue_exports__ = __webpack_require__(2)

/* template */
var __vue_template__ = __webpack_require__(3)
__vue_options__ = __vue_exports__ = __vue_exports__ || {}
if (
  typeof __vue_exports__.default === "object" ||
  typeof __vue_exports__.default === "function"
) {
if (Object.keys(__vue_exports__).some(function (key) { return key !== "default" && key !== "__esModule" })) {console.error("named exports are not supported in *.vue files.")}
__vue_options__ = __vue_exports__ = __vue_exports__.default
}
if (typeof __vue_options__ === "function") {
  __vue_options__ = __vue_options__.options
}
__vue_options__.__file = "E:\\weexsimple\\testweex-app\\src\\components\\OrderInput.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-0798afa5"
__vue_options__.style = __vue_options__.style || {}
__vue_styles__.forEach(function (module) {
  for (var name in module) {
    __vue_options__.style[name] = module[name]
  }
})
if (typeof __register_static_styles__ === "function") {
  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)
}

module.exports = __vue_exports__
module.exports.el = 'true'
new Vue(module.exports)


/***/ }),
/* 1 */
/***/ (function(module, exports) {

module.exports = {
  "spinner": {
    "flex": 2,
    "height": "80"
  },
  "shipper_info": {
    "height": "80",
    "marginTop": "2",
    "flexDirection": "row",
    "justifyItems": "center",
    "backgroundColor": "#FFFFFF"
  },
  "shipper_input": {
    "flex": 2,
    "height": "80",
    "fontSize": "30",
    "alignItems": "center",
    "justifyContent": "flex-start",
    "background": "none"
  },
  "text": {
    "flex": 1,
    "fontSize": "30",
    "height": "80",
    "paddingLeft": "20",
    "justifyContent": "center",
    "color": "#000000"
  },
  "shipper_mobile_inout": {
    "flex": 2,
    "height": "80",
    "alignItems": "center",
    "justifyContent": "center",
    "fontSize": "30",
    "paddingLeft": "30",
    "background": "none"
  }
}

/***/ }),
/* 2 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

var modal = weex.requireModule('modal');
module.exports = {
    data: function data() {
        return {
            list: ["215", "asdasd", "dfgas", "casd", "dasd", "btrbhtr"]
        };
    },

    methods: {
        onrefresh: function onrefresh(args) {
            modal.toast({
                message: '选择的魔戒是' + args.data,
                duration: 0.3
            });
        }
    }
};

/***/ }),
/* 3 */
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["container"]
  }, [_c('div', {
    staticClass: ["shipper_info"]
  }, [_c('div', {
    staticClass: ["text"]
  }, [_vm._v("发货人")]), _c('input', {
    staticClass: ["shipper_input"],
    attrs: {
      "type": "txt",
      "placeholder": "发货人姓名",
      "autofocus": "true",
      "value": (_vm.shipper)
    },
    on: {
      "input": function($event) {
        _vm.shipper = $event.target.attr.value
      }
    }
  }), _c('input', {
    staticClass: ["shipper_mobile_inout"],
    attrs: {
      "type": "mobile",
      "placeholder": "发货人手机号",
      "value": (_vm.shipper_mobile)
    },
    on: {
      "input": function($event) {
        _vm.shipper_mobile = $event.target.attr.value
      }
    }
  })]), _c('div', {
    staticClass: ["shipper_info"]
  }, [_c('div', {
    staticClass: ["text"]
  }, [_vm._v("发货人")]), _c('input', {
    staticClass: ["shipper_input"],
    attrs: {
      "type": "txt",
      "placeholder": "发货人姓名",
      "autofocus": "true",
      "value": (_vm.shipper)
    },
    on: {
      "input": function($event) {
        _vm.shipper = $event.target.attr.value
      }
    }
  }), _c('input', {
    staticClass: ["shipper_mobile_inout"],
    attrs: {
      "type": "mobile",
      "placeholder": "发货人手机号",
      "value": (_vm.shipper_mobile)
    },
    on: {
      "input": function($event) {
        _vm.shipper_mobile = $event.target.attr.value
      }
    }
  })]), _c('div', {
    staticClass: ["shipper_info"]
  }, [_c('div', {
    staticClass: ["text"]
  }, [_vm._v("所在地区")]), _c('WeexSpinner', {
    staticClass: ["spinner"],
    attrs: {
      "type": "list"
    }
  }, [_vm._v("list")]), _c('input', {
    staticClass: ["shipper_mobile_inout"],
    attrs: {
      "type": "mobile",
      "placeholder": "发货人手机号",
      "value": (_vm.shipper_mobile)
    },
    on: {
      "input": function($event) {
        _vm.shipper_mobile = $event.target.attr.value
      }
    }
  })], 1)])
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })
/******/ ]);