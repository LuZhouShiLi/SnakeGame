<template>

<PlayGround/>


</template>

<script>

// 将空白的公共组件 添加进去

import PlayGround from '../../components/PlayGround.vue'

export default{
    components:{
        PlayGround
    }
}

</script>


<style scoped>
div.playground{
    width:60vw;
    height:70vh;
    /* margin 表示左右上下的距离间隔  auto表示居中 */
    margin:40px auto;
}

</style>
