<template>
  <div>
    <div class="alert alert-danger alert-dismissible fade show" role="alert" v-if="alert.isOpen">
      {{ alert.msg }}
      <button type="button" class="close" @click="alert.isOpen = false">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <p>Total Orders : {{ total }}</p>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">Status</th>
          <th scope="col">Notes</th>
          <th scope="col">Site Id</th>
          <th scope="col">Registered Id</th>
          <th scope="col">Staff Id</th>
          <th scope="col">Total</th>
          <th scope="col">Total Tax</th>
          <th scope="col">Paid</th>
          <th scope="col">Tips</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="o in orders" :key="o.id">
          <th scope="row">{{ p.orderId }}</th>
          <td>{{ o.status }}</td>
          <td>{{ o.notes }}</td>
          <td>{{ o.siteId }}</td>
          <td>{{ o.registeId }}</td>
          <td>{{ o.staffId }}</td>
          <td>{{ o.total }}</td>
          <td>{{ o.totalTax }}</td>
          <td>{{ o.paid }}</td>
          <td>{{ o.tips }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>


<script>

export default {
  name: "Orders",
  data() {
    return {
      alert: {
        isOpen: false,
        msg: ""
      },
      orders: [],
      total: 0
    };
  },
  created() {
    this.$http.get("orders/db").then(
      response => {
        this.orders = response.body.data;
        this.total = response.data.totalCount;
      },
      () => {
        this.alert.isOpen = true;
        this.alert.msg = "Orders couldn't fetch from server!";
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


