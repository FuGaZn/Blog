<template>
  <div class="myDiv">
    <h1 style="position: absolute;left: 120px;top: -5px">{{Title}}</h1>
    <span style="position: absolute;left: 120px;top: 70px;font-size: 16px">{{Sign}}</span>
    <div>
      <el-menu>

      </el-menu>
    </div>
    <div>

    </div>
  </div>
</template>

<script>
  export default {
    name: "main-nav-bar",
    data(){
      return{
        Title:"将晨の站",
        Sign:"白首相知犹按剑，朱门先达笑弹冠。",
        categories:[
          {
            value:"",
            label:"主页"
          },
          {
            value:"",
            label:"技术"
          },
          {
            value:"",
            label:"学习"
          },
          {
            value:"",
            label:"随笔"
          },
          {
            value:"",
            label:"分享"
          }
        ]
      }
    }
  }
</script>

<style scoped>
  .myDiv{
    position: absolute;
    left: 0;
    top: 0;
    height: 120px;
    border-radius: 5px;
    width: 100%;
    background-color: #ffc09f;
  }
</style>
