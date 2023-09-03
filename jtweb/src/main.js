import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import './assets/ali-icon/iconfont.css'
//导入富文本编译器
import VueQuillEditor from 'vue-quill-editor'
//导入富文本编译器对应的样式
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'


//导入axios包
import axios from 'axios'

//设定axios请求根目录
axios.defaults.baseURL = 'http://localhost:8091/'
//向vue对象中添加全局变量  以后发送ajax请求使用$http对象
Vue.prototype.$http = axios

//降幅文本编译器主场为全局可用组件
Vue.use(VueQuillEditor)

//定义格式化价格的过滤器
Vue.filter('priceFormat', (price) => {
  return (price/100).toFixed(2)
})

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
