<template>
  <div>
    <div class="alert alert-danger alert-dismissible fade show" role="alert" v-if="alert.isOpen">
      {{ alert.msg }}
      <button type="button" class="close" @click="alert.isOpen = false">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <p>Total Products : {{ total }}</p>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Name</th>
          <th scope="col">Description</th>
          <th scope="col">Code</th>
          <th scope="col">Number</th>
          <th scope="col">Friendly Name</th>
          <th scope="col">Show Online</th>
          <th scope="col">Always Prompt</th>
          <th scope="col">Modifier</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="p in products" :key="p.id">
          <th scope="row">{{ p.productId }}</th>
          <td>{{ p.name }}</td>
          <td>{{ p.description }}</td>
          <td>{{ p.code }}</td>
          <td>{{ p.number }}</td>
          <td>{{ p.friendlyName }}</td>
          <td>{{ p.showOnline }}</td>
          <td>{{ p.alwaysPrompt }}</td>
          <td>{{ p.modifier }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>


<script>

export default {
  name: "Users",
  data() {
    return {
      alert: {
        isOpen: false,
        msg: ""
      },
      products: [],
      total: 0
    };
  },
  created() {
    this.$http.get("products/db").then(
      response => {
        this.products = response.body.data;
        this.total = response.data.totalCount;
      },
      () => {
        this.alert.isOpen = true;
        this.alert.msg = "Products couldn't fetch from server!";
      }
    );
  }
};
</script>

<style scoped>
#action-button {
  margin: 0px 2% 0px 2%;
}
</style>


