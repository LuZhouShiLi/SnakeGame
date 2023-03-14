<template>
    <div class = "playground">
        <!-- 导入地图组件 -->
        <GameMap/>
    </div>
</template>

<script>
import GameMap from './GameMap.vue'

export default{

    // 将游戏地图组件导入进去
    components:{
        GameMap,
    }
}

</script>

<style scoped>

div.playground{
    width:60vw;
    height:70vh;
    /* background:lightblue; */

    /* 距离上下左右的边距  auto代表居中 */
    margin:40px auto;
}


</style>
