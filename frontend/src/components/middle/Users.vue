<template>
  <div class="users datatable">
    <div class="caption">User</div>
    <table>
      <template v-if="users && users.length!== 0">
        <thead>
        <tr>
          <th>Id</th>
          <th>Login</th>
          <th>Name</th>
        </tr>
        </thead>
        <tbody v-for="user in users" :key="user.id">
        <tr>
          <td class="id">{{ user.id }}</td>
          <td class="login">{{ user.login }}</td>
          <td class="name">{{ user.name }}</td>
        </tr>
        </tbody>
      </template>
      <template v-else>
        No users
      </template>
    </table>
  </div>
</template>

<script>
export default {
  name: "Users",
  props: ["users"]
}
</script>

<style scoped>

</style>