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

        this.next_cell = null;// 下一步的目标位置
        this.speed = 5;// 速度
        this.direction = -1;// -1表示没有指令 0 1 2 3 表示上右下左

        this.status = 'idle';// idle 表示静止  Move表示正在移动 die表示失败

        this.dr = [-1,0,1,0];// 四个方向行的偏移量
        this.dc = [0,1,0,-1];// 四个方向列的偏移量
    }


    start(){

    }


    set_direction(d){
        this.direction = d;
    }

    next_step(){
        // 将蛇的状态变为下一步
        // 取出当前蛇的状态
        const d = this.direction;
        this.next_cell = new Cell(this.cells[0].r + this.dr[d],this.cells[0].c + this.dc[d]);
        this.direction = -1;
        this.status = "move";// 下一步状态是移动状态
        this.step++;
    }

    update_move(){
        
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