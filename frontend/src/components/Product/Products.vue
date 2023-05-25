<template>
  <div>
    <div class="alert alert-danger alert-dismissible fade show" role="alert" v-if="alert.isOpen">
      {{ alert.msg }}
      <button type="button" class="close" @click="alert.isOpen = false">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <p>Total User : {{ total }}</p>
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
import { EventBus } from "./../../event-bus";

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
        this.alert.msg = "Users couldn't fetch from server!";
      }
    );

    EventBus.$on("userIsCreated", data => {
      if (data) {
        /* if the data has id field so can be push into users array
      otherwise there is an error occur*/
        if (data.id) {
          this.products.push(data);
          this.total++;
          if (this.alert.isOpen) this.alert.isOpen = false;
        } else {
          this.alert.isOpen = true;
          this.alert.msg = "The User couldn't create! " + data;
        }
      } else {
        this.alert.isOpen = true;
        this.alert.msg = "Couldn't get any response from the server!";
      }
    });

    EventBus.$on("userIsUpdated", data => {
      if (data.id) {
        /* find the updated user according to id 
        and update the user in the users array */
        var userIndex = this.products.findIndex(user => user.id == data.id);
        if (userIndex != -1) this.$set(this.products, userIndex, data);
      } else {
        this.alert.isOpen = true;
        this.alert.msg = "The User couldn't update! " + data;
      }
    });
  }
};
</script>

<style scoped>
#action-button {
  margin: 0px 2% 0px 2%;
}
</style>


