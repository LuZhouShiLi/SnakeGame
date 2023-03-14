// import { render } from "vue";
import { AcGameObject } from "./AcGameObjects";
import { Cell } from "./Cell";


export class Snake extends AcGameObject{
    constructor(info,gamemap){
        super();// 基类构造函数
        this.id = info.id;
        this.color = info.color;
        this.gamemap = gamemap;
        this.cells = [new Cell(info.r,info.c)];// cell 0存放蛇头  cells数组 存放蛇身体

        this.speed = 5;// 速度
        this.direction = -1;// -1表示没有指令 0 1 2 3 表示上右下左

        this.status = 'idle';// idle 表示静止  Move表示正在移动 die表示失败
    }


    start(){

    }

    update_move(){
        // 蛇的移动距离 是 速度乘以 每两帧之间的时间间隔
        
        // 遍历没一条蛇
        for(const snake of this.snakes){
            if(snake.status !== "idle"){
                return false;
            }

            if(snake.direction === -1){
                return false;
            }
        }

        return true;// 两条蛇都准备好了 返回true
    }

    // 刷新函数
    update(){
        this.update_move();// 蛇移动函数
        this.render();// 每一帧进行一次渲染
    }


    render(){
        const L = this.gamemap.L;
        const ctx = this.gamemap.ctx;
        ctx.fillStyle = this.color;

        for(const cell of this.cells){
            ctx.beginPath();
            ctx.arc(cell.x * L,cell.y * L, L / 2,0,Math.PI * 2);
            ctx.fill();
        }

    }
}