<template>
    <ContentField>
        我的Bot
    </ContentField>
</template>

<script>
import ContentField from '../../../components/ContentField.vue'
import $ from 'jquery'
import { useStore} from 'vuex';

export default {
    components: {
        ContentField
    },
    setup(){
        const store = useStore();// 获取全局资源
        // $.ajax({
        //     url:"http://127.0.0.1:3000/user/bot/add/",
        //     type:"POST",
        //     data:{
        //         title:"Bot的标题",
        //         description:"Bot的描述",
        //         content:"Bot的代码",
        //     },
        //     headers:
        //     {
        //         // 验证
        //         Authorization:"Bearer " + store.state.user.token,
        //     },
        //     success(resp){
        //         //  打印是否成功的消息
        //         console.log(resp);
        //     },
        //     error(resp){
        //         console.log(resp);
        //     }
        // })

        // $.ajax({
        //     url:"http://127.0.0.1:3000/user/bot/remove/",
        //     type:"POST",
        //     data:{
        //         // 删除bot_id 为1 的数据库Bot记录
        //         bot_id:1,
        //     },
        //     headers:
        //     {
        //         // 验证
        //         Authorization:"Bearer " + store.state.user.token,
        //     },
        //     success(resp){
        //         //  打印是否成功的消息
        //         console.log(resp);
        //     },
        //     error(resp){
        //         console.log(resp);
        //     }
        // })
        // $.ajax({
        //     url:"http://127.0.0.1:3000/user/bot/update/",
        //     type:"POST",
        //     data:{
        //         // 删除bot_id 为1 的数据库Bot记录
        //         bot_id:3,
        //         title:"更新的标题",
        //         description:"更新的描述",
        //         content:"更新的代码",
        //     },
        //     headers:
        //     {
        //         // 验证
        //         Authorization:"Bearer " + store.state.user.token,
        //     },
        //     success(resp){
        //         //  打印是否成功的消息
        //         console.log(resp);
        //     },
        //     error(resp){
        //         console.log(resp);
        //     }
        // })

        $.ajax({
            url:"http://127.0.0.1:3000/user/bot/getlist/",
            type:"get",
            headers:
            {
                // 验证
                Authorization:"Bearer " + store.state.user.token,
            },
            success(resp){
                //  打印是否成功的消息
                console.log(resp);
            },
            error(resp){
                console.log(resp);
            }
        })

    }
}
</script>

<style scoped>
</style>