<template>
  <div class="container">

    <div class="jumbotron jumbotron-fluid">
      <div class="container">
        <h1 class="display-4">Peiso Exam</h1>
        <p class="lead">This project fetch the product and order from Kounta API and display</p>
      </div>
    </div>

    <ul class="nav nav-tabs">
      <li class="nav-item">
        <router-link to="/product" class="nav-link" active-class="active" exact>Products</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/order" class="nav-link" active-class="active">Orders</router-link>
      </li>
    </ul>

    <router-view></router-view>

  </div>
</template>

<script>
import Products from './components/Product/Products'

export default {
  name: 'app',
  components: {
    Products,
  }
  
}
</script>

<style>
.error {
  color: red;
}

.error-input {
  border-color: red;
  color: red;
}
</style>
