<template>
  <div id="app">
    <Header :user="user"/>
    <Middle :posts="posts" :users="users"/>
    <Footer/>
  </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";
import axios from "axios"

export default {
  name: 'App',
  components: {
    Footer,
    Middle,
    Header
  },
  methods: {
    getAllPosts() {
      axios.get("/api/1/posts").then(response => {
        this.posts = response.data;
      })
    },
    getAllUsers() {
      axios.get("/api/1/users").then(response => {
        this.users = response.data;
      })
    }
  },
  data: function () {
    return {
      user: null,
      posts: [],
      users: []
    }
  },
  beforeMount() {
    if (localStorage.getItem("jwt") && !this.user) {
      this.$root.$emit("onJwt", localStorage.getItem("jwt"));
    }

    this.getAllPosts();
    this.getAllUsers();
  },
  beforeCreate() {
    this.$root.$on("onEnter", (login, password) => {
      if (password === "") {
        this.$root.$emit("onEnterValidationError", "Password is required");
        return;
      }

      axios.post("/api/1/jwt", {
        login, password
      }).then(response => {
        localStorage.setItem("jwt", response.data);
        this.$root.$emit("onJwt", response.data);
      }).catch(error => {
        this.$root.$emit("onEnterValidationError", error.response.data);
      });
    });
    this.$root.$on("onRegister", (name, login, password, passwordConfirmation) => {
      if (password === "") {
        this.$root.$emit("onRegisterValidationError", "Password is required");
      }
      axios.post("/api/1/users", {
        name,
        login,
        password,
        passwordConfirmation
      }).then(response => {
        localStorage.setItem("jwt", response.data);
        this.getAllUsers();
        this.$root.$emit("onJwt", response.data);

      }).catch(error => {
        this.$root.$emit("onRegisterValidationError", error.response.data);
      });
    })
    this.$root.$on("onWritePost", (title, text, jwt) => {
      axios.post("/api/1/posts", {
        title, text, jwt
      }).then(() => {
        this.getAllPosts();
        this.$root.$emit("onChangePage", 'Index');
      }).catch(error => {
        this.$root.$emit("onWritePostValidationError", error.response.data);
      })
    })
    this.$root.$on("onJwt", (jwt) => {
      localStorage.setItem("jwt", jwt);

      axios.get("/api/1/users/auth", {
        params: {
          jwt
        }
      }).then(response => {
        this.user = response.data;
        this.$root.$emit("onChangePage", "Index");
      }).catch(() => this.$root.$emit("onLogout"))
    });

    this.$root.$on("onLogout", () => {
      localStorage.removeItem("jwt");
      this.user = null;
    });

  }
}
</script>

<style>
#app {

}
</style>
