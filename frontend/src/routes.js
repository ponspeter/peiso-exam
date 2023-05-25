import Home from './components/Home'
import Products from './components/Product/Products'
import Orders from './components/Order/Orders'


export const routes = [
    { path: '/', component: Home},
    { path: '/product', component: Products},
    { path: '/order', component: Orders}
];