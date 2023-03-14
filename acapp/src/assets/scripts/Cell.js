export class Cell{
// 定义一个格子
    constructor(r,c){
        this.r = r;
        this.c = c;
        this.x = c + 0.5;// 一个格子的中间
        this.y = r + 0.5;// 格子中间
    }
}