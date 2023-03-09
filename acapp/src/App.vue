<template>
  <div>
    <div>Bot昵称:{{bot_name}}</div>
    <div>Bot战力:{{bot_rating}}</div>
  </div>
<router-view></router-view>
</template>

<script>
import $ from 'jquery';
import {ref} from 'vue';// 定义变量之前需要实现导入ref

export default{

  // 定义变量 bot_name bot_rating
  name:"App",
  setup:()=>{
    // 定义两个变量 存放后端解析出来的数据
    let bot_name = ref("");
    let bot_rating = ref("");

    // 将后端信息提取出来  先定义好后端的解析路径 采用get方式进行获取  打印成功的消息
    // ajax请求 返回resp消息 包含后端的数据
    $.ajax({
      url:"http://localhost:8080/pk/getbotinfo/",
      type:"get",
      success:resp=>{
        // console.log(resp);
        bot_name.value = resp.name;
        bot_rating.value = resp.rating;// 解析后端的数据
      }
    });
    // 返回后端获取的数据给上面的div标签
    return {
      bot_name,
      bot_rating
    }

  }
}
</script>


<style>

/* 设置背景 */

body{
  background-image:url("@/assets/background.jpg");
  background-size:cover;
}

</style>
