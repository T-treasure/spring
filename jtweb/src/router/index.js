import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcome.vue'
import User from '../components/user/user.vue'
import ItemCat from '../components/item/ItemCat.vue'
import ItmCatParam from '../components/item/ItemCatParam.vue'
import Item from '../components/item/Item.vue'
import AddItem from '../components/item/addItem.vue'

Vue.use(VueRouter)
const routes = [
  {path: '/', redirect: '/login'},
  {path: '/login', component: Login},
  //定义子集
  {path: '/home', component: Home, redirect: '/welcome', children: [
    {path: '/welcome', component : Welcome},
    {path: '/user', component : User},
    {path: '/itemCat', component: ItemCat},
    {path: '/itemCatParam', component: ItmCatParam},
    {path: '/item', component: Item},
	{path: '/item/addItem', component: AddItem}
  ]}

]


//1.定义路由对象
const router = new VueRouter({
  routes
})

//2.定义导航首位
//beforEach循环遍历用户的所有请求(拦截) 在其中定义回调函数(3个参数)
//        to:要访问的请求路径
//        from:从那个页面访问
//        next:表示请求放行
//业务需求: 1.如果请求/login
//         2.如果不是/login则判断是否登录token
router.beforeEach((to, from, next) => {
  if(to.path === '/login')return next()
  ////2.获取token信息
  let token = window.sessionStorage.getItem('token')
  //3.判断token是否有数据 if(token)
  //如果token不为null直接通过
  //如果token为null直接跳转登录页面
  if(!token) return next('/login')
  next()
})

export default router
