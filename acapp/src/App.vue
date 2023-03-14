<template>
  <NavBar/>
  <router-view></router-view>
</template>

<script>

import NavBar from '@/components/NavBar.vue'


// 导入 bootstrap依赖
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"

export default{
  components:{
    NavBar
  }
}

</script>



<style>

body{
  background-image:url("@/assets/images/background.jpg");
  background-size:cover;
}

</style>
