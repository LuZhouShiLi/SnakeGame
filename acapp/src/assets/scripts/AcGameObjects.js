const AC_GAME_OBJECTS = [];// 数组对象

export class AcGameObject{
    constructor(){
        AC_GAME_OBJECTS.push(this);
        this.timedelta = 0;// 这一帧指向的时间间隔 距离上一帧指向的时间间隔
        this.has_called_start = false;
    }

    start(){
        // 只执行一次
    }

    update(){
        // 每一帧执行一次  除第一帧之外
    }

    on_destroy(){
        // 删除之前执行
    }

    destroy(){
        // 销毁对象
        for(let i in AC_GAME_OBJECTS){
            const obj = AC_GAME_OBJECTS[i];
            if(obj === this){
                AC_GAME_OBJECTS
            }
        }
    }
}
let last_timestamp;// 上一次执行的时刻

// timestamp参数表示当前时刻
const step = timestamp =>{

    // 遍历所有对象
    for(let obj of AC_GAME_OBJECTS){

        // 如果该对象还没有开始
        if(!obj.has_called_start){
            obj.has_called_start = true;// 替换开始标志
            obj.start();// 启动该对象
        }else{
            obj.timedelta = timestamp - last_timestamp;// 该对象已经执行过  计算时间间隔

            // 执行更新函数
            obj.update();
        }
    }
    // 将当前时间戳设置为上一个时间戳
    last_timestamp = timestamp;

    requestAnimationFrame(step)
}

requestAnimationFrame(step)

