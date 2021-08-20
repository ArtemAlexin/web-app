<template>
  <div class="middle">
    <Sidebar :posts="viewPosts"/>
    <main>
      <Index :posts="posts" v-if="page === 'Index'"/>
      <Enter v-if="page === 'Enter'"/>
      <Users :users="users" v-if="page === 'Users'"/>
      <Register v-if="page === 'Register'"/>
      <WritePost v-if="page === 'WritePost'"/>
      <Post :post="post" v-if="page === 'Post'"/>
    </main>
  </div>
</template>

<script>
import Sidebar from "@/components/sidebar/Sidebar";
import Index from "@/components/middle/Index";
import Enter from "@/components/middle/Enter";
import Register from "@/components/middle/Register";
import Users from "@/components/middle/Users";
import WritePost from "@/components/middle/WritePost";
import Post from "@/components/middle/Post";
import axios from "axios";

export default {
  name: "Middle",
  data: function () {
    return {
      page: "Index",
      post: null
    }
  },
  components: {
    Post,
    WritePost,
    Users,
    Register,
    Enter,
    Index,
    Sidebar
  },
  props: ["posts", "users"],
  computed: {
    viewPosts: function () {
      return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
    }
  },
  beforeCreate() {
    this.$root.$on("onChangePage", (page, post) => {
      if (post) {
        axios.get(`/api/1/post/${post.id}`).then(response => {
          this.post = response.data;
          console.log(this.post);
          this.page = page
        })
      } else {
        this.page = page;
      }
    })
  }
}
</script>

<style scoped>

</style>