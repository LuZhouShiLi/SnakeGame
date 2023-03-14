import { AcGameObject } from "./AcGameObjects";
import {Wall} from './Wall';

import { Snake } from "./Snake";// 导入蛇对象组件
export class GameMap extends AcGameObject{
    // 构造函数
    constructor(ctx,parent){
        super();
        this.ctx = ctx;
        this.parent = parent;
        this.L = 0;// 一个格子的距离
        this.rows = 13;
        this.cols = 13;
        this.inner_walls_counts = 40;// 默认20 障碍物数量
        this.walls = [];

        // 创建蛇对象 数组  一张地图 两条贪吃蛇
        this.snakes = [
        new Snake({id:0,color:"#4876EC",r:this.rows - 2,c: 1},this),
        new Snake({id:1,color:"#F94848",r: 1,c:this.cols - 2},this),
    
    ]}


    // 检查连通性  传入参数 地图  起点坐标 终点坐标
    check_connectivity(g,sx,sy,tx,ty){
        if(sx == tx && sy == ty){
            return true;// 起点等于终点
        }

        // 标记当前已经走过
        g[sx][sy] = true;

        let dx = [-1,0,1,0],dy = [0,1,0,-1];

        for(let i = 0; i < 4; i++){
            let x = sx + dx[i],y = sy + dy[i];
            if(!g[x][y] && this.check_connectivity(g,x,y,tx,ty))
            {
                return true;
            }
        }
        return false;

    }

    // 创建墙障碍物  返回值true表示 联通
    create_walls(){
        const g = [];

        // 先将所有的格子标记为false
        for(let r = 0; r < this.rows;r ++){
            g[r] = [];
            for(let c = 0; c < this.cols;c++){
                g[r][c] = false;
            }
        }

        // 给四周加上障碍物  将障碍物标记为true
        // 左右两边加上墙
        for(let r = 0; r < this.rows;r++){
            // 将所有行的第一列加上墙  标记为true
            g[r][0] = g[r][this.cols - 1] = true;
        }
        // 上下两边加上墙
        for(let c = 0; c < this.cols; c++){
            g[0][c] = g[this.rows - 1][c] = true;
        }
        // 创建随机障碍物  中间创建随机障碍物
        for(let i = 0; i < this.inner_walls_counts / 2; i++){
            for(let j = 0; j < 1000; j++){

                // 使用随机值 乘以 行数 列数  计算出一个随机的中间坐标
                let r = parseInt(Math.random() * this.rows);
                let c = parseInt(Math.random() * this.cols);


                // 如果对称的两个点都是被标记过
                if(g[r][c] || g[this.rows - 1 - r][this.cols - 1 - c]){
                    continue;
                }

                // 左下角 右上角 都没有障碍物 直接跳过
                if(r == this.rows - 2 && c == 1 || r == 1 && c == this.cols - 2){
                    continue;
                }

                g[r][c] = g[this.rows - 1 - r][this.cols - 1 - c] = true;// 设置对称的障碍物  中心堆成  

                break;
            }
        }


        const copy_g = JSON.parse(JSON.stringify(g));

        // 检查连通性
        if(!this.check_connectivity(copy_g,this.rows - 2,1,1,this.cols - 2)){
            return false;// 检查 连通性
        }

        for(let r = 0; r < this.rows; r++){
            for(let c= 0; c < this.cols; c++){
                if(g[r][c]){
                    this.walls.push(new Wall(r,c,this));
                }
            }
        }
        // new Wall(0,0,this);
        return true;
    }

    // 地图对象开启  r如果不连通 尝试一千次创建 直到联通
    start(){

        // 循环1000
        for(let i = 0; i < 1000; i++){
            if(this.create_walls()){
                break;
            }
        }


        // this.create_walls();
    }



    update_size(){
        // 当浏览器缩放  更新画布尺寸
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols,this.parent.clientHeight / this.rows))
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }

    check_ready(){

        // 检查两条蛇的状态 枚举没一条蛇
        for(const snake of snakes){
            
        }


    }

    // 地图对象更新
    update(){
        this.update_size();
        this.render();

    }

    render(){
        const color_even = '#AAD751'//偶数颜色
        const color_odd = '#A2D149'//奇数颜色
        for(let r = 0 ; r < this.rows; r++){
            for(let c = 0; c < this.cols; c++){
                if(( r + c ) % 2 == 0){
                    this.ctx.fillStyle = color_even;
                }else{
                    this.ctx.fillStyle = color_odd;
                }
                this.ctx.fillRect(c* this.L, r* this.L, this.L, this.L);
            }
        }
    }


}