<template>
  <div class="register form-box">
    <div class="header">Register</div>
    <div class="body">
      <form @submit.prevent="onRegister">
        <div class="field">
          <div class="name">
            <label for="login">Login</label>
          </div>
          <div class="value">
            <input autofocus id="login" name="login" v-model="login"/>
          </div>
        </div>
        <div class="field">
          <div class="name">
            <label for="password">Name</label>
          </div>
          <div class="value">
            <input id="name" name="name" type="text" v-model="name"/>
          </div>
        </div>
        <div class="field">
          <div class="name">
            <label for="password">Password</label>
          </div>
          <div class="value">
            <input id="password" name="password" type="password" v-model="password"/>
          </div>
        </div>
        <div class="field">
          <div class="name">
            <label for="passwordConfirmation">Password confirmation</label>
          </div>
          <div class="value">
            <input id="passwordConfirmation" name="passwordConfirmation" type="password" v-model="passwordConfirmation"/>
          </div>
        </div>
        <div class="field error">{{ error }}</div>
        <div class="button-field">
          <input type="submit" value="Register">
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data: function () {
    return {
      login: "",
      name: "",
      password: "",
      passwordConfirmation: "",
      error: ""
    }
  },
  methods: {
    onRegister: function () {
      this.$root.$emit("onRegister", this.name, this.login, this.password, this.passwordConfirmation);
    }
  },
  beforeCreate() {
    this.$root.$on("onRegisterValidationError", (error) => this.error = error);
  }
}
</script>

<style scoped>

</style>